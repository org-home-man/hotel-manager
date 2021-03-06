package com.hotel.admin.controller.report;

import com.hotel.admin.dto.*;
import com.hotel.admin.qo.MrSummaryQo;
import com.hotel.admin.qo.WrSummaryQo;
import com.hotel.admin.service.MrDetailService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("mrDetail")
public class MrDetailController {

    @Autowired
    private MrDetailService mrDetailService;

    /**
     * 基础查询
     *
     * @param
     * @return
     */
    @PostMapping(value = "/r0001Page")
    public HttpResult r0001Page(MrSummaryQo record) {
        return HttpResult.ok(mrDetailService.findR0001Page(record));
    }

    /*
    导出报表
     */
    @RequestMapping("/r0001ExportExcel")
    @SystemControllerLog(description = "r0001报表Excel导出")
    public void exportR0001Excel(HttpServletResponse response, MrSummaryQo record) throws IOException {
        Map<String,List<MrR0001DetailDto>> map = mrDetailService.selectR0001All(record);
        ExcelUtils.writeMoreSheetExcel(response,map,MrR0001DetailDto.class,"R0001excel");
//        return HttpResult.ok("1");
    }

    /**
     * 基础查询
     *
     * @param
     * @return
     */
    @PostMapping(value = "/r0004Page")
    public HttpResult r0004Page(MrSummaryQo record) {
        return HttpResult.ok(mrDetailService.findR0004Page(record));
    }


    /**
     * 基础查询
     *
     * @param
     * @return
     */
    @PostMapping(value = "/r0004Data")
    public HttpResult r0004Data(MrSummaryQo record) {
        return HttpResult.ok(mrDetailService.findR0004Data(record));
    }


    /*
    导出报表
     */
    @RequestMapping("/r0004ExportExcel")
    @SystemControllerLog(description = "r0004报表Excel导出")
    public void exportR0004Excel(HttpServletResponse response, MrSummaryQo record) throws IOException {
        Map<String,List<MrR0004DetailDto>> map = new HashMap<>();
         map.put("R0004excel", mrDetailService.findR0004Page(record));
        ExcelUtils.writeMoreSheetExcel(response,map,MrR0004DetailDto.class,"R0004excel");
//        return HttpResult.ok("1");
    }


    /**
     * 基础查询
     *
     * @param
     * @return
     */
    @PostMapping(value = "/r0005Page")
    public HttpResult r0005Page(MrSummaryQo record) {
        return HttpResult.ok(mrDetailService.findR0005Page(record));
    }

    /*
    导出报表
     */
    @RequestMapping("/r0005ExportExcel")
    @SystemControllerLog(description = "r0005报表Excel导出")
    public void exportR0005Excel(HttpServletResponse response, MrSummaryQo record) throws IOException {
        Map<String,List<MrR0005DetailDto>> map = new HashMap<>();
        map.put("R0005excel", mrDetailService.findR0005Page(record));
        ExcelUtils.writeMoreSheetExcel(response,map,MrR0005DetailDto.class,"R0005excel");
//        return HttpResult.ok("1");
    }


}
