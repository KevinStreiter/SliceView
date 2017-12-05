package ch.fhnw.ima.sliceview.logic.impl;

import ch.fhnw.ima.sliceview.app.ApplicationContext;
import ch.fhnw.ima.sliceview.logic.*;
import ch.fhnw.ima.sliceview.present.histo.HistogramView;

public class SimpleHistogramViewController implements HistogramViewController {

    private int index;
    private ApplicationContext applicationContext;
    private HistogramView histogramView;
    private MouseSelection mouseSelection;
    private GridData gridData;

    public SimpleHistogramViewController(ApplicationContext applicationContext, HistogramView histogramView) {
        this.applicationContext = applicationContext;
        this.mouseSelection = applicationContext.getMouseSelection();
        this.histogramView = histogramView;
        this.gridData = applicationContext.getGridData();

        getRange();
    }

    public void setStartPosition(double startPosition) {
        mouseSelection.setStartPosition(calculateRange(startPosition));
    }

    public void setEndPosition(double endPosition) {
        mouseSelection.setEndPosition(calculateRange(endPosition));
    }

    public void setSelectedHistogramValue(double histogramValue){
        mouseSelection.setSelectedHistogramValue(calculateRange(histogramValue));
    }

    public void getMin(int min){
        while(index == applicationContext.getHistogram().getBinIndex(min) && min > applicationContext.getGridData().getMinValue()){
            min -= 1;
        }
        mouseSelection.setMin(min);
    }

    public void getMax(int max){
        while(index == applicationContext.getHistogram().getBinIndex(max) && max < applicationContext.getGridData().getMaxValue()){
            max += 1;
        }
        mouseSelection.setMax(max);
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

        histogramView.setOnMouseMoved((event) -> {

            setSelectedHistogramValue(event.getX());
            getBinRange(event.getX());
        });

        histogramView.setOnMouseExited((event) -> {

            mouseSelection.setMin(applicationContext.getGridData().getMinValue());
            mouseSelection.setMax(applicationContext.getGridData().getMaxValue());
        });
    }

    private double calculateRange(double position) {
        int min = gridData.getMinValue();
        int max = gridData.getMaxValue();
        double width = histogramView.getWidth();
        return ((position/width)*(max-min))+min;
    }

    private void getBinRange(double Position){
        double range = calculateRange(Position);
        index = applicationContext.getHistogram().getBinIndex((int)range);
        getMin((int)range);
        getMax((int)range);
    }
}
