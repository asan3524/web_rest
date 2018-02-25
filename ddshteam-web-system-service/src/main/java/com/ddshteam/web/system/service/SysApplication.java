package com.ddshteam.web.system.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages="com.ddshteam.web.system.service.dao.**")
@DubboComponentScan(basePackages="com.ddshteam.web.system.service.impl")
//@ServletComponentScan
//@ImportResource(value = "classpath:config/spring-dubbo-provider.xml")
public class SysApplication {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SysApplication.class);
        application.setBannerMode(Banner.Mode.LOG); 
        application.run(args); 
        
	}
}
