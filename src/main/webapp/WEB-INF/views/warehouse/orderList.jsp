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
      
      table{
        table-layout:fixed
      }
      #orderList>tr>td{ 
      	text-overflow:ellipsis; overflow:hidden
      }
		
    </style>
  </head>
  <body>
    <%@ include file="../common/header.jsp" %>
    <%@ include file="../common/warehouseHeader.jsp" %>
    
    <script>
    $(function(){
    	initComponent();
    	initData();
    	initEvent();
    	
    	function initComponent() {
    		$('#warehouseOrderList').addClass('active');
    	}
    	
    	function initData() {
    		var param = {};
    		param.loginId = '${userVo.loginId}';
    		
    		goAjaxGet('/orderList',param, function(result) {
    			
    			console.log(result);
    			if ( result.status == "OK" ) {
    			 	var html = "";

    				$.each(result.data,function(k,v) { 
    					html +="<tr>";
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
    			 
    		});
    	}
    	
    	function initEvent() {
    		
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
              <th style="width:10%">픽업위치</th>
              <th style="width:50%">목적지</th>
              <th style="width:15%">수량</th>
              <th style="width:5%">반품</th>
              <th style="width:25%">주문시간</th>
              <th style="width:5%">배치기사</th>
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
