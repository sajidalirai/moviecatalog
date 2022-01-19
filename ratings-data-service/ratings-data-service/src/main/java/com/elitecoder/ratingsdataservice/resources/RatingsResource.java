package com.elitecoder.ratingsdataservice.resources;

import com.elitecoder.ratingsdataservice.entity.Rating;
import com.elitecoder.ratingsdataservice.entity.UserRatings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId,4);
    }

    @GetMapping("/users/{userId}")
    public UserRatings getUserRatings(@PathVariable("userId") String userId){
        return new UserRatings(Arrays.asList(
                new Rating("5",4),
                new Rating("500",3)
        ));
    }
}
