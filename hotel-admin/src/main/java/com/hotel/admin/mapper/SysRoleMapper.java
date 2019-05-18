package com.hotel.admin.mapper;

import com.hotel.admin.dto.SysRoleEx;
import com.hotel.admin.model.SysRole;
import com.hotel.core.mybatis.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends AbstractMapper<SysRole>{
	
	List<SysRole> findPageByName(@Param(value = "name") String name);

	List<SysRoleEx> findByName(@Param(value = "name") String name);

	int delete(SysRole record);
}