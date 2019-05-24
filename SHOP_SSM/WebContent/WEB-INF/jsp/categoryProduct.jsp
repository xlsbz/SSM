<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乐淘商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>

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
<div class="container productList">
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
		
		<!-- 分类商品 -->
		<div class="span18 last">
			<form id="productForm" action="${pageContext.request.contextPath}/image/蔬菜 - Powered By Mango Team.htm" method="get">
				<input type="hidden" id="brandId" name="brandId" value="">
				<input type="hidden" id="promotionId" name="promotionId" value="">
				<input type="hidden" id="orderType" name="orderType" value="">
				<input type="hidden" id="pageNumber" name="pageNumber" value="1">
				<input type="hidden" id="pageSize" name="pageSize" value="20">
					
				<!-- 如果一级分类不为空就显示一级分类的 分页 -->
				<c:if test="${not empty pageInfoCid}">
					<div id="result" class="result table clearfix">
						<ul>
							<!-- 遍历商品 -->
							<c:forEach items="${pageInfoCid.list }" var="product">
								<li>
									<a href="${pageContext.request.contextPath }/product/productById/${product.pid}">
										<img src="${pageContext.request.contextPath}/image/${product.image}" width="170" height="170" " style="display: inline-block;">
										<span style='color:green'>
										  	${product.pname }
										</span>
										<span class="price">
											商城价： ￥${product.shop_price}
										</span>
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="pagination">
						<span >
							<a class="firstPage" href="${pageContext.request.contextPath }/product/findProductByCid/${cid}?pageNumber=1">&nbsp;</a>
						</span>
							<a class="previousPage" href="${pageContext.request.contextPath }/product/findProductByCid/${cid}?pageNumber=${pageInfoCid.pageNum-1}">&nbsp;</a>
							<c:forEach begin="1" end="${pageInfoCid.pages}" var="n">
								<a href="${pageContext.request.contextPath }/product/findProductByCid/${cid}?pageNumber=${n}">${n }</a>
							</c:forEach>
						<a class="nextPage" href="${pageContext.request.contextPath }/product/findProductByCid/${cid}?pageNumber=${pageInfoCid.pageNum+1}">&nbsp;</a>
						<a class="lastPage" href="${pageContext.request.contextPath }/product/findProductByCid/${cid}?pageNumber=${pageInfoCid.lastPage}">&nbsp;</a>
					</div>
				</c:if>
				<!-- 否则就选择二级分类的分页 -->
				<c:if test="${not empty pageInfoCsid}">
					<div id="result" class="result table clearfix">
						<ul>
							<!-- 遍历商品 -->
							<c:forEach items="${pageInfoCsid.list }" var="product">
								<li>
									<a href="${pageContext.request.contextPath }/product/productById/${product.pid}">
										<img src="${pageContext.request.contextPath}/image/${product.image}" width="170" height="170" " style="display: inline-block;">
										<span style='color:green'>
										  	${product.pname }
										</span>
										<span class="price">
											商城价1： ￥${product.shop_price}
										</span>
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="pagination">
						<span >
							<a class="firstPage" href="${pageContext.request.contextPath }/product/findProductByCsid/${csid}?pageNumber=1">&nbsp;</a>
						</span>
							<a class="previousPage" href="${pageContext.request.contextPath }/product/findProductByCsid/${csid}?pageNumber=${pageInfoCsid.pageNum-1}">&nbsp;</a>
							<c:forEach begin="1" end="${pageInfoCsid.pages}" var="n">
								<a href="${pageContext.request.contextPath }/product/findProductByCsid/${csid}?pageNumber=${n}">${n }</a>
							</c:forEach>
						<a class="nextPage" href="${pageContext.request.contextPath }/product/findProductByCsid/${csid}?pageNumber=${pageInfoCsid.pageNum+1}">&nbsp;</a>
						<a class="lastPage" href="${pageContext.request.contextPath }/product/findProductByCsid/${csid}?pageNumber=${pageInfoCsid.lastPage}">&nbsp;</a>
					</div>
				</c:if>
			</form>
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
						<a >诚聘英才</a>
						|
					</li>
					<li>
						<a >法律声明</a>
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
						<a >官网</a>
						|
					</li>
					<li>
						<a >论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright©2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>