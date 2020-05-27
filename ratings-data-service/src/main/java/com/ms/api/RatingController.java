package com.ms.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.model.Rating;
import com.ms.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingController {
	
	@RequestMapping("{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
			return new Rating(movieId, 5);
	}

	@RequestMapping("users/{userId}")
	public UserRating getUserRaing(@PathVariable("userId") String userId) {
		
		List<Rating> ratings = Arrays.asList(	
				new Rating("100", 4),
				new Rating("200", 5)
		);
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
	}
	
}
