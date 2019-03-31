package com.hotel.admin.mapper;

import com.hotel.admin.model.BizHotl;

import java.util.List;


/**
 * ---------------------------
 * 酒店信息表 (BizHotlMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-30 17:15:22
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizHotlMapper {

	/**
	 * 添加酒店信息表
	 * @param record
	 * @return
	 */
    int add(BizHotl record);

    /**
     * 删除酒店信息表
     * @param hotelCode
     * @return
     */
    int delete(Long hotelCode);
    
    /**
     * 修改酒店信息表
     * @param record
     * @return
     */
    int update(BizHotl record);
    
    /**
     * 根据主键查询
     * @param hotelCode
     * @return
     */    
    BizHotl findById(String hotelCode);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<BizHotl> findPage();
    
}