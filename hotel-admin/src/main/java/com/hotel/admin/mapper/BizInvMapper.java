package com.hotel.admin.mapper;

import java.util.List;

import com.hotel.admin.model.BizInv;

/**
 * ---------------------------
 * 客房库存表 (BizInvMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-08 16:15:59
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizInvMapper {

	/**
	 * 添加客房库存表
	 * @param record
	 * @return
	 */
    int add(BizInv record);

    /**
     * 删除客房库存表
     * @param roomCode
     * @return
     */
    int delete(String roomCode);
    
    /**
     * 修改客房库存表
     * @param record
     * @return
     */
    int update(BizInv record);
    
    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */    
    BizInv findById(String roomCode);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<BizInv> findPage();
    
}