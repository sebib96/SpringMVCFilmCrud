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
	<c:choose>
		<c:when test="${! empty film}">

			<h4>Film title: ${film.title}</h4>
			<ul>
				<li>ID: ${film.id}</li>
				<li>Description: ${film.desc}.</li>
				<li>Release year: ${film.releaseYear}</li>
				<li>Language id: ${film.langId}</li>
				<li>Language name: ${film.langName}</li>
				<li>Rental duration: ${film.rentDur} days</li>
				<li>Rental cost: ${film.rentRate}</li>
				<li>Film length: ${film.length} mins</li>
				<li>Replacement cost: ${film.repCost}</li>
				<li>Film Rating: ${film.rating}</li>
				<li>Special Features ${film.features}</li>
			</ul>


			<a href="Update.do?filmId=${film.id}">Update</a>

			<br>


			<form action="Delete.do" method="POST">
				Delete Film <input type="hidden" name="filmId" value="${film.id}" />
				<input type="submit" value="Delete" />


			</form>


		</c:when>
		<c:otherwise>
			<p>No film found</p>
		</c:otherwise>
	</c:choose>


</body>
</html>