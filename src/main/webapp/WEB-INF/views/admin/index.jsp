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
    <title>Dashboard Template Â· Bootstrap</title>

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
<script>
$(function(){
	initData();
	initWebsocket();
	
	
	function initData() {
		
		goAjaxGet('/orderList',null, function(result) {
			
			console.log(result);
			if ( result.status == "OK" ) {
			 	var html = "";

				$.each(result.data,function(k,v){ 
					html +="<tr>";
					html +="<td>"+ v.brandName+"</td>";
					html +="<td>"+ v.pickupDest+"</td>";
					html +="<td>"+ v.dest+"</td>";
					html +="<td>"+ v.item+"</td>";
					if ( v.replaceItem ) {
						html +="<td>있음</td>";
					}
					else {
						html +="<td>없음</td>";
					}
					html +="<td>"+ v.orderTime+"</td>";
					if ( v.driverNum == 0 ) {
						html +="<td>미할당</td></tr>";
					}
					else {
						html +="<td>"+ v.driverNum+" 번 기사</td></tr>";

					}
				});
			}
		
			$("#orderList").append(html);
			 
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
				var html = "";
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
				html +="<td>미할당</td></tr>";
				$("#orderList").prepend(html);
				
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
    <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
  <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">Company name</a>
  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
  <ul class="navbar-nav px-3">
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="#">Sign out</a>
    </li>
  </ul>
</nav>

<div class="container-fluid">
  <div class="row">
    <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="sidebar-sticky pt-3">
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link active" href="#">
              <span data-feather="home"></span>
              Dashboard <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file"></span>
              Orders
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="shopping-cart"></span>
              Products
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="users"></span>
              Customers
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="bar-chart-2"></span>
              Reports
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="layers"></span>
              Integrations
            </a>
          </li>
        </ul>

        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
          <span>Saved reports</span>
          <a class="d-flex align-items-center text-muted" href="#" aria-label="Add a new report">
            <span data-feather="plus-circle"></span>
          </a>
        </h6>
        <ul class="nav flex-column mb-2">
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Current month
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Last quarter
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Social engagement
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Year-end sale
            </a>
          </li>
        </ul>
      </div>
    </nav>

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
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <th>업체명</th>
              <th>픽업위치</th>
              <th>목적지</th>
              <th>수량</th>
              <th>반품유무</th>
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
<%@ include file="../common/footer.jsp" %>
        
</body>
</html>
