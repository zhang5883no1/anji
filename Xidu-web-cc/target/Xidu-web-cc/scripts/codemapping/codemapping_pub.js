var treeTitle = '标题';	
var treeUrl = 'ListAllCodeMapping?parentcode=-1';		
$(function(){			
	$('#treewindow').window({				
		title: treeTitle,				
		width: 400,				
		height: 400,				
		modal: true,				
		shadow: false,				
		closed: true,				
		resizable: false,				
		maximizable: false,				
		minimizable: false,				
		collapsible: false			
		});		
	});		
	function treeWindowOpen(){			
		$('#basetree').tree({				
			checkbox: true,				
			animate: true,				
			url: treeUrl,				
			cascadeCheck: true,				
			onlyLeafCheck: false			
			});			
		$('#treewindow').window('open');		
	}				
	function treeWindowClose(){			
		$('#treewindow').window('close');		
	}		
	
	$(function(){
		treeWindowSubmit();
	});
	
	function treeWindowSubmit(){	
		/**var nodes = $('#basetree').tree('getChecked');			
		var info = '';			
		if (nodes.length > 0) {				
			for ( var i = 0; i < nodes.length; i++) {					
				if (info != '') {						
					info += ',';					
				}					
				info += nodes[i].id;				
			}				
		 alert(JSON.stringify(nodes));			
		} else {				
		var node = $('#basetree').tree('getSelected');				
		if (node != null) {					
			info = node.id;					
			// alert(JSON.stringify(node));				
			}			
		}*/
		
		$('#basetree').tree({ 
		  	checkbox: false,
		  	url: 'ListAllCodeMapping?parentcode=-1', 
		  	onBeforeExpand:function(node,param){ 
		  		$('#basetree').tree('options').url = "ListAllCodeMapping?parentcode="+node.id;
		  	}, 
		  	//onExpand:function(node){
		  		//alert(node.id);
		  	//},
		  	onClick:function(node){ 
		  		clickTree(node.id);
		  	},
		  	onContextMenu:function(e,node){
		  		treeOperateMenu(node.id);
		  		return false;
		  	}
		});
		//$.messager.alert('提示',node.id,'info');			
		$('#treewindow').window('close');		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	