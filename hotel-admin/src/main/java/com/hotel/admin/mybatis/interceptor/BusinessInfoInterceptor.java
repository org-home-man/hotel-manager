package com.hotel.admin.mybatis.interceptor;

import com.hotel.common.entity.BusinessEntity;
import com.hotel.common.entity.auth.ISysUser;
import com.hotel.common.utils.DateUtils;
import com.hotel.common.utils.Utils;
import com.hotel.core.context.UserContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @FileName:    BusinessInfoInterceptor.java
      
 *
 
 * @version      V1.0
 * @date:        2016年6月29日 上午11:28:05
 * 
 * @Description: 新增、修改公共属性维护 
 *
 */
@Component
@Intercepts({ @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class }) })
public class BusinessInfoInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
		Object obj = invocation.getArgs()[1];
		BusinessEntity entity = null;
		if (obj instanceof BusinessEntity) {
			entity = (BusinessEntity) obj;
		}else{
			return invocation.proceed();
		}
		ISysUser currentUser = UserContext.getCurrentUser();
		if (sqlCommandType == SqlCommandType.UPDATE) {
			if (entity != null) {
				entity.setUpdateId(currentUser.getId());
				entity.setUpdateTime(DateUtils.getNowTime());
				entity.setUpdateName(currentUser.getName());
			}
			
		}else if (sqlCommandType == SqlCommandType.INSERT) {
			if (entity != null) {
				if (Utils.isEmpty(entity.getCreateTime())){
					entity.setCreateTime(DateUtils.getNowTime());
				}
				entity.setUpdateTime(DateUtils.getNowTime());
				entity.setCreateName(currentUser.getName());
				entity.setCreateId(currentUser.getId());
				entity.setUpdateName(currentUser.getName());
//				if(Utils.isEmpty(entity.getId())){
//					//自动生成ID
//					Long id = IdUtil.nextId();
//					System.out.println(id);
//					entity.setId(id);
//				}
			}
		}

		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
