package com.hotel.admin.mapper;

import java.util.List;

import com.hotel.admin.model.BizRoomExt;

/**
 * ---------------------------
 * 客房信息铺表 (BizRoomExtMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-07 17:16:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizRoomExtMapper {

	/**
	 * 添加客房信息铺表
	 * @param record
	 * @return
	 */
    int add(BizRoomExt record);

    /**
     * 删除客房信息铺表
     * @param roomCode
     * @return
     */
    int delete(String roomCode);
    
    /**
     * 修改客房信息铺表
     * @param record
     * @return
     */
    int update(BizRoomExt record);
    
    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */    
    BizRoomExt findById(String roomCode);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<BizRoomExt> findPage();
    
}