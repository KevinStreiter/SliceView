package ch.fhnw.ima.sliceview.logic.impl;

import ch.fhnw.ima.sliceview.app.ApplicationContext;
import ch.fhnw.ima.sliceview.logic.HistogramViewController;
import ch.fhnw.ima.sliceview.logic.MouseSelection;
import ch.fhnw.ima.sliceview.present.histo.HistogramView;

public class SimpleHistogramViewController implements HistogramViewController {

    private HistogramView histogramView;
    private MouseSelection mouseSelection;

    public SimpleHistogramViewController(ApplicationContext applicationContext, HistogramView histogramView) {
        this.mouseSelection = applicationContext.getMouseSelection();
        this.histogramView = histogramView;
        getRange();

    }

    private void getRange(){

        histogramView.setOnMousePressed((event) -> {









        });
    }

}
