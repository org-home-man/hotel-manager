package com.hotel.admin.mapper;

import com.hotel.admin.dto.HotelRoomQry;
import com.hotel.admin.model.BizRoom;
import com.hotel.core.mybatis.mapper.AbstractMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface HotelRoomMapper extends AbstractMapper<BizRoom> {

    /**
     * 基础分页查询
     * @param hotelRoomQry
     * @return
     */    
    List<BizRoom> findPageByPara(HotelRoomQry hotelRoomQry);


}