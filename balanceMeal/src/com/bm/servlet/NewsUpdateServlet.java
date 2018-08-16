package com.bm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.model.Meal;
import com.bm.model.News;
import com.bm.service.MealService;
import com.bm.service.NewsService;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/NewsUpdateServlet.do")
public class NewsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		// 根据id获取当前用户的详细信息 
		NewsService ems = new NewsService();
		request.setAttribute("news",ems.getNewsById(Integer.parseInt(id)));
		request.getRequestDispatcher("WEB-INF/assets/news/newsUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String newstit = request.getParameter("newstit");
		String newsintro = request.getParameter("newsintro");
		String newsdetail = request.getParameter("newsdetail");
		//String password = request.getParameter("password");
		System.out.println(id);
		News news = new News();
		
		//System.out.println(age);
		news.setId(Integer.parseInt(id));
		news.setNewstit(newstit);
		news.setNewsintro(newsintro);
		news.setNewsdetail(newsdetail);
		//everydayMeal.setMealimg(mealimg);
		
		if(newstit.trim().length()==0){
			request.setAttribute("message", "姓名不能为空");
			request.setAttribute("news", newstit);
			request.getRequestDispatcher("WEB-INF/assets/news/newsUpdate.jsp").forward(request, response);
			return;
		}
		if(newsintro.trim().length()==0){
			request.setAttribute("message", "密码不能为空");
			request.setAttribute("news", newsintro);
			request.getRequestDispatcher("WEB-INF/assets/news/newsUpdate.jsp").forward(request, response);
			return;
		}
		if(newsdetail.trim().length()==0){
			request.setAttribute("message", "年龄不能为空");
			request.setAttribute("news", newsdetail);
			request.getRequestDispatcher("WEB-INF/assets/news/newsUpdate.jsp").forward(request, response);
			return;
		}

		NewsService newsService = new NewsService();
		try {
			newsService.updateNewsById(news);
			response.sendRedirect("NewsListServlet.do");
			
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("news", news);
			request.getRequestDispatcher("WEB-INF/assets/news/newsUpdate.jsp").forward(request, response);
			return;
		}
	}

}
