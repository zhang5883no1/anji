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
			submitFormForAnji("robot_form","${webcontext}/mainTain/robot/queryRobot",0);
		}
		//add 
		function addCustomer(){
			$("#id").val("");
			$("#robot_form").attr("action","addOrEditRobot");
			submitFormForAnji("robot_form","${webcontext}/mainTain/robot/addOrEditRobot",0);
//			$("#robot_form").submit();
		}
		//edit
        function editCustomer(){
			var selCustomer=$("#tableResults input:radio:checked");
        	if(selCustomer.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	$("#id").val(selCustomer.val());
        	$("#robot_form").attr("action","addOrEditRobot");
			submitFormForAnji("robot_form","${webcontext}/mainTain/robot/addOrEditRobot",0);
//			$("#robot_form").submit();
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
	        	$("#robot_form").attr("action","deleteRobot");
				submitFormForAnji("robot_form","${webcontext}/mainTain/robot/deleteRobot",0);
//		 		$("#robot_form").submit();
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
        <td><span id="lblHeader" class="PageTitle">机器人信息</span></td>
      </tr>
    </tbody>
  </table>
  <form:form id="robot_form" name ="robot_form" action="queryRobot" method="post" modelAttribute="queryRobotBasicInfoDto">
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
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">状态:</span></td>
                  <td nowrap="nowrap">
                  	<select class="userInput" data-type="type_id" name="typeId"> 
                  		<option value="" >未选择</option>
                  		<option value="0" >试用会员</option>
		        		<option value="1" >普通会员</option>
		        		<option value="2" >VIP</option>
		        		<option value="3" >名师</option>
		        		<option value="4" >业务员</option>
		        		<option value="5" >管理员</option>
		        		<option value="6" >钻石VIP</option>
		        		<option value="7" >至尊VIP</option>
		        	</select>
                  </td>
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
                      <ec:table styleClass="DataGrid"  style="height:25px;width:100%;" border="1"  cellpadding="0" cellspacing="0" tableId="tableResults" form="robot_form"
					   items="queryRobotBasicInfoDto.resultList"
					   action="${webcontext}/mainTain/robot/queryCustom"
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
						    <ec:column property="name" title="用户名"/>
						    <ec:column alias="性别" sortable="false">
						    	<select class="userInput" data-type="sex">
						    		<option value="man" <core:if test="${bo.sex=='man'}">selected</core:if>>男</option>
						    		<option value="woman" <core:if test="${bo.sex=='woman'}">selected</core:if>>女</option>
						    	</select>
						    </ec:column>
						    <ec:column alias="类型" sortable="false">
					        	<select class="userInput" data-type="type_id"> 
					        		<option value="0" <core:if test="${bo.type_id=='0'}">selected</core:if>>游客</option>
					        		<option value="1" <core:if test="${bo.type_id=='1'}">selected</core:if>>普通会员</option>
					        		<option value="2" <core:if test="${bo.type_id=='2'}">selected</core:if>>VIP</option>
					        		<option value="3" <core:if test="${bo.type_id=='3'}">selected</core:if>>名师</option>
					        		<option value="4" <core:if test="${bo.type_id=='4'}">selected</core:if>>业务员</option>
					        		<option value="5" <core:if test="${bo.type_id=='5'}">selected</core:if>>管理员</option>
					        		<option value="6" <core:if test="${bo.type_id=='6'}">selected</core:if>>钻石VIP</option>
					        		<option value="7" <core:if test="${bo.type_id=='7'}">selected</core:if>>至尊VIP</option>

					        	</select>
						    </ec:column>
						    <ec:column alias="所属业务员" sortable="false">
						    	<select class="userInput" data-type="userId"> 
						    		<core:forEach items="${queryRobotBasicInfoDto.customerList}" var="cu">
						        		<option value="${cu.id }" <core:if test="${bo.user_id==cu.id}">selected</core:if>>${cu.nickName}</option>
						    		</core:forEach>
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
  						url:"${webcontext}/mainTain/robot/updateInfo?id="+$(this).parent().parent().find("input:radio").val()+"&name="+$(this).attr("data-type")+"&value="+$(this).val()+"&currTime="+(new Date()).getTime(),
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