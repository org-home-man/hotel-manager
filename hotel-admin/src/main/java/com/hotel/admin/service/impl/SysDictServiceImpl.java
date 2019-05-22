package com.hotel.admin.service.impl;

import com.hotel.admin.mapper.SysDictMapper;
import com.hotel.admin.model.SysDict;
import com.hotel.admin.service.SysDictService;
import com.hotel.common.utils.Utils;
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
	public Page findPage(String name) {
		sysDictMapper.findPageByName(name);
		return PageContext.getPage();
	}
}
