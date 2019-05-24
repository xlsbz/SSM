<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script type="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript">
			function showDetail(oid){
				var $val = $("#but"+oid).val();
				if($val == "订单详情"){
					// ajax 显示图片,名称,单价,数量
					$.ajax({
						url:"${pageContext.request.contextPath}/admin/findOrderByOid/"+oid,
						type:"GET",
						data:null,
						success:function(data){
							/* $("#div"+oid).append("<img width='60' height='65' src='${pageContext.request.contextPath}/"+data.object.orderitems[0].product.pimage+"'>&nbsp;'"+data.object.orderitems[0].product.pname+"'&nbsp;998<br/>"); */
							$("#div"+oid).css("display","block");
							$("#but"+oid).val("关闭");
						}
					});
				}else{
					$("#div"+oid).css("display","none");
					$("#div"+oid).html("");
					$("#but"+oid).val("订单详情");
				}
			}
		</script>
	</HEAD>
	
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
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

									<td align="center" width="5%">
										序号
									</td>
									<td align="center" width="10%">
										订单编号
									</td>
									<td align="center" width="10%">
										订单金额
									</td>
									<td align="center" width="10%">
										订单姓名
									</td>
									<td align="center" width="10%">
										订单地址
									</td>
									<td align="center" width="10%">
										联系电话
									</td>
									<td align="center" width="10%">
										订单时间
									</td>
									<td align="center" width="10%">
										订单状态
									</td>
									<td align="center" width="25%">
										订单详情
									</td>
								</tr>
								<!-- 遍历订单 -->
									<c:forEach items="${pageInfoOrders.list }" var="order" varStatus="s">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${s.count }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												${order.oid }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												${order.total }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												${order.name }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												${order.address }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												${order.telephone }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												${order.ordertime }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
													<c:if test="${order.state==0 }">未付款</c:if>
													<c:if test="${order.state==1 }">待发货</c:if>
													<c:if test="${order.state==2 }">待收货</c:if>
													<c:if test="${order.state==3 }">交易完成</c:if>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<input type="button" value="订单详情" id="but${order.oid}" onclick="showDetail('${order.oid}')"/>
												<div id="div${order.oid}" style="display:none">
														<h3>订单列表如下</h3>
														<table width="100%" border="10%">
															<c:forEach items="${order.orderitems}" var="oi">
															<tr>
																<td><img width="40" height="45" src="${pageContext.request.contextPath }/${oi.product.pimage}"></td>
																<td>${oi.product.pname}</td>
																<td>${oi.quantity}</td>
																<td>${oi.total}</td>
															</tr>
															</c:forEach>
														</table>
												</div>
											</td>
							
										</tr>
									</c:forEach>
							</table>
						</td>
					</tr>
					<!--分页 -->
					
				
		<div style="width:380px;margin:0 auto;margin-top:50px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
				<!-- 判断是否是第一页 -->
				<c:if test="${pageInfoOrders.pageNum==1 }">
					<li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				</c:if>
					<c:if test="${pageInfoOrders.pageNum!=1 }">
					<li><a href="${pageContext.request.contextPath}/admin/showOrder/${state}?pageNumber=${pageInfoOrders.pageNum-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				</c:if>
				<c:forEach begin="1" end="${pageInfoOrders.pages }" var="n">
					<!-- 判断是否是当前页 -->
					<c:if test="${pageInfoOrders.pageNum==n}">
						<li class="active"><a href="#">${n }</a></li>
					</c:if>
					<c:if test="${pageInfoOrders.pageNum!=n}">
						<li class=""><a href="${pageContext.request.contextPath}/admin/showOrder/${state}?pageNumber=${n}">${n }</a></li>
					</c:if>
				</c:forEach>
				
					<!-- 判断是否是最后一页-->
				<c:if test="${pageInfoOrders.pageNum==pageInfoOrders.pages }">
					<li class="disabled"><a href="#"  aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
				</c:if>
				<c:if test="${pageInfoOrders.pageNum!=pageInfoOrders.pages }">
					<li><a href="${pageContext.request.contextPath}/admin/showOrder/${state}?pageNumber=${pageInfoOrders.pageNum+1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
				</c:if>
			</ul>
		</div>
		<!-- 分页结束=======================-->
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

