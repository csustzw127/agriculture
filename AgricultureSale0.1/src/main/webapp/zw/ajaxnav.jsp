<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="zw/css/nav.css" rel="stylesheet" type="text/css"/>
	<link href="zw/css/lunbo.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="zw/js/nav.js"> </script>
    <script type="text/javascript" src="zw/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="zw/js/jquery.js"></script>
    <script type="text/javascript" src="zw/js/lunbo.js"></script>
    
    <script type="text/javascript">
  
    </script>
  </head>
  
  <body>
  <button id="test">点我</button>
  <div class="nav" id="mainNav">
  		<ul id="nav_list" class="list">
               
        </ul>
     </div>
 <div class="panel_ad">
        <div class="slider">
            <ul class="slider-main">
                <li class="slider-panel">
                    <a href="#" target="_blank"><img src="zw/image/ad1.png"></a>
                </li>
                <li class="slider-panel">
                    <a href="#" target="_blank"><img src="zw/image/ad2.png"></a>
                </li>
            </ul>
            <div class="slider-extra">
                <ul class="slider-nav">
                    <li class="slider-item">
                    </li>
                    <li class="slider-item">
                    </li>
                </ul>
                <div class="slider-page">
                    <a class="slider-pre" href="javascript:;;"> < </a>
                    <a class="slider-next" href="javascript:;;">></a>
                </div>
            </div>
        </div>
    </div>
  </body>
</html>
