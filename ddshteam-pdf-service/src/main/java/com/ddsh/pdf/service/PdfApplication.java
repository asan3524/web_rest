package com.ddsh.pdf.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages="com.ddsh.pdf.service.dao.**")
@DubboComponentScan(basePackages="com.ddsh.pdf.service.impl")
public class PdfApplication {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(PdfApplication.class);
        application.setBannerMode(Banner.Mode.LOG); 
        application.run(args); 
        
	}
}
