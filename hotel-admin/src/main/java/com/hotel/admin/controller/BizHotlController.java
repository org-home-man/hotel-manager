package com.hotel.admin.controller;

import com.hotel.admin.dto.BizHotelQueryDto;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.service.BizHotlService;
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

        System.out.println("licy12347" + record.getHotelAddr());
        try{
        bizHotlService.save(record);
        }catch (
    GlobalException e) {
        return HttpResult.error(e.getCode(),e.getMsg());
    }
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
        try{
        bizHotlService.delete(records);
    }catch (GlobalException e) {
        return HttpResult.error(e.getCode(),e.getMsg());
    }
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
        Page list = null;
        try {
             list=  bizHotlService.findPage(dto);
    }catch (GlobalException e) {
        return HttpResult.error(e.getCode(),e.getMsg());
    }
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
