<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="">
	请输入数字<input type="text" name="number">
	<input type="submit" value="提交">
	</form>
	<%
		try{
		int	number=Integer.parseInt(request.getParameter("number"));
	%>	
		<table border=1>
	<%
			for(int i=1;i<=number;i++){
	%>
		<tr>
	<%
		for(int j=1;j<=number;j++){
	%>
		<td><%=i %>*<%=j %>=<%=i*j %></td>
	<% 		
		}
	%>		
		</tr>
	<% 
			}
	%>
		</table>
		
	<%
		}catch(NumberFormatException e){  %>
		
			<h1>请输入正确的格式</h1>
	
	<% 	
	}
	
	%>
</body>
</html>