package br.com.studying.moviesearchapi.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie {
	
	private String title;
	private Double rating;
	private String urlImage;
	private Date releaseDate;
	
	public Movie(String title, Double rating, String urlImage, Date releaseDate) {
		this.title = title;
		this.rating = rating;
		this.urlImage = urlImage;
		this.releaseDate = releaseDate;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Double getRating() {
		return rating;
	}
	
	public String getUrlImage() {
		return urlImage;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	@Override
	public String toString() {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(this.releaseDate);
		return "title: " + this.title + " - "
				+ "rating: " + this.rating + " - "
				+ "urlImage: " + this.urlImage + " - "
				+ "releaseDate: " + date;
	}

}
