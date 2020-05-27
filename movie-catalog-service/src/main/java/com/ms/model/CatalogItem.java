package com.ms.model;

public class CatalogItem {

	private String movieName;
	private String movieDescription;
	private int rating;
	
	public CatalogItem(String movieName, String movieDescription, int rating) {
		//super();
		this.movieName = movieName;
		this.movieDescription = movieDescription;
		this.rating = rating;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
}
