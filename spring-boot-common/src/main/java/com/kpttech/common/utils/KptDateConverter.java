package com.kpttech.common.utils;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class KptDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String arg0) {
		// TODO Auto-generated method stub
		if (StringUtils.isNotEmpty(arg0.trim())) {
			Timestamp timestamp = Timestamp.valueOf(arg0);
			return timestamp;
		}
		return null;
	}

}
