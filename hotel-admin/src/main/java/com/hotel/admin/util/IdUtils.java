package com.hotel.admin.util;

import com.hotel.admin.mapper.BizHotlMapper;
import com.hotel.admin.mapper.BizPuchsMapper;
import com.hotel.admin.mapper.BizRoomMapper;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.model.BizPuchs;
import com.hotel.admin.model.BizRoom;
import com.hotel.admin.redis.template.RedisCacheTemplate;
import com.hotel.common.utils.Utils;
import com.hotel.core.exception.GlobalException;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @ProjectName: hotel-admin
 * @ClassName: IdUtils
 * @Author: cc
 * @Description: 酒店和客房id生成器
 * @Date: 2019-06-02 1:45
 * @Version: 1.0
 */
@Component
public class IdUtils {

    @Autowired
    private BizHotlMapper bizHotlMapper;
    @Autowired
    private BizRoomMapper bizRoomMapper;
    @Autowired
    private BizPuchsMapper bizPuchsMapper;

    public static final String HOTEL_CODE = "hotel:hotelCode";
    public static final String ROOM_CODE = "hotel:roomCode";
    public static final String ORDER_CODE = "hotel:orderCode";

    @Autowired
    private RedisCacheTemplate redisCacheTemplate;

    @PostConstruct
    private void initSequence(){
        //酒店流水同步
        String hotelSeq = bizHotlMapper.selectMaxSequence();
        String s = redisCacheTemplate.get(HOTEL_CODE);
        if(null == s){
            redisCacheTemplate.set(HOTEL_CODE,String.valueOf(Integer.valueOf(hotelSeq)));
        }
        //客房流水同步
        String roomSeq = bizRoomMapper.selectMaxSequence();
        String s1 = redisCacheTemplate.get(ROOM_CODE);
        if(s1 == null){
            redisCacheTemplate.set(ROOM_CODE, String.valueOf(Integer.valueOf(roomSeq)));
        }
        //订单流水同步
        String orderSeq = bizPuchsMapper.selectMaxSequence();
        String s2 = redisCacheTemplate.get(ORDER_CODE);
        if(s2 == null){
            redisCacheTemplate.set(ORDER_CODE,String.valueOf(Integer.valueOf(orderSeq)));
        }
    }

    /**
     * 根据酒店所在区域和序号生成编码 3位县 2位市 5位流水
     * @param bizHotl
     * @return
     */
    public String generateHotelCode(BizHotl bizHotl){
        String provinceCode = bizHotl.getProvinceCode();
        String cityCode = bizHotl.getCityCode();
        if(Utils.isEmpty(provinceCode) || Utils.isEmpty(cityCode)){
            throw new GlobalException("noProvince");
        }
        //根据酒店编码生成
        long number = returnCode(HOTEL_CODE);
        String priCode = new DecimalFormat("000").format(Long.parseLong(provinceCode));
        String ctCode = new DecimalFormat("00").format(Long.parseLong(cityCode));
        String numCode = new DecimalFormat("00000").format(number);
        StringBuilder sb = new StringBuilder();
        sb.append(priCode).append(ctCode).append(numCode);
        return sb.toString();
    }

    /**
     * 根据10位酒店编号 + 2位客房类型 + 4位流水号
     * @param bizRoom
     * @return
     */
    public String generateRoomCode(BizRoom bizRoom){
        String hotelCode = bizRoom.getHotelCode();
        String roomType = bizRoom.getRoomType();
        if(Utils.isEmpty(hotelCode) || Utils.isEmpty(roomType)){
            throw new GlobalException("noProvince");
        }
        //根据酒店编码生成
        long number = returnCode(ROOM_CODE);
        String typeCode = new DecimalFormat("00").format(Long.parseLong(roomType));
        String numCode = new DecimalFormat("0000").format(number);
        StringBuilder code = new StringBuilder();
        code.append(hotelCode).append(typeCode).append(numCode);
        return code.toString();
    }

    /**
     * 根据订单创建人id4 + 当前时间 + 4流水号生成
     * @param bizPuchs
     * @return
     */
    public String generateOrderCode(BizPuchs bizPuchs){
        Long createId = bizPuchs.getCreateId();
        if(Utils.isEmpty(createId)){
            throw new GlobalException("noProvince");
        }
        //根据酒店编码生成
        long number = returnCode(ORDER_CODE);
        String idCode = new DecimalFormat("0000").format(createId);
        String numCode = new DecimalFormat("0000").format(number);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        String date = fmt.format(new Date());
        StringBuilder code = new StringBuilder();
        code.append(idCode).append(date).append(numCode);
        return code.toString();
    }

    private long returnCode(String code){
        long number = 1;
        String result = redisCacheTemplate.get(code);
        if(Utils.isNotEmpty(result)){
            number = redisCacheTemplate.incr(code);
        }else {
            redisCacheTemplate.set(code,String.valueOf(number));
        }
        return number;
    }


}
