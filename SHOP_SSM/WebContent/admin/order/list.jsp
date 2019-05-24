<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
	</HEAD>
	<script type="text/javascript">
	//创建ajax
	
		function orderAja(oid){
			//获取按钮
			var btn = document.getElementById("but"+oid).value;
			//获取div
			var content = document.getElementById("div"+oid);
			if(btn=="点击查看"){
				document.getElementById("but"+oid).value  = "关闭详情";
				//发送ajax请求
				$.ajax({
					url:"${pageContext.request.contextPath}/admin/findOrderItems/"+oid,
					type:"GET",
					data:null,
					success:function(data){
						location.href="${pageContext.request.contextPath}/admin/findOrderItems/"+oid;
					}
				});
			}else{
				content.innerHTML = "";
				document.getElementById("but"+oid).value="点击查看";
			}
			
		}
		
	</script>
	<body>
	
		<br>
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>订单列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="3%">
										序号
									</td>
									<td align="center" width="5%">
										收货人
									</td>
									<td align="center" width="5%">
										收货地址
									</td>
									<td align="center" width="5%">
										联系人
									</td>
									<td align="center" width="5%">
										下单时间
									</td>
									<td align="center" width="5%">
										订单金额
									</td>
									<td width="5%" align="center">
										交易状态
									</td>
									<td width="15%" align="center">
										查看订单详情
									</td>
								</tr>
								<c:forEach items="${pageInfoOrders.list }" var="order" varStatus="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${status.count }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${order.name }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${order.addr }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${order.user.username }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
													${order.ordertime }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="6%">
												${order.total }
											</td>
											<td align="center" style="HEIGHT: 22px">
												<c:if test="${order.state==1 }">未付款</c:if>
												<c:if test="${order.state==2 }">
													<a href="${pageContext.request.contextPath }/admin/orderGoGoods/${order.oid}">去发货</a>
												</c:if>
												<c:if test="${order.state==3 }">待收货</c:if>
												<c:if test="${order.state==4 }">完成交易</c:if>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<input type="button" id="but${order.oid }" onclick="orderAja(${order.oid})" value="点击查看">
												<div id="div${order.oid }"></div>
											</td>
										</tr>
									</c:forEach>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
				<div class="pagination">
				
				<!-- 判断type类型 -->
				<c:if test="${pageInfoOrders.list[0].state==0 }">
					<c:set scope="page" var="type" value="all"/>
				</c:if>
				<c:if test="${pageInfoOrders.list[0].state==1 }">
					<c:set scope="page" var="type" value="1"/>
				</c:if>
				<c:if test="${pageInfoOrders.list[0].state==2 }">
					<c:set scope="page" var="type" value="2"/>
				</c:if>
				<c:if test="${pageInfoOrders.list[0].state==3 }">
					<c:set scope="page" var="type" value="3"/>
				</c:if>
				<c:if test="${pageInfoOrders.list[0].state==4 }">
					<c:set scope="page" var="type" value="4"/>
				</c:if>
				
				
				<!-- 第一页 -->
				<a class="firstPage" href="${pageContext.request.contextPath }/admin/orderfindAllByPage?type=${type }&pageNumber=1"/>&nbsp;</a>
				<!-- 判断是否为首页 -->
				<c:if test="${!pageInfoOrders.isFirstPage}">
					<a class="previousPage" href="${pageContext.request.contextPath }/admin/orderfindAllByPage?type=${type }&pageNumber=${pageInfoOrders.pageNum-1}">&nbsp;</a>
				</c:if>
				<c:if test="${pageInfoOrders.isFirstPage}">
					<span class="previousPage"><a href="#">&nbsp;</a></span>
				</c:if>
				
					<!-- 循环遍历中间页 -->
						<!-- 当不足3页 -->
						<c:choose>
							<c:when test="${pageInfoOrders.pages<=3}">
								<c:set var="start"  value="1"></c:set>
								<c:set var="end" value="${pageInfoOrders.pages}"></c:set>
							</c:when>
							<c:otherwise>
								<!-- 正常显示三条 -->
								<c:set var="start" value="${pageInfoOrders.pageNum-1}"></c:set>
								<c:set var="end" value="${pageInfoOrders.pageNum+1}"></c:set>
								<!-- 头溢出 -->
								<c:if test="${start<1 }">
									<c:set var="start" value="1"></c:set>
									<c:set var="end" value="3"></c:set>
								</c:if>
								<!-- 尾溢出 -->
								<c:if test="${end>pageInfoOrders.pages }">
									<c:set var="start" value="${pageInfoOrders.pages-2}"></c:set>
									<c:set var="end" value="${pageInfoOrders.pages}"></c:set>
								</c:if>
							</c:otherwise>
						</c:choose>
					<c:forEach begin="${start}" end="${end}" var="i">
						 <c:if test="${pageInfoOrders.pageNum!=i }">
						 	<a href="${pageContext.request.contextPath }/admin/orderfindAllByPage?type=${type }&pageNumber=${i }">${i}</a>
						 </c:if>
						 <c:if test="${pageInfoOrders.pageNum==i }">
						 	<a href="#">${i}</a>
						 </c:if>
					</c:forEach>
				<!-- 判断是否为尾页 -->
				<c:if test="${pageInfoOrders.pageNum!=pageInfoOrders.pages}">
					<a class="nextPage" href="${pageContext.request.contextPath }/admin/orderfindAllByPage?type=${type }&pageNumber=${pageInfoOrders.pageNum+1}">&nbsp;</a>
				</c:if>
				<c:if test="${pageInfoOrders.pageNum==pageInfoOrders.pages}">
					<span class="nextPage"><a href="#">&nbsp;</a></span>
				</c:if>
				<!-- 最后一页 -->
				<a class="lastPage" href="${pageContext.request.contextPath }/admin/orderfindAllByPage?type=${type }&pageNumber=${pageInfoOrders.pages}">&nbsp;</a>
			</div>
	</body>
	
</HTML>

