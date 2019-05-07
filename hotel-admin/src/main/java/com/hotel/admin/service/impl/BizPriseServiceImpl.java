package com.hotel.admin.service.impl;

import java.util.Date;
import java.util.List;

import com.hotel.admin.mapper.BizPriseMapper;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.admin.model.BizPrise;
import com.hotel.admin.service.BizPriseService;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public int save(BizPrise record) {
		if (record.getPriceDateData() ==null) {
			return 0;
		}

		if (record.getPriceDateData() !=null && record.getPriceDateData().size()==0) {
			return 0;
		}
		List<BizPrise> li = record.getPriceDateData();
		System.out.println("传入进来的BizPrise:"+record);
		for (int i=0 ; i<li.size() ; i++) {
			BizPrise bp = li.get(i);
			record.setPriceDate(bp.getPriceDate());
			record.setSPrice(bp.getSPrice());
			record.setTPrice(bp.getTPrice());
			if("01".equals( record.getRoomType() ) ) {
				record.setSRoomPrice(bp.getSPrice());
			}
			if("02".equals( record.getRoomType() ) ) {
				record.setSRoomPrice(bp.getSPrice()*2);
			}
			if("03".equals( record.getRoomType() ) ) {
				record.setSRoomPrice(bp.getSPrice()*3);
			}
			if("04".equals( record.getRoomType() ) ) {
				record.setSRoomPrice(bp.getSPrice()*4);
			}
			if("01".equals( record.getRoomType() ) ) {
				record.setTRoomPrice(bp.getTPrice());
			}
			if("02".equals( record.getRoomType() ) ) {
				record.setTRoomPrice(bp.getTPrice()*2);
			}
			if("03".equals( record.getRoomType() ) ) {
				record.setTRoomPrice(bp.getTPrice()*3);
			}
			if("04".equals( record.getRoomType() ) ) {
				record.setTRoomPrice(bp.getTPrice()*4);
			}
			List<BizPrise> bpLi = bizPriseMapper.findById(record);

			if (bpLi.size() >0) {
				int j = bizPriseMapper.updateByUser(record);
				if (j != 1) {
					throw  new GlobalException("submitException",10001);
				}
			} else {
				int j = bizPriseMapper.addByUser(record);
				if (j != 1) {
					throw  new GlobalException("submitException",10001);
				}
			}

		}

		return 1;


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



	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, bizPriseMapper);
	}

	@Override
	public BizPrise findById(String id) {
		return null;
	}
}
