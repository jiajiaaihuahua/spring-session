<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="轻实训" />
<meta name="author" content="轻实训" />
<title>SpringMVC 文件上传案例</title>

</head>
<body>
	<div style="width: 400px; margin: 0 auto;">
		<header>
			<h2>Spring MVC 文件上传练习</h2>
		</header>
		<div class="container">
			<div class="page-header">
				<h3>请选择上传文件（文件大小在 30M 以内）</h3>
			</div>
			<form action="${ctx }/upload" method="post" enctype="multipart/form-data">
				<input name="file" class="file" type="file" multiple data-min-file-count="1">
				<button type="submit">提交</button>
				<button type="reset">重置</button>
			</form>
		</div>
	</div>
</body>
</html>