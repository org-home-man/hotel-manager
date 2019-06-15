package com.hotel.admin.service.impl;

import com.hotel.admin.dto.WrDetailDto;
import com.hotel.admin.mapper.SysDeptMapper;
import com.hotel.admin.mapper.WrDetailMapper;
import com.hotel.admin.mapper.WrSummaryMapper;
import com.hotel.admin.model.SysDept;
import com.hotel.admin.model.WrDetail;
import com.hotel.admin.model.WrSummary;
import com.hotel.admin.qo.WrSummaryQo;
import com.hotel.admin.service.SysDeptService;
import com.hotel.admin.service.WrDetailService;
import com.hotel.admin.service.WrSummaryService;
import com.hotel.core.context.PageContext;
import com.hotel.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WrDetailServiceImpl implements WrDetailService {

    @Autowired
    private WrDetailMapper wrDetailMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<WrDetailDto> findR0002Page(WrSummaryQo record) {

        List<SysDept> deptLi =  sysDeptMapper.selectAll();
        List<WrDetailDto> list = new ArrayList<WrDetailDto>();
        for (int i = 0;i<deptLi.size() ; i++) {
            SysDept sysDept = deptLi.get(i);
            record.setDeptId(String.valueOf(sysDept.getId()));
            List<WrDetailDto> li = wrDetailMapper.findR0002Page(record);
            if (li.size()>2) {
                list.addAll(li);
            }
        }
        return list;
    }

    @Override
    public Map<String, List<WrDetailDto>> selectAll(WrSummaryQo record) {
        List<SysDept> deptLi =  sysDeptMapper.selectAll();
        Map<String,List<WrDetailDto>> map = new HashMap<>();
        List<WrDetailDto> list = new ArrayList<WrDetailDto>();
        for (int i = 0;i<deptLi.size() ; i++) {
            SysDept sysDept = deptLi.get(i);
            record.setDeptId(String.valueOf(sysDept.getId()));
            List<WrDetailDto> li = wrDetailMapper.findR0002Page(record);
            if (li.size()>2) {
                WrDetailDto wrDetailDto = li.get(0);
                String sheetName = wrDetailDto.getDeptName();
                map.put(sheetName,li);
            }
        }
        return map;
    }

    @Override
    public List<WrDetailDto> findR0003Page(WrSummaryQo record) {
        List<SysDept> deptLi =  sysDeptMapper.selectAll();
        List<WrDetailDto> list = new ArrayList<WrDetailDto>();
        for (int i = 0;i<deptLi.size() ; i++) {
            SysDept sysDept = deptLi.get(i);
            record.setDeptId(String.valueOf(sysDept.getId()));
            List<WrDetailDto> li = wrDetailMapper.findR0003Page(record);
            if (li.size()>2) {
                list.addAll(li);
            }
        }
        return list;
    }


    @Override
    public int save(WrDetail record) {
        return 0;
    }

    @Override
    public int delete(WrDetail record) {
        return 0;
    }

    @Override
    public int delete(List<WrDetail> records) {
        return 0;
    }

    @Override
    public WrDetail findById(Long id) {
        return null;
    }
}
