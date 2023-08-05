package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.entity.Movie;
import com.example.entity.MovieSummary;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Value("${api.key}")
	private String apiKey;
	@Autowired
	private RestTemplate template;

	@RequestMapping("/{movieId}")
	public Movie getMovie(@PathVariable("movieId") String id) {

		MovieSummary movieSummary = template
				.getForObject("https://api.themoviedb.org/3/movie/" + id + "?api_key=" + apiKey, MovieSummary.class);
		return new Movie(id, movieSummary.getTitle(),movieSummary.getOverview());

	}

}
