package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	// ________________________________________________________________________________
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver");
			throw new RuntimeException("Unable to load MySQL Driver class");
		}
	}
	// ________________________________________________________________________________

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
	// ________________________________________________________________________________

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
	// ________________________________________________________________________________

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
	// ________________________________________________________________________________

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
		System.out.println(filmList);
		return filmList;
	}
	// ________________________________________________________________________________

	@Override
	public Film createFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration,"
					+ " rental_rate, replacement_cost ) " + " VALUES (?, ?, ?, 1, ?, ?, ? )";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDesc());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getRentDur());
			stmt.setDouble(5, film.getRentRate());
//					stmt.setInt(6, film.getLength());
			stmt.setDouble(6, film.getRepCost());
//					stmt.setString(8, film.getRating());
//					stmt.setString(9, film.getFeatures());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);
				}
			} else {
				film = null;
			}
			conn.commit(); // COMMIT TRANSACTION
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting film " + film);
		}
		return film;
	}
	// ________________________________________________________________________________

	@Override
	public boolean deleteFilm(int filmId) {
		Connection conn = null;
		Film film = new Film();
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "DELETE film FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			int updateCount = stmt.executeUpdate();
			conn.commit(); // COMMIT TRANSACTION
			if (updateCount == 1) {

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}
	// ________________________________________________________________________________

	@Override
	public Film updateFilm(int filmId, Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "UPDATE film SET (title, description, release_year, language_id, rental_duration, rental_rate, "
					+ "replacement_cost ) " + " VALUES (?, ?, ?, 1, ?, ?, ? )";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDesc());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getRentDur());
			stmt.setDouble(5, film.getRentRate());
//			stmt.setInt(6, film.getLength());
			stmt.setDouble(6, film.getRepCost());
//			stmt.setString(8, film.getRating());
//			stmt.setString(9, film.g
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				conn.commit();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return null;
		}
		return film;
	}

	// ________________________________________________________________________________

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
	// ________________________________________________________________________________

	public String findCategory(int filmId) {
		String cat = "";

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT cat.name \n" + "From Category cat Join film flm \n" + "On cat.id = flm.id \n"
					+ "WHERE flm.id = ? ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				cat = rs.getString("name");
			} else {
				cat = "none";
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return cat;

	}

	
	
}
