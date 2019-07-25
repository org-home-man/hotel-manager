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

import com.hotel.admin.model.CrtId;
import com.hotel.admin.service.CrtIdService;

/**
 * ---------------------------
 * 自增序列表 (CrtIdController)         
 * ---------------------------
 */
@RestController
@RequestMapping("crtId")
public class CrtIdController {

	@Autowired
	private CrtIdService crtIdService;

	/**
	 * 保存自增序列表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(CrtId record) {
		crtIdService.save(record);
		return HttpResult.ok();
	}

    /**
     * 删除自增序列表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<CrtId> records) {
		crtIdService.delete(records);
		return HttpResult.ok();
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(PageRequest pageRequest) {
		return HttpResult.ok(crtIdService.findPage(pageRequest));
	}

}
