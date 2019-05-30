package com.hotel.admin.mapper;

import com.hotel.admin.model.SysRole;
import com.hotel.core.mybatis.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends AbstractMapper<SysRole>{

	List<SysRole> findByName(@Param(value = "name") String name);

}