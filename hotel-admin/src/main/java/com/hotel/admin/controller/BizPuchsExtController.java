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

import com.hotel.admin.model.BizPuchsExt;
import com.hotel.admin.service.BizPuchsExtService;

/**
 * ---------------------------
 * 订单信息辅助表 (BizPuchsExtController)         
 * ---------------------------
 */
@RestController
@RequestMapping("bizPuchsExt")
public class BizPuchsExtController {

	@Autowired
	private BizPuchsExtService bizPuchsExtService;

	/**
	 * 保存订单信息辅助表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(BizPuchsExt record) {
		bizPuchsExtService.save(record);
		return HttpResult.ok();
	}

    /**
     * 删除订单信息辅助表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<BizPuchsExt> records) {
		bizPuchsExtService.delete(records);
		return HttpResult.ok();
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(PageRequest pageRequest) {
		return HttpResult.ok(bizPuchsExtService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(String roomCode) {
		return HttpResult.ok(bizPuchsExtService.findById(roomCode));
	}
}
