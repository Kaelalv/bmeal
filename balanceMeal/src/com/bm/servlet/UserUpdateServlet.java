package com.bm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.model.Meal;
import com.bm.model.User;
import com.bm.service.MealService;
import com.bm.service.UserService;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/UserUpdateServlet.do")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		// 根据id获取当前用户的详细信息 
		UserService ems = new UserService();
		request.setAttribute("user",ems.getUserById(Integer.parseInt(id)));
		request.getRequestDispatcher("WEB-INF/assets/user/userUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String sex = request.getParameter("sex");
		String account = request.getParameter("account");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		//String password = request.getParameter("password");
		
		User user = new User();
		
		//System.out.println(age);
		user.setId(Integer.parseInt(id));
		user.setSex(sex);
		user.setAccount(account);
		user.setNickname(nickname);
		user.setPassword(password);
		user.setPhone(phone);
		//everydayMeal.setMealimg(mealimg);
		
		if(sex.trim().length()==0){
			request.setAttribute("message", "姓名不能为空");
			request.setAttribute("user", sex);
			request.getRequestDispatcher("WEB-INF/assets/user/userUpdate.jsp").forward(request, response);
			return;
		}
		if(account.trim().length()==0){
			request.setAttribute("message", "姓名不能为空");
			request.setAttribute("user", account);
			request.getRequestDispatcher("WEB-INF/assets/user/userUpdate.jsp").forward(request, response);
			return;
		}
		if(nickname.trim().length()==0){
			request.setAttribute("message", "密码不能为空");
			request.setAttribute("user", nickname);
			request.getRequestDispatcher("WEB-INF/assets/user/userUpdate.jsp").forward(request, response);
			return;
		}
		if(password.trim().length()==0){
			request.setAttribute("message", "年龄不能为空");
			request.setAttribute("user", password);
			request.getRequestDispatcher("WEB-INF/assets/user/userUpdate.jsp").forward(request, response);
			return;
		}
		if(phone.trim().length()==0){
			request.setAttribute("message", "年龄不能为空");
			request.setAttribute("user", phone);
			request.getRequestDispatcher("WEB-INF/assets/user/userUpdate.jsp").forward(request, response);
			return;
		}

		UserService userService = new UserService();
		try {
			userService.updateUserById(user);
			response.sendRedirect("UserListServlet.do");
			
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("meal", user);
			request.getRequestDispatcher("WEB-INF/assets/user/userUpdate.jsp").forward(request, response);
			return;
		}
	}

}
