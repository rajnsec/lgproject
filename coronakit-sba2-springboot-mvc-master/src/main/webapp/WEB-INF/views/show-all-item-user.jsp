
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<title>User- Products</title>
</head>
<body>
<jsp:include page="header.jsp" /><br><br>
<h3>All Products</h3>
<ul class="nav justify-content-end">
    <li class="nav-item">
    <a class="nav-link active" href="${pageContext.request.contextPath}/logout">Logout</a>
  </li>
  </ul>
<hr>
	<c:if test="${msg != null }">
		<p class="alert alert-info"><strong>${msg }</strong></p>
	</c:if>
	<section class="container-fluid p-4">
	<c:choose>
		<c:when test="${products == null || products.isEmpty() }">
			<p class="well well-info">No Product Found. Contact Admin.</p>
		</c:when>
<c:otherwise>
			<form action="${pageContext.request.contextPath}/user/add-to-cart" method="POST">
				<table class="table table-striped table-hover table-border">
					<tr>
						<th>Product#</th>
						<th>Product Name Name</th>
						<th>Cost</th>
						<th>Product Desc</th>
						<th>Quantity</th>
					</tr>
					<c:forEach items="${products }" var="product">
						<tr>
							<td>${product.id }</td>
							<td>${product.productName }</td>
							<td>${product.cost }</td>
							<td>${product.productDescription }</td>
							<td><select name="quantity" id="quantity${product.id}">
								<option value="${product.id}0">0</option>
								<option value="${product.id}1">1</option>
								<option value="${product.id}2">2</option>
								<option value="${product.id}3">3</option>
								<option value="${product.id}4">4</option>
							</select></td>
						</tr>
					</c:forEach>
				</table>
				<input type="submit" class="btn btn-sm btn-success" value="Add to Kit"/>
				</form>
			</c:otherwise>
	
	</c:choose>
	</section>
	<jsp:include page="footer.jsp" /><br><br>
</body>
</html>