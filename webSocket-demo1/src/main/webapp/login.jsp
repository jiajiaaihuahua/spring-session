<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script
  src="https://code.jquery.com/jquery-3.2.1.js"
  integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  crossorigin="anonymous"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#button1").click(function(){
			$.ajax({
				type : "post",
				url : "web/LoginServlet",
				data : "username="+$("#username").val(),
				success : function(message){
					if(message == "success"){
						window.location.href = "web/MyWebsocket";
					}
				},
				error : function(){
					alert("请联系管理员");
				}
			})
		})
		
	})
</script>
</head>
<body>
	<input id ="username" type="text" name = "username" />
	<input id="button1" type="button" value ="登录">
</body>
</html>