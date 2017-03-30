<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>MLV</title>
<link href="${csspath}/style.css" type="text/css" rel="stylesheet">
<link href="${csspath}/prettyPhoto.css" type="text/css" rel="stylesheet">
<script language="javascript" src="${jspath}/utilities.js"></script>
<script type='text/javascript' src='${jspath}/jquery.js'></script>
<script type='text/javascript' src='${jspath}/custom.js'></script>
<script type='text/javascript' src='${jspath}/jquery.prettyPhoto.js'></script>
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
          <span id="Label2" class="uiHomePageMessage"><br>
          <br>
          </span> <span id="Label3" class="uiHomePageMessage"><br>
          </span></td>
      </tr>
    </tbody>
  </table>
  <span id="PageMessageUpdatePanel"></span> 
</body>
</html>