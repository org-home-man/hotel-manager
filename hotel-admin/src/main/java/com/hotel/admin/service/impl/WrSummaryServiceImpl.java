package com.hotel.admin.service.impl;

import com.hotel.admin.mapper.WrSummaryMapper;
import com.hotel.admin.model.WrSummary;
import com.hotel.admin.service.WrSummaryService;
import com.hotel.core.context.PageContext;
import com.hotel.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WrSummaryServiceImpl implements WrSummaryService {

    @Autowired
    private WrSummaryMapper wrSummaryMapper;

    @Override
    public Page findPage(WrSummary record) {

        List<WrSummary> li = wrSummaryMapper.findPage(record);
        return PageContext.getPage();
    }

    @Override
    public int save(WrSummary record) {
        return 0;
    }

    @Override
    public int delete(WrSummary record) {
        return 0;
    }

    @Override
    public int delete(List<WrSummary> records) {
        return 0;
    }

    @Override
    public WrSummary findById(Long id) {
        return null;
    }
}
