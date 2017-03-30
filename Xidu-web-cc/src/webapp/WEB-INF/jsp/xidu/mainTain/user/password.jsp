<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	Edit Password
  </title>
	<script LANGUAGE="JavaScript">		
		
		function updatePwd(){
			$("#pwd").parent().children(".validSpan").remove();
			if($("#pwd").val()==""){
				alert("please input new password");
				return;
			}
			if($("#confirmpwd").val()==""){
				alert("please input confirmpwd password");
				return;
			}
			if($("#confirmpwd").val()!=$("#pwd").val()){
				alert("The two passwords don't match");
				return;
			}
			
			$.ajax({
				method:"GET",
				url:"${webcontext}/maintain/user/updatePwd?pwd="+$("#pwd").val(),
				success:function(msg){
					$("#pwd").parent().children(".validSpan").remove();
					if(msg=="success"){
						$("#pwd").parent().append("<span class='validSpan'>update password success</span>");
						alert("update password success");
					}else{
						$("#pwd").parent().append("<span class='validSpan'>update password faild</span>");
						alert("update password faild");
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
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">用户管理</a><span class="crumb-step">&gt;</span><span>修改密码</span></div>
        </div>
		<table class="insert-tab" width="100%">
                        <tbody>
							<tr>
							<th>新密码:</th>
							<td>
                                <input class="common-text required" id="pwd" name="title" size="50" value="" type="text">
                            </td>
							</tr>
							<tr>
							<th>重复新密码:</th>
							<td>
                                <input class="common-text required" id="confirmpwd" name="title" size="50" value="" type="text">
                            </td>
							</tr>
							<tr>
                                <th></th>
                                <td>
                                    <input id="btnDone" class="btn btn-primary btn6 mr10" onClick="updatePwd();" value="提交" type="button">
                                    <input id="btnClose" class="btn btn6" onClick="backList();" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody>
		</table>

    </body>
</html>
