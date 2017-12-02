package ch.fhnw.ima.sliceview.present.info;

import ch.fhnw.ima.sliceview.app.ApplicationContext;
import ch.fhnw.ima.sliceview.logic.MouseSelectionListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class InfoPanel extends StackPane {
    private ApplicationContext applicationContext;
    private Label nameLabel;
    private Label coordinatesLabel;
    private Label valueLabel;

    public InfoPanel(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;

        nameLabel = new Label();
        coordinatesLabel = new Label();
        valueLabel = new Label();

        VBox vBox = new VBox();
        vBox.getChildren().add(new Label("Image Name:"));
        vBox.getChildren().add(nameLabel);
        vBox.getChildren().add(new Label("Coordinates:"));
        vBox.getChildren().add(coordinatesLabel);
        vBox.getChildren().add(new Label("Value:"));
        vBox.getChildren().add(valueLabel);

        vBox.setPadding(new Insets(10));
        getChildren().add(vBox);

        refreshDataInformation();

        applicationContext.getGridData().addListener(this::refreshDataInformation);
        applicationContext.getMouseSelection().addListener(new MouseSelectionListener() {
            @Override
            public void selectionChanged() {
                refreshDataInformation();
            }
        });
    }

    private void refreshDataInformation() {

        nameLabel.setText(applicationContext.getGridData().getName());
        coordinatesLabel.setText(String.valueOf((applicationContext.getMouseSelection().getXCoordinate()))+"/"+
                                 String.valueOf((applicationContext.getMouseSelection().getYCoordinate())));
        valueLabel.setText(String.valueOf(applicationContext.getMouseSelection().getValue()));

    }

}