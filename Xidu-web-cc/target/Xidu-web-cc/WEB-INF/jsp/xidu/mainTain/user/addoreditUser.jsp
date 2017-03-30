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
			valid($("#userCode"));
			if(validateForm()){
				if(	$("#flag").val()=="0"){
					var selrole=$("#tableResults input:radio:checked");
		        	if(selrole.length==0){
		        		alertForAnji('Please select one record.',null);
		        		return;
		        	}
					submitFormForAnji("addUser_form","${webcontext}/maintain/user/saveuser",0);
//				$("#addUser_form").submit();
				}
			}

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
   <form:form id="addUser_form" name ="User_form" action="saveuser" method="post" modelAttribute="userDto">
     <form:hidden path="id"/>
     <input type="hidden" id="isvalue" value="${userDto.userCode}"/>
          <input type="hidden" id="flag" value="0" />
	<!-- BEGIN HEADER -->
	<table cellpadding="0" cellspacing="0" border="0" width="100%" >
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
					<td><span id="lblheader" class="PageTitle"></span></td>
				</tr>
			</table>			
	        <table cellSpacing="1" cellPadding="1" width="100%" height="30" >
	            <tr>
	                <td>
	                    <fieldset><legend name="Legend1" class="legend" id="Legend1">Details</legend>
							        <table cellspacing="1" cellpadding="1" width="100%" id="tableResults">
								        <tbody>
								        <tr width="100%">
									        <td align="right" nowrap=""><span class="LabelBold" id="lblDimType">User Code:</span></td>
									          <td nowrap="">
									           <form:input path="userCode" cssStyle="width:190px;" cssClass="RequiredField userInput "  maxlength="50" onblur="valid(this)"/>
											  </td>
											  
											  <td align="right" nowrap=""><span class="LabelBold" id="lblDimType">User Name:</span></td>
									          <td nowrap="">
									           <form:input path="name" cssStyle="width:190px;" cssClass="RequiredField userInput " maxlength="50"/>
											  </td>
										 </tr>
										 <tr width="100%">
									        <td align="right" nowrap=""><span class="LabelBold" id="lblDimType">EMPLOYER OWNER ID:</span></td>
									          <td nowrap="">
									           <form:input path="employerOwnerId" cssStyle="width:190px;" cssClass="RequiredField userInput " maxlength="50"/>
											  </td>
											   <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">User Password:</span></td>
									        <td nowrap="">
									        	<form:input path="password" cssStyle="width:190px;" cssClass="userInput RequiredField" maxlength="50"/>
											</td>
											 
										 </tr>
										 <tr width="100%">
									        <td align="right" nowrap=""><span class="LabelBold" id="lblDimType"> USER TYPE:</span></td>
									          <td nowrap="">
									            <form:select path="userType" cssClass="userInput RequiredField" cssStyle="width:190px;">
									        		<form:option value="0">--未选择--</form:option>
							                  		<core:forEach items="${customerList}" var="cu">
														<form:option value="${cu.id }" >${cu.nickName}</form:option>
													</core:forEach>
									        	</form:select>
											  </td>
											   <td align="right" nowrap=""><span class="LabelBold" id="lblDimType">STATUS CODE:</span></td>
									          <td nowrap="">
									           <form:select path="statusCode" cssClass="userInput RequiredField" cssStyle="width:190px;">
									        		<form:option value="0">- Not Selected -</form:option>
									        		<form:option value="1">Block</form:option>
									        		<form:option value="2">Normal</form:option>
									        	</form:select>
											  </td>
										 </tr>
										 <tr width="100%">	  
											 <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">Role:</span></td>
									        <td nowrap="">
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
								        
							        </tbody></table>
						        </fieldset>
	                </td>
	            </tr>
	           
	        </table>
            <table style="width:100%;">
              <tr>
					        <td nowrap=""><span id="imgRequiredField"><img style="border-width:0px;" src="${imagepath}/required.gif" id="imgRequired">&nbsp;<span class="Label">Required Field</span>&nbsp;</span></td>
					        <td align="right">
						        <input type="button" style="width:100px;" class="Button" type="button"  id="btnDone" onClick="saveUser();" value="Save" name="btnDone">
						        <input type="button" style="width:100px;" class="Button" type="button"  id="btnClose" onClick="backList();" value="Return" name="btnReturn"/>
					        </td>
				        </tr>
            </table>			    
        <span id="PageMessageUpdatePanel"></span>


		</form:form>
    </body>
</html>
