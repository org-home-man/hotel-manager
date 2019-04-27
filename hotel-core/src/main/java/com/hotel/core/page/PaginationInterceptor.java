package com.hotel.core.page;

import com.hotel.common.utils.Utils;
import com.hotel.core.context.PageContext;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
import java.util.Properties;


/**
 * 分页拦截器mybatis
 *
 * @author cc
 * @date 2019年4月22日 23:25:47
 */
@Component
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class}),
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class PaginationInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(PaginationInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (PageContext.getLocalPage().get() == null) {
            return invocation.proceed();
        }
        if (invocation.getTarget() instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) invocation
                    .getTarget();
            MetaObject metaStatementHandler = SystemMetaObject
                    .forObject(statementHandler);
            // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
            // 可以分离出最原始的的目标类)
            while (metaStatementHandler.hasGetter("h")) {
                Object object = metaStatementHandler.getValue("h");
                metaStatementHandler = SystemMetaObject.forObject(object);
            }
            // 分离最后一个代理对象的目标类
            while (metaStatementHandler.hasGetter("target")) {
                Object object = metaStatementHandler.getValue("target");
                metaStatementHandler = SystemMetaObject.forObject(object);
            }
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler
                    .getValue("delegate.mappedStatement");

            SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

            if (sqlCommandType == SqlCommandType.SELECT) {
                // 分页信息if (localPage.get() != null) {
                Page page = PageContext.getLocalPage().get();
                BoundSql boundSql = (BoundSql) metaStatementHandler
                        .getValue("delegate.boundSql");
                // 分页参数作为参数对象parameterObject的一个属性
                String sql = boundSql.getSql();

                // 重写sql
                String pageSql = buildPageSql(sql, page);

                // 重写分页sql
                metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
                Connection connection = (Connection) invocation.getArgs()[0];

                // 重设分页参数里的总页数等
                if (PageContext.getLocalPage().get() != null
                        && PageContext.getLocalPage().get().getPageNum() != 0) {
                    setPageParameter(sql, connection, mappedStatement,
                            boundSql, page);
                }

            }

            // 将执行权交给下一个拦截器
            return invocation.proceed();
        } else if (invocation.getTarget() instanceof ResultSetHandler) {
            Object result = invocation.proceed();
            // 分页查询信息
            Page page = PageContext.getLocalPage().get();
            page.setRows((List) result);
            return result;
        }
        return null;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler
                || target instanceof ResultSetHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 修改原SQL为分页SQL
     *
     * @param sql
     * @param page
     * @return

     * @date 2016年6月29日
     */
    private String buildPageSql(String sql, Page page) {
        if (Utils.isEmpty(page.getPageSize()))
            return sql;
        StringBuilder pageSql = new StringBuilder(200);
        pageSql.append(sql);
        pageSql.append(" limit ");
        pageSql.append(page.getStartRow());
        pageSql.append(",");
        pageSql.append(page.getPageSize());
        // 判断是否有order by

        return pageSql.toString();
    }

    StringBuilder bindOrberBy(String sql, StringBuilder pageSql) {
        sql = sql.toLowerCase();
        String s = "order by";
        if (sql.indexOf("order by") > 0) {
            // 需要截取
            String orderByStr = sql.substring(sql.lastIndexOf(s), sql.length());
            String cls = sql.substring(0, sql.lastIndexOf(s)).toLowerCase();
            String orf = "";
            String s1 = orderByStr.toLowerCase().replaceAll("order by", "");
            String[] ayy = s1.split(",");
            int i = 0;
            for (String string : ayy) {

                if (string.indexOf(".") > 0) {
                    String c = string.substring(string.indexOf(".") + 1,
                            string.length());
                    if (cls.indexOf(c) > 0) {
                        i++;
                        orf = orf + " t." + c + " and ";
                    }
                } else {
                    i++;
                    orf = orf + " t." + string + " and ";
                }

            }
            if (i > 0) {
                orf = " order by " + (orf.substring(0, orf.lastIndexOf("and")));
                return pageSql.append(" ").append(orf);
            }

        }
        //
        return pageSql;

    }

    /**
     * 获取总记录数
     *
     * @param sql
     * @param connection
     * @param mappedStatement
     * @param boundSql
     * @param page

     * @date 2016年6月29日
     */
    private void setPageParameter(String sql, Connection connection,
                                  MappedStatement mappedStatement, BoundSql boundSql, Page page) {
        // 记录总记录数
        String countSql = "select count(0) from (" + sql + ") t";
        String ysql = sql.toLowerCase();
        boolean join = false;
//        if (ysql.indexOf("left") != -1 && ysql.indexOf("join") != -1 ) {
//            join = true;
//            countSql = countSql + " group by t.id ";
//        }
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            countStmt = connection.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(),
                    countSql, boundSql.getParameterMappings(),
                    boundSql.getParameterObject());
            setParameters(countStmt, mappedStatement, boundSql,
                    boundSql.getParameterObject());
            rs = countStmt.executeQuery();
            int totalCount = 0;
            if (join) {
                while(rs.next()) {
                    totalCount++;
                }
            } else {
                if (rs.next()) {
                    totalCount = rs.getInt(1);
                }
            }
            page.setTotal(totalCount);
        } catch (SQLException e) {
            logger.error("Ignore this exception", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                logger.error("Ignore this exception", e);
            }
            try {
                if (countStmt != null) {
                    countStmt.close();
                }
            } catch (SQLException e) {
                logger.error("Ignore this exception", e);
            }
        }
    }

    /**
     * 代入参数值
     *
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws SQLException
     */
    private void setParameters(PreparedStatement ps,
                               MappedStatement mappedStatement, BoundSql boundSql,
                               Object parameterObject) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(
                mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }
}
