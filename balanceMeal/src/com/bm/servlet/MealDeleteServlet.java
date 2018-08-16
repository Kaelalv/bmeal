package com.bm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.service.EverydayMealService;
import com.bm.service.MealService;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MealDeleteServlet.do")
public class MealDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		// 1. 通过用户id查询用户详细信息并显示到页面
		MealService mealService = new MealService();
		request.setAttribute("meal",mealService.getMealById(Integer.parseInt(id)));
		
		request.getRequestDispatcher("WEB-INF/assets/meal/mealDelete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		// 2. 根据id删除 --- 跳到service 3
		MealService ms = new MealService();
		ms.deleteMealById(Integer.parseInt(id));
		// 获取out对象 ， 在当前Servlet上写标签
		response.setContentType(" text/html;charset=UTF-8 ");
		PrintWriter out = response.getWriter();
		out.write("<script>alert('删除成功！');window.location.href='MealListServlet.do';</script>");
		out.flush();
		out.close();
		//response.sendRedirect("EverydayListServlet.do");
		
	}

}
