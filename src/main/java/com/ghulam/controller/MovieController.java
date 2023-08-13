package com.ghulam.controller;

import com.ghulam.dto.MovieDto;
import com.ghulam.model.Movie;
import com.ghulam.response.Response;
import com.ghulam.service.impl.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ------ expected movie end-points ------
 * add a new movie
 * get an existing movie
 * update movie data
 * delete specified movie
 * list all movie
 */

@RestController
@RequestMapping("/api")
public class MovieController {
    final MovieService movieService;
    private Movie movie;
    private MovieDto movieDto;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
        movie = new Movie();
        movieDto = new MovieDto();
    }

    @PostMapping("/add_movie")
    public ResponseEntity<Object> addMovie(@RequestBody MovieDto dto) {
        movie = movie.toMovie(dto);

        try {
            movie = movieService.addMovie(movie);
            movieDto = movieDto.toMovieDto(movie);

            return Response.handler("A new movie added successfully.", HttpStatus.OK, movieDto);
        } catch (Exception e) {
            System.out.println("Could not add a new movie.");
            e.printStackTrace();
        }
        return Response.handler("Could not add a new movie.", HttpStatus.BAD_REQUEST, null);
    }

    @GetMapping("/get_movie/{movieId}")
    public ResponseEntity<Object> getMovie(@PathVariable int movieId) {
        try {
            movie = movieService.getMovie(movieId);
            movieDto = movieDto.toMovieDto(movie);
            return Response.handler("Getting specified movie.", HttpStatus.OK, movieDto);
        } catch (Exception e) {
            System.out.println("Can not find movie by id: " + movieId);
            e.printStackTrace();
        }
        return Response.handler("Can not find movie.", HttpStatus.BAD_REQUEST, null);
    }

    @PutMapping("/update_movie/{movieId}")
    public ResponseEntity<Object> updateMovie(@PathVariable int movieId, @RequestBody MovieDto dto) {
        movie = movie.toMovie(dto);
        try {
            movie = movieService.updateMovie(movieId, movie);
            movieDto = movieDto.toMovieDto(movie);
            return Response.handler("Movie data updated successfully.", HttpStatus.OK, movieDto);
        } catch (Exception e) {
            System.out.println("Could not update movie by id: " + movieId);
            e.printStackTrace();
        }
        return Response.handler("Can\'t update movie data.", HttpStatus.BAD_REQUEST, null);
    }

    @DeleteMapping("/delete_movie/{movieId}")
    public ResponseEntity<Object> deleteMovie(@PathVariable int movieId) {
        try {
            if(movieService.deleteMovie(movieId)) {
                return Response.handler("Movie data updated successfully.", HttpStatus.OK, null);
            }
        } catch (Exception e) {
            System.out.println("Could not delete user by id: " + movieId);
            e.printStackTrace();
        }
        return Response.handler("Can\'t delete user data.", HttpStatus.BAD_REQUEST, null);
    }

    @GetMapping("/all_movies")
    public ResponseEntity<Object> listAllMovies() {
        try {
            List<Movie> movies = movieService.allMovies();
            List<MovieDto> allMovies = new ArrayList<>();
            for(Movie m:movies) {
                MovieDto dto = movieDto.toMovieDto(m);
                // System.out.println(dto.toString());
                /**
                 * here is some issue with all movies
                 */

                allMovies.add(dto);
            }

            return Response.handler("Getting all movies data.", HttpStatus.OK, allMovies);
        } catch (Exception e) {
            System.out.println("Can not get all movies");
            e.printStackTrace();
        }
        return Response.handler("Can\'t get movies data.", HttpStatus.BAD_REQUEST, null);
    }
}
