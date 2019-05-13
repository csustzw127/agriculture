<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单详细</title>
    <base href="<%=basePath%>">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<link rel="stylesheet" type="text/css" href="<c:url value='/preOrder.css'/>">
	<script type="text/javascript" src="zw/js/address.js"></script>
  </head>
  
<body>

	<div class="divContent" style="margin-top:20px;margin-left:150px;">
		<div class="div2">
			<dl>
				<dt>商品清单</dt>
				<dd>
					<table cellpadding="0" cellspacing="0" style=" text-align:center;width:800px; ">
					    <tr>
				          <th class="tt"></th>
				          <th class="tt"></th>
				           <th class="tt"></th>
				          <th class="tt">商品名</th>
				          <th class="tt"></th>
				          <th class="tt">数量</th>
				          <th class="tt">小结</th>
				          <th class="tt"></th>
				        </tr>
				        <c:forEach var="cart" items="${carts }">
				         <tr >
				          <td><input type="hidden" name="cartId" value="${cart.id }"></input><td>
				          <td ><img src="${cart.commodity.image }" width="150" height="80" /></td>
				          <td class="commName">${cart.commodity.name}</td>
				          <td >
				                     &nbsp 颜色 : &nbsp&nbsp${cart.color.detail } &nbsp<br/>
				                <c:forEach var="sizeItem" items="${cart.sizeItems }">&nbsp ${sizeItem.size.detail }&nbsp: &nbsp&nbsp${sizeItem.size.name }<br/></c:forEach>
				          </td>
				          <td>${cart.quantity }</td>
				          <td class="subtotal">${cart.subtotal }</td>
				        </tr>
				        </c:forEach>
					</table>
				</dd>
			</dl>
		</div>
		<div style="margin: 10px 10px 10px 550px;">
			<span style="font-weight: 900; font-size: 15px;">合计金额：</span>
			<span class="price_t" id="totalPrice">&yen;</span><br/>

		    <a id="cancel" href="javascript:history.go(-1);">取消订单</a>
			<a id="confirm" href="javascript:submit();">提交订单</a><br/>	
		</div>
	</div>
</body>

</html>

