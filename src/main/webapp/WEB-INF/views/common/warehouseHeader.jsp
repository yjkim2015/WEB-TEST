<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
    <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
  <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">동아리 퀵 서비스</a>
  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
<!--   <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
 -->  <ul class="navbar-nav px-3">
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="#" id="logout">Sign out</a>
	      <form action="/logout" method="post" id='logoutForm'>
	   <!--  <input type="submit" value="Logout"/> -->
	   	 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
    </li>
  </ul>
</nav>

<div class="container-fluid">
  <div class="row">
    <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="sidebar-sticky pt-3">
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link" href="/warehouse/admin" id="warehouseOrder">
              <span data-feather="home"></span>
              	주문서 작성 <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/warehouse/orderList" id="warehouseOrderList">
              <span data-feather="file"></span>
              	주문내역
            </a>
          </li>
        </ul>
      </div>
    </nav>
    <script>
$(function(){
	var nav_var = $('.nav-item');
	$(nav_var).each(function(k,v){
		if ( $(v).hasClass('active') ){ 
			$(v).removeClass('active');
		} 
	});
});
</script>
</body>
</html>