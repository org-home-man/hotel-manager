package com.hotel.admin.service.impl;

import com.hotel.admin.dto.*;
import com.hotel.admin.mapper.*;
import com.hotel.admin.model.*;
import com.hotel.admin.qo.MrSummaryQo;
import com.hotel.admin.qo.WrSummaryQo;
import com.hotel.admin.service.MrDetailService;
import com.hotel.admin.service.MrSummaryService;
import com.hotel.admin.service.WrDetailService;
import com.hotel.core.annotation.SystemServiceLog;
import com.hotel.core.context.UserContext;
import com.hotel.core.exception.GlobalException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MrDetailServiceImpl implements MrDetailService {

    @Autowired
    private MrDetailMapper mrDetailMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private BizHotlMapper bizHotlMapper;

    @Autowired
    private BizPuchsMapper bizPuchsMapper;



    @Override
    public List<MrR0001DetailDto> findR0001Page(MrSummaryQo record) {
        try {
            record = getMonthDate(record);
        } catch (ParseException e) {
            throw new GlobalException("sysException");
        }
        record.setDeptId(String.valueOf( UserContext.getCurrentUser().getDeptId() ));
        List<MrR0001DetailDto> li = bizPuchsMapper.r0001MonthReport(record);

        return li;
    }

    @Override
    @SystemServiceLog(description = "r0001报表Excel数据查询")
    public Map<String,List<MrR0001DetailDto>> selectR0001All(MrSummaryQo record) {
        try {
            record = getMonthDate(record);
        } catch (ParseException e) {
            throw new GlobalException("sysException");
        }
        record.setDeptId(String.valueOf( UserContext.getCurrentUser().getDeptId() ));
        Map<String,List<MrR0001DetailDto>> map = new HashMap<>();
        List<MrR0001DetailDto> li = bizPuchsMapper.r0001MonthReport(record);
        if (li.size() > 2) {
            MrR0001DetailDto mrR0001DetailDto = li.get(0);
            map.put(mrR0001DetailDto.getDeptName(),li);
        } else {
            map.put("MonthReport",li);
        }

        return map;
    }

    @Override
    public List<MrR0004DetailDto> findR0004Page(MrSummaryQo record) {

       return mrDetailMapper.r0004ReportSelect(record);

    }

    @Override
    public List<MrR0005DetailDto> findR0005Page(MrSummaryQo record) {
        return mrDetailMapper.r0005ReportSelect(record);
    }

    @Override
    public MrPendAmtTotlDto findR0004Data(MrSummaryQo record) {


        return mrDetailMapper.findR0004Data(record);
    }

    @Override
    public MrPendAmtTotlDto findR0005Data(MrSummaryQo record) {
        return null;
    }

    @Override
    public int save(MrDetail record) {
        return 0;
    }

    @Override
    public int delete(MrDetail record) {
        return 0;
    }

    @Override
    public int delete(List<MrDetail> records) {
        return 0;
    }

    @Override
    public MrDetail findById(Long id) {
        return null;
    }

    /*
    获取月初和月末的日期
     */
    private MrSummaryQo getMonthDate(MrSummaryQo mrSummaryQo) throws ParseException {
        String reportId = mrSummaryQo.getReportId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(reportId.substring(0,4)+mrSummaryQo.getReportMonth());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        String firstDay =sf.format(calendar.getTime());

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.MONTH,1);
        cal.set(Calendar.DAY_OF_MONTH,0);
        String lastDay = sf.format(cal.getTime());

        mrSummaryQo.setStartDate(firstDay);
        mrSummaryQo.setEndDate(lastDay);

        return mrSummaryQo;
    }


}
