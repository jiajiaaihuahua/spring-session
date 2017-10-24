<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
*{
	margin : 0px;
	padding : 0px;
}
#contain {
	display: flex;
	flex-direction: row;
	flex-wrap: wrap;
	border: solid 1px #FFE4B5;
	justify-content: flex-start;
}

#output {
	height: 698px;
	width: 100%;
	background: #FFFFF0;
}

#input {
	text-align: left;
	height: 100%;
	width: 100%;
}

#textID {
	height: 88px;
    width: 100%;
	display: inline-block;
}
#send{
    width: 117px;
    height: 51px;
	display: inline-block;
	width: 4 %;
	position: relative;
    bottom: 52px;
    left: 1401px;
}
</style>
</head>
<script language="javascript" type="text/javascript">
	var wsUri = getRootUri() + "/webSocket-demo1/chat";
	
	//声明一个websocket对象
	var websocket = null;
	

	function getRootUri() {
		return "ws://"
				+ (document.location.hostname == "" ? "localhost"
						: document.location.hostname)
				+ ":"
				+ (document.location.port == "" ? "8080"
						: document.location.port);
	}

	function init() {
		output = document.getElementById("output");
	}

	function send_message() {
		var message = document.getElementById("textID").value;
		if(websocket == undefined){
			alert("请建立连接!");
		}else if(websocket.readyState == 0){
			alert("连接尚未建立，请先建立连接！");
		}else if(websocket.readyState == 3){
			alert("连接已关闭！");
		}else{
			//开始发送消息
			doSend(message.trim());
			document.getElementById("textID").value = "";
		}
		

	}

	function onMessage(evt) {
		var evt = JSON.parse(evt.data);
		if(evt.flag == "initUser"||evt.flag == "exit"){
			//初始化用户
			writeToScreen(evt.message);
			document.getElementById("currentUser").innerHTML = "当前在线用户： "+evt.user;
		}else if(evt.flag == "returnMessage"){
			writeToScreen(evt.prefix +":"+ evt.data);
		}
	}

	function onError(evt) {
		writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
	}

	function doSend(message) {
		//writeToScreen("Message Sent: " + message);
		websocket.send(message);
	}
	//消息传送到页面组件
	function writeToScreen(message) {
		var pre = document.createElement("p");
		pre.style.wordWrap = "break-word";
		pre.innerHTML = message;

		output.appendChild(pre);
	}
	//连接到websocket服务端
	function connectSocket(){
		websocket = new WebSocket(wsUri);
		websocket.onopen = function(evt) {
			var data = JSON.parse(evt.data);
			writeToScreen('<span style="color: red;">连接成功。</span> ');
		};
		
		//消息反馈
		websocket.onmessage = function(evt) {
			onMessage(evt)
		};
		
		//消息出错
		websocket.onerror = function(evt) {
			onError(evt)
		};
		websocket.onclose = function(){
			writeToScreen('<span style="color: red;">连接断开。</span> ');
		};
	}
	
	//断开连接
	function disconnectSocket(){
		websocket.close();
	}
	window.addEventListener("load", init, false);
</script>
<h1 style="text-align: center;">WebSocket Client</h1>

<br>
<div id="contain">
<button id ="connect" onclick = "connectSocket()" >连接</button>
<button id ="disconnect" onclick = "disconnectSocket()" >断开</button>
	<div id="input">
		<form action="">
			<textarea autofocus maxlength=1000 id="textID" name="message" value="Hello WebSocket!"></textarea>
			<input id ="send" onclick="send_message()" value="Send" type="button" size="10%"><br>
		</form>
	</div>
	<div id = "currentUser">当前在线用户：</div>
	<div id="output"></div>
</div>
</body>
</html>