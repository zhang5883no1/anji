<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	error page
  </title>
	<script type="text/javascript" src="${jspath}/jquery_1.3.2.min.js"></script>
	<script type="text/javascript" src="${jspath}/jquery.blockUI.js"></script>
    <script type="text/javascript" language="javascript">
	  if(self!=top){
		  top.location.href= location.href;
	  }
	  
	  function showMsg(){
		  if($("#errMsg").css("display")=='none'){
			  $("#errMsg").show();
		  }else{
			  $("#errMsg").hide();
		  }
	  }
	  function goToURL(url){
		  top.location.href=url;
	  }
   </script>
   
	<style type="text/css">
	        .style1
	        {
	            height: 6px;
	            width: 27px;
	        }
	        .style2
	        {
	            width: 27px;
	        }
	    </style>
	     
</head>
<body style="background-color:#ffffff; margin-left:0px; margin-top:0px; margin-right:0px;" marginheight="0" marginwidth="0" text="#000000">
    <form:form id="login_form" name ="login_form" action="login" method="post" modelAttribute="loginDto">
    <div style="width:100%;height:100%;border:0px solid red;text-align:center;padding-top:10px;background-color:#ffffff;">
    <div class="info_div">
		<span><form:errors path="*"/></span>
	</div>
        <table style="border:0px solid red;margin:auto;">
            <tbody><tr>
                    <td>
                       
                       <% 
							String errCode = (String)request.getAttribute("errCode");
                       		String errMsg=(String)request.getAttribute("errMsg");
                       		String backUrl=(String)request.getAttribute("backUrl");
							if (errCode != null && errCode.trim().length() > 0) {
						%>
						<h3><font color=red><spring:message code='<%=errCode%>'/></font></h3>
						<a onclick="showMsg();" href="javascript:void(0);">details</a>
						
						<br>
						<span style="display:none;" id="errMsg"><%=errMsg %></span>
						<br/>
						<%
							if(backUrl==null || "".equals(backUrl.trim())){
						%>
					
						<a onclick="history.go(-1);" href="javascript:void(0);">back</a>
						<%}else{ %>
						<a onclick="goToURL('<%=backUrl %>');" href="javascript:void(0);">back</a>
						<%} %>
						<br/>
						<%
							} 
						%>
                       
                    </td>                      
                    </tr>               
      
                <tr>
                    <td style="text-align:right; ">
                    <p style="font-size:12px;">
                            <font face="Geneva, Arial, Helvetica, san-serif ">   Copyright© 2002-2012 ANJI-CEVA All Rights Reserved &nbsp;&nbsp;</font></p> 
                      
                    </td>
                   
                </tr>
            </tbody></table>
    </div>
    


</form:form>


</body></html>