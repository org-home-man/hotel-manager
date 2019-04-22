package com.hotel.core.page;

import com.hotel.core.context.PageContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * 分页拦截器mybatis
 *
 * @author cc
 * @date 2019年4月22日 23:25:47
 */
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = { StatementHandler.class, ResultHandler.class})})
public class PaginationInterceptor implements Interceptor{

    private static final Logger logger = LoggerFactory.getLogger(PaginationInterceptor.class);

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
    private static String dialect = "mysql";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (PageContext.getLocalPage().get() == null) {
            return invocation.proceed();
        }
        // 获得拦截的对象
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        if (invocation.getTarget() instanceof StatementHandler) {

            // 待执行的sql的包装对象
            BoundSql boundSql = statementHandler.getBoundSql();
            // 判断是否是查询语句
            if (isSelect(boundSql.getSql())) {
                // 获得参数集合
                Object params = boundSql.getParameterObject();

                if (params instanceof Map) { // 请求为多个参数，参数采用Map封装
                    return complexParamsHandler(invocation, boundSql, (Map<?, ?>) params);
                } else if (params instanceof Page) { // 单个参数且为Page，则表示该操作需要进行分页处理
                    return simpleParamHandler(invocation, boundSql, (Page) params);
                }
            }
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

    private Object complexParamsHandler(Invocation invocation, BoundSql boundSql, Map<?, ?> params) throws Throwable {
        //判断参数中是否指定分页
        if (containsPage(params)) {
            return pageHandlerExecutor(invocation, boundSql, (Page) params.get("page"));
        } else {
            return invocation.proceed();
        }
    }

    private boolean containsPage(Map<?, ?> params) {
        if(params==null){
            return false;
        }else if(!params.containsKey("page")){
            return false;
        }
        Object page = params.get("page");
        if(page==null){
            return false;
        }else if(page instanceof SimplePage){
            return true;
        }
        return false;
    }

    private boolean isSelect(String sql) {
        if (!StringUtils.isEmpty(sql) && sql.toUpperCase().trim().startsWith("SELECT")) {
            return true;
        }
        return false;
    }

    private Object simpleParamHandler(Invocation invocation, BoundSql boundSql, Page page) throws Throwable {
        return pageHandlerExecutor(invocation, boundSql, page);
    }

    private Object pageHandlerExecutor(Invocation invocation, BoundSql boundSql, Page page) throws Throwable {
        // 获得数据库连接
        Connection connection = (Connection) invocation.getArgs()[0];
        // 使用Mybatis提供的MetaObject，该对象主要用于获取包装对象的属性值
        MetaObject statementHandler = MetaObject.forObject(invocation.getTarget(), DEFAULT_OBJECT_FACTORY,
                DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);

        // 获取该sql执行的结果集总数
        int maxSize = getTotalSize(connection, (MappedStatement) statementHandler.getValue("delegate.mappedStatement"),
                boundSql);

        // 生成分页sql
        page.setTotal(maxSize);
        String wrapperSql = getPageSql(boundSql.getSql(), page);

        MetaObject boundSqlMeta = MetaObject.forObject(boundSql, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,
                DEFAULT_REFLECTOR_FACTORY);
        // 修改boundSql的sql
        boundSqlMeta.setValue("sql", wrapperSql);
        return invocation.proceed();
    }

    private int getTotalSize(Connection connection, MappedStatement mappedStatement, BoundSql boundSql) {
        String countSql = getCountSql(boundSql.getSql());
        PreparedStatement countStmt;
        ResultSet rs;
        List<AutoCloseable> closeableList = new ArrayList<AutoCloseable>();

        try {
            countStmt = connection.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
                    boundSql.getParameterMappings(), boundSql.getParameterObject());
            setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
            rs = countStmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
            closeableList.add(countStmt);
            closeableList.add(rs);
        } catch (SQLException e) {
            logger.error("append an exception[{}] when execute sql[{}] with {}", e, countSql,
                    boundSql.getParameterObject());
        } finally {
            for (AutoCloseable closeable : closeableList) {
                try {
                    if (closeable != null)
                        closeable.close();
                } catch (Exception e) {
                    logger.error("append an exception[{}] when close resource[{}] ", e, closeable);
                }
            }
        }
        return 0;
    }

    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
                               Object parameterObject) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }

    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public String getCountSql(String sql) {
        if("mysql".equals(dialect)){
            return "select count(0) from (" + sql + ") as total";
        }
        return sql;
    }

    public String getPageSql(String sql, Page page) {

        if("mysql".equals(dialect)){
            return sql+" limit "+page.getStartRow()+", "+page.getRows();
        }
        return sql;
    }

}
