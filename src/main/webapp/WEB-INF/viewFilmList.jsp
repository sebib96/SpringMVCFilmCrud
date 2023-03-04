<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Film</title>
</head>
<body>
	<ul>
		<c:choose>
			<c:when test="${empty film}"> No Films Found </c:when>
			<c:otherwise>
				<c:forEach var="film" items="${film}">
					<li>${film}</li>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</ul>

	
	<form action="Update.do" method="GET">
		Update Film Details
		<input type="text" name="" size="6"/> 
		<input type="submit" value="" />
	</form>
	<form action="Delete.do" method="GET">
		Delete Film
		<input type="text" name="" size="6"/> 
		<input type="submit" value="" />
	</form>
	
</body>
</html>