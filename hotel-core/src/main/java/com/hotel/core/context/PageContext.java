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

    /**
     * 是否分页
     */
    private static final ThreadLocal<Boolean> pagination = new ThreadLocal<>();
    /**
     * 是否数据授权
     */
    private static final ThreadLocal<Boolean> authDataSql = new ThreadLocal<>();

    /**
     * 是否是接口访问
     */
    private static final ThreadLocal<Boolean> apiRequest = new ThreadLocal<>();


    /**
     * 判断是否是需要分页
     * @return

     * @date 2019年4月23日 00:04:34
     */
    public static boolean isPager(){
        Boolean pager = pagination.get();
        if(pager == null) return false;
        return pager;
    }

    /**
     * 判断是否是需要数据授权
     * @return

     * @date 2019年4月23日 00:04:30
     */
    public static boolean isAuthDataSql(){
        Boolean authData = authDataSql.get();
        if(authData == null) return false;
        return authData;
    }

    /**
     * 判断是否是api接口访问
     * @return

     * @date 2019年4月23日 00:04:27
     */
    public static boolean isApi(){
        Boolean isApi = apiRequest.get();
        if(isApi == null) return false;
        return isApi;
    }

    public static void setPagination(boolean flag){
        pagination.set(flag);
    }
    public static void setAuthDataSql(boolean flag){
        authDataSql.set(flag);
    }
    public static void setApiFlag(boolean flag){
        apiRequest.set(flag);
    }

    private static final String TOTAL_COLUMNS = "_TOTAL_COLUMNS";
    private static final String TOTAL_NAME = "_TOTAL_NAME";

    /**
     * 缓存分页信息
     */
    public static final ThreadLocal<Page> localPage = new ThreadLocal<Page>();


    /**
     * 总计字段缓存
     */
    private static final ThreadLocal<String> totalCols = new ThreadLocal<>();
    private static final ThreadLocal<String> nameCols = new ThreadLocal<>();
    /**
     * 初始化分页信息
     * @param request

     * @date 2019年4月23日 00:04:47
     */
    public static void init(HttpServletRequest request){
        localPage.set(null);
        setNameCols(null);
        setTotalCols(null);
        String rowStr = request.getParameter("rows");
        String pageStr = request.getParameter("page");
        if(Utils.isNotNull(pageStr) && Utils.isNotNull(rowStr)){
            pagination.set(true);
            localPage.set(new Page(Integer.valueOf(pageStr),Integer.valueOf(rowStr) ));
        }else{
            localPage.set(new Page());
        }

        /**
         * 设置总计信息
         */
        String colsStr = request.getParameter(TOTAL_COLUMNS);
        String nameStr = request.getParameter(TOTAL_NAME);
        if(Utils.isNotNull(colsStr) && Utils.isNotNull(nameStr)){
            setTotalCols(colsStr);
            setNameCols(nameStr);

        }
    }

    public static void setPage(int rowNum,int rowSize){
        localPage.set(new Page(rowNum, rowSize));
    }

    public static void setPage(Page page){
        localPage.set(page);
    }

    public static ThreadLocal<Page> getLocalPage(){
        return localPage;
    }

    public static void setTotalCols(String str){
        totalCols.set(str);
    }

    public static void setNameCols(String str){
        nameCols.set(str);
    }

    public static String getTotalCols(){
        return totalCols.get();
    }

    public static String getNameCols(){
        return nameCols.get();
    }

    /**
     * 获取分页后的查询结果
     * @return

     * @date 2019年4月23日 00:04:51
     */
    public static Page getPage(){
        Page result = localPage.get();
        localPage.remove();
        return result;
    }
}
