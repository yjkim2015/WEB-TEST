<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title id='Description'>jQuery Tree Sample</title>
    <link rel="stylesheet" href="../../../resources/css/jqwidgets/styles/jqx.base.css" type="text/css" />
    <script type="text/javascript" src="../../resources/js/jqwidgets/scripts/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="../../resources/js/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="../../resources/js/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="../../resources/js/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="../../resources/js/jqwidgets/jqxpanel.js"></script>
    <script type="text/javascript" src="../../resources/js/jqwidgets/jqxtree.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            // Create jqxTree
            $('#jqxTree').jqxTree({ height: '300px', width: '300px' });
            $('#jqxTree').bind('select', function (event) {
                var htmlElement = event.args.element;
                var item = $('#jqxTree').jqxTree('getItem', htmlElement);
                alert(item.label);
            });
        });
    </script>
</head>
<body class='default'>
    <div id='jqxTree'>
        <ul>
            <li item-selected='true' id="home">Home</li>
            <li item-expanded='true'>Solutions
              <ul>
                  <li>Education</li>
                  <li>Financial services</li>
                  <li>Government</li>
                  <li>Manufacturing</li>
                  <li>Solutions
                        <ul>
                            <li>Consumer photo and video</li>
                            <li>Mobile</li>
                            <li>Rich Internet applications</li>
                            <li>Technical communication</li>
                            <li>Training and eLearning</li>
                            <li>Web conferencing</li>
                        </ul>
                  </li>
                  <li>All industries and solutions</li>
              </ul>
            </li>
        </ul>
    </div>
</body>
</html>