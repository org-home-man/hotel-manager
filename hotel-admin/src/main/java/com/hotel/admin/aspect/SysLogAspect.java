package com.hotel.admin.aspect;

import javax.servlet.http.HttpServletRequest;

import com.hotel.admin.service.SysLogService;
import com.hotel.core.context.UserContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.hotel.admin.model.SysLog;
import com.hotel.admin.util.HttpUtils;
import com.hotel.admin.util.IPUtils;


/**
 * 系统日志，切面处理类，记录日志
 */
//@Aspect
//@Component
public class SysLogAspect {
	
	@Autowired
	private SysLogService sysLogService;
	
	@Pointcut("execution(* com.hotel.*.service.*.*(..))")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		// 保存日志
		saveSysLog(point, time);
		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
//		String userName = "admin";
		if(joinPoint.getTarget() instanceof SysLogService) {
			return ;
		}
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		SysLog sysLog = new SysLog();
		
//		Method method = signature.getMethod();
//		com.louis.merak.admin.annotation.SysLog syslogAnno = method.getAnnotation(com.louis.merak.admin.annotation.SysLog.class);
//		if(syslogAnno != null){
//			//注解上的描述
//			sysLog.setOperation(syslogAnno.value());
//		}

		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		if(methodName.indexOf("login")!=-1 ){
			return;
		}
		sysLog.setMethod(className + "." + methodName + "()");

		// 请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = JSONObject.toJSONString(args[0]);
			if(params.length() > 200) {
				params = params.substring(0, 200) + "...";
			}
			sysLog.setParams(params);
		} catch (Exception e){
		}

		// 获取request
		HttpServletRequest request = HttpUtils.getHttpServletRequest();
		// 设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));

//		String userName = UserContext.getCurrentUser().getName();
		// 用户名
//		sysLog.setUserName(userName);
		
		// 执行时长(毫秒)
		sysLog.setTime(time);
		
		// 保存系统日志
		sysLogService.save(sysLog);
	}

	
}
