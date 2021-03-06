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

	int updatePassword(SysUser sysUser);/* 仅更新密码*/
    int updateUserInfor(SysUser sysUser);/*更新用户信息*/

	SysUser findByNameAll(@Param(value = "name") String name);/* 查询所有状态用户 */

    List<SysUser> findLikeByName(@Param("name") String name);

    void deleteByIds(@Param("ids") List<Long> ids);

    List<SysUser> selectManager();

    List<SysUser> findByDeptId(SysUser user);
}