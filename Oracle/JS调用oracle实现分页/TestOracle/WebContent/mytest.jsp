<%@ page language="java"  import="java.util.*, java.sql.*"         contentType="text/html; charset=UTF-8"
    pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Oracle ��ҳ����</h2>
	<table>
		<tr><td>�û���</td><td>нˮ</td></tr>
		<%

			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection ct=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL","scott","tiger");
			
			 Statement sm=ct.createStatement();
			 
			 //����pageNow
			String s_pageNow=(String)request.getParameter("pageNow");
			 
			 int  pageNow=1;
			 if(s_pageNow!=null){
				 pageNow=Integer.parseInt(s_pageNow);
				 
				 
			 }
			 
			 //��ѯ��ҳ��
			 int pageCount=0;
			 int rowCount=0;  //���м����¼
			 int pageSize=3;//ÿҳ��ʾ������¼
			 
			 ResultSet rs=sm.executeQuery("select count(*) from emp");
			 
			 if(rs.next()){
				 rowCount=rs.getInt(1);
				 
				 if(rowCount%pageSize==0){
					 pageCount=rowCount/pageSize;
				 }else{
					 pageCount=rowCount/pageSize+1;
				 }
			 }
			 //��ӡ����ҳ��������
			 for(int i=1;i<=pageCount;i++){
				 out.print("<a href=mytest.jsp?pageNow="+i+"> "+i+" </a>");
			 }
			 
			 
			 rs=sm.executeQuery
					 ("select * from (select a1.*,rownum rn from(select * from emp) a1 where rownum<="
			 +pageNow*pageSize+")where rn>="+((pageNow-1)*pageSize+1)+"");
			 while (rs.next()) {
			 out.println("<tr>");
				//�û���
			out.println("<td>"+rs.getString(2)+"</td>");
			out.println("<td>"+rs.getString(6)+"</td>");
			 out.println("</tr>");
			}
			 //�رմ򿪵���Դ
			 	rs.close();	
			 	sm.close();
			 	ct.close();
%>
	
	</table>
</body>
</html>