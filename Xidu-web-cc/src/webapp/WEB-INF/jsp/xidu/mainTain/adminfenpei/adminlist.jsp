<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	管理员分配
  </title>
  <script LANGUAGE="JavaScript">
		//query
		function search(){
			submitFormForAnji("admin_form","${webcontext}/mainTain/adminInfo/queryadmin",0);
		}
		//save
		function saveadmin(){
			$("#btnDone").css("display","none");
			var chk_value = "";
			$('input[name="test"]:checked').each(function(){
				chk_value = chk_value + $(this).val() + ",";
			});
			chk_value = chk_value.substring(0,chk_value.lastIndexOf(","));
			$("#checklist").val(chk_value);
			$("#admin_form").attr("action","saveadmin");
			submitFormForAnji("admin_form","${webcontext}/mainTain/adminInfo/saveadmin",null);
		}
		//edit
        function editadmin(){
			$("#btnDone").css("display","");
			
        }
	</script>
 </head>
<body leftMargin="0" topMargin="0">
<%@include file="../../include/head.jsp"%>
	<form:form  id="admin_form" name ="admin_form" action="queryadmin" method="post" modelAttribute="RoomManagers">
	<div class="main-wrap">
		<div class="crumb-wrap">
			<div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">管理员分配</span></div>
		</div>
		<div class="search-wrap">
            <div class="search-content">
					<table class="search-tab">
					<tr><td width="80%">
					<table>
						<tr align="center">
							<td width="130" style="text-align:right;">房间:</td>
							<td width="130">
								<form:select path="id" cssClass="userInput">
									<form:option value="1">--未选择--</form:option>
									<core:forEach items="${RoomsList}" var="roomlist">
										<form:option value="${roomlist.id }">${roomlist.roomName }</form:option>
									</core:forEach>
								</form:select>
							</td>
						<tr>
						</table>
						<td style="padding-right:2px;" align="right" nowrap="nowrap"><span id="lblMaxRows" class="LabelBold">返回行数:</span>
								
								<input name="btnSearch" value="查询" onClick="search();" id="btnSearch" class="btn btn-primary btn2" type="button"/>
								<input name="btnSearch" value="清空" onClick="clearAll();" id="btnSearch" class="btn btn-primary btn2" type="button"/>
							</td>

						</tr>
					</table>
            </div>
        </div>
		<div class="result-title">
		<div class="result-wrap">
			<div class="result-content">
				<table class="insert-tab" width="100%" id="tableResults">
					<tbody>
						<tr><td colspan="6">
							<div class="result-list">
								<a id="btnReset" href="javascript:void(0)" onclick="editadmin();">修改</a>
							</div>
						</td></tr>
						<tr height="35px;">
							<td style="text-align:center;">
								<input type="hidden" id="checkid" value="${RoomManagers.id}" />
								<core:forEach items="${RoomManagers.userList}" var="user">
									
									
										<input type="checkbox" name="test" value="${user.id}" 
										
										<core:forEach items="${CheckedId}" var="ci">
											<core:if test="${user.id==ci}">checked=true</core:if> 
										</core:forEach>
										
										>
										${user.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									

								</core:forEach>
								<input type="hidden" id="checklist" name="checklist" value="" />
							</td>

						</tr>
						<tr>
							<td colspan="6" style="text-align:right;">
								<input id="btnDone" class="btn btn-primary btn6 mr10" onClick="saveadmin()" value="保存" type="button" style="display:none;">
							</td>
                        </tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</form:form>
</body>
</html>