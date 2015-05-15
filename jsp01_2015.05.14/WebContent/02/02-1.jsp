<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 相对路径   ../上级路径-->
	<a href="../01/03.jsp">访问03页面（相对路径）</a>
	<img src="../img/03.jpg"></br>
	
<!-- 绝对路径 -->
	<a href="<%=request.getContextPath() %>/01/03.jsp">
	访问03页面（绝对路径:/<%=request.getContextPath() %>）</a>
	
</body>
</html>