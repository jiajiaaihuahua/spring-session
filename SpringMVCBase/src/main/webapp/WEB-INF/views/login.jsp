<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="轻实训" />
<meta name="author" content="轻实训" />

</head>
<body>
	<%
	    String contextPath = request.getContextPath();
	%>
	<div style="width: 300px; margin: 0 auto; padding: 10px; border: 1px solid #bbb;">
		<form action="<%=contextPath%>/login" method="post">
			<div>
				用户名：<input name="username" type="text" />
			</div>
			<br>
			<div>
				密&nbsp;&nbsp;&nbsp;&nbsp;码：<input name="password" type="password" />
			</div>
			<br>
			<div>
				<button type="submit">登录</button>
			</div>
		</form>
	</div>
</body>
</html>