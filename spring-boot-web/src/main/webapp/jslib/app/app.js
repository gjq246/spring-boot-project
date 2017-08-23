//Configure为系统参数
angular.module('Thesis', ['ui.bootstrap','ngFileUpload','ngCookies']).constant('Configure', {
    URLBase:'/',
    uploadUrlBase:'http://127.0.0.1/manager',
    Language:{
        "lengthMenu": "每页 _MENU_ 行",
        "info": "从 _START_ 到 _END_，共 _TOTAL_ 记录",
        "zeroRecords": "没有找到记录",
        "infoEmpty": "暂无记录",
        "infoFiltered": "(从 _MAX_ 条记录过滤)",
        "paginate": {
            "previous": "上一页",
            "next": "下一页",

        },
        "processing": "正在加载..."
    },
    PageLength:15,
    DateTimeConfigure:{
    	 locale: 'zh-cn',
         useCurrent: false, //Important! See issue #1075
         sideBySide: true,
         showTodayButton: true
    }
}).config(function($httpProvider) {
			//解决post数据问题
			$httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
			$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

			// Override $http service's default transformRequest
			$httpProvider.defaults.transformRequest = [function(data) {
				/**
				 * The workhorse; converts an object to x-www-form-urlencoded serialization.
				 * @param {Object} obj
				 * @return {String}
				 */
				var param = function(obj) {
					var query = '';
					var name, value, fullSubName, subName, subValue, innerObj, i;

					for (name in obj) {
						value = obj[name];

						if (value instanceof Array) {
							for (i = 0; i < value.length; ++i) {
								subValue = value[i];
								fullSubName = name + '[' + i + ']';
								innerObj = {};
								innerObj[fullSubName] = subValue;
								query += param(innerObj) + '&';
							}
						} else if (value instanceof Object) {
							for (subName in value) {
								subValue = value[subName];
								fullSubName = name + '[' + subName + ']';
								innerObj = {};
								innerObj[fullSubName] = subValue;
								query += param(innerObj) + '&';
							}
						} else if (value !== undefined && value !== null) {
							query += encodeURIComponent(name) + '='
									+ encodeURIComponent(value) + '&';
						}
					}

					return query.length ? query.substr(0, query.length - 1) : query;
				};

				return angular.isObject(data) && String(data) !== '[object File]'
						? param(data)
						: data;
			}];
		}).factory('locals',['$window',function($window){
			      return{
			          //存储单个属性
			          set :function(key,value){
			            $window.localStorage[key]=value;
			          },
			          //读取单个属性
			          get:function(key,defaultValue){
			            return  $window.localStorage[key] || defaultValue;
			          },
			          //存储对象，以JSON格式存储
			          setObject:function(key,value){
			            $window.localStorage[key]=JSON.stringify(value);
			          },
			          //读取对象
			          getObject: function (key) {
			            return JSON.parse($window.localStorage[key] || '{}');
			          }

			        };
			    }]).factory('myInterceptor', ['$q','Configure', '$cookieStore', function($q,Configure,$cookieStore) {  
			        var responseInterceptor = {
			                response: function(response) {
			                	//log(123456);
			                	//alert("dd");
			                	//undefined
			                	//log(response);
			                	//log(response.data.islogin);
			                	if(response.data.islogin==false){
			                		//超时拦截
			                		//alert(response.data.msg);
			                		//toastr.error(response.data.msg, '加载数据失败!');
			                		location.href=Configure.URLBase + "index.jsp";
			                	}
			                    var deferred = $q.defer();
			                    //someAsyncService.doAsyncOperation().then(function() {
			                        // Asynchronous operation succeeded, modify response accordingly                         ...
			                        deferred.resolve(response);
			                    //}, function() {
			                        // Asynchronous operation failed, modify response accordingly                            ...
			                        //deferred.resolve(response);
			                    //});
			                    return deferred.promise;
			                },
			                request: function(config) {
//			                    if (!SessionService.isAnonymus) {
//			                      config.headers['x-session-token'] = SessionService.token;
//			                    }
			                	//添加token
			                	if($cookieStore.get("token")!=undefined && config.data!=undefined && (config.data["token"]==undefined||config.data["token"]=="")){
			                		//log($cookieStore.get("token"));
			                		//log(config.data["token"]);
			                		config.data["token"] = $cookieStore.get("token");
			                	}	
			                	//log(config);
			                    return config;
			               }

			            };
			         
			            return responseInterceptor;
			           }]).config(['$httpProvider', function($httpProvider) {  
			        	    $httpProvider.interceptors.push('myInterceptor');
			           }]).run(function($rootScope){  
			        	    //定义一个全局函数，跳转到指定的页面  
			        	    $rootScope.stateStyle = function(data){  
			        	        //如果跳转到页面是选择小区,则记录当前页面，方便回退  
			        	    	var tempclass="";   
			    				if(data=="待申报")
			    			   {
			    					tempclass="btn-info";
			    			   }
			    				else if(data=="招募中"||data=="延期")
			    				{
			    					tempclass="btn-primary";
			    				}
			    				else if(data=="待确认")
			    			   {
			    					tempclass="btn-warning";
			    			   }
			    				else if(data=="通过"||data=="同意"||data=="立项")
			    				{
			    					tempclass="btn-success";
			    				}
			    				else if(data=="不通过"||data=="不同意"||data=="招募结束")
			    				{
			    					tempclass="btn-danger";
			    				}
			    				else
			    				{
			    					tempclass="";
			    				}
			    				return "<span class='"+tempclass+"'>"+data+"</span>";
			        	    };  
			        	  
			        	});