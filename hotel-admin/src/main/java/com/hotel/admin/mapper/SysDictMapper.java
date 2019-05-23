package com.hotel.admin.mapper;

import com.hotel.admin.dto.SysDictDto;
import com.hotel.admin.model.SysDict;
import com.hotel.core.mybatis.mapper.AbstractMapper;
import com.hotel.core.service.AbstractService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictMapper extends AbstractMapper<SysDict> {

    SysDict validateUnique(SysDict dict);

    List<SysDictDto> findByCode(@Param("code") String code, @Param("locale") String locale);

}