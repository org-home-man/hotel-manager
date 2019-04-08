package com.hotel.admin.service.impl;

import java.util.List;

import com.hotel.admin.mapper.BizPriseMapper;
import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.admin.model.BizPrise;
import com.hotel.admin.service.BizPriseService;

/**
 * ---------------------------
 * 客房牌价表 (BizPriseServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-08 16:15:59
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class BizPriseServiceImpl implements BizPriseService {

	@Autowired
	private BizPriseMapper bizPriseMapper;

	@Override
	public int save(BizPrise record) {
		if(record.getRoomCode() == null ) {
			return bizPriseMapper.add(record);
		}
		return bizPriseMapper.update(record);
	}

	@Override
	public int delete(BizPrise record) {
		return bizPriseMapper.delete(record.getRoomCode());
	}

	@Override
	public int delete(List<BizPrise> records) {
		for(BizPrise record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public BizPrise findById(Long id) {
		return null;
	}


	public BizPrise findById(String id) {
		return bizPriseMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, bizPriseMapper);
	}
	
}
