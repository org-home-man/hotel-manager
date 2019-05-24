package com.hotel.admin.security;

import com.hotel.admin.redis.UserInfoCache;
import com.hotel.core.context.UserContext;
import com.hotel.core.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: hotel-admin
 * @ClassName: JwtLogoutSuccessHandler
 * @Author: cc
 * @Description: 退出登录处理
 * @Date: 2019-05-25 0:49
 * @Version: 1.0
 */
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

    UserInfoCache userInfoCache;
    public JwtLogoutSuccessHandler(UserInfoCache userInfoCache){
        this.userInfoCache = userInfoCache;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        userInfoCache.clearUserInfoByToken(UserContext.getToken());
        response.setStatus(HttpStatus.SC_OK);
        response.getWriter().flush();
    }
}
