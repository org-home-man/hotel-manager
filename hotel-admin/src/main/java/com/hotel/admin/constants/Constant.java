package com.hotel.admin.constants;

/**
 * @Author:chenchao
 * @Version: v1.0.0
 * @Despriction:
 * @Date:Created in 2019-04-20
 */
public class Constant {

    public static final String BOOL_NO = "1";

    public static final String BOOL_YES = "2";
    /** 订单类型 **/
    public static final String ORDER_TYPE = "puchs";
    /** 订单初始编码 **/
    public static final String ORDER_FIRST_CODE = "0001";

    /** 登陆模式 **/
    public static final String LOGIN_MODE_M = "1"; //多次
    public static final String LOGIN_MODE_S = "2";  //单次

    public static final String MANAGER_ROLE = "1";
    public static final String NO_MANAGER_ROLE = "2";

    public static final String SOCKET_LOGIN = "101";
    public static final String SOCKET_LOGIN_EXPIRED = "102";
    public static final String SOCKET_HEAT_BEAT = "100";
    //登录过期
    public static final String LOGIN_EXPIRED_KEY = "loginExpired";
    //踢出用户
    public static final String REMOVE_LOGIN_KEY = "removeLogin";
}
