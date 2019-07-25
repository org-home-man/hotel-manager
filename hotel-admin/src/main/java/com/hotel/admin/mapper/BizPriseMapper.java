package com.hotel.admin.mapper;

import java.util.List;
import java.util.Map;

import com.hotel.admin.dto.HotelRoomQry;
import com.hotel.admin.model.BizPrise;

/**
 * ---------------------------
 * 客房牌价表 (BizPriseMapper)         
 * ---------------------------
 */
public interface BizPriseMapper {

	/**
	 * 添加客房牌价表
	 * @param record
	 * @return
	 */
    int add(BizPrise record);

    /**
     * 删除客房牌价表
     * @param roomCode
     * @return
     */
    int delete(String roomCode);
    
    /**
     * 修改客房牌价表
     * @param record
     * @return
     */
    int update(BizPrise record);
    
    /**
     * 根据主键查询
     * @param
     * @return
     */    
    List<BizPrise> findById(BizPrise code);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<BizPrise> findPage();

    /*
    根据roomCode查询
     */
    List<BizPrise> queryById(String roomCode);

    /*
     merge 更新插入数据库
    */
    int addByUser(BizPrise code);
    int updateByUser(BizPrise code);

    List<BizPrise> findByDate(HotelRoomQry record);
    
}