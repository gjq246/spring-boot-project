angular.module('Thesis')
    .service('userService', function ($http, $q, Configure) {
    	
    	
    	//以下是通用方法
        this.remove=function(user){
    		
    		var deferred = $q.defer();

            $http.post(Configure.URLBase + "user/delete.action",user, {
            	params:{}
            }).success(function (data) {
                // 如果连接成功，延时返回给调用者
                deferred.resolve(data);
            }).error(function () {
                    deferred.reject('连接服务器出错！');
            });
            
            return deferred.promise;
    		
    	};
    	
        this.add = function (user) {    		
    		var deferred = $q.defer();
            $http.post(Configure.URLBase + "user/add.action", user,{
            	params:{}
            }).success(function (data) {
                // 如果连接成功，延时返回给调用者
                deferred.resolve(data);
            }).error(function () {
                    deferred.reject('连接服务器出错！');
            });            
            return deferred.promise;    		
    	};
    	
        this.edit = function (user) {    		
    		var deferred = $q.defer();
            $http.post(Configure.URLBase + "user/update.action",user, {
            	params:{}
            }).success(function (data) {
                // 如果连接成功，延时返回给调用者
                deferred.resolve(data);
            }).error(function () {
                    deferred.reject('连接服务器出错！');
            });
            
            return deferred.promise;
    		
    	};
    	
    	this.getuserlist = function (user) {
    		
    		var deferred = $q.defer();
            $http.post(Configure.URLBase + "user/getuserlist.action",user, {
            	params:{}
            }).success(function (data) {
                // 如果连接成功，延时返回给调用者
                deferred.resolve(data);
            }).error(function () {
                    deferred.reject('连接服务器出错！');
            });
            
            return deferred.promise;
    		
    	};
    	
    	this.getSingleUser = function (user) {
    		
    		var deferred = $q.defer();
    		$http.post(Configure.URLBase + "user/getsingleuser.action",user, {
    			params:{}
    		}).success (function (data) {
    			
    			deferred.resolve(data);
    			
    		}).error (function () {
    			
    			deferred.reject('连接服务器出错！');
    			
    		});
    		return deferred.promise;
    	}
    	
    	this.modifyPwd=function(user){    		
    		var deferred = $q.defer();
            $http.post(Configure.URLBase + "user/modifyPwd.action",user, {
            	params:{}
            }).success(function (data) {
                // 如果连接成功，延时返回给调用者
                deferred.resolve(data);
            }).error(function () {
                    deferred.reject('连接服务器出错！');
            });
            
            return deferred.promise;
    		
    	};
    	
    	this.review=function(user){    		
    		var deferred = $q.defer();
            $http.post(Configure.URLBase + "user/review.action",user, {
            	params:{}
            }).success(function (data) {
                // 如果连接成功，延时返回给调用者
                deferred.resolve(data);
            }).error(function () {
                    deferred.reject('连接服务器出错！');
            });
            
            return deferred.promise;
    		
    	};
    	
    	this.lotReview=function(user){    		
    		var deferred = $q.defer();
            $http.post(Configure.URLBase + "user/lotReview.action",user, {
            	params:{}
            }).success(function (data) {
                // 如果连接成功，延时返回给调用者
                deferred.resolve(data);
            }).error(function () {
                    deferred.reject('连接服务器出错！');
            });
            
            return deferred.promise;
    		
    	};
    	
    });