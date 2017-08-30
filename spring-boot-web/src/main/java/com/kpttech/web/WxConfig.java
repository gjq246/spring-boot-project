package com.kpttech.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//加上注释@Component，可以直接其他地方使用@Autowired来创建其实例对象  
@Component  
@ConfigurationProperties(prefix = "wx")   
public class WxConfig
{

	private String appID;
	private String appsecret;
	
	private String returnUrl;//授权后要跳回的页面地址

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
	
	
}