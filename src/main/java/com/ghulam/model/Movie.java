package com.ghulam.model;

import com.ghulam.dto.MovieDto;
import com.ghulam.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false)
    private int movieId;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Genre genre;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "duration", nullable = false)
    private int duration;

    public Movie toMovie(MovieDto dto) {
        this.name = dto.getName();
        this.genre = dto.getGenre();
        this.director = dto.getDirector();
        this.duration = dto.getDuration();
        return this;
    }
}
