package com.hotel.admin.mapper;

import java.util.List;

import com.hotel.admin.dto.DateRangeDto;
import com.hotel.admin.dto.ManagerRequestReportDto;
import com.hotel.admin.dto.MrR0001DetailDto;
import com.hotel.admin.dto.UserRequestReportDto;
import com.hotel.admin.model.*;
import com.hotel.admin.qo.BizPuchsQuery;
import com.hotel.admin.qo.BizPuchsStatusUpdate;
import com.hotel.admin.qo.MrSummaryQo;
import com.hotel.core.mybatis.mapper.AbstractMapper;

/**
 * ---------------------------
 * 订单信息表 (BizPuchsMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-30 12:29:00
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizPuchsMapper extends AbstractMapper<BizPuchs> {

    List<BizPuchsExtDto> findPage(BizPuchsQuery bizPuchsQuery);
    int updateBizPushs(BizPuchsUpdate bizPuchsQuery);
    int puchsConfirm(BizPuchsUpdate bizPuchsQuery);
    List<BizPuchs> findPageS(BizPuchsUpdate bizPuchsQuery);
    List<BizPuchs> findByRoomCd(String roomCode);

    String findGroupRoomCode();
    String findMonthLowestRoom(DateRangeDto record);
    int puchsStatusUpdate(BizPuchsStatusUpdate bizPuchsStatusUpdate);

    //r0001报表
    List<MrR0001DetailDto> r0001MonthReport(MrSummaryQo record);

    List<UserRequestReportDto> userRequestReport(BizPuchsUpdate bizPuchsUpdate);

    List<ManagerRequestReportDto> managerRequestReport(BizPuchsUpdate bizPuchsUpdate);

}