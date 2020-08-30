<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
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
    	initEvent();
    	var userVo = '${userVo}';
    	
    	function initEvent() {
    		addEmsEventListener(handleEmsEvent);
    		
    		$('#order').submit(function(event){
        		event.preventDefault();
        		
        		var param = serializeObject($('#order'));
        		
        		param.item = param.item + "절" +param.item2 +"봉";
        		var _today = new Date(); // 예제 기준 시간 : 2000-01-01 13:12:12

        		param.orderTime = _today.format('yyyy-MM-dd HH:mm:ss');
				param.pickupDest = $('#pickupDest').val();
				var replaceItem = $('#replaceItem').val();
				if ( replaceItem == 'on' ) {
					param.replaceItem = true;
				}
				else {
					param.replaceItem = false;

				}
				
				if ( param.pickupDest == '선택' ) {
					alert("픽업 위치를 선택해주세요");
					return;
				}
        		goAjaxPost('/order', param, function(result){
        			
        			console.log(result);
        		});
        	});
    	}
    	
    	function handleEmsEvent(message) {
			/* if (message.type == EventType.EQP_TREE_EQP_NODE_SELECTED) {
			} */
    	}
    });
    
    </script>
    <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
  <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">동아리 퀵 서비스</a>
  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
<!--   <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
 -->  <ul class="navbar-nav px-3">
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="/logout">Sign out</a>
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
      <div class="row">
    <div class="col-md-8 order-md-1">
      <h4 class="mb-3" style="margin-top:20px; margin-bottom:10px; text-align:center;">주문서</h4>
      <form id="order" class=""  action="/order" method="post">
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="pickupDest">픽업위치</label>
<!--             <input type="text" class="form-control" id="pickupDest" name="pickupDest" placeholder="픽업 위치를 입력해주세요" value="" required>
 -->         
 		  <select class="custom-select d-block w-100" id="pickupDest" required>
              <option value="">선택</option>
              <option>매장</option>
              <option>창고</option>
              <option>매장+창고</option>
            </select>	
  		 </div>
          <div class="col-md-6 mb-3">
            <label for="dest">행선지</label>
            <input type="text" class="form-control" id="dest" name="dest" placeholder="배송 위치를 입력해주세요" value="" required>
          </div>
          
          <div class="col-md-6 mb-3">
            <label for="item">물품 수량 (절)</label>
            <input type="text" class="form-control" id="item" name="item" placeholder="수량을 입력해주세요" value="" required>
          </div>
          
           <div class="col-md-6 mb-3">
            <label for="item2">물품 수량 (봉)</label>
            <input type="text" class="form-control" id="item2" name="item2" placeholder="수량을 입력해주세요" value="" required>
          </div>
          
          <div class="col-md-6 mb-3 form-check" style="margin-left:17px">
         	 <input type="checkbox" class="form-check-input" id="replaceItem">
   			 <label class="form-check-label" for="replaceItem">반품유무</label>
          </div>
          
        </div>
        
        <input type="hidden" id="loginId" name="loginId" value="${userVo.loginId}"/>

        <button class="btn btn-primary btn-lg btn-block" type="submit">주문하기</button>
      </form>
    </div>
  </div>
    </main>
  </div>
</div>
<%@ include file="../common/footer.jsp" %>
        
</body>
</html>
