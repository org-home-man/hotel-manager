package com.hotel.core.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * @Author:chenchao
 * @Version: v1.0.0
 * @Despriction:
 * @Date:Created in 2019-04-20
 */
public class ResultInfo extends LinkedHashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 4379129506879806179L;
    private boolean success;
    public static final String SUCCESS = "200";
    public static final String ISNULL = "201";
    public static final String EXIST = "202";
    public static final String PAY_PSW_ERROR = "203";
    public static final String PAY_PSW_LOCKED = "204";
    public static final String ERROR = "500";
    public static final String EXPIRED = "2001";
    public static final String PASS_ERROR = "2002";

    public ResultInfo() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public static ResultInfo instance() {
        ResultInfo ri = new ResultInfo();
        ri.setSuccess(true);
        return ri;
    }

    public void fail() {
        this.put("success", false);
        this.success = false;
    }

    public void setSuccess(boolean success) {
        this.put("success", success);
        this.success = success;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public String getMsg() {
        return (String)this.get("msg");
    }

    public void setMsg(String msg) {
        this.put("msg", msg);
    }

    public Object getData() {
        return this.get("data");
    }

    public void setData(Object data) {
        this.put("data", data);
    }

    public String getStatusCode() {
        return (String)this.get("statusCode");
    }

    public void setStatusCode(String statusCode) {
        this.put("statusCode", statusCode);
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
