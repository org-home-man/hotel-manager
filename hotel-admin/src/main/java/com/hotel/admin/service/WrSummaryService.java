package com.hotel.admin.service;

import com.hotel.admin.model.WrSummary;
import com.hotel.core.page.Page;
import com.hotel.core.service.NewCurdService;

import java.util.List;

public interface WrSummaryService extends NewCurdService<WrSummary> {

    Page findPage(WrSummary record);

}
