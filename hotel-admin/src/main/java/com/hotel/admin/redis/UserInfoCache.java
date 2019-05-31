package com.hotel.admin.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hotel.admin.model.SysUser;
import com.hotel.admin.security.GrantedAuthorityImpl;
import com.hotel.admin.security.JwtAuthenticatioToken;
import com.hotel.common.entity.auth.ISysUser;
import com.hotel.common.utils.Utils;
import com.hotel.core.context.PageContext;
import com.hotel.core.context.UserContext;
import com.hotel.core.http.HttpResult;
import com.hotel.common.redis.RedisCacheTemplate;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;

/**
 * 当前用户信息redis
 */
@Component
public class UserInfoCache extends RedisCacheTemplate implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 用户名称 */
    private static final String USERNAME = Claims.SUBJECT;
    /** 创建时间 */
    private static final String CREATED = "created";
    /** 权限列表 */
    private static final String AUTHORITIES = "authorities";
    /** 密钥 */
    private static final String SECRET = "chen";
    /** 有效期12小时  */
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;
    /** redis中用户token key */
    private static final String TOKEN_KEY = "hotel:token_info";

    @Value("${allow.login.max}")
    private String LOGIN_MODE;

    /**
     * 根据请求令牌获取登录认证信息
     * @param request
     */
    public void checkAuthentication(HttpServletRequest request) {
        Authentication authentication = validateTokenForRequest(request);
        // 设置登录认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 校验请求中token信息 以及redis状态
     * @param request
     * @return
     */
    public Authentication validateTokenForRequest(HttpServletRequest request){
        // 获取令牌并根据令牌获取登录认证信息
        Authentication authentication = null;
        // 获取请求携带的令牌
        String token = getToken(request);
        if(token != null) {
            //校验token是否存在
            if(!validateToken(token)){
                //token不存在
                return null;
            }
            // 请求令牌不能为空
            if(getAuthentication() == null) {
                // 上下文中Authentication为空
                Claims claims = getClaimsFromToken(token);
                if(claims == null) {
                    return null;
                }
                String username = claims.getSubject();
                if(username == null) {
                    return null;
                }
                if(isTokenExpired(token)) {
                    return null;
                }
                Object authors = claims.get(AUTHORITIES);
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                if (authors != null && authors instanceof List) {
                    for (Object object : (List) authors) {
                        authorities.add(new GrantedAuthorityImpl((String) ((Map) object).get("authority")));
                    }
                }
                authentication = new JwtAuthenticatioToken(username, null, authorities, token);
            } else {
                if(validateToken(token, getUsername())) {
                    // 如果上下文中Authentication非空，且请求令牌合法，直接返回当前登录认证信息
                    authentication = getAuthentication();
                }
            }
            //初始化page
            PageContext.init(request);
            UserContext.setToken(token);
            SysUser user = getUserByToken(token);
            UserContext.setUser(user);
        }

        return authentication;
    }


    /**
     * 获取当前用户名
     * @return
     */
    public String getUsername() {
        String username = null;
        Authentication authentication = getAuthentication();
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
            if(principal != null && principal instanceof String){
                username = (String) principal;
            }
        }
        return username;
    }

    /**
     * 获取当前登录信息
     * @return
     */
    public Authentication getAuthentication() {
        if(SecurityContextHolder.getContext() == null) {
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }


    /**
     * 生成令牌
     *
     * @param authentication
     * @return 令牌
     */
    public String generateToken(Authentication authentication, ISysUser sysUser) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(USERNAME, sysUser.getName());
        claims.put(CREATED, new Date());
        claims.put(AUTHORITIES, authentication.getAuthorities());
        String token = generateToken(claims);
        //缓存token 进 redis
        hSet(TOKEN_KEY,token, JSONObject.toJSONString(sysUser),EXPIRE_TIME);
        logger.info("生成token 存入redis中 ---> token:{}",token);
        return token;
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    /**
     * 根据token 获取用户
     *
     * @param token
     * @return

     * @date 2016年11月7日
     */
    public SysUser getUserByToken(String token) {
        String userStr = hGet(TOKEN_KEY, token);
        if (userStr == null)
            return null;
        return JSON.parseObject(userStr, SysUser.class);
    }

    /**
     * 根据用户token清除用户缓存
     *
     * @param token

     * @date 2016年11月8日
     */
    public void clearUserInfoByToken(String token) {
        SysUser user = getUserByToken(token);
        logger.info("清除redis中用户信息 {}  ---> token:{}",user==null?"":user.getName(),token);
        if (user != null) {
            hDel(TOKEN_KEY, token);

        }
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 验证令牌
     * @param token
     * @param username
     * @return
     */
    public Boolean validateToken(String token, String username) {
        String userName;
        try {
            Claims claims = getClaimsFromToken(token);
            userName = claims.getSubject();
        } catch (Exception e) {
            userName = null;
        }
        return userName!=null && (userName.equals(username) && !isTokenExpired(token));
    }
    /**
     * 验证令牌 从redis中
     * @param token
     * @return
     */
    public Boolean validateToken(String token) {
        String value = this.hGet(TOKEN_KEY,token);
        if(null==value){
            return false;
        }
        return true;
    }

    /**
     * 刷新令牌
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取请求token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String tokenHead = "Bearer ";
        if(token == null) {
            token = request.getHeader("token");
        } else if(token.contains(tokenHead)){
            token = token.substring(tokenHead.length());
        }
        if("".equals(token)) {
            token = null;
        }
        return token;
    }

    /**
     * 写入响应信息
     *
     * @param response
     * @throws IOException
     */
    private void writeMessage(HttpServletResponse response, int status,
                              String message) {
        HttpResult error = HttpResult.error(status, message);
        String result = JSON.toJSONString(error);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
