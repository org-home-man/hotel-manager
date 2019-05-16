package com.hotel.admin.service.impl;

import com.hotel.admin.dto.BizHotelQueryDto;
import com.hotel.admin.dto.BizHotlUpdate;
import com.hotel.admin.model.CrtId;
import com.hotel.admin.mapper.BizHotlMapper;
import com.hotel.admin.mapper.CrtIdMapper;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.service.BizHotlService;
import com.hotel.common.utils.StringUtils;
import com.hotel.common.utils.Utils;
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

	@Override
	@Transactional
	public int save(BizHotl record) {
            if(Utils.isEmpty(record.getHotelCode()) || record.getHotelCode() == "0") {
                System.out.println("licy12345");
                /* 获取自增序列并加1*/
                System.out.println(record.getHotelCode());
				 CrtId crt =crtIdMapper.findById("hotel");
				 if(crt == null)
				 {
					 CrtId ncrt = new CrtId();
					 System.out.println("licy1");
					 ncrt.setCrtNo("1");
					 ncrt.setCrtType("hotel");
					 crtIdMapper.add(ncrt);
					 record.setHotelCode(record.getProvinceCode()+record.getCityCode() + "0000001");
				 }
				 else {
					 String crtno = crt.getCrtNo();
					 System.out.println(crtno);
      			     String newCrt = String.valueOf(Integer.parseInt(crtno) + 1);
      			     while (newCrt.length() < 7) {
                         newCrt = "0" + newCrt;
                     }
					 System.out.println(newCrt);
					 crt.setCrtNo(newCrt);
					 crtIdMapper.update(crt);
					 record.setHotelCode(record.getProvinceCode()+record.getCityCode()+ newCrt);
				 }
				 try
				 {
                     System.out.println(record.getHotelAddr());
					 bizHotlMapper.insertSelective(record);
				 }
				 catch (Exception e)
				 {
				 	/* 返回错误信息  预留开发*/
					  HttpResult.error(e.getMessage());
				 }
                return 0;
            }
			System.out.println(record.getHotelCode());
//			return bizHotlMapper.updateByPrimaryKeySelective(record);
			return bizHotlMapper.update(record);
	}

	public int delete(BizHotl record)
	{
		System.out.println(record.getId());
		return  bizHotlMapper.deleteUp(record);
	}

	@Override
	public int delete(List<BizHotl> records) {
		for(BizHotl record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public List<BizHotl> findAllData(BizHotl bizHotl) {
		return bizHotlMapper.findPage();
	}

	@Override
	public Page findPage(BizHotelQueryDto dto) {
//		a.error("test err");
//		throw new GlobalException("NotNullEception", HttpStatus.SC_INSUFFICIENT_BUSINESERR);
		bizHotlMapper.findPageByPara(dto);
//		(BizHotl)(li.get(0)).get()
//		PageResult pr =  MybatisPageHelper.findPage(pageRequest, bizHotlMapper,"findPageByPara",map);
		return PageContext.getPage();

	}
}
