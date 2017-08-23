angular.module('Thesis')
    .controller('userCtrl', ['$scope', '$timeout', 'userService','Configure','$cookieStore','Upload', function ($scope, $timeout, userService,Configure,$cookieStore,Upload) {
        
    	$scope.userList = [];
    	$scope.usersearchKey="cusername";//默认查询字段名称
    	$scope.usersearchValue="";
    	$scope.userDataTable={};//表格对象
    	//user对象用来跟action做模型映射,可以从pageModel中的属性复制过来,加上默认值，注意分类,ids专门用来删除（主键逗号隔开）
    	$scope.user = {cid:'', cusername:'', tuseraddtime:'', cdepartmentname:'',ids:''};
    	
    	$scope.modalTitle="";
    	
    	$scope.pwdenable=false;
    	$scope.userPwd={};
    	
        //以下是通用方法
    	//user
        $scope.initDataTable=function () {
        	
        	$scope.userDataTable= $('#userDataTable').DataTable({
        		"processing": true,
                "serverSide": true,
                "ajax": {
 	               url: Configure.URLBase + "user/getdatatable.action",
 	               cat:"post",
 	               data: function (d) { 
 	            	   //添加额外的参数传给服务器
 	            	  d["token"]=$cookieStore.get("token");
 	            	   d[$scope.usersearchKey]=$scope.usersearchValue
 	               }
                },   
                 "language": Configure.Language,
                 "autoWidth": false,
                 "pageLength": Configure.PageLength,
                 "lengthChange": false,
                 "searching": false,
                 "columns": [

 					{"data":"cid","orderable": false,
 						"render": function ( data, type, full, meta ) {
 							//log(full);
 								return '<input type="checkbox" value="'+data+'" />';
 								
 							}
 					},
 					{"data":"cusername","name":"cusername"},
 					{"data":"cdepartmentname","name":"cdepartmentname"},									
 					{"data":"tuseraddtime","name":"tuseraddtime"},
 					//有排序功能必须指定name为字段名称

                  ],
                  "order": [[3, 'desc']]
        	});
        	
        	//初始化验证控件
        	$("#userForm").validate({
                rules: {
//                	userUsername: {
//                		required:true,
//                		minlength: 3,
//                		maxlength: 16,
//                		remote: {
//                            type: "post", 
//                            url: "/manager/user/checkUser.action",       //发送请求的url地址
//                            data: {
//                                username: function() {
//                                    return $("#userUsername").val(); 
//                                }
//                            },
//                            dataType: "html",        //发送的数据类型
//                            dataFilter: function(data, type) { //返回结果
//                                if (data == "true")
//                                    return true;
//                                else
//                                    return false;
//                            }
//                        }
//                	},
                	cusername: "required",
//                	userPwd: {
//                		required:true,
//                		minlength:6,
//                	},
//                	userRepwd: {
//                		required:true,
//                		minlength:6,
//                		equalTo:"#userPwd"
//                	}
                },
                messages: {
                	cusername: "请输入姓名",
//                	userPwd:{
//                		required:"请输入密码",
//                		minlength:"密码长度不能少于6位",
//                	},
//                	userRepwd:{
//                		required:"请输入确认密码",
//                		minlength:"密码长度不能少于6位",
//                		equalTo:"两次密码不一致"
//                	},
                	
                }
            });
        	
        	//console.log($scope.userDataTable);
        };
        
        $scope.refreshuserData = function () {
        	$scope.userDataTable.ajax.reload();//当前页 
        };
        
        $scope.checkAll = function () {        	
            if ($("#cb_selectAll").get(0).checked) {
                $("#userDataTable tbody :checkbox").prop("checked", true);
            } else {
            	$("#userDataTable tbody :checkbox").prop("checked", false);
            }
        };
        
        //---------------------comment end-------------------------------
        
        //---------------------modal start-------------------------------
        // add modal
        $scope.adduser = function () { 
        	
        	$scope.modalTitle="添加用户";
        	$scope.pwdenable=false;
        	// 初始化-模型
        	$scope.user = {cid:'', cusername:'', tuserupdatetime:'2017-08-23 16:03:59', cdepartmentname:'',ids:''};
        	
        	$("#userModal").modal('show');
        };
        
        // edit modal
        $scope.edituser = function () {
        	
        	$scope.modalTitle = "修改信息";
        	$scope.pwdenable=true;
        	
        	// initialize-raw data
        	var checkboxlist=$("#userDataTable tbody :checked");
        	if (checkboxlist.length != 1) {
        		toastr.error("请选择一条记录修改", '选择记录错误!');
        		return;
        	}
        	
        	var temp = {};
        	temp.cid = checkboxlist[0].value;
        	temp.token = $cookieStore.get("token");
        	$("#photo").empty();
        	
        	log(11);
        	//
        	userService.getSingleUser(temp).then(function(data) {
        		log(22);
        		if(data.success)
        		{
        			log(data.obj);
        			angular.copy(data.obj,$scope.user);
        			$scope.user.userRepwd=$scope.user.userPwd;
        			var s="<img alt='' src='"+$scope.user.userPhoto+"' height='99' width='99'>";
        			$("#photo").empty().append(s);
        			if ($scope.user.userAge == 0) 
        			{
        				$scope.user.userAge = '';
        			}
        		}
        	});
        	
        	$("#userModal").modal('show');
        };
        
        // modify-password modal
        $scope.modifyPwd=function () {
        	
        	var checkboxlist=$("#userDataTable tbody :checked");
        	if (checkboxlist.length != 1) {
        		toastr.error("请选择一条记录修改", '选择记录错误!');
        		return;
        	}
        	var id = checkboxlist[0].value;
        	
        	$scope.userPwd.pwd="";
   		    $scope.userPwd.repwd="";
   		    $("#user_modifyPwdModal").modal('show');
        	
        };
        
        // info modal
        $scope.info = function () {
        	
        	$scope.modalTitle="查看详情";
        	
        	//init
        	$scope.user = {userId:'', userUsername:'', userAddtime:'', userName:'', userSex:'男', userAge:'',userState:'',
        			userPwd:'',userRepwd:'',userMail:'', userPhone:'', userQq:'', userPhoto:'', userDistributionrank:'',
        			userParentid:'', userParentname:'', userIsdistribution:0, userWxopenid:'', userWxnickname:'',
        			userWxprovince:'', userWxcity:'', userWxcountry:'', userWxprivilege:'', userWxunionid:'',
        			userAddtime:'', userUpdatetime:'', userRemark:'', ids:'', isdelete:0, userEnable:1};
        	
        	var checkboxlist=$("#userDataTable tbody :checked");
        	if(checkboxlist.length!=1){
        		toastr.error("请选择一条记录修改", '选择记录错误!');
        		return;
        	}
        	var id=checkboxlist[0].value;
        	//
        	var temp = {};
        	temp.userId = checkboxlist[0].value;
        	temp.token = $cookieStore.get("token");
        	$("#infophoto").empty();
        	//
        	userService.getSingleUser(temp).then(function(data){
        		if(data.success)
        		{
        			angular.copy(data.obj,$scope.user);
        			var s="<img alt='' src='"+$scope.user.userPhoto+"' height='99' width='99'>";
        			if ($scope.user.userAge == 0) 
        			{
        				$scope.user.userAge = '';
        			}
        			// 存在parent再次请求数据
                	if ($scope.user.userParentid != '') {
                		var pUser = {};
                    	pUser.userId = $scope.user.userParentid;
                    	pUser.token = $cookieStore.get("token");
                    	userService.getSingleUser(pUser).then(function(data){
                    		if (data.success) 
                    		{
                    			angular.copy(data.obj,pUser);
                    			$scope.user.userParentname = pUser.userName;
                    		}
                    	})
                	}
        		}
        	});
        	
        	$("#infoModal").modal('show');
        };
                
         
        
        //---------------------modal end-------------------------------
        
        //---------------------event start-------------------------------
        // remove event
        $scope.removeuser = function () {
        	
        	//加入ids
        	var checkboxlist=$("#userDataTable tbody :checked");
        	if (checkboxlist.length>0) {
        		if (!confirm("您确定删除数据吗？")) {
            		return;
                }
        	} else {
        		toastr.info("请选择要删除的记录。", '提示!');
        		return;
        	}
        	
        	var ids="";
        	$.each(checkboxlist, function(n, cb) {  
        		ids+=cb.value+",";  
            });
        	if (ids.length>0) {
        	  ids=ids.substring(0,ids.length-1);
        	}
        	$scope.user.ids = ids;
        	
        	userService.remove($scope.user).then(function (data) {  
        		
        		if (data.success) 
        		{
        			toastr.success(data.msg, '删除成功！');
        			$scope.userDataTable.ajax.reload(null, false);//刷新当前页 
        		} else 
        		{
        			toastr.error(data.msg, '删除失败!');
        			
        		}
            });        	
        };
        
        // save event
        $scope.saveuser = function () {  
        	
        	if ($("#userForm").valid()) { 
        		if ($scope.modalTitle=="添加用户") { // do add
                    log($scope.user);
    				userService.add($scope.user).then(function (data) {   	       				
                		if (data.success) {
                			
                			$("#userModal").modal('hide');
                			toastr.success(data.msg, '添加成功！');
                			$scope.userDataTable.ajax.reload();
                			
                		} else {
                			
                			toastr.error(data.msg, '添加失败!');
                			
                		}
                        
                    });
        			
        		} else { // do save (edited)
        			
        			userService.edit($scope.user).then(function (data) {   
        				
        				if (data.success) 
        				{
        					$("#userModal").modal('hide');
                			toastr.success(data.msg, '修改成功！');   
                			$scope.userDataTable.ajax.reload(null, false);
                			
                		} else 
                		{
                			toastr.error(data.msg, '修改失败!');
                		}
                    });
        		}
        	}else{
        		//表单验证不通过，界面有提示这里不处理
        	}        	
        	
        };

        // modify password event
        $scope.saveUserPwd=function () {
	   		 if ($scope.userPwd.pwd == "" || $scope.userPwd.repwd == "")
	   		 {
	   			alert("密码不能为空！"); 
	   			return;
	   		 }
	   		 if ($scope.userPwd.pwd != $scope.userPwd.repwd)
	   		 {
	   			alert("密码不一致！"); 
	    		return;
	   		 }
   		 			
			//读取id加载用户资料，调出模态框
			var user={};
			user.userId = $scope.user.userId;
			user.userPwd = $scope.userPwd.pwd;
			
			userService.modifyPwd(user).then(function(data) {
				if(data.success)
				{
					$("#user_modifyPwdModal").modal('hide');
         			toastr.success(data.msg, '修改成功！'); 
				}else
				{
        			toastr.error(data.msg, '修改失败!');
        		}
			});
   	 	};
   	 	
   	 	// 批量审核
        $scope.lotReview=function(){
      	var temp={};
	       	temp.token=$cookieStore.get("token");
	       	temp.ids = $scope.user.ids;
	       	temp.userState = $scope.user.userState;
	       	userService.lotReview(temp).then(function(data){
	     		   if(data.success)
	     			{
	     			   toastr.success(data.msg, '批量审核成功！'); 
	     			   $scope.userDataTable.ajax.reload();//刷新
	     			}
	     		   else
	     			{
	     			   toastr.error(data.msg, '批量审核失败!');
	     			}
	     	   });
	      $("#lotReviewModal").modal('hide');
       };
       
       //审核
       $scope.review=function () {  
      	var temp={};
	       	temp.token=$cookieStore.get("token");
	       	temp.ids = $scope.user.ids;
	       	temp.userState = $scope.user.userState;
			userService.review(temp).then(function (data) {        				
       		if(data.success){
       			
       			toastr.success(data.msg, '添加成功！');
       			$scope.userDataTable.ajax.reload();//刷新
       		}else{
       			toastr.error(data.msg, '添加失败!');
       		}
       	});
			$("#reviewModal").modal('hide');
       };
        
        //-----------------------------event end-------------------------
        
        //-----------------------------custom start----------------------
        
        /*// get list-user
        $scope.getuserlist = function() { 
        	
        	$scope.userlist = [];
        	
        	var temp = {};
        		temp.token=$cookieStore.get("token");
        	userService.getuserlist(temp).then(function (data) {
        		
        		if (data.success) {
        			$scope.userList = data.obj;
        			//log($scope.userList.length);
        		}
        	});
        }*/
        
        $scope.uploadphoto=function(file) { 
    		if(file==null)
    		{
    			return;
    		}
    		if(file.type.indexOf("image/")<0)
    		{
    			alert("文件格式错误，请选择图片格式的文件上传");
    		}
        	Upload.upload({
        		url: Configure.URLBase + "fileupload/upload.action",
                data: {file: file, fileName:file.name,fileType:file.type,fileSize:file.size}
            }).then(function (resp) {
               //success+
            	
               //service(filepath)
              if(resp.data.success&&resp.data.obj!=null&&resp.data.obj.length>0)
              {
            	  $scope.user.userPhoto=resp.data.obj[0];
            	  var s="<img alt='' src='"+Configure.URLBase+resp.data.obj[0]+"' height='99' width='99'>";
            	  $("#photo").empty().append(s);
              }
              else
              {
            	  alert("上传失败");
              }
               
            }, function (resp) {
               
            }, function (evt) {
               
            });
        };

        
      
        //----------------------custom end--------------------------------
        
        //----------------------test start--------------------------------
        

    }]);