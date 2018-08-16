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
			<form class="everday_list_search" action="NewsListServlet.do" method="post">
				<label>关键词：</label>
				<input type="text" name="newstit" value="${queryEverydayMeal.newstit }" />
				<label>发布时间：</label>
				<input type="date" name="newspublish" value="${queryEverydayMeal.newspublish }" />
				<input type="submit" class="e_l_search" value="查询" />
			</form>
			
			<div class="e_l_table">
				<ul class="e_l_table_tit">
					<li class="width1">编号</li>
					<li style="width:15%">标题</li>
					<li style="width:20%">摘要</li>
					<li style="width:20%">详细内容</li>
					<li style="width:15%">图片</li>
					<li>发布时间</li>
					<li>操作</li>
				</ul>
				<ul class="e_l_table_cont">	
					<c:if test="${!empty pageBean.data }">
					<!-- 这个属性是用来计数的，varStatus="vs" -->
						<c:forEach items="${pageBean.data }" var="everydayMeal" varStatus="vs">
						<li class="bg1">
							<ul class="e_l_table_list">
								<li class="width1">${vs.count }</li>
								<li style="width:15%">${everydayMeal.newstit }</li>
								<li style="width:20%">${everydayMeal.newsintro }</li>
								<li style="width:20%">${everydayMeal.newsdetail }</li>
								<li style="width:15%" title="${everydayMeal.newsimg }">${everydayMeal.newsimg }</li>
								<li>${everydayMeal.newspublish }</li>
								<li>
									<a href="NewsUpdateServlet.do?id=${everydayMeal.id }">编辑&nbsp;&nbsp;|</a>	
									<a href="NewsDeleteServlet.do?id=${everydayMeal.id }">&nbsp;删除</a>	
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
					<a href="NewsListServlet.do?pageNow=1">首页</a>
					<a href="NewsListServlet.do?pageNow=${pageBean.pageNow-1>0 ? pageBean.pageNow-1:1 }">上一页</a>
					<a href="NewsListServlet.do?pageNow=${pageBean.pageNow+1<=pageBean.totalPage ? pageBean.pageNow+1:pageBean.totalPage }">下一页</a>
					<a href="NewsListServlet.do?pageNow=${pageBean.totalPage }">尾页</a>
					当前第&nbsp;${pageBean.pageNow }&nbsp;页&nbsp;&nbsp;|&nbsp;&nbsp;共&nbsp;${pageBean.totalCount }&nbsp;条记录&nbsp;&nbsp;|&nbsp;&nbsp;共&nbsp;${pageBean.totalPage }&nbsp;页 
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>