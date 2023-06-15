package at.ac.fhcampuswien.fhmdb.state_pattern;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.List;

public interface SortedState {
    List<Movie> sort(List<Movie> movieList);
    SortedState toggle();
}
