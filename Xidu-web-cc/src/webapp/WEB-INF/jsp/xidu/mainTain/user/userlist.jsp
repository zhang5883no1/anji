<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	User
  </title>
	<script LANGUAGE="JavaScript">		
		function search(){
			$("#user_form").submit();
		}
		//add 
		function adduser(){
			$("#id").val("");
			$("#user_form").attr("action","addOrEdituser");
			submitFormForAnji("user_form","${webcontext}/maintain/user/addOrEdituser",0);
//			$("#user_form").submit();
		}
		//edit
        function edituser(){
			var seluser=$("#tableResults input:radio:checked");
        	if(seluser.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	$("#id").val(seluser.val());
        	$("#user_form").attr("action","addOrEdituser");
			submitFormForAnji("user_form","${webcontext}/maintain/user/addOrEdituser",0);
//			$("#user_form").submit();
        }
		//delete
        function deleteuser(){
        	var seluser=$("#tableResults input:radio:checked");
        	if(seluser.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	if(comfirmForAnji('Do you confirm to delete this record?',null)){
	        	$("#id").val(seluser.val());
	        	$("#user_form").attr("action","deleteuser");
				submitFormForAnji("user_form","${webcontext}/maintain/user/deleteuser",0);
//		 		$("#user_form").submit();
		    }
        }
		
    </script>   
	
<head></head>
<body style="cursor: default;" leftmargin="0" topmargin="0">
	<%@include file="../../include/head.jsp"%>
	<form:form id="user_form" name ="user_form" action="queryUser" method="post" modelAttribute="userDto">
 	<form:hidden path="id" id="id"/>
	<div class="main-wrap">
		<div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">用户管理</span></div>
        </div>
		<div class="search-wrap">
            <div class="search-content">
					
					<table class="search-tab">
						<tr align="center">
							<td width="130">用户编码:</td>
							<td><form:input path="userCode" class="common-text" placeholder="用户编码" /></td>
							
							<td width="130">用户名称:</td>
							<td><form:input path="name" class="common-text" placeholder="用户名称" /></td>
							
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
                        <a href="javascript:void(0)" onClick="adduser();" name="btnAdd">新增</a>
                        <a id="btnReset" href="javascript:void(0)" onClick="edituser();">修改</a>
                        <a id="btnReset" href="javascript:void(0)" onclick="deleteuser();">删除</a>
                    </div>
                </div>
                <div class="result-content">
					<table id="tableResults" class="result-tab" width="100%">
							<tr><td>
							<ec:table styleClass="DataGrid"  style="height:25px;width:100%;" border="1"  cellpadding="0" cellspacing="0" tableId="tableResults" form="user_form"
					   items="userDto.resultList"
					   action="${webcontext}/maintain/user/queryUser"
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
						 	<ec:column  alias="选择"  sortable="false">
						 	<input type='radio' name='selId' value='${bo.id }'/> 
						 	</ec:column>
						    <ec:column property="userCode" title="用户编码"/>
						    <ec:column property="password" title="密码"/>
						    <ec:column property="name" title="用户名称"/>
						    <ec:column property="statusCode" title="状态编码"/>
						    <ec:column property="userType" title="用户类型"/>
						    <ec:column property="employerOwnerId" title="员工所属ID"/>
						 </ec:row>
					</ec:table>
							</td></tr>
							
					</table>
					<div class="list-page">
					<%@include file="../../include/page.jsp"%>
					</div>
				</div>
		</div>
	</div>

  <script type="text/javascript">
//<![CDATA[
_pleaseWaitMessage='Please Wait...';_ajax_SessionTimeoutMessage='Your session has expired, please login again.';_ajax_MaxRequestLengthMessage='Your request exceeds the maximum allowed.  Please try limiting your criteria and re-try your request again.';_ajax_SQLTimeoutMessage='Matrix is currently experiencing a large volume of traffic.  Please re-try your request again.';_ajax_ServerTimeoutMessage='Your request exceeded the maximum allowed timeout period.  This can be caused by system issues and/or networking related problems.  \r\rPlease Note that the request may have completed and resubmitting the request may result in duplicate records.';Sys.Application.add_load(PageResize);$addHandler(window, 'resize', PageResize );EndRequestHandler();(function() {var fn = function() {$get("ctl01_HiddenField").value = '';Sys.Application.remove_init(fn);};Sys.Application.add_init(fn);})();//]]>
</script> 
  <script language="JavaScript" for="document" event="onhelp()">var link = document.getElementById("header_lnkHelp");if ( link != null ){link.click();document.parentWindow.event.returnValue=0;}</script>
</form:form>
</body>
</html>