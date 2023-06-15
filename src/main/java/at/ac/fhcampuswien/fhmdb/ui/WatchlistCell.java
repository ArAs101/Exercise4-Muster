package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.database.WatchlistMovieEntity;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.stream.Collectors;

public class WatchlistCell extends ApplicationCell<WatchlistMovieEntity> {
    public WatchlistCell(ClickEventHandler<WatchlistMovieEntity> removeFromWatchlistClick) {
        super();
        actionBtn.setText("Remove");
        actionBtn.setOnMouseClicked(mouseEvent -> {
            removeFromWatchlistClick.onClick(getItem());
        });
    }

    @Override
    protected VBox getDetails() {
        VBox details = new VBox();
        Label releaseYear = new Label("Release Year: " + getItem().getReleaseYear());
        Label length = new Label("Length: " + getItem().getLengthInMinutes() + " minutes");
        Label rating = new Label("Rating: " + getItem().getRating());

        releaseYear.getStyleClass().add("text-white");
        length.getStyleClass().add("text-white");
        rating.getStyleClass().add("text-white");

        details.getChildren().add(releaseYear);
        details.getChildren().add(rating);
        details.getChildren().add(length);
        return details;
    }

    @Override
    protected void updateItem(WatchlistMovieEntity watchlistMovieEntity, boolean empty) {
        super.updateItem(watchlistMovieEntity, empty);

        if (empty || watchlistMovieEntity == null) {
            setGraphic(null);
            setText(null);
        } else {
            this.getStyleClass().add("movie-cell");
            super.getTitle().setText(watchlistMovieEntity.getTitle());
            super.getDetail().setText(
                    watchlistMovieEntity.getDescription() != null
                            ? watchlistMovieEntity.getDescription()
                            : "No description available"
            );

            if (this.getScene() != null) {
                super.getDetail().setMaxWidth(this.getScene().getWidth() - 30);

                String genres = watchlistMovieEntity.getGenres()
                        .stream()
                        .map(Enum::toString)
                        .collect(Collectors.joining(", "));
                super.getGenre().setText(genres);
                setGraphic(super.getLayout());
            }
        }
    }
}

