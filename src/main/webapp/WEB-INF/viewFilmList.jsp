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

</head>
<body>
	<ul>
		<c:choose>
			<c:when test="${empty film}"> No Films Found </c:when>
			<c:otherwise>
				<c:forEach var="film" items="${film}">
				
					<h4>Film title: ${film.title}</h4>
					
					<ul>
						<li>Film id: ${film.id}</li>
						<li>Description: ${film.desc}.</li>
						<li>Release year: ${film.releaseYear}</li>
						<li>Language Name: ${film.langName}</li>
						<li>Rental duration: ${film.rentDur} days</li>
						<li>Rental cost: $${film.rentRate}</li>
						<li>Film length: ${film.length} mins</li>
						<li>Replacement cost: $${film.repCost}</li>
						<li>Film Rating: ${film.rating}</li>
						<li>Special Features ${film.features}</li>
						<li>Genere: ${film.cat }</li>
						<li>Cast: ${film.filmActors}</li>
					</ul>
				</c:forEach>


				



					<form action="Update.do" method="GET">
						<label for="filmId">Enter Film Id to Update</label> <input type="text"
						name="filmId" value="" size="6" > <input type="submit"
						value="Update film">
						</form>

				


				<br />
				<form action="Delete.do" method="POST">
					<label for="filmId">Enter Film Id to Delete</label> <input type="text"
						name="filmId" value="" size="6"> <input type="submit"
						value="Delete film">
				</form>
				<a href="home.do">Home</a>
			</c:otherwise>
		</c:choose>
	</ul>






</body>
</html>