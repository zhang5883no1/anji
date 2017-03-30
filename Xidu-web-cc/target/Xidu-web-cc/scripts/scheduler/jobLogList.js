 	jQuery(function($){
		$('#jobLoggerTable').datagrid({
			title:'JobLogger列表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:true,//可折叠
			url:"queryJobLoggerList", //数据来源
			sortName: 'id', //排序的列
			sortOrder: 'desc', //倒序
			remoteSort: true, //服务器端排序
			idField:'id', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			rownumbers:true, //显示行号
			columns:[[
				{field:'jobName',title:'Job名字',width:20,sortable:true,
					formatter:function(value,row,index){return row.jobName;} //需要formatter一下才能显示正确的数据
				},
				//{field:'jobClassName',title:'Job Class名称',width:60,sortable:true,
				//	formatter:function(value,row,index){return row.jobClassName;} 
			//	},
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
				},{field:'jobStatus',title:'job状态',width:20,sortable:true,
					formatter:function(value,row,index){return row.jobStatus;} 
				},
				
				{field:'retry',title:'最大重试次数',width:20,sortable:true,
					formatter:function(value,row,index){return row.retry;} 
				},
				{field:'jobStartTime',title:'开始时间',width:20,sortable:true,
					formatter:function(value,row,index){return row.jobStartTime;} 
				},
		       
		       {field:'jobEndTime',title:'结束时间',width:20,sortable:true,
			    formatter:function(value,row,index){return row.jobStartTime;} 
		        },
				{field:'jobDesc',title:'job描述',width:20,sortable:true,
					formatter:function(value,row,index){return row.jobDesc;}
			}
		        ]],
			
			onLoadSuccess:function(){
				$('#jobLoggerTable').datagrid('clearSelections'); 
			}
		});
  }
  );
  
