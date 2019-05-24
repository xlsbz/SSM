<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>用户列表</strong>
						</TD>
					</tr>
					<tr>
						
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="18%">
										序号
									</td>
									<td align="center" width="10%">
										用户名称
									</td>
									<td align="center" width="10%">
										真实姓名
									</td>
									<td align="center" width="10%">
										用户性别
									</td>
									<td align="center" width="10%">
										用户状态
									</td>
									<td align="center" width="10%">
										用户邮箱
									</td>
									<td align="center" width="10%">
										用户电话
									</td>
									<td align="center" width="10%">
										用户生日
									</td>
								</tr>
								<c:forEach items="${user }" var="u" varStatus="s">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="18%">
												${s.count }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												${u.username }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												${u.name }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												${u.sex }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<c:if test="${u.state==1}"><b>正常</b></c:if>
												<c:if test="${u.state!=1}"><b style="color:red">未激活</b></c:if>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												${u.email }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												${u.telephone }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<fmt:formatDate value="${u.birthday }" pattern="yyyy-MM-dd"/>
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

