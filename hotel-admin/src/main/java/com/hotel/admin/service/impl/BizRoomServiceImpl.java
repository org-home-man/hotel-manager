package com.hotel.admin.service.impl;

import java.util.List;
import java.util.Map;

import com.hotel.admin.mapper.BizRoomMapper;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.model.BizInv;
import com.hotel.admin.model.BizPrise;
import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.admin.model.BizRoom;
import com.hotel.admin.service.BizRoomService;

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
public class BizRoomServiceImpl implements BizRoomService {

	@Autowired
	private BizRoomMapper bizRoomMapper;

	@Override
	public int save(BizRoom record) {

		if(record.getRoomCode() == null || record.getRoomCode() == "0") {

			record.setRoomCode("123457");
			System.out.println("进入了add");
			return bizRoomMapper.add(record);
		}
		System.out.println("进入了update");
		return bizRoomMapper.update(record);
	}

	@Override
	public int delete(BizRoom record) {
		return bizRoomMapper.delete(record.getRoomCode());
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
		return null;
	}

	public BizRoom findById(String id) {
		return bizRoomMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {

		PageResult pr =  MybatisPageHelper.findPage(pageRequest, bizRoomMapper);
		List prList =  pr.getContent();
		System.out.println(prList.size());
		if (prList.size() > 0 ) {
			for (int i=0;i<prList.size();i++) {
				BizRoom bizRoom = (BizRoom) prList.get(i);
				List hotlList = bizRoom.getBizHotls();
				if(hotlList.size() >0) {
					BizHotl bizHotl = (BizHotl) hotlList.get(0);
					bizRoom.setHotelCname(bizHotl.getHotelCname());
					bizRoom.setHotelEname(bizHotl.getHotelEname());
					bizRoom.setProvinceCode(bizHotl.getProvinceCode());
					bizRoom.setCityCode(bizHotl.getCityCode());

				}
				List invList =  bizRoom.getBizInvs();
				if(invList.size() >0) {
					BizInv bizInv = (BizInv) invList.get(0);
					bizRoom.setInventory(bizInv.getInventory());
				}
				List priseList = bizRoom.getBizPrises();
				if(priseList.size() >0) {
					BizPrise bizPrise = (BizPrise) priseList.get(0);
					bizRoom.setsRoomPrice(bizPrise.getSRoomPrice());
				}
			}
		}

		return pr;
	}
	
}
