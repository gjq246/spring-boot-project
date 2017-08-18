package com.kpttech.common.utils;

import java.util.UUID;

/**
 * UUID工具类
 * 
 * @author 高建清
 * 
 */
public class UUIDUtil {
	/**
	 * 获取UUID
	 * @return
	 */
	public static String gernerateUUID(){
		
//		return UUID.randomUUID().toString().replace("-", "");
		 UUID uuid = UUID.randomUUID();
		 String struuid=uuid.toString();
		 
		 return struuid.replaceAll("-", "");
	}

}
