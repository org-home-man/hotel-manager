package com.hotel.admin.mapper;

import com.hotel.admin.model.SysSocketMessage;
import com.hotel.core.mybatis.mapper.AbstractMapper;

import java.util.List;

public interface SysSocketMessageMapper extends AbstractMapper<SysSocketMessage> {
    List<SysSocketMessage> selectNoRead(SysSocketMessage socketMessage);

    Long selectNoReadCount(SysSocketMessage sysSocketMessage);
}