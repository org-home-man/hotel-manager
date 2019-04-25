package com.hotel.admin.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @FileName:    MyBatisUtil.java
      
 *
 
 * @version      V1.0
 * @date:        2016年7月11日 上午11:16:15
 * 
 * @Description: MyBatis根据数据库自动创建实体，接口，映射文件
 *
 */
public class MyBatisUtil {

	/**
	 * @param xml 编译路径下的配置文件名
	 * @param overwrite 是否覆盖已有文件
	
	 * @date 2016年7月11日
	 */
	public static void generate(String xml, boolean overwrite) {
		List<String> warnings = new ArrayList<String>();
		InputStream cfgStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(xml);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config;
        try {
	        config = cp.parseConfiguration(cfgStream);
	        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
	        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
	        myBatisGenerator.generate(null);
        } catch (Exception e) {
	        e.printStackTrace();
        } 
	}
	
	/**
	 * 配置好generatorConfig.xml后直接运行生成
	 * @param args
	
	 * @date 2016年7月11日
	 */
	public static void main(String[] args) {
		System.out.println("start");
		MyBatisUtil.generate("generatorConfig.xml", true);
		System.out.println("end");
	}
}
