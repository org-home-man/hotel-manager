package com.hotel.admin.controller.report;

import com.hotel.admin.model.WrSummary;
import com.hotel.admin.service.WrSummaryService;
import com.hotel.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wrSummary")
public class WrSummaryController {

    @Autowired
    private WrSummaryService wrSummaryService;

    @PostMapping("/page")
    public HttpResult findPage(WrSummary wrSummary) {
        return HttpResult.ok(wrSummaryService.findPage(wrSummary));
    }

}
