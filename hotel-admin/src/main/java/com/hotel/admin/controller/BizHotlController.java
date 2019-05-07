package com.hotel.admin.controller;

import com.hotel.admin.dto.BizHotelQueryDto;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.service.BizHotlService;
import com.hotel.core.http.HttpResult;
import com.hotel.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * ---------------------------
 * 酒店信息表 (BizHotlController)
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-30 17:15:22
 * 说明：  我是由代码生成器生生成的
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
    public HttpResult save(BizHotl record) {

        System.out.println("licy12347");
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
    public HttpResult findPage(BizHotelQueryDto dto) {
        return HttpResult.ok(bizHotlService.findPage(dto));
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
