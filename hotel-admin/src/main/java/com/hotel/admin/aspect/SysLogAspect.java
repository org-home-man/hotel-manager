package com.hotel.admin.aspect;

import com.alibaba.fastjson.JSONObject;
import com.hotel.admin.model.SysLog;
import com.hotel.admin.service.SysLogService;
import com.hotel.admin.util.IPUtils;
import com.hotel.common.entity.auth.ISysUser;
import com.hotel.core.annotation.SystemControllerLog;
import com.hotel.core.annotation.SystemServiceLog;
import com.hotel.core.context.UserContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * 系统日志，切面处理类，记录日志
 */
@Aspect
@Component
@SuppressWarnings("all")
public class SysLogAspect {
	
	@Autowired
	private SysLogService sysLogService;
	private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

	@Pointcut("@annotation(com.hotel.core.annotation.SystemServiceLog)")
	public void serviceAspect(){

	}
	@Pointcut("@annotation(com.hotel.core.annotation.SystemControllerLog)")
	public void controllerAspect(){

	}

	/**
	 * @Description 前置通知 用户拦截Controller层记录用户的操作
	 * @param joinPoint
	 */
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		ISysUser user = UserContext.getCurrentUser();

		String ip = IPUtils.getIpAddr(request);

		//获取用户请求方法的参数并序列化为JSON格式字符串
		String params = "";
		if (joinPoint.getArgs()!=null&&joinPoint.getArgs().length>0){
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				Object arg = joinPoint.getArgs()[i];
				if(arg instanceof HttpServletResponse || arg instanceof HttpServletRequest){
					continue;
				}
				params+= JSONObject.toJSONString(arg)+";";
			}
		}

		try {
			//*========控制台输出=========*//
			logger.info("==============前置通知开始==============");
			logger.info("请求方法" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
			logger.info("请求参数" + params);
			logger.info("方法描述：" + getControllerMethodDescription(joinPoint));
			logger.info("请求人："+user.getName());
			logger.info("请求ip："+ip);

			//*========数据库日志=========*//
			SysLog sysLog = new SysLog();
			sysLog.setUserName(user.getName());
			sysLog.setMethod(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
			sysLog.setOperation(getControllerMethodDescription(joinPoint));
			sysLog.setIp(ip);
			sysLog.setParams(params);
			sysLog.setTime(System.currentTimeMillis());
			//保存数据库
			sysLogService.save(sysLog);

		}catch (Exception e){
			//记录本地异常日志
			logger.error("==前置通知异常==");
			logger.error("异常信息：{}",e.getMessage());
		}
	}

	/**
	 * @Description  异常通知 用于拦截service层记录异常日志
	 * @date 2018年9月3日 下午5:43
	 */
	@AfterThrowing(pointcut = "serviceAspect()",throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint,Throwable e){
//		if(e instanceof GlobalException){
//			//不处理业务异常
//			return;
//		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		ISysUser user = UserContext.getCurrentUser();
		//获取请求ip
		String ip = IPUtils.getIpAddr(request);
		//获取用户请求方法的参数并序列化为JSON格式字符串
		String params = "";
		if (joinPoint.getArgs()!=null&&joinPoint.getArgs().length>0){
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				params+= JSONObject.toJSONString(joinPoint.getArgs()[i])+";";
			}
		}
		try{
			/*========控制台输出=========*/
			System.out.println("=====异常通知开始=====");
			System.out.println("异常代码:" + e.getClass().getName());
			System.out.println("异常信息:" + e.getMessage());
			System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			System.out.println("方法描述:" + getServiceMethodDescription(joinPoint));
			System.out.println("请求人:" + user.getName());
			System.out.println("请求IP:" + ip);
			System.out.println("请求参数:" + params);
			/*==========数据库日志=========*/
			SysLog sysLog = new SysLog();
			sysLog.setUserName(user.getName());
			sysLog.setMethod(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
			sysLog.setOperation(getControllerMethodDescription(joinPoint));
			sysLog.setIp(ip);
			sysLog.setParams(params);
			sysLog.setTime(System.currentTimeMillis());
			//保存数据库
			sysLogService.save(sysLog);
		}catch (Exception ex){
			//记录本地异常日志
			logger.error("==异常通知异常==");
			logger.error("异常信息:{}", ex.getMessage());
		}
	}

	/**
	 * @Description  获取注解中对方法的描述信息 用于service层注解
	 * @date 2018年9月3日 下午5:05
	 */
	public static String getServiceMethodDescription(JoinPoint joinPoint)throws Exception{
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method:methods) {
			if (method.getName().equals(methodName)){
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length==arguments.length){
					description = method.getAnnotation(SystemServiceLog.class).description();
					break;
				}
			}
		}
		return description;
	}

	/**
	 * @author chenchao
	 * @Description  获取注解中对方法的描述信息 用于Controller层注解
	 * @date 2019年6月27日15:16:16
	 */
	public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();//目标方法名
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method:methods) {
			if (method.getName().equals(methodName)){
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length==arguments.length){
					description = method.getAnnotation(SystemControllerLog.class).description();
					break;
				}
			}
		}
		return description;
	}
}
