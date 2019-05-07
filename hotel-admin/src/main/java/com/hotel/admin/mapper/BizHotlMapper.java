package com.hotel.admin.mapper;

import com.hotel.admin.dto.BizHotelQueryDto;
import com.hotel.admin.model.BizHotl;
import com.hotel.core.mybatis.mapper.AbstractMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ---------------------------
 * 酒店信息表 (BizHotlMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-30 17:15:22
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizHotlMapper extends AbstractMapper<BizHotl>{


    List<Map> findPageByPara(BizHotelQueryDto dto);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<BizHotl> findPage();

}