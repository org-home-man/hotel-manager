package com.hotel.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FileName:     DateUtils.java
 * Company:      
 *
 
 * @version      V1.0
 * @date:        2016年6月29日 上午10:13:26
 * -------------------------------------------------------------------
 * @Description: 日期时间工具类
 *
 */
public class DateUtils {
	
	/**
	 * 
	 * @Description 根据表达式获取当前时间格式
	 * @param expression
	 * @return
	 */
	public static String getTime(String expression){
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat(expression);
		return f.format(d);
	}
	/**
	 * 获取当前时间
	 * @return
	
	 * @date 2016年6月29日
	 */
	public static String getNowTime(){
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		return f.format(d);
	}
	/**
	 * 获取当前月份
	 * @return
	 */
	public static String getSimpleMonth(){
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		return f.format(d);
	}
	
	/**
	 * 获取日
	 * @return
	
	 * @date 2016年7月25日
	 */
	public static String getDay(){
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("dd");
		return f.format(d);
	}
	
	/**
	 * 获取月份
	 * @return
	
	 * @date 2016年7月25日
	 */
	public static String getMonth(){
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("MM");
		return f.format(d);
	}
	
	/**
	 * 获取2位年
	 * @return
	
	 * @date 2016年7月25日
	 */
	public static String get2Year(){
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yy");
		return f.format(d);
	}
	
	/**
	 * 获取4位年
	 * @return
	
	 * @date 2016年7月25日
	 */
	public static String get4Year(){
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy");
		return f.format(d);
	}
}
