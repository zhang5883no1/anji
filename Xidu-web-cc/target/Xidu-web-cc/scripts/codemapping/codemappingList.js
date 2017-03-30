  jQuery(function($){
	  var lastIndex;
		$('#dealerTable').datagrid({
			title:'基础数据', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:true,//可折叠
			url:"queryCodeInfoList", //数据来源
			sortName: 'id', //排序的列
			sortOrder: 'desc', //倒序
			remoteSort: true, //服务器端排序
			idField:'id', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			rownumbers:true, //显示行号
			columns:[[
				{field:'ck',checkbox:true,width:6}, //显示复选框
				{field:'code',title:'编号',editor:"text",width:10,sortable:true,
					formatter:function(value,row,index){return row.code;} //需要formatter一下才能显示正确的数据
				},
				
				{field:'parentCode',title:'父节点',editor:"text",width:30,sortable:true,
					formatter:function(value,row,index){return row.parentCode;}
				},
				 {field:'value',title:'名称',width:30,editor:"text",sortable:true,
					formatter:function(value,row,index){return row.value;}
				},
				{field:'type',title:'类型',width:30,editor:"text",sortable:true,
					formatter:function(value,row,index){return row.type;}
				 },
				{field:'description',title:'描述',editor:"text",width:30,sortable:true,
					formatter:function(value,row,index){return row.description;}
				}
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					$('#dealerTable').datagrid('endEdit', lastIndex);
					$('#dealerTable').datagrid('appendRow',{
						code:'',
						parentCode: '',
						value: '',
						type: '',
						description: '',
						//createBy: '',
					});
					lastIndex = $('#dealerTable').datagrid('getRows').length-1;
					 $('#dealerTable').datagrid('selectRow', lastIndex);
				 $('#dealerTable').datagrid('beginEdit', lastIndex);   
					
				}
			},'-',{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					$('#dealerTable').datagrid('acceptChanges');
					var row = $('#dealerTable').datagrid('getSelected');
					if(row.id){
						updaterow();
					}else{
						saverow();
					}
				}
				},'-',{
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					var row = $('#dealerTable').datagrid('getSelected');
					var index = $('#dealerTable').datagrid('getRowIndex',row);
					$('#dealerTable').datagrid('beginEdit', index); //开始编辑这1行
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					deleterow();
				}
			},'-'],
			onLoadSuccess:function(){
				$('#dealerTable').datagrid('clearSelections'); 
			},
			
			onLoadSuccess:function(){
				$('#dealerTable').datagrid('clearSelections'); 
			}
		});
  }
  );
  
  function updaterow(){
		var row = $('#dealerTable').datagrid('getSelected');
		 var params = "?code="+row.code;
		  params+="&type="+row.type;
		  params+="&value="+row.value;
		  params+="&parentCode="+row.parentCode;
		  params+="&description="+row.description;
		 /* params+="&url="+row.url;
		  params+="&operatorId="+row.operatorId;*/
		  $.ajax({
			   type: "POST",
			   url: "updateCodeInfo"+params,
			   success: function(msg){
				   $.messager.alert('提示', "["+msg.result+"] "+"保存成功", 'info');
				   $('#dealerTable').datagrid('reload');
			   }
			});
	}
	
	
  
  function saverow(index){
		$('#dealerTable').datagrid('endEdit', index);
		
		  var params = "?code="+row.code;
		  params+="&type="+row.parentCode;
		  params+="&value="+row.value;
		  params+="&parentCode="+row.type;
		  params+="&description="+row.description;
		  $.ajax({
			   type: "POST",
			   url: "createCodeInfo"+params,
			   success: function(msg){
				   //$('#menu_table').datagrid('reload');
				   $.messager.alert('提示', "["+msg.result+"] "+"保存成功", 'info');
			   }
			});
	}
	function cancelrow(index){
		$('#dealerTable').datagrid('cancelEdit', index);
	}
  
  
	function editrow(index){
		alert(index);
			$('#dealerTable').datagrid('beginEdit', index);
		}
		function deleterow(index){
			$.messager.confirm('Confirm','Are you sure?',function(r){
				if (r){
					$('#dealerTable').datagrid('deleteRow', index);
					updateActions();
				}
			});
		}

	function deleterow(){
		  isSelect();
			$.messager.confirm('提示', '确定要删除吗?', function(result) {
				if (result) {
					var rows = $('#dealerTable').datagrid('getSelections');
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0)
							ps += "?code=" + n.code;
						else
							ps += "&code=" + n.code;
				hffj	});
					$.post('deleteCodeInfo' + ps, function(data) {
						$('#dealerTable').datagrid('reload');
						$.messager.alert('提示', data.mes, 'info');
					});
				}
			});
			
		}
function serchCodeInfo(){
		var params = $('#dealerTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#dealerTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	} 
function isSelect() {
		var row = $('#dealerTable').datagrid('getSelected');
		if (!row) {
			$.messager.alert("提示", "请选择操作行数！");
			return;
		}
	} 
function addrow(){  
	  lastIndex = $('#dealerTable').datagrid('getRows').length-1; //得到最后1行，从0开始
	  var row = $('#dealerTable').datagrid('getRows')[lastIndex];
	  var params = "?code="+row.code;
	  params+="&type="+row.type;
	  params+="&value="+row.value;
	  params+="&parentCode="+row.parentCode;
	  params+="&description="+row.description;
	  $.ajax({
		   type: "POST",
		   url: "createCodeInfo"+params,
		   success: function(msg){
			   //$('#menu_table').datagrid('reload');
			   $.messager.alert('提示', "["+msg.result+"] "+"保存成功", 'info');
		   }
		});
}  

/**
$(function() {
	$('#nn').numberbox({   
		min:0,   
		precision:2   
	});  
});
*/









