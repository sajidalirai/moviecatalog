package com.elitecoder.movieinfoservice.resources;

import com.elitecoder.movieinfoservice.entity.Movie;
import com.elitecoder.movieinfoservice.entity.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MoviewResource {


    @Value("${api.key}")
    private String apikey;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){

        System.out.println("Before calling TheMovieDB");
        String url = "http://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apikey;
        System.out.println(url);
        System.out.println("***********************************");
        //https://api.themoviedb.org/3/movie/5?api_key=7d5bbecb609e72d7140962f40af256ae
        MovieSummary movieSummary = restTemplate.getForObject(
                url, MovieSummary.class);

        System.out.println("After calling TheMovieDB");


        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());

    }

}
