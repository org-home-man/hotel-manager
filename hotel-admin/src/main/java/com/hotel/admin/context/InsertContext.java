package com.hotel.admin.context;

/**
 * @Author:chenchao
 * @Version: v1.0.0
 * @Despriction:
 * @Date:Created in 2018-10-30
 */
public class InsertContext {
    private static final ThreadLocal<Boolean> insertion = new ThreadLocal();

    public static void setInsertion(boolean flag) {
        insertion.set(flag);
    }
    public static boolean isGenerateId() {
        Boolean generate = (Boolean)insertion.get();
        return generate == null ? true : generate;
    }
}
