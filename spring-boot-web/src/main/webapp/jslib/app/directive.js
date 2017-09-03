angular.module('Thesis').directive('onRepeatFinishedRender',
		function($timeout) {
			return {
				restrict : 'A',
				link : function(scope, element, attr) {
					if (scope.$last === true) {
						$timeout(function() {
							// 这里element, 就是ng-repeat渲染的最后一个元素
							scope.$emit('ngRepeatFinished', element);
						});
					}
				}
			};
		}).directive('datePicker', function ($filter) {
		    return {
		        require: 'ngModel',
		        link: function (scope, element, attr, ngModel) {
		            $(element).datetimepicker({
		           	 locale: 'zh-cn',
		             useCurrent: false, //Important! See issue #1075
		             sideBySide: true,
		             showTodayButton: true
		            });

		            $(element).on("dp.change", function (e) {
		                ngModel.$viewValue = $filter('date')(e.date.valueOf(), 'yyyy-MM-dd HH:mm:ss');
		                if(ngModel.$viewValue==false)
		                {
		                	ngModel.$viewValue="";
		                }
		                ngModel.$commitViewValue();
		               
		            });
		        }
		    };
		}).directive('datePickerInput', function() {
		    return {
		        require: 'ngModel',
		        link: function (scope, element, attr, ngModel) {
		            // Trigger the Input Change Event, so the Datepicker gets refreshed
		            scope.$watch(attr.ngModel, function (value) {
		                if (value) {
		                    element.trigger("change");
		                }
		            });
		        }
		    };
		}).directive('datePickerdate', function ($filter) {
		    return {
		        require: 'ngModel',
		        link: function (scope, element, attr, ngModel) {
		            $(element).datetimepicker({
		           	 locale: 'zh-cn',
		             useCurrent: false, //Important! See issue #1075
		             sideBySide: true,
		             showTodayButton: true,
		             format: 'YYYY-MM-DD',
		            });

		            $(element).on("dp.change", function (e) {
		                ngModel.$viewValue = $filter('date')(e.date.valueOf(), 'yyyy-MM-dd');
		                if(ngModel.$viewValue==false)
		                {
		                	ngModel.$viewValue="";
		                }
		                ngModel.$commitViewValue();
		               
		            });
		        }
		    };
		}).directive('datePickerdateInput', function() {
		    return {
		        require: 'ngModel',
		        link: function (scope, element, attr, ngModel) {
		            // Trigger the Input Change Event, so the Datepicker gets refreshed
		            scope.$watch(attr.ngModel, function (value) {
		                if (value) {
		                    element.trigger("change");
		                }
		            });
		        }
		    };
		});