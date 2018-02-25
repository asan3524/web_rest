package com.ddshteam.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@ServletComponentScan
@EnableAsync
@EnableScheduling
@EnableCaching
@EnableSwagger2
public class WebApplication extends SpringBootServletInitializer implements CommandLineRunner {

	private final static Logger logger = LoggerFactory.getLogger(WebApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(WebApplication.class);
		application.setBannerMode(Banner.Mode.LOG);
		application.run(args);
		// SpringApplication.run(WebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(" web application start success................... ");
	}
}
