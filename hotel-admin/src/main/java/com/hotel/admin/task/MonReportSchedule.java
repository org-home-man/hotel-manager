package com.hotel.admin.task;

import com.hotel.admin.mapper.BizHotlMapper;
import com.hotel.admin.mapper.MrDetailMapper;
import com.hotel.admin.mapper.WrDetailMapper;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.model.MrDetail;
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
    private BizHotlMapper hotlMapper;
//    @Scheduled(fixedRate = 1000*200) //每15s执行一次
    @Scheduled(cron = "0 0 2 1 * ? * ") //每月1上午02：:0触发 


    public void weekReport() throws ParseException {
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
        mrDetail.setCreateTimeStart(dateStart);
        mrDetail.setCreateTimeEnd(dateEnd);
        //按“所属公司”产生本周内发生的订单信息报表
        mrDetail.setReportId("R0005");
        //获取月份
        String lastMonth = dateStart.substring(4,6);
        System.out.println("lastMonth=" +lastMonth);
        mrDetail.setReportMonth(lastMonth);

        mrDetail.setReportSeq("1");


        int ret = monReportMapper.impMonDeptData(mrDetail);

        //按照用户id进行报表统计
        mrDetail.setReportId("R0004");
        ret = monReportMapper.impMonUserData(mrDetail);
        ret = monReportMapper.updDeptInfor(mrDetail);

        System.out.println("ret =" + ret);
    }
}
