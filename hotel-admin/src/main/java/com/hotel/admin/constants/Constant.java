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
    public static final String SOCKET_ORDER_MESSAGE = "201";
    //登录过期
    public static final String LOGIN_EXPIRED_KEY = "loginExpired";
    //踢出用户
    public static final String REMOVE_LOGIN_KEY = "removeLogin";

    //待确认
    public static final String PUCHS_STAT_NO_CONFIRM ="1";
    //确认
    public static final String PUCHS_STAT_CONFIRM = "2";
    //订单取消
    public static final String PUCHS_STAT_CANCEL = "3";
    //订单自动取消
    public static final String PUCHS_STAT_CANCEL_AUTO = "4";
    //订单未结算
    public static final String PUCHS_STAT_NO_ACCOUNTS = "5";
    //订单已结算
    public static final String PUCHS_STAT_ACCOUNTS = "6";

    //客房类型 当月房价最低
    public static final String CUSTROOM_PRICE_LOWEST = "01";

    //客房类型 历史预定最多
    public static final String CUSTROOM_MAXINUM = "02";

}
