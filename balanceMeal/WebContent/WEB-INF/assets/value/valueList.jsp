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
			<form class="everday_list_search" action="ValueListServlet.do" method="post">
				<label>关键词：</label>
				<input type="text" name="foodname" value="${queryEverydayMeal.foodname }" />
				<label>发布时间：</label>
				<input type="date" name="valuepublish" value="${queryEverydayMeal.valuepublish }" />
				<input type="submit" class="e_l_search" value="查询" />
			</form>
			
			<div class="e_l_table">
				<ul class="e_l_table_tit">
					<li class="width1">编号</li>
					<li style="width:15%;">标题</li>
					<li style="width:30%;">食物价值</li>
					<li style="width:20%;">图片</li>
					<li>发布时间</li>
					<li style="width:15%;">操作</li>
				</ul>
				<ul class="e_l_table_cont">	
					<c:if test="${!empty pageBean.data }">
					<!-- 这个属性是用来计数的，varStatus="vs" -->
						<c:forEach items="${pageBean.data }" var="everydayMeal" varStatus="vs">
						<li class="bg1">
							<ul class="e_l_table_list">
								<li class="width1">${vs.count }</li>
								<li style="width:15%;">${everydayMeal.foodname }</li>
								<li style="width:30%;">${everydayMeal.foodvalue }</li>
								<li style="width:20%;" title="${everydayMeal.foodimg }">${everydayMeal.foodimg }</li>
								<li>${everydayMeal.valuepublish }</li>
								<li style="width:15%;">
									<a href="ValueUpdateServlet.do?id=${everydayMeal.value_id }">编辑&nbsp;&nbsp;|</a>	
									<a href="ValueDeleteServlet.do?id=${everydayMeal.value_id }">&nbsp;删除</a>	
								</li>
							</ul>
						</li>
						</c:forEach>
					</c:if>
					<c:if test="${empty pageBean.data }">
						<li>
							<ul>
								<li>没有数据</li>
							</ul>
						</li>
					</c:if>
				</ul>
				<div class="page">
					<a href="ValueListServlet.do?pageNow=1">首页</a>
					<a href="ValueListServlet.do?pageNow=${pageBean.pageNow-1>0 ? pageBean.pageNow-1:1 }">上一页</a>
					<a href="ValueListServlet.do?pageNow=${pageBean.pageNow+1<=pageBean.totalPage ? pageBean.pageNow+1:pageBean.totalPage }">下一页</a>
					<a href="ValueListServlet.do?pageNow=${pageBean.totalPage }">尾页</a>
					当前第&nbsp;${pageBean.pageNow }&nbsp;页&nbsp;&nbsp;|&nbsp;&nbsp;共&nbsp;${pageBean.totalCount }&nbsp;条记录&nbsp;&nbsp;|&nbsp;&nbsp;共&nbsp;${pageBean.totalPage }&nbsp;页 
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>