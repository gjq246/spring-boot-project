<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String currentPath = request.getRequestURI();
%>

<!--[if lte IE 10]>
				<div id="ie6-warning"><p>本页面采用HTML5+CSS3，您正在使用老版本 Internet Explorer ，在本页面的显示效果可能有差异。建议您升级到Internet Explorer 11或以下浏览器：
				    <br>
				
				<a href="http://browser.qq.com/?adtag=SEM1" target="_blank"><img src="<%=contextPath%>/images/qq.png">QQ浏览器</a> / 
				<a href="http://se.360.cn/" target="_blank"><img src="<%=contextPath%>/images/360.png">360浏览器</a> /
				<a href="http://www.firefox.com.cn/" target="_blank"><img src="<%=contextPath%>/images/firefox.png">火狐浏览器</a> / 
				<a href="http://www.google.cn/chrome/browser/index.html?hl=zh-CN&standalone=1" target="_blank"><img src="<%=contextPath%>/images/chrome.png">谷歌浏览器</a> / 
				<a href="http://windows.microsoft.com/zh-cn/internet-explorer/download-ie" target="_blank"><img src="<%=contextPath%>/images/ie.png">ie浏览器</a> 
				</p></div>
				<style type="text/css">
				/*ie6提示*/
				#ie6-warning{width:100%;background:#ffffe1;padding:5px 0;font-size:12px}
				#ie6-warning p{width:960px;margin:0 auto;}
				  </style>
	<![endif]-->

<nav id="sidebar1"
	class="navbar-default navbar-static-side left-menu-scroll-1"
	role="navigation">
	<div id="sidebar2" class="sidebar-collapse left-menu-scroll-2">

		<div class="nav-header" style="padding:23px 0;">
			<div class="dropdown profile-element">
				<span> <img src="<%=contextPath%>/images/logo_school.png" />
					<a href="<%=contextPath%>/admin/index.jsp"><!-- <img
						src="<%=contextPath%>/images/logo_lw.png" /> --></a>
				</span>
			</div>
			<div class="logo-element">
				<a href="<%=contextPath%>/admin/index.jsp"><img
					src="<%=contextPath%>/images/logo_school_small.png" /></a>
			</div>

		</div>
		<div id="sidebar3"
			class="left-menu-scroll-3 content mCustomScrollbar light"
			data-mcs-theme="minimal-dark" ng-controller="menuCtrl"
			ng-init="initMenu()">
			<ul class="nav" id="side-menu">
				<li ng-repeat="m in menuList track by $index"
					class="{{m.menu.menuUrl | menufilter:'<%=currentPath%>'}}"
					on-repeat-finished-render repeat-id="r1"><a href="#"><i
						class="{{m.menu.menuIconcls}}"></i> <span class="nav-label" ng-bind="m.menu.menuText"></span>
						<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li ng-repeat="submenu in m.submenus"
							class="{{submenu.menuUrl | submenufilter:'<%=currentPath%>'}}"><a
							href="<%=contextPath%>{{submenu.menuUrl}}" ng-bind="submenu.menuText"></a></li>
					</ul></li>

			</ul>
		</div>
	</div>
</nav>

