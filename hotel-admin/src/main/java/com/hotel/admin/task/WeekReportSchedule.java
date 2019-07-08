package com.hotel.admin.task;

import com.alibaba.druid.sql.visitor.functions.Trim;
import com.alibaba.fastjson.JSONObject;
import com.hotel.admin.constants.Constant;
import com.hotel.admin.dto.SocketMessage;
import com.hotel.admin.mapper.WrDetailMapper;
import com.hotel.admin.mapper.WrSummaryMapper;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.model.WrDetail;
import com.hotel.admin.model.WrSummary;
import com.hotel.admin.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class WeekReportSchedule {

    @Autowired(required=false)
    private WrDetailMapper weekReportMapper;
    @Autowired(required=false)
    private WrSummaryMapper wrSummaryMapper;

//    @Scheduled(fixedRate = 1000*200) //每15s执行一次
    @Scheduled(cron = "0 30 01 ? * MON") //每周1上午01:30触发 

    public void weekReport() throws ParseException {
         SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        //当前日期
        String curDateStr = fmt.format(new Date());
        Date curDate = fmt.parse(curDateStr);
        //获取前七天一周的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.DAY_OF_MONTH,0);
        String dateEnd = fmt.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH,-7);
        String dateStart = fmt.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH,6);
        String lastDate =  fmt.format(calendar.getTime());
        //获取年份
        String lastYear = lastDate.substring(0,4);
        System.out.println("报表生成时间范围" + dateStart +"  "+ dateEnd);
        WrDetail wrDetail = new WrDetail();
        WrSummary wrSummary =new WrSummary();
        //按“所属公司”产生本周内发生的订单信息报表
        wrDetail.setReportId(lastYear+"R0002");
        wrSummary.setReportId(lastYear+"R0002");
        wrSummary.setReportTxt("订单信息-公司分类周报表");
        //获取月份
        String lastMonth = lastDate.substring(4,6);
        System.out.println("lastMonth=" +lastMonth);
        wrDetail.setReportMonth(lastMonth);
        wrSummary.setReportMonth(lastMonth);
        //获取报表第几周
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        System.out.println("week=" +String.valueOf(week) +"week");

        wrSummary.setReportSeq(String.valueOf(week));
        wrDetail.setReportSeq(String.valueOf(week));
        wrDetail.setCreateTimeStart(dateStart);
        wrDetail.setCreateTimeEnd(dateEnd);
        WrSummary retSummary = wrSummaryMapper.selectOne(wrSummary);
        wrSummary.setCreatTime(curDateStr);
        if(retSummary ==null) {
             wrSummaryMapper.insertSelective(wrSummary);
             weekReportMapper.impWeekData(wrDetail);

            WebSocketServer.sendMessageToManager("公司分类周报表生成成功，请注意查看", Constant.WEEK_MES);

        }else {
            System.out.println("retSummary " + retSummary.getReportId() + retSummary.getReportSeq());
        }
    }
}
