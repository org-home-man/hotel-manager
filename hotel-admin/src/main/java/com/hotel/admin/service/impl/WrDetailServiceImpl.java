package com.hotel.admin.service.impl;

import com.hotel.admin.dto.WrDetailDto;
import com.hotel.admin.dto.WrR0003DetailDto;
import com.hotel.admin.mapper.*;
import com.hotel.admin.model.*;
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

    @Autowired
    private BizHotlMapper bizHotlMapper;



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

    /*
    查询R0002报表
     */
    @Override
    public Map<String, List<WrDetailDto>> selectR0002All(WrSummaryQo record) {
        List<SysDept> deptLi =  sysDeptMapper.selectAll();
        Map<String,List<WrDetailDto>> map = new HashMap<>();
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
    public List<WrR0003DetailDto> findR0003Page(WrSummaryQo record) {
        List<BizHotl> hotlLi = bizHotlMapper.selectAll();
        List<WrR0003DetailDto> list = new ArrayList<WrR0003DetailDto>();
        for (int i = 0;i<hotlLi.size() ; i++) {
            BizHotl bizHotl = hotlLi.get(i);
            record.setHotelCode(bizHotl.getHotelCode());
            List<WrR0003DetailDto> li = wrDetailMapper.findR0003Page(record);
            if (li.size()>2) {
                list.addAll(li);
            }
        }
        return list;
    }

    @Override
    public Map<String, List<WrR0003DetailDto>> selectR0003All(WrSummaryQo record) {
        List<BizHotl> hotlLi = bizHotlMapper.selectAll();
        Map<String,List<WrR0003DetailDto>> map = new HashMap<>();
        for (int i = 0;i<hotlLi.size() ; i++) {
            BizHotl bizHotl = hotlLi.get(i);
            record.setHotelCode(bizHotl.getHotelCode());
            List<WrR0003DetailDto> li = wrDetailMapper.findR0003Page(record);
            if (li.size()>2) {
                WrR0003DetailDto WrR0003DetailDto = li.get(0);
                String sheetName = WrR0003DetailDto.getHotelName();
                map.put(sheetName,li);
            }
        }
        return map;

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
