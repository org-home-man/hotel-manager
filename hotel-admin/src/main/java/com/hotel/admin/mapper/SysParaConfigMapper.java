package com.hotel.admin.mapper;

import java.util.List;

import com.hotel.admin.model.SysParaConfig;
import com.hotel.core.mybatis.mapper.AbstractMapper;

/**
 * ---------------------------
 * 参数配置表 (SysParaConfigMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-05 11:24:48
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface SysParaConfigMapper extends AbstractMapper<SysParaConfig> {

    /**
     * 根据主键查询
     * @param record
     * @return
     */
    List<SysParaConfig> findKeyValue(SysParaConfig record);
    
}