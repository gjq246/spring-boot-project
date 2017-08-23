package com.kpttech.web;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.kpttech.common.utils.KptDateConverter;
import com.kpttech.common.utils.TimestampConverter;

@Configuration
public class PropertiesConfig extends WebMvcConfigurerAdapter {

	private static final Logger logger = Logger.getLogger(PropertiesConfig.class);

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new TimestampConverter());
		registry.addConverter(new KptDateConverter());
	}

}
