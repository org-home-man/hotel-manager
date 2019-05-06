package com.hotel.admin.mapper;

import com.hotel.admin.model.SysRoleMenu;
import com.hotel.core.mybatis.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMenuMapper extends AbstractMapper<SysRoleMenu>{

	List<SysRoleMenu> findRoleMenus(@Param(value = "roleId") Long roleId);

	int deleteByRoleId(@Param(value = "roleId") Long roleId);
}