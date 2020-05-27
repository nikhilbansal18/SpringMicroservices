package com.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.model.CatalogItem;
import com.ms.model.Movie;
import com.ms.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	// Circuit breaker pattern
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
			commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            })
	
	//Another option bulkhead pattern -> In which you can divide thread pools between microservices. 
	//So, slow microservice does not impact the allocation of threads of fast one.
	
	public CatalogItem getCatalogItem(Rating rating) {
		//For each MovieId call for movie info service and get details
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
		// put them all together
		return new CatalogItem(movie.getMovieName(), movie.getMovieDesc(), rating.getRating());
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name not found", "", rating.getRating());
	}	

}
