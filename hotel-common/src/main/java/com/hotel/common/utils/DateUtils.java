package com.hotel.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

	/**
	 * 获取时间格式化字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getDateString(Date date,String pattern){
		SimpleDateFormat f = new SimpleDateFormat(pattern);
		return f.format(date);
	}

	/**
	 * 把时间格式到一天的最后
	 * @param time
	 * @return
	 */
	public static Date set2beEndOfDay(Date time) {
		if(null == time){return null;}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 计算两个时间之间的天数
	 * @return
	 */
	public static int getDateDiff(Date d1,Date d2){
		d1 = set2beEndOfDay(d1);
		d2 = set2beEndOfDay(d2);

		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(d1);
		calendar2.setTime(d2);

		Long t1 = calendar1.getTimeInMillis();
		Long t2 = calendar2.getTimeInMillis();
		int a = 24 * 60 * 60 * 1000;
		BigDecimal b1 = new BigDecimal(t1.toString());
		BigDecimal b2 = new BigDecimal(t2.toString());
		BigDecimal ba = new BigDecimal(String.valueOf(a));

		BigDecimal distance = b1.subtract(b2).divide(ba,2, RoundingMode.HALF_UP);
		if (distance.compareTo(BigDecimal.ZERO) > 0
				&& distance.compareTo(BigDecimal.ONE) < 0)
			return 1;
		if (distance.compareTo(BigDecimal.ONE.negate()) > 0
				&& distance.compareTo(BigDecimal.ZERO) < 0)
			return -1;

		Long val = distance.longValue();
		if (distance.compareTo(new BigDecimal(val.toString())) > 0)
			val += 1;
		return  Math.abs(val.intValue());
	}

	/**
	 *
	 * @param beginningDate
	 * @param days2add
	 * @return
	 */
	public static Date addDays(Date beginningDate, int days2add) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginningDate);
		calendar.add(Calendar.DATE, days2add);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date addYear(Date beginningDate, int year2add) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginningDate);
		calendar.add(Calendar.YEAR, year2add);
		return calendar.getTime();
	}

	public static Date addHour(Date beginningDate, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginningDate);
		calendar.add(Calendar.HOUR, hours);
		return calendar.getTime();
	}

	public static Date addMonth(Date beginningDate, int monthes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginningDate);
		calendar.add(Calendar.MONTH, monthes);
		return calendar.getTime();
	}

	public static Date addMin(Date beginningDate, int mins) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginningDate);
		calendar.add(Calendar.MINUTE, mins);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date addSec(Date beginningDate, int sec) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginningDate);
		calendar.add(Calendar.SECOND, sec);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getDate(String date, String pattern) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
		}
		return null;
	}

	public static Date getDate(Date date, String pattern) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String datestr=simpleDateFormat.format(date);
			return simpleDateFormat.parse(datestr);
		} catch (ParseException e) {
		}
		return null;
	}
}
