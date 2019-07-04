package com.hotel.admin.controller;

import com.hotel.admin.model.BizRoomExt;
import com.hotel.admin.service.BizRoomExtService;
import com.hotel.core.annotation.SystemControllerLog;
import com.hotel.core.http.HttpResult;
import com.hotel.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	@SystemControllerLog(description = "客房扩展信息保存")
	public HttpResult save(BizRoomExt record) {
		bizRoomExtService.save(record);
		return HttpResult.ok();
	}

    /**
     * 删除客房信息铺表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	@SystemControllerLog(description = "客房扩展信息删除")
	public HttpResult delete(@RequestBody List<BizRoomExt> records) {
		bizRoomExtService.delete(records);
		return HttpResult.ok();
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	@SystemControllerLog(description = "客房扩展信息查询")
	public HttpResult findPage(PageRequest pageRequest) {
		return HttpResult.ok(bizRoomExtService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(String roomCode) {
		return HttpResult.ok(bizRoomExtService.findById(roomCode));
	}
}
