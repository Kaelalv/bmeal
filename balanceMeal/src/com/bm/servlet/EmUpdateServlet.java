package com.bm.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.model.EverydayMeal;
import com.bm.service.EverydayMealService;
import com.bm.util.UploadUtil;

/**
 * Servlet implementation class EmUpdateServlet
 */
@WebServlet("/EmUpdateServlet.do")
public class EmUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		// 根据id获取当前用户的详细信息 
		EverydayMealService ems = new EverydayMealService();
		request.setAttribute("everydayMeal",ems.getEverydayMealById(Integer.parseInt(id)));
		request.getRequestDispatcher("WEB-INF/assets/everyday/emUpdate.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String mealtime = request.getParameter("mealtime");
		String mealtit = request.getParameter("mealtit");
		String mealintro = request.getParameter("mealintro");
		String mainingredient = request.getParameter("mainingredient");
		String condiment = request.getParameter("condiment");
		//String password = request.getParameter("password");
		
		EverydayMeal everydayMeal = new EverydayMeal();
		
		//System.out.println(age);
		everydayMeal.setId(Integer.parseInt(id));
		everydayMeal.setMealtime(mealtime);
		everydayMeal.setMealtit(mealtit);
		everydayMeal.setMealintro(mealintro);
		everydayMeal.setMainingredient(mainingredient);
		everydayMeal.setCondiment(condiment);
		//everydayMeal.setMealimg(mealimg);
		
		if(mealtime.trim().length()==0){
			request.setAttribute("message", "姓名不能为空");
			request.setAttribute("everydayMeal", mealtime);
			request.getRequestDispatcher("WEB-INF/assets/everyday/emUpdate.jsp").forward(request, response);
			return;
		}
		if(mealtit.trim().length()==0){
			request.setAttribute("message", "姓名不能为空");
			request.setAttribute("everydayMeal", mealtit);
			request.getRequestDispatcher("WEB-INF/assets/everyday/emUpdate.jsp").forward(request, response);
			return;
		}
		if(mealintro.trim().length()==0){
			request.setAttribute("message", "密码不能为空");
			request.setAttribute("everydayMeal", mealintro);
			request.getRequestDispatcher("WEB-INF/assets/everyday/emUpdate.jsp").forward(request, response);
			return;
		}
		if(mainingredient.trim().length()==0){
			request.setAttribute("message", "年龄不能为空");
			request.setAttribute("everydayMeal", mainingredient);
			request.getRequestDispatcher("WEB-INF/assets/everyday/emUpdate.jsp").forward(request, response);
			return;
		}
		if(condiment.trim().length()==0){
			request.setAttribute("message", "年龄不能为空");
			request.setAttribute("everydayMeal", condiment);
			request.getRequestDispatcher("WEB-INF/assets/everyday/emUpdate.jsp").forward(request, response);
			return;
		}

		EverydayMealService emService = new EverydayMealService();
		try {
			emService.updateEmById(everydayMeal);
			response.sendRedirect("EverydayListServlet.do");
			
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("everydayMeal", everydayMeal);
			request.getRequestDispatcher("WEB-INF/assets/everyday/emUpdate.jsp").forward(request, response);
			return;
		}
				
	}

}
