package ch.fhnw.ima.sliceview.logic;

import javafx.scene.image.Image;

public interface MouseSelection {

    int getXCoordinate();

    void setXCoordinate(int xCoordinate);

    int getYCoordinate();

    void setYCoordinate(int yCoordinate);

    double getValue();

    void setValue();

    void setStartPosition(double startPosition);

    void setEndPosition(double endPosition);

    double getSelectedHistogramValue();

    void setSelectedHistogramValue(double histogramValue);

    int getMin();

    void setMin(int min);

    int getMax();

    void setMax(int max);

    Image getSelectionImage();

    void setSelectionImage();

    void addListener(MouseSelectionListener listener);

    void addHistogramListener(MouseSelectionListener listener);
}
