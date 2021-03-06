package com.bm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.model.News;
import com.bm.model.PageBean;
import com.bm.service.NewsService;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/NewsListServlet.do")
public class NewsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getRequestDispatcher("WEB-INF/assets/news/newsList.jsp").forward(request, response);
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 三种情况   1.第一次通过导航访问，2.查询，3.分页
		// 1. 接受查询数据 可能没有
		String newstit = request.getParameter("newstit");
		String newspublish = request.getParameter("newspublish");
		
		// 2. 创建实体bean的对象；
		News news = new News();
		
		if(newspublish!=null && newspublish.trim().length() > 0){
			news.setNewspublish(newspublish);
		}
		news.setNewstit(newstit);
		
		
		// 3. 创建分页的实体对象
		PageBean pageBean = new PageBean();
		String pageNow = request.getParameter("pageNow");
		if(pageNow != null){
			pageBean.setPageNow(Integer.parseInt(pageNow));
			
		}
		
		/* -------- 然后跳到EverydayMealService，执行   4. 分页查询用户列表 ---*/
		
		
		// 12. 调用service
		NewsService newsService = new NewsService();
		request.setAttribute("pageBean", newsService.getUserList(news, pageBean));
		// 传回查询数据
		request.setAttribute("queryEverydayMeal", news);
		
		request.getRequestDispatcher("WEB-INF/assets/news/newsList.jsp").forward(request, response);
		
	}

}
