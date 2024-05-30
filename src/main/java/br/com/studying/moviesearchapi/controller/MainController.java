package br.com.studying.moviesearchapi.controller;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.studying.moviesearchapi.client.ClientHttpConfiguration;

@RestController
@RequestMapping("/movies")
public class MainController {
	
	@Value("${app.uri}")
	private String uri;

	@GetMapping
	public HttpResponse<String> getMovies(){
		
		ClientHttpConfiguration client = new ClientHttpConfiguration();
		
		String response = client.getMovies(uri).body();
		
		System.out.println(response);
		
		return null;
		
	}
	
}
