package com.hotel.common.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @Author:chenchao
 * @Version: v1.0.0
 * @Despriction:
 * @Date:Created in 2019-04-20
 */
public class Utils {
    public Utils() {
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj == "") {
            return true;
        } else {
            if (obj instanceof String) {
                if (((String)obj).length() == 0) {
                    return true;
                }
            } else if (obj instanceof Long) {
                if ((Long)obj == 0L) {
                    return true;
                }
            } else if (obj instanceof Integer) {
                if ((Integer)obj == 0) {
                    return true;
                }
            } else if (obj instanceof Float) {
                if ((Float)obj == 0.0F) {
                    return true;
                }
            } else if (obj instanceof Double) {
                if ((Double)obj == 0.0D) {
                    return true;
                }
            } else if (obj instanceof Short) {
                if ((Short)obj == 0) {
                    return true;
                }
            } else if (obj instanceof Collection) {
                if (((Collection)obj).size() == 0 || ((Collection)obj).isEmpty()) {
                    return true;
                }
            } else if (obj instanceof Map && ((Map)obj).size() == 0) {
                return true;
            }

            return false;
        }
    }

    public static boolean isNotEmpty(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj == "") {
            return false;
        } else {
            if (obj instanceof String) {
                if (((String)obj).length() == 0 || ((String)obj).trim().equals("")) {
                    return false;
                }
            } else if (obj instanceof Long) {
                if ((Long)obj == 0L) {
                    return false;
                }
            } else if (obj instanceof Integer) {
                if ((Integer)obj == 0) {
                    return false;
                }
            } else if (obj instanceof Float) {
                if ((Float)obj == 0.0F) {
                    return false;
                }
            } else if (obj instanceof Double) {
                if ((Double)obj == 0.0D) {
                    return false;
                }
            } else if (obj instanceof Short) {
                if ((Short)obj == 0) {
                    return false;
                }
            } else if (obj instanceof Collection) {
                if (((Collection)obj).size() == 0 || ((Collection)obj).isEmpty()) {
                    return false;
                }
            } else if (obj instanceof Map && (((Map)obj).size() == 0 || ((Map)obj).isEmpty())) {
                return false;
            }

            return true;
        }
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }
}
