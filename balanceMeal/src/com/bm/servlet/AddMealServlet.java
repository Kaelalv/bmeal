package com.bm.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bm.service.EverydayMealService;
import com.bm.service.MealService;
import com.bm.util.UploadUtil;

/**
 *  写完登录页面后，就写添加页面
 * 
 * 
 * */


/**
 * Servlet implementation class AddEverydayServlet
 */
@WebServlet("/AddMealServlet.do")
public class AddMealServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/assets/meal/addMeal.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ------ 调用UploadUtil 工具类
		Map<String, Object> map = UploadUtil.upload(request, "meal_upload");
		System.out.println(map);
		request.getSession().setAttribute("map", map);
		
		MealService mealService = new MealService();
		
		try {
			mealService.mealAdd(map); // 这里可能会出现异常，所以需要自己输入抛出异常
			response.sendRedirect("MealListServlet.do");
			
		} catch (Exception e) {
			map.put("message", e.getMessage());
			request.setAttribute("map", map);
			request.getRequestDispatcher("WEB-INF/assets/meal/addMeal.jsp").forward(request, response);
			return;
		}
				
	}
}
