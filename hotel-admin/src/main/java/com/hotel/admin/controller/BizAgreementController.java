package com.hotel.admin.controller;

import com.hotel.admin.dto.BizHotelQueryDto;
import com.hotel.admin.model.BizAgreement;
import com.hotel.admin.service.BizAgreementService;
import com.hotel.core.annotation.SystemControllerLog;
import com.hotel.core.http.HttpResult;
import com.hotel.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("bizAgreement")
public class BizAgreementController {


    @Autowired
    private BizAgreementService bizAgreementService;


    /**
     * 基础分页查询
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/findPage")
    @SystemControllerLog(description = "协议信息查询")
    public HttpResult findPage(BizAgreement dto) {
        List<BizAgreement> list=  bizAgreementService.findPage(dto);
        return HttpResult.ok(list);
    }

}
