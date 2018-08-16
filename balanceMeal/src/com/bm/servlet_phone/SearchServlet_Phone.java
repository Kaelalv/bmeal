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

import org.apache.commons.lang.StringEscapeUtils;

import com.bm.util.DBUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class SearchServlet_Phone
 */
@WebServlet("/SearchServlet_Phone.do")
public class SearchServlet_Phone extends HttpServlet {
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

		String search = request.getParameter("search");
		System.out.println(search+"========");
		StringBuilder sb = null;
		if(search.endsWith("价值")){
		
			// 2. 拼接查询语句  -- 第一部分
			sb = new StringBuilder("select * from value where 1=1 ");
			// 2. 拼接查询条件  -- 第二部分
			if(search!=null && search.trim().length()>0){
				sb.append(" and foodname like '%"+ StringEscapeUtils.escapeSql(search) +"%'");
				System.out.println(search+"========1111");
			}
		}else{
			// 2. 拼接查询语句  -- 第一部分
			sb = new StringBuilder("select * from meal where 1=1 ");
			// 2. 拼接查询条件  -- 第二部分
			if(search!=null && search.trim().length()>0){
				sb.append(" and ( mealtime = '"+ search +"' or mealtit like '%" + StringEscapeUtils.escapeSql(search) +"%')");
				System.out.println(search+"========1111");
			}
			/*else if(search!=null && search.trim().length()>0){
				//sb.append(" or mealtit like '%"+ StringEscapeUtils.escapeSql(search) +"%')");
				System.out.println(search+"========1111");
			}*/
		}
		
		String sql = sb.toString();
		System.out.println(sql+"========");
		// 3. 执行查询语句
		List<Map<String,Object>> list = DBUtil.list(sql);

		System.out.println(list+"========");
		
		
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
			System.out.println("搜索信息获取成功");
			System.out.println(list);
			System.out.println(js);
		}else{
			System.out.println("搜索没有信息");

		}
		out.flush();
		out.close();
	}

}
