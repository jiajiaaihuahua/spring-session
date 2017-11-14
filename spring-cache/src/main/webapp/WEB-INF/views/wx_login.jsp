<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <script
	src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script> -->
	

</script>
</head>
<body>
	<div id="login_container"></div>
	
<script src="assets/js/wxLogin.js"></script>
<script type="text/javascript">
	var contain = document.getElementById("login_container").innerHTML; 
	var obj = new WxLogin({
		id :"login_container",
		appid : "${appid}",
		scope : "snsapi_login",
		redirect_uri : "${redirect_uri}",
		state : "0",
		style : "",
		href : "" 
	});
</body>
</html>