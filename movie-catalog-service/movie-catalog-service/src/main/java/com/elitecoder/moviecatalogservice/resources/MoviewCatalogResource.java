package com.elitecoder.moviecatalogservice.resources;

import com.elitecoder.moviecatalogservice.entity.CatalogItem;
import com.elitecoder.moviecatalogservice.entity.Movie;
import com.elitecoder.moviecatalogservice.entity.Rating;
import com.elitecoder.moviecatalogservice.entity.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.xml.ws.ResponseWrapper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MoviewCatalogResource {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        //get all the rated movies IDs
        UserRatings userRatings = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId,
                UserRatings.class);
        List<Rating> ratings = userRatings.getRatingList();

        //for each movie ID, call movie info service and get details
        return ratings.stream().map(rating -> {
            Movie movie= restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie
                    .class);
            //put them all to gather.
            return new CatalogItem(movie.getName(),movie.getOverview(),rating.getRating());
        }).collect(Collectors.toList());

    }
}
 /*
            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
*/