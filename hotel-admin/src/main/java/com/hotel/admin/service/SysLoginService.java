package com.hotel.admin.service;

import com.hotel.admin.dto.LoginBean;
import com.hotel.core.http.HttpResult;

import javax.servlet.http.HttpServletRequest;

public interface SysLoginService {
    /**
     * 用户登录
     * @param loginBean
     * @param request
     * @return
     */
    HttpResult login(LoginBean loginBean, HttpServletRequest request);

    /**
     * 登出操作
     * @return
     */
    HttpResult logout();

}
