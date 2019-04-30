package com.hotel.admin.mapper;

import java.util.List;

import com.hotel.admin.model.BizPuchsExt;

/**
 * ---------------------------
 * 订单信息辅助表 (BizPuchsExtMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-30 12:34:59
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizPuchsExtMapper {

	/**
	 * 添加订单信息辅助表
	 * @param record
	 * @return
	 */
    int add(BizPuchsExt record);

    /**
     * 删除订单信息辅助表
     * @param roomCode
     * @return
     */
    int delete(String roomCode);
    
    /**
     * 修改订单信息辅助表
     * @param record
     * @return
     */
    int update(BizPuchsExt record);
    
    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */    
    BizPuchsExt findById(String roomCode);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<BizPuchsExt> findPage();
    
}