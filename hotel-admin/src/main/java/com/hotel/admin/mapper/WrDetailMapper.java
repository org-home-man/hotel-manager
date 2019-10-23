package com.hotel.admin.mapper;

import com.hotel.admin.dto.WrDetailDto;
import com.hotel.admin.dto.WrPendAmtTotlDto;
import com.hotel.admin.dto.WrR0003DetailDto;
import com.hotel.admin.model.WrDetail;
import com.hotel.admin.model.WrSummary;
import com.hotel.admin.qo.WrSummaryQo;
import com.hotel.core.mybatis.mapper.AbstractMapper;

import java.util.List;

public interface WrDetailMapper extends AbstractMapper<WrDetail> {
    int impWeekData(WrDetail wrDetail);
    int insertWrSummary(WrDetail wrDetail);

    List<WrDetailDto> findR0002Page(WrSummaryQo record);

    List<WrR0003DetailDto> findR0003Page(WrSummaryQo record);

    WrPendAmtTotlDto findWrQuery(WrSummaryQo record);
}