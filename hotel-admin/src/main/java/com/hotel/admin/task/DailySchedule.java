package com.hotel.admin.task;

import com.hotel.admin.constants.Constant;
import com.hotel.admin.dto.DateRangeDto;
import com.hotel.admin.dto.SysDictDto;
import com.hotel.admin.mapper.BizPuchsMapper;
import com.hotel.admin.mapper.BizRecommendRoomMapper;
import com.hotel.admin.mapper.SysDictMapper;
import com.hotel.admin.model.BizRecommendRoom;
import com.hotel.admin.model.SysDict;
import com.hotel.admin.qo.BizPuchsStatusUpdate;
import com.hotel.common.utils.Utils;
import com.hotel.core.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DailySchedule {

    @Autowired
    private BizPuchsMapper bizPuchsMapper;

    @Autowired
    private BizRecommendRoomMapper bizRecommendRoomMapper;

    @Autowired
    private SysDictMapper sysDictMapper;


    /*
    生成  2. 当月最低房价客房信息
          3. 历史预约最多客房预约信息
        插入信息表(biz_recommend_room)。

        逐一确认“待确认”订单，若订单的【入住日期】<=【当前日期】+【7天】（7天天为配置参数），则该订单状态变更成“自动取消”

        逐一确认“已确认”订单，若订单的【入住日期】<=【当前日期】+【7天】（7天天为配置参数），则该订单状态变更成“待结算”
     */
    @Scheduled(cron = "0 1 0 * * ?")
    public void dailyTwelveHour() {
        /*
        查询出订单表中当月最低房价客房信息
        历史预约最多客房预约信息   （插入到信息表）
         */
        //历史预定最多的客房信息
        BizRecommendRoom bizRecommendRoom = new BizRecommendRoom();
        String groupCode = bizPuchsMapper.findGroupRoomCode();
        bizRecommendRoom.setRoomCode(groupCode);
        bizRecommendRoom.setCustroomType(Constant.CUSTROOM_MAXINUM);
        inserOrUpdateRecommend(bizRecommendRoom);

        //当月价格最低的客房信息
        String lowestRoom = bizPuchsMapper.findMonthLowestRoom(getDateRange());
        bizRecommendRoom.setRoomCode(lowestRoom);
        bizRecommendRoom.setCustroomType(Constant.CUSTROOM_PRICE_LOWEST);
        inserOrUpdateRecommend(bizRecommendRoom);

        //更新订单状态
        String sysDate = getSystemDate(getSystemParams());
        BizPuchsStatusUpdate bizPuchsStatusUpdate = new BizPuchsStatusUpdate();
        bizPuchsStatusUpdate.setInDateStart(sysDate);
        bizPuchsStatusUpdate.setOldStatus(Constant.PUCHS_STAT_NO_CONFIRM);
        bizPuchsStatusUpdate.setStatus(Constant.PUCHS_STAT_CANCEL_AUTO);
        bizPuchsMapper.puchsStatusUpdate(bizPuchsStatusUpdate);

        bizPuchsStatusUpdate.setOldStatus(Constant.PUCHS_STAT_CONFIRM);
        bizPuchsStatusUpdate.setStatus(Constant.PUCHS_STAT_NO_ACCOUNTS);
        bizPuchsMapper.puchsStatusUpdate(bizPuchsStatusUpdate);

    }

    /*
    根据客房类型查询是否有相应的记录
     */
    private void inserOrUpdateRecommend(BizRecommendRoom bizRecommendRoom) {
        BizRecommendRoom record = new BizRecommendRoom();
        if (Utils.isNotEmpty( bizRecommendRoom.getRoomCode() )) {
            record.setCustroomType(bizRecommendRoom.getCustroomType());
            List<BizRecommendRoom> li =  bizRecommendRoomMapper.select(record);
            if (li.size() > 0) {
                BizRecommendRoom br = li.get(0);
                br.setCustroomType(bizRecommendRoom.getCustroomType());
                br.setRoomCode(bizRecommendRoom.getRoomCode());
                bizRecommendRoomMapper.updateByPrimaryKey(br);
            } else {
                bizRecommendRoomMapper.insertSelective(bizRecommendRoom);
            }
        }


    }

    /*
    获取月初1号 以及 今天的日期
     */
    private DateRangeDto getDateRange() {
        DateRangeDto dto = new DateRangeDto();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        String earlyDay = sdf.format(calendar.getTime());
        String today = sdf.format(new Date());
        dto.setEarlyMonth(earlyDay);
        dto.setTodayMonth(today);
        return dto;
    }

    /*
    获取当前日期+天数 的日期
     */
    private String getSystemDate(int systemDays) {
        if (Utils.isEmpty(systemDays)) {
            throw new GlobalException("sysException");
        }
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,systemDays);
        String dateString = sdf.format(calendar.getTime());
        return dateString;
    }

    /*
    获取系统参数
     */
    private int getSystemParams() {
        List<SysDictDto> dict = sysDictMapper.findByCode("SYSTEM_DAYS","1");
        if (dict.size() < 1) {
            throw new GlobalException("sysExcetpion");
        }

        try{
            return Integer.parseInt(dict.get(0).getCode());
        }catch (Exception e) {
            throw new GlobalException("sysExcetpion");
        }
    }
}
