<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>

  </title>
	<script LANGUAGE="JavaScript">		
	var id="";
		function search(){
			submitFormForAnji("bill_form","${webcontext}/mainTain/bill/queryBill",0);
		}
		//add 
		function addCustomer(){
			$("#id").val("");
			$("#bill_form").attr("action","addOrEditBill");
			submitFormForAnji("bill_form","${webcontext}/mainTain/bill/addOrEditBill",0);
//			$("#bill_form").submit();
		}
		//edit
        function editCustomer(){
			var selCustomer=$("#tableResults input:radio:checked");
        	if(selCustomer.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	$("#id").val(selCustomer.val());
        	$("#bill_form").attr("action","addOrEditBill");
			submitFormForAnji("bill_form","${webcontext}/mainTain/bill/addOrEditBill",0);
//			$("#bill_form").submit();
        }
		//delete
        function deleteCustomer(){
        	var selCustomer=$("#tableResults input:radio:checked");
        	if(selCustomer.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	if(confirm('Do you confirm to delete this record?')){
	        	$("#id").val(selCustomer.val());
	        	$("#bill_form").attr("action","deleteBill");
				submitFormForAnji("bill_form","${webcontext}/mainTain/bill/deleteBill",0);
//		 		$("#bill_form").submit();
		    }
        }
    </script>   
	
<head></head>
<body style="cursor: default;" leftmargin="0" topmargin="0">
 
  <%@include file="../../include/head.jsp"%>
   
  <br>
  <table border="0" cellspacing="2" width="100%">
    <tbody>
      <tr>
        <td><span id="lblHeader" class="PageTitle">喊单记录</span></td>
      </tr>
    </tbody>
  </table>
  <form:form id="bill_form" name ="bill_form" action="queryBill" method="post" modelAttribute="queryBillDto">
 	<form:hidden path="id" id="id"/>
  <span id="UpdatePanelSearch">
  <table cellpadding="3" cellspacing="1" width="100%">
    <tbody>
      <tr>
        <td><!-- <fieldset>
            <legend id="legSearchCriteria" class="legend">Search Criteria</legend>
            <table border="0" cellpadding="1" cellspacing="1" width="100%">
              <tbody>
                <tr>
                  <td align="right" nowrap="nowrap"><span id="lblIsHub" class="LabelBold">所属业务员:</span></td>
                  <td nowrap="nowrap">
			    	<select class="userInput" name="userId" data-type="userId"> 
			    		<option value="0" >---未选择---</option>
			    		<core:forEach items="${queryRobotBasicInfoDto.customerList}" var="cu">
			        		<option value="${cu.id }" >${cu.nickName}</option>
			    		</core:forEach>
		        	</select>
                  </td>
                </tr>
                
              </tbody>
            </table>

            <hr style="width:99%;">
            <table border="0" cellpadding="1" cellspacing="1" width="100%">
              <tbody>
                <tr>
                  <td style="padding-right:2px;" align="right" nowrap="nowrap"><span id="lblMaxRows" class="LabelBold">Rows Returned:</span>
                    <form:input id="pagerSize" path="pager.pageSize" cssStyle="width:40px;"/>
                    <input name="btnSearch" value="Search" onClick="search();" id="btnSearch" class="Button" type="button"  style="width:90px;" type="button"/>
                    <input name="btnSearch" value="Reset" onClick="clearAll();" id="btnSearch" class="Button" type="button"  style="width:90px;" type="button"/>
                    </td>
                </tr>
              </tbody>
            </table>
          </fieldset> --></td>
      </tr>
      <tr>
      <td>   <%@include file="../../include/page.jsp"%>                
  
  <table id="tableResults" cellpadding="1" cellspacing="1" width="100%">
    <tbody>
      <tr>
        <td><table border="0" cellpadding="1" cellspacing="1" width="100%">
            <tbody>
              <tr>
                <td colspan="2"><div id="resizableDiv" class="DivResultsGrid" style="OVERFLOW: auto;border-top:solid 1px silver">
                      <ec:table styleClass="DataGrid"  style="height:25px;width:100%;" border="1"  cellpadding="0" cellspacing="0" tableId="tableResults" form="bill_form"
					   items="queryBillDto.resultList"
					   action="${webcontext}/mainTain/bill/queryBill"
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
						 	<ec:column alias="Select" sortable="false">
						 		 <input type='radio' name='selId' value='${bo.id }'/>
						 	</ec:column>
						    <ec:column property="openTime" title="开仓时间"/>
						    <ec:column property="type" title="类型"/>
						    <ec:column property="position" title="仓位%"/>
						    <ec:column property="product" title="商品"/>
						    <ec:column property="openPrice" title="开仓价"/>
						    <ec:column property="stopLossPrice" title="止损价"/>
						    <ec:column property="stopGainPrice" title="止盈价"/>
						    <ec:column property="closingTime" title="平仓时间"/>
						    <ec:column property="closingPrice" title="平仓价"/>
						    <ec:column property="gainPercent" title="获利点数"/>
						    <ec:column property="detail" title="备注"/>
						    <ec:column property="teacherName" title="分析师"/>
						    <ec:column property="status" title="状态"/>
						    <ec:column property="createDate" title="创建时间"/>
						 </ec:row>
					</ec:table>
                    
                  </div></td>
              </tr>
              
            </tbody>
          </table>
          <table style="width:100%">
            <tr>
              <td align="right"><input style="width:90px; text-align:center;" class="Button" type="button"  onClick="addCustomer();" value="新增" name="btnAdd"> 
              <input style="width:90px; text-align:center;" class="Button" type="button"  id="btnReset" onClick="editCustomer();" value="修改" name="btnEdit">
             <input style="width:90px; text-align:center;" class="Button" type="button"  id="btnReset" onclick="deleteCustomer();" value="删除" name="btnDelete">
             </td>
            </tr>
          </table>
          </td>
      </tr>
    </tbody>
  </table>
   </span>
  <span id="PageMessageUpdatePanel"></span> </td>
      </tr>
    </tbody>
  </table>
  </span> 
 
 <div id="iframe_div" style="display:none">
	 <iframe src="#"></iframe>
 </div>

<script LANGUAGE="JavaScript">	
  		$(document).ready(
  			function(){
  				
  			}
  		);
  </script> 
  </form:form>
</body>
</html>