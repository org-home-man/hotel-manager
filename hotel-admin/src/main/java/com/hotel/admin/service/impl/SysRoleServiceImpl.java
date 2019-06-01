package com.hotel.admin.service.impl;

import com.hotel.admin.constants.SysConstants;
import com.hotel.admin.mapper.*;
import com.hotel.admin.model.SysMenu;
import com.hotel.admin.model.SysRole;
import com.hotel.admin.model.SysRoleMenu;
import com.hotel.admin.service.SysRoleService;
import com.hotel.common.utils.Utils;
import com.hotel.core.context.PageContext;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.http.HttpResult;
import com.hotel.core.page.Page;
import com.hotel.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysRoleServiceImpl extends AbstractService<SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private SysRoleDeptMapper sysRoleDeptMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public int save(SysRole sysRole) {
		List<SysRole> byName = sysRoleMapper.findByName(sysRole.getName());
		if(Utils.isNotEmpty(byName)){
			throw new GlobalException("roleNameExist");
		}
		sysRoleMapper.insertSelective(sysRole);
        return 1;
	}

	@Override
	public int updateNotNull(SysRole sysRole) {
		Long id = sysRole.getId();
		if(Utils.isEmpty(id)){
			return 1;
		}
		SysRole role = sysRoleMapper.selectByPrimaryKey(id);
		//校验修改的角色名是否存在
		if(SysConstants.ADMIN.equals(role.getName())){
			throw new GlobalException("noEdit");
		}
		//校验角色是否重复
		if(!role.getName().equals(sysRole.getName())){
			List<SysRole> byName = sysRoleMapper.findByName(sysRole.getName());
			if(Utils.isNotEmpty(byName)){
				throw new GlobalException("roleNameExist");
			}
		}
		sysRoleMapper.updateByPrimaryKeySelective(sysRole);
		return 1;
	}

	@Override
	public Page findPage(String name) {
		sysRoleMapper.findByName(name);
		return PageContext.getPage();
	}

	@Override
	public List<SysMenu> findRoleMenus(Long roleId) {
		if(Utils.isEmpty(roleId)){
			return new ArrayList<>();
		}
		SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
		if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
			// 如果是超级管理员，返回全部
			return sysMenuMapper.selectAll();
		}
		return sysMenuMapper.findRoleMenus(roleId);
	}

	@Transactional
	@Override
	public int saveRoleMenus(List<SysRoleMenu> records) {
		if(records == null || records.isEmpty()) {
			return 1;
		}
		Long roleId = records.get(0).getRoleId(); 
		sysRoleMenuMapper.deleteByRoleId(roleId);
		for(SysRoleMenu record:records) {
			sysRoleMenuMapper.insertSelective(record);
		}
		return 1;
	}

	@Override
	public void deleteBatch(List<SysRole> records) {
		//删除角色，删除角色对于的所有关系
		List<Long> ids = records.stream().map(r -> r.getId()).collect(Collectors.toList());
		//1.删除角色用户关系表
		sysUserRoleMapper.deleteByRoleIds(ids);
		//2.删除角色菜单关系表
		sysRoleMenuMapper.deleteByRoleIds(ids);
		//3.删除角色部门关系表
		sysRoleDeptMapper.deleteByRoleIds(ids);
		//删除对于角色
		sysRoleMapper.deleteByIds(ids);
	}

}
