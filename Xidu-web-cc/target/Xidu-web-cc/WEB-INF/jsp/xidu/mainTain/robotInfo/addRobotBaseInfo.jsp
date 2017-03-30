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
   <form:form id="addRobot_form" name ="robot_form" action="saveRobot" method="post" modelAttribute="robotDto">
     <form:hidden path="id"/>
     <form:hidden path="faceImg"/>
	<!-- BEGIN HEADER -->
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
	    <tr style="height:40px">
	        <td width="80%">
	            <img id="header_imgMatrixLogo" src="${imagepath}/site_logo.gif" align="middle" style="border-width:0px;" />
	        </td>
	        <td>
	            <table cellpadding="0" cellspacing="0" width="100%" border="0" style="padding: 0 4 0 4;">
	                <tr >
	                    <td align="right" nowrap="nowrap">
	                        <span id="header_upLinks">                            
	                            <table cellpadding="4" cellspacing="0" border="0"><tr>
	                                <td><a id="header_lnkHelp" disabled="disabled" class="HeaderLinkButton" onMouseOver="this.classname=&#39;HeaderLinkButtonItemOver&#39;;" onMouseOut="this.classname=&#39;HeaderLinkButton&#39;;">Help</a></td>
	                                <td class="HeaderLinkButton">|</td>
	                                <td><span id="header_lblClose" class="HeaderLinkButton" onMouseOver="this.className=&#39;HeaderLinkButtonItemOver&#39;;" onMouseOut="this.className=&#39;HeaderLinkButton&#39;;" onClick="ClosePage();">Close Dialog</span></td>
			                        <td style="width:20px;text-align:center" onClick="AbortRequestHandler();"><img id="imgUpdating" alt="" src="${imagepath}/updating.gif" style="border-width:0px;visibility:hidden;cursor:hand;" /></td>
	                            </tr></table>
	                        </span>
	                    </td>
	                </tr>
	            </table>
	        </td>
	    </tr>
	    <tr style="background-image:url(${imagepath}/menu_bar.png);background-repeat:repeat-x;height:10px">
	        <td colspan="2"></td>
	    </tr>
	</table>
     <!-- END HEADER -->

			<table cellSpacing="1" cellPadding="1" width="100%">
				<tr width="100%">
					<td><span id="lblheader" class="PageTitle">机器人基本信息</span></td>
				</tr>
			</table>			
	        <table cellSpacing="1" cellPadding="1" width="100%" height="30">
	            <tr>
	                <td>
	                    <fieldset><legend name="Legend1" class="legend" id="Legend1">详细</legend>
							        <table cellspacing="1" cellpadding="1" width="100%">
								        <tbody><tr width="100%">
									        <td align="right" nowrap=""><span style="display:inline-block;" class="LabelBold" id="lblLength">昵称:</span></td>
									        <td nowrap="">
									        	<form:input path="name" cssStyle="width:190px;" cssClass="userInput " maxlength="15"/>
									        </td>
									        <td align="right" nowrap=""><span class="LabelBold" id="lblLabel">性别:</span></td>
									        <td nowrap="">
									             <form:select path="sex" cssClass="userInput ">
									        		<form:option value="man">男</form:option>
									        		<form:option value="woman">女</form:option>
									        	</form:select>
									        </td>
								        </tr>
                                        <tr>
                                          <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">所属业务员:</span></td>
									        <td nowrap="">
									        	 <form:select class="userInput" path="user_id" cssStyle="width:190px;" > 
										    		<core:forEach items="${adminList}" var="cu">
										        		<form:option value="${cu.id }" >${cu.nickName}</form:option>
										    		</core:forEach>
									        	</form:select>
									        </td>
                                            <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">会员类型:</span></td>
									        <td nowrap="">
									        	 <form:select path="type_id" cssClass="userInput ">
									        		<form:option value="1">试用会员</form:option>
									        		<form:option value="2">开户会员</form:option>
									        		<form:option value="3">名师</form:option>
									        		<form:option value="4">业务员</form:option>
									        		<form:option value="5">管理员</form:option>
									        	</form:select>
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
						        <input type="button" style="width:100px;" class="Button" type="button"  id="btnDone" onClick="saveCUstomer();" value="保存" name="btnDone">
						        <input type="button" style="width:100px;" class="Button" type="button"  id="btnClose" onClick="backList();" value="返回" name="btnReturn"/>
					        </td>
				        </tr>
            </table>			    
        <span id="PageMessageUpdatePanel"></span>
	<script LANGUAGE="JavaScript">	
  		$(document).ready(
  				function(){
  					
  				}
  		);
  	</script> 
		</form:form>
    </body>
</html>
