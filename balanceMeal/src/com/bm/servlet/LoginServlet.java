package com.bm.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bm.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("session_user")!=null){
			session.removeAttribute("session_user");
		}
		
		request.getRequestDispatcher("WEB-INF/assets/login/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String status = request.getParameter("status");
		// 判断数据是否为空
		if(account.trim().length()==0){
			request.setAttribute("message", "账号不能为空！");
			request.getRequestDispatcher("WEB-INF/assets/login/login.jsp").forward(request, response);
			return;
		}
		if(password.trim().length()==0){
			request.setAttribute("message", "密码不能为空！");
			request.setAttribute("account", account);
			request.getRequestDispatcher("WEB-INF/assets/login/login.jsp").forward(request, response);
			return;
		}
		
		// 与数据库进行连接 
		LoginService loginService = new LoginService();
		
		try {
			Map<String, Object> map = loginService.login(account, password,status);
			// 存入session
			HttpSession session = request.getSession();
			session.setAttribute("session_user", map);
			response.sendRedirect("MainServlet.do");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("message",e.getMessage());
			request.setAttribute("account",account);
			request.getRequestDispatcher("WEB-INF/assets/login/login.jsp").forward(request, response);
			return;
		}
		
		
		System.out.println("ok----post");
	}
}
	