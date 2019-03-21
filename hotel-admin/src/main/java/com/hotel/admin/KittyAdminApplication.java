package com.hotel.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动器
 * @author Louis
 * @date Oct 29, 2018
 */
//@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"com.hotel"})
public class KittyAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(KittyAdminApplication.class, args);
	}
}
