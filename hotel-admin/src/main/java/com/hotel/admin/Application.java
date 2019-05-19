package com.hotel.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 启动器
 * @author Louis
 * @date Oct 29, 2018
 */
//@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"com.hotel"})
//@ComponentScan(basePackages = "com.hotel.admin.config")
@tk.mybatis.spring.annotation.MapperScan("com.hotel.*.mapper")	// 扫描DAO
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
