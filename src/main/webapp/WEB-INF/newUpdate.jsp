<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Film</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">

<link rel="icon" type="image/x-icon" href="resources/film.svg">
</head>
<body>



	<form action="finalUpdate.do" method="POST">
		<input type="hidden" name="id" value="${film.id}" /> 
		<label for="title">Film Title:</label> 
		<input type="text" name="title" value="${film.title}" /> 
		<br> 
		<label for="description">Film Description:</label> <input type="text" name="desc" value="${film.desc}">

		<br> 
		<label for="releaseYear">Release Year:</label> 
		<input type="number" name="releaseYear" value="${film.releaseYear}"> 
		<br>
		<label for="rentDur">Rental Duration:</label>
		 <input type="number" name="rentDur" value="${film.rentDur}"> 
		 <br> 
		 <label for="langId">Choose a language:</label>

		<select name="langId" id="langId">
		    <option value="${film.langId}" selected>${film.langName}</option>
		    <option value="1">1- English</option>
		    <option value="2">2- Italian</option>
		    <option value="3">3- Japanese</option>
		    <option value="4">4- Mandarin</option>
		    <option value="5">5- French</option>
		    <option value="6">6- German</option>
		</select>
		 <br> 
		 <label for="rentRate">Rental Rate:</label> 
		 <input type="number" step="0.01" name="rentRate" value="${film.rentRate}"> 
		 <br> 
		 <label for="length">Length:</label> 
		 <input type="number" name="length" value="${film.length}"> 
		 <br> 
		 <label for="repCost">Replacement Cost:</label> <input type="number" step="0.01" name="repCost" value="${film.repCost}">

		<br> 
		<label for="rating">Choose a Rating:</label>

		<select name="rating" id="rating">
		    <option value="${film.rating}" selected>${film.rating}</option>
		    <option value="G">G</option>
		    <option value="PG">PG</option>
		    <option value="PG13">PG13</option>
		    <option value="R">R</option>
		    <option value="NC17">NC17</option>
		</select>
		<br> 
		   
		   <label for="features">Special Features:</label> 
		   <input type="text" name="features" value="${film.features}"> 
		   
		
		
		<br> 
		
		<input type="submit" value="Update Film">
	</form>



</body>
</html>