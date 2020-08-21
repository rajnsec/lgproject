<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Place Order(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<h3> Enter Delivery Address</h3><br/>
<form action="user?action=saveorder" method="POST">
	
		<div>
			<label>Flat No.</label>
			<input type="text" name="flat" required/>
		</div>
		<div>
			<label>Street</label>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="text" name="street" required />
		</div>
		<div>
			<label>City</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="text" name="city" required/>
		</div>
		<div>
			<label>Pin</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="text" name="pin" required/>
		</div><br/>
		<button>Save Order For Delivery</button>
	</form>

<br/>
<hr/>	<br/><br/>
	<jsp:include page="footer.jsp"/>
</body>
</html>