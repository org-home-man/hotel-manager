package com.hotel.admin.mapper;

import com.hotel.admin.model.MrSummary;
import com.hotel.admin.model.WrSummary;
import com.hotel.core.mybatis.mapper.AbstractMapper;

import java.util.List;

public interface MrSummaryMapper extends AbstractMapper<MrSummary> {
    /**
     *
     * @mbggenerated 2019-06-12
     */
//    MrSummary selectByPrimaryKey(Long id);

    List<MrSummary> findPage(MrSummary record);
}