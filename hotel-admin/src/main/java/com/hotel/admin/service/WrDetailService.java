package com.hotel.admin.service;

import com.hotel.admin.dto.WrDetailDto;
import com.hotel.admin.model.WrDetail;
import com.hotel.admin.model.WrSummary;
import com.hotel.admin.qo.WrSummaryQo;
import com.hotel.core.page.Page;
import com.hotel.core.service.NewCurdService;

import java.util.List;
import java.util.Map;

public interface WrDetailService extends NewCurdService<WrDetail> {

    List<WrDetailDto> findR0002Page(WrSummaryQo record);

    Map<String,List<WrDetailDto>> selectAll(WrSummaryQo record);

    List<WrDetailDto> findR0003Page(WrSummaryQo record);

}
