package com.hotel.admin.controller.sys;

import com.hotel.admin.constants.SysConstants;
import com.hotel.admin.dto.SysUserUp;
import com.hotel.admin.model.SysUser;
import com.hotel.admin.qo.SysUserQuery;
import com.hotel.admin.service.SysUserService;
import com.hotel.admin.util.PasswordUtils;
import com.hotel.core.annotation.SystemControllerLog;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	@SystemControllerLog(description = "用户新增/编辑操作")
	public HttpResult save(@RequestBody SysUser record) {
		SysUser user = sysUserService.findById(record.getId());
		if(user != null) {
			if(SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
				throw new GlobalException("noEdit");
			}
		}
		if(record.getPassword() != null) {
			String salt = PasswordUtils.getSalt();
			if(user == null) {
				// 新增用户
				if(sysUserService.findByName(record.getName()) != null) {
                    throw new GlobalException("userInfoHav");
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
	@SystemControllerLog(description = "用户删除操作")
	public HttpResult delete(@RequestBody List<SysUser> records) {
		for(SysUser record:records) {
			SysUser sysUser = sysUserService.findById(record.getId());
			if(sysUser != null && SysConstants.ADMIN.equalsIgnoreCase(sysUser.getName())) {
				return HttpResult.error("超级管理员不允许删除!");
			}
		}
		sysUserService.delete(records);
		return HttpResult.ok();
	}

	@PostMapping(value="/findByName")
	public HttpResult findByUserName(String name) {
		return HttpResult.ok(sysUserService.findByName(name));
	}
	
//	@PreAuthorize("hasAuthority('sys:user:view')")
	@PostMapping(value="/findPermissions")
	public HttpResult findPermissions(String name) {
		return HttpResult.ok(sysUserService.findPermissions(name));
	}
	
//	@PreAuthorize("hasAuthority('sys:user:view')")
	@PostMapping(value="/findUserRoles")
	public HttpResult findUserRoles(Long userId) {
		return HttpResult.ok(sysUserService.findUserRoles(userId));
	}

//	@PreAuthorize("hasAuthority('sys:user:view')")
	@PostMapping(value="/findPage")
	public HttpResult findPage(SysUserQuery qo) {
		return HttpResult.ok(sysUserService.findPage(qo));
	}

	@PostMapping(value="/updatePassword")
	@SystemControllerLog(description = "用户密码修改")
	public HttpResult updatePassword(SysUserUp record) {
		sysUserService.updatePassword(record);
		return HttpResult.ok();
	}

	@PostMapping(value="/updateUserInfor")
//	@SystemControllerLog(description = "用户信息修改")
	public HttpResult updateUserInfor(@RequestBody SysUserUp record) {
		System.out.println("licy test");
		sysUserService.updateUserInfor(record);
		return HttpResult.ok();
	}

	@PostMapping(value = "/findLikeByName")
	public HttpResult findLikeByName(String name){
		List<SysUser> likeByName = sysUserService.findLikeByName(name);
		return HttpResult.ok(likeByName);
	}

}
