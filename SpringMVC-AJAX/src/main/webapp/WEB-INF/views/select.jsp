<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>轻实训-SpringMVC-AJAX</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="轻实训" />
<meta name="author" content="轻实训" />

<script type="text/javascript" src="${ctx}/plugins/jquery-2.2.4.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		//初始化所有省数据
		initProvince();

		//选择省
		$("#provinceSelector").change(function(e) {
			//获取选中的省编号 
			var provinceCode = $(this).find("option:selected").val();
			selectProvince(provinceCode);
		});

		//选择市
		$("#citySelector").change(function(e) {
			//获取选中的市编号 
			var cityCode = $(this).find("option:selected").val();
			selectCity(cityCode);
		});
	})

	//初始化省份
	function initProvince() {
		$.ajax({
			type : "POST",
			cache : false,
			url : "${ctx}/select/province",
			data : {},
			success : function(data) {
				var $province = $("#provinceSelector");
				for ( var item in data) {
					console.log(data[item]);
					$province.append("<option value='"+ data[item].code +"'>" + data[item].name + "</option>");
				}
			},
			error : function(data) {
				alert("系统故障:" + data);
			}
		})
	}

	//选择省份后，加载该省下的所有市
	function selectProvince(provinceCode) {
		//如果 provinceCode 为空，则不进行任何处理
		if (provinceCode == '') {
			return;
		}
		$.get("${ctx}/select/city/" + provinceCode, function(data) {
			var $city = $("#citySelector");
			var $district = $("#districtSelector");
			//清空市列表（除了第一项：请选择城市...）
			$city.children("option:not('option:first')").remove();
			//清空区列表（除了第一项：请选择城区...）
			$district.children("option:not('option:first')").remove();
			//加载市列表
			for ( var item in data) {
				console.log(data[item]);
				$city.append("<option value='"+ data[item].code +"'>" + data[item].name + "</option>");
			}
		});
	}

	//选择市后，加载该市下的所有区
	function selectCity(cityCode) {
		//如果 cityCode 为空，则不进行任何处理
		if (cityCode == '') {
			return;
		}
		$.post("${ctx}/select/district/" + cityCode, function(data) {
			var $district = $("#districtSelector");
			//清空区列表（除了第一项：请选择城区...）
			$district.children("option:not('option:first')").remove();
			//加载区列表
			for ( var item in data) {
				console.log(data[item]);
				$district.append("<option value='"+ data[item].code +"'>" + data[item].name + "</option>");
			}
		});
	}
</script>
</head>
<body>
	<select id="provinceSelector">
		<option value="">请选择省份...</option>
	</select>
	<select id="citySelector">
		<option value="">请选择城市...</option>
	</select>
	<select id="districtSelector">
		<option value="">请选择城区...</option>
	</select>
</body>
</html>