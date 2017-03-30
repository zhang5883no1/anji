<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>

  </title>
	<script LANGUAGE="JavaScript">		
		function search(){
			$("#user_form").submit();
		}
		//add 
		function adduser(){
			$("#id").val("");
			$("#user_form").attr("action","addOrEdituser");
			submitFormForAnji("user_form","${webcontext}/maintain/user/addOrEdituser",0);
//			$("#user_form").submit();
		}
		//edit
        function edituser(){
			var seluser=$("#tableResults input:radio:checked");
        	if(seluser.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	$("#id").val(seluser.val());
        	$("#user_form").attr("action","addOrEdituser");
			submitFormForAnji("user_form","${webcontext}/maintain/user/addOrEdituser",0);
//			$("#user_form").submit();
        }
		//delete
        function deleteuser(){
        	var seluser=$("#tableResults input:radio:checked");
        	if(seluser.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	if(comfirmForAnji('Do you confirm to delete this record?',null)){
	        	$("#id").val(seluser.val());
	        	$("#user_form").attr("action","deleteuser");
				submitFormForAnji("user_form","${webcontext}/maintain/user/deleteuser",0);
//		 		$("#user_form").submit();
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
        <td><span id="lblHeader" class="PageTitle">user</span></td>
      </tr>
    </tbody>
  </table>
  <form:form id="user_form" name ="user_form" action="queryUser" method="post" modelAttribute="userDto">
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
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">User Code:</span></td>
                  <td nowrap="nowrap">
                  	<form:input path="userCode"  cssClass="userInput" maxLength="15"/> 
                  </td>
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">User Name:</span></td>
                  <td nowrap="nowrap">
                  	<form:input path="name"  cssClass="userInput" maxLength="15"/> 
                  </td>
                <!--<td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">Status Code:</span></td>
                  <td nowrap="nowrap">
                  	<form:input path="statusCode"  cssClass="userInput" maxLength="5"/> 
                  </td>
                </tr>
                                <tr>
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">User Type:</span></td>
                  <td nowrap="nowrap">
                  	<form:input path="userType"  cssClass="userInput" maxLength="5"/> 
                  </td>
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">Employer Owner Id:</span></td>
                  <td nowrap="nowrap">
                  	<form:input path="employerOwnerId"  cssClass="userInput" maxLength="5"/> 
                  </td>  -->  
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
                      <ec:table styleClass="DataGrid"  style="height:25px;width:100%;" border="1"  cellpadding="0" cellspacing="0" tableId="tableResults" form="user_form"
					   items="userDto.resultList"
					   action="${webcontext}/maintain/user/queryUser"
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
						    <ec:column property="userCode" title="User Code"/>
						    <ec:column property="password" title="Password"/>
						    <ec:column property="name" title="User Name"/>
						    <ec:column property="statusCode" title="Status Code"/>
						    <ec:column property="userType" title="User Type"/>
						    <ec:column property="employerOwnerId" title="Employer Owner Id"/>
						 </ec:row>
					</ec:table>
                  </div></td>
              </tr>
              
            </tbody>
          </table>
          <table style="width:100%">
            <tr>
              <td align="right"><input style="width:90px; text-align:center;" class="Button" type="button"  onClick="adduser();" value="Add" name="btnAdd">
              <input style="width:90px; text-align:center;" class="Button" type="button"  id="btnReset" onClick="edituser();" value="Edit" name="btnEdit">
             <input style="width:90px; text-align:center;" class="Button" type="button"  id="btnReset" onclick="deleteuser();" value="Delete" name="btnDelete"> </td>
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