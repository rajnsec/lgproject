<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<a href="admin?action=logout" style="float:right;color:blue;font-size:20px;">Logout</a>
<h3>Hello&nbsp; <%=session.getAttribute("username") %></h3>
<br/>
<c:choose>
		<c:when test="${products == null || products.isEmpty() }">
			<p>No Product Found </p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>Product#</th>
					<th>Product Name</th>
					<th>Cost</th>
					<th>Description</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${products}" var="product">
					<tr>
						<td>${product.id }</td>
						<td>${product.productName }</td>
						<td>${product.cost}</td>
						<td>${product.productDescription }</td>
						<td>
							<a href="admin?action=deleteproduct&&pid=${product.id }">DELETE</a>
							<span>|</span>
							<a href="admin?action=editproduct&&pid=${product.id }">EDIT</a>
							
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
<br/>
<%if(request.getAttribute("DelMsg")!=null){ %>
<%=request.getAttribute("DelMsg") %>
<%} %>
<%if(request.getAttribute("UpdateMsg")!=null){ %>
<%=request.getAttribute("UpdateMsg") %>
<%} %>
<%if(request.getAttribute("AddMsg")!=null){ %>
<%=request.getAttribute("AddMsg") %>
<%} %>

<br/><br/><br/>
<a href="admin?action=newproduct" style="color:blue;font-size:17px;">Add new product</a>
<hr/>	

	<jsp:include page="footer.jsp"/>
</body>
</html>