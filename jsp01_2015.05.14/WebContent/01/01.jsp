<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>hello jsp</h1>
	<%--jsp内置对象  out(printwriter)  request  response  session application --%>
	<%
		String str="hello";
		out.println(str+"</br>");
		String name=request.getParameter("username");
		out.println(name);
	%>
	</br>
	<%=name%>
</body>
</html>