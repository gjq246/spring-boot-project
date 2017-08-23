<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>心榕网上商城管理系统</title>
    <meta name="keywords" content="福建心榕心理咨询有限公司,心榕网上商城管理系统">
    <meta name="description" content="福建心榕心理咨询有限公司,心榕网上商城管理系统">

    <jsp:include page="/inc.jsp"/>

</head>

<body ng-app="Thesis" ng-controller="indexCtrl" ng-init="init()">
    <div id="wrapper">
               
        <!-- 左侧菜单 -->
        <jsp:include page="/menu.jsp"/>

        <div id="page-wrapper" class="gray-bg dashbard-1">
        
            <!-- 头部导航 -->
            <jsp:include page="/head.jsp"/>
            
            <div class="row">
            
            <!-- 内容部分 -->
            <div class="row wrapper border-bottom white-bg page-heading" style="padding:10px;margin-left:0px;">
                <div class="col-lg-10">
                    <ol class="breadcrumb">
                        <li>
                            <a href="index.jsp">主页</a>
                        </li>
                        
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
            
                <div class="col-lg-12"><!-- start col-lg-12 -->                    
                    
                                        
                    <div class="wrapper wrapper-content">
                      
                            
                    </div>
                    
                
                </div><!-- end col-lg-12 --> 
                
                
             
                </div>
                <!-- 底部信息 -->
                <jsp:include page="/footer.jsp"/>
            </div>

        </div>
    </div>

    
    <jsp:include page="/incjs.jsp"/>
    <!-- Angularjs Custom -->
    <script src="<%=contextPath%>/jslib/app/service/collegeService.js"></script>
    <script src="<%=contextPath%>/jslib/app/service/departmentService.js"></script>
    <script src="<%=contextPath%>/jslib/app/controller/indexController.js"></script>
    <script src="<%=contextPath%>/jslib/app/service/downloadService.js"></script>
    <script src="<%=contextPath%>/jslib/app/service/noticeService.js"></script>
</body>

</html>