package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.Buffer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); // ��ָ��������ı���
		response.setContentType("text/html;charset=UTF-8");
		int PageSize = 3;
		int PageNow = 1;

		String sPageNow = request.getParameter("PageNow");
		if (sPageNow != null) {
			// �û���һ�ν���welcome
			PageNow = Integer.parseInt(sPageNow);
		}

		// �õ�session
		HttpSession hs = request.getSession(true);
		// ȡ��session��ֵ
		String uname = (String) hs.getAttribute("name");

		String name = null;
		String passwd = null;
		// �ж�
		if (uname == null) {
			// ���session��û���û���Ϣ������cookie
			Cookie[] allCookies = request.getCookies();
			if (allCookies != null) {

				for (int i = 0; i < allCookies.length; i++) {
					Cookie temp = allCookies[i];
					if (temp.getName().equals("myname")) {
						name = temp.getValue();
					} else if (temp.getName().equals("mypasswd")) {
						passwd = temp.getValue();
					}
				}
				if (!name.equals("") && !passwd.equals("")) {
					// ��login����ȥ��֤�����ⴴ��cookieƭ����½
					response.sendRedirect("LoginCl?username=" + name
							+ "&passwd=" + passwd);

					return;
				}
			}
			response.sendRedirect("Login?info=error");
		}

		PrintWriter pw = response.getWriter();
		// servlet����ʾͼƬ
		pw.print("<body><center>");
		pw.print("<img src='1.jpg'  width='' hight='200'>  <br>");

		// �õ���logincl���ݵ��û���

		pw.print("Welcome," + uname);
		pw.print("<a href=Login>�������µ�¼</a>");

		// -----------------------------------
		pw.println("<table border=1>");
		pw.println("<tr><th>id</th><th>name</th><th>passwd</th>"
				+ "<th>email</th><th>grade</th></tr>");
	
			//����userBeancl
		UserBeanCl ubc=new UserBeanCl();
		
		
		ArrayList al=ubc.getResultByPage(PageNow, PageSize);

			for(int i=0;i<al.size();i++){
				UserBean ub=(UserBean)al.get(i);
				
				pw.println("<tr>");
				pw.println("<td>" + ub.getUserId() + "</td>");
				pw.println("<td>" + ub.getUserName() + "</td>");
				pw.println("<td>" + ub.getPassWd() + "</td>");
				pw.println("<td>" +ub.getEmail() + "</td>");
				pw.println("<td>" + ub.getGrade() + "</td>");
				pw.println("</tr>");

				// System.out.println("id:"+rs.getInt(1)+",name:"+rs.getString(2));
				// System.out.println("rownum"+rowNum);
				// System.out.println("PageCount"+PageCount);
			}
			pw.println("</table>");

			int PageCount =ubc.getPageCount();
			// ��ʾ������
			if (PageNow != 1)
				pw.print("<a href=Welcome?PageNow=" + (int) (PageNow - 1)
						+ ">ǰһҳ</a>&nbsp;&nbsp;");

			for (int i = 1; i <= PageCount; i++) {
				pw.print("<a href=Welcome?PageNow=" + i + ">" + i
						+ "</a>&nbsp; ");
			}
			if (PageNow != PageCount)
				pw.print("&nbsp;&nbsp;<a href=Welcome?PageNow="
						+ (int) (PageNow + 1) + ">��һҳ </a>");
		
		// --------------------------
			int times=Integer.parseInt(this.getServletContext().getAttribute("visitTimes").toString());
			
			pw.println("<br>����ҳ��������"+times+"��<br>");
			pw.println("����ip��"+request.getRemoteAddr()+"<br>");
			pw.println("���Ļ�������"+request.getRemoteHost()+"<br>");

	
		pw.print("</center></body>");

		
		
		
		
	
	
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
