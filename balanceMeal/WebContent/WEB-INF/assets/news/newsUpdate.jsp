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
<script type="text/javascript">
	$(function(){
		var height = $("#content").height();
		$(".nav").css("height",height+"px");
		
		$(".file").on("change","input[type='file']",function(){
		    var filePath=$(this).val();
		  	$(".file").html(filePath);
		  	alert(filePath);
		})
	});
</script>
</head>
<body>
	<%@include file="../public/top.jsp" %>
	<%@include file="../public/nav.jsp" %>
	<div id="content">
		<div class="addEveryday">
			<h1>今日关注——编辑</h1>
			<form action="NewsUpdateServlet.do" method="post" >
				<input type="hidden" name="id" value="${news.id }" />
				<div class="select_meal_time">
					<label>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</label>
					<input type="text" name="newstit" value="${news.newstit }" />
				</div>
				<div class="select_meal_time">
					<label>摘&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;要：</label>
					<input class="abstract" type="text" name="newsintro" value="${news.newsintro }" />
				</div>
				<div class="select_meal_time">
					<label>详细内容：</label>
					<textarea name="newsdetail">${news.newsdetail }</textarea>
				</div>
				
				<%-- <div class="select_meal_time">
					<label>图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片：</label>
					<input type="file" name="mealimg" value="./resource/${everydayMeal.mealimg }" />
					<!-- <a class="file">选择文件
					    <input type="file" name="mealimg"  />
					</a> -->
				</div> --%>
				<div class="select_meal_time">
				    <input type="submit"  class="btn" value="更新" />
				</div>
			</form>
			<span style="color:red;"></span>
		</div>
	</div>
</body>
</html>