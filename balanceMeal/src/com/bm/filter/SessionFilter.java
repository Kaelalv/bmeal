package com.bm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//request,response,session
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpSession session = request.getSession();
		// 验证是否是登录界面----获取访问路径
		String path = request.getServletPath();
		if(path.endsWith("LoginServlet.do")||path.endsWith("Servlet_Phone.do")){
			chain.doFilter(req, resp);
		}else{
			if(session.getAttribute("session_user")!=null){
				chain.doFilter(req, resp);
			}else{
				response.sendRedirect("LoginServlet.do");
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}
	
}
