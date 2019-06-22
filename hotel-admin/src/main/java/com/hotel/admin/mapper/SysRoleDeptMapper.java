package com.hotel.admin.mapper;

import com.hotel.admin.model.SysRoleDept;
import com.hotel.core.mybatis.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleDeptMapper extends AbstractMapper<SysRoleDept> {

    void deleteByRoleIds(@Param("ids") List<Long> ids);
}