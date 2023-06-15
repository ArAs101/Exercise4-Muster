package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.stream.Collectors;

public class MovieCell extends ApplicationCell<Movie> {
    public MovieCell(ClickEventHandler<Movie> addToWatchlistClicked) {
        super();
        actionBtn.setText("Add to Watchlist");
        actionBtn.setOnMouseClicked(mouseEvent -> {
            addToWatchlistClicked.onClick(getItem());
        });
    }


    @Override
    protected VBox getDetails() {
        VBox details = new VBox();
        Label releaseYear = new Label("Release Year: " + getItem().getReleaseYear());
        Label length = new Label("Length: " + getItem().getLengthInMinutes() + " minutes");
        Label rating = new Label("Rating: " + getItem().getRating() + "/10");

        Label directors = new Label("Directors: " + String.join(", ", getItem().getDirectors()));
        Label writers = new Label("Writers: " + String.join(", ", getItem().getWriters()));
        Label mainCast = new Label("Main Cast: " + String.join(", ", getItem().getMainCast()));

        releaseYear.getStyleClass().add("text-white");
        length.getStyleClass().add("text-white");
        rating.getStyleClass().add("text-white");
        directors.getStyleClass().add("text-white");
        writers.getStyleClass().add("text-white");
        mainCast.getStyleClass().add("text-white");

        details.getChildren().add(releaseYear);
        details.getChildren().add(rating);
        details.getChildren().add(length);
        details.getChildren().add(directors);
        details.getChildren().add(writers);
        details.getChildren().add(mainCast);
        return details;
    }

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);
        if (empty || movie == null) {
            setGraphic(null);
            setText(null);
        } else {
            this.getStyleClass().add("movie-cell");
            super.getTitle().setText(movie.getTitle());
            super.getDetail().setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"
            );

            if (this.getScene() != null) {
                super.getDetail().setMaxWidth(this.getScene().getWidth() - 30);

                String genres = movie.getGenres()
                        .stream()
                        .map(Enum::toString)
                        .collect(Collectors.joining(", "));
                super.getGenre().setText(genres);
                setGraphic(super.getLayout());
            }
        }
    }
}

