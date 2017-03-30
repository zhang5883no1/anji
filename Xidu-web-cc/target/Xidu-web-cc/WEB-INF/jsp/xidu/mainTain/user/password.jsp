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

			<table cellSpacing="1" cellPadding="1" width="100%">
				<tr width="100%">
					<td><span id="lblheader" class="PageTitle"></span></td>
				</tr>
			</table>			
	        <table cellSpacing="1" cellPadding="1" width="100%" height="30">
	            <tr>
	                <td>
	                    <fieldset><legend name="Legend1" class="legend" id="Legend1">Details</legend>
							        <table cellspacing="1" cellpadding="1" width="100%">
								        <tbody>
								        <tr width="100%">
									        <td align="right" nowrap=""><span class="LabelBold" id="lblDimType">New Password:</span></td>
									          <td nowrap="">
									           <input id="pwd" cssStyle="width:190px;"  maxlength="50" />
											  </td>
											  <td align="right" nowrap=""><span class="LabelBold" id="lblDimType">Confirm password:</span></td>
									          <td nowrap="">
									           <input id="confirmpwd" cssStyle="width:190px;"  maxlength="50" />
											  </td>
										 </tr>
							        </tbody></table>
						        </fieldset>
	                </td>
	            </tr>
	           
	        </table>
            <table style="width:100%;">
              <tr>
					        <td nowrap=""><span id="imgRequiredField"><img style="border-width:0px;" src="${imagepath}/required.gif" id="imgRequired">&nbsp;<span class="Label">Required Field</span>&nbsp;</span></td>
					        <td align="right">
						        <input type="button" style="width:100px;" class="Button" type="button"  id="btnDone" onClick="updatePwd();" value="Save" name="btnDone">
					        </td>
				        </tr>
            </table>			    
        <span id="PageMessageUpdatePanel"></span>


    </body>
</html>
