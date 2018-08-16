package com.bm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.service.MealService;
import com.bm.service.NewsService;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/NewsDeleteServlet.do")
public class NewsDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		// 1. 通过用户id查询用户详细信息并显示到页面
		NewsService newsService = new NewsService();
		request.setAttribute("news",newsService.getNewsById(Integer.parseInt(id)));
		request.getRequestDispatcher("WEB-INF/assets/news/newsDelete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		// 2. 根据id删除 --- 跳到service 3
		NewsService ms = new NewsService();
		ms.deleteNewsById(Integer.parseInt(id));
		// 获取out对象 ， 在当前Servlet上写标签
		response.setContentType(" text/html;charset=UTF-8 ");
		PrintWriter out = response.getWriter();
		out.write("<script>alert('删除成功！');window.location.href='NewsListServlet.do';</script>");
		out.flush();
		out.close();
		//response.sendRedirect("EverydayListServlet.do");
	}

}
