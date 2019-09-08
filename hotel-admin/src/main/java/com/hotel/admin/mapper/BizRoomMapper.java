package com.hotel.admin.mapper;

import com.hotel.admin.dto.BizRoomQuery;
import com.hotel.admin.dto.RecommendRoomQuery;
import com.hotel.admin.model.BizRoom;

import java.util.List;
import java.util.Map;

/**
 * ---------------------------
 * 客房信息表 (BizRoomMapper)         
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
     * @param BizRoom
     * @return
     */
    int delete(BizRoom record);
    
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
     * @param bizRoomQuery
     * @return
     */    
    List<Map> findPageByPara(BizRoomQuery bizRoomQuery);

    /**
     * 基础分页查询
     * @param record
     * @return
     */
    List<Map> findPage();

    /**
     * 查询是否存在推荐房间
     */
    List<BizRoom> findByRecommend(RecommendRoomQuery recommendRoomQuery);

    /**
     * 根据hotelCode查询客房信息
     */
    List<BizRoom> findByHtlCd(String hotelCode);

    String selectMaxSequence();
}