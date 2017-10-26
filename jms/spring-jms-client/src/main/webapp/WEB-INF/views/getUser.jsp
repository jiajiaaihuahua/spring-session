<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.div1 {
	display: flex;
	flex-direction: column;
}

.contain {
	display: flex;
	flex-direction: row;
	justify-content: flex-start;
}
.contain div{
	margin: 10px;
}
</style>
</head>

<body>
	<div class="div1">
		<div class="contain">
			<div>商品id</div>
			<div>商品名称</div>
			<div>商品品类</div>
		</div>
		<c:forEach items="${goodslist}" var="item">
			<div class="contain">
				<div>${item.id}</div>
				<div>${item.goodsname}</div>
				<div>${item.category}</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>