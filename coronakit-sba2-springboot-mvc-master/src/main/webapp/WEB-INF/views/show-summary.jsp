<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Kit Details</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

</head>
<body>
<jsp:include page="header.jsp" /><br>
<h5>Thank you for shopping!!!</h5><br>
<h3>Order Summary</h3>
<ul class="nav justify-content-end">
	     <li class="nav-item">
    <a class="nav-link active" href="${pageContext.request.contextPath}/user/show-list">Order Again</a>
  </li>
    <li class="nav-item">
    <a class="nav-link active" href="${pageContext.request.contextPath}/logout">Logout</a>
  </li>
  </ul>


<div class="card-deck">
  <div class="card bg-light">
    <div class="card-body text-left">
    <p class="card-text">
    <label><b>Customer Name:</b></label>
    ${customername}</p>
      <p class="card-text">
      <label><b>Delivery Address:</b></label>
      ${address}</p>
    </div>
  </div>
  </div>

	<section class="container-fluid p-4">
	
		<table class="table table-striped table-hover table-border">
			<tr>
				<th>Product#</th>
				<th>Product Name</th>
				<th>Quantity</th>
				<th>Cost per Qty</th>
				<th>Amount</th>
				
			</tr>
			<c:forEach items="${map}" var="element">
				<tr>
				<td><c:out value="${element.key}"></c:out></td>
				<td><c:out value="${element.value[0]}"></c:out></td>
				<td><c:out value="${element.value[1]}"></c:out></td>
				<td><c:out value="${element.value[2]}"></c:out></td>
				<td><c:out value="${element.value[3]}"></c:out></td>
				
				</tr>
			</c:forEach>
			<tr class="p-3 mb-2 bg-gradient-danger text-black">
				<td><b>Total</b></td>
				<td></td>
				<td><b>${totalqty}</b></td>
				<td></td>
				<td><b>${tamnt}</b></td>
			</tr>
		</table>
	</section>
	<jsp:include page="footer.jsp" /><br><br>
</body>
</html>