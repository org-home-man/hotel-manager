package com.hotel.admin.mapper;

import com.hotel.admin.dto.HotelRoomQry;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.model.BizRoom;
import com.hotel.core.mybatis.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelRoomMapper extends AbstractMapper<BizRoom> {

    /**
     * 基础分页查询
     * @param hotelRoomQry
     * @return
     */    
    List<BizRoom> findPageByPara(HotelRoomQry hotelRoomQry);

    void callCalendar(@Param("inDateStart") String inDateStart,@Param("outDateEnd") String outDateEnd);

    List<BizHotl> findLikeByName(@Param("name") String name);
}