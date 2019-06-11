package com.hotel.admin.mapper;

import com.hotel.admin.model.MrDetail;
import com.hotel.admin.model.WrDetail;
import com.hotel.core.mybatis.mapper.AbstractMapper;

public interface MrDetailMapper extends AbstractMapper<MrDetail> {
    int impMonDeptData(MrDetail mrDetail);
    int impMonUserData(MrDetail mrDetail);
    int updDeptInfor(MrDetail mrDetail);
}