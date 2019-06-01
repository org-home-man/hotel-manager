package com.hotel.admin.service.impl;

import com.hotel.admin.constants.Constant;
import com.hotel.admin.mapper.*;
import com.hotel.admin.model.*;
import com.hotel.admin.qo.BizPuchsQuery;
import com.hotel.admin.service.BizInvService;
import com.hotel.admin.service.BizPuchsService;
import com.hotel.admin.service.SysUserService;
import com.hotel.admin.websocket.WebSocketServer;
import com.hotel.common.utils.DateUtils;
import com.hotel.common.utils.Utils;
import com.hotel.core.context.PageContext;
import com.hotel.core.context.UserContext;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public int save(BizPuchs record) {
        if (Utils.isEmpty(record.getInDateStart()) || Utils.isEmpty(record.getOutDateEnd())) {
            return 0;
        }
        if (record.getOrderCode() == null || record.getOrderCode() == "0") {
            CrtId crt = crtIdMapper.findById("puchs");

            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");//可以方便地修改日期格式
            String timeNow = dateFormat.format(now);

            //获取用户id
            String userName = UserContext.getCurrentUser().getName();
            if (Utils.isEmpty(userName)) {
                return 0;
            }
            String id = sysUser.findByName(userName).getDeptId() + "";
            if (Utils.isEmpty(crt)) {
                CrtId ncrt = new CrtId();
                ncrt.setCrtNo("0001");
                ncrt.setCrtType(Constant.ORDER_TYPE);
                crtIdMapper.add(ncrt);
                record.setOrderCode(id + timeNow + Constant.ORDER_FIRST_CODE);
            } else {
                String crtno = crt.getCrtNo();
                String newCrt = String.valueOf(Integer.parseInt(crtno) + 1);
                while (newCrt.length() < 4) {
                    newCrt = "0" + newCrt;
                }
                System.out.println(newCrt);
                crt.setCrtNo(newCrt);
                crtIdMapper.update(crt);
                record.setOrderCode(id + timeNow + newCrt);
            }
            record.setStatus(Constant.BOOL_NO);
            bizPuchsMapper.insertSelective(record);
            BizPuchsExt recordExt = new BizPuchsExt();
            recordExt.setRoomCode(record.getRoomCode());
            recordExt.setOrderCode(record.getOrderCode());
            WebSocketServer.sendMessage("订单号:"+record.getOrderCode());
            return bizPuchsExtMapper.insertSelective(recordExt);
        }

        return bizPuchsMapper.updateByPrimaryKey(record);
    }

	@Override
	public int updateInfo(BizPuchsUpdate record) {
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
        System.out.println(record.getoutDateEnd()+ record.getinDateStart() );
        //获取入住时间和退房时间
        if (record.getoutDateEnd() == null || record.getinDateStart() == null) {
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

        record.setStatus("2");
        record.setConfirmTime(DateUtils.getDateString(new Date(),"yyyyMMdd"));
        return bizPuchsMapper.puchsConfirm(record);
    }

    @Override
    public List<BizPuchsExtDto> findPage(BizPuchsQuery bizPuchsQuery) {
        PageContext.setPagination(false);
        String username = UserContext.getCurrentUser().getName();
        SysUser sysUser = sysUserService.findByName(username);
        List<SysUserRole> userRoles = sysUserRoleMapper.findUserRoles(sysUser.getId());
        for (SysUserRole userRole : userRoles) {
            SysRole role = new SysRole();
            role.setId(userRole.getRoleId());

            role = sysRoleMapper.selectByPrimaryKey(role);
            if(Constant.NO_MANAGER_ROLE.equals(role.getIsManager())){
                bizPuchsQuery.setCreateName(username);
                break;
            }
        }
        PageContext.setPagination(true);
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
    public void cancel(BizPuchsExtDto bizPuchsExtDto) {
        BizPuchs bizPuchs = bizPuchsMapper.selectByPrimaryKey(bizPuchsExtDto.getId());
        if (bizPuchs == null) {
            throw new GlobalException("isOrderException");
        }
        bizPuchs.setStatus("3");
        bizPuchsMapper.updateByPrimaryKeySelective(bizPuchs);
        List<BizInv> list = bizInvService.findCancelBizInv(bizPuchs);
        if (list.size() <= 0) {
            throw new GlobalException("isOrderException");
        }
        for (BizInv bizInv : list) {
            bizInv.setInventory(bizInv.getInventory() + bizPuchs.getRoomNum());
            bizInvService.update(bizInv);
        }
    }


}
