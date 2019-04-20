package com.hotel.common.utils;

/**
 * 描述:
 * 作者:cc
 * 时间:2018-09-19 上午9:16
 */
public class SystemUtil {

    private SystemUtil() {

    }


    public static boolean isLinux() {
        String os = System.getProperty("os.name");
        return os.toLowerCase().startsWith("linux");
    }

    public static boolean isWin() {
        String os = System.getProperty("os.name");
        return os.toLowerCase().startsWith("win");
    }

}
