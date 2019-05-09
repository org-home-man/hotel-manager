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
import com.hotel.common.utils.Utils;
import com.hotel.core.context.PageContext;
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
	public Page findPagePara(HotelRoomQry HotelRoomQry) {
		hotelRoomMapper.findPageByPara(HotelRoomQry);
		return PageContext.getPage();
	}

}