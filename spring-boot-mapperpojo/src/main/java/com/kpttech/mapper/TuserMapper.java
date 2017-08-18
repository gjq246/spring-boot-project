package com.kpttech.mapper;

import java.util.List;

import com.kpttech.generator.MyMapper;
import com.kpttech.pagepojo.User;
import com.kpttech.pojo.Tuser;

public interface TuserMapper extends MyMapper<Tuser> {
	
	List<User> getUserList(User user);
}