package com.kpttech.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.kpttech.mapper.TuserMapper;
import com.kpttech.pojo.Tuser;

public class UserService extends BaseService<Tuser> {
	
	private static final Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	private TuserMapper tuserMapper;
	
	

}
