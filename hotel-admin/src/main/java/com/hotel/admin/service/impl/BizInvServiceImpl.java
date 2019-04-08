package com.hotel.admin.service.impl;

import java.util.List;

import com.hotel.admin.mapper.BizInvMapper;
import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.hotel.admin.model.BizInv;

import com.hotel.admin.service.BizInvService;

/**
 * ---------------------------
 * 客房库存表 (BizInvServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-08 16:15:59
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class BizInvServiceImpl implements BizInvService {

	@Autowired
	private BizInvMapper bizInvMapper;

	@Override
	public int save(BizInv record) {
		if(record.getRoomCode() == null ) {
			return bizInvMapper.add(record);
		}
		return bizInvMapper.update(record);
	}

	@Override
	public int delete(BizInv record) {
		return bizInvMapper.delete(record.getRoomCode());
	}

	@Override
	public int delete(List<BizInv> records) {
		for(BizInv record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public BizInv findById(Long id) {
		return null;
	}

	public BizInv findById(String id) {
		return bizInvMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, bizInvMapper);
	}
	
}
