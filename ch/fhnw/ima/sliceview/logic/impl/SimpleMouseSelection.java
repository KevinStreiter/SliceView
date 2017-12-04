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
    private double value;
    private double histogramValue;
    private int min;
    private int max;
    private List<MouseSelectionListener> listeners;
    private List<MouseSelectionListener> listeners2;
    private GridData gridData;
    private ApplicationContext applicationContext;

    public SimpleMouseSelection(ApplicationContext applicationContext, GridData gridData) {
        this.applicationContext = applicationContext;
        this.gridData = gridData;
        listeners = new ArrayList<>();
        listeners2 = new ArrayList<>();

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

    public void getMin(int min){
        this.min = min;
        setImage();

    }

    public void getMax(int max){
        this.max = max;
        setImage();

    }

    public Image setImage(){
        return applicationContext.getImageModel().getSelection(min, max);
    }

    public void addListener(MouseSelectionListener listener) {
        listeners.add(listener);
    }

    public void addListener2(MouseSelectionListener listener) {
        listeners2.add(listener);
    }

    private void fireSelectionChanged() {
        for (MouseSelectionListener listener : listeners) {
            listener.selectionChanged();
        }
    }

    private void fireHistogramChange() {
        for (MouseSelectionListener listener : listeners2) {
            listener.histogramChanged();
        }
    }
}
