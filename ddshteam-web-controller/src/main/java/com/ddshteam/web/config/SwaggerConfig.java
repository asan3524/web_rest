package com.ddshteam.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.ddshteam.web.controller.ExceptionHandleController;
import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	/**
     * SpringBoot默认已映射classpath:/META-INF/resources/和classpath:/META-INF/resources/webjars/
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
	
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                .allowCredentials(false).maxAge(3600);
    }

	
	@Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                @SuppressWarnings("deprecation")
				Class<?> declaringClass = input.declaringClass();
                if (declaringClass == ExceptionHandleController.class)
                    return false;
                if(declaringClass.isAnnotationPresent(RestController.class)) // 注解的类
                    return true;
                if(input.isAnnotatedWith(ResponseBody.class)) // 注解的方法
                    return true;
                return false;
            }
        };
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.groupName("web")
                .useDefaultResponseMessages(false)
                .select()
                .apis(predicate) //RequestHandlerSelectors.basePackage("whayer.cloud.storage.service.web.wcpm")
                .build();
    }
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("ddshteam.web API")//大标题
            .version("1.0.0")//版本
            //.termsOfServiceUrl("接口路径: 192.168.40.24:8090/")
            .contact(new Contact("duyu", "", ""))
            //.license("The Apache License, Version 2.0")
            //.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .build();
    }
}
