<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Edit Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/><br/>
<hr/>


	
	<form action="admin?action=updateproduct" method="POST">
	
		<div>
			<label>Product Id</label>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="number" name="pid" value="${product.id }" readonly="readonly" required/>
		</div>
		<div>
			<label>Product Name</label>
			<input type="text" name="pname" value="${product.productName }" required/>
		</div>
		<div>
			<label>Cost</label>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="number" name="cost" value="${product.cost }" required/>
		</div>
		<div>
			<label>Description</label>
			&nbsp;&nbsp;&nbsp;
			<input type="text" name="desc" value="${product.productDescription }" required/>
		</div><br/>
		<button>SAVE</button>
	</form>

<hr/><br/>	<br/>	<br/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>