<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head><%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
  </title>
	<script LANGUAGE="JavaScript">		
		function search(){
			submitFormForAnji("role_form","${webcontext}/maintain/role/queryrole",0);
//			$("#role_form").submit();
		}
		//add 
		function addrole(){
			$("#id").val("");
			$("#role_form").attr("action","addOrEditRole");
			submitFormForAnji("role_form","${webcontext}/maintain/role/addOrEditRole",0);
//			$("#role_form").submit();
		}
		//edit
        function editrole(){
			var selrole=$("#tableResults input:radio:checked");
        	if(selrole.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	$("#id").val(selrole.val());
        	$("#role_form").attr("action","addOrEditRole");
			submitFormForAnji("role_form","${webcontext}/maintain/role/addOrEditRole",0);
//			$("#role_form").submit();
        }
		//delete
        function deleterole(){
        	var selrole=$("#tableResults input:radio:checked");
        	if(selrole.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	if(comfirmForAnji('Do you confirm to delete this record?',null)){
	        	$("#id").val(selrole.val());
	        	$("#role_form").attr("action","deleterole");
				submitFormForAnji("role_form","${webcontext}/maintain/role/deleterole",0);
//		 		$("#role_form").submit();
		    }
        }
		
    </script>   
	
<head></head>
<body style="cursor: default;" leftmargin="0" topmargin="0">
 
  <%@include file="../../include/head.jsp"%>
   
  <br>
  <table border="0" cellspacing="2" width="100%">
    <tbody>
      <tr>
        <td><span id="lblHeader" class="PageTitle">Role</span></td>
      </tr>
    </tbody>
  </table>
  <form:form id="role_form" name ="role_form" action="queryrole" method="post" modelAttribute="RoleDto">
 	<form:hidden path="id" id="id"/>
  <span id="UpdatePanelSearch">
  <table cellpadding="3" cellspacing="1" width="100%">
    <tbody>
      <tr>
        <td><fieldset>
            <legend id="legSearchCriteria" class="legend">Search Criteria</legend>
            <table border="0" cellpadding="1" cellspacing="1" width="100%">
              <tbody>
                <tr>
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">Role Code:</span></td>
                  <td nowrap="nowrap">
                  	<form:input path="roleCode"  cssClass="userInput" maxLength="15"/> 
                  </td>
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">Role Name:</span></td>
                  <td nowrap="nowrap">
                  	<form:input path="roleName"  cssClass="userInput" maxLength="15"/> 
                  </td>
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">Role Desc:</span></td>
                  <td nowrap="nowrap">
                  	<form:input path="roleDesc"  cssClass="userInput" maxLength="15"/> 
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
                    <input name="btnSearch" value="Search" onClick="search();" id="btnSearch" class="Button" type="button"  style="width:90px;" type="button"/>
                    <input name="btnSearch" value="Reset" onClick="clearAll();" id="btnSearch" class="Button" type="button"  style="width:90px;" type="button"/>
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

  <table id="tableResults" cellpadding="1" cellspacing="1" width="100%">
    <tbody>
      <tr>
        <td><table border="0" cellpadding="1" cellspacing="1" width="100%">
            <tbody>
              <tr>
                <td colspan="2"><div id="resizableDiv" class="DivResultsGrid" style="OVERFLOW: auto;border-top:solid 1px silver">
                      <ec:table styleClass="DataGrid"  style="height:25px;width:100%;" border="1"  cellpadding="0" cellspacing="0" tableId="tableResults" form="role_form"
					   items="RoleDto.resultList"
					   action="${webcontext}/maintain/role/queryrole"
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
						 	<ec:column  alias="Select"  sortable="false">
						 	<input type='radio' name='selId' value='${bo.id }'/> 
						 	</ec:column>
						    <ec:column property="roleCode" title="Role Code"/>
						    <ec:column property="roleName" title="Role Name"/>
						    <ec:column property="roleDesc" title="Role Desc"/>
						 </ec:row>
					</ec:table>
                  </div></td>
              </tr>
              
            </tbody>
          </table>
          <table style="width:100%">
            <tr>
              <td align="right"><input style="width:90px; text-align:center;" class="Button" type="button"  onClick="addrole();" value="Add" name="btnAdd">
              <input style="width:90px; text-align:center;" class="Button" type="button"  id="btnReset" onClick="editrole();" value="Edit" name="btnEdit">
             <input style="width:90px; text-align:center;" class="Button" type="button"  id="btnReset" onclick="deleterole();" value="Delete" name="btnDelete"> </td>
            </tr>
          </table>
          </td>
      </tr>
    </tbody>
  </table>
   </span>
  <span id="PageMessageUpdatePanel"></span> 
  <script type="text/javascript">
//<![CDATA[
_pleaseWaitMessage='Please Wait...';_ajax_SessionTimeoutMessage='Your session has expired, please login again.';_ajax_MaxRequestLengthMessage='Your request exceeds the maximum allowed.  Please try limiting your criteria and re-try your request again.';_ajax_SQLTimeoutMessage='Matrix is currently experiencing a large volume of traffic.  Please re-try your request again.';_ajax_ServerTimeoutMessage='Your request exceeded the maximum allowed timeout period.  This can be caused by system issues and/or networking related problems.  \r\rPlease Note that the request may have completed and resubmitting the request may result in duplicate records.';Sys.Application.add_load(PageResize);$addHandler(window, 'resize', PageResize );EndRequestHandler();(function() {var fn = function() {$get("ctl01_HiddenField").value = '';Sys.Application.remove_init(fn);};Sys.Application.add_init(fn);})();//]]>
</script> 
  <script language="JavaScript" for="document" event="onhelp()">var link = document.getElementById("header_lnkHelp");if ( link != null ){link.click();document.parentWindow.event.returnValue=0;}</script>


  </form:form>
</body>
</html>