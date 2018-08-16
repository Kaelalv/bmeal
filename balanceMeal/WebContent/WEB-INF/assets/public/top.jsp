<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resource/css/main.css" />
<link rel="stylesheet" type="text/css" href="./resource/plugin/font-awesome/css/font-awesome.min.css" />
<script type="text/javascript" src="./resource/plugin/jquery.js"></script>

</head>
<body>
	<header id="top">
		<div class="top_right">
			<a class="logout" href="LoginServlet.do" >退出</a>
			<p class="last_visit">上次访问时间：2017-12-28</p>
		</div>
		<div class="top_left">
			<h2 class="manager_name">${session_user.account}</h2>
		</div>
	</header>
	
</body>
</html>