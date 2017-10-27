package com.kpttech.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class KptDateConverter implements Converter<String, Date> {

//	@Override
//	public Date convert(String arg0) {
//		System.out.println("时间："+arg0);
//		// TODO Auto-generated method stub
//		if (StringUtils.isNotEmpty(arg0.trim())) {
//			Timestamp timestamp = Timestamp.valueOf(arg0);
//			return timestamp;
//		}
//		return null;
//	}
//	
	
	 //可以转换的格式,用数组的形式进行存储  
    private static final SimpleDateFormat[] ACCEPT_DATE_FORMATS = {  
        //这个必须放到前面  
        //否则按照年月日时分进行匹配,匹配成功直接转换为0时0分  
        new SimpleDateFormat("yyyy-MM-dd")  
    };  
      
    @Override  
    public Date convert(String dateStr) { 
        //空串不进行处理返回null  
        if(dateStr == "")  
            return null;  
          
        //看是否满足某种格式  
        for(SimpleDateFormat format : ACCEPT_DATE_FORMATS){  
            try{  
                Date tmp = format.parse(dateStr);  
                return tmp;  
            }catch (Exception e) {  
                e.printStackTrace();  
                continue;  
            }  
        }  
          
        //所有格式都不满足返回null  
        return null;  
    }  

}
