<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<base href="<%=basePath%>"/>
		<title>搜索页面</title>

		<link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" ></link>
		<link href="AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css" ></link>
		<link href="basic/css/demo.css" rel="stylesheet" type="text/css" ></link>
		<link href="css/seastyle.css" rel="stylesheet" type="text/css" />
		<link href="zw/css/nav.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="zw/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="zw/js/jquery.js"></script>
		<script type="text/javascript" src="zw/js/ajaxNav.js"></script> 
	    <script type="text/javascript" src="zw/js/nav.js"></script>
	   
      
	    <style type="text/css">
       
        .top {
            background-color: #eee;
            width: 100%;
		    font:14px "Arial Black", arial, serif;
            line-height: 30px;
            margin: 0px auto;
        }
		.top a{
			color:black;
		}
        .top a:hover{color:red;}
		.top_l{
			margin-left:50px;
		}
		.top_r{
			margin-left:850px;
		}
		.searchBar {
			background-color:#fff;
			width:1200px;
			margin:0 auto;
		} 
		.searchBar input {
		  margin-top : 22px;
		  margin-left:300px;
		  height:40px;
		  width: 500px;
		  font-size : 14pt;
		  border: solid 2px;
		  border-color:#fe4365;
		  display:block;
		  float:left;
		}
		.searchBar img{
		   height:40px;
		   width:75px;
		   margin-top:22px;
		   margin-left:0px;
		}
		.search {
			margin:0 auto;
		}
		
    </style>
	</head>

	<body>

		

    <iframe frameborder="0" src="/AgricultureSale0.1/zw/top.jsp" width="100%" hight="110px" name="top" scrolling="no"></iframe>
    <!-- 导航栏 -->
	<div class="clear"></div>
        <div class="nav" id="mainNav">
            <ul id="nav_list" class="list">
               
            </ul>
     </div>
			

        <div class="search">
			<div class="search-list">
				
							<div class="search-content">
								<div class="sort">
									<li class="first"><a title="综合">综合排序</a></li>
									<li><a title="销量">销量排序</a></li>
									<li><a title="价格">价格优先</a></li>
									<li class="big"><a title="评价" href="#">评价为主</a></li>
								</div>
								<div class="clear"></div>

								<ul class="am-avg-sm-2 am-avg-md-3 am-avg-lg-4 boxes">
								   <c:forEach var="commodity" items="${page.pageList }">
                                      <li>
							     		<div class='i-pic limit'>
							     		     <a href="commodity/findByCommId.action?commodityId=${commodity.id }">
			  								  <img src='/AgricultureSale0.1/zw/${commodity.image }'/>
			  								 </a>    
											 <p class='title fl'> <a href="commodity/findById.action?commodityId=${commodity.id }">${commodity.name }</a></p>													
											 <p class='price fl'>
											<b>¥</b>
											<strong>${commodity.price }</strong>
										   </p>
										 <input id='commid' name='id' type='hidden' value='${commodity.id }'></input>
									  </div>
								    </li>
              					   </c:forEach>
								</ul>
								<!--分页 -->
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
							
							<div class="search-side">

								<div class="side-title">
									每日推荐
								</div>

								<li>
									<div class="i-pic check">
										<img src="../images/cp.jpg" />
										<p class="check-title">萨拉米 1+1小鸡腿</p>
										<p class="price fl">
											<b>¥</b>
											<strong>29.90</strong>
										</p>
										<p class="number fl">
											销量<span>1110</span>
										</p>
									</div>
								</li>
								<li>
									<div class="i-pic check">
										<img src="../images/cp2.jpg" />
										<p class="check-title">ZEK 原味海苔</p>
										<p class="price fl">
											<b>¥</b>
											<strong>8.90</strong>
										</p>
										<p class="number fl">
											销量<span>1110</span>
										</p>
									</div>
								</li>
								<li>
									<div class="i-pic check">
										<img src="../images/cp.jpg" />
										<p class="check-title">萨拉米 1+1小鸡腿</p>
										<p class="price fl">
											<b>¥</b>
											<strong>29.90</strong>
										</p>
										<p class="number fl">
											销量<span>1110</span>
										</p>
									</div>
								</li>
								<li>
									<div class="i-pic check">
										<img src="../images/cp.jpg" />
										<p class="check-title">萨拉米 1+1小鸡腿</p>
										<p class="price fl">
											<b>¥</b>
											<strong>29.90</strong>
										</p>
										<p class="number fl">
											销量<span>1110</span>
										</p>
									</div>
								</li>

							</div>
							<div class="clear"></div>
							
			</div>
		</div>
				
		<script>
			window.jQuery || document.write('<script src="basic/js/jquery-1.9.min.js"><\/script>');
		</script>
		<script type="text/javascript" src="../basic/js/quick_links.js"></script>

<div class="theme-popover-mask"></div>
  
	</body>

</html>