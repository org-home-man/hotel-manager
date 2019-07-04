package com.hotel.admin.controller;

import com.hotel.admin.dto.HotelRoomQry;
import com.hotel.admin.model.BizPrise;
import com.hotel.admin.service.BizPriseService;
import com.hotel.core.annotation.SystemControllerLog;
import com.hotel.core.http.HttpResult;
import com.hotel.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ---------------------------
 * 客房牌价表 (BizPriseController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-08 16:15:59
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("bizPrise")
public class BizPriseController {

	@Autowired
	private BizPriseService bizPriseService;

	/**
	 * 保存客房牌价表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	@SystemControllerLog(description = "牌价信息编辑/保存")
	public HttpResult save(@RequestBody BizPrise record) {
		bizPriseService.save(record);
		return HttpResult.ok();
	}

    /**
     * 删除客房牌价表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	@SystemControllerLog(description = "牌价信息删除")
	public HttpResult delete(@RequestBody List<BizPrise> records) {
		bizPriseService.delete(records);
		return HttpResult.ok();
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	@SystemControllerLog(description = "牌价信息查询")
	public HttpResult findPage(PageRequest pageRequest) {
		return HttpResult.ok(bizPriseService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */ 	
	@GetMapping(value="/findById")
	@SystemControllerLog(description = "牌价信息根据客房编号查询")
	public HttpResult findById(String roomCode) {
		return HttpResult.ok(bizPriseService.findById(roomCode));
	}

	@PostMapping(value = "/findByDate")
	@SystemControllerLog(description = "牌价信息根据日期查询")
	public HttpResult findByDate(HotelRoomQry record) {
		return HttpResult.ok(bizPriseService.findByDate(record));
	}
}
