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

import com.hotel.admin.model.BizRoom;
import com.hotel.admin.service.BizRoomService;

/**
 * ---------------------------
 * 客房信息表 (BizRoomController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-01 21:00:17
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("bizRoom")
public class BizRoomController {

	@Autowired
	private BizRoomService bizRoomService;

	/**
	 * 保存客房信息表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody BizRoom record) {
		return HttpResult.ok(bizRoomService.save(record));
	}

    /**
     * 删除客房信息表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<BizRoom> records) {
		return HttpResult.ok(bizRoomService.delete(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(bizRoomService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam String roomCode) {
		return HttpResult.ok(bizRoomService.findById(roomCode));
	}
}
