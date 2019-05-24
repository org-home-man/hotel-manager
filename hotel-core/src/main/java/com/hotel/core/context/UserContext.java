package com.hotel.core.context;

import com.hotel.common.entity.auth.ISysUser;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.http.HttpStatus;

/**
 * @Description: 会话上下文，含有当前用户信息
 *
 */
public class UserContext {

	private static ThreadLocal<ISysUser> ISysUser = new ThreadLocal<>();
	private static ThreadLocal<String> token = new ThreadLocal<>();

	public static <T extends ISysUser> void setUser(T t) {
		// if(Utils.isNotEmpty(t))
		ISysUser.set(t);
	}

	public static String getToken() {
		return token.get();
	}

	public static void setToken(String t) {
		token.set(t);
	}

	public static ISysUser getCurrentUser() {
		if (ISysUser.get() == null) {
			throw new GlobalException("loginExpired", HttpStatus.SC_LOGIN_EXPIRE);
		}
		return ISysUser.get();
	}

}
