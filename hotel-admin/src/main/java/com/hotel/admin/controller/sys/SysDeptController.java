package com.hotel.admin.controller.sys;

import com.hotel.admin.model.SysDept;
import com.hotel.admin.service.SysDeptService;
import com.hotel.core.annotation.SystemControllerLog;
import com.hotel.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构控制器
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("dept")
public class SysDeptController {

	@Autowired
	private SysDeptService sysDeptService;
	
	@PreAuthorize("hasAuthority('sys:dept:add') AND hasAuthority('sys:dept:edit')")
	@PostMapping(value="/save")
	@SystemControllerLog(description = "机构新增/编辑操作")
	public HttpResult save(SysDept record) {
		sysDeptService.save(record);
		return HttpResult.ok();
	}

	@PreAuthorize("hasAuthority('sys:dept:delete')")
	@PostMapping(value="/delete")
	@SystemControllerLog(description = "机构删除操作")
	public HttpResult delete(@RequestBody List<SysDept> records) {
		sysDeptService.deleteBatch(records);
		return HttpResult.ok();
	}

	@PreAuthorize("hasAuthority('sys:dept:view')")
	@GetMapping(value="/findTree")
	public HttpResult findTree() {
		return HttpResult.ok(sysDeptService.findTree());
	}

}
