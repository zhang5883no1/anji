<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>

  </title>
	<script type='text/javascript' src='${jspath}/My97DatePicker/WdatePicker.js'></script>
	<script LANGUAGE="JavaScript">		
		function search(){
			submitFormForAnji("inter_sup_form","${webcontext}/ebooking/internationalsupplier/queryInterSup",0);
//			$("#inter_sup_form").submit();
		}
		//add 
		function addInterSup(){
			$("#id").val("");
			$("#inter_sup_form").attr("action","addOrEditInterSup");
			submitFormForAnji("inter_sup_form","${webcontext}/ebooking/internationalsupplier/addOrEditInterSup",0);
//			$("#inter_sup_form").submit();
		}
		//edit
        function editInterSup(){
			var selctnsize=$("#tableResults input:radio:checked");
        	if(selctnsize.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	$("#id").val(selctnsize.val());
        	$("#inter_sup_form").attr("action","addOrEditInterSup");
			submitFormForAnji("inter_sup_form","${webcontext}/ebooking/internationalsupplier/addOrEditInterSup",0);
//			$("#inter_sup_form").submit();
        }
		//delete
        function deleteInterSup(){
        	var selctnsize=$("#tableResults input:radio:checked");
        	if(selctnsize.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	$.ajax({
				method:"GET",
				url:"${webcontext}/ebooking/internationalsupplier/checkTime"+"?currTime="+(new Date()).getTime(),
				success:function(msg){
					if(msg=="false"){
						alertForAnji("Current time is beyond 11:00, please contact your system administrator deleted.",null);
					}else{
						if(comfirmForAnji('Do you confirm to delete this record?',null)){
				        	$("#id").val(selctnsize.val());
				        	$("#inter_sup_form").attr("action","deleteInterSup");
							submitFormForAnji("inter_sup_form","${webcontext}/ebooking/internationalsupplier/deleteInterSup",0);
//					 		$("#inter_sup_form").submit();
					    }
					}
				}
			});
        	
        }
		
		function showDetail(){
			var selctnsize=$("#tableResults input:radio:checked");
        	if(selctnsize.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	$("#id").val(selctnsize.val());
        	$("#inter_sup_form").attr("action","showDetail");
			submitFormForAnji("inter_sup_form","${webcontext}/ebooking/internationalsupplier/showDetail",0);
//	 		$("#inter_sup_form").submit();
		}
		
		//edit active
		function editActive(id){
			$("#id").val(id);
			$("#inter_sup_form").attr("action","editActive");
			submitFormForAnji("inter_sup_form","${webcontext}/ebooking/internationalsupplier/editActive",0);
//	 		$("#inter_sup_form").submit();
		}
		
		function openDetail(button){
			//var isvalue=button.parent().parent().find("td:eq(0) input:radio").val();
			window.open('showDetail?id='+button, 'add', 'width=1050,height=510,left=10,top=20,location=yes,status=yes,scrollbars=yes');
		}
    </script>   
	
<head></head>
<body style="cursor: default;" leftmargin="0" topmargin="0">
 
  <%@include file="../../include/head.jsp"%>
   
  <br>
  <table border="0" cellspacing="2" width="100%">
    <tbody>
      <tr>
        <td><span id="lblHeader" class="PageTitle">International Supplier</span></td>
      </tr>
    </tbody>
  </table>
  <form:form id="inter_sup_form" name ="inter_sup_form" action="queryInterSup" method="post" modelAttribute="intersupDto">
 	<form:hidden path="id" id="id"/>
  <span id="UpdatePanelSearch">
  <table cellpadding="3" cellspacing="1" width="100%">
    <tbody>
      <tr>
        <td><fieldset>
            <legend id="legSearchCriteria" class="legend">Search Criteria</legend>
            <table border="0" cellpadding="1" cellspacing="1" width="100%">
              <tbody>
                <tr>
                 <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">International Supplier No:</span></td>
                  <td nowrap="nowrap">
                  	<form:input path="internationSupplierNo"  cssClass="userInput integer" maxLength="50"/> 
                  </td>
                
                 <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">Status:</span></td>
                  <td nowrap="nowrap">
                  	<form:select path="status"  cssClass="userInput">
                  	    <form:option value="">All</form:option>
	                  	<form:option value="PENDING">Pending</form:option>
	                  	<form:option value="APPROVED">Approved</form:option>
	                  	<form:option value="DISAPPROVED">Disapproved</form:option>
	                  	<form:option value="CANCELLED">Cancelled</form:option>
                  	</form:select> 
                  </td>
                
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">Pickup Date:</span></td>
                  <td nowrap="nowrap">
                  	<form:input path="pickUpDate"  cssClass="userInput Wdate" maxLength="50" onfocus="WdatePicker()" readonly="true"/> 
                  </td>
                  
                </tr>
                
                 <tr>
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">Part No:</span></td>
                  <td nowrap="nowrap">
                  	<form:input path="partno"  cssClass="userInput integer" maxLength="50"/> 
                  </td>
                  
                </tr>
                
              </tbody>
            </table>
            <hr style="width:99%;">
            <table border="0" cellpadding="1" cellspacing="1" width="100%">
              <tbody>
                <tr>
                  <td style="padding-left:2px"><span><span class="Label" style="color:Red;">* will match one or more words.</span>&nbsp;<img  src="${imagepath}/i.gif" style="border-width:0px;"></span></td>
                  <td style="padding-right:2px;" align="right" nowrap="nowrap"><span id="lblMaxRows" class="LabelBold">Rows Returned:</span>
                    <form:input id="pagerSize" path="pager.pageSize" cssStyle="width:40px;" class="RequiredField userInput integer"/>
                    <input name="btnSearch" value="Search" onClick="search();" id="btnSearch" class="Button" type="button"  style="width:90px;" type="button"/>
                    <input name="btnSearch" value="Reset" onClick="clearAll();" id="btnSearch" class="Button" type="button"  style="width:90px;" type="button"/>
                    </td>
                </tr>
              </tbody>
            </table>
          </fieldset></td>
      </tr>
    </tbody>
  </table>
  </span> 
  
  <%@include file="../../include/page.jsp"%>

  <table id="tableResults" cellpadding="1" cellspacing="1" width="100%">
    <tbody>
      <tr>
        <td><table border="0" cellpadding="1" cellspacing="1" width="100%">
            <tbody>
              <tr>
                <td colspan="2">
                <div id="resizableDiv" class="DivResultsGrid" style="OVERFLOW: auto;border-top:solid 1px silver">
                    <ec:table styleClass="DataGrid"  style="height:25px;width:100%;" border="1"  cellpadding="0" cellspacing="0" tableId="tableResults" form="inter_sup_form"
					   items="intersupDto.resultList"
					   action="${webcontext}/ebooking/internationalsupplier/queryInterSup"
					   imagePath="${webcontext}/images/table/*.gif"
					   filterable="false"
					   sortable="true"
  					   showPagination="false"
  					   autoIncludeParameters="false"
  					   showTitle="false"
  					   showStatusBar="false"
  					   var="bo"
					   >
						 <ec:row>
							<ec:column alias="Select"  sortable="false">
						 		 <input type='radio' name='selId' value='${bo.id }'/>
						 	</ec:column>						 	
						    <ec:column property="internationSupplierNo" title="International Supplier No">
						      <a href="javascript:openDetail('${bo.id }');" >${bo.internationSupplierNo}</a>
						    </ec:column>
						    <ec:column property="omsNo" title="Order No"/>
						    <ec:column property="shipperCode" title="Shipper Code"/>
						    <ec:column property="status" title="Status"/>
						    <ec:column property="cnee" title="Des Name"/>
						    <ec:column property="pol" title="POL"/>
						    <ec:column property="pod" title="POD"/>
						    <ec:column property="tranPort" title="Tran Port"/>
						    <ec:column property="hsCode" title="Hs Code"/>
						    <ec:column property="totalPallets" title="Package "/>
						    <ec:column property="deliveryDate" title="Delivery date"/>
						    <ec:column property="plantDate" title="Plant date"/>
						    <ec:column property="readyDate" title="Ready date"/>
						    <ec:column property="pickUpDate" title="Pick up date"/>
						    <ec:column property="eta" title="ETA" />
						    <ec:column property="arrivalDate" title="Arrival Date"/>
						    <ec:column property="transportationMode" title="Transportation Mode"/>
						    <ec:column property="container" title="Container"/>
						    <ec:column property="totalGross" title="Weight/Pallet" />
						    <ec:column property="special_instructions" title="Remarks"/>
						    <ec:column property="active" title="Active"/>
						 </ec:row>
					</ec:table>
                  </div></td>
              </tr>
              
            </tbody>
          </table>
          <table style="width:100%">
            <tr>
            
              <td align="right">
              <%
					String type = (String) request.getSession().getAttribute("USERTYPE");
													if (!"IT".equals(type)&&!"OPERATION".equals(type)) {
											%>
              <input style="width:90px; text-align:center;" class="Button" type="button"  onClick="addInterSup();" value="Add" name="btnAdd">
              <%} %>
              <input style="width:90px; text-align:center;" class="Button" type="button"  id="btnReset" onClick="editInterSup();" value="Edit" name="btnEdit">
             <input style="width:90px; text-align:center;" class="Button" type="button"  id="btnReset" onclick="deleteInterSup();" value="Delete" name="btnDelete"> 
             <input name="btnEdit" value="Details" onClick="showDetail();" id="btnReset" class="Button" type="button"  style="width:90px; text-align:center;">
              </td>
            </tr>
          </table>
          </td>
      </tr>
    </tbody>
  </table>
   </span>
  <span id="PageMessageUpdatePanel"></span> 
  <script LANGUAGE="JavaScript">	
	$(document).ready(
  			function(){
  				var selctntype=$("#tableResults input:radio");
  				$.each(selctntype, function(i, n){
  					if($(n).parent().parent().children().eq($(n).parent().parent().children().length-1).html()=="1"){
  						//$(n).parent().parent().children().eq($(n).parent().parent().children().length-1).html("active");
  						$(n).parent().parent().children().eq($(n).parent().parent().children().length-1).html("<input style='width:90px; text-align:center;' class='Button' type='button' id='btnEditActive' onClick='editActive("+$(n).val()+");' name='btnEditActive' value='deactivate'>");
  					}else{
  						//$(n).parent().parent().children().eq($(n).parent().parent().children().length-1).html("deactivate")
  						$(n).parent().parent().children().eq($(n).parent().parent().children().length-1).html("<input style='width:90px; text-align:center;' class='Button' type='button' id='btnEditActive' onClick='editActive("+$(n).val()+");' name='btnEditActive' value='active'>");

  					}
  				});
  			}
  		);
  </script> 
  </form:form>
</body>
</html>