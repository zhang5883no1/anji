<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	Edit URL
  </title>
	<script LANGUAGE="JavaScript">		
		
		function updatePwd(){
			$.ajax({
				method:"GET",
				url:"${webcontext}/mainTain/video/updateDefaultUrl?defaultUrl="+escape($("#defaultUrl").val())+"&defaultUrl2="+escape($("#defaultUrl2").val()),
				success:function(msg){
					$("#defaultUrl").parent().children(".validSpan").remove();
					if(msg=="success"){
						$("#defaultUrl").parent().append("<span class='validSpan'>update url success</span>");
						alert("update url success");
					}else{
						$("#defaultUrl").parent().append("<span class='validSpan'>update url faild</span>");
						alert("update url faild");
					}
				}
			});
		}
		function resetUrl(){
			$("#defaultUrl").val("http://yy.com/s/18035127//yyscene.swf");
			$("#defaultUrl2").val("http://yy.com/s/18035127//yyscene.swf");
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
									        <td align="right" nowrap=""><span class="LabelBold" id="lblDimType">普通房间视频地址:</span></td>
									          <td nowrap="">
									           <input id="defaultUrl" name="defaultUrl" value="${defaultUrl }" cssClass="userInput" style="width:300px"/>
											  </td>
										 </tr>
										 <tr width="100%">
									        <td align="right" nowrap=""><span class="LabelBold" id="lblDimType">VIP房间视频地址:</span></td>
									          <td nowrap="">
									           <input id="defaultUrl2" name="defaultUrl2" value="${defaultUrl2 }" cssClass="userInput" style="width:300px"/>
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
						         <input type="button" style="width:100px;" class="Button" type="button"  onClick="resetUrl();" value="Reset" name="btnDone">
					        </td>
				        </tr>
            </table>			    
        <span id="PageMessageUpdatePanel"></span>


    </body>
</html>
