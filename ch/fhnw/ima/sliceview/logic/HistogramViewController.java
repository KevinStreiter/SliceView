package ch.fhnw.ima.sliceview.logic;

public interface HistogramViewController {

    void setStartPosition(double startPosition);

    void setEndPosition(double endPosition);

    void getMin(int min);

    void getMax(int max);

    void setSelectedHistogramValue(double histogramValue);
}
