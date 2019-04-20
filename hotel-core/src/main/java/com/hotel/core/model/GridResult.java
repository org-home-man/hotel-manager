package com.hotel.core.model;

import java.util.ArrayList;
import java.util.List;

public class GridResult extends Grid {
    private static final long serialVersionUID = 8234251686575881423L;
    private int total = 0;
    private int pageNum = 0;
    private int pageSize = 0;
    private int startRow;
    private int endRow;
    private List rows = new ArrayList();
    private List footer = new ArrayList();

    public GridResult() {
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return this.rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return this.startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return this.endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public List getFooter() {
        return this.footer;
    }

    public void setFooter(List footer) {
        this.footer = footer;
    }
}