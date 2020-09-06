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

<link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/sign-in/">
<link href="${getContextPath}/resources/css/signin.css" rel="stylesheet">

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
  <body class="text-center">
    <%@ include file="../common/header.jsp" %>
  
<script>
$(function(){
    $('#login').on('click', function(){
	    var loginId = $('#loginId').val();
	    var passwd = $('#passwd').val();
	    goLogin(loginId,passwd);
    });
    
    $('#signup').on('click', function(){
    	window.location.href="/sign";
    });
    
	function goLogin(loginId, passwd) {
		goAjaxPost('/login/proc?loginId=' + loginId + '&passwd=' + encodeURIComponent(passwd) +'&remember-me='+$('#remember-me').val() /* +'&_csrf='+$('#csrf').val() */, null, function(result) {
			if ( 'OK' == result.result ) {
				location.href = result.redirectUrl;	
			}
			else {
				errorMsg(result.reason);
			}
		});	
	}
});
</script>
    <form class="form-signin" action="/login/proc" method="post">
  <!-- <img class="mb-4" src="../assets/brand/bootstrap-solid.svg" alt="" width="72" height="72"> -->
  <h1 class="h3 mb-3 font-weight-normal">동아리 퀵 서비스</h1>
  <label for="inputId" class="sr-only">Id</label>
  <input type="text" id="loginId" name="loginId" class="form-control" placeholder="Id" required autofocus>
  <label for="inputPassword" class="sr-only">Password</label>
  <input type="password" id="passwd" name="passwd" class="form-control" placeholder="Password" required>
  <div class="checkbox mb-3">
    <label>
      <input type="checkbox"  value="true"name="remember-me" id="remember-me"> 로그인 유지
    </label>
  </div>
  <button class="btn btn-lg btn-primary btn-block" type="button" id="login">로그인</button>
  <button class="btn btn-lg btn-primary btn-block" type="button" id="signup">회원가입</button>
  
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrf"/>
  <p class="mt-5 mb-3 text-muted">&copy; 2020-08-22 created by yjkim</p>
</form>
<%@ include file="../common/footer.jsp" %>

</body>
</html>
