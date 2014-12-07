package com.test;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginCl
 */
public class LoginCl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String u=request.getParameter("username");
		String p=request.getParameter("passwd");
		Connection ct=null;
		Statement sm=null;
		ResultSet rs=null;
		//连接数据库
		try {
			 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				//2、得到连接
				ct=DriverManager.getConnection("jdbc:odbc:test","scott","tiger");
			
			 sm=ct.createStatement();
			 
			 //有sql注入漏洞
			 // select * from users where username='dtft'  and passwd='  aa' or 1='1   ';
			 //rs=sm.executeQuery("select * from users where username='"+u+"'and passwd='"+p+"'");
				 
			 rs=sm.executeQuery("select passwd from users where username='"+u+"'");

			 if(rs.next()){
				 //说明用户存在
				 String dbpass=rs.getString(1);
				 if(dbpass.equals(p)){
					 //用户合法
					 //创建session，将验证成功的信息存到session
					 //1、得到session
					 //true: 如果session存在，则返回该session，否则创建一个新的session；
					 //false: 如果session存在，则返回该session，否则返回null.
					 HttpSession hs=request.getSession(true);
					 //修改session时间
					 hs.setMaxInactiveInterval(20);
					 //存值
					 hs.setAttribute("pass", "ok");
					 response.sendRedirect("Welcome?uname="+u+"&upasswd="+p);  //传递用户名
				 }else{
					 //说明用户不存在
					 response.sendRedirect("Login");	//写跳转的URL
				 }
				 
			 }else{
				 //说明用户不存在
				 response.sendRedirect("Login");	//写跳转的URL
			 }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				//关闭流  先开后关
				if(rs!=null){
					rs.close();
				}
				if(sm!=null){
					sm.close();
				}
				if(ct!=null){
					ct.close();					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
