<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resource/css/main.css" />
</head>
<body>
	<div class="login">
		<div class="login_bg">
			<form class="login_box" action="LoginServlet.do" method="post">
				<p>均衡餐</p>
				<div class="login_input">
					<input type="hidden" name="status" value="0" />
					<input type="text" name="account" placeholder="请输入用户名" value="${account }" />
					<input type="password" name="password" placeholder="请输入用密码" value="" />
					<input type="submit" class="login_btn" value="登录" />
					<span class="tips">${message }</span>
				</div>
				
			</form>
		</div>
	</div>
</body>
</html>