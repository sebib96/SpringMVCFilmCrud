package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public ModelAndView getFilmbyId(int id) {
		ModelAndView mv = new ModelAndView();
		Film film = filmDao.findFilmById(id);
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
	

}
