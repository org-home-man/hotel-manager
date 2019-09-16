package com.hotel.admin.task;

import com.hotel.admin.aspect.SysLogAspect;
import com.hotel.admin.constants.Constant;
import com.hotel.admin.dto.DateRangeDto;
import com.hotel.admin.dto.SysDictDto;
import com.hotel.admin.mapper.BizInvMapper;
import com.hotel.admin.mapper.BizPuchsMapper;
import com.hotel.admin.mapper.BizRecommendRoomMapper;
import com.hotel.admin.mapper.SysDictMapper;
import com.hotel.admin.model.*;
import com.hotel.admin.qo.BizPuchsStatusUpdate;
import com.hotel.admin.service.BizInvService;
import com.hotel.common.utils.DateUtils;
import com.hotel.common.utils.Utils;
import com.hotel.core.exception.GlobalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DailySchedule {

    private static final Logger logger = LoggerFactory.getLogger(DailySchedule.class);

    @Autowired
    private BizPuchsMapper bizPuchsMapper;

    @Autowired
    private BizRecommendRoomMapper bizRecommendRoomMapper;

    @Autowired
    private BizInvMapper bizInvMapper;

    @Autowired
    private SysDictMapper sysDictMapper;

    @Autowired
    private BizInvService bizInvService;


    /*
    生成  2. 当月最低房价客房信息
          3. 历史预约最多客房预约信息
        插入信息表(biz_recommend_room)。

        逐一确认“待确认”订单，若订单的【入住日期】<=【当前日期】+【7天】（7天天为配置参数），则该订单状态变更成“自动取消”

        逐一确认“已确认”订单，若订单的【入住日期】<=【当前日期】+【7天】（7天天为配置参数），则该订单状态变更成“待结算”
     */
    @Scheduled(cron = "0 30 17 * * ?")
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
        logger.info("日跑批历史预定最多的客房信息："+bizRecommendRoom.getRoomCode() );
        inserOrUpdateRecommend(bizRecommendRoom);

        //当月价格最低的客房信息
        BizRecommendRoom bizLowestRoom = new BizRecommendRoom();
        String lowestRoom = bizPuchsMapper.findMonthLowestRoom(getDateRange());
        bizLowestRoom.setRoomCode(lowestRoom);
        bizLowestRoom.setCustroomType(Constant.CUSTROOM_PRICE_LOWEST);
        logger.info("日跑批价格最低的客房信息："+bizLowestRoom.getRoomCode() );
        inserOrUpdateRecommend(bizLowestRoom);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(new Date());

        //更新17.30订单状态
        logger.info("执行将未确认订单更新为自动取消订单");
        BizPuchsStatusUpdate bizPuchsStatusUpdate = new BizPuchsStatusUpdate();
        bizPuchsStatusUpdate.setCreateTime(today);
        bizPuchsStatusUpdate.setOldStatus(Constant.PUCHS_STAT_NO_CONFIRM);
        bizPuchsStatusUpdate.setStatus(Constant.PUCHS_STAT_CANCEL_AUTO);
        bizPuchsMapper.puchsStatusCrtTm(bizPuchsStatusUpdate);

        logger.info("执行将确认状态的订单更新成待结算订单");
        String sysDate = getSystemDate(getSystemParams());
        BizPuchsStatusUpdate statusUpdate = new BizPuchsStatusUpdate();
        statusUpdate.setInDateStart(sysDate);
        statusUpdate.setOldStatus(Constant.PUCHS_STAT_CONFIRM);
        statusUpdate.setStatus(Constant.PUCHS_STAT_NO_ACCOUNTS);
        bizPuchsMapper.puchsStatusUpdate(statusUpdate);

        //查询出当日创建以前的订单信息 未确认的订单取消之后需要还库存
        logger.info("执行还原库存信息");
        BizPuchsUpdate bizPuchsUpdate = new BizPuchsUpdate();
        bizPuchsUpdate.setStatus(Constant.PUCHS_STAT_NO_CONFIRM);
        bizPuchsUpdate.setCreateTime(today);
        List<BizPuchs> bizPuchsList =  bizPuchsMapper.selectPuchsInfo(bizPuchsUpdate);
        if (bizPuchsList.size()>0) {
            for (int i = 0 ;i<bizPuchsList.size() ; i++) {
                //取消订单还原库存
                Date outDate = DateUtils.getDate(bizPuchsList.get(i).getOutDateEnd(), "yyyyMMdd");
                Date inDate = DateUtils.getDate(bizPuchsList.get(i).getInDateStart(), "yyyyMMdd");
                int invDate = DateUtils.getDateDiff(outDate, inDate);
                for (int index = 0; index < invDate; index++) {
                    String newInDate = DateUtils.getDateString(DateUtils.addDays(inDate, index), "yyyyMMdd");
                    BizInv inv = new BizInv();
                    inv.setHotelCode(bizPuchsList.get(i).getHotelCode());
                    inv.setInvDate(newInDate);
                    BizInv bizInvs = bizInvMapper.findByHotelCode(inv);
                    logger.info("record.getHotelCode() =" + bizPuchsList.get(i).getHotelCode()+","+newInDate);
                    if (bizInvs == null) {
                        continue;
                    } else {
                        //还原被扣除库存
                        bizInvs.setInventory(bizInvs.getInventory() + bizPuchsList.get(i).getRoomNum());
                        bizInvs.setInvDate(newInDate);
                        bizInvService.update(bizInvs);
                    }
                }
            }
        }

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
        //设置月初
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        String earlyDay = sdf.format(calendar.getTime());

        //设置月末
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DAY_OF_MONTH,1);
        calendar1.add(Calendar.MONTH,1);
        calendar1.add(Calendar.DAY_OF_MONTH,-1);
        String today = sdf.format(calendar1.getTime());
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
