<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	Role
  </title>
	<script LANGUAGE="JavaScript">		
		function saveRole(){
			var selrole=$("#tableResults input:checkbox:checked");
        	if(selrole.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
			if(validateForm()){
				submitFormForAnji("addRole_form","${webcontext}/maintain/role/saverole",0);
//				$("#addRole_form").submit();
			}
		}
		
		function backList(){
			submitHrefForAnji("${webcontext}/maintain/role/init",null);
			//location.href="${webcontext}/maintain/role/init";
		}
		
		function parentcheck(button){
			if($(button).children("input:checkbox:checked").length>0){
				for(var i=0;i<$("dd[id='"+button.id+"']").length;i++){
					$("dd[id='"+button.id+"']:eq("+i+")").children("input:checkbox").attr("checked",true);
				}
			}else{
				for(var i=0;i<$("dd[id='"+button.id+"']").length;i++){
					$("dd[id='"+button.id+"']:eq("+i+")").children("input:checkbox").removeAttr("checked");
				}
			}
		}
		
		function childcheck(button){
			var index=0;
			for(var i=0;i<$("dd[id='"+button.id+"']").length;i++){
				if($("dd[id='"+button.id+"']:eq("+i+")").children("input:checkbox:checked").length>0){
					index++;
				}
			}
			//if(index==$("dd[id='"+button.id+"']").length){
			if(index>0){	
				$("#"+button.id).children("input:checkbox").attr("checked",true);
			}else if(index==0){
				$("#"+button.id).children("input:checkbox").removeAttr("checked");
			}
		}
    </script>   
 </head>
<body leftMargin="0" topMargin="0">
   <form:form id="addRole_form" name ="Role_form" action="saverole" method="post" modelAttribute="roleDto">
     <form:hidden path="id"/>
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
					<td><span id="lblheader" class="PageTitle"></span></td>
				</tr>
			</table>			
	        <table cellSpacing="1" cellPadding="1" width="100%" height="30">
	            <tr>
	                <td>
	                    <fieldset><legend name="Legend1" class="legend" id="Legend1">Details</legend>
							        <table cellspacing="1" cellpadding="1" width="100%" id="tableResults">
								        <tbody>
								        <tr width="100%">
									        <td align="right" nowrap=""><span class="LabelBold" id="lblDimType">Role Code:</span></td>
									          <td nowrap="">
									           <form:input path="roleCode" cssStyle="width:190px;" cssClass="RequiredField userInput " maxlength="50"/>
											  </td>
											  
											  <td align="right" nowrap=""><span class="LabelBold" id="lblDimType">Role Name:</span></td>
									          <td nowrap="">
									           <form:input path="roleName" cssStyle="width:190px;" cssClass="RequiredField userInput " maxlength="50"/>
											  </td>
										 </tr>
										 <tr width="100%">	  
                                            <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">Role Desc:</span></td>
									        <td nowrap="">
									        	<form:input path="roleDesc" cssStyle="width:190px;" cssClass="userInput " maxlength="50"/>
											</td>
											
											<td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">Menu:</span></td>
									        <td nowrap="">
									        <div>
									  
					
									  <dl>   
									     <core:forEach items="${menuList1 }" var="menulist">
									     <dt> <input type="checkbox" id="check_${menulist.id }" name="menucheckbox" value="${menulist.id}" 
										     	<core:forEach items="${isSelectMenu}" var="isselectmenu">
										     		<core:if test="${menulist.id==isselectmenu.id }">checked</core:if>
										     	</core:forEach> 
										     />${menulist.menuName}</dt>
										     <core:forEach items="${menulist.list}" var="menu">
										    
										     	<dd> <input type="checkbox" id="check_${menu.id }" name="menucheckbox" value="${menu.id}" 
										     	<core:forEach items="${isSelectMenu}" var="isselectmenu">
										     		<core:if test="${menu.id==isselectmenu.id }">checked</core:if>
										     	</core:forEach> 
										     />${menu.menuName}</dd>
										     </core:forEach>
									     </core:forEach>
									   </dl>
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
						        <input type="button" style="width:100px;" class="Button" type="button"  id="btnDone" onClick="saveRole();" value="Save" name="btnDone">
						        <input type="button" style="width:100px;" class="Button" type="button"  id="btnClose" onClick="backList();" value="Return" name="btnReturn"/>
					        </td>
				        </tr>
            </table>			    
        <span id="PageMessageUpdatePanel"></span>


		</form:form>
    </body>
</html>
