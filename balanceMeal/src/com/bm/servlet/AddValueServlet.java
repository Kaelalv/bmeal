package com.bm.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.service.ValueService;
import com.bm.util.UploadUtil;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/AddValueServlet.do")
public class AddValueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/assets/value/addValue.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ------ 调用UploadUtil 工具类
		Map<String, Object> map = UploadUtil.upload(request, "value_upload");
		System.out.println(map);
		request.getSession().setAttribute("map", map);
		
		ValueService valueService = new ValueService();
		
		try {
			valueService.valueAdd(map); // 这里可能会出现异常，所以需要自己输入抛出异常
			response.sendRedirect("ValueListServlet.do");
			
		} catch (Exception e) {
			map.put("message", e.getMessage());
			request.setAttribute("map", map);
			request.getRequestDispatcher("WEB-INF/assets/value/addValue.jsp").forward(request, response);
			return;
		}
				
	}

}
