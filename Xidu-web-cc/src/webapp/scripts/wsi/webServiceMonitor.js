jQuery(function($) {
	$('#dealerTable').datagrid({
		title : '监控WebService运行', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 360, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryListWeb", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号

		columns : [ [ {
			field : 'ck',
			checkbox : true,
			width : 2
		}, // 显示复选框
		{
			field : 'serviceName',
			title : '服务名称',
			width : 20,
			sortable : true,
			formatter : function(value, row, index) {
				return row.serviceName;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'methodName',
			title : '方法名',
			width : 20,
			sortable : true,
			formatter : function(value, row, index) {
				return row.methodName;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'methodExecutionTime',
			title : '执行时间区域',
			width : 20,
			sortable : true,
			formatter : function(value, row, index) {
				return row.methodExecutionTime;
			} // 需要formatter一下才能显示正确的数据
		
		}, {
			field : 'methodExecutionStatus',
			title : '执行状态',
			width : 20,
			sortable : true,
			formatter : function(value, row, index) {
				return row.methodExecutionStatus;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'resultDescription',
			title : '结果描述',
			width : 20,
			sortable : true,
			formatter : function(value, row, index) {
				return row.resultDescription;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'serviceCaller',
			title : '服务调用者',
			width : 20,
			sortable : true,
			formatter : function(value, row, index) {
				return row.serviceCaller;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'serviceProvider',
			title : '服务提供者',
			width : 20,
			sortable : true,
			formatter : function(value, row, index) {
				return row.serviceProvider;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'createDate',
			title : '创建日期',
			width : 20,
			sortable : true,
			formatter : function(value, row, index) {
				return new Date(row.createDate).toLocaleDateString();
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'createBy',
			title : '创建人',
			width : 20,
			sortable : true,
			formatter : function(value, row, index) {
				return row.createBy;
			} // 需要formatter一下才能显示正确的数据
		} ] ],
		toolbar : [ {
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
				addrow();
			}
		}, '-', {
			text : '更新',
			iconCls : 'icon-edit',
			handler : function() {
				updaterow();
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				deleterow();
			}
		}, '-' ],
		onLoadSuccess : function() {
			$('#dealerTable').datagrid('clearSelections');
		}

	});
});
function queryListWeb() {
	var params = $('#dealerTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
	var fields = $('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
		params[field.name] = field.value; //设置查询参数
	});
	$('#dealerTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
}
//清空查询条件
function clearForm() {
	$('#queryForm').form('clear');
	queryListWeb();
}