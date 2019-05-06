package com.hotel.admin.mapper;

import com.hotel.admin.model.SysUser;
import com.hotel.admin.qo.SysUserQuery;
import com.hotel.core.mybatis.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends AbstractMapper<SysUser>{

    List<SysUser> findPage(SysUserQuery qo);
    
    SysUser findByName(@Param(value = "name") String name);

	List<SysUser> findPageByName(@Param(value = "name") String name);

	List<SysUser> findPageByNameAndEmail(@Param(value = "name") String name, @Param(value = "email") String email);
}