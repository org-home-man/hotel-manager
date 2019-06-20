package com.hotel.admin.service;

import com.hotel.admin.model.MrSummary;
import com.hotel.admin.model.WrSummary;
import com.hotel.core.page.Page;
import com.hotel.core.service.NewCurdService;

import java.util.List;

public interface MrSummaryService extends NewCurdService<MrSummary> {

    Page findPage(MrSummary record);

}
