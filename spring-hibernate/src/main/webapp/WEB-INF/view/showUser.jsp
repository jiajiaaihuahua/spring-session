<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE ">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>showUser</title>
</head>
<body>
 <h2>struts2接收到的数据如下：</h2>
 <table>
	 <c:forEach items="${list}" var="item">
	 	<tr>
	 		<td>${item.id} </td>
	 		<td>${item.name} </td>
	 		<td>${item.age} </td>
	 	</tr>
	 </c:forEach>
 </table>
</body>
</html>