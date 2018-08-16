package com.bm.servlet_phone;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
 * Servlet implementation class MealServlet_Phone
 */
@WebServlet("/MealServlet_Phone.do")
public class MealServlet_Phone extends HttpServlet {
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
		String mealPeople = request.getParameter("mealPeople");
		
		String sqlEveryday = "select * from meal where mealpeople=?";
		List<Map<String,Object>> list = DBUtil.list(sqlEveryday,new Object[]{mealPeople.trim()});
		
		System.out.println(list+"========Meal ===================");
		
		
		// 给移动端返回数据，和ajax返回数据一样
		response.setHeader("Content-type", "text/json;charset=utf-8");
		
		/* --------- 这两句是将json对象整齐地输出  */
		GsonBuilder gsonbuilder = new GsonBuilder().setPrettyPrinting().serializeNulls();
		Gson gson = gsonbuilder.create();
		/* 这两句是将json对象整齐地输出 ------- */
	    String js = gson.toJson(list);
		// out对象
		PrintWriter out = response.getWriter();
		if(list!=null&&list.size()>0){
			out.print(js);
			System.out.println("---------Meal ----------信息获取成功");
			System.out.println(list);
			System.out.println(js);
		}else{
			System.out.println("---------Meal ----------没有信息");

		}
		out.flush();
		out.close();
	}

}
