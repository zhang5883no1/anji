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
	客户管理
  </title>
	<script LANGUAGE="JavaScript">		
	var id="";
		function search(){
			var chk_value = "";
			$('input[name="test"]:checked').each(function(){
				chk_value = chk_value + $(this).val() + ",";
			});
			chk_value = chk_value.substring(0,chk_value.lastIndexOf(","));
			alert(chk_value);
			$("#checklevel").val(chk_value);
			submitFormForAnji("custom_form","${webcontext}/mainTain/customerinfo/queryCustom",0);
		}
    </script>   
	
<head></head>
<body style="cursor: default;" leftmargin="0" topmargin="0">
 <%SessionUserDto dto=((SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER)); %>
  <%@include file="../../include/head.jsp"%>
   <form:form id="custom_form" name ="custom_form" action="queryCustom" method="post" modelAttribute="querycustomerBasicInfoDto">
 	<form:hidden path="id" id="id"/>
	<div class="main-wrap">
		<div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">客户管理</span></div>
        </div>
		<div class="search-wrap">
            <div class="search-content">
					
					<table class="search-tab">
					<tr><td width="80%">
					<table>
						<tr align="center">
							<td width="130">用户名:</td>
							<td width="130">
								<form:input path="userName" cssClass="userInput" class="common-text" placeholder="用户名" />
							</td> 
							<td width="130">昵称:</td>
							<td width="130">
								<form:input path="nickName" cssClass="userInput" class="common-text" placeholder="昵称" />
							</td> 
							<td width="130">状态:</td>
							<td width="130">
								<form:select path="status" cssClass="userInput">
									<form:option value="">--未选择--</form:option>
									<form:option value="normal">正常</form:option>
									<form:option value="block">锁定</form:option>
								</form:select>
							</td>
						<tr>
						<tr align="center">	
							<td width="130">分组:</td>
							<td width="130">
								<form:select path="groupCode" cssClass="userInput" id="_group_select">
									<form:option value="">--未选择--</form:option>
									<core:forEach items="${querycustomerBasicInfoDto.grouplist}" var="group">
										<form:option value="${group.userGroupCode }" >${group.userGroupName}</form:option>
									</core:forEach>
								</form:select>
							</td>						
							<td width="130">业务员:</td>
							<td width="130">
								<form:select path="userId" cssClass="userInput">
									<form:option value="">--未选择--</form:option>
									<core:forEach items="${querycustomerBasicInfoDto.grouplist}" var="group">
										<core:forEach items="${group.userlist}" var="users">
											<form:option value="${users.id }" class="_users_${group.userGroupCode}">${users.name}</form:option>
										</core:forEach>
									</core:forEach>
								</form:select>
							</td>
							<td width="130">房间:</td>
							<td width="130">
								<form:select path="roomId" cssClass="userInput">
									<form:option value="">--未选择--</form:option>
									<core:forEach items="${querycustomerBasicInfoDto.rooms}" var="roomlist">
										<form:option value="${roomlist.id }">${roomlist.roomName }</form:option>
									</core:forEach>
								</form:select>
							</td>
						<tr>
						<tr align="center">
							<td width="130">等级:</td>
							<td width="390" colspan="5">
							<input type="checkbox" name="test" value="0" >游客&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="test" value="1" >会员&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="test" value="2" >VIP&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="test" value="3" >铂金&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="test" value="4" >钻石&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="test" value="5" >皇冠&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="test" value="6" >金冠&nbsp;&nbsp;&nbsp;&nbsp;
							
							<input id="checklevel" type="hidden" name="level" value="">
							</td>
						</tr>
						</table>
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
                <div class="result-content">
					<table class="result-tab" width="100%">
							<tr><td>
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
						    <ec:column property="mobile" title="电话"/>
						    <ec:column alias="QQ号码" sortable="false" property="qq" title="QQ号码" >
								<input type='text' name='qq' value='${bo.qq }'/>
							</ec:column>
						    <ec:column property="createDate" title="注册时间"/>
						    <ec:column property="lastLoginTime" title="最后登陆时间"/>
						    <ec:column alias="类型" sortable="false">
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
						    <ec:column alias="状态" sortable="false">
						    	<select class="userInput" data-type="status"> 
					        		<option value="normal" <core:if test="${bo.status=='normal'}">selected</core:if>>正常</option>
					        		<option value="block" <core:if test="${bo.status=='block'}">selected</core:if>>锁定</option>
					        	</select>
						    </ec:column>
						    
						     <ec:column alias="所属人" sortable="false">
						    	<select class="userInput" data-type="userId"> 
					        		<core:forEach items="${querycustomerBasicInfoDto.rooms}" var="rooms">
					        			<core:if test="${rooms.id==bo.roomNo}">
						        			<core:forEach items="${rooms.userList}" var="users">
						        				<option value="${users.id }" <core:if test="${users.id==bo.userId}">selected</core:if>>${users.employerOwnerId }</option>
						        			</core:forEach>
					        			</core:if>
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
  				$("#tableResults select").change(function(){
  					$.ajax({
  						method:"GET",
  						url:"${webcontext}/mainTain/customerinfo/updateInfo?id="+$(this).parent().parent().find("input:radio").val()+"&name="+$(this).attr("data-type")+"&value="+$(this).val()+"&currTime="+(new Date()).getTime(),
  						success:function(msg){
  							
  						}
  					});
  				});
  				
  				
  				$("#_group_select").change(function(){
  					$("#userId option:gt(0)").hide();
  					$("._users_"+$(this).val()).show();
  				});
  			}
  		);
  </script> 
</body>
</html>