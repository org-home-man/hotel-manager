package com.hotel.core.page;

import com.hotel.core.model.GridResult;

/**
 * @Author:chenchao
 * @Version: v1.0.0
 * @Despriction:
 * @Date:Created in 2019-04-20
 */
public class Page extends GridResult {
    private static final long serialVersionUID = -3654849016503970448L;
    private String msg;

    public Page(int pageNum, int pageSize) {
        this.setPageNum(pageNum);
        this.setPageSize(pageSize);
        this.setStartRow(pageNum > 0 ? (pageNum - 1) * pageSize + 1 : 1);
        this.setEndRow(pageNum * pageSize + 1);
    }

    public Page() {
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
