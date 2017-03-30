<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Common Error Page</title>
</head>
<body>

<h1><spring:message code="error.title" text="Errors"/></h1>

<% 
	String errCode = (String)request.getAttribute("errCode");
	if (errCode != null && errCode.trim().length() > 0) {
%>
<h3><font color=red><spring:message code='<%=errCode%>'/></font></h3>

<br>
<%
	} 
%>


</body>
</html>