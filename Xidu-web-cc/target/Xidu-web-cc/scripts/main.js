$(function() {
	//设置鼠标悬停/点击时的菜单样式变化
	$("#menu li").mouseover(function() {
		$(this).addClass("hover");
	}).mouseout(function() {
		$(this).removeClass("hover");
	}).click(function() {
		$(this).addClass("select").siblings().removeClass("select");
	});
});

//退出系统
function logout() {
	window.opener=null;
	window.open("","_self");
	window.close();
}