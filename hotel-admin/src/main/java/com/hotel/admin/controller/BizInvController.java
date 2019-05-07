package com.hotel.admin.controller;

import com.hotel.admin.model.BizInv;
import com.hotel.admin.service.BizInvService;
import com.hotel.core.http.HttpResult;
import com.hotel.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ---------------------------
 * 客房库存表 (BizInvController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-08 16:15:59
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("bizInv")
public class BizInvController {

	@Autowired
	private BizInvService bizInvService;

	/**
	 * 保存客房库存表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(BizInv record) {
		bizInvService.save(record);
		return HttpResult.ok();
	}

    /**
     * 删除客房库存表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<BizInv> records) {
		bizInvService.delete(records);
		return HttpResult.ok();
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(PageRequest pageRequest) {
		return HttpResult.ok(bizInvService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(String roomCode) {
		return HttpResult.ok(bizInvService.findById(roomCode));
	}
}
