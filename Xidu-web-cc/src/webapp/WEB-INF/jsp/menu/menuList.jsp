<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	Menu
  </title>
	<script type="text/javascript" src="${jspath}/jquery_1.3.2.min.js"></script>
	<script type="text/javascript" src="${jspath}/jquery.blockUI.js"></script>
	
	<link href="${csspath}/style.css" type="text/css" rel="stylesheet"/>
	<link href="${csspath}/prettyPhoto.css" type="text/css" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="${csspath}/extremecomponents.css"/>
	
	<script language="javascript" src="${anjijspath}/utilities.js"></script>
	<script type='text/javascript' src='${jspath}/jquery.js'></script>
	<script type='text/javascript' src='${jspath}/custom.js'></script>
	<script type='text/javascript' src='${jspath}/jquery.prettyPhoto.js'></script>
	<script type='text/javascript' src='${anjijspath}/page.js'></script>
	<script type='text/javascript' src='${anjijspath}/pub.js'></script>
	<script type='text/javascript' src='${anjijspath}/extremecomponents.js'></script>
	
	<script LANGUAGE="JavaScript">		
		function search(){
			$("#menu_form").submit();
		}
		//add 
		function addMenu(){
			$("#menuId").val('');
			$("#menu_form").attr("action","addOrEditMenu");
			$("#menu_form").submit();
		}
		//edit
        function editMenu(){
			var selMenu=$("#tableResults input:radio:checked");
        	if(selMenu.length==0){
        		alert('Please select one record.');
        		return;
        	}
        	$("#menuId").val(selMenu.val());
        	$("#menu_form").attr("action","addOrEditMenu");
			$("#menu_form").submit();
        }
		//delete
        function deleteMenu(){
        	var selMenu=$("#tableResults input:radio:checked");
        	if(selMenu.length==0){
        		alert('Please select one record.');
        		return;
        	}
        	if(confirm('Do you confirm to delete this record?')){
	        	$("#menuId").val(selMenu.val());
	        	$("#menu_form").attr("action","deleteMenu");
		 		$("#menu_form").submit();
		    }
        }
		//detail
        function showMenuDetail(){
        	var selMenu=$("#tableResults input:radio:checked");
        	if(selMenu.length==0){
        		alert('Please select one record.');
        		return;
        	}
        	window.open("${webcontext}/baseInfo/MenuInfo/showMenuDetail?MenuId="+selMenu.val());
        }
		
    </script>   
</head>


<body style="cursor: default;" leftmargin="0" topmargin="0">

  <%@include file="../../include/head.jsp"%>
   
  <br>
  <table border="0" cellspacing="2" width="100%">
    <tbody>
      <tr>
        <td><span id="lblHeader" class="PageTitle">Menu</span></td>
      </tr>
    </tbody>
  </table>
  
  <form:form  id="menu_form" name ="menu_form" action="queryMenu" method="post" modelAttribute="queryMenuDto">
  	<form:hidden path="menuId" id="menuId"/>
  	
  <span id="UpdatePanelSearch">
  <table cellpadding="3" cellspacing="1" width="100%">
    <tbody>
      <tr>
        <td><fieldset>
            <legend id="legSearchCriteria" class="legend">Search Criteria</legend>
            <table border="0" cellpadding="1" cellspacing="1" width="100%">
              <tbody>
                <tr>
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">Menu Code:</span></td>
                  <td nowrap="nowrap">
                  	<form:input path="menuCode"  cssClass="userInput"/> 
                  </td>
                  <td align="right" nowrap="nowrap"><span id="lblIsHub" class="LabelBold">Menu Name:</span></td>
                  <td nowrap="nowrap">
                  	<form:input path="menuName" cssClass="userInput" />
                  </td>
                  <td align="right" nowrap="nowrap"><span id="lblCity" class="LabelBold">Parent Menu Code:</span></td>
                  <td nowrap="nowrap">
					<form:input path="parentMenuCode"  cssClass="userInput"/>
                   </td>
                </tr>
              </tbody>
            </table>
            <hr style="width:99%;">
            <table border="0" cellpadding="1" cellspacing="1" width="100%">
              <tbody>
                <tr>
                  <td style="padding-left:2px"><span><span class="Label" style="color:Red;">* will match one or more words.</span>&nbsp;<img onMouseOver="PopupInfo(window.event.clientX,window.event.clientY,286,90,'<ul style=&quot;margin-left:5pt; list-style-type:none&quot;><li><B>Usage examples:</B></li><ul style=&quot;margin-left:20pt; list-style-type:disc&quot;><li>* returns all records.</li><li>Trip* returns all records beginning with Trip.</li><li>*trip returns all records ending with trip.</li><li>*trip* returns all records containing trip.</li></ul></ul>');" onMouseOut="PopupInfoHide();" onClick="PopupInfo(window.event.clientX,window.event.clientY,286,90,'<ul style=&quot;margin-left:5pt; list-style-type:none&quot;><li><B>Usage examples:</B></li><ul style=&quot;margin-left:20pt; list-style-type:disc&quot;><li>* returns all records.</li><li>Trip* returns all records beginning with Trip.</li><li>*trip returns all records ending with trip.</li><li>*trip* returns all records containing trip.</li></ul></ul>');" src="${imagepath}/i.gif" style="border-width:0px;"></span></td>
                  <td style="padding-right:2px;" align="right" nowrap="nowrap"><span id="lblMaxRows" class="LabelBold">Rows Returned:</span>
                    <form:input id="pagerSize" path="pager.pageSize" cssStyle="width:40px;"/>
                    <input name="btnSearch" value="Search" onClick="search();" id="btnSearch" class="Button" style="width:90px;" type="button"/>
                    <input name="btnSearch" value="Reset" onClick="clearAll();" id="btnSearch" class="Button" style="width:90px;" type="button"/>
                    </td>
                </tr>
              </tbody>
            </table>
          </fieldset></td>
      </tr>
    </tbody>
  </table>
  </span> 

  
  	<%@include file="../../include/page.jsp"%>
  </form:form>
  
  <table id="tableResults" cellpadding="1" cellspacing="1" width="100%">
    <tbody>
      <tr>
        <td><table border="0" cellpadding="1" cellspacing="1" width="100%">
            <tbody>
              <tr>
                <td colspan="2"><div id="resizableDiv" class="DivResultsGrid" style="OVERFLOW: auto;border-top:solid 1px silver">
                	
                	<ec:table styleClass="DataGrid"  style="width:100%;" border="0" cellpadding="2" cellspacing="2" tableId="tableResults"
					   items="queryMenuDto.resultList"
					   action="${webcontext}/maintain/menu/queryMenu"
					   imagePath="${webcontext}/images/table/*.gif"
					   filterable="false"

  					   showPagination="false"
  					   autoIncludeParameters="false"
  					   showTitle="false"
  					   showStatusBar="false"
  					   var="bo"
					   >
						 <ec:row>
						    <ec:column  alias="Select" value="<input type='radio' name='selId' value='${bo.id }'/> "width="22" viewsAllowed="html" />
						 	<ec:column property="menuCode"></ec:column>
						 	<ec:column property="menuName"></ec:column>
						 	<ec:column property="parentMenuCode"></ec:column>
						 	<ec:column property="url"></ec:column>
						 	<ec:column property="isLeaf"></ec:column>
						 	<ec:column property="menuLevel"></ec:column>
						 </ec:row>
					</ec:table>
                    
                  </div></td>
              </tr>
              
            </tbody>
          </table>
          <table style="width:100%">
            <tr>
              <td align="right"><input style="width:90px; text-align:center;" class="Button" onClick="addMenu();" value="Add" name="btnAdd">
              <input style="width:90px; text-align:center;" class="Button" id="btnReset" onClick="editMenu();" value="Edit" name="btnEdit">
               <input style="width:90px; text-align:center;" class="Button" id="btnReset" onclick="deleteMenu();" value="Delete" name="btnDelete">
            </tr>
          </table>
          </td>
      </tr>
    </tbody>
  </table>
  </span>
  <span id="PageMessageUpdatePanel"></span> 
  
</body></html>