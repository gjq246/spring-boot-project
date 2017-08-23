// Custom scripts
$(document).ready(function() {

	toastr.options = {
		"closeButton" : true,
		"debug" : false,
		"progressBar" : true,
		"positionClass" : "toast-bottom-center",
		"onclick" : null,
		"showDuration" : "400",
		"hideDuration" : "1000",
		"timeOut" : "5000",
		"extendedTimeOut" : "1000",
		"showEasing" : "swing",
		"hideEasing" : "linear",
		"showMethod" : "fadeIn",
		"hideMethod" : "fadeOut"
	}
	// MetsiMenu
	// $('#side-menu').metisMenu();

	// Collapse ibox function
	$('.collapse-link').click(function() {
		var ibox = $(this).closest('div.ibox');
		var button = $(this).find('i');
		var content = ibox.find('div.ibox-content');
		content.slideToggle(200);
		button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
		ibox.toggleClass('').toggleClass('border-bottom');
		setTimeout(function() {
			ibox.resize();
			ibox.find('[id^=map-]').resize();
		}, 50);
	});

	// Close ibox function
	$('.close-link').click(function() {
		var content = $(this).closest('div.ibox');
		content.remove();
	});

	// Small todo handler
	$('.check-link').click(function() {
		var button = $(this).find('i');
		var label = $(this).next('span');
		button.toggleClass('fa-check-square').toggleClass('fa-square-o');
		label.toggleClass('todo-completed');
		return false;
	});

	// Append config box / Only for demo purpose
	$.get("skin-config.html", function(data) {
		$('body').append(data);
	});

	// minimalize menu
    $('.navbar-minimalize').click(function () {
        $("body").toggleClass("mini-navbar");
        $("#sidebar1").toggleClass("left-menu-scroll-1");
        $("#sidebar2").toggleClass("left-menu-scroll-2");
        $("#sidebar3").toggleClass("left-menu-scroll-3");
        if (!$("#sidebar3").hasClass("left-menu-scroll-3")) {
            $("#sidebar3").mCustomScrollbar("destroy");
            $(".my-fixed-header").addClass("my-fixed-header3");
        }else{
            $("#sidebar3").mCustomScrollbar();
            $(".my-fixed-header").removeClass("my-fixed-header3");
        }
        
        SmoothlyMenu();
    })

	// tooltips
	$('.tooltip-demo').tooltip({
		selector : "[data-toggle=tooltip]",
		container : "body"
	})

	// Move modal to body
	// Fix Bootstrap backdrop issu with animation.css
	$('.modal').appendTo("body")

	// Full height of sidebar
	function fix_height() {
		var heightWithoutNavbar = $("body > #wrapper").height() - 61;
		$(".sidebard-panel").css("min-height", heightWithoutNavbar + "px");
	}
	fix_height();

	// Fixed Sidebar
	// unComment this only whe you have a fixed-sidebar
	// $(window).bind("load", function() {
	// if($("body").hasClass('fixed-sidebar')) {
	// $('.sidebar-collapse').slimScroll({
	// height: '100%',
	// railOpacity: 0.9,
	// });
	// }
	// })

	$(window).bind("load resize click scroll", function() {
		if (!$("body").hasClass('body-small')) {
			fix_height();
		}
	})

	$("[data-toggle=popover]").popover();
	
	//下拉框验证
	 jQuery.validator.addMethod("isSelected", function(value, element) {  
		// log(value);
	     return (value!="?"&&value!=null);
	 }, "请选择一个下拉列表项");

});

// For demo purpose - animation css script
function animationHover(element, animation) {
	element = $(element);
	element.hover(function() {
		element.addClass('animated ' + animation);
	}, function() {
		// wait for animation to finish before removing classes
		window.setTimeout(function() {
			element.removeClass('animated ' + animation);
		}, 2000);
	});
}

// Minimalize menu when screen is less than 768px
$(function() {
	$(window).bind("load resize", function() {
		if ($(this).width() < 769) {
			$('body').addClass('body-small')
		} else {
			$('body').removeClass('body-small')
		}
	})
})

function SmoothlyMenu() {
	if (!$('body').hasClass('mini-navbar') || $('body').hasClass('body-small')) {
		// Hide menu in order to smoothly turn on when maximize menu
		$('#side-menu').hide();
		// For smoothly turn on menu
		setTimeout(function() {
			$('#side-menu').fadeIn(500);
		}, 100);
	} else if ($('body').hasClass('fixed-sidebar')) {
		$('#side-menu').hide();
		setTimeout(function() {
			$('#side-menu').fadeIn(500);
		}, 300);
	} else {
		// Remove all inline style from jquery fadeIn function to reset menu
		// state
		$('#side-menu').removeAttr('style');
	}
}

// Dragable panels
function WinMove() {
	var element = "[class*=col]";
	var handle = ".ibox-title";
	var connect = "[class*=col]";
	$(element).sortable({
		handle : handle,
		connectWith : connect,
		tolerance : 'pointer',
		forcePlaceholderSize : true,
		opacity : 0.8,
	}).disableSelection();
};
//调试打印
function log(msg) {
	if (window["console"]) {
		console.log(msg);
	}
}

//验证插件
$.validator.setDefaults({
    highlight: function (element) {
        $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
    },
    success: function (element) {
        element.closest('.form-group').removeClass('has-error').addClass('has-success');
    },
    errorElement: "span",
    errorClass: "help-block m-b-none",
    validClass: "help-block m-b-none"


});

//GUID
function GUID() {
    this.date = new Date();   /* 判断是否初始化过，如果初始化过以下代码，则以下代码将不再执行，实际中只执行一次 */
    if (typeof this.newGUID != 'function') {   /* 生成GUID码 */
        GUID.prototype.newGUID = function () {
            this.date = new Date(); var guidStr = '';
            sexadecimalDate = this.hexadecimal(this.getGUIDDate(), 16);
            sexadecimalTime = this.hexadecimal(this.getGUIDTime(), 16);
            for (var i = 0; i < 9; i++) {
                guidStr += Math.floor(Math.random() * 16).toString(16);
            }
            guidStr += sexadecimalDate;
            guidStr += sexadecimalTime;
            while (guidStr.length < 32) {
                guidStr += Math.floor(Math.random() * 16).toString(16);
            }
            return this.formatGUID(guidStr);
        }
        /* * 功能：获取当前日期的GUID格式，即8位数的日期：19700101 * 返回值：返回GUID日期格式的字条串 */
        GUID.prototype.getGUIDDate = function () {
            return this.date.getFullYear() + this.addZero(this.date.getMonth() + 1) + this.addZero(this.date.getDay());
        }
        /* * 功能：获取当前时间的GUID格式，即8位数的时间，包括毫秒，毫秒为2位数：12300933 * 返回值：返回GUID日期格式的字条串 */
        GUID.prototype.getGUIDTime = function () {
            return this.addZero(this.date.getHours()) + this.addZero(this.date.getMinutes()) + this.addZero(this.date.getSeconds()) + this.addZero(parseInt(this.date.getMilliseconds() / 10));
        }
        /* * 功能: 为一位数的正整数前面添加0，如果是可以转成非NaN数字的字符串也可以实现 * 参数: 参数表示准备再前面添加0的数字或可以转换成数字的字符串 * 返回值: 如果符合条件，返回添加0后的字条串类型，否则返回自身的字符串 */
        GUID.prototype.addZero = function (num) {
            if (Number(num).toString() != 'NaN' && num >= 0 && num < 10) {
                return '0' + Math.floor(num);
            } else {
                return num.toString();
            }
        }
        /*  * 功能：将y进制的数值，转换为x进制的数值 * 参数：第1个参数表示欲转换的数值；第2个参数表示欲转换的进制；第3个参数可选，表示当前的进制数，如不写则为10 * 返回值：返回转换后的字符串 */GUID.prototype.hexadecimal = function (num, x, y) {
            if (y != undefined) { return parseInt(num.toString(), y).toString(x); }
            else { return parseInt(num.toString()).toString(x); }
        }
        /* * 功能：格式化32位的字符串为GUID模式的字符串 * 参数：第1个参数表示32位的字符串 * 返回值：标准GUID格式的字符串 */
        GUID.prototype.formatGUID = function (guidStr) {
            var str1 = guidStr.slice(0, 8) + '', str2 = guidStr.slice(8, 12) + '', str3 = guidStr.slice(12, 16) + '', str4 = guidStr.slice(16, 20) + '', str5 = guidStr.slice(20);
            return str1 + str2 + str3 + str4 + str5;
        }
    }
}

