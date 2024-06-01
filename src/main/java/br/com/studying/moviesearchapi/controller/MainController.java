package br.com.studying.moviesearchapi.controller;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.studying.moviesearchapi.client.ClientHttpConfiguration;
import br.com.studying.moviesearchapi.service.ParseService;

@RestController
@RequestMapping("/movies")
public class MainController {
	
	@Value("${app.uri}")
	private String uri;

	@GetMapping
	public HttpResponse<String> getMovies(){
		
		ClientHttpConfiguration client = new ClientHttpConfiguration();
		ParseService service = new ParseService();
		
		String response = client.getMovies(uri).body();
		System.out.println(response);
		
		String jsonMovies = service.parseAllMovies(response);
		System.out.println(jsonMovies);
		
		String[] moviesArray = service.parseJsonMovies(jsonMovies);
		List<String> movieJsonList = Arrays.stream(moviesArray).toList();
		for(String movie: movieJsonList) {
			System.out.println(movie);
		}
		
		String[] titles = service.parseAtributes(moviesArray, "\"title\":");
		List<String> titlesList = Arrays.stream(titles).toList();
		for(String title: titlesList) {
			System.out.println(title);
		}
		
		String[] urlImages = service.parseAtributes(moviesArray, "\"poster_path\":");
		List<String> urlImagesList = Arrays.stream(urlImages).toList();
		for(String urlImage: urlImagesList) {
			System.out.println(urlImage);
		}
		
		double[] rating = service.parseRating(moviesArray, "\"vote_average\":");
		List<Double> ratingList = new ArrayList<>();
		for(double ratingIndex: rating) {
			ratingList.add(ratingIndex);
			System.out.println(ratingIndex);
		}
		
		String[] releaseDates = service.parseAtributes(moviesArray, "\"release_date\":");
		List<String> releaseDateList = Arrays.stream(releaseDates).toList();
		for(String date: releaseDateList) {
			System.out.println(date);
		}
		
		return null;
		
	}
	
}
