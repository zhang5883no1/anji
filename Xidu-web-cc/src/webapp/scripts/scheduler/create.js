function createJob()
{
	if(!beforeSubmit()){
		return ;
	}
	
	document.forms[0].submit();
}

function beforeSubmit(){
	var isOk=true;
	$('.notNull').each(function(i,obj){
		if($(this).val()==""){
			$(this).parent().find("span").remove();
			var info =$(this).attr("title")+"不能为空!";
			var $span=$("<span>",{html:info});
			$(this).parent().append($span);	
			$span.css("backgroundColor","#FFF68F");
			$span.css("padding","3px");
			isOk=false;
		}
		
	});
	$('#cronExpression').parent().find("span").remove();
	if($('#jobType').val()==3 && $('#cronExpression').val()==''){
		
		var info =$('#cronExpression').attr("title")+"不能为空!";
		var $span=$("<span>",{html:info});
		$('#cronExpression').parent().append($span);	
		$span.css("backgroundColor","#FFF68F");
		$span.css("padding","3px");
		isOk=false;
	}
	return isOk;
}
function checkJobNameDuplicate(){
	var url = 'scheduler/isJobNameDuplicate?jobname=' + $("#jobName").val();
	ajaxValid(url,{},"message","检测中...","名字重复","名字重复 ");
}