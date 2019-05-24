<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>购物车</title>
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
			
			.container .row div {
				/* position:relative;
	 float:left; */
			}
			
			font {
				color: #3164af;
				font-size: 18px;
				font-weight: normal;
				padding: 0 10px;
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

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong style="font-size:16px;margin:5px 0;">购物车详情</strong>
					<table class="table table-bordered">
						<tbody>
					<c:if test="${not empty cartBean}">
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
								<th>操作</th>
							</tr>
					</c:if>
							<!-- 求总价 -->
							<c:set scope="page" value="0" var="total"></c:set>
							<c:forEach items="${cartBean}" var="cart">
								<tr class="active">
									<td width="60" width="40%">
										<input type="hidden" name="id" value="22">
										<img src="${pageContext.request.contextPath}/${cart.product.pimage}" width="70" height="60">
									</td>
									<td width="30%">
										<a target="_blank"> ${cart.product.pname}</a>
									</td>
									<td width="20%">
										￥${cart.product.shopPrice}
									</td>
									<td width="10%">
										<input type="text" name="quantity" value="${cart.quantity}" maxlength="4" size="10" onblur="updateCart(this.value,${cart.product.pid})">
									</td>
									<td width="15%">
										<span class="subtotal">￥${cart.subTotal}</span>
									</td>
									<td>
										<a href="${pageContext.request.contextPath }/cart/removeCart/${cart.product.pid}" class="delete">删除</a>
									</td>
									<!-- 求总价 -->
									<c:set scope="page" value="${total+cart.subTotal}" var="total"></c:set>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		<form method="post" action="${pageContext.request.contextPath }/order/saveOrder">
			<div style="margin-right:130px;">
				<div style="text-align:right;">
					<em style="color:#ff6600;">
				登录后确认是否享有优惠&nbsp;&nbsp;
			</em> 赠送积分: <em style="color:#ff6600;">596</em>&nbsp; 商品金额: <strong style="color:#ff6600;">￥${total}元</strong>
				</div>
				<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
					<a href="${pageContext.request.contextPath}/cart/clearCart" id="clear" class="clear">清空购物车</a>
					<a href="${pageContext.request.contextPath}/jsp/order_info.jsp">
						<input type="hidden" name="total" value="${total }">
						<%--提交表单 --%>
						<input type="submit" width="100" value="提交订单"  style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						height:35px;width:100px;color:white;">
					</a>
				</div>
			</div>
		</form>

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
	<script type="text/javascript">
		function updateCart(quantity,pid){
			//发送ajax请求
			$.ajax({
				url:"${pageContext.request.contextPath}/cart/updateCart/"+pid+"/"+quantity,
				type:"GET",
				data:null,
				success:function(data){
						location.href = "${pageContext.request.contextPath}/cart/showCart";
					}
			});
		}
	</script>
	</body>
</html>