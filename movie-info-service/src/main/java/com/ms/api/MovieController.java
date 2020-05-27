package com.ms.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ms.model.*;;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		//https://api.themoviedb.org/3/movie/550?api_key=0094e54a5934d89802cec42e897bd0ab
		String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
		MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);
		
		return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
		
	}
	
}
