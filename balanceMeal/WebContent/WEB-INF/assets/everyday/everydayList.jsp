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
			<form class="everday_list_search" action="EverydayListServlet.do" method="post">
				<label>餐点时间：</label>
				<select class="e_l_s_option" name="mealtime">
					<option value="">全部</option>
					<option value="早餐" 
						<c:if test="${queryEverydayMeal.mealtime=='早餐' }">
						selected
						</c:if>
					>早餐</option>
					<option value="午餐" 
						<c:if test="${queryEverydayMeal.mealtime=='午餐' }">
						selected
						</c:if>
					>午餐</option>
					<option value="下午茶" 
						<c:if test="${queryEverydayMeal.mealtime=='下午茶' }">
						selected
						</c:if>
					>下午茶</option>
					<option value="晚餐" 
						<c:if test="${queryEverydayMeal.mealtime=='晚餐' }">
						selected
						</c:if>
					>晚餐</option>
					<option value="宵夜" 
						<c:if test="${queryEverydayMeal.mealtime=='宵夜' }">
						selected
						</c:if>
					>宵夜</option>
				</select>
				<label>关键词：</label>
				<input type="text" name="mealtit" value="${queryEverydayMeal.mealtit }" />
				<label>发布时间：</label>
				<input type="date" name="mealpublish" value="${queryEverydayMeal.mealtit }" />
				<input type="submit" class="e_l_search" value="查询" />
			</form>
			
			<div class="e_l_table">
				<ul class="e_l_table_tit">
					<li class="width1">编号</li>
					<li class="width1">餐点时间</li>
					<li>标题</li>
					<li>摘要</li>
					<li>材料</li>
					<li>步骤</li>
					<li>图片</li>
					<li>发布时间</li>
					<li class="width1">点赞</li>
					<li>操作</li>
				</ul>
				<ul class="e_l_table_cont">	
					<c:if test="${!empty pageBean.data }">
					<!-- 这个属性是用来计数的，varStatus="vs" -->
						<c:forEach items="${pageBean.data }" var="everydayMeal" varStatus="vs">
						<li class="bg1">
							<ul class="e_l_table_list">
								<li class="width1">${vs.count }</li>
								<li class="width1">${everydayMeal.mealtime }</li>
								<li>${everydayMeal.mealtit }</li>
								<li>${everydayMeal.mealintro }</li>
								<li>${everydayMeal.mainingredient }</li>
								<li>${everydayMeal.condiment }</li>
								<li title="${everydayMeal.mealimg }">${everydayMeal.mealimg }</li>
								<li>${everydayMeal.mealpublish }</li>
								<li class="width1">${everydayMeal.mealzan }</li>
								<li>
									<a href="EmUpdateServlet.do?id=${everydayMeal.id }">编辑&nbsp;&nbsp;|</a>	
									<a href="EmDeleteServlet.do?id=${everydayMeal.id }">&nbsp;删除</a>	
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
					<a href="EverydayListServlet.do?pageNow=1">首页</a>
					<a href="EverydayListServlet.do?pageNow=${pageBean.pageNow-1>0 ? pageBean.pageNow-1:1 }">上一页</a>
					<a href="EverydayListServlet.do?pageNow=${pageBean.pageNow+1<=pageBean.totalPage ? pageBean.pageNow+1:pageBean.totalPage }">下一页</a>
					<a href="EverydayListServlet.do?pageNow=${pageBean.totalPage }">尾页</a>
					当前第&nbsp;${pageBean.pageNow }&nbsp;页&nbsp;&nbsp;|&nbsp;&nbsp;共&nbsp;${pageBean.totalCount }&nbsp;条记录&nbsp;&nbsp;|&nbsp;&nbsp;共&nbsp;${pageBean.totalPage }&nbsp;页 
				</div>
				
				<!-- <div class="page">
					<a class="first" href="#">&lt;</a>
					<a class="first" href="#">1</a>
					<a class="first" href="#">2</a>
					<a class="first" href="#">···</a>
					<a class="first" href="#">9</a>
					<a class="first" href="#">10</a>
					<a class="first" href="#">&gt;</a>
				</div> -->
				
				
				<%-- 姓名：${sessionScope.map.mealtit }
				
				头像：<img src='./resource/${sessionScope.map.mealimg  }' /> --%>
			</div>
		</div>
	</div>
</body>
</html>