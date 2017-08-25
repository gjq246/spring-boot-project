<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%

    request.setCharacterEncoding( "utf-8" );

//跨域时需要设置http头信息以返回参数给源地址
response.addHeader("Access-Control-Allow-Origin", "*");
response.addHeader("Access-Control-Allow-Headers", "X-Requested-With,X_Requested_With");

	response.setHeader("Content-Type" , "text/html");
	
	String rootPath = application.getRealPath( "/" );
	
	System.out.println("rootPath:"+application.getRealPath( "/" ));
	System.out.println("contextPath:"+request.getContextPath());
	System.out.println("getRequestURI:"+request.getRequestURI());
					
	/*
	this.request = request;
	this.rootPath = rootPath;
	this.actionType = request.getParameter( "action" );
	this.contextPath = request.getContextPath();
	this.configManager = ConfigManager.getInstance( this.rootPath, this.contextPath, request.getRequestURI() );
	*/
	
	
	out.write( new ActionEnter( request, rootPath ).exec() );
	
%>