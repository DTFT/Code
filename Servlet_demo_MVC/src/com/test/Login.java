package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   response.setCharacterEncoding("UTF-8"); // ��ָ��������ı���
		   response.setContentType("text/html;charset=UTF-8");
		   
		PrintWriter pw=response.getWriter();
		pw.println("<html>");
		pw.print("<font color='white'>");
		pw.print("<body background='imgs/3.jpg'>");
		pw.println("<img src='imgs/2.jpg'  width='300' hight='300'><hr><center>");
		 
		//�õ�error��Ϣ
		String info=request.getParameter("info");
		if(info!=null){
			pw.print("<h1>����û������������</h1>");
		}
		pw.println("<h1>��½����</h1>");
		pw.println("<form action=LoginCl  method=post>");
		pw.println("�û���: <input type=text name=username><br>");
		pw.println("����: <input type=password name=passwd><br>");
		pw.println("<input type=checkbox name=keep value=2>���������½<br>");
		pw.println(" <input type=submit value=login><br>");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
