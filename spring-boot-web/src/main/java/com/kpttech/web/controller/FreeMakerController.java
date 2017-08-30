package com.kpttech.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * FreeMaker适合内容发布型，对SEO有帮助，如果有强交互则应该采用JSON方式传输数据
 * @author GJQ
 *
 */
@Controller
public class FreeMakerController {
	
	//测试地址：http://localhost:8002/index
	@RequestMapping("/index")  
    public String hello(HttpServletRequest request,Map<String,Object> map){
	   String name=request.getParameter("name");
	   if(name!=null && StringUtils.isNotEmpty(name)){
		   map.put("name", name+":您好！");  
	   }else{
		   map.put("name", "匿名:您好！");  
	   }
	   
	   map.put("gender",1);//gender:性别，1：男；0：女；  
       
       List<Map<String,Object>> friends =new ArrayList<Map<String,Object>>();  
       Map<String,Object> friend = new HashMap<String,Object>();  
       friend.put("name", "张三");  
       friend.put("age", 20);  
       friends.add(friend);  
       friend = new HashMap<String,Object>();  
       friend.put("name", "李四");  
       friend.put("age", 22);  
       friends.add(friend);  
       map.put("friends", friends); 
       
       return "index";  
    } 

}
