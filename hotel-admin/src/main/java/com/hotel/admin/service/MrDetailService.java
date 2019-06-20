package com.hotel.admin.service;

import com.hotel.admin.dto.*;
import com.hotel.admin.model.MrDetail;
import com.hotel.admin.model.WrDetail;
import com.hotel.admin.qo.MrSummaryQo;
import com.hotel.admin.qo.WrSummaryQo;
import com.hotel.core.service.NewCurdService;

import java.util.List;
import java.util.Map;

public interface MrDetailService extends NewCurdService<MrDetail> {

    List<MrR0001DetailDto> findR0001Page(MrSummaryQo record);

    Map<String,List<MrR0001DetailDto>> selectR0001All(MrSummaryQo record);

    List<MrR0004DetailDto> findR0004Page(MrSummaryQo record);

    List<MrR0005DetailDto> findR0005Page(MrSummaryQo record);

}
