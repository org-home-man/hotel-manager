package com.hotel.core.context;

import com.hotel.common.utils.Utils;
import com.hotel.core.page.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:chenchao
 * @Version: v1.0.0
 * @Despriction:
 * @Date:Created in 2019-04-20
 */
public class PageContext {
    private static final ThreadLocal<Boolean> pagination = new ThreadLocal();
    private static final ThreadLocal<Boolean> authDataSql = new ThreadLocal();
    private static final ThreadLocal<Boolean> apiRequest = new ThreadLocal();
    private static final String TOTAL_COLUMNS = "_TOTAL_COLUMNS";
    private static final String TOTAL_NAME = "_TOTAL_NAME";
    public static final ThreadLocal<Page> localPage = new ThreadLocal();
    private static final ThreadLocal<String> totalCols = new ThreadLocal();
    private static final ThreadLocal<String> nameCols = new ThreadLocal();

    public PageContext() {
    }

    public static boolean isPager() {
        Boolean pager = (Boolean)pagination.get();
        return pager == null ? false : pager;
    }

    public static boolean isAuthDataSql() {
        Boolean authData = (Boolean)authDataSql.get();
        return authData == null ? false : authData;
    }

    public static boolean isApi() {
        Boolean isApi = (Boolean)apiRequest.get();
        return isApi == null ? false : isApi;
    }

    public static void setPagination(boolean flag) {
        pagination.set(flag);
    }

    public static void setAuthDataSql(boolean flag) {
        authDataSql.set(flag);
    }

    public static void setApiFlag(boolean flag) {
        apiRequest.set(flag);
    }

    public static void init(HttpServletRequest request) {
        localPage.set(null);
        setNameCols((String)null);
        setTotalCols((String)null);
        String rowStr = request.getParameter("rows");
        String pageStr = request.getParameter("page");
        if (Utils.isNotNull(pageStr) && Utils.isNotNull(rowStr)) {
            pagination.set(true);
            localPage.set(new Page(Integer.valueOf(pageStr), Integer.valueOf(rowStr)));
        } else {
            localPage.set(new Page());
        }

        String colsStr = request.getParameter("_TOTAL_COLUMNS");
        String nameStr = request.getParameter("_TOTAL_NAME");
        if (Utils.isNotNull(colsStr) && Utils.isNotNull(nameStr)) {
            setTotalCols(colsStr);
            setNameCols(nameStr);
        }

    }

    public static void setPage(int rowNum, int rowSize) {
        localPage.set(new Page(rowNum, rowSize));
    }

    public static void setPage(Page page) {
        localPage.set(page);
    }

    public static ThreadLocal<Page> getLocalPage() {
        return localPage;
    }

    public static void setTotalCols(String str) {
        totalCols.set(str);
    }

    public static void setNameCols(String str) {
        nameCols.set(str);
    }

    public static String getTotalCols() {
        return (String)totalCols.get();
    }

    public static String getNameCols() {
        return (String)nameCols.get();
    }

    public static Page getPage() {
        Page result = (Page)localPage.get();
        localPage.remove();
        return result;
    }
}
