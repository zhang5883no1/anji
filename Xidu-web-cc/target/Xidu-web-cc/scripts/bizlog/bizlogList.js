jQuery(function($) {
		$('#dealerTable').datagrid({
			title : '日志列表', //标题
			method : 'post',
			iconCls : 'icon-edit', //图标
			singleSelect : false, //多选
			height : 360, //高度
			fitColumns : true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped : true, //奇偶行颜色不同
			collapsible : true,//可折叠
			url : "queryBizLogList", //数据来源
			sortName : 'id', //排序的列
			sortOrder : 'desc', //倒序
			remoteSort : true, //服务器端排序
			idField : 'id', //主键字段
			queryParams : {}, //查询条件
			pagination : true, //显示分页
			rownumbers : true, //显示行号
			columns : [ [ {
				field : 'logCode',
				title : '日志代码',
				width : 10,
				sortable : true,
				formatter : function(value, row, index) {
					return row.logCode;
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'userName',
				title : '用户姓名',
				width : 10,
				sortable : true,
				formatter : function(value, row, index) {
					return row.userName;
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'operationType',
				title : '操作类型',
				width : 10,
				sortable : true,
				formatter : function(value, row, index) {
					return row.operationType;
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'operationTime',
				title : '操作时间',
				width : 10,
				sortable : true,
				formatter : function(value, row, index) {
					return new Date(row.operationTime).toLocaleDateString();
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'reason',
				title : '原因描述',
				width : 10,
				sortable : true,
				formatter : function(value, row, index) {
					return row.reason;
				} //需要formatter一下才能显示正确的数据
			} ] ],

			onLoadSuccess : function() {
				$('#dealerTable').datagrid('clearSelections');
			}
		});
	});
	
	function searchLogs() {
		var params = $('#dealerTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields = $('#submitForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
			params[field.name] = field.value; //设置查询参数
		});
		$('#dealerTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}

	function disable() {
		$('#dd').datebox('disable');
	}
	function enable() {
		$('#dd').datebox('enable');
	}
	function formatDate(v) {
		if (v instanceof Date) {
			var y = v.getFullYear();
			var m = v.getMonth() + 1;
			var d = v.getDate();

		}
		return v;
	}

	$(function() {
		$('#dd').datebox({
			required : true

		});
	});

	function disable() {
		$('#dd2').datebox('disable');
	}
	function enable() {
		$('#dd2').datebox('enable');
	}
	function formatDate(v) {
		if (v instanceof Date) {
			var y = v.getFullYear();
			var m = v.getMonth() + 1;
			var d = v.getDate();
		}
		return v;
	}

	$(function() {
		$('#dd2').datebox({
			required : true
		});
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	