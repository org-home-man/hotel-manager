package com.hotel.admin.service.impl;

import com.hotel.admin.constants.SysConstants;
import com.hotel.admin.mapper.SysMenuMapper;
import com.hotel.admin.model.SysMenu;
import com.hotel.admin.service.SysMenuService;
import com.hotel.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SysMenuServiceImpl extends AbstractService<SysMenu> implements SysMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public int save(SysMenu record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysMenuMapper.insertSelective(record);
		}
		if(record.getParentId() == null) {
			record.setParentId(0L);
		}
		return sysMenuMapper.updateByPrimaryKeySelective(record);
	}


	@Override
	public List<SysMenu> findTree(String userName, int menuType) {
		List<SysMenu> sysMenus = new ArrayList<>();
		List<SysMenu> menus = findByUser(userName);
		for (SysMenu menu : menus) {
			if (menu.getParentId() == null || menu.getParentId() == 0) {
				menu.setLevel(0);
				if(!exists(sysMenus, menu)) {
					sysMenus.add(menu);
				}
			}
		}
		sysMenus.sort(Comparator.comparing(SysMenu::getOrderNum));
		findChildren(sysMenus, menus, menuType);
		return sysMenus;
	}

	@Override
	public List<SysMenu> findByUser(String userName) {
		if(userName == null || "".equals(userName) || SysConstants.ADMIN.equalsIgnoreCase(userName)) {
			return sysMenuMapper.selectAll();
		}
		return sysMenuMapper.findByUserName(userName);
	}

	private void findChildren(List<SysMenu> SysMenus, List<SysMenu> menus, int menuType) {
		for (SysMenu SysMenu : SysMenus) {
			List<SysMenu> children = new ArrayList<>();
			for (SysMenu menu : menus) {
				if(menuType == 1 && menu.getType() == 2) {
					// 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
					continue ;
				}
				if (SysMenu.getId() != null && SysMenu.getId().equals(menu.getParentId())) {
					menu.setParentName(SysMenu.getName());
					menu.setLevel(SysMenu.getLevel() + 1);
					if(!exists(children, menu)) {
						children.add(menu);
					}
				}
			}
			SysMenu.setChildren(children);
			children.sort(Comparator.comparing(com.hotel.admin.model.SysMenu::getOrderNum));
			findChildren(children, menus, menuType);
		}
	}

	private boolean exists(List<SysMenu> sysMenus, SysMenu sysMenu) {
		boolean exist = false;
		for(SysMenu menu:sysMenus) {
			if(menu.getId().equals(sysMenu.getId())) {
				exist = true;
			}
		}
		return exist;
	}

}
