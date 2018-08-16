<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./resource/plugin/jquery.js"></script>
</head>
<body>
	<form action="#" method="post">
		<input type="hidden" id="status" value="2" />
		<lable>账号：</lable>
		<input id="acc" type="text" value="" >
		<lable>密码：</lable>
		<input id="pass" value="" type="password" >
		<input onclick="login()" type="button" value="登录">
		<p id="tip"></p>
	</form>
	<script type="text/javascript">
		function login(){
			var acc = $("#acc").val();
			var pass = $("#pass").val();
			var status = $("#status").val();
			console.log(acc+"--"+pass+"--"+status);
			$.ajax({
				  type: 'post',
				  url: 'TestServlet.do',
				  dataType:"JSON",
				  data: {'account':acc,'password':pass,'status':status},
				  success: function(result){
					  if(result.code != 0){
						  $("#tip").html(result.msg);
					  }else{
						  window.location.href = "TestIndexServlet.do";
					  }
						  
				  }
			});
		}
	</script>
</body>
</html>