package com.hotel.admin.service;

import java.util.List;

import com.hotel.admin.dto.SysRoleEx;
import com.hotel.admin.model.SysMenu;
import com.hotel.admin.model.SysRole;
import com.hotel.admin.model.SysRoleMenu;
import com.hotel.core.page.Page;
import com.hotel.core.service.CurdService;
import com.hotel.core.service.IService;

/**
 * 角色管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysRoleService extends IService<SysRole> {

	/**
	 * 查询角色菜单集合
	 * @return
	 */
	List<SysMenu> findRoleMenus(Long roleId);

	/**
	 * 保存角色菜单
	 * @param records
	 * @return
	 */
	int saveRoleMenus(List<SysRoleMenu> records);

	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	List<SysRoleEx> findByName(String name);

	/**
	 * 通过名称分页查询
	 * @param name
	 * @return
	 */
	Page findPage(String name);

	int deleteBatch(List<SysRole> record);
}
