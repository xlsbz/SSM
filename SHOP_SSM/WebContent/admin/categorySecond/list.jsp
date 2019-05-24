<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
			function addUser(){
				window.location.href = "${pageContext.request.contextPath}/admin/categorySecond/add.jsp";
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
							<strong>二 级  分  类  列  表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="right">
							<button type="button" id="add" name="add" value="添加" class="button_add" onclick="addUser()">
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

									<td align="center" width="15%">
										序号
									</td>
									<td align="center" width="14%">
										二级分类名称
									</td>
									<td align="center" width="14%">
										所属一级分类
									</td>
									<td width="7%" align="center">
										编辑
									</td>
									<td width="7%" align="center">
										删除
									</td>
								</tr>
								<c:forEach items="${pageInfoCategorySecond.list}" var="cs" varStatus="s">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="15%">
												${s.count }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
												${cs.csname }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
												${cs.category.cname }
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/admin/categorySecondEdit/${cs.csid}">
													<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/admin/categorySecondDelete/${cs.csid}">
													<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
											</td>
										</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
			<div class="pagination">
				<!-- 第一页 -->
				<a class="firstPage" href="${pageContext.request.contextPath }/admin/findCategorySecond?pageNumber=1"/>&nbsp;</a>
				<!-- 判断是否为首页 -->
				<c:if test="${!pageInfoCategorySecond.isFirstPage}">
					<a class="previousPage" href="${pageContext.request.contextPath }/admin/findCategorySecond?pageNumber=${pageInfoCategorySecond.pageNum-1}">&nbsp;</a>
				</c:if>
				<c:if test="${pageInfoCategorySecond.isFirstPage}">
					<span class="previousPage"><a href="#">&nbsp;</a></span>
				</c:if>
				
					<!-- 循环遍历中间页 -->
						<!-- 当不足3页 -->
						<c:choose>
							<c:when test="${pageInfoCategorySecond.pages<=3}">
								<c:set var="start"  value="1"></c:set>
								<c:set var="end" value="${pageInfoCategorySecond.pages}"></c:set>
							</c:when>
							<c:otherwise>
								<!-- 正常显示三条 -->
								<c:set var="start" value="${pageInfoCategorySecond.pageNum-1}"></c:set>
								<c:set var="end" value="${pageInfoCategorySecond.pageNum+1}"></c:set>
								<!-- 头溢出 -->
								<c:if test="${start<1 }">
									<c:set var="start" value="1"></c:set>
									<c:set var="end" value="3"></c:set>
								</c:if>
								<!-- 尾溢出 -->
								<c:if test="${end>pageInfoCategorySecond.pages }">
									<c:set var="start" value="${pageInfoCategorySecond.pages-2}"></c:set>
									<c:set var="end" value="${pageInfoCategorySecond.pages}"></c:set>
								</c:if>
							</c:otherwise>
						</c:choose>
					<c:forEach begin="${start}" end="${end}" var="i">
						 <c:if test="${pageInfoCategorySecond.pageNum!=i }">
						 	<a href="${pageContext.request.contextPath }/admin/findCategorySecond??pageNumber=${i }">${i}</a>
						 </c:if>
						 <c:if test="${pageInfoCategorySecond.pageNum==i }">
						 	<a href="#">${i}</a>
						 </c:if>
					</c:forEach>
				<!-- 判断是否为尾页 -->
				<c:if test="${pageInfoCategorySecond.pageNum!=pageInfoCategorySecond.pages}">
					<a class="nextPage" href="${pageContext.request.contextPath }/admin/findCategorySecond?pageNumber=${pageInfoCategorySecond.pageNum+1}">&nbsp;</a>
				</c:if>
				<c:if test="${pageInfoCategorySecond.pageNum==pageInfoCategorySecond.pages}">
					<span class="nextPage"><a href="#">&nbsp;</a></span>
				</c:if>
				<!-- 最后一页 -->
				<a class="lastPage" href="${pageContext.request.contextPath }/admin/findCategorySecond?pageNumber=${pageInfoCategorySecond.pages}">&nbsp;</a>
			</div>
		</form>
	</body>
</HTML>

