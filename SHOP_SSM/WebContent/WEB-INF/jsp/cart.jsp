<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>

<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css">


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
					<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
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
						<a href="${pageContext.request.contextPath }/">我的订单</a>
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
			</div>
				<table>
					<tbody>
					<h2 style="color:red">${msg }</h2>
					<c:if test="${not empty cart.cartAllItems}">
						<tr>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
							<th>操作</th>
						</tr>
					</c:if>
					<c:if test="${empty cart.cartAllItems }">
						<h1 style="color:red">购物车空空如也,快去购物吧!</h1>
					</c:if>
						<c:forEach items="${cart.cartAllItems }" var="ci">
							<tr>
								<td width="60">
									<input type="hidden" name="id" value="22">
									<img src="${pageContext.request.contextPath}/image/${ci.product.image }">
								</td>
								<td>
									<a target="_blank">${ci.product.pname }</a>
								</td>
								<td>
									￥${ci.product.shop_price }
								</td>
								<td class="quantity" width="60">
									${ci.num}
								</td>
								<td width="140">
									<span class="subtotal">￥${ci.subTotal }</span>
								</td>
								<td>
									<a href="${pageContext.request.contextPath }/cart/removeCart/${ci.product.pid}" class="delete">删除</a>
								</td>
							</tr>
						</c:forEach>
				</tbody></table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion"></em>
							<em>
								登录后确认是否享有优惠
							</em>
					赠送积分: <em id="effectivePoint">${cart.total}</em>
					商品金额: <strong id="effectivePrice">￥${cart.total }元</strong>
				</div>
				<div class="bottom">
					<a href="#" id="clear" onclick="clearCart()" class="clear">清空购物车</a>
					<a href="${pageContext.request.contextPath }/order/createOrder" id="submit" class="submit">提交订单</a>
				</div>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
				<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a>招贤纳士</a>
						|
					</li>
					<li>
						<a>法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a>服务声明</a>
						|
					</li>
					<li>
						<a>广告声明</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
		
<script>
	function clearCart(){
		if(confirm("确定要清空购物车吗?")){
			location.href = "${pageContext.request.contextPath }/cart/clearCart";
		}
	}
</script>
</body>
</html>