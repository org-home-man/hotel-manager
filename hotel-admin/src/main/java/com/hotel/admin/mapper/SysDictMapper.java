package com.hotel.admin.mapper;

import com.hotel.admin.model.SysDict;
import com.hotel.core.mybatis.mapper.AbstractMapper;
import com.hotel.core.service.AbstractService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictMapper extends AbstractMapper<SysDict> {

    List<SysDict> findPageByName(@Param("name") String name);
}