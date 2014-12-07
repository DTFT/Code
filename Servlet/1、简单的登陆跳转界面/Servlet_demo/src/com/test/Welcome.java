package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Welcome
 */
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); // 先指定输出流的编码
		response.setContentType("text/html;charset=UTF-8");
		
			//得到session
			HttpSession hs=request.getSession(true);
			//取出session的值
			String val=(String) hs.getAttribute("pass");
			//判断
			if(val==null){
				response.sendRedirect("Login?info=error");
				
			}
			
		   PrintWriter pw=response.getWriter();
		   //servlet中显示图片
		   pw.print("<img src='1.jpg'  width='200' hight='200'>  <br>");
		   
		   pw.print("Welcome,");
		   
		   //得到从logincl传递的用户名
		   String u=request.getParameter("uname");
		   pw.print(u+",");
		   String p=request.getParameter("upasswd");
		   pw.println(p);
		   Connection ct=null;
		   //-----------------------------------
		   pw.println("<table border=1>");
		   pw.println("<tr><th>id</th><th>name</th><th>passwd</th>"
				   + "<th>email</th><th>grade</th></tr>");
		   try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection cn = DriverManager.getConnection(
				"jdbc:oracle:thin:@127.0.0.1:1521:ORCL", "scott", "tiger");
		
//			   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//				//2、得到连接
//				ct=DriverManager.getConnection("jdbc:odbc:test","scott","tiger");
				
				
				CallableStatement cs=cn.prepareCall("{call fenye(?,?,?,?,?,?)}");
		
		cs.setString(1, "users");
		cs.setInt(2, 5);
		cs.setInt(3, 1);
		
		cs.registerOutParameter(4, oracle.jdbc.OracleTypes.INTEGER);
		cs.registerOutParameter(5, oracle.jdbc.OracleTypes.INTEGER);
		cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
		cs.execute();
		
		int rowNum=cs.getInt(4);
		int PageCount=cs.getInt(5);
		ResultSet rs = (ResultSet) cs.getObject(6);
		
	   
		while(rs.next()){
			pw.println("<tr>");
			pw.println("<td>"+rs.getInt(1)+"</td>");
			pw.println("<td>"+rs.getString(2)+"</td>");
			pw.println("<td>"+rs.getString(3)+"</td>");
			pw.println("<td>"+rs.getString(4)+"</td>");
			pw.println("<td>"+rs.getInt(5)+"</td>");
			pw.println("</tr>");
			
//			System.out.println("id:"+rs.getInt(1)+",name:"+rs.getString(2));
//			System.out.println("rownum"+rowNum);
//			System.out.println("PageCount"+PageCount);
		}
		pw.println("</table>");
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   //--------------------------
		   
		 
		   
		   pw.print("<br><a href=Login>返回重新登录</a>");
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
