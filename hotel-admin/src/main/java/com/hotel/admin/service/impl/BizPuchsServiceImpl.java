package com.hotel.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hotel.admin.mapper.BizPuchsExtMapper;
import com.hotel.admin.mapper.BizRoomExtMapper;
import com.hotel.admin.mapper.CrtIdMapper;
import com.hotel.admin.model.BizPuchsExt;
import com.hotel.admin.model.BizRoomExt;
import com.hotel.admin.model.CrtId;
import com.hotel.admin.service.SysUserService;
import com.hotel.admin.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;

import com.hotel.admin.model.BizPuchs;
import com.hotel.admin.mapper.BizPuchsMapper;
import com.hotel.admin.service.BizPuchsService;


/**
 * ---------------------------
 * 订单信息表 (BizPuchsServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-30 12:29:00
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class BizPuchsServiceImpl implements BizPuchsService {

	@Autowired
	private BizPuchsMapper bizPuchsMapper;

	@Autowired
	private BizPuchsExtMapper bizPuchsExtMapper;

	@Autowired
	private CrtIdMapper crtIdMapper;
	@Autowired
	private SysUserService sysUser;
	@Override
	public int save(BizPuchs record) {
		System.out.println("订单生成开始"+ record);
		if(record.getOrderCode() == null || record.getOrderCode() == "0") {
			CrtId crt =crtIdMapper.findById("puchs");

			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");//可以方便地修改日期格式
			String timeNow = dateFormat.format( now );
			System.out.println(timeNow);

			//获取用户id
			String userName = SecurityUtils.getUsername();
			System.out.println(userName);
			String id= sysUser.findByName(userName).getDeptId() +"";

			if(crt == null)
			{
				CrtId ncrt = new CrtId();
				System.out.println("licy1");
				ncrt.setCrtNo("0001");
				ncrt.setCrtType("puchs");
				crtIdMapper.add(ncrt);
				record.setOrderCode(id+timeNow+"0001");
			}
			else {
				String crtno = crt.getCrtNo();
				System.out.println(crtno);
				String newCrt = String.valueOf(Integer.parseInt(crtno) + 1);
				while (newCrt.length() < 4) {
					newCrt = "0" + newCrt;
				}
				System.out.println(newCrt);
				crt.setCrtNo(newCrt);
				crtIdMapper.update(crt);
				record.setOrderCode(id+timeNow+newCrt);
			}
			//1为订单预订未确认状态
			record.setStatus("1");
			System.out.println("record = "+record.getPName());
			bizPuchsMapper.add(record);
			BizPuchsExt recordExt = new BizPuchsExt();
			recordExt.setCreatBy(record.getCreatBy());
			recordExt.setCreatTime(record.getCreatTime());
			recordExt.setRoomCode(record.getRoomCode());
			recordExt.setOrderCode(record.getOrderCode());
			return  bizPuchsExtMapper.add(recordExt);
		}

		return bizPuchsMapper.update(record);
	}
	@Override
	public BizPuchs findById(Long id) {
		return null;
	}
	@Override
	public int delete(BizPuchs record) {
		return bizPuchsMapper.delete(record.getOrderCode());
	}

	@Override
	public int delete(List<BizPuchs> records) {
		for(BizPuchs record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public BizPuchs findById(String id) {
		return bizPuchsMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, bizPuchsMapper);
	}
	
}
