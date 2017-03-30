<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.xidu.framework.usermgnt.constant.WorkbenchConstant"%>
<%@page import="com.xidu.framework.usermgnt.dto.SessionUserDto"%>
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
			submitFormForAnji("custom_form","${webcontext}/mainTain/customerinfo/queryCustom",0);
		}
		//add 
		function addCustomer(){
			$("#id").val("");
			$("#custom_form").attr("action","addOrEditCustom");
			submitFormForAnji("custom_form","${webcontext}/mainTain/customerinfo/addOrEditCustom",0);
//			$("#custom_form").submit();
		}
		//edit
        function editCustomer(){
			var selCustomer=$("#tableResults input:radio:checked");
        	if(selCustomer.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	$("#id").val(selCustomer.val());
        	$("#custom_form").attr("action","addOrEditCustom");
			submitFormForAnji("custom_form","${webcontext}/mainTain/customerinfo/addOrEditCustom",0);
//			$("#custom_form").submit();
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
	        	$("#custom_form").attr("action","deleteCustom");
				submitFormForAnji("custom_form","${webcontext}/mainTain/customerinfo/deleteCustom",0);
//		 		$("#custom_form").submit();
		    }
        }
		//assign
		function assignCustomer(){
			var selCustomer=$("#tableResults input:radio:checked");
        	if(selCustomer.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	if(confirm('Do you confirm to assign this record?')){
	        	id=selCustomer.val();
	        	$("#iframe_div iframe").attr("src","${webcontext}/mainTain/customerinfo/queryMaster");
	        	$("#iframe_div").show();
		    }
		}
    </script>   
	
<head></head>
<body style="cursor: default;" leftmargin="0" topmargin="0">
 <%SessionUserDto dto=((SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER)); %>
  <%@include file="../../include/head.jsp"%>
   
  <br>
  <table border="0" cellspacing="2" width="100%">
    <tbody>
      <tr>
        <td><span id="lblHeader" class="PageTitle">客户信息</span></td>
      </tr>
    </tbody>
  </table>
  <form:form id="custom_form" name ="custom_form" action="queryCustom" method="post" modelAttribute="querycustomerBasicInfoDto">
 	<form:hidden path="id" id="id"/>
  <span id="UpdatePanelSearch">
  <table cellpadding="3" cellspacing="1" width="100%">
    <tbody>
      <tr>
        <td><fieldset>
            <legend id="legSearchCriteria" class="legend">查询条件</legend>
            <table border="0" cellpadding="1" cellspacing="1" width="100%">
              <tbody>
                <tr>
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">用户名:</span></td>
                  <td nowrap="nowrap">
                  	<form:input path="userName"  cssClass="userInput" maxLength="100"/> 
                  </td>
                  <td align="right" nowrap="nowrap"><span id="lblIsHub" class="LabelBold">状态:</span></td>
                  <td nowrap="nowrap">
                  	<form:select path="status" cssClass="userInput">
                  		<form:option value="">--未选择--</form:option>
                  		<form:option value="normal">正常</form:option>
                  		<form:option value="block">锁定</form:option>
                  	</form:select>
                  </td>
                  <td align="right" nowrap="nowrap"><span id="lblCity" class="LabelBold">类型:</span></td>
                  <td nowrap="nowrap">
					<form:select path="type" cssClass="userInput">
                  		<form:option value="">--未选择--</form:option>
                  		<form:option value="0">游客</form:option>
                  		<form:option value="1">试用会员</form:option>
                  		<form:option value="2">开户会员</form:option>
                  	</form:select>
                   </td>
                </tr>
                <tr>
                  <%if(dto.getRole().getId()==1||dto.getRole().getId()==2){%>
	                  <td align="right" nowrap="nowrap"><span id="lblAliasType" class="LabelBold">所属业务员:</span></td>
	                  <td nowrap="nowrap">
	                  	<form:select path="userId" cssClass="userInput">
	                  		<form:option value="">--未选择--</form:option>
	                  		<core:forEach items="${querycustomerBasicInfoDto.adminList}" var="cu">
								<form:option value="${cu.id }" >${cu.nickName}</form:option>
							</core:forEach>
	                  	</form:select>
	                  </td>
                  <%}%>
                  <td align="right" nowrap="nowrap"><span id="lblAliasType" class="LabelBold">成交状态:</span></td>
                  <td nowrap="nowrap">
                  	<form:select path="business" cssClass="userInput">
                  		<form:option value="">--未选择--</form:option>
                  		<form:option value="0">未成交</form:option>
                  		<form:option value="1">已成交</form:option>
                  	</form:select>
                  </td>
                  <td align="right" nowrap="nowrap"><span id="lblAliasType" class="LabelBold">开户状态:</span></td>
                  <td nowrap="nowrap">
                  	<form:select path="openAccount" cssClass="userInput">
                  		<form:option value="">--未选择--</form:option>
                  		<form:option value="0">开户已签约</form:option>
                  		<form:option value="1">开户未签约</form:option>
                  		<form:option value="2">开户入金</form:option>
                  	</form:select>
                  </td>
                </tr>
                
              </tbody>
            </table>

            <hr style="width:99%;">
            <table border="0" cellpadding="1" cellspacing="1" width="100%">
              <tbody>
                <tr>
                  <td style="padding-right:2px;" align="right" nowrap="nowrap"><span id="lblMaxRows" class="LabelBold">返回行数:</span>
                    <form:input id="pagerSize" path="pager.pageSize" cssStyle="width:40px;"/>
                    <input name="btnSearch" value="查询" onClick="search();" id="btnSearch" class="Button" type="button"  style="width:90px;" type="button"/>
                    <input name="btnSearch" value="重置" onClick="clearAll();" id="btnSearch" class="Button" type="button"  style="width:90px;" type="button"/>
                    </td>
                </tr>
              </tbody>
            </table>
          </fieldset></td>
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
                      <ec:table styleClass="DataGrid"  style="height:25px;width:100%;" border="1"  cellpadding="0" cellspacing="0" tableId="tableResults" form="custom_form"
					   items="querycustomerBasicInfoDto.resultList"
					   action="${webcontext}/mainTain/customerinfo/queryCustom"
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
						 	<ec:column alias="选择" sortable="false">
						 		 <input type='radio' name='selId' value='${bo.id }'/>
						 	</ec:column>
						    <ec:column property="userName" title="用户名"/>
						    <ec:column property="nickName" title="昵称"/>
						    <ec:column property="createDate" title="注册时间"/>
						    <ec:column alias="类型" sortable="false">
					        	<select class="userInput" data-type="type_id"> 
					        		<option value="0" <core:if test="${bo.type_id=='0'}">selected</core:if>>游客</option>
					        		<option value="1" <core:if test="${bo.type_id=='1'}">selected</core:if>>普通会员</option>
					        		<option value="2" <core:if test="${bo.type_id=='2'}">selected</core:if>>VIP</option>
					        		<option value="6" <core:if test="${bo.type_id=='6'}">selected</core:if>>钻石VIP</option>
					        		<option value="7" <core:if test="${bo.type_id=='7'}">selected</core:if>>至尊VIP</option>
					        	</select>
						    </ec:column>
						  <%if(dto.getRole().getId()==1||dto.getRole().getId()==2){%>
						    <ec:column alias="所属业务员" sortable="false">
						    	<select class="userInput" data-type="userId"> 
						    		<option value="0" <core:if test="${bo.userId==null}">selected</core:if>>未选择</option>
						    		<core:forEach items="${querycustomerBasicInfoDto.adminList}" var="cu">
						        		<option value="${cu.id }" <core:if test="${bo.userId==cu.id}">selected</core:if>>${cu.nickName}</option>
						    		</core:forEach>
					        	</select>
						    </ec:column>
					        <%} %>
						    <ec:column alias="状态" sortable="false">
						    	<select class="userInput" data-type="status"> 
					        		<option value="normal" <core:if test="${bo.status=='normal'}">selected</core:if>>正常</option>
					        		<option value="block" <core:if test="${bo.status=='block'}">selected</core:if>>锁定</option>
					        	</select>
						    </ec:column>
						    <ec:column alias="成交状态" sortable="false">
						    	<select class="userInput" data-type="business"> 
					        		<option value="0" <core:if test="${bo.business=='0'}">selected</core:if>>未成交</option>
					        		<option value="1" <core:if test="${bo.business=='1'}">selected</core:if>>已成交</option>
					        	</select>
						    </ec:column>
						    <ec:column alias="签约状态" sortable="false">
						    	<select class="userInput" data-type="openAccount"> 
					        		<option value="0" <core:if test="${bo.openAccount=='0'}">selected</core:if>>未开户</option>
					        		<option value="1" <core:if test="${bo.openAccount=='1'}">selected</core:if>>开户未签约</option>
					        		<option value="2" <core:if test="${bo.openAccount=='2'}">selected</core:if>>开户已签约</option>
					        		<option value="3" <core:if test="${bo.openAccount=='3'}">selected</core:if>>开户入金</option>
					        	</select>
						    </ec:column>
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
	             <input style="width:90px; text-align:center;" class="Button" type="button"  id="btnReset" onclick="exportToExcel();" value="导出" name="btnDelete">
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
  				//var selctntype=$("#tableResults input:radio");
  				//$.each(selctntype, function(i, n){
  				//	if($(n).parent().parent().children().eq($(n).parent().parent().children().length-1).html()=="1"){
  				//		$(n).parent().parent().children().eq($(n).parent().parent().children().length-1).html("active");
  				//	}else{
  				//		$(n).parent().parent().children().eq($(n).parent().parent().children().length-1).html("deactivate")
  				//	}
  				//});
  				$("#tableResults select").change(function(){
  					$.ajax({
  						method:"GET",
  						url:"${webcontext}/mainTain/customerinfo/updateInfo?id="+$(this).parent().parent().find("input:radio").val()+"&name="+$(this).attr("data-type")+"&value="+$(this).val()+"&currTime="+(new Date()).getTime(),
  						success:function(msg){
  							
  						}
  					});
  				});
  			}
  		);
  		 function exportToExcel(){
  			 $("#custom_form").attr("enctype","multipart/form-data");
  			submitFormForAnji('custom_form','${webcontext}'+ '/mainTain/customerinfo/exportExcel',"2")
  		 }
  </script> 
  </form:form>
</body>
</html>