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
    <link href="zw/css/lunbo.css" rel="stylesheet" type="text/css"/>
	<link href="zw/css/nav.css" rel="stylesheet" type="text/css"/>
	
	<link href="zw/css/list.css" rel="stylesheet" type="text/css"/>
	
	<link href="zw/css/top_search.css" rel="stylesheet" type="text/css"/>


    <script type="text/javascript" src="zw/js/nav.js">
    </script>
    <script type="text/javascript" src="zw/js/jquery-1.7.2.min.js">
    </script>
    <script type="text/javascript" src="zw/js/jquery.js">
    </script>
    <script type="text/javascript" src="zw/js/lunbo.js">
    </script>
    <style type="text/css">
       
    </style>
	<script type=""text/javascript>
   function moveIn(ele) {
     ele.style.border = "3px solid #FFCFB1" ;
   }
   
   function moveOut(ele) {
     ele.style.border = "3px solid #ffffff" ;
   }
	</script>
</head>
<body>
    <div class="top" >
      <span class="top_l">
        <a href="main.jsp" target="_parent">首页</a>
        |&nbsp; <a href="login.jsp" target="_parent">会员登录</a>
        |&nbsp; <a href="regist.jsp" target="_parent">注册会员</a>
	  </span>
	  <span class="top_r" ">
        <a href="<c:url value='/cart/findByUser.action?currPage=1'/>" target="_parent">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="<c:url value='/order/findByUser.action?currPage=1'/>" target="_parent">个人中心</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="javaScript:void(0);" target="body">退出</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	  </span>
    </div>
    <!-- 搜索框-->
	 <div class="search" >
		  <input type="text" name="searchCondition"></input>
		  <a href="#" onclick="addCondition(this)" ><img src="zw/image/search.png"></img></a>
	</div>
    <!-- 导航栏 -->
	<div class="clear"></div>
        <div class="nav" id="mainNav">
            <ul class="list">
		
		   <c:forEach var="parent" items="${allCategory }">
                <li>
                    <a href="#" class="game_hover">${parent.name }</a>
                   
                    <div class="hover_cont wlyx">
                     <c:forEach var="child" items="${parent.childs }">
                        <div class="nav_cont">
                            <div class="nav_li">
                             
                                <div class="nav_li_l">
                                     ${child.name }
                                </div>
                                <div class="nav_li_r">
                                   <c:forEach var="child2" items="${child.childs }" varStatus="status">
                                    <a href="#">${child2.name }</a>┊
                                    <c:if test="${status.count%10 == 0}"> <br/></c:if>
                                   </c:forEach>
                                </div>
                               
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                     
                </li>
           </c:forEach>
              
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
	
				<div class="shopMainbg">
				<div class="shopMain" id="shopmain">

    <div class="recomm">
    	    <h1>今日推荐</h1><hr/>
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
    	    <h1>水果蔬菜</h1><hr/>
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
