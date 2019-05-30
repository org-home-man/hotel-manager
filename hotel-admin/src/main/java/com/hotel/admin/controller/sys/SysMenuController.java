package com.hotel.admin.controller.sys;

import com.hotel.admin.model.SysMenu;
import com.hotel.admin.service.SysMenuService;
import com.hotel.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单控制器
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("menu")
public class SysMenuController {

	@Autowired
	private SysMenuService sysMenuService;
	
	@PreAuthorize("hasAuthority('sys:menu:add') AND hasAuthority('sys:menu:edit')")
	@PostMapping(value="/save")
	public HttpResult save(SysMenu record) {
		sysMenuService.save(record);
		return HttpResult.ok();
	}

	@PreAuthorize("hasAuthority('sys:menu:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysMenu> records) {
		sysMenuService.delete(records);
		return HttpResult.ok();
	}

//	@PreAuthorize("hasAuthority('sys:menu:view')")
	@PostMapping(value="/findNavTree")
	public HttpResult findNavTree(String userName) {
		return HttpResult.ok(sysMenuService.findTree(userName, 1));
	}
	
	@PreAuthorize("hasAuthority('sys:menu:view')")
	@GetMapping(value="/findMenuTree")
	public HttpResult findMenuTree() {
		return HttpResult.ok(sysMenuService.findTree(null, 0));
	}
}
