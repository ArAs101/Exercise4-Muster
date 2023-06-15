package at.ac.fhcampuswien.fhmdb.state_pattern;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.List;

public class NotSortedState implements SortedState {
    private List<Movie> movies;

    public NotSortedState(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public List<Movie> sort(List<Movie> movieList) {
        return movieList;
    }

    @Override
    public SortedState toggle() {
        return new SortedAscendingState(movies);
    }
}
