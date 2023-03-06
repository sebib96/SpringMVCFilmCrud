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
	<link rel="icon" type="image/x-icon" href="resources/film.svg">
	<link rel="stylesheet" href="resources/styles.css">
</head>

<body>
<a class="btn btn-dark" href="home.do" role="button" id=homeButton>Home</a>
<br>
<br>
<br>
	<c:choose>
		<c:when test="${! empty film}">
		<div id="titleBlock">
			<h4>Film Title: ${film.title}</h4>
			</div>
			<ul style="width: auto; height:auto; background: #1b1a42;
					border-radius: 10px; align-self:center;">
				<li><strong>ID:</strong> ${film.id}</li>
				<li><strong>Description:</strong> ${film.desc}.</li>
				<li><strong>Release year:</strong> ${film.releaseYear}</li>
				<li><strong>Language Name:</strong> ${film.langName}</li>
				<li><strong>Rental duration:</strong> ${film.rentDur} days</li>
				<li><strong>Rental cost:</strong> ${film.rentRate}</li>
				<li><strong>Film length:</strong> ${film.length} minutes</li>
				<li><strong>Replacement cost:</strong> ${film.repCost}</li>
				<li><strong>Film Rating:</strong> ${film.rating}</li>
				<li><strong>Special Features:</strong> ${film.features}</li>
				<li><strong>Genre:</strong> ${film.cat }</li>
				<li><strong>Cast:</strong> ${film.filmActors}</li>

			</ul>

		</c:when>
		<c:otherwise>
			<p>No film found </p>
		</c:otherwise>
	</c:choose>
	
			<br>
	<a class="btn btn-warning" href="Update.do?filmId=${film.id}" role="button">Update Film</a>
			<br>
			<br>
	
			<form action="Delete.do" method="POST">
				<label for="filmId"></label> 
				<input type="hidden" name="filmId" value="${film.id}" />
				<input type="submit" class="btn btn-danger"value="Delete" />
			</form>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"
		integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD"
		crossorigin="anonymous"></script>
</body>
</html>