package com.kpttech.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import com.kpttech.common.utils.MappingFastJsonHttpMessageConverter;

@MapperScan(basePackages = "com.kpttech.mapper")
@SpringBootApplication(scanBasePackages = "com.kpttech.service,com.kpttech.web.controller")
@ServletComponentScan
public class Application {

	@Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
	   MappingFastJsonHttpMessageConverter fastConverter = new MappingFastJsonHttpMessageConverter();
       HttpMessageConverter<?> converter = fastConverter;
       return new HttpMessageConverters(converter);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Application.class, args);
	}

//安全传输协议

//	@Bean
//	public EmbeddedServletContainerFactory servletContainer() {
//
//		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//
//			@Override
//			protected void postProcessContext(Context context) {
//
//				SecurityConstraint securityConstraint = new SecurityConstraint();
//				securityConstraint.setUserConstraint("CONFIDENTIAL");
//				SecurityCollection collection = new SecurityCollection();
//				collection.addPattern("/*");
//				securityConstraint.addCollection(collection);
//				context.addConstraint(securityConstraint);
//			}
//		};
//		tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
//		return tomcat;
//	}
//
//	private Connector initiateHttpConnector() {
//
//		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//		connector.setScheme("http");
//		connector.setPort(8080);
//		connector.setSecure(false);
//		connector.setRedirectPort(8444);
//		return connector;
//	}

}
