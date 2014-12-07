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
		//�������ݿ�
		try {
			 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				//2���õ�����
				ct=DriverManager.getConnection("jdbc:odbc:test","scott","tiger");
			
			 sm=ct.createStatement();
			 
			 //��sqlע��©��
			 // select * from users where username='dtft'  and passwd='  aa' or 1='1   ';
			 //rs=sm.executeQuery("select * from users where username='"+u+"'and passwd='"+p+"'");
				 
			 rs=sm.executeQuery("select passwd from users where username='"+u+"'");

			 if(rs.next()){
				 //˵���û�����
				 String dbpass=rs.getString(1);
				 if(dbpass.equals(p)){
					 //�û��Ϸ�
					 //����session������֤�ɹ�����Ϣ�浽session
					 //1���õ�session
					 //true: ���session���ڣ��򷵻ظ�session�����򴴽�һ���µ�session��
					 //false: ���session���ڣ��򷵻ظ�session�����򷵻�null.
					 HttpSession hs=request.getSession(true);
					 //�޸�sessionʱ��
					 hs.setMaxInactiveInterval(20);
					 //��ֵ
					 hs.setAttribute("pass", "ok");
					 response.sendRedirect("Welcome?uname="+u+"&upasswd="+p);  //�����û���
				 }else{
					 //˵���û�������
					 response.sendRedirect("Login");	//д��ת��URL
				 }
				 
			 }else{
				 //˵���û�������
				 response.sendRedirect("Login");	//д��ת��URL
			 }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				//�ر���  �ȿ����
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
