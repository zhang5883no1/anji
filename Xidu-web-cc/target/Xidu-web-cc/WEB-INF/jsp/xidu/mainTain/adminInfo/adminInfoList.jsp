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
			submitFormForAnji("admin_form","${webcontext}/mainTain/adminInfo/queryAdmin",0);
		}
		//add 
		function addCustomer(){
			$("#id").val("");
			$("#admin_form").attr("action","addOrEditAdmin");
			submitFormForAnji("admin_form","${webcontext}/mainTain/adminInfo/addOrEditAdmin",0);
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
        	$("#admin_form").attr("action","addOrEditAdmin");
			submitFormForAnji("admin_form","${webcontext}/mainTain/adminInfo/addOrEditAdmin",0);
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
	        	$("#admin_form").attr("action","deleteAdmin");
				submitFormForAnji("admin_form","${webcontext}/mainTain/adminInfo/deleteAdmin",0);
//		 		$("#custom_form").submit();
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
        <td><span id="lblHeader" class="PageTitle">管理员信息</span></td>
      </tr>
    </tbody>
  </table>
  <form:form id="admin_form" name ="admin_form" action="queryAdmin" method="post" modelAttribute="queryadminBasicInfoDto">
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
                  	<form:input path="status" cssClass="userInput" maxLength="100" />
                  </td>
                  <td align="right" nowrap="nowrap"><span id="lblCity" class="LabelBold">类型:</span></td>
                  <td nowrap="nowrap">
					<form:input path="type"  cssClass="userInput" maxLength="100"/>
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
					   items="queryadminBasicInfoDto.resultList"
					   action="${webcontext}/mainTain/adminInfo/queryAdmin"
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
						    <ec:column property="sex" title="性别"/>
						    <ec:column property="mobile" title="手机"/>
						    <ec:column property="ip" title="ip"/>
						    <ec:column property="lastLoginTime" title="最后登录时间"/>
						    <ec:column alias="类型" sortable="false">
					        	<select class="userInput" data-type="type_id"> 
					        		<option value="1" <core:if test="${bo.type_id=='1'}">selected</core:if>>试用会员</option>
					        		<option value="2" <core:if test="${bo.type_id=='2'}">selected</core:if>>开户会员</option>
					        		<option value="3" <core:if test="${bo.type_id=='3'}">selected</core:if>>名师</option>
					        		<option value="4" <core:if test="${bo.type_id=='4'}">selected</core:if>>业务员</option>
					        		<option value="5" <core:if test="${bo.type_id=='5'}">selected</core:if>>管理员</option>
					        	</select>
						    </ec:column>
						    <ec:column alias="状态" sortable="false">
						    	<select class="userInput" data-type="status"> 
					        		<option value="normal" <core:if test="${bo.status=='normal'}">selected</core:if>>正常</option>
					        		<option value="block" <core:if test="${bo.status=='block'}">selected</core:if>>锁定</option>
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
              <td align="right"><!--<input style="width:90px; text-align:center;" class="Button" type="button"  onClick="addCustomer();" value="Add" name="btnAdd"> -->
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
  						url:"${webcontext}/mainTain/adminInfo/updateInfo?id="+$(this).parent().parent().find("input:radio").val()+"&name="+$(this).attr("data-type")+"&value="+$(this).val()+"&currTime="+(new Date()).getTime(),
  						success:function(msg){
  							
  						}
  					});
  				});
  			}
  		);
  </script> 
  </form:form>
</body>
</html>