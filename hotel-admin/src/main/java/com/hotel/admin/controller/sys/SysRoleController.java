package com.hotel.admin.controller.sys;

import com.hotel.admin.constants.SysConstants;
import com.hotel.admin.mapper.SysRoleMapper;
import com.hotel.admin.model.SysRole;
import com.hotel.admin.model.SysRoleMenu;
import com.hotel.admin.service.SysRoleService;
import com.hotel.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

/**
 * 角色控制器
 * @author chenchao
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("role")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@PreAuthorize("hasAuthority('sys:role:add')")
	@PostMapping(value="/save")
	public HttpResult save(SysRole record) {
		sysRoleService.save(record);
		return HttpResult.ok();
	}
	@PreAuthorize("hasAuthority('sys:role:edit')")
	@PostMapping(value="/update")
	public HttpResult update(SysRole record) {
		sysRoleService.updateNotNull(record);
		return HttpResult.ok();
	}

	@PreAuthorize("hasAuthority('sys:role:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysRole> records) {
		sysRoleService.deleteBatch(records);
		return HttpResult.ok();
	}

	@PreAuthorize("hasAuthority('sys:role:view')")
	@PostMapping(value="/findPage")
	public HttpResult findPage(String name) {
		return HttpResult.ok(sysRoleService.findPage(name));
	}
	
//	@PreAuthorize("hasAuthority('sys:role:view')")
	@GetMapping(value="/findAll")
	public HttpResult findAll() {
		List<SysRole> sysRoles = sysRoleService.selectAll();
		Stream<SysRole> sysRoleStream = sysRoles.stream().filter(r -> !SysConstants.ADMIN.equals(r.getName()));
		return HttpResult.ok(sysRoleStream);
	}
	
	@PreAuthorize("hasAuthority('sys:role:view')")
	@PostMapping(value="/findRoleMenus")
	public HttpResult findRoleMenus(Long roleId) {
		return HttpResult.ok(sysRoleService.findRoleMenus(roleId));
	}
	
	@PreAuthorize("hasAuthority('sys:role:view')")
	@PostMapping(value="/saveRoleMenus")
	public HttpResult saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
		for(SysRoleMenu record:records) {
			SysRole sysRole = sysRoleMapper.selectByPrimaryKey(record.getRoleId());
			if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
				// 如果是超级管理员，不允许修改
				return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
			}
		}
		sysRoleService.saveRoleMenus(records);
		return HttpResult.ok();
	}
}
