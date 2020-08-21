<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-My Kit(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<a href="user?action=showproducts" style="float:left;color:blue;font-size:17px;">Return to Shopping</a><br/><br/>

<c:choose>
		<c:when test="${items == null || items.isEmpty() }">
			<p>Cart is empty </p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>Product#</th>
					<th>Product Name</th>
					<th>Description</th>
					<th>Cost</th>
					<th>Qty</th>					
					<th>Total Cost</th>
				</tr>
				<c:forEach items="${items}" var="item">
					<tr>
						<td>${item.productId }</td>
						<td>${item.productName }</td>
						<td>${item.productDescription}</td>
						<td>${item.cost }</td>
						<td>${item.quantity }</td>
						<td>${item.amount }</td>
						</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<br/>
<label style="float:left;color:green;font-size:18px;">Total Order Amount:</label>&nbsp;${OrderTotal}
<br/><br/>
<form action="user?action=placeorder" method="post"><button>Place Order</button></form>
<br/><br/>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>