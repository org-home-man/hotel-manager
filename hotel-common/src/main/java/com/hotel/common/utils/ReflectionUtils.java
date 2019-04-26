package com.hotel.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射相关辅助方法
 * @author Louis
 * @date Aug 19, 2018
 */
public class ReflectionUtils {

	
	/**
	 * 根据方法名调用指定对象的方法
	 * @param object 要调用方法的对象
	 * @param method 要调用的方法名
	 * @param args 参数对象数组
	 * @return
	 */
	public static Object invoke(Object object, String method, Object... args) {
		Object result = null;
		Class<? extends Object> clazz = object.getClass();
		Method queryMethod = getMethod(clazz, method, args);
//		System.out.println("++++++queryMethod:"+ queryMethod.getName() );
		if(queryMethod != null) {
			try {
				result = queryMethod.invoke(object, args);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
//				e.getTargetException();
				e.printStackTrace();
			}
		} else {
			try {
				throw new NoSuchMethodException(clazz.getName() + " 类中没有找到 " + method + " 方法。");
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 根据方法名和参数对象查找方法
	 * @param clazz
	 * @param name
	 * @param args 参数实例数据
	 * @return
	 */
	public static Method getMethod(Class<? extends Object> clazz, String name, Object[] args) {
		Method queryMethod = null;
		Method[] methods = clazz.getMethods();
		for(Method method:methods) {
			if(method.getName().equals(name)) {
//				System.out.println("方法名找到了++++++++");
				Class<?>[] parameterTypes = method.getParameterTypes();
//				System.out.println("参数个数+++++："+parameterTypes.length);
//				System.out.println("传入的参数长度："+args.length);
				if(parameterTypes.length == args.length) {
					boolean isSameMethod = true;
					for(int i=0; i<parameterTypes.length; i++) {
						Object arg = args[i];
						if(arg == null) {
							arg = "";
						}
//						System.out.println("传入参数的镜像："+args[i].getClass());
//						System.out.println("方法参数的镜像："+parameterTypes[i]);
						if(!parameterTypes[i].equals(args[i].getClass())) {
							isSameMethod = false;
						}
					}
					if(isSameMethod) {
						queryMethod = method;
						break ;
					}
				}
			}
		}
		return queryMethod;
	}
}
