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

import com.bm.util.DBUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class PhoneRegisterServlet
 */
@WebServlet("/RegisterServlet_Phone.do")
public class RegisterServlet_Phone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   


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
		
		System.out.println("1111111");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String status = request.getParameter("status");
		String nickname = "未编辑";
		String sex = "未编辑";
		String phone = "未编辑";
		String userimg = "未编辑";
		String userbirth = "未编辑";
	
		
		// 接口回调
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("account", account);
		map.put("password", password);
		map.put("status", status);
		map.put("nickname", nickname);
		map.put("sex", sex);
		map.put("phone", phone);
		map.put("userimg", userimg);
		map.put("userbirth", userbirth);
		
		String sqlTest = "select count(*) as num from user where account = ?";
		int num = Integer.parseInt(DBUtil.query(sqlTest,account).get("num").toString());
		
		if(num==0){
			String sql = "insert into user (account,nickname,password,sex,userbirth,phone,userimg,status) values (?,?,?,?,?,?,?,?)";
			DBUtil.executeUpdate(sql, new Object[]{
					map.get("account"),
					map.get("nickname"),
					map.get("password"),
					map.get("sex"),
					map.get("userbirth"),
					map.get("phone"),
					map.get("userimg"),
					map.get("status")
			});
		}else{
			System.out.println("已存在");
		}
		
		
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
			out.print(2);
			//out.print(map);
			System.out.println("注册成功");
			System.out.println(map);
			System.out.println(js);
		}else{
			out.print(1);
			System.out.println("账号或密码不匹配");
			
		}
		out.flush();
		out.close();
	}

}
