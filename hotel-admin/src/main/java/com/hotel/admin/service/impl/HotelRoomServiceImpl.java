package com.hotel.admin.service.impl;

import com.hotel.admin.mapper.BizRoomExtMapper;
import com.hotel.admin.mapper.BizRoomMapper;
import com.hotel.admin.mapper.CrtIdMapper;
import com.hotel.admin.mapper.HotelRoomMapper;
import com.hotel.admin.model.BizRoom;
import com.hotel.admin.model.BizRoomExt;
import com.hotel.admin.model.CrtId;
import com.hotel.admin.service.BizRoomService;
import com.hotel.admin.service.HotelRoomService;
import com.hotel.common.utils.StringUtils;
import com.hotel.core.page.ColumnFilter;
import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ---------------------------
 * 客房信息表 (BizRoomServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-01 21:00:17
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class HotelRoomServiceImpl implements HotelRoomService {

	private Log a = LogFactory.getLog(HotelRoomServiceImpl.class);

	@Autowired
	private HotelRoomMapper hotelRoomMapper;


	@Override
	@Transactional
	public int save(BizRoom record) {

		if(StringUtils.isBlank( record.getRoomCode() )  ) {
			System.out.println("进入了新增");

			return 1;
		}
		return 1;
	}

	@Override
	public int delete(BizRoom record) {
		return hotelRoomMapper.delete(record.getRoomCode());
	}

	@Override
	public int delete(List<BizRoom> records) {
		for(BizRoom record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public BizRoom findById(Long id) {
		System.out.println("进入了查询1");

		return null;
	}

	public BizRoom findById(String id) {
		System.out.println("进入了查询2");

		return hotelRoomMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		System.out.println("进入了查询3");
		Map<String,Object> map = new HashMap<String ,Object>();
		Map<String,ColumnFilter> temp = pageRequest.getColumnFilters();
		for (Map.Entry<String,ColumnFilter> entry : temp.entrySet() ) {
			ColumnFilter columnFilter = entry.getValue();
			if (!StringUtils.isBlank(columnFilter.getValue())) {
				System.out.println("查询条件" + columnFilter.getName() + ":" +columnFilter.getValue());
				map.put(columnFilter.getName(),columnFilter.getValue());
			}
		}

		PageResult pr =  MybatisPageHelper.findPage(pageRequest, hotelRoomMapper,"findPageByPara",map);

		return pr;
	}

	private BizRoomExt getBizRoomExtObject(BizRoom br,String str) {
		BizRoomExt bizRoomExt = new BizRoomExt();

		return bizRoomExt;

	}
	
}