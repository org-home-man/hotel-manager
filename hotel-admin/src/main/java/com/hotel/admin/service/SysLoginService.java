package com.hotel.admin.service;

import com.hotel.admin.vo.LoginBean;
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
}
