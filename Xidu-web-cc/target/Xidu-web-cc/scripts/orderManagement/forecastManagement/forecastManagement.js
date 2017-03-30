	//show order Detail
	function showOrderDetail(webPath){
		var selctntype=$("#tableResults_table input:radio:checked");
    	if(selctntype.length==0){
    		alertForAnji("Please select one record.");
    		return;
    	}
    	$("#body").append("<form action=searchDetails method=post name=formx1 id='formx1' style='display:none'>" + "<input type=hidden name=id value="+ selctntype.val() +">" +"</form>" );
    	$("#formx1").submit();
    	//window.open(webPath + "/orderManagement/forecastManagement/searchDetails?id="+selctntype.val());
	}
	//ajax get OperationType dynamic
	function getOperationType(webPath,operationMode){
		var selecter = "#" + operationMode;
		var operationModeCode = $(selecter).find(("option:selected")).val();
	    $.ajax({
	        type: "Post",
	        url: webPath + "/orderManagement/forecastManagement/dynamicSearchOperationType",
	        dataType: "json",
	        data: {"operationMode":operationModeCode},
	        cache: true,
	        success: function(res) {
	                var str = $("#operationType");
	                var option = "";
	                for(var j =0;j < res.OPERATION_TYPE.length; j++)
	                {
	                    option += "<option value=\"" + res.OPERATION_TYPE[j].type + "\">" + res.OPERATION_TYPE[j].type + "</option>";
	                }
	                strVoucherGroupSelect = $(str).html(option).parent().html();
	        }
	    });
	}
