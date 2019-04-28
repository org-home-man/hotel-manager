package com.hotel.admin.service.impl;

import java.util.Date;
import java.util.List;

import com.hotel.admin.mapper.BizInvMapper;
import com.hotel.core.exception.GlobalException;
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

		if (record.getStockDateData() ==null) {
			return 0;
		}

		if (record.getStockDateData() !=null && record.getStockDateData().size()==0) {
			return 0;
		}
		List<BizInv> li = record.getStockDateData();
		System.out.println("传入进来的BizPrise:"+record);
		for (int i=0 ; i<li.size() ; i++) {
			BizInv bp = li.get(i);
			record.setInvDate(bp.getInvDate());
			record.setCreatBy(record.getLastUpdateBy());
			record.setCreatTime(new Date());
			record.setLastUpdateTime(new Date());

			List<BizInv> bpLi = bizInvMapper.findById(record);

			if (bpLi.size() >0) {
				int j = bizInvMapper.updateByUser(record);
				if (j != 1) {
					throw  new GlobalException("submitException",10001);
				}
			} else {
				int j = bizInvMapper.addByUser(record);
				if (j != 1) {
					throw  new GlobalException("submitException",10001);
				}
			}

		}

		return 1;

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
		return null;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, bizInvMapper);
	}
	
}
