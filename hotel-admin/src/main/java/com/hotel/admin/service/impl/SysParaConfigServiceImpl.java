package com.hotel.admin.service.impl;

import java.util.List;

import com.hotel.admin.mapper.SysParaConfigMapper;
import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hotel.admin.model.SysParaConfig;
import com.hotel.admin.service.SysParaConfigService;

/**
 * ---------------------------
 * 参数配置表 (SysParaConfigServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-05 11:24:48
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class SysParaConfigServiceImpl implements SysParaConfigService, com.hotel.admin.service.impl.SysParaConfigService {

	@Autowired
	private SysParaConfigMapper sysParaConfigMapper;

	@Override
	public int save(SysParaConfig record) {
		if(record.getParaSubCode2() == null || record.getParaSubCode2() == "0") {
			return sysParaConfigMapper.add(record);
		}
		return sysParaConfigMapper.update(record);
	}

	@Override
	public int delete(SysParaConfig record) {
		return sysParaConfigMapper.delete(record.getParaCode());
	}

	@Override
	public int delete(List<SysParaConfig> records) {
		for(SysParaConfig record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysParaConfig findById(Long id) {
		return null;
	}

	@Override
	public SysParaConfig findById(String id) {
		return null;
	}


	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, sysParaConfigMapper);
	}
	
}
