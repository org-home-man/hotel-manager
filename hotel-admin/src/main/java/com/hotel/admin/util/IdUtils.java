package com.hotel.admin.util;

import com.hotel.admin.model.BizHotl;
import com.hotel.admin.model.BizPuchs;
import com.hotel.admin.model.BizRoom;
import com.hotel.common.redis.RedisCacheTemplate;
import com.hotel.common.utils.Utils;
import com.hotel.core.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static final String HOTEL_CODE = "hotel:hotelCode:";
    public static final String ROOM_CODE = "hotel:roomCode:";
    public static final String ORDER_CODE = "hotel:orderCode:";

    @Autowired
    private RedisCacheTemplate redisCacheTemplate;

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
        String key = HOTEL_CODE+provinceCode+cityCode;
        long number = 1;
        String result = redisCacheTemplate.get(key);
        if(Utils.isNotEmpty(result)){
            number = redisCacheTemplate.incr(key);
        }else {
            redisCacheTemplate.set(key,String.valueOf(number));
        }
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
        String key = ROOM_CODE + hotelCode ;
        long number = 1;
        String result = redisCacheTemplate.get(key);
        if(Utils.isNotEmpty(result)){
            number = redisCacheTemplate.incr(key);
        }else {
            redisCacheTemplate.set(key,String.valueOf(number));
        }
        String typeCode = new DecimalFormat("00").format(roomType);
        String numCode = new DecimalFormat("0000").format(number);
        StringBuilder code = new StringBuilder();
        code.append(hotelCode).append(typeCode).append(numCode);
        return code.toString();
    }

    /**
     * 根据订单创建人id + 当前时间 + 4流水号生成
     * @param bizPuchs
     * @return
     */
    public String generateOrderCode(BizPuchs bizPuchs){
        Long createId = bizPuchs.getCreateId();
        if(Utils.isEmpty(createId)){
            throw new GlobalException("noProvince");
        }
        long number = 1;
        //根据酒店编码生成
        String key = ORDER_CODE + createId;
        String result = redisCacheTemplate.get(key);
        if(Utils.isNotEmpty(result)){
            number = redisCacheTemplate.incr(key);
        }else {
            redisCacheTemplate.set(key,String.valueOf(number));
        }
        String numCode = new DecimalFormat("0000").format(number);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        String date = fmt.format(new Date());
        StringBuilder code = new StringBuilder();
        code.append(createId).append(date).append(numCode);
        return code.toString();
    }


}
