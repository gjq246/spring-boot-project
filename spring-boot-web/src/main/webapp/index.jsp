<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<title>心榕网上商城管理系统</title>
<meta name="keywords" content="福建心榕心理咨询有限公司,心榕网上商城管理系统管理系统">
<meta name="description" content="福建心榕心理咨询有限公司,心榕网上商城管理系统管理系统">

<jsp:include page="/inc.jsp" />

</head>

<body class="gray-bg" ng-app="Thesis">
	
	<div class="middle-box text-center loginscreen  animated fadeInDown">
		<div>
			<div style="margin-top:100px;">

				<h1 class="logo-name">
					<span> <img src="images/logo.png" />
					</span>
				</h1>

			</div>
			

			<form class="m-t" role="form" action="" ng-controller="loginCtrl" ng-keypress="($event.which === 13)?login():0" ng-init="init()">
				<div class="form-group">
					<div class="input-group m-b">
						<span class="input-group-addon">用户类型：</span> <select
							class="form-control" ng-model="user.userType">
							<option value="咨询师">咨询师</option>
							<option value="管理员">管理员</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group m-b">
						<span class="input-group-addon">用户名：</span> <input type="text"
							class="form-control" placeholder="用户名" ng-model="user.userName" required="请填写用户名">
					</div>
				</div>
			

				<div class="form-group">
					<div class="input-group m-b">
						<span class="input-group-addon">密　码：</span> <input type="password"
							class="form-control" placeholder="密码" ng-model="user.userPassword" required="请填写密码">
					</div>
				</div>
				<div class="form-group">
					<div class="input-group m-b">
						<span class="input-group-addon">验证码：</span> <input type="text"
							class="form-control" placeholder="验证码" ng-model="user.kaptcha" required="请填写验证码">
							<span style="padding:1px;" class="input-group-addon"><img ng-src="{{captchaurl}}" id="kaptchaImage" style="height:30px;vertical-align:middle;" ng-click="changeCaptcha()" title="点击刷新"  alt="点击刷新" ></span>
					</div>
				</div>
				<div class="form-group">
					<div id="over" class="loadingover"></div>
					<div id="layout" class="loadinglayout">
						<img src="<%=contextPath%>/images/shishiloading.gif" />
					</div>
				</div>
				<button type="button" class="btn btn-primary block full-width m-b" ng-click="login()">登录</button>

			</form>
		</div>
	</div>


	<jsp:include page="/incjs.jsp" />
	<!-- Angularjs Custom -->
    <script src="<%=contextPath%>/jslib/app/controller/loginController.js"></script>
	<script src="<%=contextPath%>/jslib/app/service/loginService.js"></script>
	<script src="<%=contextPath%>/jslib/app/service/departmentworkflowService.js"></script>
</body>
</html>
