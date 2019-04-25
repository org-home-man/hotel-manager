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

    /**
     * @param pageNum  页数
     * @param pageSize  每页数据量
     */

    public Page(int pageNum, int pageSize) {
        setPageNum(pageNum);;
        setPageSize(pageSize);
        setStartRow(pageNum > 0 ? (pageNum - 1) * pageSize : 0);
        setEndRow(pageNum * pageSize);
    }

    public Page() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
