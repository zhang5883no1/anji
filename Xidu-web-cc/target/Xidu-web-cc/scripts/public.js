$(function() {
	//设置输入框获取/失去焦点时的背景色变化
	$(".text").focus(function() {
		$(this).addClass("oninput");
	}).blur(function() {
		$(this).removeClass("oninput");
	});
	//设置只读输入框的背景色
	$(".text[readonly]").addClass("readonly");
});

/**

$(function() {
	//输入日期
	$("input.date").datepicker({
		dateFormat: "yy-mm-dd" //日期格式
		//showButtonPanel: true, //显示【Today】和【Done】按钮
		//changeMonth: true, changeYear: true, //选择年月
		//minDate: -7, maxDate: "+1m +1d", //限定可选的日期范围
	});
});
**/

$(function() {
	//设置鼠标悬停/移开时按钮的变化
	$(".btn").mouseover(function() {
		$(this).addClass("btn_over");	
	}).mouseout(function() {
		$(this).removeClass("btn_over");
	});
});

$(function() {
	//设置数据展示表格奇、偶行的背景色
	$(".data_tab tr:odd").addClass("odd");
	$(".data_tab tr:even").addClass("even");
	//设置鼠标滑过时数据展示表格行背景色的变化
	$(".data_tab tr").mouseover(function() {
		$(this).addClass("over");
	}).mouseout(function() {
		$(this).removeClass("over");
	});
});

$(function() {
	//点击标题，切换其下的内容
	$(function() {
		$(".title.max").toggle(function() {
			$(this).addClass("min").next("div").slideUp("slow");
		},function() {
			$(this).removeClass("min").next("div").slideDown("slow");
		});
	});
});

$(function() {
	//全选/全不选
	$(function() {
		$("#check_all").click(function() {
			if($(this).attr("checked")==true) {
				$(this).parent().parent().parent().find("input[type='checkbox']").attr("checked",true);
			}else {
				$(this).parent().parent().parent().find("input[type='checkbox']").attr("checked",false);
			}
		});
	});
});

$(function() {
	//取消遮罩 - 测试使用
	$("#save, #cancel").click(function() {
		$.unblockUI();
	});
});		