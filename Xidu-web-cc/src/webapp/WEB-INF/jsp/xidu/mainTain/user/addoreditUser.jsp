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
		function saveUser(){
			submitFormForAnji("addUser_form","${webcontext}/maintain/user/saveuser",0);
		}
		
		function backList(){
			submitHrefForAnji("${webcontext}/maintain/user/init",null);
			//location.href="${webcontext}/maintain/user/init";
		}
		
		function valid(txtbox){
			if($("#isvalue").val()==$(txtbox).val()){
				return;
			}
			$.ajax({
				method:"GET",
				url:"${webcontext}/maintain/user/valid?code="+$(txtbox).val()+"&currTime="+(new Date()).getTime(),
				success:function(msg){
					if(msg!="false"){
						//alert("CustomerCode is not exist");
						$(txtbox).parent().children(".validSpan").remove();
						$(txtbox).parent().append("<span class='validSpan'>User code is exist</span>");
						$("#flag").val("1");
						//$("#btnSave").hide();
					}else{
						$(txtbox).parent().children(".validSpan").remove();
						$(txtbox).next().next().val(msg);
						$("#flag").val("0");
					}
				}
			});
		}
    </script>   
 </head>
<body leftMargin="0" topMargin="0">
   <%@include file="../../include/head.jsp"%>

	 <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">用户管理</a><span class="crumb-step">&gt;</span><span>新增用户</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form:form id="addUser_form" name ="User_form" action="saveuser" method="post" modelAttribute="userDto">
					<form:hidden path="id"/>
                    <table class="insert-tab" width="100%" id="tableResults">
                        <tbody>
							<tr>
                                <th><i class="require-red">*</i>用户编码:</th>
                                <td>
                                    <form:input path="userCode" class="common-text" size="50" />
                                </td>
                            </tr>
                            <tr>
                                <th>用户名称:</th>
                                <td><form:input path="name" class="common-text" size="50" /></td>
                            </tr>
							<tr>
                                <th>员工所属ID:</th>
                                <td><form:input path="employerOwnerId" class="common-text" size="50" /></td>
                            </tr>
							<tr>
                                <th>用户密码:</th>
                                <td><form:input path="password" class="common-text" size="50" /></td>
                            </tr>
							<tr>
                                <th>对应业务员:</th>
                                <td>
									<form:select path="userType" class="required">
										<form:option value="0">--未选择--</form:option>
										<core:forEach items="${customerlist}" var="cu">
											<form:option value="${cu.id }" >${cu.nickName}</form:option>
										</core:forEach>
									</form:select>
								</td>
                            </tr>
							<tr>
                                <th>状态:</th>
                                <td>
									<form:select path="statusCode" class="required">
									  	<form:option value="0">- 未选择 -</form:option>
									    <form:option value="1">锁定</form:option>
									    <form:option value="2">正常</form:option>
									</form:select>
								</td>
                            </tr>
							<tr>
                                <th>角色:</th>
                                
                                <td>
									<div style="overflow-y:scroll;width:200px;height:70px;">
										<core:forEach items="${rolelist}" var="list">
										    <input type="radio" value="${list.id }" name="rolelist"
										    <core:forEach items="${userRole}" var="selected">
										    <core:if test="${selected==list.id}">checked</core:if>
									    </core:forEach>
									    />${list.roleName}<br/>
									    	</core:forEach>
									</div>
								</td>
                            </tr>
							<tr>
                                <th>所属组:</th>
                                <td>
									<form:select path="groupId" class="required">
										<form:option value="1">--未选择--</form:option>
										<core:forEach items="${grouplist}" var="cu">
											<form:option value="${cu.id }" >${cu.userGroupName}</form:option>
										</core:forEach>
									</form:select>
								</td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="btnDone" class="btn btn-primary btn6 mr10" onClick="saveUser();" value="提交" type="button">
                                    <input id="btnClose" class="btn btn6" onClick="backList();" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody></table>
                </form:form>
            </div>
        </div>

    </div>
    </body>
</html>
