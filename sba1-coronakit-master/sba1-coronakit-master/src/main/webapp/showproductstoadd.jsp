<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<a href="user?action=showkit" style="float:right;color:blue;font-size:18px;">Kit Details</a>

<%if(session.getAttribute("user")!=null){ %>
<%="Hello "+ session.getAttribute("user")%>
<%} %>
<br/><br/>

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
						
						<td><a href="user?action=addnewitem&&pid=${product.id }">Add to Kit</a>	
						<span>|</span>
							<a href="user?action=deleteitem&&pid=${product.id }">Delete from Kit</a>
																			
						</td>
												
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
<br/><br/>
<%if(request.getAttribute("AddItemMsg")!=null){ %>
<%= request.getAttribute("AddItemMsg")%>
<%} %>
<%if(request.getAttribute("DelItemMsg")!=null){ %>
<%= request.getAttribute("DelItemMsg")%>
<%} %>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>