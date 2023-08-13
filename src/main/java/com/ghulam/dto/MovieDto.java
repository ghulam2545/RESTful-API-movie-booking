package com.ghulam.dto;

import com.ghulam.enums.Genre;
import com.ghulam.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDto {
    private String name;
    private Genre genre;
    private String director;
    private int duration;

    public MovieDto toMovieDto(Movie movie) {
        this.name = movie.getName();
        this.genre = movie.getGenre();
        this.director = movie.getDirector();
        this.duration = movie.getDuration();
        return this;
    }
}
