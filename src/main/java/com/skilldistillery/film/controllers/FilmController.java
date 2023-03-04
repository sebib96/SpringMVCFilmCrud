package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDao;

	public void setFilmDao(FilmDAO filmDao) {
		this.filmDao = filmDao;
	}

	@RequestMapping(path = { "/", "home.do" })
	public String goHome(Model model) {
		Film TEST = filmDao.findFilmById(1); // Debug
		model.addAttribute("TestFilm", TEST);

		return "home";
	}

	@RequestMapping(path = "NewFilm.do", method = RequestMethod.POST)
	public ModelAndView makeNewFilm(Film film, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		filmDao.createFilm(film);
		redir.addFlashAttribute("film", film);
		mv.setViewName("redirect:filmAdded.do");

		return mv;
	}

	@RequestMapping(path = "filmAdded.do", method = RequestMethod.GET)
	public ModelAndView createdFilm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewFilm");

		return mv;
	}

	@RequestMapping(path = "GetFilmDataById.do", method = RequestMethod.GET)
	public ModelAndView getFilmbyId(int filmId) {
		ModelAndView mv = new ModelAndView();
		Film film = filmDao.findFilmById(filmId);
		mv.addObject("film", film);
		mv.setViewName("viewFilm");
		return mv;
	}

	@RequestMapping(path = "GetFilmDataByKey.do", method = RequestMethod.GET)
	public ModelAndView getFilmbyKey(String keyWord) {
		ModelAndView mv = new ModelAndView();
		List<Film> film = filmDao.findFilmByKeyWord(keyWord);
		mv.addObject("film", film);
		mv.setViewName("viewFilmList");
		return mv;
	}

	@RequestMapping(path = "Delete.do", method = RequestMethod.POST)
	public ModelAndView deleteFilm(int filmId, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		filmDao.deleteFilm(filmId);
		redir.addFlashAttribute("film", filmId);
		mv.setViewName("redirect:filmDeleted.do");

		return mv;
	}

	@RequestMapping(path = "filmDeleted.do", method = RequestMethod.GET)
	public ModelAndView deletedFilm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");

		return mv;
	}
	
	@RequestMapping(path = "Update.do", method = RequestMethod.GET)
	public ModelAndView updateFilm(int filmId, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		Film film =filmDao.findFilmById(filmId);
		mv.addObject("film",film);
		
		
		mv.setViewName("newUpdate");

		return mv;
	}
	
	@RequestMapping(path = "finalUpdate.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(int id, Film film, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		filmDao.updateFilm(id, film);
		redir.addFlashAttribute("film", film);
		mv.setViewName("redirect:filmUpdated.do");

		return mv;
	}


	@RequestMapping(path = "filmUpdated.do", method = RequestMethod.GET)
	public ModelAndView updatedFilm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewFilm");

		return mv;
	}
}
