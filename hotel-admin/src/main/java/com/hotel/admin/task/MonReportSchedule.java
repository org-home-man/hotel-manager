package com.hotel.admin.task;

import com.hotel.admin.constants.Constant;
import com.hotel.admin.mapper.*;
import com.hotel.admin.model.*;
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
public class MonReportSchedule {

    @Autowired(required=false)
    private MrDetailMapper monReportMapper;

    @Autowired(required=false)
    private MrSummaryMapper mrSummaryMapper;

    @Autowired(required=false)
    private MrOrderdetailMapper mrOrderdetailMapper;
//    @Scheduled(fixedRate = 1000*200) //每15s执行一次
//    @Scheduled(cron = "0 0 2 1 * ?") //每月1上午02：:0触发 
   // @Scheduled(cron = "0 40 15 14 * ?") //每月1上午02：:0触发 

//    @Scheduled(cron = "0 0 13 21 * ?") //每月1上午02：:0触发 

    @Scheduled(cron = "${report.month.times}") //每月1上午02：:0触发 
    public void monReport() throws ParseException {
         SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        //当前日期
        String curDateStr = fmt.format(new Date());
        Date curDate = fmt.parse(curDateStr);
        //获取前七天一周的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.DAY_OF_MONTH,0);
        String dateEnd = fmt.format(calendar.getTime());
       // calendar.add(Calendar.DAY_OF_MONTH,0);
        calendar.add(Calendar.MONTH,-1);
        String dateStart = fmt.format(calendar.getTime());
        System.out.println("报表生成时间范围" + dateStart +"  "+ dateEnd);
        MrDetail mrDetail = new MrDetail();
        MrSummary mrSummary = new MrSummary();
        String lastYear = dateStart.substring(0,4);

        mrDetail.setCreateTimeStart(dateStart);
        mrDetail.setCreateTimeEnd(dateEnd);

        //获取月份
        String lastMonth = dateStart.substring(4,6);
        System.out.println("lastMonth=" +lastMonth);
        mrDetail.setReportMonth(lastMonth);
        mrSummary.setReportMonth(lastMonth);

        mrDetail.setReportSeq("0");
        mrSummary.setReportSeq("0");

        //月报明细表数据生成
        mrSummary.setReportId(lastYear+"R0006");
        mrSummary.setReportTxt("订单明细及待结算款额表");
        mrSummary.setId(null);
        mrSummary.setCreatTime(null);
        MrSummary mrSummary4 = mrSummaryMapper.selectOne(mrSummary);
        mrSummary.setCreatTime(curDateStr);
        MrOrderdetail mrOrderdetail =new MrOrderdetail();
        mrOrderdetail.setReportId(lastYear+"R0006");
        mrOrderdetail.setReportMonth(lastMonth);
        mrOrderdetail.setReportSeq("0");
        mrOrderdetail.setCreateTimeStart(dateStart);
        mrOrderdetail.setCreateTimeEnd(dateEnd);
        if (mrSummary4  == null) {
            mrSummaryMapper.insertSelective(mrSummary);
            mrOrderdetailMapper.impMrOrderdetailData(mrOrderdetail);
            //插入月报开始时间范围前的未结算年数据
            mrOrderdetail.setId(null);
            mrOrderdetail.setCreateTimeStart(null);
            mrOrderdetail.setCreateTimeEnd(dateStart);
            mrOrderdetail.setStatus(Constant.PUCHS_STAT_NO_ACCOUNTS);
            mrOrderdetailMapper.impMrOrderdetailData(mrOrderdetail);
            WebSocketServer.sendMessageToManager("订单明细及待结算款额表生成成功,请注意查看", Constant.MONTH_MES);
        }

        //按照公司与酒店来统计数据
        mrDetail.setReportId(lastYear+"R0005");
        mrSummary.setReportId(lastYear+"R0005");
        mrSummary.setReportTxt("酒店分类统计表");
        mrSummary.setId(null);
        mrSummary.setCreatTime(null);
        MrSummary mrSummary8 = mrSummaryMapper.selectOne(mrSummary);
        mrSummary.setCreatTime(curDateStr);
        if (mrSummary8  == null){
            mrSummaryMapper.insertSelective(mrSummary);
            mrDetail.setStatus(Constant.PUCHS_STAT_NO_ACCOUNTS);
            monReportMapper.impMonDeptData(mrDetail);
            monReportMapper.updDeptInforNew(mrDetail);
            monReportMapper.updHotelName(mrDetail);
            WebSocketServer.sendMessageToManager("酒店分类统计表，请注意查看", Constant.MONTH_MES);
        }else {
            System.out.println("mrsummary =" + mrSummary8.getReportId() );
        }

        //按“所属公司”产生本周内发生的订单信息报表
//        mrDetail.setReportId(lastYear + "R0005");
//        mrSummary.setReportId(lastYear+"R0005");
//        mrSummary.setReportTxt("订单信息-酒店汇总月报表");
//        MrSummary mrSummary1 = mrSummaryMapper.selectOne(mrSummary);
//        mrSummary.setCreatTime(curDateStr);
//        if (mrSummary1 == null)
//        {
//            mrSummaryMapper.insertSelective(mrSummary);
//            monReportMapper.impMonDeptData(mrDetail);
//            //将酒店名称更新进表
//            monReportMapper.updHotelName(mrDetail);
//            WebSocketServer.sendMessageToManager("酒店汇总月报表生成成功，请注意查看", Constant.MONTH_MES);
//        }

        //按照用户id进行报表统计
        mrDetail.setReportId(lastYear+"R0004");
        mrSummary.setReportId(lastYear+"R0004");
        mrSummary.setReportTxt("公司分类统计表");
        mrSummary.setId(null);
        mrSummary.setCreatTime(null);
        MrSummary mrSummary2 = mrSummaryMapper.selectOne(mrSummary);
        mrSummary.setCreatTime(curDateStr);
        if (mrSummary2  == null)
        {
            mrSummaryMapper.insertSelective(mrSummary);
            monReportMapper.impMonUserData(mrDetail);
            monReportMapper.updDeptInfor(mrDetail);
            WebSocketServer.sendMessageToManager("公司分类统计表生成成功，请注意查看", Constant.MONTH_MES);
        }
        else {
            System.out.println("mrsummary =" + mrSummary2.getReportId() );
        }

        //用户月报
        mrSummary.setReportId(lastYear+"R0001");
        mrSummary.setReportTxt("订单明细及待结算款额表");
        mrSummary.setId(null);
        mrSummary.setCreatTime(null);
        MrSummary mrSummary3 = mrSummaryMapper.selectOne(mrSummary);
        mrSummary.setCreatTime(curDateStr);
        if (mrSummary3  == null) {
            mrSummaryMapper.insertSelective(mrSummary);
        }
        else {
            System.out.println("mrsummary =" + mrSummary3.getReportId() );
            WebSocketServer.sendMessageToManager("订单明细及待结算款额表生成成功，请注意查看", Constant.MONTH_MES);
        }

        //mrOrderdetailMapper.
        monReportMapper.updPandAmt(mrDetail);
    }
}
