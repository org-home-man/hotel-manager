package com.hotel.admin.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.hotel.admin.service.SysLoginService;
import com.hotel.admin.vo.LoginBean;
import com.hotel.common.utils.IOUtils;
import com.hotel.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录控制器
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
public class SysLoginController {

	@Autowired
	private Producer producer;
	@Autowired
	private SysLoginService loginService;

	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		// 生成文字验证码
		String text = producer.createText();
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到验证码到 session
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);	
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录接口
	 */
	@PostMapping(value = "/login")
	public HttpResult login(LoginBean loginBean, HttpServletRequest request){
		return loginService.login(loginBean,request);
	}

	/**
	 * 登录接口
	 */
	@GetMapping(value = "/signOut")
	public HttpResult logout(){
		return loginService.logout();
	}
}
