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
import com.hotel.common.utils.DateUtils;
import com.hotel.common.utils.StringUtils;
import com.hotel.common.utils.Utils;
import com.hotel.core.context.PageContext;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.page.*;
import com.hotel.core.service.AbstractService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hotel.admin.model.SysParaConfig;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import java.util.*;

/**
 *
 */

@Service
@Transactional
public class HotelRoomServiceImpl extends AbstractService<BizRoom> implements HotelRoomService {

	private Logger LOGGER = LoggerFactory.getLogger(HotelRoomServiceImpl.class);

	@Autowired
	private HotelRoomMapper hotelRoomMapper;

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
				throw new GlobalException("日期有误");
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