package com.hotel.admin.service.impl;

import com.hotel.admin.mapper.*;
import com.hotel.admin.model.*;
import com.hotel.admin.qo.BizPuchsQuery;
import com.hotel.admin.service.BizInvService;
import com.hotel.admin.service.BizPuchsService;
import com.hotel.admin.service.SysUserService;
import com.hotel.admin.util.SecurityUtils;
import com.hotel.common.utils.DateUtils;
import com.hotel.common.utils.Utils;
import com.hotel.core.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


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
    private BizInvMapper bizInvMapper;

    @Autowired
    private BizRoomMapper bizRoomMapper;

	@Autowired
	private CrtIdMapper crtIdMapper;
	@Autowired
	private SysUserService sysUser;
	@Autowired
	private BizInvService bizInvService;

    @Override
	public int save(BizPuchs record) {
		if(Utils.isEmpty(record.getInDateStart()) || Utils.isEmpty(record.getOutDateEnd())){
			return 0;
		}
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
//				System.out.println("licy1");
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
//			//获取入住时间和退房时间
//			if(record.getOutDate() == null || record.getInDate()==null)
//			{
//				System.out.println("请选择入住时间和退房时间");
//			}
//			int invDate = 0;
//			SimpleDateFormat stodate = new SimpleDateFormat("yyyyMMdd");
//			String outdate = stodate.format(record.getOutDate());
//			String indate = stodate.format(record.getInDate());
//			/* 取时间跨度，需要加1*/
//			System.out.println("indate , outdate "  + indate +outdate);
//			invDate = (int) ((record.getOutDate().getTime() - record.getInDate().getTime()) / (1000*3600*24)) +1;
//			System.out.println("indate , outdate "  + indate +outdate +"相隔" + invDate);
//
//			if (invDate <=0 )
//			{
//				System.out.println("退房日期需大于入住日期");
//			}
			//1为订单预订未确认状态
			record.setStatus("1");
//			System.out.println("record = "+record.getpName());
			// 判断库存表的库存数是否满足客户需要
			//如果库存表没有值则将默认库存数插入库存表管理
			//获取时间跨度
//			String newOutdate = String.valueOf(Integer.parseInt(outdate));

//			for (int dateNum =0; dateNum < invDate -1 ;dateNum ++)
//			{
//				newOutdate = String.valueOf(Integer.parseInt(newOutdate) - 1);
//				BizInv inv = new BizInv();
//				inv.setRoomCode(record.getRoomCode());
//				inv.setInvDate(newOutdate);
//				List<BizInv> bizInvs = bizInvMapper.findById(inv);
//				if (bizInvs.size() ==0)
//				{
//					BizRoom mroom= bizRoomMapper.findById(record.getRoomCode());
//					if( mroom.getRoomStock() <= 0 || mroom.getRoomStock() ==null)
//					{
//						System.out.println("房间默认库存数不能为0或者空" +  mroom.getRoomStock());
//					}
//					System.out.println("默认库存数" + mroom.getRoomStock());
//					BizInv addInv = new BizInv();
//					addInv.setInvDate(newOutdate);
//					addInv.setRoomCode(record.getRoomCode());
//					addInv.setInventory(mroom.getRoomStock()-1);
//					addInv.setAutoClose("Y");
////					addInv.setCreatBy(record.getCreatBy());
////					addInv.setCreatTime(record.getCreatTime());
//					bizInvMapper.add(addInv);
//					//将默认库存数插入数据库，将库存数减一
//				}
//				else{
//					//k库存数大于1这可以减1
//					if(bizInvs.get(0).getInventory() >0)
//					{
//						System.out.println("库存数" + bizInvs.get(0).getInventory());
//						bizInvs.get(0).setInventory(bizInvs.get(0).getInventory()-1);
//						bizInvs.get(0).setInvDate(newOutdate);
//						bizInvMapper.updateByUser(bizInvs.get(0));
//					}
//				}
//			}

			bizPuchsMapper.insertSelective(record);
			BizPuchsExt recordExt = new BizPuchsExt();
//			recordExt.setCreatBy(record.getCreatBy());
//			recordExt.setCreatTime(record.getCreatTime());
			recordExt.setRoomCode(record.getRoomCode());
			recordExt.setOrderCode(record.getOrderCode());
			return  bizPuchsExtMapper.insertSelective(recordExt);
		}

		return bizPuchsMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateInfo(BizPuchsUpdate record) {
		System.out.println("订单更新开始"+ record);
		if(record.getOrderCode() == null || record.getOrderCode() == "0") {
		}
//return 0;
		return bizPuchsMapper.updateBizPushs(record);
	}

	@Override
	public int puchsConfirm(BizPuchsUpdate record) {
        if (record.getOrderCode() == null || record.getOrderCode() == "0") {
        }
        BizPuchs listStat = bizPuchsMapper.selectByPrimaryKey(record.getId());
        if ("2".equals(listStat.getStatus())) {
            throw new GlobalException("isOrderException");
        }
        //获取入住时间和退房时间
        if (record.getOutDate() == null || record.getInDate() == null) {
            throw new GlobalException("inoutdateIsNull");
        }

		Date outDate = DateUtils.getDate(record.getOutDate(), "yyyyMMdd");
		Date inDate = DateUtils.getDate(record.getInDate(), "yyyyMMdd");
		int invDate = DateUtils.getDateDiff(outDate, inDate);

		if (invDate <= 0) {
			throw new GlobalException("inDateExcepiton");
		}

        // 判断库存表的库存数是否满足客户需要
        //如果库存表没有值则将默认库存数插入库存表管理
        //获取时间跨度

        for (int index = 0; index < invDate ; index++) {
        	String newInDate = DateUtils.getDateString(DateUtils.addDays(inDate,index),"yyyyMMdd");
            BizInv inv = new BizInv();
            inv.setRoomCode(record.getRoomCode());
            inv.setInvDate(newInDate);
            BizInv bizInvs = bizInvService.findByRoomCode(inv);
            if (bizInvs == null) {
                BizRoom mroom = bizRoomMapper.findById(record.getRoomCode());
                if (mroom.getRoomStock() <= 0 || mroom.getRoomStock() == null) {
                    throw new GlobalException("RoomStockIsNull");
                }

                BizInv addInv = new BizInv();
                addInv.setInvDate(newInDate);
                addInv.setRoomCode(record.getRoomCode());
                addInv.setInventory(mroom.getRoomStock() - record.getRoomNum());
                addInv.setAutoClose("Y");
                bizInvService.addByUser(addInv);
                //将默认库存数插入数据库，将库存数减一
            } else {
                //k库存数大于1这可以减1
                if (bizInvs.getInventory() - record.getRoomNum() >= 0) {

                    bizInvs.setInventory(bizInvs.getInventory() - record.getRoomNum());
                    bizInvs.setInvDate(newInDate);
                    bizInvService.update(bizInvs);
                } else {
                    //提示库存数不够
                    throw new GlobalException("oraException");
                }
            }
        }

        record.setStatus("2");
        return bizPuchsMapper.puchsConfirm(record);
	}

	@Override
	public List<BizPuchsExtDto> findPage(BizPuchsQuery bizPuchsQuery) {
		return bizPuchsMapper.findPage(bizPuchsQuery);
	}

	@Override
	public int orderCancel(List<BizPuchsExtDto> bizPuchs) {
		for (BizPuchsExtDto bizPuch : bizPuchs) {
			cancel(bizPuch);
		}
		return 1;
	}

	@Override
	public void cancel(BizPuchsExtDto bizPuchsExtDto){
        BizPuchs bizPuchs = bizPuchsMapper.selectByPrimaryKey(bizPuchsExtDto.getId());
        if(bizPuchs == null){
            throw new GlobalException("isOrderException");
        }
        bizPuchs.setStatus("3");
		bizPuchsMapper.updateByPrimaryKeySelective(bizPuchs);
		List<BizInv> list = bizInvService.findCancelBizInv(bizPuchs);
		if(list.size() <= 0){
            throw new GlobalException("isOrderException");
        }
		for (BizInv bizInv : list) {
			bizInv.setInventory(bizInv.getInventory() + bizPuchs.getRoomNum());
			bizInvService.update(bizInv);
		}
	}


    @Override
    public int delete(BizPuchs record) {
        return bizPuchsMapper.deleteByPrimaryKey(record);
    }

    @Override
    public int delete(List<BizPuchs> records) {
        for(BizPuchs record:records) {
            bizPuchsMapper.updateByPrimaryKeySelective(record);
        }
        return 1;
    }

    @Override
    public BizPuchs findById(Long id) {
        return bizPuchsMapper.selectByPrimaryKey(id);
    }

}
