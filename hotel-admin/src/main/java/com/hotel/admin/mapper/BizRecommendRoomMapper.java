package com.hotel.admin.mapper;

import com.hotel.admin.dto.RecommendRoomQuery;
import com.hotel.admin.model.BizRecommendRoom;
import com.hotel.core.mybatis.mapper.AbstractMapper;

import java.util.List;


public interface BizRecommendRoomMapper extends AbstractMapper<BizRecommendRoom> {

    List<BizRecommendRoom> findRecommendInfo(RecommendRoomQuery recommendRoomQuery);

}
