package com.hotel.admin.service.impl;

import java.util.Date;
import java.util.List;

import com.hotel.admin.dto.HotelRoomQry;
import com.hotel.admin.mapper.BizPriseMapper;
import com.hotel.core.annotation.SystemServiceLog;
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
 */
@Service
public class BizPriseServiceImpl implements BizPriseService {

	@Autowired
	private BizPriseMapper bizPriseMapper;

	@Override
	@Transactional
	@SystemServiceLog(description = "牌价信息保存/编辑（业务层）")
	public int save(BizPrise record) {
		if (record.getPriceDateData() ==null) {
			return 0;
		}

		if (record.getPriceDateData() !=null && record.getPriceDateData().size()==0) {
			return 0;
		}
		List<BizPrise> li = record.getPriceDateData();
		for (int i=0 ; i<li.size() ; i++) {
			BizPrise bp = li.get(i);
			record.setPriceDate(bp.getPriceDate());
			record.setSPrice(bp.getSPrice());
			record.setTPrice(bp.getTPrice());
			if("1".equals( record.getRoomType() ) ) {
				record.setSRoomPrice(bp.getSPrice());
			}
			if("2".equals( record.getRoomType() ) ) {
				record.setSRoomPrice(bp.getSPrice()*2);
			}
			if("3".equals( record.getRoomType() ) ) {
				record.setSRoomPrice(bp.getSPrice()*3);
			}
			if("4".equals( record.getRoomType() ) ) {
				record.setSRoomPrice(bp.getSPrice()*4);
			}
			if("5".equals( record.getRoomType() ) ) {
				record.setSRoomPrice(bp.getSPrice());
			}
			List<BizPrise> bpLi = bizPriseMapper.findById(record);

			if (bpLi.size() >0) {
				int j = bizPriseMapper.updateByUser(record);
				if (j != 1) {
					throw  new GlobalException("oraException");
				}
			} else {
				int j = bizPriseMapper.addByUser(record);
				if (j != 1) {
					throw  new GlobalException("oraException");
				}
			}

		}

		return 1;


	}

	@Override
	@SystemServiceLog(description = "牌价信息删除（业务层）")
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

	@Override
	@SystemServiceLog(description = "牌价信息根据日期查询（业务层）")
	public List<BizPrise> findByDate(HotelRoomQry record) {
		List<BizPrise> li =  bizPriseMapper.findByDate(record);
		return li;
	}
}
