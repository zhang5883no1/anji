<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	Robot Base Info
  </title>
	<script LANGUAGE="JavaScript">		
		function saveCUstomer(){
			submitFormForAnji("addRobot_form","${webcontext}/mainTain/robot/saveRobot",0);
		}
		
		function backList(){
			submitHrefForAnji("${webcontext}/mainTain/robot/init",null);
			//location.href="${webcontext}/mainTain/robot/init";
		}
		
    </script>   
 </head>
<body leftMargin="0" topMargin="0">
	<%@include file="../../include/head.jsp"%>
	<div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">机器人管理</a><span class="crumb-step">&gt;</span><span>新增机器人</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
			<form:form id="addRobot_form" name ="robot_form" action="saveRobot" method="post" modelAttribute="robotDto">
				<form:hidden path="id"/>
				<form:hidden path="faceImg"/>
				<table class="insert-tab" width="100%" id="tableResults">
					<tbody>
						<tr>
							<th><i class="require-red">*</i>昵称:</th>
							<td>
								<form:input path="name" class="common-text" size="50" title="Menu Code" />
							</td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>所属业务员:</th>
							<td>
								<form:select class="userInput" path="user_id" cssStyle="width:190px;" >
									<core:forEach items="${admins}" var="cu">
										<form:option value="${cu.id }" >${cu.nickName}</form:option>
									</core:forEach>
								</form:select>
							</td>
						</tr>
						<tr>
							<th></th>
							<td>
								<input id="btnDone" class="btn btn-primary btn6 mr10" onClick="saveCUstomer();" value="提交" type="button">
								<input id="btnClose" class="btn btn6" onClick="backList();" value="返回" type="button">
							</td>
						</tr>
					</tbody>
				</table>
				<table cellpadding="0" cellspacing="0" width="100%" border="0" style="padding: 0 4 0 4;">
					<tr>
						<td align="right" nowrap="nowrap">
							<span id="header_upLinks">
								<table cellpadding="4" cellspacing="0" border="0">
									<tr>
										<td>
											<a id="header_lnkHelp" disabled="disabled" class="HeaderLinkButton" onMouseOver="this.classname=&#39;HeaderLinkButtonItemOver&#39;;" onMouseOut="this.classname=&#39;HeaderLinkButton&#39;;">Help</a>
										</td>
										<td class="HeaderLinkButton">|</td>
										<td>
											<span id="header_lblClose" class="HeaderLinkButton" onMouseOver="this.className=&#39;HeaderLinkButtonItemOver&#39;;" onMouseOut="this.className=&#39;HeaderLinkButton&#39;;" onClick="ClosePage();">Close Dialog</span>
										</td>
										<td style="width:20px;text-align:center" onClick="AbortRequestHandler();">
											<img id="imgUpdating" alt="" src="${imagepath}/updating.gif" style="border-width:0px;visibility:hidden;cursor:hand;" />
										</td>
									</tr>
								</table>
							</span>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</div>

<span id="PageMessageUpdatePanel"></span>
<script LANGUAGE="JavaScript">	
$(document).ready(
	function(){
	
	}
);
</script> 
</body>
</html>
