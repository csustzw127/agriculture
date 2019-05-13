<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
         <base href="<%=basePath%>">
        <title>购物车</title>
        
        <link href="/AgricultureSale0.1/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
        <link href="/AgricultureSale0.1/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
        <link href="/AgricultureSale0.1/css/personal.css" rel="stylesheet" type="text/css">
        <link href="/AgricultureSale0.1/css/addstyle.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="zw/css/user/cart.css"/>
        <script src="/AgricultureSale0.1/AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
        <script src="/AgricultureSale0.1/AmazeUI-2.4.2/assets/js/amazeui.js"></script>
        <script src="/AgricultureSale0.1/zw/js/user/cart.js"></script>
          <script src="/AgricultureSale0.1/zw/js/user/round.js"></script>
        <style type="text/css">
            .top {
                background-color: white;
                width: 1200px;
                font: 14px "Arial Black", arial, serif;
                line-height: 30px;
                margin: 0px auto;
            }
            
            .top_l {
                margin-left: 20px;
            }
        </style>
        <script type="text/javascript">
            function moveIn(ele){
                ele.style.border = "3px solid #FFCFB1";
            }
            
            function moveOut(ele){
                ele.style.border = "3px solid #ffffff";
            }
           
        </script>
        
    </head>
    <body>
        <!--头 -->
        <iframe frameborder="0" src="zw/top.jsp" width="100%" hight="110px" name="top" scrolling="no"></iframe>
   
        <div class="center">
            <div class="col-main">
                <div class="main-wrap">
                    <div class="user-address">
                        <!--标题 -->
                        <div class="am-cf am-padding">
                            <div class="am-fl am-cf">
                                <strong class="am-text-danger am-text-lg">我的购物车</strong>
                            </div>
                        </div>
                        <hr/>
                        <div class="clear">
                        </div>
                        <!--例子-->
                        <c:forEach var="cartList" items="${page.pageList }" varStatus="status">
                         <div class="showcart">
                            <table width="95%" cellpadding="0" cellspacing="0">
                                <tr>
                            		<td colspan="7" bgcolor="#efeae5" > <strong class="am-text-danger am-text-lg">${cartList[0].commodity.farmer.nickname }</strong></td>
                            	</tr>
                                <tr id="cartTableHead" align="center" bgcolor="#efeae5">
                                    <td align="left" >
                                        <input type="checkbox" id="selectAll${status.count }" checked="checked"/>
                                        <label for="selectAll${status.count }">
                                       		     全选
                                        </label>
                                    </td>
                                    <td colspan="2">
                                  		      商品名称
                                    </td>
                                    <td>
                                	        单价
                                    </td>
                                    <td>
                                     	   数量
                                    </td>
                                    <td>
                                     	   小计
                                    </td>
                                    <td>
                             		           操作
                                    </td>
                                </tr>
                                <c:forEach var="cart" items="${cartList }">
	                                <tr align="center">
	                                    <td align="left">
	                                        <input value="12345" id="${cart.id }" type="checkbox" name="checkboxBtn${status.count }" checked="checked"/>
	                                    </td>
	                                    <td align="left" width="70px">
	                                        <a class="linkImage" href=""><img border="0" width="54" align="top" src="zw/${cart.commodity.image }"/></a>
	                                    </td>
	                                    <td align="left" width="400px">
	                                        <a href=""><span>${cart.commodity.name }</span></a>
	                                    </td>
	                                    <td>
	                                        <span>&yen;<span class="currPrice" id="12345CurrPrice${status.count }">${cart.commodity.price }</span></span>
	                                    </td>
	                                    <td>
	                                        <a class="jian" id="12345Jian${status.count }"></a>
	                                        <input class="quantity" readonly="readonly" id="12345Quantity${status.count }" type="text" value="1"/>
	                                        <a class="jia" id="12345Jia${status.count }"></a>
	                                    </td>
	                                    <td width="100px">
	                                        <span class="price_n">&yen;<span class="subTotal" id="12345Subtotal${status.count }">${cart.subtotal }</span></span>
	                                    </td>
	                                    <td>
	                                        <a href="<c:url value='/cart/delete.action?cartIds=${cart.id }'/>">删除</a>
	                                    </td>
	                                </tr>
	                             
                                </c:forEach>
	                                <tr>
	                                    <td colspan="4" class="tdBatchDelete">
	                                        <a href="<c:url value='/cart/delete.action?'/>" onclick="deleteCarts(this,${status.count })">批量删除</a>
	                                    </td>
	                                    <td colspan="3" align="right" class="tdTotal">
	                                        <span>总计：</span>
	                                        <span class="price_t">&yen;<span id="total${status.count }"></span></span>
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td colspan="7" align="right">
	                                        <a href="<c:url value='/jsps/cart/showitem.jsp'/>" id="jiesuan${status.count }" class="jiesuan"></a>
	                                    </td>
	                                </tr>
                            </table>
                            <form id="form${status.count }" action="<c:url value='/jsps/cart/showitem.jsp'/>" method="post">
                                <input type="hidden" name="cartItemIds" id="cartItemIds"/>
                            </form>
                        </div>
                       </c:forEach>
                    </div>
                    <div class="clear">
                    </div>
                     <!-- 分页 -->
                <ul class="am-pagination am-pagination-right">
							
							  <c:if test="${page.currPage-1 == 0 }">
							  <li class="am-disabled"><a href="#">&laquo;</a></li>
							  </c:if>
							  <c:if test="${page.currPage-1 != 0 }">
							  <li><a href="${page.url }&currPage=${page.currPage-1 }">&laquo;</a></li>
							  </c:if>
							  
							  <c:forEach begin="0" end="${page.totalPage-1}"  varStatus="status">
								<c:if test="${status.count == page.currPage }">
								  <li class="am-active"><a href="${page.url }&currPage=${status.count }">${status.count }</a></li>
								</c:if>
								<c:if test="${status.count != page.currPage }">
								<li><a href="${page.url }&currPage=${status.count }">${status.count }</a></li>
								</c:if>
								</c:forEach>
								
								<c:if test="${page.currPage == page.totalPage }">
								  <li class="am-disabled"><a href="#">&raquo;</a></li>
								</c:if>
								<c:if test="${page.currPage != page.totalPage }">
								  <li><a href="${page.url }&currPage=${page.currPage+1 }">&raquo;</a></li>
								</c:if>
								
							</ul>
                </div>
               
                <!--底部-->
            </div>
            <aside class="menu">
                <ul>
                    <li class="person active">
                        <a href="index.html"><i class="am-icon-user"></i>个人中心</a>
                    </li>
                    <li class="person">
                        <p>
                            <i class="am-icon-newspaper-o"></i>个人资料
                        </p>
                        <ul>
                            <li>
                                <a href="information.html">个人信息</a>
                            </li>
                            <li>
                                <a href="address.html">地址管理</a>
                            </li>
                        </ul>
                    </li>
                    <li class="person">
                        <p>
                            <i class="am-icon-balance-scale"></i>我的交易
                        </p>
                        <ul>
                            <li>
                                <a href="order.html">订单管理</a>
                            </li>
                            <li>
                                <a href="change.html">退款售后</a>
                            </li>
                            <li>
                                <a href="comment.html">评价商品</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </aside>
        </div>
    </body>
</html>
