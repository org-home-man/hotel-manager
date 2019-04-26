package com.hotel.admin.service.impl;

import com.hotel.admin.dto.BizRoomQuery;
import com.hotel.admin.mapper.BizPriseMapper;
import com.hotel.admin.mapper.BizRoomExtMapper;
import com.hotel.admin.mapper.BizRoomMapper;
import com.hotel.admin.mapper.CrtIdMapper;
import com.hotel.admin.model.*;
import com.hotel.admin.service.BizRoomService;
import com.hotel.common.utils.StringUtils;
import com.hotel.core.context.PageContext;
import com.hotel.core.page.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
 * 作者：  kitty-generator
 * 时间：  2019-04-01 21:00:17
 * 说明：  我是由代码生成器生生成的
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

	@Override
	@Transactional
	public int save(BizRoom record) {

		if(StringUtils.isBlank( record.getRoomCode() )  ) {
			System.out.println("进入了新增");
			//新增客房信息，
			String roomCode = record.getHotelCode()+record.getRoomType();
			//根据客房类型+ 酒店编号+ crt_type=room查询编号，如果查询到就加1，查询不到就插入一条数据
			CrtId auto = new CrtId();
			auto.setCrtType("room");
			auto.setTypeno(record.getHotelCode());
			auto.setType(record.getRoomType());
			CrtId ci =  crtIdMapper.findByRoomId(auto);
			if (ci == null) {
				auto.setCrtNo("0001");
				int i = crtIdMapper.add(auto);
				if (i==1) {
					roomCode = roomCode+"0001";
				} else {
					throw new RuntimeException("bizRoom");
				}

			} else {
				String crtNo = ci.getCrtNo();
				String ncrtNo = String.format("%04d",Integer.parseInt(crtNo)+1 );
				auto.setCrtNo(ncrtNo);
				int i = crtIdMapper.roomAutoAddUp(auto);
				if(i==1) {
					roomCode = roomCode + ncrtNo;
				} else {
					throw new RuntimeException("bizRoom");
				}
			}
			//插入BizRoom表
			record.setRoomCode(roomCode);
			record.setCreatTime(new Date());
			int room =  bizRoomMapper.add(record);
			if(room !=1) {
				throw new RuntimeException("bizRoom");
			}
			//插入BizRoomExt
			BizRoomExt bizRoomExt = getBizRoomExtObject(record,"add");
			int roomExt = bizRoomExtMapper.add(bizRoomExt);
			if (roomExt !=1 ) {
				throw new RuntimeException("bizRoom");
			}
			return 1;
//			return bizRoomMapper.add(record);
		}
		System.out.println("进入了update");
		record.setLastUpdateTime(new Date());

		int room = bizRoomMapper.update(record);
		if (room !=1) {
			throw new RuntimeException("bizRoom");
		}
		BizRoomExt bizRoomExt = getBizRoomExtObject(record,"update");
		int roomExt = bizRoomExtMapper.update(bizRoomExt);
		if (roomExt != 1) {
			throw new RuntimeException("bizRoom");
		}
		return 1;
	}

	@Override
	public int delete(BizRoom record) {
		return bizRoomMapper.delete(record.getRoomCode());
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
		return null;
	}

	public BizRoom findById(String id) {
		return bizRoomMapper.findById(id);
	}

	/*
	生成客户输入的牌价数据
	 */
	@Override
	public Map productDatePrice(BizProPrice bizProPrice) {
		Map bkMap = new HashMap();
		/*
		先查询是否已经有提交牌价信息（根据客房id查询）
		 */
		List<BizPrise> pli = bizPriseMapper.queryById(bizProPrice.getRoomCode());
		Date  priceYear = bizProPrice.getPriceYear();
		Date[] priceDateInterval = bizProPrice.getPriceDateInterval();
		if (StringUtils.isBlank(bizProPrice.getSprice())) {
			bkMap.put("code" ,"");
			return bkMap;
		}
		List<Map> list = new ArrayList<>();
		if (priceYear != null) {
			System.out.println(bizProPrice);
			//直接生成一年的数据
			list = onceYearData(bizProPrice);
		} else {
			if (priceDateInterval==null) {
				bkMap.put("code","");
				return bkMap;
			}
			//根据用户输入的范围生成数据
			list = dateIntervalData(bizProPrice);
			System.out.println("范围生成数据："+list.toString());
		}
		if ( pli.size() > 0 ) {
			System.out.println("pli大于0");
			System.out.println("pli数据库的数据为："+pli.toString());
			list = mergeList(list,pli);
			System.out.println("合并数据库中的数据："+list.toString());
		}
		if (bizProPrice.getPriceDateData() != null) {
			if (bizProPrice.getPriceDateData().size() > 0) {
				System.out.println("priceDateData大于0");
				for (int i = 0;i<bizProPrice.getPriceDateData().size();i++) {
					System.out.println("前台缓存的数据:"+i+",,"+bizProPrice.getPriceDateData().get(i).toString());
				}
				list = mergeList(list,bizProPrice.getPriceDateData());
				System.out.println("合并前台缓存数据组："+list.toString());
			}
		}
		bkMap.put("list",list);
		bkMap.put("code" ,"0000");

		return bkMap;
	}
	/*
	组装牌价信息用于客户显示
	 */
	@Override
	public Map producePriceCalendar(BizProPrice bizProPrice) {
		Map bkMap = new HashMap();
		if (StringUtils.isBlank(bizProPrice.getDate())) {
			bkMap.put("code","0001");
			return bkMap;
		}
		System.out.println(bizProPrice);
		List<BizPrise> bpLi =  bizPriseMapper.queryById(bizProPrice.getRoomCode());
		if (bpLi.size() > 0) {
			System.out.println("组牌价信息查询到数据");
			if(bizProPrice.getDateArray() != null && bizProPrice.getDateArray().size()>0) {
				bizProPrice.setDateArray(mergeList(bizProPrice.getDateArray(),bpLi));
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
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public PageResult findPage(PageRequest pageRequest) {
		Map<String,Object> map = new HashMap<String ,Object>();
		Map<String,ColumnFilter> temp = pageRequest.getColumnFilters();
		for (Map.Entry<String,ColumnFilter> entry : temp.entrySet() ) {
			ColumnFilter columnFilter = entry.getValue();
			if (!StringUtils.isBlank(columnFilter.getValue())) {
				map.put(columnFilter.getName(),columnFilter.getValue());
			}
		}
		PageResult pr =  MybatisPageHelper.findPage(pageRequest, bizRoomMapper,"findPageByPara",map);
//		List<Map> bizList = bizRoomMapper.findPageByPara(map);
		return pr;
	}

	@Override
	public Page findPagePara(BizRoomQuery bizRoomQuery) {
//		Map<String,ColumnFilter> temp = pageRequest.getColumnFilters();
//		for (Map.Entry<String,ColumnFilter> entry : temp.entrySet() ) {
//			ColumnFilter columnFilter = entry.getValue();
//			if (!StringUtils.isBlank(columnFilter.getValue())) {
//				map.put(columnFilter.getName(),columnFilter.getValue());
//			}
//		}
//		PageResult pr =  MybatisPageHelper.findPage(pageRequest, bizRoomMapper,"findPageByPara",map);
		List<Map> bizList = bizRoomMapper.findPageByPara(bizRoomQuery);
		System.out.println(bizList);
		return PageContext.getPage();
	}

	private BizRoomExt getBizRoomExtObject(BizRoom br,String str) {
		BizRoomExt bizRoomExt = new BizRoomExt();
		bizRoomExt.setIsbalcony(br.getIsbalcony());
		bizRoomExt.setIsbarrifr(br.getIsbarrifr());
		bizRoomExt.setIsbeach(br.getIsbeach());
		bizRoomExt.setIsbus(br.getIsbus());
		bizRoomExt.setIschildct(br.getIschildct());
		bizRoomExt.setIsfront(br.getIsfront());
		bizRoomExt.setIsgym(br.getIsgym());
		bizRoomExt.setIsheat(br.getIsheat());
		bizRoomExt.setIshighrise(br.getIshighrise());
		bizRoomExt.setIshotsp(br.getIshotsp());
		bizRoomExt.setIsicebox(br.getIsicebox());
		bizRoomExt.setIsiron(br.getIsiron());
		bizRoomExt.setIskitchen(br.getIskitchen());
		bizRoomExt.setIsknead(br.getIsknead());
		bizRoomExt.setIslandscape(br.getIslandscape());
		bizRoomExt.setIslounge(br.getIslounge());
		bizRoomExt.setIsnosmk(br.getIsnosmk());
		bizRoomExt.setIspark(br.getIspark());
		bizRoomExt.setIsrestau(br.getIsrestau());
		bizRoomExt.setIsroomserv(br.getIsroomserv());
		bizRoomExt.setIssuper(br.getIssuper());
		bizRoomExt.setIsswmp(br.getIsswmp());
		bizRoomExt.setIstrafic(br.getIstrafic());
		bizRoomExt.setIswify(br.getIswify());
		bizRoomExt.setIswindow(br.getIswindow());
		bizRoomExt.setRoomCode(br.getRoomCode());
		if ("add".equals(str)){
			bizRoomExt.setCreatTime(new Date());
			bizRoomExt.setCreatBy(br.getCreatBy());
		} else if ("update".equals(str)){
			bizRoomExt.setLastUpdateBy(br.getLastUpdateBy());
			bizRoomExt.setLastUpdateTime(new Date());
		}
		return bizRoomExt;

	}

	/*
	生成一年的数据
	 */
	private List<Map> onceYearData(BizProPrice bizProPrice) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		List<Map> li = new ArrayList<Map>() ;
		Date priceYear = bizProPrice.getPriceYear();
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
		return li;
	}
	/*
	生成牌价范围数据，需要根据范围数据做筛选，选择出客户选择的周几，再插入到集合里面
	 */
	private List<Map> dateIntervalData(BizProPrice bizProPrice) {
		System.out.println("传入到日期范围的数据为："+bizProPrice);
		List<Map> list = new ArrayList<Map>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date[] dates = bizProPrice.getPriceDateInterval();
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
}
