<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <base href="<%=basePath%>">
	<link href="zw/css/list.css" rel="stylesheet" type="text/css"/>
    <link href="zw/css/nav.css" rel="stylesheet" type="text/css"/>
	<link href="zw/css/lunbo.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="zw/js/nav.js"></script>
    <script type="text/javascript" src="zw/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="zw/js/lunbo.js"></script>
    <script type="text/javascript" src="zw/js/jquery.js"></script>
    <script type="text/javascript" src="zw/js/ajaxNav.js"></script>  
	
</head>
<body>
   
    <iframe frameborder="0" src="zw/top.jsp" width="100%" hight="110px" name="top" scrolling="no"></iframe>
    <!-- 导航栏 -->
	<div class="clear"></div>
	
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
   
    <div class="recomm">
    	    <h1  style=" font:25px 'Arial Black' ">今日推荐</h1><hr/>
    	    <ul class="good_list">
			  <li>
			  <div class="inner" onmouseover="moveIn(this)" onmouseout="moveOut(this)">
			    <a class="pic" href="" target="_parent">
			      <img src="zw/image/cp2.jpg" border="0"/>
			    </a>
			    <p class="price">
					<span class="price_n">&yen;26.5</span>
				</p>
				<p><a id="commodityname" title="" href="#">原味海苔</a></p>
				<p><a href="javascript:void(0);" name='P_zz' id="info" title='${commodity.info } '>脆脆好吃</a></p>
				
			  </div>
			  </li>
			  <li>
			  <div class="inner" onmouseover="moveIn(this)" onmouseout="moveOut(this)">
			    <a class="pic" href="" target="_parent">
			      <img src="zw/image/cp2.jpg" border="0"/>
			    </a>
			    <p class="price">
					<span class="price_n">&yen;26.5</span>
				</p>
				<p><a id="commodityname" title="" href="#">原味海苔</a></p>
				<p><a href="javascript:void(0);" name='P_zz' id="info" title='${commodity.info } '>脆脆好吃</a></p>
				
			  </div>
			  </li>
			  <li>
			  <div class="inner" onmouseover="moveIn(this)" onmouseout="moveOut(this)">
			    <a class="pic" href="" target="_parent">
			      <img src="zw/image/cp2.jpg" border="0"/>
			    </a>
			    <p class="price">
					<span class="price_n">&yen;26.5</span>
				</p>
				<p><a id="commodityname" title="" href="#">原味海苔</a></p>
				<p><a href="javascript:void(0);" name='P_zz' id="info" title='${commodity.info } '>脆脆好吃</a></p>
				
			  </div>
			  </li>
			  <li>
			  <div class="inner" onmouseover="moveIn(this)" onmouseout="moveOut(this)">
			    <a class="pic" href="" target="_parent">
			      <img src="zw/image/cp2.jpg" border="0"/>
			    </a>
			    <p class="price">
					<span class="price_n">&yen;26.5</span>
				</p>
				<p><a id="commodityname" title="" href="#">原味海苔</a></p>
				<p><a href="javascript:void(0);" name='P_zz' id="info" title='${commodity.info } '>脆脆好吃</a></p>
				
			  </div>
			  </li>
      
    </ul>
    </div>
	 <div class="recomm">
    	    <h1  style=" font:25px 'Arial Black' ">水果蔬菜</h1><hr/>
    	    <ul class="good_list">
			  <li>
			  <div class="inner" onmouseover="moveIn(this)" onmouseout="moveOut(this)">
			    <a class="pic" href="" target="_parent">
			      <img src="zw/image/cp2.jpg" border="0"/>
			    </a>
			    <p class="price">
					<span class="price_n">&yen;26.5</span>
				</p>
				<p><a id="commodityname" title="" href="#">原味海苔</a></p>
				<p><a href="javascript:void(0);" name='P_zz' id="info" title='${commodity.info } '>脆脆好吃</a></p>
				
			  </div>
			  </li>
			  <li>
			  <div class="inner" onmouseover="moveIn(this)" onmouseout="moveOut(this)">
			    <a class="pic" href="" target="_parent">
			      <img src="zw/image/cp2.jpg" border="0"/>
			    </a>
			    <p class="price">
					<span class="price_n">&yen;26.5</span>
				</p>
				<p><a id="commodityname" title="" href="#">原味海苔</a></p>
				<p><a href="javascript:void(0);" name='P_zz' id="info" title='${commodity.info } '>脆脆好吃</a></p>
				
			  </div>
			  </li>
			  <li>
			  <div class="inner" onmouseover="moveIn(this)" onmouseout="moveOut(this)">
			    <a class="pic" href="" target="_parent">
			      <img src="zw/image/cp2.jpg" border="0"/>
			    </a>
			    <p class="price">
					<span class="price_n">&yen;26.5</span>
				</p>
				<p><a id="commodityname" title="" href="#">原味海苔</a></p>
				<p><a href="javascript:void(0);" name='P_zz' id="info" title='${commodity.info } '>脆脆好吃</a></p>
				
			  </div>
			  </li>
			  <li>
			  <div class="inner" onmouseover="moveIn(this)" onmouseout="moveOut(this)">
			    <a class="pic" href="" target="_parent">
			      <img src="zw/image/cp2.jpg" border="0"/>
			    </a>
			    <p class="price">
					<span class="price_n">&yen;26.5</span>
				</p>
				<p><a id="commodityname" title="" href="#">原味海苔</a></p>
				<p><a href="javascript:void(0);" name='P_zz' id="info" title='${commodity.info } '>脆脆好吃</a></p>
				
			  </div>
			  </li>
      
    </ul>
    </div>
	
</body>
</html>
