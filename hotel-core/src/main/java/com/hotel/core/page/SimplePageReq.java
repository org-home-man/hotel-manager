package com.hotel.core.page;

import java.util.HashMap;
import java.util.Map;

public class SimplePageReq {


    /**
     * 当前页码
     */
    private int page = 1;
    /**
     * 每页数量
     */
    private int rows = 10;
    /**
     * 每页数量
     */
    private Map<String, ColumnFilter> columnFilters = new HashMap<String, ColumnFilter>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Map<String, ColumnFilter> getColumnFilters() {
        return columnFilters;
    }

    public void setColumnFilters(Map<String, ColumnFilter> columnFilters) {
        this.columnFilters = columnFilters;
    }

    public ColumnFilter getColumnFilter(String name) {
        return columnFilters.get(name);
    }
}
