package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Component
public class FilmDaoJdbcImpl implements FilmDAO {
	private static String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String user = "student";
	private static final String pass = "student";

	//________________________________________________________________________________
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver");
			throw new RuntimeException("Unable to load MySQL Driver class");
		}
	}
	//________________________________________________________________________________

	
	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();

			if (actorResult.next()) {
				actor = new Actor(actorResult.getInt("id"), actorResult.getString("first_name"),
						actorResult.getString("last_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
		
	}
	//________________________________________________________________________________


	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		String sql = "SELECT id, first_name, last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_id = ?";
		List<Actor> filmActors = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement stmnt = conn.prepareStatement(sql);
			stmnt.setInt(1, filmId);
			ResultSet actorResult = stmnt.executeQuery();

			while (actorResult.next()) {
				Actor actor = new Actor(actorResult.getInt("id"), actorResult.getString("first_name"),
						actorResult.getString("last_name"));
				filmActors.add(actor);
			}
			conn.close();
			stmnt.close();
			actorResult.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return filmActors;
		
	}
	//________________________________________________________________________________


	@Override
	public Film findFilmById(int filmId) {

		Film foundFilm = null;
		String query = "SELECT * FROM film WHERE id = ?";

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, filmId);
			ResultSet film = stmnt.executeQuery();

			if (film.next()) {
				foundFilm = new Film(film.getInt("id"), film.getString("title"), film.getString("description"),
						film.getInt("release_year"), film.getInt("language_id"), film.getInt("rental_duration"),
						film.getDouble("rental_rate"), film.getInt("length"), film.getDouble("replacement_cost"),
						film.getString("rating"), film.getString("special_features"));

				foundFilm.setLangName(findFilmLanguage(filmId));
				foundFilm.setFilmActors(findActorsByFilmId(filmId));
				foundFilm.setFilmActors(findActorsByFilmId(foundFilm.getId()));
			}
			conn.close();
			stmnt.close();
			film.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundFilm;
		
	}
	//________________________________________________________________________________


	@Override
	public List<Film> findFilmByKeyWord(String keyWord) {
		List<Film> filmList = new ArrayList<>();
		Film foundFilm = null;
		String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?;";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyWord + "%");
			stmt.setString(2, "%" + keyWord + "%");
			ResultSet film = stmt.executeQuery();

			while (film.next()) {
				foundFilm = new Film(film.getInt("id"), film.getString("title"), film.getString("description"),
						film.getInt("release_year"), film.getInt("language_id"), film.getInt("rental_duration"),
						film.getDouble("rental_rate"), film.getInt("length"), film.getDouble("replacement_cost"),
						film.getString("rating"), film.getString("special_features"));
				foundFilm.setLangName(findFilmLanguage(foundFilm.getId()));
				foundFilm.setFilmActors(findActorsByFilmId(foundFilm.getId()));

				filmList.add(foundFilm);

			}
			conn.close();
			stmt.close();
			film.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmList;
	}
	//________________________________________________________________________________


	@Override
	public Film createFilm(Film film) {
		// TODO Auto-generated method stub
		return null;
	}
	//________________________________________________________________________________


	@Override
	public boolean deleteFilm(int filmId) {
		// TODO Auto-generated method stub
		return false;
	}
	//________________________________________________________________________________

	@Override
	public Film updateFilm(int filmId, Film film) {
		// TODO Auto-generated method stub
		return null;
	}
	//________________________________________________________________________________


	@Override
	public String findFilmLanguage(int filmId) {
		String langName = null;
		String sql = "SELECT language.name FROM film f JOIN language ON f.language_id=language.id WHERE f.id = ?";

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement stmnt = conn.prepareStatement(sql);
			stmnt.setInt(1, filmId);
			ResultSet lang = stmnt.executeQuery();

			if (lang.next()) {
				langName = lang.getString("language.name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return langName;

	}
	//________________________________________________________________________________

}
