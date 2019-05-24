<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>

			<!--
            	描述：菜单栏
            -->
			<div class="container-fluid">
				<%@include file="header.jsp" %>
			</div>


		<div class="container">
			<div class="row">
				<div class="step step1">
					<ul>
						<c:if test="${empty pageInfoOrder.list}">
						
							<h2 style="color:red">你还没有订单,快去下单吧 !</h2>
						</c:if>
					</ul>
				</div>

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong>我的订单</strong>
					<table class="table table-bordered">
						<tbody>
						<!-- 遍历订单 -->
						<c:forEach items="${pageInfoOrder.list }" var="ol">
								<tr>
									<th colspan="5">订单编号:${ol.oid}&nbsp;&nbsp;&nbsp;&nbsp;订单金额:<font
										color="red">${ol.total }
									</font>
									&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">
										<c:if test="${ol.state==0}">
											<a href="${pageContext.request.contextPath }/order/findOrderByOid/${ol.oid}">去付款</a>
										</c:if>
										<c:if test="${ol.state==1}">
											待发货
										</c:if>
										<c:if test="${ol.state==2}">
											<a href="${pageContext.request.contextPath }/order/sureOrder/${ol.oid}">确认收货</a>
										</c:if>
										<c:if test="${ol.state==3}">
											交易成功
										</c:if>
									</font>
									</th>
								</tr>
							
								<tr class="warning">
									<th>图片</th>
									<th>商品</th>
									<th>价格</th>
									<th>数量</th>
									<th>小计</th>
								</tr>
								<!-- 遍历订单项 -->
								<c:forEach items="${ol.orderitems }" var="oi">
									<tr class="active">
										<td width="60" width="40%">
											<input type="hidden" name="id" value="22">
											<img src="${pageContext.request.contextPath}/${oi.product.pimage}" width="70" height="60">
										</td>
										<td width="30%">
											<a target="_blank">${oi.product.pname }</a>
										</td>
										<td width="20%">
											￥${oi.product.shopPrice }
										</td>
										<td width="10%">
											${oi.quantity }
										</td>
										<td width="15%">
											<span class="subtotal">￥${oi.total }</span>
										</td>
									</tr>
								</c:forEach>
						</c:forEach>
					</tbody>
						
						
					</table>
				</div>
			</div>
			<!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:50px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
				<!-- 判断是否是第一页 -->
				<c:if test="${pageInfoOrder.pageNum==1 }">
					<li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				</c:if>
					<c:if test="${pageInfoOrder.pageNum!=1 }">
					<li><a href="${pageContext.request.contextPath}/order/findOrderByUid/${user.uid}?pageNumber=${pageInfoOrder.pageNum-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				</c:if>
				<c:forEach begin="1" end="${pageInfoOrder.pages }" var="n">
					<!-- 判断是否是当前页 -->
					<c:if test="${pageInfoOrder.pageNum==n}">
						<li class="active"><a href="#">${n }</a></li>
					</c:if>
					<c:if test="${pageInfoOrder.pageNum!=n}">
						<li class=""><a href="${pageContext.request.contextPath}/order/findOrderByUid/${user.uid}?pageNumber=${n}">${n }</a></li>
					</c:if>
				</c:forEach>
				
					<!-- 判断是否是最后一页-->
				<c:if test="${pageInfoOrder.pageNum==pageInfoOrder.pages }">
					<li class="disabled"><a href="#"  aria-label="Next"><span aria-hidden="true">&laquo;</span></a></li>
				</c:if>
				<c:if test="${pageInfoOrder.pageNum!=pageInfoOrder.pages }">
					<li><a href="${pageContext.request.contextPath}/order/findOrderByUid/${user.uid}?pageNumber=${pageInfoOrder.pageNum+1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
				</c:if>
			</ul>
		</div>
		<!-- 分页结束=======================-->
		</div>

		<div style="margin-top:50px;">
			<img src="${pageContext.request.contextPath}/img/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a href="${pageContext.request.contextPath}/jsp/info.jsp">关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2005-2016 传智商城 版权所有
		</div>
	</body>

</html>