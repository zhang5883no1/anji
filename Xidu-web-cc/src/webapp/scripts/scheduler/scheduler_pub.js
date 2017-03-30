/**
 * 根据条件查询
 * @param formId 表单Id
 * @param dataTableId 数据表格Id
 */  
function doSearch(formId,dataTableId){
	  var params = $('#'+dataTableId).datagrid('options').queryParams; //先取得 datagrid 的查询参数
	  var fields =$('#'+formId).serializeArray(); //自动序列化表单元素为JSON对象
	  $.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
	  }); 
	  $('#'+dataTableId).datagrid('reload'); 

  }
  
  /**
   * 重置查询条件
   */
  function reset(){
	  $('#'+formId).find('input[type="text",type="hidden"]').val();
	  $('#'+formId).find('input[type="radio",type="checkbox"]').remove('checked');
	  $('#'+formId).find('select').find('option').remove('selected');
  }