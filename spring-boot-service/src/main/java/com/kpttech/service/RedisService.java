package com.kpttech.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisService {

	@Autowired
    private StringRedisTemplate template;

    public  void setKey(String key,String value,long timeout){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value,timeout, TimeUnit.MINUTES);//分钟过期
    }

    public String getValue(String key){
    	try{
    		ValueOperations<String, String> ops = this.template.opsForValue();
            return ops.get(key);
    	}catch (Exception e) {
			// TODO: handle exception
    		return null;
		}
        
    }
}
