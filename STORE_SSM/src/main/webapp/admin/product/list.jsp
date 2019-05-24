<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/admin/toAddProduct";
			}
			
			function shutDown(pid){
				if(confirm("确认要下架该商品吗?")){
					$.ajax({
						url:"${pageContext.request.contextPath}/admin/shutDownProduct/"+pid,
						type:"GET",
						data:null,
						success:function(data){
							location.href = "${pageContext.request.contextPath}/admin/showPushDownProduct"
						}
					});
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
							<strong>商品列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="right">
							<button type="button" id="add" name="add" value="添加" class="button_add" onclick="addProduct()">
&#28155;&#21152;
</button>

						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="10%">
										序号
									</td>
									<td align="center" width="14%">
										商品图片
									</td>
									<td align="center" width="14%">
										商品名称
									</td>
									<td align="center" width="14%">
										商品价格
									</td>
									<td align="center" width="14%">
										是否热门
									</td>
									<td align="center" width="7%">
										商品状态
									</td>
									<td align="center" width="14%">
										修改日期
									</td>
									<td width="7%" align="center">
										编辑
									</td>
									<td width="7%" align="center">
										下架
									</td>
								</tr>
									<c:forEach items="${pageInfoProducts.list}" var="pl" varStatus="s">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												${s.count }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
												<img width="40" height="45" src="${ pageContext.request.contextPath }/${pl.pimage}">
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
												${pl.pname }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
												${pl.shopPrice }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
													<c:if test="${pl.isHot==1 }">是</c:if>
													<c:if test="${pl.isHot==0 }">否</c:if>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="7%">
													<c:if test="${pl.pflag==0}">上架商品</c:if>
													<c:if test="${pl.pflag==1}">下架商品</c:if>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
													<fmt:formatDate value="${pl.pdate }" pattern="yyyy-MM-dd"/>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/admin/toEditProduct/${pl.pid}">
													<img src="${pageContext.request.contextPath}/img/admin/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<%--下架 pushdown --%>
												<a href="#">
													<img onclick="shutDown(${pl.pid})" src="${pageContext.request.contextPath}/img/admin/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
											</td>
										</tr>
									</c:forEach>
							</table>
						</td>
					</tr>
					<tr align="center">
						<td colspan="7">
								<!--分页 -->
							<div style="width:380px;margin:0 auto;margin-top:50px;">
								<ul class="pagination" style="text-align:center; margin-top:10px;">
									<!-- 判断是否是第一页 -->
									<c:if test="${pageInfoProducts.pageNum==1 }">
										<li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
									</c:if>
										<c:if test="${pageInfoProducts.pageNum!=1 }">
										<li><a href="${pageContext.request.contextPath}/admin/showProduct?pageNumber=${pageInfoProducts.pageNum-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
									</c:if>
									<c:forEach begin="1" end="${pageInfoProducts.pages }" var="n">
										<!-- 判断是否是当前页 -->
										<c:if test="${pageInfoProducts.pageNum==n}">
											<li class="active"><a href="#">${n }</a></li>
										</c:if>
										<c:if test="${pageInfoProducts.pageNum!=n}">
											<li class=""><a href="${pageContext.request.contextPath}/admin/showProduct?pageNumber=${n}">${n }</a></li>
										</c:if>
									</c:forEach>
									
										<!-- 判断是否是最后一页-->
									<c:if test="${pageInfoProducts.pageNum==pageInfoProducts.pages }">
										<li class="disabled"><a href="#"  aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
									</c:if>
									<c:if test="${pageInfoProducts.pageNum!=pageInfoProducts.pages }">
										<li><a href="${pageContext.request.contextPath}/admin/showProduct?pageNumber=${pageInfoProducts.pageNum+1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
									</c:if>
								</ul>
							</div>
							<!-- 分页结束=======================-->
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

