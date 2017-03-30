<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	Menu
  </title>
	
	<script LANGUAGE="JavaScript">		
		function search(){
			submitFormForAnji("menu_form","${webcontext}/maintain/menu/queryMenu",0);
//			$("#menu_form").submit();
		}
		//add 
		function addMenu(){
			$("#menuId").val('');
			$("#menu_form").attr("action","addOrEditMenu");
			submitFormForAnji("menu_form","${webcontext}/maintain/menu/addOrEditMenu",0);
//			$("#menu_form").submit();
		}
		//edit
        function editMenu(){
			var selMenu=$("#tableResults input:radio:checked");
        	if(selMenu.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	$("#menuId").val(selMenu.val());
        	$("#menu_form").attr("action","addOrEditMenu");
			submitFormForAnji("menu_form","${webcontext}/maintain/menu/addOrEditMenu",0);
//			$("#menu_form").submit();
        }
		//delete
        function deleteMenu(){
        	var selMenu=$("#tableResults input:radio:checked");
        	if(selMenu.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	if(comfirmForAnji('Do you confirm to delete this record?',null)){
	        	$("#menuId").val(selMenu.val());
	        	$("#menu_form").attr("action","deleteMenu");
				submitFormForAnji("menu_form","${webcontext}/maintain/menu/deleteMenu",0);
//		 		$("#menu_form").submit();
		    }
        }
		//detail
        function showMenuDetail(){
        	var selMenu=$("#tableResults input:radio:checked");
        	if(selMenu.length==0){
        		alertForAnji('Please select one record.',null);
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
                	
                	<ec:table styleClass="DataGrid"  style="height:25px;width:100%;" border="1"  cellpadding="0" cellspacing="0" tableId="tableResults"
					   items="queryMenuDto.resultList"
					   action="${webcontext}/maintain/menu/queryMenu"
					   imagePath="${webcontext}/images/table/*.gif"
					   filterable="false"
						sortable="true"
  					   showPagination="false"
  					   autoIncludeParameters="false"
  					   showTitle="false"
  					   showStatusBar="false"
  					   var="bo"
					   >
						 <ec:row>
						 	<ec:column alias="Select">
						 		 <input type='radio' name='selId' sortable="false" value='${bo.id }'/>
						 	</ec:column>
						 	<ec:column property="menuCode"></ec:column>
						 	<ec:column property="menuName"></ec:column>
						 	<ec:column property="parentMenuCode"></ec:column>
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