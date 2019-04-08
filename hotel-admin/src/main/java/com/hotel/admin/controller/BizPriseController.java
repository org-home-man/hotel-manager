package com.hotel.admin.controller;

import java.util.List;

import com.hotel.core.http.HttpResult;
import com.hotel.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.admin.model.BizPrise;
import com.hotel.admin.service.BizPriseService;

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
	public HttpResult save(@RequestBody BizPrise record) {
		return HttpResult.ok(bizPriseService.save(record));
	}

    /**
     * 删除客房牌价表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<BizPrise> records) {
		return HttpResult.ok(bizPriseService.delete(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(bizPriseService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam String roomCode) {
		return HttpResult.ok(bizPriseService.findById(roomCode));
	}
}
