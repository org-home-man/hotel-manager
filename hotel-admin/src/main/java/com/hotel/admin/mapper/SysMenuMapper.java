package com.hotel.admin.mapper;

import com.hotel.admin.model.SysMenu;
import com.hotel.core.mybatis.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends AbstractMapper<SysMenu> {

	List<SysMenu> findPageByName(@Param(value = "name") String name);

	List<SysMenu> findByUserName(@Param(value = "userName") String userName);

	List<SysMenu> findRoleMenus(@Param(value = "roleId") Long roleId);
}