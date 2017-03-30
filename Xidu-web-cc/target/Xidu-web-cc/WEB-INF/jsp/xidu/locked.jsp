<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>MLV</title>

<link href="${csspath}/style.css" type="text/css" rel="stylesheet">
<link href="${csspath}/prettyPhoto.css" type="text/css" rel="stylesheet">
<%@include file="../include/baseJsAndCss.jsp"%>
</head>

<body style="cursor: default;" leftmargin="0" topmargin="0">

<%@include file="include/head.jsp"%>

  <br>
  <table border="0" cellpadding="5" cellspacing="5" width="900">
    <tbody>
      <tr>
        <td align="center" valign="top" width="40%"><img src="${imagepath}/home.png"></td>
        <td valign="top" width="60%"><span id="Label1" class="uiWelcomeHeader">Welcome to MLV</span> <br>
          <br>
          <span id="Label2" class="uiHomePageMessage">ANJI-CEVA Logistics' MLV
          is a centrally hosted, integrated suite of supply chain technologies 
          that enables us to manage complex domestic and global supply chains.<br>
          <br>
          </span> <span id="Label3" class="uiHomePageMessage">It provides a 
          real-time executable link for ANJI-CEVA Logistics and our trading partners. 
          Matrix supports inbound just-in-time logistics, outbound logistics and 
          reverse logistics across multiple industry verticals and integrates a 
          complete suite of transportation, inventory management, order 
          fulfillment, financial settlement and e-commerce applications that 
          enables global collaboration with trading partners.<br>
          </span></td>
      </tr>
    </tbody>
  </table>
  <span id="PageMessageUpdatePanel"></span> 
  <script LANGUAGE="JavaScript">	
 
  		$(document).ready(
  			function(){
  				if(confirm("${info}")){
  					location.href="/showcase/maintain/user/initpassword";
  				}
  			}
  		);
  </script>
</body>
</html>