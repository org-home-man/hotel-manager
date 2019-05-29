package com.hotel.admin.service.impl;

import com.hotel.admin.constants.SysConstants;
import com.hotel.admin.mapper.SysMenuMapper;
import com.hotel.admin.mapper.SysRoleMapper;
import com.hotel.admin.mapper.SysRoleMenuMapper;
import com.hotel.admin.model.SysMenu;
import com.hotel.admin.model.SysRole;
import com.hotel.admin.model.SysRoleMenu;
import com.hotel.admin.service.SysRoleService;
import com.hotel.common.utils.Utils;
import com.hotel.core.context.PageContext;
import com.hotel.core.http.HttpResult;
import com.hotel.core.page.Page;
import com.hotel.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public HttpResult save(SysRole sysRole) {
		SysRole role = sysRoleMapper.selectByPrimaryKey(sysRole.getId());
//		if(role != null) {
//			if(SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
//				return HttpResult.error("超级管理员不允许修改!");
//			}
//		}
//		// 新增角色
//		if((sysRole.getId() == null || sysRole.getId() ==0) && !sysRoleService.findByName(record.getName()).isEmpty()) {
//			return HttpResult.error("角色名已存在!");
//		}
//		if(sysRole.getId() == null || sysRole.getId() == 0) {
//			return sysRoleMapper.insertSelective(record);
//		}
//		return sysRoleMapper.updateByPrimaryKeySelective(record);
        return null;
	}

	@Override
	public HttpResult update(SysRole sysRole) {
		if(Utils.isEmpty(sysRole.getId())){
			return HttpResult.ok();
		}
		//校验修改的角色名是否存在
		return null;
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
		records.forEach( s -> s.setDelFlag(true));
//		updateNotNull(records);
	}

}
