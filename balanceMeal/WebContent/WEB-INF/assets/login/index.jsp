<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
			<!-- <p class="last_visit">上次访问时间：2017-12-28</p> -->
		</div>
		<div class="top_left">
			<h2 class="manager_name">${session_user.account}</h2>
		</div>
	</header>
	<nav class="nav height527">
		<div class="manager_img">
			<img src="./resource/${session_user.userimg}">
		</div>
		<ul class="nav_list">
			<li class="list_one list_personal active"><a href="#">每日推荐</a>
				<ul class="list_personal_list">
					<li><a href="AddEverydayServlet.do">添加推荐</a></li>
					<li><a href="EverydayListServlet.do">推荐列表</a></li>
				</ul>
			</li>
			<li class="list_one list_personal"><a href="#">今日关注</a>
				<ul class="list_personal_list">
					<li><a href="AddNewsServlet.do">添加新闻</a></li>
					<li><a href="NewsListServlet.do">新闻列表</a></li>
				</ul>
			</li>
			<li class="list_one list_personal"><a href="#">均衡餐</a>
				<ul class="list_personal_list">
					<li><a href="AddMealServlet.do">添加均衡餐</a></li>
					<li><a href="MealListServlet.do">均衡餐列表</a></li>
				</ul>
			</li>
			<li class="list_one"><a href="#">食物价值</a>
				<ul class="list_personal_list">
					<li><a href="AddValueServlet.do">添加食物价值</a></li>
					<li><a href="ValueListServlet.do">食物价值列表</a></li>
				</ul>
			</li>
		<c:if test="${session_user.status==0 }">
			<li class="list_one list_personal"><a href="#">人员管理</a>
				<ul class="list_personal_list">
					<li><a href="AddUserServlet.do">添加管理员、用户</a></li>
					<li><a href="UserListServlet.do">人员列表</a></li>
				</ul>
			</li>
		</c:if>
		</ul>
	</nav>
	<div id="content">
		
	</div>
</body>
</html>