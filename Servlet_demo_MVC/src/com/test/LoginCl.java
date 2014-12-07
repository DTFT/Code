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

	// ��дinit����
	// ֻ�ᱻ����һ��
	public void init() {
		try {
			System.out.println("init������");

			// �����ҳ���ʴ�������
			// ��ȡ���ʴ���
			FileReader fr = new FileReader("f:\\times.txt");
			BufferedReader br = new BufferedReader(fr);
			String num = br.readLine();
			br.close();
			fr.close();

			// ��timesֵ���뵽servletcontext��
			this.getServletContext().setAttribute("visitTimes", num);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// ��дdestroy����
	public void destroy() {
		try {

			System.out.println("destroy������");
			FileWriter fw = new FileWriter("f:\\times.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			// ��servletcontext��ȡ
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

		// ����userbeanCl
		// 1������һ������
		UserBeanCl ubc = new UserBeanCl();
		// 2��ʹ��userbeancl����
		if (ubc.checkUser(u, p)) {

			// �û��Ϸ�

			String keep = request.getParameter("keep");
			// ���û��������뱣���ڿͻ�������cookie
			// ����cookie
			if (keep != null) {
				Cookie name = new Cookie("myname", u);
				Cookie pass = new Cookie("mypasswd", p);

				// ����ʱ��
				name.setMaxAge(14 * 24 * 3600);
				pass.setMaxAge(14 * 24 * 3600);

				// ��д���ͻ���
				response.addCookie(name);
				response.addCookie(pass);
			}

			// ����session������֤�ɹ�����Ϣ�浽session
			// 1���õ�session
			// true: ���session���ڣ��򷵻ظ�session�����򴴽�һ���µ�session��
			// false: ���session���ڣ��򷵻ظ�session�����򷵻�null.
			HttpSession hs = request.getSession(true);
			// �޸�sessionʱ��
			hs.setMaxInactiveInterval(20);
			// ��ֵ
			// hs.setAttribute("pass", "ok");
			hs.setAttribute("name", u);
			
			//��servletcontext�е�visittimeֵ++
			String times=this.getServletContext().getAttribute("visitTimes").toString();
			//��times++�����·Ż�
			this.getServletContext().setAttribute("visitTimes", Integer.parseInt(times)+1+"");
			
			
			response.sendRedirect("Welcome?uname=" + u + "&upasswd=" + p); // �����û���
		} else {
			// ˵���û�������
			response.sendRedirect("Login"); // д��ת��URL
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
