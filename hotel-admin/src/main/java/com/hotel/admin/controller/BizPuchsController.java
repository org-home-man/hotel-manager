package com.hotel.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.core.http.HttpResult;
import com.hotel.core.page.PageRequest;

import com.hotel.admin.model.BizPuchs;
import com.hotel.admin.service.BizPuchsService;

/**
 * ---------------------------
 * 订单信息表 (BizPuchsController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-30 12:29:00
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("bizPuchs")
public class BizPuchsController {

	@Autowired
	private BizPuchsService bizPuchsService;

	/**
	 * 保存订单信息表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody BizPuchs record) {
		return HttpResult.ok(bizPuchsService.save(record));
	}

    /**
     * 删除订单信息表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<BizPuchs> records) {
		return HttpResult.ok(bizPuchsService.delete(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(bizPuchsService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam String roomCode) {
		return HttpResult.ok(bizPuchsService.findById(roomCode));
	}
}
