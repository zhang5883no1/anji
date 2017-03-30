 function showTable(){
		$('#userTable').datagrid({
			title:'用户列表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			height:360, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:true,//可折叠
			url:"getUserListByOrgCode", //数据来源
			sortName: 'id', //排序的列
			sortOrder: 'desc', //倒序
			remoteSort: true, //服务器端排序
			idField:'id', //主键字段
			queryParams:{}, //查询条件
			pagination:true, //显示分页
			rownumbers:true, //显示行号
			columns:[[
				
				{field:'name',title:'名字',width:20,sortable:true,
					formatter:function(value,row,index){return row.name;} //需要formatter一下才能显示正确的数据
				},
				{field:'userCode',title:'员工号',width:20,sortable:true,
					formatter:function(value,row,index){return row.userCode;}
				},
				{field:'userType',title:'类型',width:30,sortable:true,
					formatter:function(value,row,index){return row.userType}
				}
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					addrow();
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					deleterow();
				}
			},'-'],
			onLoadSuccess:function(){
				$('#userTable').datagrid('clearSelections'); 
			}
		});
  }
 
  
$(function(){
	clouseWindow();
	  showCategory();
	  showTable();
	 
	
});
function showCategory(){
	$('#cateTree').tree({ 
	  	checkbox: false,
	  	url: 'getTree?orgCode=0', 
	  	onBeforeExpand:function(node,param){ 
	  		$('#cateTree').tree('options').url = "getTree?orgCode="+node.id;
	  	}, 
	  	onClick:function(node){ 
	  		clickTree(node.id);
	  	},
	  	onContextMenu:function(e,node){
	  		treeOperateMenu(node.id);
	  		return false;
	  	}
	});
}

//右键添加或者删除Group
function treeOperateMenu(nodeId){
	   popDiv($(".tree-node[node-id='"+nodeId+"']").find(".tree-title"),'<table><tr height="30"><td><a href="javascript:addNode('+nodeId+');">&nbsp添加节点&nbsp&nbsp</a></td></tr><tr height="30"><td><a href="javascript:deleteNode('+nodeId+');">&nbsp删除节点&nbsp&nbsp</a></td></tr><tr height="30"><td><a href="javascript:hiddenDiv();">&nbsp取消&nbsp&nbsp&nbsp&nbsp</a></td></tr></table>');
	   /*
	   var selectedNode=jq$(".nodeSel");
	   $.each(selectedNode,function(n,value) {  
           selectedNode[n].className='node';
     	});
	   obj.className='nodeSel';
	   */ 
	   return false;
}

/**
用于在控件的右下角显示Div
目前用于部门管理左侧树右击事件
**/
function popDiv(obj, text) {
	 //用于排除重复元素
	var isFishished=$("#pop");
	if(isFishished.length>0){
		$("#pop").remove();
	}
	 if(null==text || ""==text){
		 return;
	 }
   var $divObj = $('<div id="pop" style="overflow:auto;position:absolute;z-index:99;display:none;background-color: #fff5d1;border:solid 1px #99bbe8;" ></div> ');
   $('body').append($divObj);
   $divObj.html(text);
   
   if($divObj.height()>300){
   	$divObj.height(300);
   }
   if($divObj.width()>600){
   	$divObj.width(600);
   }
   
   //获取控件德高度和宽度
   var objTop = obj.offset().top;	//控件定位上
   var objLeft = obj.offset().left; //控件定位左
   var objWidth = obj.width();//控件定位本身宽度
   var objHeight = obj.height();//控件定位本身高度

   //设置弹出层x坐标,y坐标
   $divObj.offset({top:objTop + objHeight,left:objLeft});
   $divObj.show();
//   $divObj.focus();//用于元素内有链接的情况
   $divObj.blur(function(){
   	$divObj.remove();
   });
}
/**
*删除右下角的Div
**/
function hiddenDiv() {
	$('#pop').remove();
}

function addNode(nodeId){
	$('input[type="text"]').val('');
	$('#parentOrgCode').val(nodeId);
	$('#pop').remove();
}
function deleteNode(nodeId){
	//alert($(".tree-node[node-id='"+nodeId+"']").parent().html());
	$(".tree-node[node-id='"+nodeId+"']").parent().remove();
	$('#pop').remove();
}

function clickTree(nodeId) {
	$.ajax({
        type: "get",
        dataType: "json",
        url: "getOrgByOrgCode",
        data: "orgCode=" +nodeId,
        cache:false,
        success: function(msg){
        	 $("#parentOrgCode").val(msg.parentOrgCode);
        	 $("#orgCode").val(msg.orgCode);
        	 $("#orgName").val(msg.orgName);
        	 doSearchUser(nodeId);
        }
	
	});
	
}

/**
 * 点击树节点，获取其下的所有用户信息
 * @param nodeId (传入orgCode)
 */
function doSearchUser(nodeId){
	  var params = $('#userTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
	  params["orgCode"]=nodeId;
	  $('#userTable').datagrid('reload'); 

}

/**
 * 添加或编辑组织，保存
 */
function doSaveOrg(){
	alert('保存数据，可以直接用form提交');
}

/**
 * 重置输入框
 */
function doReset(){
	$('input[type="text"]').not("#parentOrgCode").val('');
}

/**
 * 添加用户
 */
function addrow(){			
	$('#searchUserWindow').window('open');		
}	

function saveUser(){
	alert('保存选中用户');
	$('#searchUserWindow').window('close');
}
function clouseWindow(){
	$('#searchUserWindow').window('close');
}

