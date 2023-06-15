package at.ac.fhcampuswien.fhmdb.state_pattern;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.Comparator;
import java.util.List;

public class SortedAscendingState implements SortedState {
    private List<Movie> movies;

    public SortedAscendingState(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public List<Movie> sort(List<Movie> movieList) {
        return movieList.stream().sorted(Comparator.comparing(Movie::getTitle)).toList();
    }

    @Override
    public SortedState toggle() {
        return new SortedDescendingState(movies);
    }
}
