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
			<h1>每日一餐——编辑</h1>
			<form action="EmUpdateServlet.do" method="post" >
				<input type="hidden" name="id" value="${everydayMeal.id }" />
				<div class="select_meal_time">
					<label>餐点时间：</label>
					<select name="mealtime">
						<option value="早餐" 
							<c:if test="${everydayMeal.mealtime=='早餐' }">
							selected
							</c:if>
						>早餐</option>
						<option value="午餐" 
							<c:if test="${everydayMeal.mealtime=='午餐' }">
							selected
							</c:if>
						>午餐</option>
						<option value="下午茶" 
							<c:if test="${everydayMeal.mealtime=='下午茶' }">
							selected
							</c:if>
						>下午茶</option>
						<option value="晚餐" 
							<c:if test="${everydayMeal.mealtime=='晚餐' }">
							selected
							</c:if>
						>晚餐</option>
						<option value="宵夜" 
							<c:if test="${everydayMeal.mealtime=='宵夜' }">
							selected
							</c:if>
						>宵夜</option>
					</select>
				</div>
				<div class="select_meal_time">
					<label>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</label>
					<input type="text" name="mealtit" value="${everydayMeal.mealtit }" />
				</div>
				<div class="select_meal_time">
					<label>摘&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;要：</label>
					<input class="abstract" type="text" name="mealintro" value="${everydayMeal.mealintro }" />
				</div>
				<div class="select_meal_time">
					<label>材&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;料：</label>
					<textarea name="mainingredient">${everydayMeal.mainingredient }</textarea>
				</div>
				<div class="select_meal_time">
					<label>步&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;骤：</label>
					<textarea name="condiment">${everydayMeal.condiment }</textarea>
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