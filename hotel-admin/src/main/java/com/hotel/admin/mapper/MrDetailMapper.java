package com.hotel.admin.mapper;

import com.hotel.admin.dto.MrR0001DetailDto;
import com.hotel.admin.dto.MrR0004DetailDto;
import com.hotel.admin.dto.MrR0005DetailDto;
import com.hotel.admin.model.MrDetail;
import com.hotel.admin.model.WrDetail;
import com.hotel.admin.qo.MrSummaryQo;
import com.hotel.core.mybatis.mapper.AbstractMapper;

import java.util.List;

public interface MrDetailMapper extends AbstractMapper<MrDetail> {
    int impMonDeptData(MrDetail mrDetail);
    int impMonUserData(MrDetail mrDetail);
    int updDeptInfor(MrDetail mrDetail);
    int updHotelName(MrDetail mrDetail);
    int updPandAmt(MrDetail mrDetail);

    List<MrR0004DetailDto> r0004ReportSelect(MrSummaryQo record);

    List<MrR0005DetailDto> r0005ReportSelect(MrSummaryQo record);
}