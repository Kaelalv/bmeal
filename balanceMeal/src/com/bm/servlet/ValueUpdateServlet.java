package com.bm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.model.Meal;
import com.bm.model.Value;
import com.bm.service.MealService;
import com.bm.service.ValueService;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/ValueUpdateServlet.do")
public class ValueUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.print(id+"=============");
		// 根据id获取当前用户的详细信息 
		ValueService ems = new ValueService();
		request.setAttribute("value",ems.getValueById(Integer.parseInt(id)));
		request.getRequestDispatcher("WEB-INF/assets/value/valueUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.print(id);
		String foodname = request.getParameter("foodname");
		String foodvalue = request.getParameter("foodvalue");
		//String password = request.getParameter("password");
		
		Value value = new Value();
		
		//System.out.println(age);
		value.setValue_id(Integer.parseInt(id));
		value.setFoodname(foodname);
		value.setFoodvalue(foodvalue);
		//everydayMeal.setMealimg(mealimg);
		
		if(foodname.trim().length()==0){
			request.setAttribute("message", "姓名不能为空");
			request.setAttribute("value", foodname);
			request.getRequestDispatcher("WEB-INF/assets/value/valueUpdate.jsp").forward(request, response);
			return;
		}
		
		if(foodvalue.trim().length()==0){
			request.setAttribute("message", "年龄不能为空");
			request.setAttribute("value", foodvalue);
			request.getRequestDispatcher("WEB-INF/assets/meal/mealUpdate.jsp").forward(request, response);
			return;
		}

		ValueService valueService = new ValueService();
		try {
			valueService.updateValueById(value);
			response.sendRedirect("ValueListServlet.do");
			
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("value", value);
			request.getRequestDispatcher("WEB-INF/assets/value/valueUpdate.jsp").forward(request, response);
			return;
		}
				
	
	}

}
