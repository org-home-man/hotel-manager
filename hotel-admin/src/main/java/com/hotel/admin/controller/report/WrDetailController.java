package com.hotel.admin.controller.report;

import com.hotel.admin.dto.WrDetailDto;
import com.hotel.admin.dto.WrR0003DetailDto;
import com.hotel.admin.qo.WrSummaryQo;
import com.hotel.admin.service.WrDetailService;
import com.hotel.admin.util.ExcelUtils;
import com.hotel.core.annotation.SystemControllerLog;
import com.hotel.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wrDetail")
public class WrDetailController {

    @Autowired
    private WrDetailService wrDetailService;

    /**
     * 基础查询
     *
     * @param
     * @return
     */
    @PostMapping(value = "/r0002Page")
    public HttpResult r0002Page(WrSummaryQo record) {
        return HttpResult.ok(wrDetailService.findR0002Page(record));
    }

    /*
    导出报表
     */
    @RequestMapping("/r0002ExportExcel")
    @SystemControllerLog(description = "r0002报表Excel导出")
    public void exportR0002Excel(HttpServletResponse response, WrSummaryQo record) throws IOException {
        Map<String,List<WrDetailDto>> map = wrDetailService.selectR0002All(record);
        ExcelUtils.writeMoreSheetExcel(response,map,WrDetailDto.class,"R0002excel");
//        return HttpResult.ok("1");
    }

    /**
     * 基础查询
     *
     * @param
     * @return
     */
    @PostMapping(value = "/r0003Page")
    public HttpResult r0003Page(WrSummaryQo record) {
        return HttpResult.ok(wrDetailService.findR0003Page(record));
    }

    /*
    导出报表
     */
    @RequestMapping("/r0003ExportExcel")
    @SystemControllerLog(description = "r0003报表Excel导出")
    public void exportR0003Excel(HttpServletResponse response, WrSummaryQo record) throws IOException {
        Map<String,List<WrR0003DetailDto>> map = wrDetailService.selectR0003All(record);
        ExcelUtils.writeMoreSheetExcel(response,map,WrR0003DetailDto.class,"R0003excel");
//        return HttpResult.ok("1");
    }

}
