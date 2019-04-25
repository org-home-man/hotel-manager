package com.hotel.admin.mapper;

import com.hotel.admin.entity.Demo;
import com.hotel.core.mybatis.mapper.AbstractMapper;

import java.util.List;

public interface DemoMapper extends AbstractMapper<Demo> {

    List<Demo> selectBy();
}