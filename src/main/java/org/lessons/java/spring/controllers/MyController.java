package org.lessons.java.spring.controllers;

import java.util.Arrays;
import java.util.List;

import org.lessons.java.spring.classes.Movie;
import org.lessons.java.spring.classes.Song;
import org.lessons.java.spring.interfaces.Searchable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyController {
	private List<Searchable> movies = getMovies();
	private List<Searchable> songs = getSongs();

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("name", "Alessio");

		return "home.html";
	}

	@GetMapping("/movies")
	public String movies(Model model) {
		model.addAttribute("name", "Alessio");
		model.addAttribute("movies", movies);
		
		return "movies.html";
	}

	@GetMapping("/songs")
	public String songs(Model model) {
		model.addAttribute("name", "Alessio");
		model.addAttribute("songs", songs);
		
		return "songs.html";
	}

	@GetMapping("/movies/{id}")
	public String movie(@PathVariable int id, Model model) {
		String page = "movie.html";

		if (search(movies, id)) {
			model.addAttribute("name", "Alessio");
			model.addAttribute("movie", getSearchable(movies, id));
		} else
			page = "error.html";

		return page;
	}

	@GetMapping("/songs/{id}")
	public String song(@PathVariable int id, Model model) {
		String page = "song.html";

		if (search(songs, id)) {
			model.addAttribute("name", "Alessio");
			model.addAttribute("song", getSearchable(songs, id));
		} else
			page = "error.html";

		return page;
	}

	@GetMapping("/error")
	public String error() {
		return "error.html";
	}

	private boolean search(List<Searchable> list, int id) {
		return list.stream().filter(movie -> movie.getId() == id).findFirst().isPresent();
	}

	private Searchable getSearchable(List<Searchable> list, int id) {
		return list.stream().filter(movie -> movie.getId() == id).findFirst().get();
	}

	private List<Searchable> getMovies() {
		return Arrays.asList(new Movie("Il Signore degli Anelli: La Compagnia dell'Anello", "/images/compagnia.jpg"),
				new Movie("Il Signore degli Anelli: Le Due Torri", "/images/torri.jpg"),
				new Movie("Il Signore degli Anelli: Il Ritorno del Re", "/images/re.jpg"));
	}

	private List<Searchable> getSongs() {
		return Arrays.asList(new Song("May It Be", "/images/may-it-be.jpg"),
				new Song("Gollum's Song", "/images/gollum.jpg"),
				new Song("Into The West", "/images/into-the-west.jpg"));
	}
}
