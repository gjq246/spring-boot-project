angular.module('Thesis').filter("menufilter",function(){
                return function(input,url){
                	if(input==undefined || input.length==0 || input==''){
                		return " ";
                	}
                    var out = "/manager" +input;
                    var fdStart = url.indexOf(out);
                    if(fdStart == 0){
                       //表示strCode是以ssss开头；
                    	return " active ";
                   }else{
                       //表示strCode不是以ssss开头
                	   return " ";
                   }
                };
            }).filter("submenufilter",function(){
                return function(input,url){
                	if(input==undefined || input.length==0 || input==''){
                		return " ";
                	}
                    var out =  "/manager" +input;
                    if(out == url){
                    	return " active ";
                   }else{
                	   return " ";
                   }
                };
            }).filter("trusted", ["$sce", function ($sce) {
                return function (html) {
                    if (typeof html== 'string')   //判断类型为字符串
                        return $sce.trustAsHtml(html);  
                    return html;
                };
            }]);
