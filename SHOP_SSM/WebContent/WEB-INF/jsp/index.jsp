<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>乐淘商城</title>
<link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>

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
	<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	</div>
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

<div class="container index">
		

		<div class="span24">
			<div id="hotProduct" class="hotProduct clearfix">
					<div class="title">
						<strong>热门商品</strong>
					</div>
					<ul class="tab">
							<li class="current">
								<a href="./蔬菜分类.htm?tagIds=1" target="_blank"></a>
							</li>
							<li>
								<a  target="_blank"></a>
							</li>
							<li>
								<a target="_blank"></a>
							</li>
					</ul>
						<ul class="tabContent" style="display: block;">
							<c:forEach items="${productHots }" var="ph">
								<li>
									<a href="${pageContext.request.contextPath }/product/productById/${ph.pid}"><img src="${pageContext.request.contextPath}/image/${ph.image}" title="${pn.pname }" style="display: block;"></a>
								</li>
							</c:forEach>
						</ul>
			</div>
		</div>
		<div class="span24">
			<div id="newProduct" class="newProduct clearfix">
					<div class="title">
						<strong>最新商品</strong>
						<a  target="_blank"></a>
					</div>
					<ul class="tab">
							<li class="current">
								<a href="./蔬菜分类.htm?tagIds=2" target="_blank"></a>
							</li>
							<li>
								<a  target="_blank"></a>
							</li>
							<li>
								<a target="_blank"></a>
							</li>
					</ul>
					<ul class="tabContent" style="display: block;">
						<c:forEach items="${productNews}" var="pn">
							<li>
								<a href="${pageContext.request.contextPath }/product/productById/${pn.pid}"><img src="${pageContext.request.contextPath}/image/${pn.image}" title="${pn.pname }" style="display: block;"></a>
							</li>
						</c:forEach>
					</ul>
			</div>
		</div>
		<div class="span24">
			<div id="newProduct" class="newProduct clearfix">
					<div class="title">
						<strong>为你推选</strong>
						<a  target="_blank"></a>
					</div>
					<ul class="tab">
							<li class="current">
								<a href="./蔬菜分类.htm?tagIds=2" target="_blank"></a>
							</li>
							<li>
								<a  target="_blank"></a>
							</li>
							<li>
								<a target="_blank"></a>
							</li>
					</ul>
					<ul class="tabContent" style="display: block;">
						<c:forEach items="${productLikes}" var="pl">
							<li>
								<a href="${pageContext.request.contextPath }/product/productById/${pl.pid}"><img src="${pageContext.request.contextPath}/image/${pl.image}" title="${pl.pname }" style="display: block;"></a>
							</li>
						</c:forEach>
					</ul>
			</div>
		</div>
		<div class="span24">
			<div class="friendLink">
				<dl>
					<dt>新手指南</dt>
							<dd>
								<a  target="_blank">支付方式</a>
								|
							</dd>
							<dd>
								<a  target="_blank">配送方式</a>
								|
							</dd>
							<dd>
								<a  target="_blank">售后服务</a>
								|
							</dd>
							<dd>
								<a  target="_blank">购物帮助</a>
								|
							</dd>
							<dd>
								<a  target="_blank">蔬菜卡</a>
								|
							</dd>
							<dd>
								<a  target="_blank">礼品卡</a>
								|
							</dd>
							<dd>
								<a target="_blank">银联卡</a>
								|
							</dd>
							<dd>
								<a  target="_blank">亿家卡</a>
								|
							</dd>
							
					<dd class="more">
						<a >更多</a>
					</dd>
				</dl>
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
						<a>关于我们</a>
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
						<a target="_blank">配送方式</a>
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
</body>
</html>