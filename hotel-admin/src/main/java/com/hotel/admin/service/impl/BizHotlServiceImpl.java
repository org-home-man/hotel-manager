package com.hotel.admin.service.impl;

import com.hotel.admin.model.CrtId;
import com.hotel.admin.mapper.BizHotlMapper;
import com.hotel.admin.mapper.CrtIdMapper;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.service.BizHotlService;
import com.hotel.core.http.HttpResult;
import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
public class BizHotlServiceImpl implements BizHotlService {

	@Autowired
	private BizHotlMapper bizHotlMapper;

	@Autowired
	private CrtIdMapper crtIdMapper;

	@Override
	@Transactional
	public int save(BizHotl record) {
            if(record.getHotelCode() == null || record.getHotelCode() == "0") {
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
					 record.setHotelCode("1");
				 }
				 else {
					 String crtno = crt.getCrtNo();
					 System.out.println(crtno);
//					 String newCrt = String.valueOf(Integer.parseInt(crt.getCrtNo()) + 1);
      			     String newCrt = String.valueOf(Integer.parseInt(crtno) + 1);
					 System.out.println(newCrt);
					 crt.setCrtNo(newCrt);
					 crtIdMapper.update(crt);
					 record.setHotelCode(newCrt);
				 }
				 try
				 {
					 bizHotlMapper.add(record);
				 }
				 catch (Exception e)
				 {
				 	/* 返回错误信息  预留开发*/
					  HttpResult.error(e.getMessage());
				 }
                return 0;
            }
		System.out.println(record.getHotelCode());

		return bizHotlMapper.update(record);

       // return bizHotlMapper.add(record);

    }

	@Override
	public int delete(BizHotl record) {
		System.out.println(record.getHotelCode());
		return bizHotlMapper.delete(record.getHotelCode());
	}

	@Override
	public int delete(List<BizHotl> records) {
		for(BizHotl record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public BizHotl findById(Long id) {
		return null;
	}

	@Override
	public BizHotl findById(String id) {
		return bizHotlMapper.findById(id);
	}

	@Override
	public List<BizHotl> findAllData(BizHotl bizHotl) {
		return bizHotlMapper.findPage();
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, bizHotlMapper);
	}
	
}