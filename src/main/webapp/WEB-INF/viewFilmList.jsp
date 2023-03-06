<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Film</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
	<link rel="stylesheet" href="resources/styles.css">
	<link rel="icon" type="image/x-icon" href="resources/film.svg">

</head>
<body>
<div class="fixed-top">
<a class="btn btn-dark" href="home.do" role="button" id=homeButton>Home</a>
</div>
<br>
<br>
	<ul>
		<c:choose>
			<c:when test="${empty film}"> No Films Found </c:when>
			<c:otherwise>
				<c:forEach var="film" items="${film}">
				
				<div id="titleBlock">
					<h4>${film.title}</h4>
				</div>
					<ul style="width: auto; height:auto; background: #1b1a42;
					border-radius: 10px; align-self:center;">
					
						<li>Film ID: ${film.id}</li>
						<li>Description: ${film.desc}.</li>
						<li>Release Year: ${film.releaseYear}</li>
						<li>Language Name: ${film.langName}</li>
						<li>Rental Duration: ${film.rentDur} days</li>
						<li>Rental Cost: $${film.rentRate}</li>
						<li>Film Length: ${film.length} minutes</li>
						<li>Replacement Cost: $${film.repCost}</li>
						<li>Film Rating: ${film.rating}</li>
						<li>Special Features ${film.features}</li>
						<li>Genre: ${film.cat }</li>
						<li>Cast: ${film.filmActors}</li>
					</ul>
					<br>
				</c:forEach>
					<form action="Update.do" method="GET">
						<label for="filmId">Enter Film ID to Update</label> <input type="number"
						name="filmId" value="" size="6" > <input type="submit" class="btn btn-warning"
						value="Update Film">
						</form>
						
				<br/>
			</c:otherwise>
		</c:choose>
	</ul>
				<form action="Delete.do" method="POST">
					<label for="filmId">Enter Film ID to Delete</label> <input type="number"
						name="filmId" value="" size="6"> <input type="submit" class="btn btn-danger"
						value="Delete Film">
				</form>
				

<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous"></script>
</body>
</html>