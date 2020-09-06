<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.1.1">
    
    <title>Dashboard Template Â· Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/dashboard/">

</head>
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
<body>
    <%@ include file="../common/header.jsp" %>
    <%@ include file="../common/adminHeader.jsp" %>
<script>
$(function(){
	
	try {
		initComponent();
		initData();
		initEvent();

	}
	catch(e) {
		
	}
	
	function initComponent() {
		$('#adminOrder').addClass('active');
	}
	
	function initData() {
		
		var param = {};
		param.role = 'warehouse';
		
		goAjaxPost('/user/selectUserList', param, function(result) {
			
			var html = "";
			html += "<option value=''>선택</option>";
			$(result.data).each(function(k,v){
				html += "<option value='"+v.loginId+"'>"+v.brandName+"</option>"
			});
			$('#brandName').html(html);
		});
	}
	
	function initEvent() {
		$('#order').submit(function(event) {
			event.preventDefault();
			
    		var param = serializeObject($('#order'));

    		param.item = param.item + "절" +param.item2 +"봉";
    		var _today = new Date(); // 예제 기준 시간 : 2000-01-01 13:12:12
		
    		param.loginId = $('#brandName').val();
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
    			
				if ( result.status == 'OK' ) {
					alert('주문이 완료 되었습니다');
				}
				else {
					alert()
				}
    		});
    		
		});
	}
	
});
</script>

    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
      <div class="row">
    <div class="col-md-8 order-md-1">
      <h4 class="mb-3" style="margin-top:20px; margin-bottom:10px; text-align:center;">주문서</h4>
      
      <form id="order" class=""  action="/order" method="post">
        <div class="row">
        
        <div class="col-md-6 mb-3">
            <label for="pickupDest">업체명</label>
 		  <select class="custom-select d-block w-100" id="brandName" required>

            </select>	
  		 </div>
        
        
          
          <div class="col-md-6 mb-3">
            <label for="dest">픽업 위치</label>
            <input type="text" class="form-control" id="pickupDest" name="pickupDest" placeholder="픽업 위치를 입력해주세요" value="" required>
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

        <button class="btn btn-primary btn-lg btn-block" type="submit" id="order">주문하기</button>
      </form>
    </div>
  </div>
    </main>
  </div>
</div>
</body>
</html>