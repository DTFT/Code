package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginCl
 */
public class LoginCl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 重写init函数
	// 只会被调用一次
	public void init() {
		try {
			System.out.println("init被调用");

			// 添加网页访问次数功能
			// 读取访问次数
			FileReader fr = new FileReader("f:\\times.txt");
			BufferedReader br = new BufferedReader(fr);
			String num = br.readLine();
			br.close();
			fr.close();

			// 将times值放入到servletcontext中
			this.getServletContext().setAttribute("visitTimes", num);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 重写destroy函数
	public void destroy() {
		try {

			System.out.println("destroy被调用");
			FileWriter fw = new FileWriter("f:\\times.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			// 从servletcontext读取
			bw.write(this.getServletContext().getAttribute("visitTimes")
					.toString());
			bw.close();
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String u = request.getParameter("username");
		String p = request.getParameter("passwd");

		// 调用userbeanCl
		// 1、创建一个兑现
		UserBeanCl ubc = new UserBeanCl();
		// 2、使用userbeancl方法
		if (ubc.checkUser(u, p)) {

			// 用户合法

			String keep = request.getParameter("keep");
			// 将用户名和密码保存在客户端利用cookie
			// 创建cookie
			if (keep != null) {
				Cookie name = new Cookie("myname", u);
				Cookie pass = new Cookie("mypasswd", p);

				// 设置时间
				name.setMaxAge(14 * 24 * 3600);
				pass.setMaxAge(14 * 24 * 3600);

				// 回写到客户端
				response.addCookie(name);
				response.addCookie(pass);
			}

			// 创建session，将验证成功的信息存到session
			// 1、得到session
			// true: 如果session存在，则返回该session，否则创建一个新的session；
			// false: 如果session存在，则返回该session，否则返回null.
			HttpSession hs = request.getSession(true);
			// 修改session时间
			hs.setMaxInactiveInterval(20);
			// 存值
			// hs.setAttribute("pass", "ok");
			hs.setAttribute("name", u);
			
			//讲servletcontext中的visittime值++
			String times=this.getServletContext().getAttribute("visitTimes").toString();
			//对times++在重新放回
			this.getServletContext().setAttribute("visitTimes", Integer.parseInt(times)+1+"");
			
			
			response.sendRedirect("Welcome?uname=" + u + "&upasswd=" + p); // 传递用户名
		} else {
			// 说明用户不存在
			response.sendRedirect("Login"); // 写跳转的URL
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
