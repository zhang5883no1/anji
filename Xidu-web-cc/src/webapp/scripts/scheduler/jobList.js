  jQuery(function($){
		$('#jobTable').datagrid({
			title:'Job列表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:true,//可折叠
			url:"queryJobList", //数据来源
			sortName: 'id', //排序的列
			sortOrder: 'desc', //倒序
			remoteSort: true, //服务器端排序
			idField:'id', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			rownumbers:true, //显示行号
			columns:[[
				{field:'id',title:'',width:10,sortable:false,
					formatter:function(value,row,index){
						var opt = '<input type="checkbox" name="ckid"  value="'+row.jobName+'" />';
						opt += '<input type="hidden" name="jobType"  value="'+row.jobType+'"/>';
						opt += '<input type="hidden" name="startTime"  value="'+row.startTime+'"/>';
						return opt ;
					}
				}, //显示复选框
				{field:'jobName',title:'Job名字',width:20,sortable:true,
					formatter:function(value,row,index){return row.jobName;} //需要formatter一下才能显示正确的数据
				},
				{field:'jobClassName',title:'Job Class名称',width:60,sortable:true,
					formatter:function(value,row,index){return row.jobClassName;} 
				},
				{field:'jobType',title:'Job类型',width:30,sortable:true,
					formatter:function(value,row,index){
						if(row.jobType==1){
							return "IMMEDIATE_JOB";
						}else if(row.jobType==2){
							return "RUN_ONCE_JOB";
						}else if(row.jobType==3){
							return "CYCLE_JOB";
						}
					} 
				},
				{field:'maxRetry',title:'最大重试次数',width:20,sortable:true,
					formatter:function(value,row,index){return row.maxRetry;} 
				},
				{field:'retryInterval',title:'重试间隔时间',width:20,sortable:true,
					formatter:function(value,row,index){return row.retryInterval;} 
				},
				{field:'cronExpression',title:'正则表达式',width:20,sortable:true,
					formatter:function(value,row,index){return row.cronExpression;} 
				}
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					addrow();
				}
			},'-',{
				text:'更新',
				iconCls:'icon-edit',
				handler:function(){
					updaterow();
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					deleterow();
				}
			},'-',{
				text:'运行',
				iconCls:'icon-redo',
				handler:function(){
					runJob();
				}
			},'-'],
			onLoadSuccess:function(){
				$('#jobTable').datagrid('clearSelections'); 
			}
		});
  }
  );

  
  function addrow(){
	  location.href="toCreateJob";
  }
  function deleterow(){
	  $.messager.confirm('确认','确定要删除选中记录吗？?',function(r){   
		  if (r){   
			  var array = $("input[type='checkbox']:checked");
			  if(array.length==0){
				  $.messager.alert('提示','至少选中一条记录'); 
				  return;
			  }
			  var ids="";
			  $.each( array, function(i, id){
				ids+=id.value+",";
			  }); 	
			  location.href= "deleteJob?jobName=" + ids; 
		  }   
	  });  

	  
  }
  function updaterow(){
	  var array = new Array(); 
	  array= $("input[type='checkbox']:checked");
	  if(array.length==0){
		  $.messager.alert('提示','必须选中一条记录进行更新'); 
		  return;
	  }else if(array.length>1){
		  $.messager.alert('提示','只能选中一条记录进行更新');
		  return;
	  }else{
		  var jobType=array.parent().find('input[name="jobType"]').val();
		  var startTime=array.parent().find('input[name="startTime"]').val();
		  if(jobType==1){
			  $.messager.alert('提示','立即执行的Job不能更新');
			  return;
		  }else if(jobType==2 && startTime<new Date().getTime()){
			  $.messager.alert('提示','已经执行过的Job不能更新');
			  return;
		  }else{
			  location.href= "updateJob?jobName=" + array.val(); 
		  }
	  }
	  
  }
  
  function runJob(){
	  var array = new Array(); 
	  array= $("input[type='checkbox']:checked");
	  if(array.length==0){
		  $.messager.alert('提示','必须选中一条记录运行'); 
		  return;
	  }else if(array.length>1){
		  $.messager.alert('提示','只能选中一条记录运行');
		  return;
	  }else{
		 location.href= "runJob?jobName=" + array.val(); 
	  }
	  
  }
