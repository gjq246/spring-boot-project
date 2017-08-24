package com.kpttech.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.kpttech.common.utils.KptDateConverter;
import com.kpttech.common.utils.TimestampConverter;

@Configuration
public class PropertiesConfig extends WebMvcConfigurerAdapter {

	private static final Logger logger = Logger.getLogger(PropertiesConfig.class);
	
	@Autowired
	private AdminInterceptor adminInterceptor;

	/* 传入字符串时间转换器 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new TimestampConverter());
		registry.addConverter(new KptDateConverter());
	}

	/* 默认首页 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.jsp");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}

	/*
	 * <!-- 登录拦截器 --> <mvc:interceptors> <mvc:interceptor> <mvc:mapping
	 * path="/**" /> <!-- 管理员登录拦截 --> <mvc:exclude-mapping
	 * path="/doNotNeedSession/**" /> <mvc:exclude-mapping path="/cross/**" />
	 * <bean class="com.xinrong.manager.interceptor.AdminInterceptor"></bean>
	 * </mvc:interceptor> </mvc:interceptors>
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(adminInterceptor).addPathPatterns("/**").excludePathPatterns("/doNotNeedSession/**").excludePathPatterns("/cross/**");
		super.addInterceptors(registry);
	}
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/fileupload/*").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS");
                //.allowCredentials(false).maxAge(3600);
        registry.addMapping("/cross/*").allowedOrigins("*")
        .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS");
        //.allowCredentials(false).maxAge(3600);
    }

}
