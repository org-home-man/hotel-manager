package com.hotel.admin.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hotel.admin.constants.Constant;
import com.hotel.admin.model.SysUser;
import com.hotel.admin.security.GrantedAuthorityImpl;
import com.hotel.admin.security.JwtAuthenticatioToken;
import com.hotel.admin.websocket.WebSocketServer;
import com.hotel.common.entity.auth.ISysUser;
import com.hotel.common.utils.Utils;
import com.hotel.core.context.PageContext;
import com.hotel.core.context.UserContext;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.http.HttpResult;
import com.hotel.common.redis.RedisCacheTemplate;
import com.hotel.core.http.HttpStatus;
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
    /** redis中用户token key */
    private static final String TOKEN_KEY = "hotel:token_info";
    private static final String USER_KEY = "hotel:user_info:";
    private static final String TIME_KEY = "hotel:act_time:";

    @Value("${allow.login.max}")
    private String LOGIN_MODE;
    @Value("${act.expire.time}")
    private Long EXPIRE_TIME;

    /**
     * 根据请求令牌获取登录认证信息
     * @param request
     */
    public Boolean checkAuthentication(HttpServletRequest request,HttpServletResponse response) {
        Authentication authentication = null;
        // 获取请求携带的令牌
        String token = getToken(request);
        if(token != null) {
            // 请求令牌不能为空
            if(getAuthentication() == null) {

                // 上下文中Authentication为空
                Claims claims = getClaimsFromToken(token);
                if(claims == null) {
                    writeMessage(response, HttpStatus.SC_LOGIN_EXPIRE,Constant.LOGIN_EXPIRED_KEY);
                    return false;
                }
                String username = claims.getSubject();
                if(username == null) {
                    writeMessage(response, HttpStatus.SC_LOGIN_EXPIRE,Constant.LOGIN_EXPIRED_KEY);
                    return false;
                }
                //校验token是否过期
                if(isTokenExpired(token)) {
                    writeMessage(response, HttpStatus.SC_LOGIN_EXPIRE,Constant.LOGIN_EXPIRED_KEY);
                    return false;
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
            //刷新token
            refreshToken(token);
        }
        // 设置登录认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
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

        //校验是否需要提出上一个用户
        if(Constant.LOGIN_MODE_S.equals(LOGIN_MODE)){
            List<String> tokens = getTokensByUser(sysUser);
            if(Utils.isNotEmpty(tokens)){
                for (String tk : tokens) {
                    clearUserInfoByToken(tk);
                }
                //清除前一个用户的session
                WebSocketServer.webSocketMap.remove(sysUser.getId());
                logger.info("踢出上一个用户,用户名 [{}]",sysUser.getName());
            }
        }
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(USERNAME, sysUser.getName());
        claims.put(CREATED, System.currentTimeMillis());
        claims.put(AUTHORITIES, authentication.getAuthorities());
        String token = generateToken(claims);
        //缓存token 进 redis
        hSet(TOKEN_KEY,token, JSONObject.toJSONString(sysUser));
        sadd(USER_KEY+sysUser.getId(),token);
        hSet(TIME_KEY,token,System.currentTimeMillis()+"");
//        hSet()
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
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET).compact();
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
     * 根据用户id获取所有token
     *
     * @param user
     * @return

     * @date 2016年11月7日
     */
    public List<String> getTokensByUser(ISysUser user) {

        return sMembers(USER_KEY + user.getId());
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
            del(USER_KEY+user.getId());
            hDel(TIME_KEY,token);
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
     * 刷新令牌
     * @param token
     * @return
     */
    public void refreshToken(String token) {
        String refreshedToken;
        try {
//            Claims claims = getClaimsFromToken(token);
//            claims.put(CREATED, new Date());
//            refreshedToken = generateToken(claims);
            //更新redis中 token
            hSet(TIME_KEY,token,System.currentTimeMillis()+"");
        } catch (Exception e) {
            refreshedToken = null;
        }
//        return refreshedToken;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
//            Claims claims = getClaimsFromToken(token);
////            Date expiration = claims.getExpiration();
////            return expiration.before(new Date());
            String s = hGet(TIME_KEY, token);
            if(Utils.isEmpty(s)){
                return true;
            }
            //上次访问时间
            long actTime = Long.parseLong(s);
            //本次访问时间
            long now = System.currentTimeMillis();
            if(now-actTime > EXPIRE_TIME){
                clearUserInfoByToken(token);
                return true;
            }
            return false;
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
