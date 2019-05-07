package com.hotel.admin.mapper;

import com.hotel.admin.model.SysLog;
import com.hotel.core.mybatis.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLogMapper extends AbstractMapper<SysLog>{
    
    List<SysLog> findPageByUserName(@Param(value = "userName") String userName);
}