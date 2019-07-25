package com.hotel.admin.controller;

import com.hotel.admin.dto.BizHotelQueryDto;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.service.BizHotlService;
import com.hotel.core.annotation.SystemControllerLog;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.http.HttpResult;
import com.hotel.core.page.Page;
import com.hotel.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * ---------------------------
 * 酒店信息表 (BizHotlController)
 * ---------------------------
 */
@RestController
@RequestMapping("bizHotl")
public class BizHotlController {

    @Autowired
    private BizHotlService bizHotlService;

    /**
     * 保存酒店信息表
     *
     * @param record
     * @return
     */
    @PostMapping(value = "/save")
    @SystemControllerLog(description = "酒店信息编辑/保存")
    public HttpResult save(BizHotl record) {

        System.out.println("licy12347" + record.getHotelAddr());

        bizHotlService.save(record);
        return HttpResult.ok();
    }

    /**
     * 删除酒店信息表
     *
     * @param records
     * @return
     */
    @PostMapping(value = "/delete")
    @SystemControllerLog(description = "酒店信息删除")
    public HttpResult delete(@RequestBody List<BizHotl> records) {

        bizHotlService.delete(records);
        return HttpResult.ok();
    }

    /**
     * 基础分页查询
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/findPage")
    @SystemControllerLog(description = "酒店信息查询")
    public HttpResult findPage(BizHotelQueryDto dto) {
        Page list = null;
        list=  bizHotlService.findPage(dto);
        return HttpResult.ok(list);
    }

    /**
     * 根据主键查询
     *
     * @param hotelCode
     * @return
     */
    @GetMapping(value = "/findById")
    public HttpResult findById(String hotelCode) {
        return HttpResult.ok(bizHotlService.selectByKey(hotelCode));
    }

    /**
     * 根据主键查询
     *
     * @param bizHotl
     * @return
     */
    @PostMapping(value = "/findAllData")
    public HttpResult findAllData(BizHotl bizHotl) {
        return HttpResult.ok(bizHotlService.findAllData(bizHotl));
    }
}
