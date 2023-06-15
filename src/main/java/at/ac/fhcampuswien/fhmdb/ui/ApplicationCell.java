package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.database.WatchlistMovieEntity;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public abstract class ApplicationCell<T> extends ListCell<T> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genre = new Label();
    private final JFXButton detailBtn = new JFXButton("Show Details");
    protected final JFXButton actionBtn = new JFXButton("Action Btn");
    private final HBox header = new HBox(title, detailBtn, actionBtn);
    private final VBox layout = new VBox(header, detail, genre);
    private boolean collapsedDetails = true;

    public ApplicationCell() {
        super();
        // color scheme
        detailBtn.setStyle("-fx-background-color: #f5c518;");
        // set margin of detailBtn
        HBox.setMargin(detailBtn, new Insets(0, 10, 0, 10));
        actionBtn.setStyle("-fx-background-color: #f5c518;");
        title.getStyleClass().add("text-yellow");
        detail.getStyleClass().add("text-white");
        genre.getStyleClass().add("text-white");
        genre.setStyle("-fx-font-style: italic");
        layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setHgrow(title, Priority.ALWAYS);
        header.setHgrow(detailBtn, Priority.ALWAYS);
        title.setMaxWidth(Double.MAX_VALUE);
        //detailBtn.setMaxWidth(Double.MAX_VALUE);

        // layout
        title.fontProperty().set(title.getFont().font(20));
        detail.setWrapText(true);
        layout.setPadding(new Insets(10));

        detailBtn.setOnMouseClicked(mouseEvent -> {
            if (collapsedDetails) {
                T t = getItem();
                layout.getChildren().add(getDetails());
                collapsedDetails = false;
                detailBtn.setText("Hide Details");
            } else {
                layout.getChildren().remove(3);
                collapsedDetails = true;
                detailBtn.setText("Show Details");
            }
            setGraphic(layout);
        });

        actionBtn.setOnMouseClicked(mouseEvent -> {
            //onActionBtnClick.onClick(getItem());
        });
    }

    protected abstract VBox getDetails();

    @Override
    protected void updateItem(T t, boolean empty) {
        super.updateItem(t, empty);
    }

    public JFXButton getDetailBtn() {
        return detailBtn;
    }

    public Label getTitle() {
        return title;
    }

    public Label getGenre() {
        return genre;
    }

    public Label getDetail() {
        return detail;
    }

    public VBox getLayout() {
        return layout;
    }
}
