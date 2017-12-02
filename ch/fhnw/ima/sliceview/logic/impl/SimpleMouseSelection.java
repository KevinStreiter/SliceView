package ch.fhnw.ima.sliceview.logic.impl;

import ch.fhnw.ima.sliceview.logic.GridData;
import ch.fhnw.ima.sliceview.logic.MouseSelection;
import ch.fhnw.ima.sliceview.logic.MouseSelectionListener;

import java.util.ArrayList;
import java.util.List;


public class SimpleMouseSelection implements MouseSelection {

    private int xCoordinate;
    private int yCoordinate;
    private double value;
    private List<MouseSelectionListener> listeners;
    private GridData gridData;

    public SimpleMouseSelection(GridData gridData) {
        this.gridData = gridData;
        listeners = new ArrayList<>();
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

    public void setValue(){
        this.value = gridData.getValue(xCoordinate,yCoordinate);
        fireSelectionChanged();
    }

    public void addListener(MouseSelectionListener listener) {
        listeners.add(listener);
    }

    private void fireSelectionChanged() {
        for (MouseSelectionListener listener : listeners) {
            listener.selectionChanged();
        }
    }

}
