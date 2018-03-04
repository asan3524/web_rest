package com.ddshteam.web.config;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.ddshteam.web.core.interceptor.MaliciousRequestInterceptor;
import com.ddshteam.web.core.interceptor.SignInterceptor;
import com.ddshteam.web.core.listener.ServerListener;

@Configuration
@Component
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

	@Value("${spring.profiles.active}")
	private String env = "env";

	@Value("${spring.upload.maxFileSize}")
	private Long maxFileSize;

	@Value("${spring.upload.maxRequestSize}")
	private Long maxRequestSize;

	/**
	 * FastJson 作为JSON MessageConverter
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		@SuppressWarnings("deprecation")
		FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
		FastJsonConfig config = new FastJsonConfig();
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		// 保留空的字段
		// String null -> ""
		// Number null -> 0
		config.setSerializerFeatures(SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullNumberAsZero, SerializerFeature.DisableCircularReferenceDetect);
		converter.setFastJsonConfig(config);
		converter.setDefaultCharset(Charset.forName("UTF-8"));

		converters.add(converter);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new MaliciousRequestInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/upload", "/upload/v1", "/upload/image");

		// 开发环境忽略签名认证,忽略拦截恶意请求
		if (!"dev".equals(env)) {
			registry.addInterceptor(new SignInterceptor()).addPathPatterns("/**")
					.excludePathPatterns("/", "/login", "/register");
		}

		super.addInterceptors(registry);
	}

	/**
	 * 静态文件请求匹配规则
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);

		// 路径模式串,URL请求 ---> 从哪个目录下加载资源文件
		registry.addResourceHandler("/internal/**").addResourceLocations("classpath:/cache/");

		/**
		 * Spring Boot 默认配置的/**映射到/static（或/public ，/resources，/META-INF/resources）,
		 * /webjars/**会映射到classpath:/META-INF/resources/webjars/
		 * 因为shiro默认放行/static,所以这里添加了一个映射(其实主要是为了开发页面时,js都是相对路径)
		 */
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

		/**
		 * 默认实现mvc已经添加, 如果使用了@EnableWebMvc需要自己写
		 */
		// registry.addResourceHandler("/favicon.ico").addResourceLocations("/static/favicon.ico");

		/**
		 * SpringBoot默认已经将classpath:/META-INF/resources/和classpath:/META-INF/resources/webjars/映射
		 */
		// registry.addResourceHandler("swagger-ui.html")
		// .addResourceLocations("classpath:/META-INF/resources/");
		// registry.addResourceHandler("/webjars/**")
		// .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	/**
	 * 不是所有的URL请求都遵循默认的规则
	 * 设置 RESTful URL匹配的时候包含定界符“.”
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		super.configurePathMatch(configurer);
		// 系统对外暴露的URL不会识别和匹配.*后缀
		// 表示系统不区分URL的最后一个字符是否是斜杠/
		configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
	}

	/**
	 * register chartSet filter
	 */
	@Bean
	@Order(1)
	public FilterRegistrationBean characterEncodingFilter() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new CharacterEncodingFilter("encodingFilter"));
		filterRegistration.addInitParameter("encoding", "UTF-8");
		filterRegistration.addInitParameter("forceEncoding", "true");
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");

		return filterRegistration;
	}

	/*
	 * @Bean(name = "xssFilter")
	 * 
	 * @Order(2) public FilterRegistrationBean xssFilter() {
	 * FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
	 * filterRegistration.setFilter(new XssFilter());
	 * filterRegistration.setEnabled(true);
	 * filterRegistration.addUrlPatterns("/*");
	 * 
	 * return filterRegistration; }
	 */
	/**
	 * 注册listener
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean<ServerListener> servletListenerRegistrationBean() {
		ServletListenerRegistrationBean<ServerListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
		servletListenerRegistrationBean.setListener(new ServerListener());
		return servletListenerRegistrationBean;
	}

	/**
	 * 上传配置,spring提供方式处理
	 * 直接参数使用 @RequestParam("name") String name, @RequestParam("file") MultipartFile file
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// -1为无限制,单文件限制2G,chunk限制10M
		factory.setMaxFileSize(maxFileSize);
		factory.setMaxRequestSize(maxRequestSize);
		// 上传目录
		// factory.setLocation("/upload");
		return factory.createMultipartConfig();
	}

	/**
	 * spring 跨域处理
	 * @Title: corsConfigurer
	 * @return WebMvcConfigurer
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowCredentials(true)
						.allowedMethods("GET", "POST", "OPTIONS", "HEAD", "PUT", "DELETE")
						.allowedHeaders("x-requested-with", "Access-Control-Allow-Origin", "EX-SysAuthToken",
								"EX-JSESSIONID", "Content-Type");
			}
		};
	}
}