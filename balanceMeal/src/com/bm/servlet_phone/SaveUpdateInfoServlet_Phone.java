package com.bm.servlet_phone;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.model.User;
import com.bm.util.DBUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class SaveUpdateInfoServlet_Phone
 */
@WebServlet("/SaveUpdateInfoServlet_Phone.do")
public class SaveUpdateInfoServlet_Phone extends HttpServlet {
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
		String id = request.getParameter("user_id");
		String nickname = request.getParameter("nickname");
		String phone = request.getParameter("phone");
		String userbirth = request.getParameter("userbirth");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		

		User user = new User();
		
		//System.out.println(age);
		user.setId(Integer.parseInt(id));
		user.setSex(sex);
		user.setNickname(nickname);
		user.setPassword(password);
		user.setPhone(phone);
		user.setUserbirth(userbirth);
		
		
		String sql = "update user set nickname = ? , password = ? , sex = ? , userbirth = ? , phone = ?  where id = ?";

		int num = DBUtil.executeUpdate(sql,new Object[]{
						
				user.getNickname(),
				user.getPassword(),
				user.getUserbirth(),
				user.getSex(),
				user.getPhone(),
				user.getId()
		});
		if(num <0){
			throw new RuntimeException("更新失败");
		}

		// 给移动端返回数据，和ajax返回数据一样
		response.setHeader("Content-type", "text/json;charset=utf-8");
		
		/* --------- 这两句是将json对象整齐地输出  */
		GsonBuilder gsonbuilder = new GsonBuilder().setPrettyPrinting().serializeNulls();
	    Gson gson = gsonbuilder.create();
	    /* 这两句是将json对象整齐地输出 ------- */
	    String js = gson.toJson(num);
		// out对象
		PrintWriter out = response.getWriter();
		
		if(num>0){
			out.print(2);
			//out.print(map);
			System.out.println("注册成功");
			System.out.println(num);
			System.out.println(js);
		}else{
			out.print(1);
			System.out.println("账号或密码不匹配");
			
		}
		out.flush();
		out.close();
	
	}

}
