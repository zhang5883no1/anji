<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	管理员管理
  </title>
	<script LANGUAGE="JavaScript">		
		function saveCUstomer(){
			submitFormForAnji("addAdmin_form","${webcontext}/mainTain/adminInfo/saveCustomer",0);
		}
		
		function backList(){
			submitHrefForAnji("${webcontext}/mainTain/adminInfo/init",null);
			//location.href="${webcontext}/mainTain/customerinfo/init";
		}
		
		//valid
		function valid(txtbox){
			$.ajax({
				method:"GET",
				url:"${webcontext}/mainTain/adminInfo/valid?code="+$(txtbox).val()+"&currTime="+(new Date()).getTime(),
				success:function(msg){
					if(msg!="false"){
						//alert("CustomerCode is not exist");
						$(txtbox).parent().children(".validSpan").remove();
						$(txtbox).parent().append("<span class='validSpan'>CustomerCode is exist</span>");
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
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">管理员管理</a><span class="crumb-step">&gt;</span><span>新增管理员</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
				<form:form id="addAdmin_form" name ="addAdmin_form" action="saveAdmin" method="post" modelAttribute="customerDto">
				<form:hidden path="id"/>
					<table class="insert-tab" width="100%" id="tableResults">
                        <tbody>
							<tr>
                                <th><i class="require-red">*</i>用户名:</th>
                                <td>
                                    <form:input path="userName" class="common-text" size="50" />
                                </td>
                            </tr>
                            <tr>
                                <th>昵称:</th>
                                <td><form:input path="nickName" class="common-text" size="50" /></td>
                            </tr>
							<tr>
                                <th>手机号码:</th>
                                <td><form:input path="mobile" class="common-text" size="50" /></td>
                            </tr>
							<tr>
                                <th>密码:</th>
                                <td><form:input path="pwd" class="common-text" size="50" /></td>
                            </tr>
							<tr>
                                <th>状态:</th>
                                <td>
									<form:select path="status" cssClass="userInput ">
										<form:option value="normal">正常</form:option>
										<form:option value="block">锁定</form:option>
									</form:select>
								</td>
                            </tr>
							<tr>
                                <th>会员类型:</th>
                                <td>
									<form:select path="level" cssClass="userInput ">
									  		<form:option value="99">业务员</form:option>
									  		<form:option value="100">巡官</form:option>
									</form:select>
								</td>
                            </tr>
							<tr>
                                <th>房间:</th>
                                <td>
									<form:select path="roomNo" cssClass="userInput">
													<form:option value="">--未选择--</form:option>
													<core:forEach items="${rooms}" var="roomlist">
														<form:option value="${roomlist.id }">${roomlist.roomName }</form:option>
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
                        </tbody></table>
                </form:form>
            </div>
        </div>

    </div>	
    </body>
</html>
