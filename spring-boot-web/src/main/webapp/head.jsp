<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

			
			
            <div class="row my-fixed-header " ng-controller="headCtrl" ng-init="initHead()">
                <nav class="navbar navbar-static-top border-bottom" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                        <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                        <span class="" ></span>
                    </div>
                    <div class="navbar-header welcome-message">
			           <h2 style="margin-top: 16px;">心榕网上商城管理系统</h2>
		            </div>
                    <ul class="nav navbar-top-links navbar-right">

                        <li class="dropdown">
                          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">{{user.trueName}}{{user.userRoles}}<span class="caret"></span></a>
                          <ul class="dropdown-menu animated fadeInRight m-t-xs">

                                <li><a href="#" ng-click="edit()">个人资料</a>
                                </li>
                                
                                <li><a href="#" ng-click="modifyPwd()">修改密码</a>
                                </li>


                                <li><a href="#" ng-click="logout()">安全退出</a>
                                </li>
                            </ul>
                        </li>
                        
                        
                    </ul>
                </nav>
            </div>         
   
   									<!-- 模态窗口 -->
									<div class="modal fade" data-backdrop="static"
										id="head_sysuserModal" tabindex="-1" role="dialog"
										aria-hidden="true">
										<div class="modal-dialog modal-lg">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">
														<span aria-hidden="true">&times;</span><span
															class="sr-only">Close</span>
													</button>
													<h2 class="modal-title">修改管理员个人资料</h2>
												</div>
												<div class="modal-body">
													<div class="row">

														<!-- 表单布局 -->
														<form id="head_sysuserForm">
															<div class="col-md-12">
																	<label class="col-sm-2 control-label">用户姓名：</label>
																	<div class="col-sm-4">
																		<input id="syspwd" class="form-control"
																			placeholder="请输入用户真实姓名"  ng-model="head_sysuser.systemuserTruename">
																		<span class="help-block m-b-none"></span>
																	</div>
																	<label class="col-sm-2 control-label">联系电话：</label>
																	<div class="col-sm-4">
																		<input id="resyspwd" class="form-control"
																			placeholder="确认联系电话"  ng-model="head_sysuser.systemuserPhone">
																		<span class="help-block m-b-none"></span>
																	</div>
																</div>
																<div class="col-md-12">
																	<label class="col-sm-2 control-label">QQ：</label>
																	<div class="col-sm-4">
																		<input id="syspwd" class="form-control"
																			placeholder="请输入QQ" ng-model="head_sysuser.systemuserQq">
																		<span class="help-block m-b-none"></span>
																	</div>
																	<label class="col-sm-2 control-label">E-mail：</label>
																	<div class="col-sm-4">
																		<input id="resyspwd" class="form-control"
																			placeholder="请输入E-mail"  ng-model="head_sysuser.systemuserEmail">
																		<span class="help-block m-b-none"></span>
																	</div>
																</div>
																<div class="col-md-12">
																	<label class="col-sm-2 control-label">性别：</label>
																	<div class="col-sm-4">
																		<select class="form-control" ng-model="head_sysuser.systemuserSex">
																			<option value="男">男</option>
																			<option value="女">女</option>
																		</select>
																		<span class="help-block m-b-none"></span>
																	</div>
																	<label class="col-sm-2 control-label">备注：</label>
																	<div class="col-sm-4">
																		<textarea class="form-control" type="text" name="remark"
																			placeholder="请输入系统用户备注" ng-model="head_sysuser.systemuserRemark"></textarea>
																		<span class="help-block m-b-none"></span>
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
														ng-click="saveSysuser()">保存</button>
												</div>
											</div>
										</div>
									</div>
									<!-- 模态窗口结束 -->	
   			
            						
			                            
			 						<!-- 模态窗口 -->
									<div class="modal fade" data-backdrop="static"
										id="head_teacherModal" tabindex="-1" role="dialog"
										aria-hidden="true">
										<div class="modal-dialog modal-lg">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">
														<span aria-hidden="true">&times;</span><span
															class="sr-only">Close</span>
													</button>
													<h2 class="modal-title">修改咨询师个人资料</h2>
												</div>
												<div class="modal-body">
													
													<div class="row">

														<!-- 表单布局 -->
														<form id="head_teacherForm">
																<div class="col-md-12">
														        <label class="col-sm-2 control-label">姓名：</label>
														        <div class="col-sm-4">
														            <input class="form-control" name="teachername" id="teachername" type="text" placeholder="请输入姓名" ng-model="head_teacher.teacherName"> 
														            <span class="help-block m-b-none"></span>
														
														        </div>
														        <label class="col-sm-2 control-label">编号：</label>
														        <div class="col-sm-4">
														            <input class="form-control" name="teacherno" id="teacherno" type="text" placeholder="请输入编号" ng-model="head_teacher.teacherNo"> 
														            <span class="help-block m-b-none"></span>
														
														        </div>
														    </div>
														    
														    <div class="col-md-12">
														     <label class="col-sm-2 control-label">职务：</label>
														        <div class="col-sm-4">
														            <input class="form-control" type="text" placeholder="请输入职务" ng-model="head_teacher.teacherPost">
														             <span class="help-block m-b-none"></span>
														
														        </div>
														        <label class="col-sm-2 control-label">性别：</label>
														        <div class="col-sm-4">
														        	<select class="form-control" ng-model="head_teacher.teacherSex">
														        		<option value="男">男</option>
														        		<option value="女">女</option>
														        	</select>
														             <span class="help-block m-b-none"></span>
														        </div>
														      
														    </div>
														     
														     <div class="col-md-12">
														       <label class="col-sm-2 control-label">学历：</label>
														        <div class="col-sm-4">
														            <input class="form-control" type="text" placeholder="请输入学历" ng-model="head_teacher.teacherEducation">
														             <span class="help-block m-b-none"></span>
														
														        </div>
														     <label class="col-sm-2 control-label">学位：</label>
														        <div class="col-sm-4">
														            <input class="form-control" type="text" name="teacherdegree" id="teacherdegree" placeholder="请输入学位" ng-model="head_teacher.teacherDegree">
														             <span class="help-block m-b-none"></span>
														
														        </div>
														        	
														    </div>
														     <div class="col-md-12">
														     <label class="col-sm-2 control-label">个人简介：</label>
														        <div class="col-sm-4">
														            <textarea class="form-control" rows="3" type="textarea" placeholder="请输入个人简历" ng-model="head_teacher.teacherIntroduce"></textarea>
														             <span class="help-block m-b-none"></span>
														
														        </div>
														        <label class="col-sm-2 control-label">备注：</label>
														        <div class="col-sm-4">
														            <textarea class="form-control"  rows="3" type="textarea" placeholder="请输入备注" ng-model="head_teacher.teacherRemark"></textarea>
														             <span class="help-block m-b-none"></span>
														
														        </div>
														    </div>
														    
														   <div class="col-md-12">
														    <label class="col-sm-2 control-label">擅长领域：</label>
														        <div class="col-sm-4">
														            <textarea class="form-control"  rows="3" type="textarea" placeholder="请输入擅长领域" ng-model="head_teacher.teacherField"></textarea>
														             <span class="help-block m-b-none"></span>
														
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
														ng-click="saveTeacher()">保存</button>
												</div>
											</div>
										</div>
									</div>
									<!-- 模态窗口结束 -->   
									
									
									<!-- 模态窗口 -->
									<div class="modal fade" data-backdrop="static"
										id="head_modifyPwdModal" tabindex="-1" role="dialog"
										aria-hidden="true">
										<div class="modal-dialog modal-md">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">
														<span aria-hidden="true">&times;</span><span
															class="sr-only">Close</span>
													</button>
													<h2 class="modal-title">修改密码</h2>
												</div>
												<div class="modal-body">
													<div class="row">

														<!-- 表单布局 -->
														<form id="head_modifyPwdForm">
															<div class="col-md-12">
															<div class="form-group">																	
																	<label class="col-sm-3 control-label">旧密码：</label>
																	<div class="col-sm-9">
																		<input id="head_pwd" class="form-control" type="password" name="head_pwd"
																			placeholder="请输入旧密码" ng-model="headPwd.jiupwd">
																		<span class="help-block m-b-none"></span>

																	</div>
																</div>
																<div class="form-group">																	
																	<label class="col-sm-3 control-label">新密码：</label>
																	<div class="col-sm-9">
																		<input id="head_pwd" class="form-control" type="password" name="head_pwd"
																			placeholder="请输入密码" ng-model="headPwd.pwd">
																		<span class="help-block m-b-none"></span>

																	</div>
																</div>
																
																<div class="form-group">
																	<label class="col-sm-3 control-label">确认密码：</label>
																	<div class="col-sm-9">
																		<input id="rehead_pwd" class="form-control" type="password" name="rehead_pwd"
																			placeholder="确认密码" ng-model="headPwd.repwd">
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
														ng-click="savePwd()">保存</button>
												</div>
											</div>
										</div>
									</div>
									<!-- 模态窗口结束 -->        
									
									<!-- 模态窗口 -->
									<div class="modal fade" data-backdrop="static"
										id="head_MessageListModal" tabindex="-1" role="dialog"
										aria-hidden="true">
										<div class="modal-dialog modal-lg">
											<div class="modal-content">
												<div class="modal-header">
													
													<h2 class="modal-title">留言信息</h2>
												</div>
												<div class="modal-body">

														<!-- 表单布局 -->
														<form id="head_MessageListForm">
															<div class="row" style="margin-top:10px;">
																			<div class="col-md-12">
																				
																				<div class="panel panel-default">
																				 <div class="panel-body" style="background-color:#F0F0F0;height:480px;overflow: auto;">
																				       <div id="searchResult" class="list-group">不存在留言信息!</div>
																				
																						
																				</div>
																				</div>
																					<div class="col-md-12">
																						<div class="col-sm-1"></div>
																						<div class="col-sm-8"><textarea class="form-control" type="text" rows="3" ng-model="sendmessagedata.sendmessagedata"></textarea></div>
																						<div class="col-sm-3" ><button class="btn btn-primary btn-xs" ng-click="sendMessageData()">发送</button></div>
																					</div>
																						<div id="Pagination" class="pagination">
																							<!-- 这里显示分页 -->
																						</div>
																						
																						<div id="hiddenresult" style="display:none;">
																							<!-- 列表元素 -->
																						</div>
																			</div>
																		</div>
														</form>
														<!-- 表单布局结束 -->	
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-white"
														ng-click="closemodal()">关闭</button>
												</div>
											</div>
										</div>
									</div>
									<!-- 模态窗口结束 -->                       
			                            