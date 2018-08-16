package com.bm.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.service.MealService;
import com.bm.service.UserService;
import com.bm.util.UploadUtil;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/AddUserServlet.do")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/assets/user/addUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ------ 调用UploadUtil 工具类
		Map<String, Object> map = UploadUtil.upload(request, "user_upload");
		System.out.println(map);
		request.getSession().setAttribute("map", map);
		
		UserService userService = new UserService();
		
		try {
			userService.userAdd(map); // 这里可能会出现异常，所以需要自己输入抛出异常
			response.sendRedirect("UserListServlet.do");
			
		} catch (Exception e) {
			map.put("message", e.getMessage());
			request.setAttribute("map", map);
			request.getRequestDispatcher("WEB-INF/assets/user/addUser.jsp").forward(request, response);
			return;
		}
	}

}
