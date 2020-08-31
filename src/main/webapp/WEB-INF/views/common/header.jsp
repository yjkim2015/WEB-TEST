<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var getContextPath = '${getContextPath}';
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${getContextPath}/resources/js/util/common_util.js"></script>
<script src="${getContextPath}/resources/js/main/constants.js"></script>
<script src="${getContextPath}/resources/js/util/event_util.js"></script>

<script src="${getContextPath}/resources/plugins/stomp/stomp.min.js"></script>
<script src="${getContextPath}/resources/plugins/sockjs-client/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
<script type="text/javascript" src="${getContextPath}/resources/js/dashboard.js"></script>

<link href="${getContextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${getContextPath}/resources/css/dashboard.css" rel="stylesheet">

<script>
 $(function(){
	 $('.navbar-toggler').on('click',function(){
		 var target = $('#sidebarMenu');
		 if ( target.hasClass('show') ) {
			 target.removeClass('show');
		 }
		 else {
			 target.addClass('show');
		 }
	 
	 }); 
 });
</script>

</head>
<body>
</body>
</html>