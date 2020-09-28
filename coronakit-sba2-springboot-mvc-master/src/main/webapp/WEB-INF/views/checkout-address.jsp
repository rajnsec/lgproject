<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delivery Address</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>
	<jsp:include page="header.jsp" /><br><br>
	<h3> Delivery Address</h3>
	<ul class="nav justify-content-end">
	     <li class="nav-item">
    <a class="nav-link active" href="${pageContext.request.contextPath}/user/show-list">Back To Products</a>
  </li>
    <li class="nav-item">
    <a class="nav-link active" href="${pageContext.request.contextPath}/logout">Logout</a>
  </li>

  </ul>
<hr>
	<section class="container-fluid p-4">
	
	<div class="col-sm-4">	
	<form action="${pageContext.request.contextPath}/user/finalize" method="POST" >

<div class="form-group">
  <label for="comment">Enter Delivery Address:</label>
  <textarea class="form-control" rows="5" id="address" name="address""></textarea>
</div>
		
		
		<div class="form-group text-right">
		<button class="btn btn-primary">Place Order</button>
		</div>
	</form>
	</div>
	
	</section>
	<jsp:include page="footer.jsp" /><br><br>
</body>
</html>