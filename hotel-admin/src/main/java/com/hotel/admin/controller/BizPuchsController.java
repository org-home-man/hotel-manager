package com.hotel.admin.controller;

import com.hotel.admin.model.BizPuchs;
import com.hotel.admin.qo.BizPuchsQuery;
import com.hotel.admin.service.BizPuchsService;
import com.hotel.core.context.PageContext;
import com.hotel.core.http.HttpResult;
import com.hotel.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	public HttpResult save(BizPuchs record) {
		bizPuchsService.saveInfo(record);
		return HttpResult.ok();
	}

    /**
     * 删除订单信息表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<BizPuchs> records) {
		bizPuchsService.delete(records);
		return HttpResult.ok();
	}

    /**
     * 基础分页查询
     * @param bizPuchsQuery
     * @return
     */    
	@PostMapping(value="/findPage")
	public Page findPage(BizPuchsQuery bizPuchsQuery) {
		bizPuchsService.findPage(bizPuchsQuery);
		return PageContext.getPage();
	}
	
    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(String roomCode) {
		return HttpResult.ok(bizPuchsService.findById(roomCode));
	}
}
