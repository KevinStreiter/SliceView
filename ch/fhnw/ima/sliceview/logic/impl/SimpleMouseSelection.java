package ch.fhnw.ima.sliceview.logic.impl;

import ch.fhnw.ima.sliceview.app.ApplicationContext;
import ch.fhnw.ima.sliceview.logic.GridData;
import ch.fhnw.ima.sliceview.logic.GridDataListener;
import ch.fhnw.ima.sliceview.logic.MouseSelection;
import ch.fhnw.ima.sliceview.logic.MouseSelectionListener;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;


public class SimpleMouseSelection implements MouseSelection {

    private int xCoordinate = -1;
    private int yCoordinate = -1;
    private int min;
    private int max;
    private double value;
    private double histogramValue;
    private Image selectionImage;
    private List<MouseSelectionListener> listeners;
    private List<MouseSelectionListener> histogramlistener;
    private GridData gridData;
    private ApplicationContext applicationContext;

    public SimpleMouseSelection(ApplicationContext applicationContext, GridData gridData) {
        this.applicationContext = applicationContext;
        this.gridData = gridData;
        listeners = new ArrayList<>();
        histogramlistener = new ArrayList<>();

        applicationContext.getGridData().addListener(new GridDataListener() {
            @Override
            public void dataChanged() {
                setXCoordinate(-1);
                setYCoordinate(-1);
            }
        });
    }

    public int getXCoordinate(){
        return this.xCoordinate;
    }

    public void setXCoordinate(int xCoordinate){
        this.xCoordinate = xCoordinate;
        setValue();
    }

    public int getYCoordinate(){
        return this.yCoordinate;
    }

    public void setYCoordinate(int yCoordinate){
        this.yCoordinate = yCoordinate;
        setValue();
    }

    public double getValue(){
        return this.value;
    }

    public void setStartPosition(double startPosition){
        applicationContext.getImageModel().setMin((int)startPosition);
        fireSelectionChanged();
    }

    public void setEndPosition(double endPosition){
        applicationContext.getImageModel().setMax((int)endPosition);
        fireSelectionChanged();
    }

    public void setValue(){
        this.value = gridData.getValue(xCoordinate, yCoordinate);
        fireSelectionChanged();
    }

    public void setSelectedHistogramValue(double histogramValue){
        this.histogramValue = histogramValue;
        fireHistogramChange();
    }

    public double getSelectedHistogramValue(){
        return this.histogramValue;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min){
        this.min = min;
        setSelectionImage();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max){
        this.max = max;
        setSelectionImage();
    }

    public Image getSelectionImage() {
        return this.selectionImage;
    }

    public void setSelectionImage(){
        this.selectionImage = applicationContext.getImageModel().getSelectionImage(min, max);
        fireHistogramChange();
    }

    public void addListener(MouseSelectionListener listener) {
        listeners.add(listener);
    }

    public void addHistogramListener(MouseSelectionListener listener) {
        histogramlistener.add(listener);
    }

    private void fireSelectionChanged() {
        for (MouseSelectionListener listener : listeners) {
            listener.selectionChanged();
        }
    }

    private void fireHistogramChange() {
        for (MouseSelectionListener listener : histogramlistener) {
            listener.histogramChanged();
        }
    }
}
