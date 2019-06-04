package com.hotel.admin.service.impl;

import com.hotel.admin.dto.HotelRoomQry;
import com.hotel.admin.mapper.BizRecommendRoomMapper;
import com.hotel.admin.mapper.BizRoomMapper;
import com.hotel.admin.mapper.HotelRoomMapper;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.model.BizInv;
import com.hotel.admin.model.BizRecommendRoom;
import com.hotel.admin.model.BizRoom;
import com.hotel.admin.service.HotelRoomService;
import com.hotel.common.utils.Utils;
import com.hotel.core.context.PageContext;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.page.Page;
import com.hotel.core.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 */

@Service
@Transactional
public class HotelRoomServiceImpl extends AbstractService<BizRoom> implements HotelRoomService {

	private Logger LOGGER = LoggerFactory.getLogger(HotelRoomServiceImpl.class);

	@Autowired
	private HotelRoomMapper hotelRoomMapper;

	@Autowired
	private BizRecommendRoomMapper bizRecommendRoomMapper;

	@Autowired
	private BizRoomMapper bizRoomMapper;

	@Override
	public Page findPagePara(HotelRoomQry hotelRoomQry) {
		try {
			validateDate(hotelRoomQry);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//调用存储过程获取当前返回时间轴
		hotelRoomMapper.callCalendar(hotelRoomQry.getInDateStart(),hotelRoomQry.getOutDateEnd());

		List<BizRoom> pageByPara = hotelRoomMapper.findPageByPara(hotelRoomQry);

		return PageContext.getPage();
	}

	@Override
	public List<BizHotl> findLikeByName(String name) {
		return hotelRoomMapper.findLikeByName(name);
	}

	/*
	用于查询三个广告位的客房
	 */
	@Override
	public List<BizRecommendRoom> findCustroomInfo() {
		BizRecommendRoom recoRoom = new BizRecommendRoom();
		List<BizRoom> bizList =  bizRoomMapper.findByRecommend();
		if ( bizList.size() > 0 ) {
			BizRoom br = bizList.get(0);
			recoRoom.setRoomCode(br.getRoomCode());
		}
		recoRoom.setCustroomType("03");

		List<BizRecommendRoom> li = bizRecommendRoomMapper.selectAll();
		li.add(recoRoom);
		return li;
	}

	/**
	 * 客房检索时间轴确认
	 * @param hotelRoomQry
	 */
	protected void validateDate(HotelRoomQry hotelRoomQry) throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		//当前日期
		String curDateStr = fmt.format(new Date());
		Date curDate = fmt.parse(curDateStr);
		//都为空 默认显示今天到后五天的酒店信息
		if (Utils.isEmpty(hotelRoomQry.getInDateStart()) && Utils.isEmpty(hotelRoomQry.getOutDateEnd())) {
			hotelRoomQry.setInDateStart(curDateStr);
			//获取后五天
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(curDate);
			calendar.add(Calendar.DAY_OF_MONTH,5);
			hotelRoomQry.setOutDateEnd(fmt.format(calendar.getTime()));
		}else if(Utils.isEmpty(hotelRoomQry.getInDateStart())){
			//开始时间为空
			String outDateEnd = hotelRoomQry.getOutDateEnd();
			Date dateEnd = fmt.parse(outDateEnd);
			if(curDate.compareTo(dateEnd) == 0){
				//今天 设置开始为今天
				hotelRoomQry.setInDateStart(outDateEnd);
			}else if (curDate.compareTo(dateEnd) >0){
				//得到往前五天时间
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dateEnd);
				calendar.add(Calendar.DAY_OF_MONTH,-5);
				Date time = calendar.getTime();
				if(curDate.compareTo(time)<0){
					hotelRoomQry.setInDateStart(curDateStr);
				}else{
					hotelRoomQry.setInDateStart(fmt.format(time));
				}
			}else{
				//结束日期有误
				throw new GlobalException("dateException");
			}
		}else if(Utils.isEmpty(hotelRoomQry.getOutDateEnd())){
			//获取后五天
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(curDate);
			calendar.add(Calendar.DAY_OF_MONTH,5);
			hotelRoomQry.setOutDateEnd(fmt.format(calendar.getTime()));
		}
	}


}