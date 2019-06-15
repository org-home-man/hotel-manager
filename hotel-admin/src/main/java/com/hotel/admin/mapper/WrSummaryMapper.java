package com.hotel.admin.mapper;

import com.hotel.admin.model.WrDetail;
import com.hotel.admin.model.WrSummary;
import com.hotel.core.mybatis.mapper.AbstractMapper;

import java.util.List;
import com.hotel.core.mybatis.mapper.AbstractMapper;

public interface WrSummaryMapper extends AbstractMapper<WrSummary> {

    List<WrSummary> findPage(WrSummary record);

}