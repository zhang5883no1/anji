<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>菜单管理</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
	<link rel="stylesheet" type="text/css" href="${csspath}/public.css"/>
	<link rel="stylesheet" type="text/css" href="${easyuipath}/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${easyuipath}/themes/icon.css">
	<script type="text/javascript" src="${easyuipath}/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${easyuipath}/jquery.easyui.min.js"></script>
<script type="text/javascript">
	jQuery(function($){
		var lastIndex;
		$('#menu_table').datagrid({
			title:'菜单管理', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			height:460, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:true,//可折叠
			url:"${webcontext}/menu/query", //数据来源
			sortName: 'id', //排序的列
			sortOrder: 'desc', //倒序
			remoteSort: true, //服务器端排序
			idField:'id', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			rownumbers:true, //显示行号
			columns:[[
				{field:'ck',checkbox:true,width:2}, //显示复选框
				{field:'id',editor:"text", title:'ID',width:20,sortable:true,
					formatter:function(value,row,index){return row.id;}
				},
				{field:'menuCode',editor:"text",title:'编号',width:20,sortable:true,
					formatter:function(value,row,index){return row.menuCode;}
				},
				{field:'menuName',editor:"text",title:'名称',width:20,sortable:true,
					formatter:function(value,row,index){return row.menuName;}
				},
				{field:'parentMenuCode',editor:"text",title:'父菜单',width:20,sortable:true,
					formatter:function(value,row,index){return row.parentMenuCode;} //需要formatter一下才能显示正确的数据
				},
				{field:'url',editor:"text",title:'URL',width:20,sortable:true,
					formatter:function(value,row,index){return row.url;}
				},
				{field:'createDate',title:'创建时间',width:20,sortable:true,
					formatter:function(value,row,index){return row.createDate;} //需要formatter一下才能显示正确的数据
				}
			]],
			toolbar:[{
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					//$('#menu_table').datagrid('endEdit', lastIndex);
					$('#menu_table').datagrid('appendRow',{ //这里定义要提交的数据，添加到最后一行
						menuCode: 'user_update',
						menuName: '用户更新',
						parentMenuCode: 'user_manage',
						menuLevel: '2',
						isLeaf: '1',
						url: 'http://localhost:8888/user/update',
						operatorId: '1'
					});
					lastIndex = $('#menu_table').datagrid('getRows').length-1; //得到最后1行，从0开始
					$('#menu_table').datagrid('selectRow', lastIndex); //选中最后1行
				 	$('#menu_table').datagrid('beginEdit', lastIndex); //开始编辑这1行
				}
			},'-',{
				text:'更新',
				iconCls:'icon-edit',
				handler:function(){
					var row = $('#menu_table').datagrid('getSelected');
					var index = $('#menu_table').datagrid('getRowIndex',row);
					$('#menu_table').datagrid('beginEdit', index); //开始编辑这1行
				}
			},
			'-',{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					$('#menu_table').datagrid('acceptChanges');
					var row = $('#menu_table').datagrid('getSelected');
					if(row.id){
						updateMenu();
					}else{
						saveMenu();
					}
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					delMenu();
				}
			},'-'],
			onLoadSuccess:function(){
				$('#menu_table').datagrid('clearSelections'); 
			}
		});
  }
  );
	function updateMenu(){
		var row = $('#menu_table').datagrid('getSelected');
		var params = "?menuCode="+row.menuCode;
		  params+="&menuName="+row.menuName;
		  params+="&parentMenuCode="+row.parentMenuCode;
		  params+="&menuLevel="+row.menuLevel;
		  params+="&isLeaf="+row.isLeaf;
		  params+="&url="+row.url;
		  params+="&operatorId=1";
		  $.ajax({
			   type: "POST",
			   url: "${webcontext}/menu/update"+params,
			   success: function(msg){
				   $.messager.alert('提示', "["+msg.result+"] "+"更新成功", 'info');
				   $('#menu_table').datagrid('reload');
			   }
			});
	}
	
	
  //删除
  function delMenu(){
	isSelect();
	$.messager.confirm('提示', '确定要删除吗?', function(result) {
		if (result) {
			var rows = $('#menu_table').datagrid('getSelections');
			$.each(rows, function(i, n) {
				$.ajax({
					   type: "POST",
					   url: '${webcontext}/menu/delete/' + n.menuCode,
					   success: function(msg){
						   $.messager.alert('提示', msg.result, 'info');
						   $('#menu_table').datagrid('reload');
					   }
					});
			});
		}
	});
  }
  function saveMenu(){
	  lastIndex = $('#menu_table').datagrid('getRows').length-1; //得到最后1行，从0开始
	  var row = $('#menu_table').datagrid('getRows')[lastIndex];
	  var params = "?menuCode="+row.menuCode;
	  params+="&menuName="+row.menuName;
	  params+="&parentMenuCode="+row.parentMenuCode;
	  params+="&menuLevel="+row.menuLevel;
	  params+="&isLeaf="+row.isLeaf;
	  params+="&url="+row.url;
	  params+="&operatorId="+row.operatorId;
	  $.ajax({
		   type: "POST",
		   url: "${webcontext}/menu/create"+params,
		   success: function(msg){
			   $.messager.alert('提示', "["+msg.result+"] "+"保存成功", 'info');
			   $('#menu_table').datagrid('reload');
		   }
		});
  }
  	/*  查询*/
  	function searchWebInfo(){
		var params = $('#menu_table').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#menu_table').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
  	function isSelect() {
		var row = $('#menu_table').datagrid('getSelected');
		if (!row) {
			$.messager.alert("提示", "请选择操作行数！");
			return;
		}
	}
  </script>
  </head>
  
  <body>
  
   <form id="queryForm" style="margin:10;text-align: center;">
		<table width="100%">
			<tr>
			<td>服务名称：<input name="serviceName" style="width: 200"></td>
			
			<td align="center"><a href="#" onclick="searchWebInfo();" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
			</tr>
		</table>
	</form>
	<div style="padding:10" id="tabdiv">
	
	
		<table id="menu_table"></table>
	</div>
  </body>
</html>
