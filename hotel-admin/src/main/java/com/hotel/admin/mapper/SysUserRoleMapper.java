package com.hotel.admin.mapper;

import com.hotel.admin.model.SysUserRole;
import com.hotel.core.mybatis.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleMapper extends AbstractMapper<SysUserRole>{

	List<SysUserRole> findUserRoles(@Param(value = "userId") Long userId);

	int deleteByUserId(@Param(value = "userId") Long userId);

    void deleteByRoleIds(@Param("ids") List<Long> ids);

	void deleteByUserIds(@Param("ids") List<Long> ids);
}