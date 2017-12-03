package ch.fhnw.ima.sliceview.present.histo;

import ch.fhnw.ima.sliceview.app.ApplicationContext;
import ch.fhnw.ima.sliceview.logic.*;
import ch.fhnw.ima.sliceview.present.DrawingPane;
import javafx.scene.paint.Color;

public class HistogramView extends DrawingPane {
    private static double BORDER_PERCENTAGE = 0.1;

    private ApplicationContext applicationContext;
    private Histogram histogram;
    private GridData gridData;
    private ImageModel imageModel;

    private boolean isLogarithmicScale;

    HistogramView(ApplicationContext applicationContext, Histogram histogram) {
        this.applicationContext = applicationContext;
        this.gridData = applicationContext.getGridData();
        this.imageModel = applicationContext.getImageModel();
        this.histogram = histogram;

        isLogarithmicScale = true;

        applicationContext.getGridData().addListener(new GridDataListener(){
            public void dataChanged() {
                repaint();
            }
        });

        applicationContext.getMouseSelection().addListener(this::selectionPaint);
        applicationContext.getImageModel().addListener(this::selectionBorder);

    }

    public boolean isLogarithmicScale() {
        return isLogarithmicScale;
    }

    public void setLogarithmicScale(boolean logarithmicScale) {
        isLogarithmicScale = logarithmicScale;
        repaint();
    }

    @Override
    protected void paint() {
        if (histogram.getBinCount() > 0) {
            g.setFill(Color.grayRgb(170));
            for (int i = 0; i < histogram.getBinCount(); i++) {
                drawBar(i);
            }

        }
    }

    private void selectionPaint() {
        selectionBorder();
        int index;
        int xCoordinate = applicationContext.getMouseSelection().getXCoordinate();
        int yCoordinate = applicationContext.getMouseSelection().getYCoordinate();
        int value = applicationContext.getGridData().getValue(xCoordinate, yCoordinate);
        if(value != (gridData.getMaxValue()+gridData.getMinValue())) {
            index = histogram.getBinIndex(value);
            g.setFill(Color.BLUE);
            drawBar(index);
        }
    }


    private void drawBar(int index) {
        double height = getHeight();
        double width = getWidth();
        double binWidth = width / histogram.getBinCount();

        int x0 = (int) (index * binWidth);
        int x1 = (int) ((index +1) * binWidth);
        int columnWidth = Math.max(x1-x0, 1);

        double count = histogram.getBin(index);
        double maxCount = histogram.getMaxCount();
        if (isLogarithmicScale) {
            count = Math.log10(count);
            maxCount = Math.log10(maxCount);
        }

        int columnHeight = (int) ((count / maxCount) * (height - BORDER_PERCENTAGE * height));

        g.fillRect(x0, height-columnHeight, columnWidth, columnHeight);
    }

    private void selectionBorder() {
        repaint();
        double minValue = imageModel.getMin();
        double maxValue = imageModel.getMax();
        int min = gridData.getMinValue();
        int max = gridData.getMaxValue();

        double minBorder = (minValue-min) / (max-min) * getWidth();
        drawStroke(minBorder);
        double maxBorder = (maxValue-min) / (max-min) * getWidth();
        drawStroke(maxBorder);

    }

    private void drawStroke(double border){
        g.beginPath();
        g.setStroke(Color.BLACK);
        g.setLineWidth(4);
        g.moveTo(border,0);
        g.lineTo(border,getHeight());
        g.getFont();
        g.stroke();
    }
}