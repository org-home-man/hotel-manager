package com.hotel.admin.controller.sys;

import com.hotel.admin.model.SysDict;
import com.hotel.admin.service.SysDictService;
import com.hotel.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典控制器
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("dict")
public class SysDictController {

	@Autowired
	private SysDictService sysDictService;
	
	@PreAuthorize("hasAuthority('sys:dict:add') AND hasAuthority('sys:dict:edit')")
	@PostMapping(value="/save")
	public HttpResult save(SysDict record) {
		sysDictService.save(record);
		return HttpResult.ok();
	}

	@PreAuthorize("hasAuthority('sys:dict:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysDict> records) {
		sysDictService.delete(records);
		return HttpResult.ok();
	}

	@PreAuthorize("hasAuthority('sys:dict:delete')")
	@PostMapping(value="/del")
	public HttpResult del(SysDict records) {
		sysDictService.delete(records);
		return HttpResult.ok();
	}

	@PreAuthorize("hasAuthority('sys:dict:view')")
	@PostMapping(value="/findPage")
	public HttpResult findPage(SysDict sysDict) {
		return HttpResult.ok(sysDictService.findPage(sysDict));
	}

//	@PreAuthorize("hasAuthority('sys:dict:view')")
	@PostMapping(value="/findByCode")
	public HttpResult findByCode(String code,String locale) {
		return HttpResult.ok(sysDictService.findByCode(code,locale));
	}
	@PostMapping(value="/findByCodes")
	public HttpResult findByCodes(String code,String locale) {
		return HttpResult.ok(sysDictService.findByCodes(code,locale));
	}

}
