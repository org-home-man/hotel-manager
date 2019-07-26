package com.hotel.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hotel.admin.constants.Constant;
import com.hotel.admin.dto.*;
import com.hotel.admin.mapper.*;
import com.hotel.admin.model.*;
import com.hotel.admin.qo.BizPuchsQuery;
import com.hotel.admin.service.BizInvService;
import com.hotel.admin.service.BizPuchsService;
import com.hotel.admin.service.SysUserService;
import com.hotel.admin.util.IdUtils;
import com.hotel.admin.websocket.WebSocketServer;
import com.hotel.common.entity.auth.ISysUser;
import com.hotel.common.utils.DateUtils;
import com.hotel.common.utils.Utils;
import com.hotel.core.annotation.SystemServiceLog;
import com.hotel.core.context.PageContext;
import com.hotel.core.context.UserContext;
import com.hotel.core.exception.GlobalException;
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
 * ---------------------------
 * 订单信息表 (BizPuchsServiceImpl)
 * ---------------------------
 */
@Service
public class BizPuchsServiceImpl extends AbstractService<BizPuchs> implements BizPuchsService {

    private Logger LOGGER = LoggerFactory.getLogger(BizPuchsServiceImpl.class);

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
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private BizPriseMapper bizPriseMapper;
    @Autowired
    private IdUtils idUtils;

    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    @Transactional
    @SystemServiceLog(description = "订单信息保存（业务层）")
    public int save(BizPuchs record) {
        //根据入住日期和退房日期 生成订单详细信息；详细信息需要订单的 每日的房间价格
        long days = 0;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (Utils.isEmpty(record.getInDateStart()) || Utils.isEmpty(record.getOutDateEnd())) {
            return 0;
        } else {
            try {
                validDateInDateStart(record.getInDateStart(),getSystemParams());
            }catch (Exception e) {
                new GlobalException(e.getMessage());
            }

            try {
                Date startDate = sdf.parse(record.getInDateStart());
                calendar.setTime(startDate);
                Date endDate = sdf.parse(record.getOutDateEnd());
                days = (Long)(endDate.getTime()-startDate.getTime())/(1000*3600*24);
            } catch (ParseException e) {
                new GlobalException("sysException");
            }
        }
        //根据入离日期查询出相应的牌价信息
        HotelRoomQry hotelRoomQry = new HotelRoomQry();
        hotelRoomQry.setRoomCode(record.getRoomCode());
        hotelRoomQry.setInDateStart(record.getInDateStart());
        hotelRoomQry.setOutDateEnd(record.getOutDateEnd());
        List<BizPrise> priceList = bizPriseMapper.findByDate(hotelRoomQry);

        if (record.getOrderCode() == null || record.getOrderCode() == "0") {

            //获取用户id
            record.setCreateId(UserContext.getCurrentUser().getId());
            String orderCode = idUtils.generateOrderCode(record);
            record.setOrderCode(orderCode);
            record.setStatus(Constant.PUCHS_STAT_NO_CONFIRM);
            bizPuchsMapper.insertSelective(record);

            for (int i = 0 ; i < days ; i++) {

                BizPuchsExt recordExt = new BizPuchsExt();
                recordExt.setRoomCode(record.getRoomCode());
                recordExt.setOrderCode(orderCode);
                recordExt.setLiveDate(sdf.format(calendar.getTime()));
                recordExt.setSAmount(priceList.get(i).getSRoomPrice()*record.getRoomNum());
                recordExt.setTAmount(priceList.get(i).getTPrice()*(record.getAdultNum()+record.getChildNum()));
                bizPuchsExtMapper.insertSelective(recordExt);

                calendar.add(Calendar.DATE,1);
            }

            WebSocketServer.sendMessageToManager("订单号:"+record.getOrderCode(),Constant.ORDER_MES);

            return 1;
        }

        bizPuchsMapper.updateByPrimaryKey(record);

        for (int i = 0 ; i < days ; i++) {

            BizPuchsExt recordExt = new BizPuchsExt();
            recordExt.setRoomCode(record.getRoomCode());
            recordExt.setOrderCode(record.getOrderCode());
            recordExt.setLiveDate(sdf.format(calendar.getTime()));
            recordExt.setSAmount(priceList.get(i).getSRoomPrice()*record.getRoomNum());
//            recordExt.setTAmount(priceList.get(i).getTPrice()*(record.getAdultNum()+record.getChildNum()));
            bizPuchsExtMapper.updateByUnique(recordExt);
            calendar.add(Calendar.DATE,1);
        }
        return 1;
    }

	@Override
    @SystemServiceLog(description = "订单信息更新（业务层）")
	public int updateInfo(BizPuchsUpdate record) {
		if(record.getOrderCode() == null || record.getOrderCode() == "0") {
		}
        //判断库存数是否够用
        Date outDate = DateUtils.getDate(record.getoutDateEnd(), "yyyyMMdd");
        Date inDate = DateUtils.getDate(record.getinDateStart(), "yyyyMMdd");
        int invDate = DateUtils.getDateDiff(outDate, inDate);

        for (int index = 0; index < invDate; index++) {
            String newInDate = DateUtils.getDateString(DateUtils.addDays(inDate, index), "yyyyMMdd");
            BizInv inv = new BizInv();
            inv.setRoomCode(record.getRoomCode());
            inv.setInvDate(newInDate);
            BizInv bizInvs = bizInvService.findByRoomCode(inv);
            if (bizInvs == null) {
                BizRoom mroom = bizRoomMapper.findById(record.getRoomCode());
                if (mroom.getRoomStock() <= 0 || mroom.getRoomStock() == null) {
                    throw new GlobalException("RoomStockIsNull");
                }
                if(mroom.getRoomStock() < record.getRoomNum() )
                {
                    throw new GlobalException("invIsOutException");
                }

            } else {
                //k库存数大于1这可以减1
                if (bizInvs.getInventory() < record.getRoomNum() ) {
                    throw new GlobalException("invIsOutException");
                }
            }
        }

        return bizPuchsMapper.updateBizPushs(record);
    }

    @Override
    @SystemServiceLog(description = "订单信息提交（业务层）")
    public int puchsConfirm(BizPuchsUpdate record) {
        if (record.getOrderCode() == null || record.getOrderCode() == "0") {
        }
//        BizPuchs listStat = bizPuchsMapper.selectByPrimaryKey(record.getId());
//        if ("2".equals(listStat.getStatus())) {
//            throw new GlobalException("isOrderException");
//        }
        System.out.println(record.getoutDateEnd()+ record.getinDateStart() );
        //获取入住时间和退房时间
        if (record.getoutDateEnd() == null || record.getoutDateEnd() == "" || record.getinDateStart() == null || record.getinDateStart() == "") {
            throw new GlobalException("inoutdateIsNull");
        }

        Date outDate = DateUtils.getDate(record.getoutDateEnd(), "yyyyMMdd");
        Date inDate = DateUtils.getDate(record.getinDateStart(), "yyyyMMdd");
        int invDate = DateUtils.getDateDiff(outDate, inDate);

        if (invDate <= 0) {
            throw new GlobalException("inDateExcepiton");
        }

//System.out.println(inDate +"  " + new Date() );
//		if(new Date().after(inDate)){
//			throw new GlobalException("isinDateOutException");
//		}

        // 判断库存表的库存数是否满足客户需要
        //如果库存表没有值则将默认库存数插入库存表管理
        //获取时间跨度

        for (int index = 0; index < invDate; index++) {
            String newInDate = DateUtils.getDateString(DateUtils.addDays(inDate, index), "yyyyMMdd");
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
                    throw new GlobalException("invIsOutException");
                }
            }
        }

        record.setStatus(Constant.PUCHS_STAT_CONFIRM);
        record.setLastCrtTime(DateUtils.getDateString(new Date(),"yyyyMMdd"));
        record.setConfirmName(UserContext.getCurrentUser().getName());
        return bizPuchsMapper.puchsConfirm(record);
    }

    @Override
    @SystemServiceLog(description = "订单信息查询（业务层）")
    public List<BizPuchsExtDto> findPage(BizPuchsQuery bizPuchsQuery) {
        PageContext.setPagination(false);
//        String username = UserContext.getCurrentUser().getName();
//        SysUser sysUser = sysUserService.findByName(username);
        ISysUser currentUser = UserContext.getCurrentUser();
        List<SysUserRole> userRoles = sysUserRoleMapper.findUserRoles(currentUser.getId());
        for (SysUserRole userRole : userRoles) {
            SysRole role = new SysRole();
            role.setId(userRole.getRoleId());

            role = sysRoleMapper.selectByPrimaryKey(role);
            if(Constant.NO_MANAGER_ROLE.equals(role.getIsManager())){
                bizPuchsQuery.setCreateName(currentUser.getName());
                break;
            }
        }
        PageContext.setPagination(true);
        return bizPuchsMapper.findPage(bizPuchsQuery);

    }

    @Override
    @SystemServiceLog(description = "订单取消（业务层）")
    public int orderCancel(List<BizPuchsExtDto> bizPuchs) {
        for (BizPuchsExtDto bizPuch : bizPuchs) {
            cancel(bizPuch);
        }
        return 1;
    }

    @Override
    public void cancel(BizPuchsExtDto bizPuchsExtDto) {
        BizPuchs bizPuchs = bizPuchsMapper.selectByPrimaryKey(bizPuchsExtDto.getId());
        if (bizPuchs == null) {
            throw new GlobalException("isOrderException");
        }
        if("2".equals(bizPuchs.getStatus())){
            List<BizInv> list = bizInvService.findCancelBizInv(bizPuchs);
            if (list.size() <= 0) {
                throw new GlobalException("isOrderException");
            }
            for (BizInv bizInv : list) {
                bizInv.setInventory(bizInv.getInventory() + bizPuchs.getRoomNum());
                bizInvService.update(bizInv);
            }
        }
        bizPuchs.setStatus(Constant.PUCHS_STAT_CANCEL);
        bizPuchsMapper.updateByPrimaryKeySelective(bizPuchs);
    }

    @Override
    @SystemServiceLog(description = "订单结算（业务层）")
    public int accountsConfirm(BizPuchsUpdate record) {
        if (!Constant.PUCHS_STAT_NO_ACCOUNTS.equals( record.getStatus())) {
            throw new GlobalException("OrderStatusNomatchException");
        }
        record.setStatus(Constant.PUCHS_STAT_ACCOUNTS);
        return bizPuchsMapper.puchsConfirm(record);
    }

    @Override
    @SystemServiceLog(description = "用户订单信息导出（业务层）")
    public List<UserRequestReportDto> exportExcel(BizPuchsUpdate record) {
        List<UserRequestReportDto> li = bizPuchsMapper.userRequestReport(record);
        return li;
    }

    @Override
    @SystemServiceLog(description = "管理员订单信息导出（业务层）")
    public List<ManagerRequestReportDto> exportManagerExcel(BizPuchsUpdate record) {
        List<ManagerRequestReportDto> li = bizPuchsMapper.managerRequestReport(record);
        return li;
    }



    /*
    日期校验每晚18.30之后的就无法预定7天内的房间
     */
    private void validDateInDateStart(String inDateStart,int systemDays) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String dateString = sf.format(date);
        try {
            Date eighteenDate = sdf.parse(dateString+"183000");
            if (date.after(eighteenDate)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DAY_OF_MONTH,systemDays);

                Date sysDate = calendar.getTime();
                if (sf.parse(inDateStart).before(sysDate)) {
                    throw new GlobalException("OverSevenAfterExcetpion");
                }

            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DAY_OF_MONTH,systemDays-1);
                Date sysDate = calendar.getTime();
                if (sf.parse(inDateStart).before(sysDate)) {
                    throw new GlobalException("OverSevenAfterExcetpion");
                }
            }

        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    /*
    获取系统参数
     */
    private int getSystemParams() {
        List<SysDictDto> dict = sysDictMapper.findByCode("SYSTEM_DAYS","1");
        if (dict.size() < 1) {
            throw new GlobalException("sysExcetpion");
        }

        try{
            return Integer.parseInt(dict.get(0).getCode());
        }catch (Exception e) {
            throw new GlobalException("sysExcetpion");
        }

    }
}
