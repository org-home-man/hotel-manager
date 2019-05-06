package com.hotel.admin.service.impl;

import com.hotel.admin.mapper.SysDictMapper;
import com.hotel.admin.model.SysDict;
import com.hotel.admin.service.SysDictService;
import com.hotel.core.context.PageContext;
import com.hotel.core.page.*;
import com.hotel.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictServiceImpl extends AbstractService<SysDict> implements SysDictService {

	@Autowired
	private SysDictMapper sysDictMapper;

	@Override
	public int save(SysDict record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysDictMapper.insertSelective(record);
		}
		return sysDictMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<SysDict> findByLable(String label) {
		return sysDictMapper.findPageByLabel(label);
	}

	@Override
	public Page findPage(String label) {
		sysDictMapper.findPageByLabel(label);
		return PageContext.getPage();
	}
}
