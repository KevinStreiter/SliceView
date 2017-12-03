package ch.fhnw.ima.sliceview.logic.impl;

import ch.fhnw.ima.sliceview.app.ApplicationContext;
import ch.fhnw.ima.sliceview.logic.*;
import ch.fhnw.ima.sliceview.present.histo.HistogramView;

import java.util.ArrayList;
import java.util.List;

public class SimpleHistogramViewController implements HistogramViewController {

    private HistogramView histogramView;
    private MouseSelection mouseSelection;
    private ImageModel imageModel;
    private GridData gridData;
    private List<HistogramViewControllerListener> listeners;

    public SimpleHistogramViewController(ApplicationContext applicationContext, HistogramView histogramView) {
        listeners = new ArrayList<>();
        this.mouseSelection = applicationContext.getMouseSelection();
        this.histogramView = histogramView;
        this.imageModel = applicationContext.getImageModel();
        this.gridData = applicationContext.getGridData();
        getRange();
    }

    private void getRange(){

        histogramView.setOnMousePressed((event) -> {

            setStartPosition(event.getX());
            setEndPosition(event.getX());
        });

        histogramView.setOnMouseDragged((event -> {
            setEndPosition(event.getX());

        }));

        histogramView.setOnMouseReleased((event -> {
            setEndPosition(event.getX());
        }));
    }

    public void setStartPosition(double startPosition) {
        imageModel.setMin(calculateRange(startPosition));
    }

    public void setEndPosition(double endPosition) {
        imageModel.setMax(calculateRange(endPosition));
    }

    private int calculateRange(double position) {

        int min = gridData.getMinValue();
        int max = gridData.getMaxValue();
        double width = histogramView.getWidth();
        return (int)(((position/width)*(max-min))+min);
    }

    public void addListener(HistogramViewControllerListener listener){
        listeners.add(listener);
    }

    private void fireHistogramChanged() {
        for (HistogramViewControllerListener listener : listeners) {
            listener.histogramChanged();
        }
    }
}
