package ch.fhnw.ima.sliceview.present.info;

import ch.fhnw.ima.sliceview.app.ApplicationContext;
import ch.fhnw.ima.sliceview.logic.MouseSelectionListener;
import ch.fhnw.ima.sliceview.present.image.ImageView;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class InfoPanel extends StackPane {

    private Label nameLabel;
    private Label coordinatesLabel;
    private Label valueLabel;
    private Label rangeLabel;
    private ApplicationContext applicationContext;

    public InfoPanel(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;

        nameLabel = new Label();
        coordinatesLabel = new Label();
        valueLabel = new Label();
        rangeLabel = new Label();

        VBox vBox = new VBox();
        vBox.getChildren().add(new Label("Image Name:"));
        vBox.getChildren().add(nameLabel);

        Region fillerRegion = new Region();
        vBox.getChildren().add(fillerRegion);
        VBox.setVgrow(fillerRegion, Priority.ALWAYS);

        vBox.getChildren().add(new Label("Coordinates:"));
        vBox.getChildren().add(coordinatesLabel);

        Region fillerRegion2 = new Region();
        vBox.getChildren().add(fillerRegion2);
        VBox.setVgrow(fillerRegion2, Priority.ALWAYS);

        vBox.getChildren().add(new Label("Value:"));
        vBox.getChildren().add(valueLabel);

        Region fillerRegion3 = new Region();
        vBox.getChildren().add(fillerRegion3);
        VBox.setVgrow(fillerRegion3, Priority.ALWAYS);

        vBox.getChildren().add(new Label("Range:"));
        vBox.getChildren().add(rangeLabel);

        Region fillerRegion4 = new Region();
        vBox.getChildren().add(fillerRegion4);
        VBox.setVgrow(fillerRegion4, Priority.ALWAYS);

        vBox.setPadding(new Insets(20));
        getChildren().add(vBox);

        refreshDataInformation();

        applicationContext.getGridData().addListener(this::refreshDataInformation);
        applicationContext.getMouseSelection().addListener(new MouseSelectionListener() {
            @Override
            public void selectionChanged() {
                refreshDataInformation();
            }

            @Override
            public void histogramChanged() {
            }
        });

        applicationContext.getMouseSelection().addHistogramListener(new MouseSelectionListener() {
            @Override
            public void selectionChanged() {
            }

            @Override
            public void histogramChanged() {
                refreshDataInformation();
            }
        });
    }

    private void refreshDataInformation() {

        int xCoordinate = applicationContext.getMouseSelection().getXCoordinate();
        int yCoordinate = applicationContext.getMouseSelection().getYCoordinate();
        double value = applicationContext.getMouseSelection().getValue();
        int min = applicationContext.getMouseSelection().getMin();
        int max = applicationContext.getMouseSelection().getMax();

        if(xCoordinate == -1 || yCoordinate == -1) {
            coordinatesLabel.setText("-/-");
            valueLabel.setText("-");
        }
        else {

            coordinatesLabel.setText(String.valueOf(xCoordinate) + "/" + String.valueOf(yCoordinate));
            valueLabel.setText(String.valueOf(value));
        }
        nameLabel.setText(applicationContext.getGridData().getName());

        if(min != applicationContext.getGridData().getMinValue() || max != applicationContext.getGridData().getMaxValue()){
            if(min != 0 && max != 0) {
                rangeLabel.setText(String.valueOf("[ " + min) + "  " + String.valueOf(max) + " ]");
            }
        }
        else{
            rangeLabel.setText("[ " + "  " + " ]");
        }

    }

}