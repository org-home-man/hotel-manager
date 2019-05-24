package com.hotel.admin.service.impl;

import com.google.code.kaptcha.Constants;
import com.hotel.admin.mapper.SysUserMapper;
import com.hotel.admin.model.SysUser;
import com.hotel.admin.redis.UserInfoCache;
import com.hotel.admin.security.JwtAuthenticatioToken;
import com.hotel.admin.service.SysLoginService;
import com.hotel.admin.util.PasswordUtils;
import com.hotel.admin.vo.LoginBean;
import com.hotel.core.context.UserContext;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Transactional
@Service
public class SysLoginServiceImpl implements SysLoginService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserInfoCache userInfoCache;

    @Override
    public HttpResult login(LoginBean loginBean, HttpServletRequest request) {
        String username = loginBean.getAccount();
        String password = loginBean.getPassword();
        String captcha = loginBean.getCaptcha();

        // 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
		Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(kaptcha == null){
            throw new GlobalException("captchaExpire");
		}
		if(!captcha.equals(kaptcha)){
            throw new GlobalException("captchaNotMatch");
		}

        // 用户信息
        SysUser user = sysUserMapper.findByName(username);

        // 账号不存在、密码错误
        if (user == null) {
            throw new GlobalException("AcIsNotException");
        }

        if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
            throw new GlobalException("pwdErException");
        }

        // 账号锁定
        if (user.getStatus() == 0) {
            throw new GlobalException("AccountException");
        }
        UserContext.setUser(user);

        // 系统登录认证
        JwtAuthenticatioToken token = new JwtAuthenticatioToken(username, password);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 执行登录认证过程
        Authentication authentication = authenticationManager.authenticate(token);
        // 认证成功存储认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌并返回给客户端

        token.setToken(userInfoCache.generateToken(authentication,user));

//        redisService.setValue(username,token.getToken(), JwtTokenUtils.EXPIRE_TIME); //登录成功缓存token
        return HttpResult.ok(token);
    }

    @Override
    public HttpResult logout() {
        userInfoCache.clearUserInfoByToken(UserContext.getToken());
        return HttpResult.ok();
    }
}
