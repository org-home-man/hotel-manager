package com.hotel.admin.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotel.admin.model.HotelArea;
import com.hotel.core.page.PageRequest;

/**
 * ---------------------------
 * 地区码表 (HotelAreaMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-13 16:24:13
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface HotelAreaMapper {

	/**
	 * 添加地区码表
	 * @param record
	 * @return
	 */
    int add(HotelArea record);

    /**
     * 删除地区码表
     * @param areaCode
     * @return
     */
    int delete(String areaCode);
    
    /**
     * 修改地区码表
     * @param record
     * @return
     */
    int update(HotelArea record);
    
    /**
     * 根据主键查询
     * @param areaCode
     * @return
     */    
    HotelArea findById(String areaCode);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<HotelArea> findPage();

    List<Map>  findAreaPage(HashMap<String, Object> record);

    List<HotelArea> findEname(HotelArea hotelArea);
    List<HotelArea> findCname(HotelArea hotelArea);


}