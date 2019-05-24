package com.hotel.admin.util;

import com.alibaba.fastjson.JSONObject;
import com.hotel.admin.redis.RedisService;
import com.hotel.admin.security.GrantedAuthorityImpl;
import com.hotel.admin.security.JwtAuthenticatioToken;
import com.hotel.common.entity.auth.ISysUser;
import com.hotel.core.context.PageContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

/**
 * JWT工具类
 * @author chenchao
 * @date Nov 20, 2018
 */
@Component
public class JwtTokenUtils implements Serializable {

	private static final long serialVersionUID = 1L;
	/*** 用户名称 */
	private static final String USERNAME = Claims.SUBJECT;
	/*** 创建时间 */
	private static final String CREATED = "created";
	/*** 权限列表 */
	private static final String AUTHORITIES = "authorities";
	/*** 密钥 */
    private static final String SECRET = "chen";
    /*** 有效期12小时  */
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;
    public static final String TOKEN_KEY = "hotel:token_info";

    @Autowired
    SecurityUtils securityUtils;
    @Autowired
	RedisService redisService;
    /**
	 * 生成令牌
	 *
	 * @param authentication
	 * @return 令牌
	 */
	public String generateToken(Authentication authentication, ISysUser sysUser) {
	    Map<String, Object> claims = new HashMap<>(3);
	    claims.put(USERNAME, securityUtils.getUsername(authentication));
	    claims.put(CREATED, new Date());
	    claims.put(AUTHORITIES, authentication.getAuthorities());
		String token = generateToken(claims);
		//缓存token 进 redis
//		redisService.hashPut(TOKEN_KEY,token, JSONObject.toJSONString(sysUser));
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
	 * 从令牌中获取用户名
	 *
	 * @param token 令牌
	 * @return 用户名
	 */
	public String getUsernameFromToken(String token) {
	    String username;
	    try {
	        Claims claims = getClaimsFromToken(token);
	        username = claims.getSubject();
	    } catch (Exception e) {
	        username = null;
	    }
	    return username;
	}
	
	/**
	 * 根据请求令牌获取登录认证信息
	 * @param request 令牌
	 * @return 用户名
	 */
	public Authentication getAuthenticationeFromToken(HttpServletRequest request) {
		Authentication authentication = null;
		// 获取请求携带的令牌
		String token = getToken(request);
		if(token != null) {
			// 请求令牌不能为空
			if(securityUtils.getAuthentication() == null) {
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
				if(validateToken(token, securityUtils.getUsername())) {
					// 如果上下文中Authentication非空，且请求令牌合法，直接返回当前登录认证信息
					authentication = securityUtils.getAuthentication();
				}
			}
		}
		if(null!=authentication){
			PageContext.init(request);
			//校验token是否存在

		}
		return authentication;
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
	    String userName = getUsernameFromToken(token);
	    return (userName.equals(username) && !isTokenExpired(token));
	}
	/**
	 * 验证令牌 从redis中
	 * @param token
	 * @return
	 */
	public Boolean validateToken(String token) {
		Object value = redisService.getValue(token);
		return !isTokenExpired(token);
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

}