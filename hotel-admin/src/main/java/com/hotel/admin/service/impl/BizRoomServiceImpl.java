package com.hotel.admin.service.impl;

import com.hotel.admin.dto.BizProInv;
import com.hotel.admin.dto.BizRoomQuery;
import com.hotel.admin.dto.RecommendRoomQuery;
import com.hotel.admin.mapper.*;
import com.hotel.admin.model.*;
import com.hotel.admin.service.BizRoomService;
import com.hotel.admin.util.IdUtils;
import com.hotel.common.utils.DateUtils;
import com.hotel.common.utils.StringUtils;
import com.hotel.core.annotation.SystemServiceLog;
import com.hotel.core.context.PageContext;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.http.HttpStatus;
import com.hotel.core.page.Page;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ---------------------------
 * 客房信息表 (BizRoomServiceImpl)         
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

	@Autowired
	private BizPriseMapper bizPriseMapper;

	@Autowired
	private BizInvMapper bizInvMapper;

	@Autowired
	private BizPuchsMapper bizPuchsMapper;

	@Autowired
	private IdUtils idUtils;

	@Override
	@Transactional
	@SystemServiceLog(description = "客房信息维护保存（业务层）")
	public int save(BizRoom record) {
		/*
		检查是否推荐为空， 如果被用户选择，那就必须查询客房信息表里面是否存在推荐房源，如果有，就更新为普通，如果没有就不做操作
		 */
		if ("01".equals( record.getRecommended() )) {
			RecommendRoomQuery recommendRoomQuery = new RecommendRoomQuery();
			List<BizRoom> reLi =  bizRoomMapper.findByRecommend(recommendRoomQuery);
			try{
                if (reLi.size() > 0) {
                    for (int i = 0 ;i<reLi.size(); i++) {
                        BizRoom br = reLi.get(i);
                        BizRoom upBr = new BizRoom();
                        upBr.setRoomCode(br.getRoomCode());
                        upBr.setRecommended("02");
                        bizRoomMapper.update(upBr);
                    }
                }
            } catch (Exception e) {
			    a.error("更新推荐房源失败："+e.getMessage());
			    throw new GlobalException("oraException");
            }
		}
		/*
		新增及更新操作
		 */
		if(StringUtils.isBlank( record.getRoomCode() )  ) {
			System.out.println("进入新增"+record);
			//新增客房信息，
//			String roomCode = record.getHotelCode()+record.getRoomType();
			//根据客房类型+ 酒店编号+ crt_type=room查询编号，如果查询到就加1，查询不到就插入一条数据
//			CrtId auto = new CrtId();
//			auto.setCrtType("room");
//			auto.setTypeno(record.getHotelCode());
//			auto.setType(record.getRoomType());
//			CrtId ci =  crtIdMapper.findByRoomId(auto);
//			if (ci == null) {
//				auto.setCrtNo("0001");
//                int i = 0;
//				try {
//				   i = crtIdMapper.add(auto);
//                }catch (Exception e) {
//                    a.error("插入自增序列表失败："+e.getMessage());
//                    throw new GlobalException("oraException");
//                }
//				if (i==1) {
//					roomCode = roomCode+"0001";
//				} else {
//					throw new GlobalException("bizRoom");
//				}
//
//			} else {
//				String crtNo = ci.getCrtNo();
//				String ncrtNo = String.format("%04d",Integer.parseInt(crtNo)+1 );
//				auto.setCrtNo(ncrtNo);
//				int i = 0;
//				try {
//                    i = crtIdMapper.roomAutoAddUp(auto);
//                }catch (Exception e) {
//                    a.error("更新自增序列表失败："+e.getMessage());
//                    throw new GlobalException("oraException");
//                }
//				if(i==1) {
//					roomCode = roomCode + ncrtNo;
//				} else {
//					throw new GlobalException("bizRoom");
//				}
//			}
			String roomCode = idUtils.generateRoomCode(record);
			//插入BizRoom表
			record.setRoomCode(roomCode);
			int room = 0;
			try {
                room =  bizRoomMapper.add(record);
            }catch (Exception e) {
                a.error("插入客房信息失败："+e.getMessage());
                throw new GlobalException("oraException");
            }
			if(room !=1) {
				throw new GlobalException("bizRoom");
			}
			//插入BizRoomExt
			BizRoomExt bizRoomExt = getBizRoomExtObject(record,"add");
            int roomExt = 0;
            try{
                roomExt = bizRoomExtMapper.add(bizRoomExt);
            }catch (Exception e) {
                a.error("插入客房铺表信息失败："+e.getMessage());
                throw new GlobalException("oraException");
            }
			if (roomExt !=1 ) {
				throw new GlobalException("bizRoom");
			}
			return 1;
//			return bizRoomMapper.add(record);
		}
		System.out.println("进入了update");

		try {
            int room = bizRoomMapper.update(record);
            if (room !=1) {
                throw new GlobalException("bizRoom");
            }
            BizRoomExt bizRoomExt = getBizRoomExtObject(record,"update");
            int roomExt = bizRoomExtMapper.update(bizRoomExt);
            if (roomExt != 1) {
                throw new GlobalException("bizRoom");
            }
        }catch (Exception e) {
            a.error("更新客房信息表（铺信息）失败："+e.getMessage());
            throw new GlobalException("oraException");
        }
		return 1;
	}

	@Override
	@SystemServiceLog(description = "客房信息删除（业务层）")
	public int delete(BizRoom record) {
		return bizRoomMapper.delete(record);
	}

	@Override
	public int delete(List<BizRoom> records) {
		for(BizRoom record:records) {

			if (bizPuchsMapper.findByRoomCd(record.getRoomCode()).size() > 0) {
				throw new GlobalException("ExistsOrderException");
			}

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

	/*
	生成客户输入的牌价数据
	 */
	@Override
	@SystemServiceLog(description = "增减牌价信息（业务层）")
	public Map productDatePrice(BizProPrice bizProPrice) {
		Map bkMap = new HashMap();
		/*
		先查询是否已经有提交牌价信息（根据客房id查询）
		 */
		List<BizPrise> pli = bizPriseMapper.queryById(bizProPrice.getRoomCode());
		String  priceYear = bizProPrice.getPriceYear();
		String[] priceDateInterval = bizProPrice.getPriceDateInterval();
		if (StringUtils.isBlank(bizProPrice.getSprice())) {
			a.error("销售单人价格不能为空");
			throw new GlobalException("NotNullEception");
		}
		List<Map> list = new ArrayList<>();
		if (!StringUtils.isBlank(priceYear) ) {
			//直接生成一年的数据
			try {
				list = onceYearData(bizProPrice);
			} catch (ParseException e) {
				a.error("onceYearData Exception:"+e.getMessage());
				throw new GlobalException("sysException");
			}
		} else {
			if (priceDateInterval==null) {
				a.error("牌价年和范围时间不能同时为空");
				throw new GlobalException("NotNullEception");
			}
			//根据用户输入的范围生成数据
			try {
				list = dateIntervalData(bizProPrice);
			} catch (ParseException e) {
				a.error("dateIntervalData Exception:"+e.getMessage());
				new GlobalException("sysException");
			}
		}
		try {
			if ( pli.size() > 0 ) {
				list = mergeList(list,pli);
			}
			if (bizProPrice.getPriceDateData() != null) {
				if (bizProPrice.getPriceDateData().size() > 0) {
					list = mergeList(list,bizProPrice.getPriceDateData());
				}
			}
		} catch (Exception e) {
			a.error("合并牌价信息失败，mergeList："+e.getMessage());
			new GlobalException("sysException");
		}

		bkMap.put("list",list);
		bkMap.put("code" ,"0000");

		return bkMap;
	}
	/*
	组装牌价信息用于客户显示
	 */
	@Override
	@SystemServiceLog(description = "查询牌价信息（业务层）")
	public Map producePriceCalendar(BizProPrice bizProPrice) {
		System.out.println(bizProPrice);
		Map bkMap = new HashMap();
		if (StringUtils.isBlank(bizProPrice.getDate())) {
			a.error("当前牌价日期不能为空，系统错误");
			throw new GlobalException("NotNullEception");
		}
		System.out.println(bizProPrice);
		List<BizPrise> bpLi =  bizPriseMapper.queryById(bizProPrice.getRoomCode());
		if (bpLi.size() > 0) {
			System.out.println("组牌价信息查询到数据");
			if(bizProPrice.getDateArray() != null && bizProPrice.getDateArray().size()>0) {
				try {
					bizProPrice.setDateArray(mergeList(bizProPrice.getDateArray(),bpLi));
				}catch (Exception e) {
					a.error("组牌价信息异常producePriceCalendar:"+e.getMessage());
					throw new GlobalException("sysException");
				}

			} else {
				List<Map> tmLi = new ArrayList<Map>();
				for (int i = 0;i<bpLi.size();i++) {
					Map tmp = new HashMap();
					tmp.put("priceDate",bpLi.get(i).getPriceDate());
					tmp.put("sprice",bpLi.get(i).getSPrice());
					tmp.put("tprice",bpLi.get(i).getTPrice());
					tmLi.add(tmp);
				}
				bizProPrice.setDateArray(tmLi);
			}
		}
		/*
		根据前端传入的日期生成日历这一月的数据
		 */
		try {
			List<Map> list =  productThisWeekDate(bizProPrice);
			bkMap.put("code","0000");
			bkMap.put("list",list);
			return bkMap;
		} catch (ParseException e) {
			a.error("根据前端传入的日期生成日历一月的数据异常："+e.getMessage());
			throw new GlobalException("sysException");
		}
	}

	/*   库存信息维护   */
	/**
	 * 生成用户输入的库存信息
	 * @param bizProInv
	 * @return
	 */
	@Override
	@SystemServiceLog(description = "增减库存信息（业务层）")
	public Map productDateStock(BizProInv bizProInv) throws GlobalException{
		Map bkMap = new HashMap();
		/*
		先查询是否已经有提交牌价信息（根据客房id查询）
		 */
		List<BizInv> pli = bizInvMapper.queryById(bizProInv.getHotelCode());
		String priceYear = bizProInv.getStockYear();
		String[] priceDateInterval = bizProInv.getStockDateInterval();
		if (StringUtils.isBlank(bizProInv.getInventory())) {
			a.error("当前库存日期不能为空，系统错误");
			throw new GlobalException("NotNullEception");
		}
		List<Map> list = new ArrayList<>();
		if (!StringUtils.isBlank(priceYear)) {
			System.out.println(bizProInv);
			//直接生成一年的数据
			try {
				list = onceYearDataStock(bizProInv);
			} catch (ParseException e) {
				a.error("onceYearDataStock Exception:"+e.getMessage());
				new GlobalException("parse");
			}
		} else {
			if (priceDateInterval==null) {
				a.error("年数据和范围数据不能同时为空，系统错误");
				throw new GlobalException("NotNullEception");
			}
			//根据用户输入的范围生成数据
			try {
				list = dateIntervalDataStock(bizProInv);
			} catch (ParseException e) {
				a.error("dateIntervalDataStock Exception:"+e.getMessage());
				new GlobalException("parse");
			}
		}
		try {
			if ( pli.size() > 0 ) {
					list = mergeStockList(list,pli);
			}
			if (bizProInv.getStockDateData() != null) {
				if (bizProInv.getStockDateData().size() > 0) {
					list = mergeStockList(list,bizProInv.getStockDateData());
				}
			}
		}catch (Exception e) {
			a.error("合并库存信息失败，mergeStockList："+e.getMessage());
			new GlobalException("sysException");
		}
		bkMap.put("list",list);
		bkMap.put("code" ,"0000");

		return bkMap;
	}

	/*
	展示库存信息，合并客户生成的库存信息
	 */
	@Override
	@SystemServiceLog(description = "查询库存信息（业务层）")
	public Map produceStockCalendar(BizProInv bizProInv) {
		Map bkMap = new HashMap();
		if (StringUtils.isBlank(bizProInv.getDate())) {
			a.error("当前日期不能为空，系统错误");
			throw new GlobalException("NotNullEception");
		}
		List<BizInv> bpLi =  bizInvMapper.queryById(bizProInv.getHotelCode());
		if (bpLi.size() > 0) {
			if(bizProInv.getDateArray() != null && bizProInv.getDateArray().size()>0) {
				try {
					bizProInv.setDateArray(mergeStockList(bizProInv.getDateArray(),bpLi));
				}catch (Exception e) {
					a.error("合并数据库中查询到的数据异常："+e.getMessage());
					throw new GlobalException("sysException");
				}

			} else {
				List<Map> tmLi = new ArrayList<Map>();

				for (int i = 0;i<bpLi.size();i++) {
					Map tmp = new HashMap();
					tmp.put("invDate",bpLi.get(i).getInvDate());
					tmp.put("inventory",bpLi.get(i).getInventory());
					tmLi.add(tmp);
				}
				bizProInv.setDateArray(tmLi);
			}
		}
		/*
		根据前端传入的日期生成日历这一月的数据
		 */
		try {
			List<Map> list =  productStockThisWeekDate(bizProInv);
			bkMap.put("code","0000");
			bkMap.put("list",list);
			return bkMap;
		} catch (ParseException e) {
			a.error("根据前端传入的日期生成日历一月的库存数据异常："+e.getMessage());
			throw new GlobalException("sysException");
		}

	}


	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return null;
	}


	@Override
	@SystemServiceLog(description = "客房信息分页查询（业务层）")
	public Page findPagePara(BizRoomQuery bizRoomQuery) {

		List<Map> bizList = bizRoomMapper.findPageByPara(bizRoomQuery);
		return PageContext.getPage();
	}

	private BizRoomExt getBizRoomExtObject(BizRoom br,String str) {
		BizRoomExt bizRoomExt = new BizRoomExt();
		BeanUtils.copyProperties(br,bizRoomExt);
		return bizRoomExt;

	}

	/*
	生成一年的数据
	 */
	private List<Map> onceYearData(BizProPrice bizProPrice) throws ParseException {
		System.out.println("生成一年的牌价数据");

		String date = bizProPrice.getPriceYear();
		date = date.replace("Z"," UTC");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		Date priceYear = format.parse(date);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		List<Map> li = new ArrayList<Map>() ;

		System.out.println(sdf.format(priceYear));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(priceYear);
		//获取年
		int days = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
		System.out.println("days:"+days);
		for (int i = 0 ; i<days ; i++) {
			Map tmp = new HashMap();
			tmp.put("priceDate",sdf.format( calendar.getTime() ));
			tmp.put("sprice",bizProPrice.getSprice());
			tmp.put("tprice",bizProPrice.getTprice());
			li.add(tmp);

			calendar.add(calendar.DATE,1); //日期加1
		}
		System.out.println("返回数据的长度："+li.size());
		return li;
	}

	/*
	生成牌价范围数据，需要根据范围数据做筛选，选择出客户选择的周几，再插入到集合里面
	 */
	private List<Map> dateIntervalData(BizProPrice bizProPrice) throws ParseException {
		System.out.println("传入到日期范围的数据为："+bizProPrice);

		String[] dts = bizProPrice.getPriceDateInterval();
		Date[] dates = new Date[dts.length];
		for (int i = 0;i<dts.length;i++) {
			String date = dts[i];
			date = date.replace("Z"," UTC");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
			dates[i] = format.parse(date);
		}

		List<Map> list = new ArrayList<Map>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Calendar calendar = Calendar.getInstance();
		long days ;
		if (dates[1].getTime()>=dates[0].getTime()) {
			days = (Long)(dates[1].getTime()-dates[0].getTime())/(1000*3600*24);
			calendar.setTime(dates[0]);
		} else {
			days = (Long)(dates[0].getTime()-dates[1].getTime())/(1000*3600*24);
			calendar.setTime(dates[1]);
		}

		System.out.println("牌价日期范围数据为："+days);
		for (int i=0;i<=days;i++) {
			System.out.println("当前是星期几："+calendar.get(Calendar.DAY_OF_WEEK));
			Map tmp = new HashMap();
			if ("1".equals(bizProPrice.getIsMonday()) && 2 == calendar.get(Calendar.DAY_OF_WEEK) ) {
				tmp.put("priceDate",sdf.format(calendar.getTime()));
				tmp.put("sprice",bizProPrice.getSprice());
				tmp.put("tprice",bizProPrice.getTprice());
				list.add(tmp);
			}
			if ("1".equals(bizProPrice.getIsTuesday()) && 3 == calendar.get(Calendar.DAY_OF_WEEK) ) {
				tmp.put("priceDate",sdf.format(calendar.getTime()));
				tmp.put("sprice",bizProPrice.getSprice());
				tmp.put("tprice",bizProPrice.getTprice());
				list.add(tmp);

			}
			if ("1".equals(bizProPrice.getIsThursday()) && 4 == calendar.get(Calendar.DAY_OF_WEEK) ) {
				tmp.put("priceDate",sdf.format(calendar.getTime()));
				tmp.put("sprice",bizProPrice.getSprice());
				tmp.put("tprice",bizProPrice.getTprice());
				list.add(tmp);
			}
			if ("1".equals(bizProPrice.getIsFourday()) && 5 == calendar.get(Calendar.DAY_OF_WEEK) ) {
				tmp.put("priceDate",sdf.format(calendar.getTime()));
				tmp.put("sprice",bizProPrice.getSprice());
				tmp.put("tprice",bizProPrice.getTprice());
				list.add(tmp);
			}
			if ("1".equals(bizProPrice.getIsFriday()) && 6 == calendar.get(Calendar.DAY_OF_WEEK) ) {
				tmp.put("priceDate",sdf.format(calendar.getTime()));
				tmp.put("sprice",bizProPrice.getSprice());
				tmp.put("tprice",bizProPrice.getTprice());
				list.add(tmp);
			}
			if ("1".equals(bizProPrice.getIsSaterday()) && 7 == calendar.get(Calendar.DAY_OF_WEEK) ) {
				tmp.put("priceDate",sdf.format(calendar.getTime()));
				tmp.put("sprice",bizProPrice.getSprice());
				tmp.put("tprice",bizProPrice.getTprice());
				list.add(tmp);
			}
			if ("1".equals(bizProPrice.getIsSunday()) && 1 == calendar.get(Calendar.DAY_OF_WEEK) ) {
				tmp.put("priceDate",sdf.format(calendar.getTime()));
				tmp.put("sprice",bizProPrice.getSprice());
				tmp.put("tprice",bizProPrice.getTprice());
				list.add(tmp);
			}
			if (!"1".equals(bizProPrice.getIsSunday()) && !"1".equals(bizProPrice.getIsSaterday()) && !"1".equals(bizProPrice.getIsFriday()) &&
					!"1".equals(bizProPrice.getIsFourday())	&& !"1".equals(bizProPrice.getIsThursday()) && !"1".equals(bizProPrice.getIsTuesday())&&
					!"1".equals(bizProPrice.getIsMonday())) {
				tmp.put("priceDate",sdf.format(calendar.getTime()));
				tmp.put("sprice",bizProPrice.getSprice());
				tmp.put("tprice",bizProPrice.getTprice());
				list.add(tmp);
			}
			calendar.add(Calendar.DATE,1);
		}
		return list;
	}

	/*
	根据两个list查找两个日期相同的，根据用第一个list的sprice和tprice，替换第二个list的值合成一个list
	 */
	private List<Map> mergeList(List<Map> li,List<BizPrise> lv) {
		List<Map> result = new ArrayList<Map>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		result.addAll(li);
		int min = 0;
		int mark = 0;
		int mark2 = 0;
		int num = 0;
		for (int i = 0; i<lv.size() ; i++) {
			for (int j = 0;j<li.size();j++) {
				if ( Integer.parseInt( lv.get(i).getPriceDate()) < Integer.parseInt(  String.valueOf( li.get(0).get("priceDate"))) ) {

					Map map = new HashMap();
					map.put("priceDate",lv.get(i).getPriceDate());
					map.put("sprice",lv.get(i).getSPrice());
					map.put("tprice",lv.get(i).getTPrice());
					result.add(min,map);
					min++;
					break;

				}
				if (Integer.parseInt( lv.get(i).getPriceDate()) > Integer.parseInt( String.valueOf(li.get(j).get("priceDate"))) && j+1<li.size() &&
						Integer.parseInt( lv.get(i).getPriceDate() ) < Integer.parseInt( String.valueOf(li.get(j+1).get("priceDate")))) {

					Map map = new HashMap();
					map.put("priceDate",lv.get(i).getPriceDate());
					map.put("sprice",lv.get(i).getSPrice());
					map.put("tprice",lv.get(i).getTPrice());
					System.out.println("priceDate:"+lv.get(i).getPriceDate());
					System.out.println("插入签result长度："+result.size()+","+"插入的下标的值："+(j+1)+","+min+","+num);
					result.add(j+1+min+num ,map);
					System.out.println("插入之后的result长度："+result.size());

					num ++;
					break;
				}
				if ( Integer.parseInt( lv.get(i).getPriceDate()) > Integer.parseInt( String.valueOf(li.get(li.size()-1 ).get("priceDate"))) ) {

					Map map = new HashMap();
					map.put("priceDate",lv.get(i).getPriceDate());
					map.put("sprice",lv.get(i).getSPrice());
					map.put("tprice",lv.get(i).getTPrice());
					result.add(result.size(),map);
					break;
				}

			}
		}

		return result;

	}

	/*
	前端传入的日期是每月的1号，定位该日期是周几，java获取一周的第几天是0-6，	根据java的定位生成1号这周的数据
	 */
	private List<Map> productThisWeekDate(BizProPrice bizProPrice) throws ParseException {
		List<Map> list = new ArrayList<Map>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(bizProPrice.getDate());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int week = calendar.get(Calendar.DAY_OF_WEEK) -1;
		System.out.println(week);
		//获取本周的数据
		for (int i = week -1;i>=0 ; i--) {
			calendar.setTime(date);
			Map map = new HashMap();
			calendar.add(calendar.DATE , -i);
			if (bizProPrice.getDateArray() != null ) {
				if (bizProPrice.getDateArray().size() > 0) {
					getMatchingValue(map,calendar,bizProPrice.getDateArray());
				} else {
					map.put("sprice","");
					map.put("tprice","");
				}
			} else {
				map.put("sprice","");
				map.put("tprice","");
			}
			map.put("priceDate",calendar.getTime());
			list.add(map);
		}

		//获取其他周的数据
		for (int i = 1;i<= 35 - week;i++) {
			calendar.setTime(date);
			Map map = new HashMap();
			calendar.add(calendar.DATE, i);
			if (bizProPrice.getDateArray() != null ) {
				if (bizProPrice.getDateArray().size() > 0) {
					getMatchingValue(map,calendar,bizProPrice.getDateArray());

				} else {
					map.put("sprice","");
					map.put("tprice","");
				}
			} else {
				map.put("sprice","");
				map.put("tprice","");
			}
			map.put("priceDate",calendar.getTime());
			list.add(map);
		}


		return list;

	}

	/*
	二分法查找是否有相同日期的一个为日期字段，另一个为list集合查询是否有相同的值， 如果有就将list中的牌价信息赋值给map
	 */
	private Map getMatchingValue(Map map,Calendar calendar,List<Map> list) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = sdf.format(calendar.getTime());
		int date = Integer.parseInt(str);
		int start = 0;
		int end = list.size() -1;
		while (start <= end) {
			int middle = (start+end)/2;
			if(list.get(middle).get("priceDate") instanceof  String) {
				if (date <  Integer.parseInt( String.valueOf( list.get(middle).get("priceDate")) ) ) {
					end = middle - 1;
				} else if (date >  Integer.parseInt(String.valueOf(list.get(middle).get("priceDate")))) {
					start = middle + 1;
				} else {
					map.put("sprice",list.get(middle).get("sprice"));
					map.put("tprice",list.get(middle).get("tprice"));
					return map;
				}
			}
		}
		map.put("sprice","");
		map.put("tprice","");
		return map;

	}



	/* 库存私有方法以下*/
	/*
	生成一年的数据(库存)
	 */
	private List<Map> onceYearDataStock(BizProInv bizProInv) throws ParseException {
		String date = bizProInv.getStockYear();
		date = date.replace("Z"," UTC");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		Date priceYear = format.parse(date);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		List<Map> li = new ArrayList<Map>() ;
		System.out.println(sdf.format(priceYear));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(priceYear);
		//获取年
		int days = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
		System.out.println("days:"+days);
		for (int i = 0 ; i<days ; i++) {
			Map tmp = new HashMap();
			tmp.put("invDate",sdf.format( calendar.getTime() ));
			tmp.put("inventory",bizProInv.getInventory());
			li.add(tmp);

			calendar.add(calendar.DATE,1); //日期加1
		}
		return li;
	}
	/*
	生成库存范围数据，需要根据范围数据做筛选，选择出客户选择的周几，再插入到集合里面（库存）
	 */
	private List<Map> dateIntervalDataStock(BizProInv bizProInv) throws ParseException {
		System.out.println("传入到日期范围的数据为："+bizProInv);

		String[] dts = bizProInv.getStockDateInterval();
		Date[] dates = new Date[dts.length];
		for (int i = 0;i<dts.length;i++) {
			String date = dts[i];
			date = date.replace("Z"," UTC");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
			dates[i] = format.parse(date);
		}

		List<Map> list = new ArrayList<Map>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		long days ;
		if (dates[1].getTime()>=dates[0].getTime()) {
			days = (Long)(dates[1].getTime()-dates[0].getTime())/(1000*3600*24);
			calendar.setTime(dates[0]);
		} else {
			days = (Long)(dates[0].getTime()-dates[1].getTime())/(1000*3600*24);
			calendar.setTime(dates[1]);
		}

		System.out.println("库存日期范围数据为："+days);
		for (int i=0;i<=days;i++) {
			System.out.println("当前是星期几："+calendar.get(Calendar.DAY_OF_WEEK));
			Map tmp = new HashMap();
			if ("1".equals(bizProInv.getIsMonday()) && 2 == calendar.get(Calendar.DAY_OF_WEEK) ) {
				tmp.put("invDate",sdf.format(calendar.getTime()));
				tmp.put("inventory",bizProInv.getInventory());
				list.add(tmp);
			}
			if ("1".equals(bizProInv.getIsTuesday()) && 3 == calendar.get(Calendar.DAY_OF_WEEK) ) {
				tmp.put("invDate",sdf.format(calendar.getTime()));
				tmp.put("inventory",bizProInv.getInventory());
				list.add(tmp);

			}
			if ("1".equals(bizProInv.getIsThursday()) && 4 == calendar.get(Calendar.DAY_OF_WEEK) ) {
				tmp.put("invDate",sdf.format(calendar.getTime()));
				tmp.put("inventory",bizProInv.getInventory());
				list.add(tmp);
			}
			if ("1".equals(bizProInv.getIsFourday()) && 5 == calendar.get(Calendar.DAY_OF_WEEK) ) {
				tmp.put("invDate",sdf.format(calendar.getTime()));
				tmp.put("inventory",bizProInv.getInventory());
				list.add(tmp);
			}
			if ("1".equals(bizProInv.getIsFriday()) && 6 == calendar.get(Calendar.DAY_OF_WEEK) ) {
				tmp.put("invDate",sdf.format(calendar.getTime()));
				tmp.put("inventory",bizProInv.getInventory());
				list.add(tmp);
			}
			if ("1".equals(bizProInv.getIsSaterday()) && 7 == calendar.get(Calendar.DAY_OF_WEEK) ) {
				tmp.put("invDate",sdf.format(calendar.getTime()));
				tmp.put("inventory",bizProInv.getInventory());
				list.add(tmp);
			}
			if ("1".equals(bizProInv.getIsSunday()) && 1 == calendar.get(Calendar.DAY_OF_WEEK) ) {
				tmp.put("invDate",sdf.format(calendar.getTime()));
				tmp.put("inventory",bizProInv.getInventory());
				list.add(tmp);
			}
			if (!"1".equals(bizProInv.getIsSunday()) && !"1".equals(bizProInv.getIsSaterday()) && !"1".equals(bizProInv.getIsFriday()) &&
					!"1".equals(bizProInv.getIsFourday())	&& !"1".equals(bizProInv.getIsThursday()) && !"1".equals(bizProInv.getIsTuesday())&&
					!"1".equals(bizProInv.getIsMonday())) {
				tmp.put("invDate",sdf.format(calendar.getTime()));
				tmp.put("inventory",bizProInv.getInventory());
				list.add(tmp);
			}
			calendar.add(Calendar.DATE,1);
		}
		return list;
	}
	/*
	根据两个list查找两个日期相同的，根据用第一个list的sprice和tprice，替换第二个list的值合成一个list（库存）
	 */
	private List<Map> mergeStockList(List<Map> li,List<BizInv> lv) {
		List<Map> result = new ArrayList<Map>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		result.addAll(li);
		System.out.println("result前:"+result);
		System.out.println("lv:"+lv);
		int min = 0;
		int mark = 0;
		int mark2 = 0;
		int num = 0;
		for (int i = 0; i<lv.size() ; i++) {
			for (int j = 0;j<li.size();j++) {
				if ( Integer.parseInt( lv.get(i).getInvDate()) < Integer.parseInt(  String.valueOf( li.get(0).get("invDate"))) ) {

					Map map = new HashMap();
					map.put("invDate",lv.get(i).getInvDate());
					map.put("inventory",lv.get(i).getInventory());
					result.add(min,map);
					min++;
					break;

				}
				if (Integer.parseInt( lv.get(i).getInvDate()) > Integer.parseInt( String.valueOf(li.get(j).get("invDate"))) && j+1<li.size() &&
						Integer.parseInt( lv.get(i).getInvDate() ) < Integer.parseInt( String.valueOf(li.get(j+1).get("invDate")))) {

					Map map = new HashMap();
					map.put("invDate",lv.get(i).getInvDate());
					map.put("inventory",lv.get(i).getInventory());
					result.add(j+1+min+num ,map);

					num ++;
					break;
				}
				if ( Integer.parseInt( lv.get(i).getInvDate()) > Integer.parseInt( String.valueOf(li.get(li.size()-1 ).get("invDate"))) ) {

					Map map = new HashMap();
					map.put("invDate",lv.get(i).getInvDate());
					map.put("inventory",lv.get(i).getInventory());
					result.add(result.size(),map);
					break;
				}

			}
		}
		System.out.println("result:"+result);
		return result;

	}
	/*
	前端传入的日期是每月的1号，定位该日期是周几，java获取一周的第几天是0-6，	根据java的定位生成1号这周的数据(库存)
	 */
	private List<Map> productStockThisWeekDate(BizProInv bizProInv) throws ParseException {
		List<Map> list = new ArrayList<Map>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(bizProInv.getDate());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int week = calendar.get(Calendar.DAY_OF_WEEK) -1;
		System.out.println(week);
		//获取本周的数据
		for (int i = week -1;i>=0 ; i--) {
			calendar.setTime(date);
			Map map = new HashMap();
			calendar.add(calendar.DATE , -i);
			if (bizProInv.getDateArray() != null ) {
				if (bizProInv.getDateArray().size() > 0) {
					getMatchingStockValue(map,calendar,bizProInv.getDateArray());
				} else {
					map.put("inventory","");
				}
			} else {
				map.put("inventory","");
			}
			map.put("invDate",calendar.getTime());
			list.add(map);
		}

		//获取其他周的数据
		for (int i = 1;i<= 35 - week;i++) {
			calendar.setTime(date);
			Map map = new HashMap();
			calendar.add(calendar.DATE, i);
			if (bizProInv.getDateArray() != null ) {
				if (bizProInv.getDateArray().size() > 0) {
					getMatchingStockValue(map,calendar,bizProInv.getDateArray());

				} else {
					map.put("inventory","");
				}
			} else {
				map.put("inventory","");
			}
			map.put("invDate",calendar.getTime());
			list.add(map);
		}
		return list;

	}

    /*
    二分法查找是否有相同日期的一个为日期字段，另一个为list集合查询是否有相同的值， 如果有就将list中的牌价信息赋值给map (库存)
     */
    private Map getMatchingStockValue(Map map,Calendar calendar,List<Map> list) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String str = sdf.format(calendar.getTime());
        int date = Integer.parseInt(str);
        int start = 0;
        int end = list.size() -1;
        while (start <= end) {
            int middle = (start+end)/2;
            if(list.get(middle).get("invDate") instanceof  String) {
                if (date <  Integer.parseInt( String.valueOf( list.get(middle).get("invDate")) ) ) {
                    end = middle - 1;
                } else if (date >  Integer.parseInt(String.valueOf(list.get(middle).get("invDate")))) {
                    start = middle + 1;
                } else {
                    map.put("inventory",list.get(middle).get("inventory"));
                    return map;
                }
            }
        }
        map.put("inventory","");

        return map;
    }


}
