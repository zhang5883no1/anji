<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<title>Insert title here</title>

</head>
<body>
	<%
		String message = (String) request.getAttribute("message");
		if (message != null) {
			out.println("<p>"+message+"</p>");
			out.println("<input type='button' value='继续上传' onclick='toupload()' />");
		}
	%>
	

<script type="text/javascript">
	function toupload(){
		location.href="http://zhibo.xiduoil.com/uploadKCB.jsp";		
	}
</script>
</body>
</html>
