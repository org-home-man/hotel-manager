package com.hotel.admin.util;

import java.util.UUID;

public class IdUtil {

    private static final IdWorker ID_WORKER = new IdWorker(5, 5);

    private IdUtil() {

    }

    public static Long nextId() {
        return ID_WORKER.nextId();
    }
    
    public static String getUUID() {
    	return UUID.randomUUID().toString();
    }
}
