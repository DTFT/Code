package com.servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
       resp.setContentType("text/html;charset=UTF-8");
       PrintWriter out=resp.getWriter();
       out.println("<html><body>");
       //获取上下文路径
       out.println("<h1>"+req.getContextPath()+"</h1>");
       //获取绝对路径
       out.println("<h1>"+req.getSession().getServletContext().getRealPath("/")+"</h1>");
       //获取请求参数
      
       String username=req.getParameter("username");
       String psw=req.getParameter("password");
       out.println("<h1>Hello:"+username+"</h1>");
       out.println("<h1>password:"+psw+"</h1>");
       
       int times=Integer.parseInt(req.getParameter("times"));
       for (int i = 0; i < times; i++) {
		out.println("hello"+i);
	}
       out.println("</body></html>");
       
       
}
}
