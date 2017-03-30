/*
 * 页面加载完毕后自动执行的函数
 * 用于检测当前屏幕分辨率并与基线标准进行对比
 */
$(function() {
	//定义基线宽高度
	var base_width = 1050;
	var base_height = 800;
	//获取当前屏幕分辨率
	var screen_width = window.screen.width;
	var screen_height = window.screen.height;
	//把当前分辨率与基线标准进行比较
	var w = screen_width - base_width;
	var h = screen_height - base_height;
	//当不符合基线标准时，遮罩整个页面
	if(w<0 || h<0) {
		//赋值
		var str_screen = screen_width+" * "+screen_height;
		var str_base = base_width+" * "+base_height;
		$("#screen_value").text(str_screen);
		$("#base_value").text(str_base);
//		//设置遮罩效果
//		$.blockUI({
//			message : $("#screen"),
//			css : {
//				width: "300px",
//				height: "106px",
//				left: "1px",
//				top: "1px",
//				border: "none"
//			}
//		});
	}
});

//刷新页面
function refreshPage() {
	window.document.location.reload(true);
}

//登录系统
function login() {
	//定义弹出窗口宽高度
	var win_width = 1024;
	var win_height = 675;
	//获取当前屏幕分辨率
	var screen_w = window.screen.width;
	var screen_h = window.screen.height;
	//计算弹出窗口的左、上边距，以使其居中显示
	var win_top = (screen_h-win_height)/2;
	var win_left = (screen_w-win_width)/2;
	//检测浏览器类型
	var Bro = {};
	var ua = navigator.userAgent.toLowerCase();
	var v;
	(v = ua.match(/msie ([\d.]+)/)) ? Bro.ie = v[1] :
	(v = ua.match(/firefox\/([\d.]+)/)) ? Bro.firefox = v[1] :
	(v = ua.match(/chrome\/([\d.]+)/)) ? Bro.chrome = v[1] :
	(v = ua.match(/opera.([\d.]+)/)) ? Bro.opera = v[1] :
	(v = ua.match(/version\/([\d.]+).*safari/)) ? Bro.safari = v[1] : 0;
	//如果为支持类型：IE、Firefox
	if(Bro.ie || Bro.firefox) {
		document.login_form.submit();
		//window.open("main.html","doss_main","width="+win_width+",height="+win_height+",top="+win_top+",left="+win_left+",location=no,menubar=no,toolbar=no,scrollbars=no,status=no,resizable=no,fullscreen=no");
	}else {
		//赋值
		var borwser_type = "";
		if(Bro.chrome) {borwser_type = "Chrome ";}
		else if(Bro.opera) {borwser_type = "Opera";}
		else if(Bro.safari) {borwser_type = "Safari";}
		else {borwser_type = "未知";}
		$("#browser_type").text(borwser_type);
		//如果为非支持类型的浏览器，设置遮罩效果
		$.blockUI({
			message : $("#borwser"),
			css : {
				width: "300px",
				height: "106px",
				left: "1px",
				top: "1px",
				border: "none"
			}
		});
	}
}