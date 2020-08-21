<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<form action="user?action=insertuser" method="POST">
	
		<div>
			<label>Customer Id</label>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="number" name="cid" required />
		</div>
		<div>
			<label>Customer Name</label>
			<input type="text" name="cname" required/>
		</div>
		<div>
			<label>email Id</label>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="text" name="email" required/>
		</div>
		<div>
			<label>phone</label>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="text" name="phone" required/>
		</div>
		<br/>
		<button>Register</button>
	</form>

<hr/>	<br/><br/><br/>
	<jsp:include page="footer.jsp"/>
</body>
</html>