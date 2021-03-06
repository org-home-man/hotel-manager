package com.hotel.admin.service.impl;

import com.hotel.admin.mapper.BizInvMapper;
import com.hotel.admin.model.BizInv;
import com.hotel.admin.model.BizPuchs;
import com.hotel.admin.service.BizInvService;
import com.hotel.common.utils.DateUtils;
import com.hotel.common.utils.Utils;
import com.hotel.core.annotation.SystemServiceLog;
import com.hotel.core.context.PageContext;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.Page;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ---------------------------
 * 客房库存表 (BizInvServiceImpl)         
 * ---------------------------
 */
@Service
public class BizInvServiceImpl implements BizInvService {

	@Autowired
	private BizInvMapper bizInvMapper;


	public int addByUser(BizInv code)
	{
		return bizInvMapper.addByUser(code);
	}

	@Override
	@SystemServiceLog(description = "库存信息编辑/保存(业务层)")
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
			BizInv bpLi = findByRoomCode(record);

			record.setInventory(bp.getInventory());
			if (bpLi != null) {
				int j = bizInvMapper.updateByUser(record);
				if (j != 1) {
					throw  new GlobalException("oraException");
				}
			} else {
				int j = bizInvMapper.addByUser(record);
				if (j != 1) {
					throw  new GlobalException("oraException");
				}
			}

		}

		return 1;
	}

	@Override
	public BizInv findByRoomCode(BizInv record){
		return bizInvMapper.findByHotelCode(record);
	}

	@Override
	@SystemServiceLog(description = "库存信息删除(业务层)")
	public int delete(BizInv record) {
		return bizInvMapper.deleteByPrimaryKey(record);
	}

	@Override
	public int delete(List<BizInv> records) {
		for (BizInv record : records) {
			bizInvMapper.selectByPrimaryKey(record);
		}
		return 1;
	}


	@Override
	public BizInv findById(Long id) {
		return bizInvMapper.selectByPrimaryKey(id);
	}


	@Override
	@SystemServiceLog(description = "范围内库存查询(业务层)")
	public List<BizInv> findCancelBizInv(BizPuchs bizPuchs) {
		return bizInvMapper.findCancelBizInv(bizPuchs.getRoomCode(),bizPuchs.getInDateStart(),bizPuchs.getOutDateEnd());
	}

	@Override
	public void update(BizInv bizInv) {
		bizInvMapper.updateByPrimaryKeySelective(bizInv);
	}

	@Override
	@SystemServiceLog(description = "范围内库存数量查询(业务层)")
	public Integer findInventory(BizPuchs bizPuchs) {
		if(Utils.isEmpty(bizPuchs.getRoomCode()) || Utils.isEmpty(bizPuchs.getInDateStart()) || Utils.isEmpty(bizPuchs.getOutDateEnd())){
			return 0;
		}
		Integer inventory = bizInvMapper.selectInventory(bizPuchs);
		return inventory;
	}
}
