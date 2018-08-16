package com.bm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.model.EverydayMeal;
import com.bm.model.Meal;
import com.bm.service.EverydayMealService;
import com.bm.service.MealService;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MealUpdateServlet.do")
public class MealUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		// 根据id获取当前用户的详细信息 
		MealService ems = new MealService();
		request.setAttribute("meal",ems.getMealById(Integer.parseInt(id)));
		request.getRequestDispatcher("WEB-INF/assets/meal/mealUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String mealtime = request.getParameter("mealtime");
		String mealtit = request.getParameter("mealtit");
		String mealintro = request.getParameter("mealintro");
		String mealmain = request.getParameter("mealmain");
		String mealcond = request.getParameter("mealcond");
		//String password = request.getParameter("password");
		
		Meal meal = new Meal();
		
		//System.out.println(age);
		meal.setId(Integer.parseInt(id));
		meal.setMealtime(mealtime);
		meal.setMealtit(mealtit);
		meal.setMealintro(mealintro);
		meal.setMealmain(mealmain);
		meal.setMealcond(mealcond);
		//everydayMeal.setMealimg(mealimg);
		
		if(mealtime.trim().length()==0){
			request.setAttribute("message", "姓名不能为空");
			request.setAttribute("meal", mealtime);
			request.getRequestDispatcher("WEB-INF/assets/meal/mealUpdate.jsp").forward(request, response);
			return;
		}
		if(mealtit.trim().length()==0){
			request.setAttribute("message", "姓名不能为空");
			request.setAttribute("meal", mealtit);
			request.getRequestDispatcher("WEB-INF/assets/meal/mealUpdate.jsp").forward(request, response);
			return;
		}
		if(mealintro.trim().length()==0){
			request.setAttribute("message", "密码不能为空");
			request.setAttribute("meal", mealintro);
			request.getRequestDispatcher("WEB-INF/assets/meal/mealUpdate.jsp").forward(request, response);
			return;
		}
		if(mealmain.trim().length()==0){
			request.setAttribute("message", "年龄不能为空");
			request.setAttribute("meal", mealmain);
			request.getRequestDispatcher("WEB-INF/assets/meal/mealUpdate.jsp").forward(request, response);
			return;
		}
		if(mealcond.trim().length()==0){
			request.setAttribute("message", "年龄不能为空");
			request.setAttribute("meal", mealcond);
			request.getRequestDispatcher("WEB-INF/assets/meal/mealUpdate.jsp").forward(request, response);
			return;
		}

		MealService mealService = new MealService();
		try {
			mealService.updateMealById(meal);
			response.sendRedirect("MealListServlet.do");
			
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("meal", meal);
			request.getRequestDispatcher("WEB-INF/assets/meal/mealUpdate.jsp").forward(request, response);
			return;
		}
				
	}

}
