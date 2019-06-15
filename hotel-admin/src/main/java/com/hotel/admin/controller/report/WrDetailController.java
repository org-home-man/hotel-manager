package com.hotel.admin.controller.report;

import com.hotel.admin.dto.WrDetailDto;
import com.hotel.admin.qo.WrSummaryQo;
import com.hotel.admin.service.WrDetailService;
import com.hotel.admin.util.ExcelUtils;
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

    @RequestMapping("/r0002ExportExcel")
    public void exportExcel(HttpServletResponse response, WrSummaryQo record) throws IOException {
        Map<String,List<WrDetailDto>> map = wrDetailService.selectAll(record);
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


}
