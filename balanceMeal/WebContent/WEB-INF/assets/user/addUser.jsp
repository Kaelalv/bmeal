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
			<form action="AddUserServlet.do" method="post"  enctype="multipart/form-data">
				<input type="hidden" name="status" value="1" />
				<div class="select_meal_time">
					<label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
					<select name="sex" style="margin-right:8%;">
						<option>男</option>
						<option>女</option>
					</select>
					
				</div>
				<div class="select_meal_time">
					<label>出生日期：</label>
					<input type="date" name="userbirth" style="margin-right:8%;" />
				</div>
				<div class="select_meal_time">
					<label>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
					<input class="abstract"  type="text" name="account" />
				</div>
				<div class="select_meal_time">
					<label>昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label>
					<input class="abstract" type="text" name="nickname" />
				</div>
				<div class="select_meal_time">
					<label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
					<input class="abstract" type="text" name="password" />
				</div>
				<div class="select_meal_time">
					<label>手机号码：</label>
					<input class="abstract" type="text" name="phone" />
				</div>
				
				<div class="select_meal_time">
					<label>图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片：</label>
					<input type="file" name="userimg"  />
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