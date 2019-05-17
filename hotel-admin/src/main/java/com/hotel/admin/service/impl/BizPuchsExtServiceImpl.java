package com.hotel.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;

import com.hotel.admin.model.BizPuchsExt;
import com.hotel.admin.mapper.BizPuchsExtMapper;
import com.hotel.admin.service.BizPuchsExtService;

/**
 * ---------------------------
 * 订单信息辅助表 (BizPuchsExtServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-30 12:34:59
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class BizPuchsExtServiceImpl implements BizPuchsExtService {

	@Autowired
	private BizPuchsExtMapper bizPuchsExtMapper;

	@Override
	public int save(BizPuchsExt record) {
		if(record.getRoomCode() == null || record.getRoomCode() == "0") {
			return bizPuchsExtMapper.insertSelective(record);
		}
		return bizPuchsExtMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(BizPuchsExt record) {
		return bizPuchsExtMapper.deleteByPrimaryKey(record.getId());
	}
	@Override
	public BizPuchsExt findById(Long id) {
		return null;
	}
	@Override
	public int delete(List<BizPuchsExt> records) {
		for(BizPuchsExt record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public BizPuchsExt findById(String id) {
		return bizPuchsExtMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, bizPuchsExtMapper);
	}
	
}
