<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add New Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<form action="admin?action=insertproduct" method="POST">
	
		<div>
			<label>Product Id</label>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="number" name="pid" required/>
		</div>
		<div>
			<label>Product Name</label>
			<input type="text" name="pname" required/>
		</div>
		<div>
			<label>Cost</label>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="number" name="cost" required/>
		</div>
		<div>
			<label>Description</label>
			&nbsp;&nbsp;&nbsp;
			<input type="text" name="desc" required/>
		</div>
		<br/>
		<button>ADD</button>
	</form>


<hr/>	<br/><br/><br/>
	<jsp:include page="footer.jsp"/>
</body>
</html>