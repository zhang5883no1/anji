function autoCompleteBinding(id, url){
	var dataList = "";
	$.ajax({
		async: false
		,dataType: "json"
		,url: url
		,success: function(data){
			dataList = data;
		}
	});
	$("#" + id).autocomplete({
		source: dataList
	});
}

function ajaxValid(url,data,divId,beforeMessage,successMessage,errorMessage){
	$("#" + divId).empty().append(beforeMessage);
	$.ajax({
		url: url+"&tid="+new Date()
		,data: data,
		success: function (data){
			if(data == true){
				$("#" + divId).empty().append(successMessage);
			}else{
				$("#" + divId).empty();
			}
			
		}
	});
}