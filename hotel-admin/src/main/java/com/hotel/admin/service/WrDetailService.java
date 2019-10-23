package com.hotel.admin.service;

import com.hotel.admin.dto.WrDetailDto;
import com.hotel.admin.dto.WrPendAmtTotlDto;
import com.hotel.admin.dto.WrR0003DetailDto;
import com.hotel.admin.model.WrDetail;
import com.hotel.admin.model.WrSummary;
import com.hotel.admin.qo.WrSummaryQo;
import com.hotel.core.page.Page;
import com.hotel.core.service.NewCurdService;

import java.util.List;
import java.util.Map;

public interface WrDetailService extends NewCurdService<WrDetail> {

    List<WrDetailDto> findR0002Page(WrSummaryQo record);

    Map<String,List<WrDetailDto>> selectR0002All(WrSummaryQo record);

    List<WrR0003DetailDto> findR0003Page(WrSummaryQo record);

    Map<String,List<WrR0003DetailDto>> selectR0003All(WrSummaryQo record);

    WrPendAmtTotlDto findWrQuery(WrSummaryQo record);

}
