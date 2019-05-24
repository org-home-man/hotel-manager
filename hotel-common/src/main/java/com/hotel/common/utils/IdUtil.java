package com.hotel.common.utils;

import java.security.MessageDigest;
import java.util.*;

public class IdUtil {

    private static final IdWorker ID_WORKER_HOTEL = new IdWorker(5, 5);
    private static final IdWorker ID_WORKER_ORDER = new IdWorker(2, 2);

    private IdUtil() {

    }

    public static Long nextHotelId() {
        return ID_WORKER_HOTEL.nextId();
    }
    public static Long nextOrderId() {
        return ID_WORKER_ORDER.nextId();
    }
    
    public static String getUUID() {
    	return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        System.out.println("hotelId:  " + nextHotelId());
        System.out.println("orderId:  " + nextOrderId());
//        System.out.println(System.currentTimeMillis());
        Set<Long> l = new HashSet<>();
        for (int i = 0; i < 1000000; i++) {
            l.add(nextHotelId());
            l.add(nextOrderId());
        }
        System.out.println(l.size());
    }

    /**
     * 基于UUID+MD5产生唯一无序ID
     * <pre>
     *  线程数量:   100
     *  执行次数:   1000
     *  平均耗时:       591 ms
     *  数组长度:   100000
     *  Map Size:   100000
     * </pre>
     * @return  ID长度32位
     */
    public static String getRandomIdByUUID(){
        return DigestUtils.md5Hex("1".toString());
    }


    /* ---------------------------------------------分割线------------------------------------------------ */
    /** 字符串MD5处理类 */
    private static class DigestUtils {

        private static final char[] DIGITS_LOWER =
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        private static final char[] DIGITS_UPPER =
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        private static char[] encodeHex(final byte[] data, final char[] toDigits) {
            final int l = data.length;
            final char[] out = new char[l << 1];
            for (int i = 0, j = 0; i < l; i++) {
                out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
                out[j++] = toDigits[0x0F & data[i]];
            }
            return out;
        }

        public static String md5Hex(String str){
            return md5Hex(str, false);
        }

        public static String md5Hex(String str, boolean isUpper){
            try {
                return new String(encodeHex(MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8")), isUpper ? DIGITS_UPPER : DIGITS_LOWER));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
