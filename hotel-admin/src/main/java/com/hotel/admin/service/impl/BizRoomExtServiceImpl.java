package com.hotel.admin.service.impl;

import java.util.List;

import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hotel.admin.model.BizRoomExt;
import com.hotel.admin.mapper.BizRoomExtMapper;
import com.hotel.admin.service.BizRoomExtService;

/**
 * ---------------------------
 * 客房信息铺表 (BizRoomExtServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-07 17:16:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class BizRoomExtServiceImpl implements BizRoomExtService {

	@Autowired
	private BizRoomExtMapper bizRoomExtMapper;

	@Override
	public int save(BizRoomExt record) {
		if(record.getRoomCode() == null || record.getRoomCode() == "0") {
			return bizRoomExtMapper.add(record);
		}
		return bizRoomExtMapper.update(record);
	}

	@Override
	public int delete(BizRoomExt record) {
		return bizRoomExtMapper.delete(record.getRoomCode());
	}

	@Override
	public int delete(List<BizRoomExt> records) {
		for(BizRoomExt record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public BizRoomExt findById(Long id) {
		return null;
	}


	public BizRoomExt findById(String id) {
		return bizRoomExtMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, bizRoomExtMapper);
	}
	
}
