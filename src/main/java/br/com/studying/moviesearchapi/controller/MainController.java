package br.com.studying.moviesearchapi.controller;

import java.io.PrintWriter;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.studying.moviesearchapi.HTMLGenerator;
import br.com.studying.moviesearchapi.domain.movie.Movie;
import br.com.studying.moviesearchapi.domain.movie.MovieHttpClient;
import br.com.studying.moviesearchapi.domain.movie.MovieParseService;

@RestController
@RequestMapping("/movies")
public class MainController {

	@Value("${app.uri}")
	private String uri;

	@Autowired
	MovieHttpClient client;

	@Autowired
	MovieParseService service;

	@GetMapping
	public HttpResponse<String> getMovies() throws ParseException {

		String response = client.getMovies(uri).body();
		System.out.println(response);

		String jsonMovies = service.parseAllMovies(response);
		System.out.println(jsonMovies);

		String[] moviesArray = service.parseJsonMovies(jsonMovies);
		List<String> movieJsonList = Arrays.stream(moviesArray).toList();
		for (String movie : movieJsonList) {
			System.out.println(movie);
		}

		String[] titles = service.parseAtributes(moviesArray, "\"title\":");
		List<String> titlesList = Arrays.stream(titles).toList();
		for (String title : titlesList) {
			System.out.println(title);
		}

		String[] urlImages = service.parseAtributes(moviesArray, "\"poster_path\":");
		List<String> urlImagesList = Arrays.stream(urlImages).toList();
		for (String urlImage : urlImagesList) {
			System.out.println(urlImage);
		}

		double[] rating = service.parseRating(moviesArray, "\"vote_average\":");
		List<Double> ratingList = new ArrayList<>();
		for (double ratingIndex : rating) {
			ratingList.add(ratingIndex);
			System.out.println(ratingIndex);
		}

		String[] releaseDates = service.parseAtributes(moviesArray, "\"release_date\":");
		List<String> releaseDateList = Arrays.stream(releaseDates).toList();
		for (String date : releaseDateList) {
			System.out.println(date);
		}

		List<Movie> movieList = new ArrayList<>();

		int cont = 0;
		for (String title : titlesList) {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDateList.get(cont));
			Movie movie = new Movie(title, ratingList.get(cont), urlImagesList.get(cont), date);
			movieList.add(movie);
			cont++;
		}

		for (Movie movie : movieList) {
			System.out.println(movie.toString());
		}
		
		new HTMLGenerator().generate(movieList);

		return null;

	}

}
