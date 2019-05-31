package com.hotel.core.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常
 * @author Louis
 * @date Aug 21, 2018
 */
public class GlobalException extends RuntimeException implements HandlerExceptionResolver {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 10001;
    
    public GlobalException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public GlobalException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public GlobalException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public GlobalException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
		return null;
	}
}
