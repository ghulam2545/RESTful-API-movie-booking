package com.ghulam.service;

import com.ghulam.model.Movie;
import java.util.List;

/**
 * ------ expected services ------
 * add a new movie
 * get an existing movie
 * update movie data
 * delete specified movie
 * list all movies
 */
public interface MovieServiceInterface {
    Movie addMovie(Movie movie);
    Movie getMovie(int movieId);
    Movie updateMovie(int movieId, Movie newMovie);
    boolean deleteMovie(int movieId);
    List<Movie> allMovies();
}
