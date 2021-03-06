package com.bm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter{
	private String encoding = "UTF-8";
	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		if(config.getInitParameter("encoding")!=null){
			encoding = config.getInitParameter("encoding");
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		
		String method = request.getMethod();
		if(method.equalsIgnoreCase("POST")){
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(req, resp);
	}


	@Override
	public void destroy() {
		
	}
}
