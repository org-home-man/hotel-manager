package com.hotel.admin.mapper;

import java.util.List;

import com.hotel.admin.model.SysParaConfig;

/**
 * ---------------------------
 * 参数配置表 (SysParaConfigMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-05 11:24:48
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface SysParaConfigMapper {

	/**
	 * 添加参数配置表
	 * @param record
	 * @return
	 */
    int add(SysParaConfig record);

    /**
     * 删除参数配置表
     * @param paraSubCode2
     * @return
     */
    int delete(String paraSubCode2);
    
    /**
     * 修改参数配置表
     * @param record
     * @return
     */
    int update(SysParaConfig record);
    
    /**
     * 根据主键查询
     * @param paraSubCode2
     * @return
     */    
    SysParaConfig findById(String paraSubCode2);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<SysParaConfig> findPage();

    /**
     * 根据主键查询
     * @param paraSubCode2
     * @return
     */
    List<SysParaConfig> findKeyValue(SysParaConfig record);
    
}