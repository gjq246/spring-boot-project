package com.kpttech.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kpttech.service.RedisService;

@Component
public class AdminInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(AdminInterceptor.class);

	@Autowired
	private RedisService jedisClient;

	//测试方法
	//1.http://localhost:8002/user/getsingleuser.action?cid=1&token=1
	//2.http://localhost:8002//doNotNeedSession/redistest.action
	//3.http://localhost:8002/user/getsingleuser.action?cid=1&token=1
	// 拦截前处理
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		logger.info("AdminInterceptor>>>>>preHandle");
		String token=request.getParameter("token");		
		if(StringUtils.isEmpty(token) ){
			try {
				String r = "{\"msg\":\"未登录！Token为空！\",\"success\":false,\"islogin\":false}";
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(r);
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
			
		}else{
			logger.info(token);
			String sessionStr = jedisClient.getValue("UserSessionKey:"+token);
			if(StringUtils.isEmpty(sessionStr)){
				try {
					String r = "{\"msg\":\"未登录！Redis为空！\",\"success\":false,\"islogin\":false}";
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write(r);
					response.getWriter().flush();
					response.getWriter().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
				
			}
		}

		return true;
	}

	// 拦截后处理
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav)
			throws Exception {
	}

	// 全部完成后处理
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e)
			throws Exception {
	}
}
