package ch.fhnw.ima.sliceview.logic;

public interface MouseSelection {

    int getXCoordinate();

    void setXCoordinate(int xCoordinate);

    int getYCoordinate();

    void setYCoordinate(int yCoordinate);

    double getValue();

    void setValue();

    double getStartRange();

    double getEndRange();

    void addListener(MouseSelectionListener listener);

}
