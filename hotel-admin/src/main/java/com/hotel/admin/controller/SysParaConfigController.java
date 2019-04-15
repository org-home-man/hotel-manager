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

import com.hotel.admin.model.SysParaConfig;
import com.hotel.admin.service.SysParaConfigService;

/**
 * ---------------------------
 * 参数配置表 (SysParaConfigController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-05 11:24:49
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("sysParaConfig")
public class SysParaConfigController {

	@Autowired
	private SysParaConfigService sysParaConfigService;

	/**
	 * 保存参数配置表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysParaConfig record) {
		return HttpResult.ok(sysParaConfigService.save(record));
	}

    /**
     * 删除参数配置表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysParaConfig> records) {
		return HttpResult.ok(sysParaConfigService.delete(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysParaConfigService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param paraSubCode2
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam String paraCode) {
		return HttpResult.ok(sysParaConfigService.findById(paraCode));
	}

	/**
	 * 根据主键查询
	 * @param SysParaConfig
	 * @return
	 */
	@PostMapping(value="/findListData")
	public HttpResult findKeyValue(@RequestBody SysParaConfig record) {

		return HttpResult.ok(sysParaConfigService.findKeyValue(record));
	}
	@PostMapping(value="/findListDataHotel")
	public HttpResult findKeyValueHotel(@RequestBody SysParaConfig record) {

		return HttpResult.ok(sysParaConfigService.findKeyValueHotel(record));
	}
}
