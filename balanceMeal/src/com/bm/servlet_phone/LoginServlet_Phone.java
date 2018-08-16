package com.bm.servlet_phone;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bm.service.LoginService;
import com.bm.util.DBUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Servlet implementation class PhoneLoginServlet
 */
@WebServlet("/LoginServlet_Phone.do")
public class LoginServlet_Phone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String js = "";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.getRequestDispatcher("WEB-INF/assets/everyday/everydayList.jsp").forward(request, response);
		
		doPost(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String status = request.getParameter("status");
		
		String sql = "select * from user where account=? and password=? and status=?";
		Map<String, Object> map =DBUtil.query(sql, new Object[]{account.trim(),password.trim(),status.trim()});
		System.out.println(account+"========"+password);
		
		// 给移动端返回数据，和ajax返回数据一样
		response.setHeader("Content-type", "text/json;charset=utf-8");
		
		/* --------- 这两句是将json对象整齐地输出  */
		GsonBuilder gsonbuilder = new GsonBuilder().setPrettyPrinting().serializeNulls();
	    Gson gson = gsonbuilder.create();
	    Map<String, Object > ok = new HashMap<String, Object >();
	    Map<String, Object > err = new HashMap<String, Object >();
	    ok.put("code", 0);
	    ok.put("msg","登录成功");
	    ok.put("data",map);
	    err.put("code", 101);
	    err.put("msg","账号或密码不正确");
	    err.put("data","");
	    /* 这两句是将json对象整齐地输出 ------- */
		
		// out对象
		PrintWriter out = response.getWriter();
		PrintWriter out1 = response.getWriter();
		if(map!=null&&map.size()>0){
			
			js = gson.toJson(ok);
			out.print(js);
			System.out.println("登录成功");
			System.out.println(map);
			System.out.println(js);
		}else{
			js = gson.toJson(err);
			System.out.println("账号或密码不匹配");
			
		}
		out.flush();
		out.close();		
	}
}
