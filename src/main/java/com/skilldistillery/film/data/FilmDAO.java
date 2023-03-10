package com.skilldistillery.film.data;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;


public interface FilmDAO {
	public Actor findActorById(int actorId);
	public List<Actor> findActorsByFilmId(int filmId);
	public Film findFilmById(int filmId);
	public List<Film> findFilmByKeyWord(String userInput);
	String findFilmLanguage(int filmId);
	Film updateFilm(int filmId, Film film);
	boolean deleteFilm(int filmId);
	Film createFilm(Film film);

}
