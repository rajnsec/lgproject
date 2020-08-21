<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Order Summary(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<hr/><a href="index.jsp" style="float:right;color:blue;font-size:18px;">Home</a>
<label style="float:left;color:green;font-size:18px;">Thank you for shopping</label><br/>
<h3>Customer Details</h3>

<c:choose>
		<c:when test="${customer == null }">
			<p>Customer Record Not Found </p>
		</c:when>
		<c:otherwise>
		<label style="float:left;font-size:14px;">Name:</label>	${customer.personName}<br/>
		<label style="float:left;font-size:15px;">Email:</label>${customer.email}<br/>
		<label style="float:left;font-size:16px;">Phone:</label>	${customer.contactNumber}<br/>
		<label style="float:left;font-size:17px;">Address:</label>	${customer.deliveryAddress}<br/>
			<hr/>
			<h3>Order Details</h3>
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
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>