<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>订单页面</title>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/product.css"
	rel="stylesheet" type="text/css" />

</head>
<body>

	<div class="container header">
		<div class="span5">
			<div class="logo">
				<a href="./网上商城/index.htm">
					<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.png" alt="乐淘商城" width="170" height="50p"/>
				</a>
			</div>
		</div>
		<div class="span9">
			<div class="headerAd">
				<img src="${pageContext.request.contextPath}/image/header.jpg"
					width="320" height="50" alt="正品保障" title="正品保障" />
			</div>
		</div>

		<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<c:if test="${empty user}">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="${pageContext.request.contextPath }/user/toLogin">登录</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${pageContext.request.contextPath }/user/toRegister">注册</a>|
					</li>
				</c:if>
				<c:if test="${not empty user}">
					<li >
						<font color="red" size="3">欢迎您！${sessionScope.user.username }</font>
					</li>
					<li>
						<a href="${pageContext.request.contextPath }/user/quit">[退出]</a>|
					</li>
					<li>
						<a href="${pageContext.request.contextPath }/order/findOrderById/${user.uid}?pageNumber=1">我的订单</a>
					</li>
				</c:if>
						<li>
							<a href="${pageContext.request.contextPath }/cart/showCart">购物车</a>
						</li>
						<li>
							<a>会员中心</a>
							|
						</li>
						
			</ul>
		</div>
			<div class="phone">
				当前在线人数:
				<strong>${online.size() }</strong>
				本站累计访问:
				<strong>${countWebSite}</strong>
			</div>
	</div>
		<div class="span24">
			<ul class="mainNav">
				<li>
					<a href="${pageContext.request.contextPath }/">首页</a>
					|
				</li>
				<!-- 遍历一级分类 -->
				<c:forEach items="${categoryList }" var="category">
						<li>
							<a href="${pageContext.request.contextPath }/product/findProductByCid/${category.cid }">${category.cname }</a>
							|
						</li>
				</c:forEach>
			</ul>
		</div>
		
	</div>

	<div class="container cart">

		<div class="span24">

			<div class="step step1">
				<ul>
					<c:if test="${not empty orderPageInfo.list}">
						<li class="current"></li>
						<li>我的订单</li>
					</c:if>
					<c:if test="${empty orderPageInfo.list}">
					
						<h2 style="color:red">你还没有订单,快去下单吧 !</h2>
					</c:if>
				</ul>
			</div>


			<table>
				<tbody>
				<c:forEach items="${orderPageInfo.list}" var="ol">
					<tr>
						<th colspan="5">订单编号:${ol.oid}&nbsp;&nbsp;&nbsp;&nbsp;订单金额:<font
							color="red">${ol.total }
						</font>
						&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">
							<c:if test="${ol.state==1}">
								<a href="${pageContext.request.contextPath }/order/payMendOrder/${ol.oid}">去付款</a>
							</c:if>
							<c:if test="${ol.state==2}">
								待发货
							</c:if>
							<c:if test="${ol.state==3}">
								<a href="${pageContext.request.contextPath }/order/sureOrder/${ol.oid}">确认收货</a>
							</c:if>
							<c:if test="${ol.state==4}">
								交易成功
							</c:if>
						</font>
						</th>
					</tr>
						<tr>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<c:forEach items="${ol.orderItems}" var="oi">
							<tr>
								<td width="60"><img
									src="${ pageContext.request.contextPath }/image/${oi.product.image}" />
								</td>
								<td>${oi.product.pname }</td>
								<td>${oi.product.shop_price}</td>
								<td class="quantity" width="60">${oi.count }</td>
								<td width="140"><span class="subtotal">￥${oi.subtotal }
								</span></td>
							</tr>
						</c:forEach>
					</c:forEach>
					<tr>
						 <th colspan="5">
								<div class="pagination">
										<!-- 第一页 -->
										<a class="firstPage" href="${pageContext.request.contextPath }/order/findOrderById/${user.uid}?pageNumber=1"/>&nbsp;</a>
										<!-- 判断是否为首页 -->
										<c:if test="${!orderPageInfo.isFirstPage}">
											<a class="previousPage" href="${pageContext.request.contextPath }/order/findOrderById/${user.uid}?pageNumber=${orderPageInfo.pageNum-1}">&nbsp;</a>
										</c:if>
										<c:if test="${orderPageInfo.isFirstPage}">
											<span class="previousPage"><a href="#">&nbsp;</a></span>
										</c:if>
										
											<!-- 循环遍历中间页 -->
												<!-- 当不足3页 -->
												<c:choose>
													<c:when test="${orderPageInfo.pages<=3}">
														<c:set var="start"  value="1"></c:set>
														<c:set var="end" value="${orderPageInfo.pages}"></c:set>
													</c:when>
													<c:otherwise>
														<!-- 正常显示三条 -->
														<c:set var="start" value="${orderPageInfo.pageNum-1}"></c:set>
														<c:set var="end" value="${orderPageInfo.pageNum+1}"></c:set>
														<!-- 头溢出 -->
														<c:if test="${start<1 }">
															<c:set var="start" value="1"></c:set>
															<c:set var="end" value="3"></c:set>
														</c:if>
														<!-- 尾溢出 -->
														<c:if test="${end>orderPageInfo.pages }">
															<c:set var="start" value="${orderPageInfo.pages-2}"></c:set>
															<c:set var="end" value="${orderPageInfo.pages}"></c:set>
														</c:if>
													</c:otherwise>
												</c:choose>
											<c:forEach begin="${start}" end="${end}" var="i">
												 <c:if test="${orderPageInfo.pageNum!=i }">
												 	<a href="${pageContext.request.contextPath }/order/findOrderById/${user.uid}?pageNumber=${i }">${i}</a>
												 </c:if>
												 <c:if test="${orderPageInfo.pageNum==i }">
												 	<a href="#">${i}</a>
												 </c:if>
											</c:forEach>
										<!-- 判断是否为尾页 -->
										<c:if test="${orderPageInfo.pageNum!=orderPageInfo.pages}">
											<a class="nextPage" href="${pageContext.request.contextPath }/order/findOrderById/${user.uid}?pageNumber=${orderPageInfo.pageNum+1}">&nbsp;</a>
										</c:if>
										<c:if test="${orderPageInfo.pageNum==orderPageInfo.pages}">
											<span class="nextPage"><a href="#">&nbsp;</a></span>
										</c:if>
										<!-- 最后一页 -->
										<a class="lastPage" href="${pageContext.request.contextPath }/order/findOrderById/${user.uid}?pageNumber=${orderPageInfo.pages}">&nbsp;</a>
								</div>
						</th> 
					</tr>
				</tbody>
			</table>


		</div>

	</div>
	<div class="container footer">
		<div class="span24">
			<div class="footerAd">
				<img src="${pageContext.request.contextPath }/image/r___________renleipic_01/footer.jpg" alt="我们的优势"
					title="我们的优势" height="52" width="950">
			</div>
		</div>
		<div class="span24">
			<ul class="bottomNav">
				<li><a href="#">关于我们</a> |</li>
				<li><a href="#">联系我们</a> |</li>
				<li><a href="#">诚聘英才</a> |</li>
				<li><a href="#">法律声明</a> |</li>
				<li><a>友情链接</a> |</li>
				<li><a target="_blank">支付方式</a> |</li>
				<li><a target="_blank">配送方式</a> |</li>
				<li><a>SHOP++官网</a> |</li>
				<li><a>SHOP++论坛</a></li>
			</ul>
		</div>
		<div class="span24">
			<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
		</div>
	</div>
</body>
</html>