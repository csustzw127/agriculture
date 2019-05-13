
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
	<link href="zw/css/top_search.css" rel="stylesheet" type="text/css"/>
	<link href="basic/css/demo.css" rel="stylesheet" type="text/css" />
	 <link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
	<link href="AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css" />
	<link href="zw/css/top_search.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
	   function addCondition(node) {
	       node.attributes['href'].value = "commodity/findByName.action?currPage=1&";
	       node.attributes['href'].value += "name="+document.all.searchCondition.value;
	   }
	</script>
  </head>
  
  <body>
  <div class="top" >
      <span class="top_l">
        <a href="main.jsp" target="_parent">首页</a>
        <c:if test="${empty user }">
	        |&nbsp; <a href="zw/user/login.jsp" target="_parent">会员登录</a>
	        |&nbsp; <a href="zw/user/regist.jsp" target="_parent">注册会员</a>
        </c:if>
         <c:if test="${not empty user }">
          |&nbsp; 您好 : ${user.nickname }
		</c:if>
	  </span>
	  <span class="top_r" ">
	   <c:if test="${not empty user }">
        <a href="<c:url value='/cart/findByUser.action?currPage=1'/>" target="_parent">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="<c:url value='/order/findByUser.action?currPage=1'/>" target="_parent">个人中心</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="javaScript:void(0);" target="body">退出</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		</c:if>
	  </span>
    </div>
     <!-- 搜索框-->
	 <div class="search" >
		  <input type="text" name="searchCondition"></input>
		  <a href="#" onclick="addCondition(this)" target="_top" ><img src="zw/image/search.png"></img></a>
	</div>
  </body>
</html>
