<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Film</title>
</head>
<body>



	 <form action="finalUpdate.do" method="POST">
      <input type="hidden" name="id" value="${film.id}"/>
      
      <label for="title">Film Title:</label>
      <input type="text" name="title" value="${film.title}"/>
      
      <br>
      
      <label for="description">Film Description:</label>
      <input type="text" name="desc" value="${film.desc}">
      
      <br>
      
      <label for="releaseYear">Release Year:</label>
      <input type="text" name="releaseYear" value="${film.releaseYear}">
      
      <br>
      <label for="rentDur">Rental Duration:</label>
      <input type="text" name="rentDur" value="${film.rentDur}">
      
       <br>
       
      <label for="rentRate">Rental Rate:</label>
      <input type="text" name="rentRate" value="${film.rentRate}">
      
      <br>
      
      <label for="length">Length:</label>
      <input type="text" name="length" value="${film.length}">
      
       <br>
      <label for="repCost">Replacement Cost:</label>
      <input type="text" name="repCost" value="${film.repCost}">
      
      <br>
      <label for="rating">Rating:</label>
      <input type="text" name="rating" value="${film.rating}">
      
       <br>
      <label for="features">Special Features:</label>
      <input type="text" name="features" value="${film.features}">
       
      <br>
      <label for="langName">Language:</label>
      <input type="text" name="langName" value="${film.langName}">
      
      
      <input type="submit" value="Update Film">
    </form>










</body>
</html>