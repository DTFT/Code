package com.servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/reg")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String sex=request.getParameter("sex");
		String address=request.getParameter("address");
		String interests[]=request.getParameterValues("interest");
		String interest="";
		
		response.setContentType("text/html;charset=utf-8");
	       PrintWriter out=response.getWriter();
	       out.println("<html><body>");
	
	       out.println("username="+username+"</br>");
	       out.println("password="+password+"</br>");
	       out.println("sex="+sex+"</br>");
	       out.println("address="+address+"</br>");
	       for(String in:interests){
	    	   interest+=in;
	       }
	       out.println("interest="+interest);
	       
	       out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
