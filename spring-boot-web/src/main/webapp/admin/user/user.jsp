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

<body ng-app="Thesis" ng-controller="userCtrl" ng-init="initDataTable()">
    <div id="wrapper">
               
        <!-- 左侧菜单 -->
        <%-- <jsp:include page="/menu.jsp"/> --%>

        <div id="page-wrapper" class="gray-bg dashbard-1">
        
            <!-- 头部导航 -->
           <%--  <jsp:include page="/head.jsp"/> --%>
            
            <div class="row">
            
	            <!-- 内容开始-->
				<div class="row wrapper border-bottom white-bg page-heading" style="padding:10px;margin-left:0px;">
	                <div class="col-lg-10">
	                    <ol class="breadcrumb">
	                        <li>
	                            <a href="../index.jsp">主页</a>
	                        </li>
	                        <li>
	                            <a>会员管理</a>
	                        </li>
	                        <li>
	                            <strong>用户管理</strong>
	                        </li>
	                    </ol>
	                </div>
	                <div class="col-lg-2">
	
	                </div>
	            </div>
            
	            <div class="wrapper wrapper-content animated fadeInRight">
	                <div class="row">
	                    <div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                             <div class="ibox-title">
	                                <h5>用户管理管理<small></small></h5>
	                                <div class="ibox-tools">
	                                    <a class="collapse-link">
	                                        <i class="fa fa-chevron-up"></i>
	                                    </a>
	                                </div>
	                            </div>
	                            <div class="ibox-content" >
									<div>
		                           		<div style="float:left">
		                           			<button id="btn_add" class="btn btn-primary btn-xs" ng-click="adduser()"><i class="fa fa-plus"></i>&nbsp;添加</button> 
		           							<button id="btn_edit" class="btn btn-primary btn-xs" ng-click="edituser()"><i class="fa fa-pencil"></i>&nbsp;查看与修改</button>
		           							<button id="btn_del" class="btn btn-primary btn-xs" ng-click="removeuser()"><i class="fa fa-remove"></i>&nbsp;删除</button>
		           							<button id="btn_edit" class="btn btn-primary btn-xs" ng-click="modifyPwd()"><i class="fa fa-user"></i>&nbsp;修改密码</button>
		           							<button id="btn_info" class="btn btn-primary btn-xs" ng-click="info()"><i class="fa fa-th-large"></i>&nbsp;查看详情</button>
		           							<button id="btn_creview" class="btn btn-primary btn-xs" ng-click="reviewModal()"><i class="fa  fa-wrench"></i>&nbsp;审核</button>
	            							<button id="btn_plreview" class="btn btn-primary btn-xs" ng-click="lotReviewModal()"><i class="fa  fa-wrench"></i>&nbsp;批量审核</button>
		           							<!-- <button class="btn btn-primary btn-xs"><i class="fa fa-search"></i>&nbsp;高级搜索</button> -->
		                           		</div>
		                           		<div style="float:right">
		                           			<label>搜索：
		           							<select id="searchCol" ng-model="usersearchKey">
		           							<!-- value为查找字段名称 -->
		           							<option value="cusername">姓名</option>
		           							</select>
		           							</label>
		           							<input id="keyuser" type="search" class="" ng-model="usersearchValue" placeholder="关键字" />
		           							<button id="btn_query" class="btn btn-primary btn-xs" ng-click="refreshuserData()">搜索</button>
		           							
		                           		</div>
		                            </div>	
									    
									<table class="display responsive" id="userDataTable">
	                                       <thead>
						                    <tr>
						                        <th style="padding-left:10px;padding-right:0;width:10px;"><input type="checkbox" id="cb_selectAll" ng-click="checkAll()"/></th>
						                        <th>用户名</th>
						                        <th>部门名称</th>
						                        <th>添加时间</th>
						                    </tr>
						                </thead>
	                                 </table>
		                        </div>
		                    </div>
		                </div>
		            </div>
	            </div>
	            <!-- 内容结束 -->
            
            
                     
                <!-- 底部信息 -->
                <jsp:include page="/footer.jsp"/>
            </div>

        </div>
    </div>
 
 	<!-- 模态窗口0:add -->
    <div class="modal fade" data-backdrop="static" id="userModal" tabindex="-1" role="dialog"  aria-hidden="true">
	    <div class="modal-dialog modal-lg">
	         <div class="modal-content">
	             <div class="modal-header">
	                 <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                 <h2 class="modal-title">{{modalTitle}}</h2>
	             </div>
                 <div class="modal-body">			                                            
                     <div class="row">			                                            
			                                            
                            <!-- 表单布局 -->
                 			<form id="userForm">
							    <div class="col-md-12">
							    	<div class="col-md-6">
								        <label class="col-sm-4 control-label"><font color="#FF0000">*</font>用户名：</label>
								        <div class="col-sm-8">
								            <input class="form-control" type="text" name="cusername" id="cusername" 
								            		placeholder="请输入用户名" ng-model="user.cusername"/>
								            <span class="help-block m-b-none"></span>
								        </div>
								        <label class="col-sm-4 control-label">真实姓名:</label>
								        <div class="col-sm-8">
								            <input class="form-control" type="text" name="userName" id="userName" 
								            		placeholder="请输入姓名" ng-model="user.userName"/>
								            <span class="help-block m-b-none"></span>
							            </div>
							            <label class="col-sm-4 control-label">电话号码:</label>
								        <div class="col-sm-8">
								            <input class="form-control" type="tel" name="userPhone" id="userPhone" 
								            		placeholder="请输入电话号码" ng-model="user.userPhone"/>
								            <span class="help-block m-b-none"></span>
							            </div>
						            </div>
						            <div class="col-md-6">
                                    	<label class="col-sm-4 control-label">个人头像：</label>
                                     	<div class="col-sm-1" style="height:100px;">&nbsp;</div>
							        	<div class="col-sm-5" style="height:100px;border:0.3px solid #E0E0E0;" id="photo"></div> 
							        	<!--  <div class="col-sm-2" style="height:100px;"><button ngf-select="uploadphoto($file)">上传 </button></div>-->
                                    </div>
							    </div>
							    <div class="col-md-12">
								    <div class="col-md-6">
								    	<label class="col-sm-4 control-label">密码：</label>
								    	<div class="col-sm-8">
								            <input class="form-control" type="password" name="userPwd" id="userPwd"
								            		placeholder="请输入密码" ng-model="user.userPwd" ng-disabled="pwdenable"/>
								            <span class="help-block m-b-none"></span>
								        </div>
							        </div>
								    <div class="col-md-6">
								        <label class="col-sm-4 control-label">确认密码:</label>
								        <div class="col-sm-8">
								            <input class="form-control" type="password" name="userRepwd" id="userRepwd"
								            		placeholder="请再次输入密码" ng-model="user.userRepwd" ng-disabled="pwdenable"/>
								            <span class="help-block m-b-none"></span>
							            </div>
							        </div>
							    </div>
							    <div class="col-md-12">
								    <div class="col-md-6">
								    	<label class="col-sm-4 control-label">性别：</label>
								    	<div class="col-sm-8">
								            <select class="form-control" ng-model="user.userSex" >
								            	<option value="男">男</option>
								            	<option value="女">女</option>
								            </select>
								            <span class="help-block m-b-none"></span>
								        </div>
								     </div>
								     <div class="col-md-6">
								        <label class="col-sm-4 control-label">年龄:</label>
								        <div class="col-sm-8">
								            <input class="form-control" type="text" 
								            		placeholder="请输入年龄" ng-model="user.userAge"/>
								            <span class="help-block m-b-none"></span>
							            </div>
							         </div>
							    </div>
							    <div class="col-md-12">
							    	<div class="col-md-6">
							    		<label class="col-sm-4 control-label">邮箱：</label>
							    		<div class="col-sm-8">
								            <input class="form-control" type="email" 
								            		placeholder="请输入邮箱" ng-model="user.userMail"/>
								            <span class="help-block m-b-none"></span>
								        </div>
								    </div>
								    <div class="col-md-6">
								        <label class="col-sm-4 control-label">QQ:</label>
								        <div class="col-sm-8">
								            <input class="form-control" type="text"
								            		placeholder="请输入QQ号" ng-model="user.userQq"/>
								            <span class="help-block m-b-none"></span>
							            </div>
							        </div>
							    </div>
							    
						</form>													
						<!-- 表单布局结束 -->
						
                    </div>			                                            
                </div>			
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" ng-click="saveuser()">保存</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 模态窗口结束 -->
    
     	<!-- 模态窗口1:info -->
    <div class="modal fade" data-backdrop="static" id="infoModal" tabindex="-1" role="dialog"  aria-hidden="true">
	    <div class="modal-dialog modal-lg">
	         <div class="modal-content">
	             <div class="modal-header">
	                 <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                 <h2 class="modal-title">{{modalTitle}}</h2>
	             </div>
                 <div class="modal-body">			                                            
                     <div class="row">			                                            
			                                            
                            <!-- 表单布局 -->
                 			<form id="infoForm">
							    <div class="col-md-12">
							    	<div class="col-md-6">
								        <label class="col-sm-4 control-label"><font color="#FF0000">*</font>用户名：</label>
								        <div class="col-sm-8">
								            <input class="form-control" type="text" name="userUserName" id="infoUsername" 
								            		readOnly ng-model="user.userUsername"/>
								            <span class="help-block m-b-none"></span>
								        </div>
								        <label class="col-sm-4 control-label"><font color="#FF0000">*</font>姓名:</label>
								        <div class="col-sm-8">
								            <input class="form-control" type="text" name="userName" id="infoName" 
								            		readOnly ng-model="user.userName"/>
								            <span class="help-block m-b-none"></span>
							            </div>
							            <label class="col-sm-4 control-label"><font color="#FF0000">*</font>电话号码:</label>
								        <div class="col-sm-8">
								            <input class="form-control" type="text" name="userPhone" id="infoPhone" 
								            		readOnly ng-model="user.userPhone"/>
								            <span class="help-block m-b-none"></span>
							            </div>
						            </div>
						            <div class="col-md-6">
                                    	<label class="col-sm-4 control-label"><font color="#FF0000">*</font>个人头像：</label>
                                     	<div class="col-sm-1" style="height:100px;">&nbsp;</div>
							        	<div class="col-sm-5" style="height:100px;border:0.3px solid #E0E0E0;" id="infophoto"></div> 
							        	<!-- <div class="col-sm-4" style="height:100px;"><button ngf-select="uploadphoto($file)">上传 </button></div> -->
                                    </div>
							    </div>
							    
							    <div class="col-md-12">
								    <div class="col-md-6">
								    	<label class="col-sm-4 control-label">性别：</label>
								    	<div class="col-sm-8">
								            <input class="form-control" ng-model="user.userSex" 
								            		readOnly>
								            <span class="help-block m-b-none"></span>
								        </div>
								     </div>
								     
							         <div class="col-md-6">
							    		<label class="col-sm-4 control-label">邮箱：</label>
							    		<div class="col-sm-8">
								            <input class="form-control" type="email" 
								            		readOnly ng-model="user.userMail"/>
								            <span class="help-block m-b-none"></span>
								        </div>
								    </div>
							    </div>
							    
							    <div class="col-md-12">
							    	<div class="col-md-6">
								        <label class="col-sm-4 control-label">年龄:</label>
								        <div class="col-sm-8">
								            <input class="form-control" type="text" ng-model="user.userAge" 
								            		readOnly/>
								            <span class="help-block m-b-none"></span>
							            </div>
							         </div>
								    <div class="col-md-6">
								        <label class="col-sm-4 control-label">QQ:</label>
								        <div class="col-sm-8">
								            <input class="form-control" type="text"
								            		readOnly ng-model="user.userQq"/>
								            <span class="help-block m-b-none"></span>
							            </div>
							        </div>
							    </div>
							    
							    <div class="col-md-12">
							    	<div class="col-md-6">
							    		<label class="col-sm-4 control-label">分销等级：</label>
							    		<div class="col-sm-8">
								            <input class="form-control" type="text" 
								            		readOnly ng-model="user.userDistributionrank"/>
								            <span class="help-block m-b-none"></span>
								        </div>
								    </div>
								    <div class="col-md-6">
								        <label class="col-sm-4 control-label">上级分销用户名:</label>
								        <div class="col-sm-8">
								            <input class="form-control" type="text"
								            		readOnly ng-model="user.userParentname"/>
								            <span class="help-block m-b-none"></span>
							            </div>
							        </div>
							    </div>
							    
							    <div class="col-md-12">
							    	<div class="col-md-6">
							    		<label class="col-sm-4 control-label">微信昵称：</label>
							    		<div class="col-sm-8">
								            <input class="form-control" type="text" 
								            		readOnly ng-model="user.userWxnickname"/>
								            <span class="help-block m-b-none"></span>
								        </div>
							    	</div>
							    	<div class="col-md-6">
							    		<label class="col-sm-4 control-label">省份：</label>
							    		<div class="col-sm-8">
								            <input class="form-control" type="text" 
								            		readOnly ng-model="user.userWxprovince"/>
								            <span class="help-block m-b-none"></span>
								        </div>
							    	</div>
							    </div>
							    
							    <div class="col-md-12">
							    	<div class="col-md-6">
							    		<label class="col-sm-4 control-label">国家：</label>
							    		<div class="col-sm-8">
								            <input class="form-control" type="text" 
								            		readOnly ng-model="user.userWxcountry"/>
								            <span class="help-block m-b-none"></span>
								        </div>
							    	</div>
							    	<div class="col-md-6">
							    		<label class="col-sm-4 control-label">城市：</label>
							    		<div class="col-sm-8">
								            <input class="form-control" type="text" 
								            		readOnly ng-model="user.userWxcity"/>
								            <span class="help-block m-b-none"></span>
								        </div>
							    	</div>
							    </div>
							    
							    <div class="col-md-12">
							    	<div class="col-md-6">
							    		<label class="col-sm-4 control-label">特权信息：</label>
							    		<div class="col-sm-8">
								            <input class="form-control" type="text" 
								            		readOnly ng-model="user.userWxprivilege"/>
								            <span class="help-block m-b-none"></span>
								        </div>
							    	</div>
							    	<div class="col-md-6">
							    		<label class="col-sm-4 control-label">公众号id：</label>
							    		<div class="col-sm-8">
								            <input class="form-control" type="text" 
								            		readOnly ng-model="user.userWxunionid"/>
								            <span class="help-block m-b-none"></span>
								        </div>
							    	</div>
							    </div>
							    
							    <div class="col-md-12">
							    	<div class="col-md-6">
								    	<label class="col-sm-4 control-label">备注:</label>
								    	<div class="col-sm-8">
								    		<textarea type="text" rows="4" class="form-control" 
								    					readOnly ng-model="user.userRemark"></textarea>
								    		<span class="help-block m-b-none"></span>
								    	</div>
								    </div>
							    </div>
						</form>													
						<!-- 表单布局结束 -->
						
                    </div>			                                            
                </div>			
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" ng-click="saveuser()">保存</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 模态窗口结束-info -->
    
    <!-- 模态窗口2:修改密码 -->
	<div class="modal fade" data-backdrop="static" id="user_modifyPwdModal" 
					tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<h2 class="modal-title">修改密码</h2>
				</div>
				<div class="modal-body">
					<div class="row">

						<!-- 表单布局 -->
						<form id="user_modifyPwdForm">
							<div class="col-md-12">
								<div class="form-group">																	
									<label class="col-sm-3 control-label">输入密码：</label>
									<div class="col-sm-9">
										<input id="userpwd" class="form-control" type="password" name="userpwd"
											placeholder="请输入密码" ng-model="userPwd.pwd">
										<span class="help-block m-b-none"></span>
									</div>
								</div>
																
								<div class="form-group">
									<label class="col-sm-3 control-label">确认密码：</label>
									<div class="col-sm-9">
										<input id="userrepwd" class="form-control" type="password" name="userrepwd"
											placeholder="确认密码" ng-model="userPwd.repwd">
										<span class="help-block m-b-none"></span>
									</div>																	
								</div>														
											
							</div>
						</form>
						<!-- 表单布局结束 -->	
						
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white"
							data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
							ng-click="saveUserPwd()">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态窗口结束 --> 
	
	<!-- 模态窗口：审核 -->
	<div class="modal fade" data-backdrop="static" id="reviewModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span
							class="sr-only">Close</span>
					</button>
					<h2 class="modal-title">审核</h2>
				</div>
				<div class="modal-body">
					<div class="row">

						<!-- 表单布局 -->
						<form id="lotReviewForm">
							<div class="row">
								<div class="col-md-6">
									<label class="col-sm-3 control-label">是否通过：</label>
									<div class="col-sm-9">
										<select ng-model="user.userState" class="form-control">
											<option value="通过">通过</option>
											<option value="不通过">不通过</option>
										</select>
										<span class="help-block m-b-none"></span>
									</div>
								</div>
								
							</div>
						</form>
						<!-- 表单布局结束 -->
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white"
						data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						ng-click="review()">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态窗口结束 -->
	
	<!-- 模态窗口：批量审核 -->
	<div class="modal fade" data-backdrop="static" id="lotReviewModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span
							class="sr-only">Close</span>
					</button>
					<h2 class="modal-title">批量审核</h2>
				</div>
				<div class="modal-body">
					<div class="row">

						<!-- 表单布局 -->
						<form id="lotReviewForm">
							<div class="row">
								<div class="col-md-6">
									<label class="col-sm-3 control-label">是否通过：</label>
									<div class="col-sm-9">
										<select ng-model="user.userState" class="form-control">
											<option value="通过">通过</option>
											<option value="不通过">不通过</option>
										</select>
										<span class="help-block m-b-none"></span>
									</div>
								</div>
								
							</div>
						</form>
						<!-- 表单布局结束 -->
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white"
						data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						ng-click="lotReview()">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态窗口结束 -->
    
    <jsp:include page="/incjs.jsp"/>
    <!-- Angularjs Custom -->
    <script src="<%=contextPath%>/jslib/app/controller/userController.js"></script>
	<script src="<%=contextPath%>/jslib/app/service/userService.js"></script>
    
</body>

</html>