<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="UTF-8">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>동아리 퀵 서비스</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/dashboard/">
 
    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
  </head>
  <body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/adminHeader.jsp" %>

<script type="text/javascript">
$(function(){
	initComponent();
	initData();
	initWebsocket();
	initEvent();
	
	function initComponent() {
		$('#Dash').addClass('active');
	}
	
	function initData() {
		var param = {};
		
		goAjaxGet('/orderList',null, function(result) {
			
			console.log(result);
			if ( result.status == "OK" ) {
			 	var html = "";

				$.each(result.data,function(k,v) { 
					html +="<tr>";
					html +="<td>"+ v.brandName+"</td>";
					html +="<td>"+ v.pickupDest+"</td>";
					html +="<td>"+ v.dest+"</td>";
					html +="<td>"+ v.item+"</td>";
					if ( v.replaceItem ) {
						html +="<td><input type='checkbox' checked onclick='return false'></td>";
					}
					else {
						html +="<td><input type='checkbox' onclick='return false'></td>";
					}
					html +="<td>"+ v.orderTime+"</td>";
					if ( v.driverNum == 0 ) {
						html +="<td><a class='batchDriver' data='"+v.orderNum+"'>미할당</a></td></tr>";
					}
					else {
						html +="<td><a class='batchDriver' data='"+v.orderNum+"'>"+ v.driverNum+" 번 기사</td></tr>";

					}
				});
			}
		
			$("#orderList").html(html);
			initBachEvent();
			 
		});
	}
	
	var orderNum;
	
	function initBachEvent() {
		
		$('.batchDriver').on('click', function(event) {
			
			orderNum = $(this).attr('data');
			
			$('#batchModal').show();
			var param = {};
			param.role = 'driver';
			
			goAjaxPost('/user/selectUserList', param, function(result) {
				
			    $('#batchModal').show();
			    
			    if ( result.status == 'OK' ) {
		    		var html = "";
			    	$.each(result.data, function(k, v) {
			    		html += "<option value=''>선택</option>";
			    		html += "<option value='"+v.driverNum+"'>"+v.driverNum +"번 기사"+"</option>";
			    	});
					$('#batchDriver').html(html);
			    }
			});
		});
	}
	
	function initEvent() {
		$('.closeBatchModal').on('click', function(){
		    $('#batchModal').hide();
		});
		
		$('#BatchDriverSave').on('click', function() {
			var param = {};
			param.loginId   = '${userVo.loginId}';
			param.orderNum  = orderNum;
			param.driverNum = $('#batchDriver').val();
			param.pickup    = false;
			
			console.log(param);
			
			goAjaxPost('/updateOrder', param, function(result){
				
				console.log(result);
			/* 	var QuickEvent = {};
				QuickEvent.type = EventType.APPROVE_ORDER;
				QuickEvent.payload = param; */
				
				if ( result.status == 'OK' ) {
					alert("할당 되었습니다");
					initData();
				}
			});
		});
	}
	// 재연결을 맺는다.
	function onSocketCloseHandler() {
		console.log("websocket closed");
		
		ws = null;
		setTimeout(initWebsocket, 1000);	
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

		ws.addEventListener('close',onSocketCloseHandler);
		
		var client = Stomp.over(ws);
		
		//웹소켓 console.log 비활성화
		client.debug = null;
		client.connect({}, function(frame) {
			// 특정 클라이언트 이벤트
			client.subscribe('/user/${userVo.loginId}/event', function(message) {
				 var event = JSON.parse(message.body);
				
				console.log(event);
				initData();

				/*var html = "";
				html +="<tr>";
				html +="<td>"+ event.payload.brandName+"</td>";
				html +="<td>"+ event.payload.pickupDest+"</td>";
				html +="<td>"+ event.payload.dest+"</td>";
				html +="<td>"+ event.payload.item+"</td>";
				if ( event.payload.replaceItem ) {
					html +="<td>있음</td>";
				}
				else {
					html +="<td>없음</td>";
				}
				html +="<td>"+ event.payload.orderTime+"</td>";
				if ( event.payload.driverNum == 0 ) {
					html +="<td><a onclick='batchDriver("+v.orderNum+")'>미할당</a></td></tr>";
				}
				else {
					html +="<td>미할당</td>";
				}
				$("#orderList").prepend(html); */
				
				
				/* if (event.type == 'CMD_RESULT') {

					var emsEvent = {};
					emsEvent.type = EventType.CMD_RESULT;
					emsEvent.data = event.payload;
					
					sendEmsEvent(emsEvent);
				}
				else if (event.type == 'APPR_RESULT') {
					var emsEvent = {};
					emsEvent.type = EventType.APPR_RESULT;
					emsEvent.data = event.payload;
					
					sendEmsEvent(emsEvent);
				} */
			});
			
			// 전체 이벤트
			client.subscribe('/topic/event', function(message) {
				var event = JSON.parse(message.body);
				
			/* 	if (event.type == 'WORK_ADD') {
					var emsEvent = {};
					emsEvent.type = EventType.WORK_ADD;
					emsEvent.data = event.payload;
					
					sendEmsEvent(emsEvent);
				} */
			
			});
		});
	}
});

</script>    

    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">Dashboard</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
          <div class="btn-group mr-2">
            <button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
            <button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
          </div>
          <button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle">
            <span data-feather="calendar"></span>
            This week
          </button>
        </div>
      </div>

      <h2>Section title</h2>
      <div class="table-responsive">
        <table class="table table-striped table-sm" style="text-align:center;">
          <thead>
            <tr>
              <th width="15%">업체명</th>
              <th width="10%">픽업위치</th>
              <th width="20%">목적지</th>
              <th>수량</th>
              <th>반품</th>
              <th>주문시간</th>
              <th>배치기사</th>
            </tr>
          </thead>
          <tbody id="orderList">
           
          </tbody>
        </table>
      </div>
    </main>
  </div>
</div>




<div id="batchModal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close closeBatchModal" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        
		<select class="custom-select d-block w-100" id="batchDriver" required>
             
        </select>


      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="BatchDriverSave">변경하기</button>
        <button type="button" class="btn btn-secondary closeBatchModal" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
  
<%@ include file="../common/footer.jsp" %>
        
</body>
</html>
