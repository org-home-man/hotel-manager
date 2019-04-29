package com.hotel.admin.service.impl;

import com.hotel.admin.dto.HotelRoomQry;
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
import com.hotel.core.context.PageContext;
import com.hotel.core.page.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hotel.admin.model.SysParaConfig;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import java.util.*;

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
		return null;
	}


	@Override
	public Page findPagePara(HotelRoomQry HotelRoomQry) {
		System.out.println("进入了查询3pageRequest=" +HotelRoomQry);

//		PageResult pr =  MybatisPageHelper.findPage(pageRequest, hotelRoomMapper,"findPageByPara",map);
		List<Map> retinfo = hotelRoomMapper.findPageByPara(HotelRoomQry);
		System.out.println("返回数据 = "+retinfo );
		if(HotelRoomQry.getoutDateEnd() == null || HotelRoomQry.getinDateStart()== null)
		{
			System.out.println("查询条件" );
//			return pr;
			return PageContext.getPage();
		}
		int invDate = 0;
		 try {
			 SimpleDateFormat stodate = new SimpleDateFormat("yyyyMMdd");
			 Date date1 = stodate.parse((String)HotelRoomQry.getoutDateEnd());
			 Date date2 = stodate.parse((String)HotelRoomQry.getinDateStart());
			 /* 取时间跨度，需要加1*/
			 invDate = (int) ((date1.getTime() - date2.getTime()) / (1000*3600*24)) +1;
			 if(invDate - 1< 0)
			 {
				 System.out.println("退房日期应该大于等于入住时间");
			 }
			 System.out.println("invDate=" + invDate);
		 } catch (ParseException e) {
		 	e.printStackTrace();
		 }
		int j = 1;
//		for (int i = 0; i <pr.getContent().size(); i++) {
//			System.out.println("roomcode = " + ((BizRoom) pr.getContent().get(i))  +" i = " +i);
//			if (i < pr.getContent().size() - 1) {
//				if (((BizRoom) pr.getContent().get(i)).getRoomCode().equals(((BizRoom) pr.getContent().get(i + 1)).getRoomCode())) {
//					j++;
//				} else {
//					j = 1;
//				}
//				if (invDate == j) {
//					System.out.println("房间牌价在时间范围内都满足要求 " + "j = "+ j + "roomcode=" + ((BizRoom) pr.getContent().get(i)).getRoomCode());
////					prRet.setContent(pr.getContent().get(i));
////					lists.add(pr.getContent().get(i));
////					prRet.setContent(lists);
//				}
//			}
//		}
//		return prRet;
		return PageContext.getPage();
	}

	private BizRoomExt getBizRoomExtObject(BizRoom br,String str) {
		BizRoomExt bizRoomExt = new BizRoomExt();

		return bizRoomExt;

	}
	
}