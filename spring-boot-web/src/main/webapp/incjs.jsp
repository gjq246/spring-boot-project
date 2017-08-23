<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>
<!-- Mainly scripts -->
    <script src="<%=contextPath%>/jslib/jquery-2.1.1.min.js"></script>
    <script src="<%=contextPath%>/jslib/bootstrap.min.js?v=3.4.0"></script>
    <script src="<%=contextPath%>/jslib/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="<%=contextPath%>/jslib/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    
    <script src="<%=contextPath%>/jslib/plugins/jquery-validation-1.14.0/dist/jquery.validate.js"></script>
    <script src="<%=contextPath%>/jslib/plugins/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script src="<%=contextPath%>/jslib/plugins/datatables-1.10.9/media/js/jquery.dataTables.min.js"></script>
    <script src="<%=contextPath%>/jslib/plugins/datatables-1.10.9/extensions/Responsive/js/dataTables.responsive.min.js"></script>
    

    <!-- Custom and plugin javascript -->
    <script src="<%=contextPath%>/jslib/hplus.js?v=2.2.0"></script>
    <script src="<%=contextPath%>/jslib/plugins/pace/pace.min.js"></script>
    <script type="text/javascript">
    //解决ng-file-upload进度条问题
    XMLHttpRequest.prototype = Object.getPrototypeOf(new XMLHttpRequest);
    </script>
    
    <script src="<%=contextPath%>/jslib/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="<%=contextPath%>/jslib/plugins/toastr-master/build/toastr.min.js"></script>
    <!-- jQuery Validation plugin javascript-->
    <script src="<%=contextPath%>/jslib/plugins/validate/jquery.validate.min.js"></script>
    <script src="<%=contextPath%>/jslib/plugins/validate/messages_zh.min.js"></script>
    
    <script type="text/javascript" src="<%=contextPath%>/jslib/plugins/bootstrapvalidator/dist/js/bootstrapValidator.js"></script>

   <script type="text/javascript" src="<%=contextPath%>/jslib/plugins/bootstrapvalidator/src/js/language/zh_CN.js"></script> 
    <!--
    <script src="<%=contextPath%>/jslib/plugins/bootstrapvalidator-master/dist/js/bootstrapValidator.min.js"></script>
    <script src="<%=contextPath%>/jslib/plugins/bootstrapvalidator-master/dist/js/language/zh_CN.js"></script>
     -->
    
    <script	src="<%=contextPath%>/jslib/plugins/jquery-plugin-pagination-zh/pagination_zh/lib/jquery.pagination.js"></script>
    
    <!-- Angularjs Base -->
    <script src="<%=contextPath%>/jslib/plugins/ngfileupload/ng-file-upload-shim.min.js"></script>
    <script src="<%=contextPath%>/jslib/angular/angular.min.js"></script>
    <script src="<%=contextPath%>/jslib/plugins/angular-cookies.min.js"></script>
    <script src="<%=contextPath%>/jslib/ui-bootstrap-tpls-0.13.3.min.js"></script>
    <script src="<%=contextPath%>/jslib/plugins/ngfileupload/ng-file-upload.min.js"></script>
    <script src="<%=contextPath%>/jslib/app/app.js"></script>
    <script src="<%=contextPath%>/jslib/app/directive.js"></script>
    <script src="<%=contextPath%>/jslib/app/filter.js"></script>

