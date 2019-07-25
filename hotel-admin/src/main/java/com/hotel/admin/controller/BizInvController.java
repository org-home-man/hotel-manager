package com.hotel.admin.controller;

import com.hotel.admin.model.BizInv;
import com.hotel.admin.model.BizPuchs;
import com.hotel.admin.service.BizInvService;
import com.hotel.core.annotation.SystemControllerLog;
import com.hotel.core.http.HttpResult;
import com.hotel.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ---------------------------
 * 客房库存表 (BizInvController)         
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
	@SystemControllerLog(description = "库存信息编辑/保存")
	public HttpResult save(@RequestBody BizInv record) {
		bizInvService.save(record);
		return HttpResult.ok();
	}

    /**
     * 删除客房库存表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	@SystemControllerLog(description = "库存信息删除")
	public HttpResult delete(@RequestBody List<BizInv> records) {
		bizInvService.delete(records);
		return HttpResult.ok();
	}

    /**
     * 根据主键查询
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(Long id) {
		return HttpResult.ok(bizInvService.findById(id));
	}

    /**
     * 根据主键查询
     * @return
     */
	@PostMapping(value="/findInv")
	@SystemControllerLog(description = "库存信息查询")
	public HttpResult findInv(BizPuchs bizPuchs) {
		return HttpResult.ok(bizInvService.findInventory(bizPuchs));
	}
}
