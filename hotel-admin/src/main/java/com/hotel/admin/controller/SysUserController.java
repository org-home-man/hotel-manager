package com.hotel.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.google.common.io.Files;
import com.hotel.admin.dto.SysUserUp;
import com.hotel.admin.qo.SysUserQuery;
import com.hotel.common.utils.StringUtils;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.admin.constants.SysConstants;
import com.hotel.admin.model.SysUser;
import com.hotel.admin.service.SysUserService;
import com.hotel.admin.util.PasswordUtils;
import com.hotel.core.http.HttpResult;
import com.hotel.core.page.PageRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户控制器
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("user")
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;
	
	@PreAuthorize("hasAuthority('sys:user:add') AND hasAuthority('sys:user:edit')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysUser record) {
		SysUser user = sysUserService.findById(record.getId());
		if(user != null) {
			if(SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
				return HttpResult.error("超级管理员不允许修改!");
			}
		}
		if(record.getPassword() != null) {
			String salt = PasswordUtils.getSalt();
			if(user == null) {
				// 新增用户
				if(sysUserService.findByName(record.getName()) != null) {
					return HttpResult.error("用户名已存在!");
				}
				String password = PasswordUtils.encode(record.getPassword(), salt);
				record.setSalt(salt);
				record.setPassword(password);
			} else {
				// 修改用户, 且修改了密码
				if(!record.getPassword().equals(user.getPassword())) {
					String password = PasswordUtils.encode(record.getPassword(), salt);
					record.setSalt(salt);
					record.setPassword(password);
				}
			}
		}
		sysUserService.save(record);
		return HttpResult.ok();
	}

	@PreAuthorize("hasAuthority('sys:user:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(List<SysUser> records) {
		for(SysUser record:records) {
			SysUser sysUser = sysUserService.findById(record.getId());
			if(sysUser != null && SysConstants.ADMIN.equalsIgnoreCase(sysUser.getName())) {
				return HttpResult.error("超级管理员不允许删除!");
			}
		}
		sysUserService.delete(records);
		return HttpResult.ok();
	}
	
	@PreAuthorize("hasAuthority('sys:user:view')")
	@PostMapping(value="/findByName")
	public HttpResult findByUserName(String name) {
		return HttpResult.ok(sysUserService.findByName(name));
	}
	
	@PreAuthorize("hasAuthority('sys:user:view')")
	@GetMapping(value="/findPermissions")
	public HttpResult findPermissions(String name) {
		return HttpResult.ok(sysUserService.findPermissions(name));
	}
	
	@PreAuthorize("hasAuthority('sys:user:view')")
	@GetMapping(value="/findUserRoles")
	public HttpResult findUserRoles(Long userId) {
		return HttpResult.ok(sysUserService.findUserRoles(userId));
	}

	@PreAuthorize("hasAuthority('sys:user:view')")
	@PostMapping(value="/findPage")
	public HttpResult findPage(SysUserQuery qo) {
		return HttpResult.ok(sysUserService.findPage(qo));
	}

	@PreAuthorize("hasAuthority('sys:user:edit')")
	@PostMapping(value="/updatePassword")
	public HttpResult updatePassword(SysUserUp record) {
		try {
			sysUserService.updatePassword(record);
		}catch (GlobalException e) {
			return HttpResult.error(e.getCode(),e.getMsg());
		}

		return HttpResult.ok();
	}

}
