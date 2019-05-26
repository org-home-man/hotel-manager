package com.hotel.admin.service;

import java.util.List;
import java.util.Set;

import com.hotel.admin.dto.SysUserUp;
import com.hotel.admin.model.SysUser;
import com.hotel.admin.model.SysUserRole;
import com.hotel.admin.qo.SysUserQuery;
import com.hotel.core.page.Page;
import com.hotel.core.page.PageRequest;
import com.hotel.core.service.CurdService;
import com.hotel.core.service.NewCurdService;

/**
 * 用户管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysUserService extends NewCurdService<SysUser> {

	SysUser findByName(String username);

	/**
	 * 查找用户的菜单权限标识集合
	 * @param userName
	 * @return
	 */
	Set<String> findPermissions(String userName);

	/**
	 * 查找用户的角色集合
	 * @param userName
	 * @return
	 */
	List<SysUserRole> findUserRoles(Long userId);


    Page findPage(SysUserQuery qo);

    int updatePassword(SysUserUp sysUserUp);

	List<SysUser> findLikeByName(String name);
}
