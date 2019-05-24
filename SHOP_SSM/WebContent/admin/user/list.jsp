<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>用  户 列 表</strong>
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

									<td align="center" width="14%">
										序号
									</td>
									<td align="center" width="14%">
										用户账号
									</td>
									<td align="center" width="14%">
										用户名
									</td>
									<td align="center" width="14%">
										用户邮箱
									</td>
									<td align="center" width="14%">
										用户地址
									</td>
									<td align="center" width="14%">
										用户状态
									</td>
								</tr>
								<c:forEach items="${users }" var="user" varStatus="s">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
												${s.count}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
												${user.username}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
												${user.name}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
												${user.email}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
												${user.addr}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
												<c:if test="${user.state==1}">正常</c:if>
												<c:if test="${user.state!=1}">未激活</c:if>
											</td>
										</tr>
									</c:forEach>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

