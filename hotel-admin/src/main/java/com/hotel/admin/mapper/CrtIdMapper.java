package com.hotel.admin.mapper;

import java.util.List;

import com.hotel.admin.model.CrtId;

/**
 * ---------------------------
 * 自增序列表 (CrtIdMapper)         
 * ---------------------------
 */
public interface CrtIdMapper {

	/**
	 * 添加自增序列表
	 * @param record
	 * @return
	 */
    int add(CrtId record);

    /**
     * 删除自增序列表
     * @param crtNo
     * @return
     */
    int delete(String crtNo);
    
    /**
     * 修改自增序列表
     * @param record
     * @return
     */
    int update(CrtId record);
    
    /**
     * 根据主键查询
     * @param crt_type
     * @return
     */    
    CrtId findById(String crt_type);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<CrtId> findPage();

    int autoAddUp(String crtNo);

    /**
     * 客房信息新增根据三个条件新增序列号
     * @param record
     * @return
     */
    int roomAutoAddUp(CrtId record);

    /**
     * 客房信息查询编号
     * @param record
     * @return
     */
    CrtId findByRoomId(CrtId record);

}