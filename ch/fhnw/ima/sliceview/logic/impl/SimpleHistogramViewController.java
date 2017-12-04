package ch.fhnw.ima.sliceview.logic.impl;

import ch.fhnw.ima.sliceview.app.ApplicationContext;
import ch.fhnw.ima.sliceview.logic.*;
import ch.fhnw.ima.sliceview.present.histo.HistogramView;

import java.util.ArrayList;
import java.util.List;


public class SimpleHistogramViewController implements HistogramViewController {

    private ApplicationContext applicationContext;
    private HistogramView histogramView;
    private MouseSelection mouseSelection;
    private GridData gridData;
    private int index;

    public SimpleHistogramViewController(ApplicationContext applicationContext, HistogramView histogramView) {
        this.applicationContext = applicationContext;
        this.mouseSelection = applicationContext.getMouseSelection();
        this.histogramView = histogramView;
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

        histogramView.setOnMouseMoved((event) -> {

            setSelectedHistogramValue(event.getX());
            getBinRange(event.getX());
        });
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

    private double calculateRange(double position) {
        int min = gridData.getMinValue();
        int max = gridData.getMaxValue();
        double width = histogramView.getWidth();
        return (((position/width)*(max-min))+min);
    }

    private void getBinRange(double Position){
        double range = calculateRange(Position);
        index = applicationContext.getHistogram().getBinIndex((int)range);
        getMin((int)range);
        getMax((int)range);
    }

    public void getMin(int min){
        while(index == applicationContext.getHistogram().getBinIndex(min)){
            min -= 1;
        }
        mouseSelection.getMin(min);
    }

    public void getMax(int max){
        while(index == applicationContext.getHistogram().getBinIndex(max)){
            max += 1;
        }
        mouseSelection.getMax(max);
    }
}
