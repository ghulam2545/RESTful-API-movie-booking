package com.ghulam.service.impl;

import com.ghulam.model.Movie;
import com.ghulam.repository.MovieRepository;
import com.ghulam.service.MovieServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MovieService implements MovieServiceInterface {
    final MovieRepository movieRepo;

    public MovieService(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public Movie addMovie(Movie movie) {
        try {
            return movieRepo.save(movie);
        } catch (Exception e) {
            System.out.println("Could not add a new movie.");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Movie getMovie(int movieId) {
        try {
            var movieData =  movieRepo.findById(movieId);
            if(movieData.isPresent()) {
                return movieData.get();
            } else {
                throw new NoSuchElementException();
            }
        } catch (Exception e) {
            System.out.println("Can not find movie by id: " + movieId);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Movie updateMovie(int movieId, Movie newMovie) {
        try {
            var movieData = movieRepo.findById(movieId);
            if(movieData.isPresent()) {
                Movie movie = movieData.get();
                movie.setName(newMovie.getName());
                movie.setGenre(newMovie.getGenre());
                movie.setDirector(newMovie.getDirector());
                movie.setDuration(newMovie.getDuration());

                return movieRepo.save(movie);
            } else {
                throw new NoSuchElementException();
            }
        } catch (Exception e) {
            System.out.println("Could not update movie by id: " + movieId);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteMovie(int movieId) {
        try {
            movieRepo.deleteById(movieId);
            return true;
        } catch (Exception e) {
            System.out.println("Could not delete movie by id: " + movieId);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Movie> allMovies() {
        try {
            return movieRepo.findAll();
        } catch (Exception e) {
            System.out.println("Can not get all movies");
            e.printStackTrace();
        }
        return null;
    }
}
