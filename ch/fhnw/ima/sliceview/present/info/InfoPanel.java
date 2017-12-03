package ch.fhnw.ima.sliceview.present.info;

import ch.fhnw.ima.sliceview.app.ApplicationContext;
import ch.fhnw.ima.sliceview.logic.MouseSelectionListener;
import ch.fhnw.ima.sliceview.present.image.ImageView;
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
        applicationContext.getMouseSelection().addListener(this::refreshDataInformation);

    }

    private void refreshDataInformation() {

        int xCoordinate = applicationContext.getMouseSelection().getXCoordinate();
        int yCoordinate = applicationContext.getMouseSelection().getYCoordinate();
        double value = applicationContext.getMouseSelection().getValue();

        if(xCoordinate == -1 || yCoordinate == -1) {
            coordinatesLabel.setText("-/-");
            valueLabel.setText("-");
        }
        else {

            coordinatesLabel.setText(String.valueOf(xCoordinate) + "/" +
                    String.valueOf(yCoordinate));
            valueLabel.setText(String.valueOf(value));
        }
        nameLabel.setText(applicationContext.getGridData().getName());
    }

}