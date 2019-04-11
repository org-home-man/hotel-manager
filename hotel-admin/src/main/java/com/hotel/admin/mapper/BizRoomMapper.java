package com.hotel.admin.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotel.admin.model.BizRoom;
import com.hotel.admin.model.BizRoomQuery;
import com.hotel.admin.model.CrtId;
import com.hotel.admin.service.CrtIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ---------------------------
 * 客房信息表 (BizRoomMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-01 21:00:17
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizRoomMapper {

	/**
	 * 添加客房信息表
	 * @param record
	 * @return
	 */
    int add(BizRoom record);

    /**
     * 删除客房信息表
     * @param roomCode
     * @return
     */
    int delete(String roomCode);
    
    /**
     * 修改客房信息表
     * @param record
     * @return
     */
    int update(BizRoom record);
    
    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */    
    BizRoom findById(String roomCode);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<Map> findPageByPara(HashMap<String, Object> record);

    /**
     * 基础分页查询
     * @param record
     * @return
     */
    List<Map> findPage();


}