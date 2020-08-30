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
    <title>Checkout example Â· Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/checkout/">

    <!-- Bootstrap core CSS -->
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
  <body class="bg-light">
      <%@ include file="../common/header.jsp" %>
  <script>
  $(function(){
	 $('#idCheck').on('click', function(){
		var loginId = $('#loginId').val();
		var param = {};
		param.loginId = loginId;
		goAjaxPost('/sign/idCheck', param, function(result) {
			if ( result.status == 'OK' ) {
				alert("사용 가능한 아이디입니다");
				$('#loginId').data(loginId,true);
			}
			else {
				alert("이미 등록 된 아이디입니다");
				$('#loginId').data(loginId,false);
			}
			
		});
	 });
	 
	 $('#signup').submit(function(event){
		event.preventDefault();
		var loginId = $('#loginId').val();

		var loginCheck = $('#loginId').data(loginId);
		if ( !loginCheck ) {
			
			alert("아이디 중복 확인 후 이용해주세요");
			return;
		}
		 
		var passwd = $('#passwd').val();
		var passwd2 = $('#passwd2').val();
		
		if ( passwd != passwd2 ) {
			alert("비밀번호가 서로 다릅니다");
			return;
		}
		
		var param = serializeObject($('#signup'));
		
		goAjaxPost('/sign', param, function(result){
			
			if ( result.status == 'OK') {
				window.location.href="/login/index"
			}
			else {
				alert('회원가입에 실패하였습니다');
			}
		});
		
	 });
  });
  </script>
    <div class="container">
  

  <div class="row">
    <div class="col-md-8 order-md-1">
      <h4 class="mb-3" style="margin-top:20px; margin-bottom:10px; text-align:center;">동아리 퀵 서비스 회원가입</h4>
      <form id="signup"class=""  action="/sign" method="post">
        <div class="row">
          <div class="col-md-6 mb-3" style="width:270px;">
            <label for="loginId">아이디</label>
            <input type="text" class="form-control" id="loginId" name="loginId" placeholder="사용하실 ID를 입력해주세요" value="" required>
          </div>
         	<button class="btn btn-primary btn-lg btn-block"type="button" style="margin-top:30px; width:100px; height:40px; font-size:15px;" id="idCheck">중복확인</button>
          <div class="col-md-6 mb-3">
            <label for="passwd">비밀번호</label>
            <input type="password" class="form-control" id="passwd" name="passwd" placeholder="비밀번호를 입력해주세요" value="" required>
          </div>
          
          <div class="col-md-6 mb-3">
            <label for="passwd2">비밀번호 확인</label>
            <input type="password" class="form-control" id="passwd2" name="passwd2" placeholder="비밀번호를 다시 한 번 입력해주세요" value="" required>
          </div>
          
          <div class="col-md-6 mb-3">
            <label for="userName">이름</label>
            <input type="text" class="form-control" id="userName" name="userName" placeholder="이름을 입력해주세요" value="" required>
          </div>
          
          <div class="col-md-6 mb-3">
            <label for="brandName">업체</label>
            <input type="text" class="form-control" id="brandName" name="brandName" placeholder="업체명을 입력해주세요" value="" required>
          </div>
          
          <div class="col-md-6 mb-3">
            <label for="phone">연락처</label>
            <input type="text" class="form-control" id="phone" name="phone" placeholder="연락처는 -없이 입력해주세요" value="" required>
          </div>
        </div>
        

        <button class="btn btn-primary btn-lg btn-block" type="submit">가입하기</button>
      </form>
    </div>
  </div>
  
</div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
