package com.hotel.admin.task;

import com.hotel.admin.mapper.BizHotlMapper;
import com.hotel.admin.mapper.MrDetailMapper;
import com.hotel.admin.mapper.MrSummaryMapper;
import com.hotel.admin.mapper.WrDetailMapper;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.model.MrDetail;
import com.hotel.admin.model.MrSummary;
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
public class MonReportSchedule {

    @Autowired(required=false)
    private MrDetailMapper monReportMapper;

    @Autowired(required=false)
    private MrSummaryMapper mrSummaryMapper;
//    @Scheduled(fixedRate = 1000*200) //每15s执行一次
    @Scheduled(cron = "0 0 2 1 * ?") //每月1上午02：:0触发 
//    @Scheduled(cron = "0 30 01 ? * MON") //每周1上午01:30触发 


    public void monReport() throws ParseException {
         SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        //当前日期
        String curDateStr = fmt.format(new Date());
        Date curDate = fmt.parse(curDateStr);
        //获取前七天一周的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        String dateEnd = fmt.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.MONTH,-1);
        String dateStart = fmt.format(calendar.getTime());
        System.out.println("报表生成时间范围" + dateStart +"  "+ dateEnd);
        MrDetail mrDetail = new MrDetail();
        MrSummary mrSummary = new MrSummary();
        String lastYear = dateStart.substring(0,4);

        mrDetail.setCreateTimeStart(dateStart);
        mrDetail.setCreateTimeEnd(dateEnd);
        //按“所属公司”产生本周内发生的订单信息报表
        mrDetail.setReportId(lastYear + "R0005");
        mrSummary.setReportId(lastYear+"R0005");
        //获取月份
        String lastMonth = dateStart.substring(4,6);
        System.out.println("lastMonth=" +lastMonth);
        mrDetail.setReportMonth(lastMonth);
        mrSummary.setReportMonth(lastMonth);

        mrDetail.setReportSeq("1");
        mrSummary.setReportSeq("1");
        mrSummary.setReportTxt("订单信息-酒店汇总月报表");
        MrSummary mrSummary1 = mrSummaryMapper.selectOne(mrSummary);
        mrSummary.setCreatTime(curDateStr);
        if (mrSummary1 == null)
        {
            mrSummaryMapper.insertSelective(mrSummary);
            monReportMapper.impMonDeptData(mrDetail);
        }

        //按照用户id进行报表统计
        mrDetail.setReportId(lastYear+"R0004");
        mrSummary.setReportId(lastYear+"R0004");
        mrSummary.setReportTxt("订单信息-公司汇总月报表");
        mrSummary.setId(null);
        mrSummary.setCreatTime(null);
        MrSummary mrSummary2 = mrSummaryMapper.selectOne(mrSummary);
        mrSummary.setCreatTime(curDateStr);
        if (mrSummary2  == null)
        {
            mrSummaryMapper.insertSelective(mrSummary);
            monReportMapper.impMonUserData(mrDetail);
            monReportMapper.updDeptInfor(mrDetail);
        }
        else {
            System.out.println("mrsummary =" + mrSummary2.getReportId() );
        }
    }
}
