package com.hotel.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotel.admin.mapper.BizRoomExtMapper;
import com.hotel.admin.mapper.BizRoomMapper;
import com.hotel.admin.mapper.CrtIdMapper;
import com.hotel.admin.model.*;
import com.hotel.common.utils.StringUtils;
import com.hotel.core.page.ColumnFilter;
import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.admin.service.BizRoomService;
import org.springframework.transaction.annotation.Transactional;

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

	private Log a = LogFactory.getLog(BizRoomServiceImpl.class);

	@Autowired
	private BizRoomMapper bizRoomMapper;

	@Autowired
	private BizRoomExtMapper bizRoomExtMapper;

	@Autowired
	private CrtIdMapper crtIdMapper;

	@Override
	@Transactional
	public int save(BizRoom record) {

		if(StringUtils.isBlank( record.getRoomCode() )  ) {
			System.out.println("进入了新增");
			//新增客房信息，
			String roomCode = record.getHotelCode()+record.getRoomType();
			//根据客房类型+ 酒店编号+ crt_type=room查询编号，如果查询到就加1，查询不到就插入一条数据
			CrtId auto = new CrtId();
			auto.setCrtType("room");
			auto.setTypeno(record.getHotelCode());
			auto.setType(record.getRoomType());
			CrtId ci =  crtIdMapper.findByRoomId(auto);
			if (ci == null) {
				auto.setCrtNo("00001");
				int i = crtIdMapper.add(auto);
				if (i==1) {
					roomCode = roomCode+"00001";
				} else {
					throw new RuntimeException("bizRoom");
				}

			} else {
				String crtNo = ci.getCrtNo();
				String ncrtNo = String.format("%07d",Integer.parseInt(crtNo)+1 );
				auto.setCrtNo(ncrtNo);
				int i = crtIdMapper.roomAutoAddUp(auto);
				if(i==1) {
					roomCode = roomCode + ncrtNo;
				} else {
					throw new RuntimeException("bizRoom");
				}
			}
			//插入BizRoom表
			record.setRoomCode(roomCode);
			record.setCreatTime(new Date());
			int room =  bizRoomMapper.add(record);
			if(room !=1) {
				throw new RuntimeException("bizRoom");
			}
			//插入BizRoomExt
			BizRoomExt bizRoomExt = getBizRoomExtObject(record,"add");
			int roomExt = bizRoomExtMapper.add(bizRoomExt);
			if (roomExt !=1 ) {
				throw new RuntimeException("bizRoom");
			}
			return 1;
//			return bizRoomMapper.add(record);
		}
		System.out.println("进入了update");
		record.setLastUpdateTime(new Date());

		int room = bizRoomMapper.update(record);
		if (room !=1) {
			throw new RuntimeException("bizRoom");
		}
		BizRoomExt bizRoomExt = getBizRoomExtObject(record,"update");
		int roomExt = bizRoomExtMapper.update(bizRoomExt);
		if (roomExt != 1) {
			throw new RuntimeException("bizRoom");
		}
		return 1;
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
		Map<String,Object> map = new HashMap<String ,Object>();
		Map<String,ColumnFilter> temp = pageRequest.getColumnFilters();
		for (Map.Entry<String,ColumnFilter> entry : temp.entrySet() ) {
			ColumnFilter columnFilter = entry.getValue();
			if (!StringUtils.isBlank(columnFilter.getValue())) {
				map.put(columnFilter.getName(),columnFilter.getValue());
			}
		}

		PageResult pr =  MybatisPageHelper.findPage(pageRequest, bizRoomMapper,"findPageByPara",map);

//		List prList =  pr.getContent();
//		if (prList.size() > 0 ) {
//			for (int i=0;i<prList.size();i++) {
//				BizRoom bizRoom = (BizRoom) prList.get(i);
//				List hotlList = bizRoom.getBizHotls();
//				if(hotlList.size() >0) {
//					BizHotl bizHotl = (BizHotl) hotlList.get(0);
//					bizRoom.setHotelCname(bizHotl.getHotelCname());
//					bizRoom.setHotelEname(bizHotl.getHotelEname());
//					bizRoom.setProvinceCode(bizHotl.getProvinceCode());
//					bizRoom.setCityCode(bizHotl.getCityCode());
//				}
//				List invList =  bizRoom.getBizInvs();
//				if(invList.size() >0) {
//					BizInv bizInv = (BizInv) invList.get(0);
//					bizRoom.setInventory(bizInv.getInventory());
//				}
//				List priseList = bizRoom.getBizPrises();
//				if(priseList.size() >0) {
//					BizPrise bizPrise = (BizPrise) priseList.get(0);
//					bizRoom.setsRoomPrice(bizPrise.getSRoomPrice());
//				}
//			}
//		}

		return pr;
	}

	private BizRoomExt getBizRoomExtObject(BizRoom br,String str) {
		BizRoomExt bizRoomExt = new BizRoomExt();

		bizRoomExt.setIsbalcony(br.getIsbalcony());

		bizRoomExt.setIsbarrifr(br.getIsbarrifr());
		bizRoomExt.setIsbeach(br.getIsbeach());
		bizRoomExt.setIsbus(br.getIsbus());
		bizRoomExt.setIschildct(br.getIschildct());
		bizRoomExt.setIsfront(br.getIsfront());
		bizRoomExt.setIsgym(br.getIsgym());
		bizRoomExt.setIsheat(br.getIsheat());
		bizRoomExt.setIshighrise(br.getIshighrise());
		bizRoomExt.setIshotsp(br.getIshotsp());
		bizRoomExt.setIsicebox(br.getIsicebox());
		bizRoomExt.setIsiron(br.getIsiron());
		bizRoomExt.setIskitchen(br.getIskitchen());
		bizRoomExt.setIsknead(br.getIsknead());
		bizRoomExt.setIslandscape(br.getIslandscape());
		bizRoomExt.setIslounge(br.getIslounge());
		bizRoomExt.setIsnosmk(br.getIsnosmk());
		bizRoomExt.setIspark(br.getIspark());
		bizRoomExt.setIsrestau(br.getIsrestau());
		bizRoomExt.setIsroomserv(br.getIsroomserv());
		bizRoomExt.setIssuper(br.getIssuper());
		bizRoomExt.setIsswmp(br.getIsswmp());
		bizRoomExt.setIstrafic(br.getIstrafic());
		bizRoomExt.setIswify(br.getIswify());
		bizRoomExt.setIswindow(br.getIswindow());
		bizRoomExt.setRoomCode(br.getRoomCode());
		if ("add".equals(str)){
			bizRoomExt.setCreatTime(new Date());
			bizRoomExt.setCreatBy(br.getCreatBy());
		} else if ("update".equals(str)){
			bizRoomExt.setLastUpdateBy(br.getLastUpdateBy());
			bizRoomExt.setLastUpdateTime(new Date());
		}
		return bizRoomExt;

	}
	
}
