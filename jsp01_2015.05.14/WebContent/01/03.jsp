<%@page import="com.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.model.UserInit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%
 	List<User> users=UserInit.initUser();

%>
</head>
<body>
	<%=request.getParameter("username") %>
<table width="700" align="center" border="1">
	<tr>
	<td>id</td><td>用户名</td><td>昵称</td>
	</tr>
	<%
	for(User u:users){
	%>
	<tr>
	<td><%=u.getId() %></td><td><%=u.getName() %></td><td><%=u.getNickname() %></td>
	</tr>
	<%
	}
	%>
	
</table>
</body>
</html>











