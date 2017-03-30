<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	Menu Info
  </title>
	<script type="text/javascript" src="${jspath}/jquery_1.3.2.min.js"></script>
	<script type="text/javascript" src="${jspath}/jquery.blockUI.js"></script>
	
	<link href="${csspath}/style.css" type="text/css" rel="stylesheet">
	<link href="${csspath}/prettyPhoto.css" type="text/css" rel="stylesheet">
	<script language="javascript" src="${jspath}/utilities.js"></script>
	<script type='text/javascript' src='${jspath}/jquery.js'></script>
	<script type='text/javascript' src='${jspath}/custom.js'></script>
	<script type='text/javascript' src='${jspath}/jquery.prettyPhoto.js'></script>
	<script type='text/javascript' src='${anjijspath}/page.js'></script>
	<script type='text/javascript' src='${anjijspath}/pub.js'></script>
	<script LANGUAGE="JavaScript">		
		function saveMenu(){
			if(validateForm()){
				$("#addMenu_form").submit();
			}
			
		}
		
		function backList(){
			location.href="${webcontext}/maintain/menu/init";
		}
		
    </script>   
 </head>
<body leftMargin="0" topMargin="0">
   <form:form id="addMenu_form" name ="addMenu_form" action="saveMenu" method="post" modelAttribute="menuDto">
     <form:hidden path="id"/>
	
     <%@include file="../../include/head.jsp"%>
   
     <br>
	
			<table cellSpacing="1" cellPadding="1" width="100%">
				<tr width="100%">
					<td><span id="lblheader" class="PageTitle">Menu Base Info</span></td>
				</tr>
			</table>			
	        <table cellSpacing="1" cellPadding="1" width="100%" height="30">
	            <tr>
	                <td>
	                    <fieldset><legend name="Legend1" class="legend" id="Legend1">Details</legend>
							        <table cellspacing="1" cellpadding="1" width="100%">
								        <tbody>
								        <tr width="100%">
									        <td align="right" nowrap=""><span class="LabelBold" id="lblDimType">Menu Code:</span></td>
									        <td nowrap="">
									           <form:input path="menuCode" cssStyle="width:190px;" cssClass="RequiredField userInput " maxlength="120" title="Menu Code"/>
											  </td>
									        <td align="right" nowrap=""><span style="display:inline-block;" class="LabelBold" id="lblLength">Menu Name:</span></td>
									        <td nowrap="">
									        	<form:input path="menuName" cssStyle="width:190px;" cssClass="RequiredField userInput " maxlength="120" title="Menu Name"/>
									        </td>
								        </tr>
								        
                                        <tr>
									        <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">Parent Menu Code:</span></td>
									        <td nowrap="">
									        	 <form:input path="parentMenuCode" cssStyle="width:190px;" cssClass="userInput " maxlength="120" title="Parent Menu Code"/>
									        </td>
                                            <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">Menu Level:</span></td>
									        <td nowrap="">
									        	 <form:input path="menuLevel" cssStyle="width:190px;" cssClass="userInput integer" maxlength="10" title="Menu Level"/>
											</td>
								        </tr>
								        
								         <tr>
									        <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">URL:</span></td>
									        <td nowrap="">
									        	 <form:input path="url" cssStyle="width:190px;" cssClass="userInput " maxlength="255" title="URL"/>
									        </td>
                                            <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">Is Leaf:</span></td>
									        <td nowrap="">
									        	<form:select path="isLeaf" cssClass="userInput RequiredField" title="isLeaf">
									        		<form:option value="">- Not Selected -</form:option>
									        		<form:option value="1"></form:option>
									        		<form:option value="0"></form:option>
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
						        <input type="button" style="width:100px;" class="Button" id="btnDone" onClick="saveMenu();" value="Save" name="btnDone">
						        <input type="button" style="width:100px;" class="Button" id="btnClose" onClick="backList();" value="Return" name="btnReturn"/>
					        </td>
				        </tr>
            </table>			    
        <span id="PageMessageUpdatePanel"></span>


		</form:form>
    </body>
</html>
