<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title id='Description'>jQuery Tree Sample</title>

</head>
<body>
<%@ include file="common/header.jsp" %>
<script>
$(function(){
	initWebsocket();
	$("#bt1").on('click',function(){
		console.log("bt1");
		$.ajax({
			url:'/test',
			type:'post',
			data:{
				"test":"test"
			},
			success:function(data){
				console.log(data);
			}
		});
	});
	$('#connect').on('click', function(){
		//initWebsocket();
		var id = $('#id').val();
		console.log("id : " + id);
		data = {"id":id};
		$.ajax({
			url:'/connect',
			type:'post',
			contentType: 'application/json',
			data:JSON.stringify(data),
			dataType   : 'json',
			success:function(data){
				console.log(data);
			}
		});
	});
	
	$("#disconnect").on('click', function() {
		var id = $('#id').val();
		console.log("id : " + id);
		data = {"id":id};
		$.ajax({
			url:'/disconnect',
			type:'post',
			contentType: 'application/json',
			data:JSON.stringify(data),
			dataType   : 'json',
			success:function(data){
				console.log(data);
			}
		});
		
		disconnectWebsocket();
		
	});
});
function disconnectWebsocket() {
	ws.close();
}
function initWebsocket() {
	console.log("initWebsocket");
	// 웹소켓
	var portStr = "";
	if (location.port.length == 0) {
		portStr = ":80";
	}
	else {
		portStr = ":" + location.port;
	}
	
	var protocolStr = "ws";
	if (location.protocol == 'https') {
		protocolStr = "wss";
	}

	var wsUrl = protocolStr + "://" + location.hostname + portStr + "/push";
	
	ws = new WebSocket(wsUrl);

	//ws.addEventListener('close',onSocketCloseHandler);
	
	var client = Stomp.over(ws);
	
	//웹소켓 console.log 비활성화
	client.debug = null;
	client.connect({}, function(frame) {
		console.log('isConnected')
		// 특정 클라이언트 이벤트
		client.subscribe('/user/${USER.loginId}/event', function(message) {
			
		});
		
		// 전체 이벤트
		client.subscribe('/topic/event', function(message) {
			var event = JSON.parse(message.body);
			
			console.log(event.payload);
			console.log("현재값 " + $("#tt").val());
			$("#tt").val($("#tt").val() +"\n" + event.payload);
		});
	});
}
</script>
<textarea id="tt" rows="50" cols="50">

</textarea>
<input type="text" id="id">
<button id="connect">접속</button>
<button id="send">전송</button>
<button id="disconnect">로그아웃</button>
</body>
</html>