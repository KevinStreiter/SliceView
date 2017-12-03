package ch.fhnw.ima.sliceview.logic;

public interface HistogramViewController {

    void setStartPosition(double startPosition);

    void setEndPosition(double endPosition);

    void addListener(HistogramViewControllerListener listener);

}
