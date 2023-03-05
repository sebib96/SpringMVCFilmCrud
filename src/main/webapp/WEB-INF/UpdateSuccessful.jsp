<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Successful</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">

</head>
<body>


<h2>Your Update Was Successful!</h2>

	<c:choose>
		<c:when test="${! empty film}">

			<h4>Film title: ${film.title}</h4>
			<ul>
				<li>ID: ${film.id}</li>
				<li>Description: ${film.desc}.</li>
				<li>Release year: ${film.releaseYear}</li>
				<li>Language id: ${film.langId}</li>
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
			<br/>
			<a href="home.do">Home</a>

		</c:when>
		<c:otherwise>
			<p>No film found</p>
		</c:otherwise>
	</c:choose>


</body>
</html>