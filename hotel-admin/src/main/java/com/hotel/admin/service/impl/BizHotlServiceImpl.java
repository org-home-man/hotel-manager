package com.hotel.admin.service.impl;

import com.hotel.admin.dto.BizHotelQueryDto;
import com.hotel.admin.dto.BizHotlUpdate;
import com.hotel.admin.mapper.BizRoomMapper;
import com.hotel.admin.model.CrtId;
import com.hotel.admin.mapper.BizHotlMapper;
import com.hotel.admin.mapper.CrtIdMapper;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.service.BizHotlService;
import com.hotel.admin.util.IdUtils;
import com.hotel.common.utils.StringUtils;
import com.hotel.common.utils.Utils;
import com.hotel.core.annotation.SystemServiceLog;
import com.hotel.core.context.PageContext;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.http.HttpResult;
import com.hotel.core.http.HttpStatus;
import com.hotel.core.page.*;
import com.hotel.core.service.AbstractService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ---------------------------
 * 酒店信息表 (BizHotlServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-30 17:15:22
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class BizHotlServiceImpl extends AbstractService<BizHotl> implements BizHotlService {
	private Log a = LogFactory.getLog(BizHotlServiceImpl.class);

	@Autowired
	private BizHotlMapper bizHotlMapper;

	@Autowired
	private CrtIdMapper crtIdMapper;

	@Autowired
	private BizRoomMapper bizRoomMapper;

	@Autowired
	private IdUtils idUtils;

	@Override
	@Transactional
	@SystemServiceLog(description = "酒店信息保存/编辑（业务层）")
	public int save(BizHotl record) {
            if(Utils.isEmpty(record.getHotelCode()) || record.getHotelCode() == "0") {
                System.out.println("licy12345");
                /* 获取自增序列并加1*/
				String hotelCode = idUtils.generateHotelCode(record);
				record.setHotelCode(hotelCode);
				 try
				 {
					 bizHotlMapper.insertInfo(record);
				 }
				 catch (Exception e)
				 {
				 	/* 返回错误信息  预留开发*/
					 a.error("新增酒店信息失败："+e.getMessage());
					 throw new GlobalException("oraException");
				 }
                return 0;
            }
//			return bizHotlMapper.updateByPrimaryKeySelective(record);
			return bizHotlMapper.update(record);
	}

	public int delete(BizHotl record)
	{
		System.out.println(record.getId());
		return  bizHotlMapper.deleteUp(record);
	}

	@Override
	@Transactional
	@SystemServiceLog(description = "酒店信息删除（业务层）")
	public int delete(List<BizHotl> records) {
		for(BizHotl record:records) {
			BizHotl hotel = bizHotlMapper.findById(record);
			if ((bizRoomMapper.findByHtlCd(hotel.getHotelCode())).size() > 0) {
				throw new GlobalException("ExistRoomException");
			}

			delete(record);
		}
		return 1;
	}

	@Override
	public List<BizHotl> findAllData(BizHotl bizHotl) {
		return bizHotlMapper.findPage();
	}

	@Override
	@SystemServiceLog(description = "酒店信息查询（业务层）")
	public Page findPage(BizHotelQueryDto dto) {
//		a.error("test err");
//		throw new GlobalException("NotNullEception", HttpStatus.SC_INSUFFICIENT_BUSINESERR);
		try {
			List<Map>  list = bizHotlMapper.findPageByPara(dto);
		}
		catch (Exception e)
		{
			a.error("查询酒店信息失败："+e.getMessage());
			throw new GlobalException("oraException");
		}
//		(BizHotl)(li.get(0)).get()
//		PageResult pr =  MybatisPageHelper.findPage(pageRequest, bizHotlMapper,"findPageByPara",map);
		return PageContext.getPage();

	}
}
