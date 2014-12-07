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
		response.setCharacterEncoding("UTF-8"); // 先指定输出流的编码
		response.setContentType("text/html;charset=UTF-8");
		int PageSize = 3;
		int PageNow = 1;

		String sPageNow = request.getParameter("PageNow");
		if (sPageNow != null) {
			// 用户第一次进入welcome
			PageNow = Integer.parseInt(sPageNow);
		}

		// 得到session
		HttpSession hs = request.getSession(true);
		// 取出session的值
		String uname = (String) hs.getAttribute("name");

		String name = null;
		String passwd = null;
		// 判断
		if (uname == null) {
			// 如果session中没有用户信息、则检查cookie
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
					// 到login处理去验证，以免创造cookie骗过登陆
					response.sendRedirect("LoginCl?username=" + name
							+ "&passwd=" + passwd);

					return;
				}
			}
			response.sendRedirect("Login?info=error");
		}

		PrintWriter pw = response.getWriter();
		// servlet中显示图片
		pw.print("<body><center>");
		pw.print("<img src='1.jpg'  width='' hight='200'>  <br>");

		// 得到从logincl传递的用户名

		pw.print("Welcome," + uname);
		pw.print("<a href=Login>返回重新登录</a>");

		// -----------------------------------
		pw.println("<table border=1>");
		pw.println("<tr><th>id</th><th>name</th><th>passwd</th>"
				+ "<th>email</th><th>grade</th></tr>");
	
			//调用userBeancl
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
			// 显示超链接
			if (PageNow != 1)
				pw.print("<a href=Welcome?PageNow=" + (int) (PageNow - 1)
						+ ">前一页</a>&nbsp;&nbsp;");

			for (int i = 1; i <= PageCount; i++) {
				pw.print("<a href=Welcome?PageNow=" + i + ">" + i
						+ "</a>&nbsp; ");
			}
			if (PageNow != PageCount)
				pw.print("&nbsp;&nbsp;<a href=Welcome?PageNow="
						+ (int) (PageNow + 1) + ">后一页 </a>");
		
		// --------------------------
			int times=Integer.parseInt(this.getServletContext().getAttribute("visitTimes").toString());
			
			pw.println("<br>该网页被访问了"+times+"次<br>");
			pw.println("您的ip是"+request.getRemoteAddr()+"<br>");
			pw.println("您的机器名是"+request.getRemoteHost()+"<br>");

	
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
