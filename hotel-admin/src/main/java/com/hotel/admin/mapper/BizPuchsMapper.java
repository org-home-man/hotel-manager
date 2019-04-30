package com.hotel.admin.mapper;

import java.util.List;

import com.hotel.admin.model.BizPuchs;

/**
 * ---------------------------
 * 订单信息表 (BizPuchsMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-30 12:29:00
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizPuchsMapper {

	/**
	 * 添加订单信息表
	 * @param record
	 * @return
	 */
    int add(BizPuchs record);

    /**
     * 删除订单信息表
     * @param roomCode
     * @return
     */
    int delete(String roomCode);
    
    /**
     * 修改订单信息表
     * @param record
     * @return
     */
    int update(BizPuchs record);
    
    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */    
    BizPuchs findById(String roomCode);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<BizPuchs> findPage();
    
}