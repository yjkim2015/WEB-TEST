<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin 2 - Dashboard</title>

</head>
<%@ include file="common/header.jsp" %>

<script>
$(function(){
	initEvent();
});

function initEvent() {
	
}
</script>
<body id="page-top">

  <!-- Page Wrapper -->
  <div id="" style="float:right;">
	  <button type="button" id="startWork" class="btn-primary">출근</button>
	  <button type="button" id="endWork" class="btn-warning">퇴근</button>
  </div>
  <div id="wrapper" style="clear:both;">
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">날짜</th>
	      <th scope="col">출근시간</th>
	      <th scope="col">퇴근시간</th>
	      <th scope="col">업무내용</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <th scope="row">1</th>
	      <td>Mark</td>
	      <td>Otto</td>
	      <td>@mdo</td>
	    </tr>
	    <tr>
	      <th scope="row">2</th>
	      <td>Jacob</td>
	      <td>Thornton</td>
	      <td>@fat</td>
	    </tr>
	    <tr>
	      <th scope="row">3</th>
	      <td>Larry</td>
	      <td>the Bird</td>
	      <td>@twitter</td>
	    </tr>
	  </tbody>
	</table>
  </div>
	<br><br>
	<input type="text" id="yymmdd" style="margin-left:5px; margin-bottom:5px;">
	<div class="input-group" style='width:50%; height:150px; id = ""'>
	  <div class="input-group-prepend">
	    <span class="input-group-text">업무내용</span>
	  </div>
	  <textarea class="form-control" aria-label="With textarea"></textarea>&nbsp
	  <button type="button" id="register" class="btn-primary">일일업무 등록</button>
	</div>
	
	<div class="input-group" id="workDetail" style='width:50%; height:150px; display:none;'>
	  <div class="input-group-prepend">
	    <span class="input-group-text">업무 상세</span>
	  </div>
	  <textarea class="form-control" aria-label="With textarea"></textarea>&nbsp
	  <button type="button" id="register" class="btn-primary"></button>
	</div>
	
	
</body>

</html>
