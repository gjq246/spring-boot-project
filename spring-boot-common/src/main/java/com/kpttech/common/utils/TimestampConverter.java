package com.kpttech.common.utils;

/**
 * 默认情况下如果接受前台数据的Vo类或者Dto类中有Timestamp类型属性,
 * 那么请求时SpringMVC会直接返回400代码,而不会执行Controller方法.
 * 这是因为无法解析参数导致的.这种情况下我们需要自定义转换器.
 */
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class TimestampConverter implements Converter<String, Timestamp> {

	@Override
	public Timestamp convert(String arg0) {
		// TODO Auto-generated method stub
		if (StringUtils.isNotEmpty(arg0.trim())) {
			Timestamp timestamp = Timestamp.valueOf(arg0);
			return timestamp;
		}
		return null;
	}
}
