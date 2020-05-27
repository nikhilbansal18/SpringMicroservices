package com.ms.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ms.model.*;
import com.ms.service.MovieInfo;
import com.ms.service.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MovieInfo movieInfo;
	
	@Autowired
	private UserRatingInfo userRatingInfo;
	
	@RequestMapping("{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId ){
		
		UserRating ratings = userRatingInfo.getUserRating(userId);
			
		return ratings.getUserRating().stream().map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());
	}
	
	
	// Only works if added on a method
	/*public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId ){
		// keep hard coded value or from the cache
		return Arrays.asList(new CatalogItem("No movie", "", 0));
	}*/
	
}
