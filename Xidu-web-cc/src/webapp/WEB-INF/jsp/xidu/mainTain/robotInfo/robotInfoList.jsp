<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
机器人管理
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
	
<head>
</head>
<body style="cursor: default;" leftmargin="0" topmargin="0">
<%@include file="../../include/head.jsp"%>
<form:form id="robot_form" name ="robot_form" action="queryRobot" method="post" modelAttribute="queryRobotBasicInfoDto">
	<form:hidden path="id" id="id"/>
	<div class="main-wrap">
		<div class="crumb-wrap">
			<div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">机器人管理</span></div>
	</div>
	<div class="search-wrap">
		<div class="search-content">
			<table class="search-tab">
				<tr align="center">
					<td width="130">分组:</td>
					<td>
						<form:select path="groupId" cssClass="userInput" id="_group_select">
							<form:option value="">--未选择--</form:option>
							<core:forEach items="${queryRobotBasicInfoDto.grouplist}" var="group">
								<form:option value="${group.userGroupCode }" >${group.userGroupName}</form:option>
							</core:forEach>
						</form:select>
					</td>
					<td width="130">业务员:</td>
					<td>
						<form:select path="userId" cssClass="userInput">
							<form:option value="">--未选择--</form:option>
							<core:forEach items="${queryRobotBasicInfoDto.grouplist}" var="group">
								<core:forEach items="${group.userlist}" var="users">
									<form:option value="${users.id }" class="_users_${group.userGroupCode}">${users.name}</form:option>
								</core:forEach>
							</core:forEach>
						</form:select>
					</td>
					<td style="padding-right:2px;" align="right" nowrap="nowrap"><span id="lblMaxRows" class="LabelBold">返回行数:</span>
						<form:input id="pagerSize" path="pager.pageSize" cssStyle="width:40px;"/>
						<input name="btnSearch" value="查询" onClick="search();" id="btnSearch" class="btn btn-primary btn2" type="button"/>
						<input name="btnSearch" value="清空" onClick="clearAll();" id="btnSearch" class="btn btn-primary btn2" type="button"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="result-wrap">
		<div class="result-title">
			<div class="result-list">
				<a href="javascript:void(0)" onClick="addCustomer();" name="btnAdd">新增</a>
				<a id="btnReset" href="javascript:void(0)" onClick="editCustomer();">修改</a>
				<a id="btnReset" href="javascript:void(0)" onclick="deleteCustomer();">删除</a>
			</div>
		</div>
		<div class="result-content">
			<table id="tableResults" class="result-tab" width="100%">
				<tr><td>
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
					 	<ec:column alias="选择" sortable="false">
					 		 <input type='radio' name='selId' value='${bo.id }'/>
					 	</ec:column>
					    <ec:column property="name" title="昵称"/>
					    <ec:column alias="等级" sortable="false">
				        	<select class="userInput" data-type="level"> 
				        		<option value="0" <core:if test="${bo.level=='0'}">selected</core:if>>游客</option>
				        		<option value="1" <core:if test="${bo.level=='1'}">selected</core:if>>普通会员</option>
				        		<option value="2" <core:if test="${bo.level=='2'}">selected</core:if>>VIP</option>
				        		<option value="3" <core:if test="${bo.level=='3'}">selected</core:if>>铂金</option>
				        		<option value="4" <core:if test="${bo.level=='4'}">selected</core:if>>钻石</option>
				        		<option value="5" <core:if test="${bo.level=='5'}">selected</core:if>>皇冠</option>
				        		<option value="6" <core:if test="${bo.level=='6'}">selected</core:if>>金冠</option>
				        	</select>
					    </ec:column>
					    <ec:column alias="所属业务员" sortable="false">
					    	<select class="userInput _users_info" data-type="userId"> 
					    		
					    		<core:forEach items="${admins}" var="as">
						    			<core:forEach items="${queryRobotBasicInfoDto.rooms }" var="rooms">
					        			<core:forEach items="${rooms.userList}" var="users">
					        				<core:if test="${as.id==users.employerOwnerId }">
						        				<option class="${as.id }" value="${as.id }" <core:if test="${as.id==bo.userId}">selected</core:if>>${as.userName }</option>
					        				</core:if>
					        			</core:forEach>
				        			</core:forEach>
				        		</core:forEach>
				        	</select>
					    </ec:column>
						 </ec:row>
			</ec:table>					
				</td></tr>
							
				</table>
				<div class="list-page">
				<%@include file="../../include/page.jsp"%>
				</div>
			</div>
		</div>
	</form:form>
	</div>
 
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
  				
  				$("#_group_select").change(function(){
  					$("#userId option:gt(0)").hide();
  					$("._users_"+$(this).val()).show();
  				});
  				
  				
  				var select=$("._users_info");
  				for(var j=0;j<select.length;j++){
  					var s=select.get(j);
	  				var options=$(s).find("option");
	  				for(var i=0;i<options.length;i++){
	  					var o=options.get(i);
	  					if($(s).find("."+$(o).val()).length>1){
	  						$(s).find("."+$(o).val()+":gt(0)").remove();
	  					}
	  				}
  				}
  			}
  		);
  </script> 
</body>
</html>

