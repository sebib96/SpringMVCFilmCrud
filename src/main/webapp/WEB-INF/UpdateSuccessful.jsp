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
	
	<link rel="icon" type="image/x-icon" href="resources/film.svg">
	<link rel="stylesheet" href="resources/styles.css">
</head>
<body>
<a class="btn btn-dark" href="home.do" role="button" id=homeButton>Home</a>
<br>
<br>
<h2>Your update was successful</h2>

	<c:choose>
		<c:when test="${! empty film}">
	<div id="titleBlock"></div>
			<h4>Film title: ${film.title}</h4>
			<div id="titleBlock"></div>
			
			<ul style="width: auto; height:auto; background: #1b1a42;
					border-radius: 10px; align-self:center;">
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
		<a class="btn btn-warning" href="Update.do?filmId=${film.id}" role="button">Update Film</a>
			<br>
			<br>
				<form action="Delete.do" method="POST">
				<label for="filmId"></label> 
				<input type="hidden" name="filmId" value="${film.id}" />
				<input type="submit" class="btn btn-danger"value="Delete" />
			</form>

			<br/>


		</c:when>
		<c:otherwise>
			<p>No film found</p>
		</c:otherwise>
	</c:choose>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"
		integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD"
		crossorigin="anonymous"></script>
</body>
</html>