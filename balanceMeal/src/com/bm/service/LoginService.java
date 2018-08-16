package com.bm.service;

import java.util.Map;

import com.bm.util.DBUtil;

public class LoginService {
	/** 验证登录 
	 * @throws Exception */
	public Map<String,Object> login(String account,String password,String status) throws Exception{
		String sql = "select * from user where account =? and password=? and status=?";
		Map<String,Object> map = DBUtil.query(sql,new Object[]{account.trim(),password.trim(),status.trim()});
		if(map!=null&&map.size()>0){
			return map;
		}else{
			throw new Exception("账号密码不匹配！");
		}
	}
	
}
