<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring-form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<title>Login</title>
</head>
<body>
<jsp:include page="header.jsp" /><br><br>
<style>
.btn
{
margin-left: 120px;
}
h3
{
margin-left: 10px;
}

</style>



<core:if test="${param.error != null}">
	<i>Invalid Credentials!!!</i>
</core:if>
<h3>Login</h3>
<spring-form:form action="${pageContext.request.contextPath}/validate" method="POST">
	<br/>

	<label>Enter user name</label>
	<input type="text" name="username" />
	<br/>
	
	<label>Enter password</label>&nbsp;&nbsp;
	<input type="password" name="password" />
	<br/>
	
	<input class="btn btn-secondary" type="submit" value="Login" />
</spring-form:form>
<core:if test="${param.logout != null}">
	<i>You have been logged out successfully!!!</i>
</core:if>

<jsp:include page="footer.jsp" /><br><br>
</body>
</html>