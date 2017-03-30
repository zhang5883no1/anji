<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"  %>
<%@ page import="com.xidu.framework.common.Utils"  %>
<%@include file="../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	${title }
  </title>
	<script type="text/javascript" src="${jspath}/jquery_1.3.2.min.js"></script>
	<script type="text/javascript" src="${jspath}/jquery.blockUI.js"></script>
	
	<link href="${csspath}/style.css" type="text/css" rel="stylesheet"/>
	<link href="${csspath}/prettyPhoto.css" type="text/css" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="${csspath}/extremecomponents.css"/>
	
	<script language="javascript" src="${anjijspath}/utilities.js"></script>
	<script language="javascript" type="text/javascript">CheckAJAXCompatibility();</script>
	<script type='text/javascript' src='${jspath}/jquery.js'></script>
	<script type='text/javascript' src='${jspath}/custom.js'></script>
	<script type='text/javascript' src='${jspath}/jquery.prettyPhoto.js'></script>
	<script type='text/javascript' src='${anjijspath}/page.js'></script>
	<script type='text/javascript' src='${anjijspath}/pub.js'></script>
	<script type='text/javascript' src='${anjijspath}/extremecomponents.js'></script>
	
	<script LANGUAGE="JavaScript">		
		function search(){
			submitFormForAnji("common_form","${webcontext}/common/showQuery/query");
		}
	
		//confirm
        function confirm(){
        	var selSupplier=$("#tableResults input:radio:checked");
        	if(selSupplier.length==0){
        		alert('Please select one record.');
        		return;
        	}
        	var sels=selSupplier.val().split('##');
        	
        	
        	var targetIds=$("input[name='targetId']").val();
        	var ts=targetIds.split(',');
        	for(var i=0;i<ts.length;i++){
        		opener.document.getElementById(ts[i]).value=sels[i];      		
        	}
        	
        	
        	window.close();
        }
		
		
		
    </script>   
</head>


<body style="cursor: default;" leftmargin="0" topmargin="0">
	
   
  <br>
  <table border="0" cellspacing="2" width="100%">
    <tbody>
      <tr>
        <td><span id="lblHeader" class="PageTitle">${title }</span></td>
      </tr>
    </tbody>
  </table>
  
  
  <form:form id="common_form" name ="common_form" action="query" method="post" modelAttribute="queryCommonDto">
  <input type="hidden" name="queryKey" value="${queryKey }"/>
  <input type="hidden" name="targetId" value="${targetId }"/>
  <span id="UpdatePanelSearch">
  <table cellpadding="3" cellspacing="1" width="100%">
    <tbody>
      <tr>
        <td><fieldset>
            <legend id="legSearchCriteria" class="legend">Search Criteria</legend>
            <table border="0" cellpadding="1" cellspacing="1" width="100%">
              <tbody>
              	<%
              	  List queryTitleList=(List)request.getAttribute("queryTitleList");
              	  List queryNameList=(List)request.getAttribute("queryNameList");
              	  List queryValueList=(List)request.getAttribute("queryValueList");
              	  for(int i=0;i<queryTitleList.size();i++){
              	%>
              	<%if(i%3==0){ %>
                <tr>
                <%} %>
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold"><%=queryTitleList.get(i) %></span></td>
                  <td nowrap="nowrap">
                  	<input type="text" class="userInput" name="<%=queryNameList.get(i)%>" value="<%=queryValueList.get(i)%>"/>
                  </td>
                  
                 <%if(i%3==2 || i==queryTitleList.size()-1){ %>
                </tr>
                 <%} %>
              <%} %>
                
              </tbody>
            </table>
            <hr style="width:99%;">
            <table border="0" cellpadding="1" cellspacing="1" width="100%">
              <tbody>
                <tr>
                  <td style="padding-left:2px"><span><span class="Label" style="color:Red;">* will match one or more words.</span>&nbsp;<img src="${imagepath}/i.gif" style="border-width:0px;"></span></td>
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
  
  <%@include file="../include/page.jsp"%>

  <table id="tableResults" cellpadding="1" cellspacing="1" width="100%">
    <tbody>
      <tr>
        <td><table border="0" cellpadding="1" cellspacing="1" width="100%">
            <tbody>
              <tr>
                <td colspan="2"><div scrolltop="0" onscroll="SaveScrollPosition('ssresizable','resizableDiv')" divoffsetright="12" style="border-right:silver 1px solid;border-top:silver 1px solid;overflow:auto;border-left:silver 1px solid;border-bottom:silver 1px solid;scrollLeft:0;" class="DataGridDivHorizontalScrolling" id="resizableDiv">
                    <table cellspacing="0" cellpadding="0" border="1" align="Right" style="height:25px;width:100%;" id="resultsGrid" rules="all" class="DataGrid">
                      <tbody>
                          
                        <tr align="center" style="color:White;" class="DataGridHeader">
                          <th align="center" scope="col">Select</th>
                           <%
								List titleList= (List)request.getAttribute("titleList");
                           	    String returnColumn=(String)request.getAttribute("returnColumn");
								for(int i=0;i<titleList.size();i++){ 
 							%>
                          		<th scope="col"><%=titleList.get(i) %></th>
                           <%} %>
                       </tr>
                           <%
								List resultList= (List)request.getAttribute("resultList");
                           		List propertyList=(List)request.getAttribute("propertyList");
								for(int j=0;j<resultList.size();j++){ 
									String returnValue="";
									if(returnColumn!=null && !"".equals(returnColumn)){
										String[] rns=returnColumn.split(",");
										returnValue+=Utils.getProperty(resultList.get(j), rns[0]);
										for(int k=1;k<rns.length;k++){
											returnValue+="##"+Utils.getProperty(resultList.get(j), rns[k]);
										}
									}
 					       %>
	                        <tr  onMouseOver="this.style.cursor='hand';" class="DataGridItem" style="">
	                          <td align="center"><input type="radio"   name="select"  value="<%=returnValue %>"></td>
		                            <%
										for(int i=0;i<propertyList.size();i++){ 
		 							%>
		                          		<td><%=Utils.getProperty(resultList.get(j), (String)propertyList.get(i))==null ? "" : Utils.getProperty(resultList.get(j), (String)propertyList.get(i)) %></td>
		                           <%} %>
	                          
	                        </tr>
	                        <%} %>
                      </tbody>
                    </table>
                </div></td>
              </tr>
            </tbody>
          </table>
  		


          <table style="width:100%">
            <tr>
              <td align="right"><input type="button" style="width:90px; text-align:center;" class="Button" onClick="confirm();" value="Confirm" name="confrim">  </td>
            </tr>
          </table>
          </td>
      </tr>
    </tbody>
  </table>
  
  

  <span id="PageMessageUpdatePanel"></span> 
  
  


 </form:form>


</body></html>