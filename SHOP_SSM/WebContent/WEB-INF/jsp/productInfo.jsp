<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>乐淘商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css">


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
					<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/header.jpg" alt="正品保障" title="正品保障" height="50" width="320">
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
<div class="container productContent">
		<div class="span6">
			<div class="hotProductCategory">
				<dl>
					<!-- 遍历一级二级分类 -->
					<c:forEach items="${categoryList}" var="cg">
						<dt>
							<a href="${pageContext.request.contextPath}/image/">${cg.cname}</a>
						</dt>
						<c:forEach items="${cg.categorySecond}" var="cs">
							<dd>
								<a href="${pageContext.request.contextPath}/product/findProductByCsid/${cs.csid}?pageNumber=1">${cs.csname}</a>
							</dd>
						</c:forEach>
					</c:forEach>
				</dl>
			</div>
		</div>
	
		<!-- 商品详情 -->
		<div class="span18 last">
			<div class="productImage">
					<a title="" style="outline-style: none; text-decoration: none;" id="zoom" href="http://image/r___________renleipic_01/bigPic1ea8f1c9-8b8e-4262-8ca9-690912434692.jpg" rel="gallery">
						<div class="zoomPad"><img style="opacity: 1;" title="" class="medium" src="${pageContext.request.contextPath }/image/${productBean.image}">
						<div style="display: block; top: 0px; left: 162px; width: 0px; height: 0px; position: absolute; border-width: 1px;" class="zoomPup"></div>
						<div style="position: absolute; z-index: 5001; left: 312px; top: 0px; display: block;" class="zoomWindow"><div style="width: 368px;" class="zoomWrapper">
						<div style="width: 100%; position: absolute; display: none;" class="zoomWrapperTitle"></div><div style="width: 0%; height: 0px;" class="zoomWrapperImage">
						<img src="%E5%B0%9A%E9%83%BD%E6%AF%94%E6%8B%89%E5%A5%B3%E8%A3%852013%E5%A4%8F%E8%A3%85%E6%96%B0%E6%AC%BE%E8%95%BE%E4%B8%9D%E8%BF%9E%E8%A1%A3%E8%A3%99%20%E9%9F%A9%E7%89%88%E4%BF%AE%E8%BA%AB%E9%9B%AA%E7%BA%BA%E6%89%93%E5%BA%95%E8%A3%99%E5%AD%90%20%E6%98%A5%E6%AC%BE%20-%20Powered%20By%20Mango%20Team_files/6d53c211-2325-41ed-8696-d8fbceb1c199-large.jpg" style="position: absolute; border: 0px none; display: block; left: -432px; top: 0px;"></div>
						</div></div><div style="visibility: hidden; top: 129.5px; left: 106px; position: absolute;" class="zoomPreload">Loading zoom</div></div>
					</a>
			</div>

			<div class="name">${productBean.pname }</div>
			<div class="sn">
				<div>编号：${productBean.pid }</div>
			</div>
			<div class="info">
				<dl>
					<dt>乐 淘 价:</dt>
					<dd>
						<strong>￥：${productBean.shop_price }</strong>
							市 场 价：
							<del>￥${productBean.market_price }</del>
					</dd>
				</dl>
					<dl>
						<dt>促销:</dt>
						<dd>
								<a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
						</dd>
					</dl>
					<dl>
						<dt>    </dt>
						<dd>
							<span>    </span>
						</dd>
					</dl>
			</div>
				<div class="action">
					<form action="${pageContext.request.contextPath}/cart/addCart" method="post">
						<input type="hidden" name="pid" value="${productBean.pid }"/>
						<dl class="quantity">
							<dt>购买数量:</dt>
							<dd>
								<input id="quantity" name="quantity" value="1" maxlength="4" onpaste="return false;" type="text">
								<div>
									<span id="increase" class="increase">&nbsp;</span>
									<span id="decrease" class="decrease">&nbsp;</span>
								</div>
							</dd>
							<dd>
								件
							</dd>
						</dl>
						<div class="buy">
							<input id="addCart" class="addCart" value="加入购物车" type="submit">
						</div>
					</form>
				</div>
			<div id="bar" class="bar">
				<ul>
						<li id="introductionTab">
							<a href="#introduction">商品介绍</a>
						</li>
						
				</ul>
			</div>
				
				<div id="introduction" name="introduction" class="introduction">
					<div class="title">
						<strong>商品介绍</strong>
					</div>
					<p>${productBean.pdesc }</p>
				</div>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
</div>
</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
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
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a>SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>