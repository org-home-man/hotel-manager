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


import com.hotel.admin.model.BizRoomExt;
import com.hotel.admin.service.BizRoomExtService;

/**
 * ---------------------------
 * 客房信息铺表 (BizRoomExtController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-07 17:16:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("bizRoomExt")
public class BizRoomExtController {

	@Autowired
	private BizRoomExtService bizRoomExtService;

	/**
	 * 保存客房信息铺表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody BizRoomExt record) {
		return HttpResult.ok(bizRoomExtService.save(record));
	}

    /**
     * 删除客房信息铺表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<BizRoomExt> records) {
		return HttpResult.ok(bizRoomExtService.delete(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(bizRoomExtService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam String roomCode) {
		return HttpResult.ok(bizRoomExtService.findById(roomCode));
	}
}
