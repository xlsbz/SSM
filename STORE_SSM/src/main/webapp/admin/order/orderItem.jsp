<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table width="100%">
	<c:forEach items="${orders.orderitems}" var="oi">
	<tr>
		<td><img width="40" height="45" src="${pageContext.request.contextPath }/${oi.product.pimage}"></td>
		<td>${oi.product.pname}</td>
		<td>${oi.quantity}</td>
		<td>${oi.total}</td>
	</tr>
	</c:forEach>
</table>