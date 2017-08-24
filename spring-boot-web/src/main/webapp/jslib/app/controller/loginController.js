angular.module('Thesis')
    .controller('loginCtrl', ['$scope', '$rootScope','$timeout', 'Configure','$cookieStore', 'locals', function ($scope, $rootScope,$timeout, Configure,$cookieStore,locals) {
        
    	// menu对象用来跟action做模型映射,可以从pageModel中的属性复制过来,加上默认值，注意类型,ids专门用来删除（主键逗号隔开）
    	$scope.user={userName:'',userPassword:'',userType:'学生',kaptcha:'',captchaguid:''};
    	$scope.belongroleList=[];//所属数据角色List
    	$scope.dataroleKey="";//默认所属的数据
    	
    	$scope.captchaurl="";
    	
    	$scope.init=function () {
    		
    		$scope.changeCaptcha();
    		
    		var u = locals.getObject("loginUser");
    		//log(u);
    		if(!$.isEmptyObject(u)){
    			//log(11);
    			$scope.user.userName = u.userName;
    			$scope.user.userType = u.userType;
    		}else{
    			$scope.user.userType = '管理员';
    		}
    		
    	};
    	
    	$scope.changeCaptcha=function(){
    		var captchaguid = new GUID();
    		$scope.user.captchaguid=captchaguid.newGUID();
    		$scope.captchaurl=Configure.URLBase + "doNotNeedSession/validcode.action?captchaguid="+$scope.user.captchaguid+"&r="+ Math.floor(Math.random()*100);

    	}
    	
        $scope.login=function () {
        	$("#over").css('display', 'block');
            $("#layout").css('display', 'block');
          // log($scope.user);
//        	loginService.login($scope.user).then(function (data) {   
//        		if(data.success){
//        			//本地存储，可以共享数据
//        			$scope.user.userPassword="";
//        		   locals.setObject("loginUser",$scope.user);
//        		   locals.setObject("sessionInfo",data.obj);
//        		   //cookie存放token，可以去服务器获取最新完整信息
//                   $cookieStore.put("token",data.obj.token);
//           				
//           				$("#over").css('display', 'none');
//	                    $("#layout").css('display', 'none');
//	           			window.location.href=Configure.URLBase + "admin/index.jsp";
//        	    }else{
//        	    	$("#over").css('display', 'none');
//                    $("#layout").css('display', 'none');
//        	       alert(data.msg);
//        	    }
//                // log($cookieStore.get("token"));
//        		$scope.changeCaptcha();
//        		$scope.user.kaptcha='';
//            });
            $("#over").css('display', 'none');
            $("#layout").css('display', 'none');
        	
        	
        };     
        
    }]);