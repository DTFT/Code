<%@page import="com.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <!--重定向  对于response.sendRedirect跳转，跳转后依然会执行后面带完才跳转 -->
    <%
    User u=null;
    //客户端跳转 地址变
    response.sendRedirect("03.jsp?username=啊啊啊");
    //在response.sendRedirect后加return 立刻跳转
    //return;
    %>
</body>
</html>