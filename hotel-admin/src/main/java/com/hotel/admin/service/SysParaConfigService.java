package com.hotel.admin.service;

import com.hotel.admin.model.SysParaConfig;
import com.hotel.core.page.Page;
import com.hotel.core.service.CurdService;
import com.hotel.core.service.IService;

import java.util.List;
import java.util.Map;

/**
 * ---------------------------
 * 参数配置表 (SysParaConfigService)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-05 11:24:48
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface SysParaConfigService extends IService<SysParaConfig> {

    public Map<String,List> findKeyValue(SysParaConfig record);
    public Map<String,List> findKeyValueHotel(SysParaConfig record);
    public Map<String,List> findKeyValueHotelRoom(SysParaConfig record);

    /**
     * 分页查询系统配置参数
     * @return
     */
    Page findPage();
}
