<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="spring-form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<title>Main Menu</title>
</head>
<body>
<jsp:include page="header.jsp" /><br><br>
<h2>Hello <security:authentication property="principal.username"/></h2>
<spring-form:form action="${pageContext.request.contextPath}/logout" method="POST">
<button class="btn btn-secondary btn-lg float-right" type="submit" >Logout</button> 
</spring-form:form>
<br><br>
<hr>
<h4>Welcome to Corona Kit Dashboard!!!</h4>

<security:authorize access="hasRole('ADMIN')">
	<hr/>
	<ul class="nav justify-content-center">
  	<li class="nav-item">
    <a class="nav-link active font-weight-bold" href="${pageContext.request.contextPath}/admin/home">ADMIN DASHBOARD</a>
  </li>
  </ul>
	
</security:authorize>

<security:authorize access="hasRole('USER')">
	<hr/>
		<ul class="nav justify-content-center">
  	<li class="nav-item">
    <a class="nav-link active font-weight-bold" href="${pageContext.request.contextPath}/user/home">USER DASHBOARD</a>
  </li>
  </ul>
	
</security:authorize>

<jsp:include page="footer.jsp" /><br><br>
</body>
</html>