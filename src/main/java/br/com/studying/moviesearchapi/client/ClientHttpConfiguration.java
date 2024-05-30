package br.com.studying.moviesearchapi.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttpConfiguration {
	
	public HttpResponse<String> getMovies(String uri){
		HttpResponse<String> response = null;	
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(uri))
					.method("GET", HttpRequest.BodyPublishers.noBody())
					.build();
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
		return response;
	}

}
