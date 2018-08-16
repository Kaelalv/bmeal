package com.bm.servlet_phone;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.util.DBUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class NewsDetailServlet_Phone
 */
@WebServlet("/ValueDetailServlet_Phone.do")
public class ValueDetailServlet_Phone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String valueId = request.getParameter("valueId");
		
		String sql = "select * from value where value_id=?";
		Map<String,Object> map = DBUtil.query(sql,new Object[]{valueId.trim()});
		System.out.println(map);
		
		// 给移动端返回数据，和ajax返回数据一样
		response.setHeader("Content-type", "text/json;charset=utf-8");
		
		/* --------- 这两句是将json对象整齐地输出  */
		GsonBuilder gsonbuilder = new GsonBuilder().setPrettyPrinting().serializeNulls();
		Gson gson = gsonbuilder.create();
		/* 这两句是将json对象整齐地输出 ------- */
	    String js = gson.toJson(map);
		// out对象
		PrintWriter out = response.getWriter();
		if(map!=null&&map.size()>0){
			out.print(js);
			System.out.println("新闻详情页面信息获取成功");
			System.out.println(map);
			System.out.println(js);
		}else{
			System.out.println("新闻没有信息");

		}
		out.flush();
		out.close();
	
	}

}
