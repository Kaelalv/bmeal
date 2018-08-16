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
			<form action="AddMealServlet.do" method="post"  enctype="multipart/form-data">
				<div class="select_meal_time">
					<label>餐点时间：</label>
					<select name="mealtime" style="margin-right:5%;">
						<option>早餐</option>
						<option>午餐</option>
						<option>下午茶</option>
						<option>晚餐</option>
						<option>宵夜</option>
					</select>
					<label>人群</label>
					<select name="mealpeople" style="margin-right:4%;">
						<option>全部</option>
						<option>儿童</option>
						<option>青年</option>
						<option>成年</option>
						<option>老年</option>
					</select>
					<label>发布时间：</label>
					<input type="date" name="mealpublish" style=" margin-right:5%;" />
					<label>点赞：</label>
					<input type="text" name="mealzan" style="width:8%;" />
				</div>
				<div class="select_meal_time">
					<label>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</label>
					<input class="abstract"  type="text" name="mealtit" />
				</div>
				<div class="select_meal_time">
					<label>摘&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;要：</label>
					<input class="abstract" type="text" name="mealintro" />
				</div>
				<div class="select_meal_time">
					<label>材&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;料：</label>
					<textarea name="mealmain"></textarea>
				</div>
				<div class="select_meal_time">
					<label>步&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;骤：</label>
					<textarea name="mealcond"></textarea>
				</div>
				
				<div class="select_meal_time">
					<label>图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片：</label>
					<input type="file" name="mealimg"  />
					<!-- <a class="file">选择文件
					    <input type="file" name="mealimg"  />
					</a> -->
				</div>
				<div class="select_meal_time">
				    <input type="submit"  class="btn" value="确认添加" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>