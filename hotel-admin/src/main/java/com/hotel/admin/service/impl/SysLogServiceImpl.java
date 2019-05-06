package com.hotel.admin.service.impl;

import com.hotel.admin.mapper.SysLogMapper;
import com.hotel.admin.model.SysLog;
import com.hotel.admin.service.SysLogService;
import com.hotel.core.context.PageContext;
import com.hotel.core.page.*;
import com.hotel.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl extends AbstractService<SysLog> implements SysLogService {

	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public int save(SysLog record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysLogMapper.insertSelective(record);
		}
		return sysLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page findPage(String name) {
		sysLogMapper.findPageByUserName(name);
		return PageContext.getPage();
	}
	
}
