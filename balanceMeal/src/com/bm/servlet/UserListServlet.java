package com.bm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.model.Meal;
import com.bm.model.PageBean;
import com.bm.model.User;
import com.bm.service.MealService;
import com.bm.service.UserService;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/UserListServlet.do")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getRequestDispatcher("WEB-INF/assets/user/userList.jsp").forward(request, response);
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 三种情况   1.第一次通过导航访问，2.查询，3.分页
		// 1. 接受查询数据 可能没有
		String account = request.getParameter("account");
		//System.out.println(mealtime);
		String sex = request.getParameter("sex");
		String name = request.getParameter("name");
		// 2. 创建实体bean的对象；
		User user = new User();
		
		if(sex!=null && sex.trim().length() > 0){
			user.setSex(sex);
		}
		user.setAccount(account);
		
		
		// 3. 创建分页的实体对象
		PageBean pageBean = new PageBean();
		String pageNow = request.getParameter("pageNow");
		if(pageNow != null){
			pageBean.setPageNow(Integer.parseInt(pageNow));
			
		}
		
		/* -------- 然后跳到EverydayMealService，执行   4. 分页查询用户列表 ---*/
		
		
		// 12. 调用service
		UserService userService = new UserService();
		request.setAttribute("pageBean", userService.getUserList(user, pageBean));
		// 传回查询数据
		request.setAttribute("queryEverydayMeal", user);
		
		request.getRequestDispatcher("WEB-INF/assets/user/userList.jsp").forward(request, response);
		
	
	}

}
