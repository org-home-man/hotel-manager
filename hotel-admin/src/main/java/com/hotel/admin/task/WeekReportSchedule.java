package com.hotel.admin.task;

import com.hotel.admin.mapper.WrDetailMapper;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.model.WrDetail;
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

//    @Scheduled(fixedRate = 1000*100) //每15s执行一次
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
        System.out.println("报表生成时间范围" + dateStart +"  "+ dateEnd);
        WrDetail wrDetail = new WrDetail();
        wrDetail.setReportId("11");
        wrDetail.setReportMonth("2");
        wrDetail.setReportSeq("4");
        wrDetail.setCreateTimeStart(dateStart);
        wrDetail.setCreateTimeEnd(dateEnd);
        int ret = weekReportMapper.impWeekData(wrDetail);
        System.out.println("ret =" + ret);
    }
}
