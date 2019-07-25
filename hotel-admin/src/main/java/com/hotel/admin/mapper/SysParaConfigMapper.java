package com.hotel.admin.mapper;

import java.util.List;

import com.hotel.admin.model.SysParaConfig;
import com.hotel.core.mybatis.mapper.AbstractMapper;

/**
 * ---------------------------
 * 参数配置表 (SysParaConfigMapper)         
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