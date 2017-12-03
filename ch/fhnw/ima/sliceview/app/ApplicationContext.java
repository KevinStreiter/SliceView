package ch.fhnw.ima.sliceview.app;

import ch.fhnw.ima.sliceview.logic.*;
import ch.fhnw.ima.sliceview.logic.impl.*;
import ch.fhnw.ima.sliceview.present.histo.HistogramView;
import ch.fhnw.ima.sliceview.present.image.ImageView;

/**
 * This class is the central container for the main data structures and other information that is
 * used throughout the application. This way, only the application context needs to be passed
 * around the various classes instead of many individual variables.
 */
public class ApplicationContext {
    private String version;
    private String name;
    private GridData gridData;
    private ImageView imageView;
    private HistogramView histogramView;
    private ImageModel imageModel;
    private MouseSelection mouseSelection;
    private ImageViewController imageViewController;
    private HistogramViewController histogramViewController;

    public ApplicationContext(String version, String name) {
        this.version = version;
        this.name = name;
        gridData = new SimpleGridData();
        imageModel = new SimpleImageModel(gridData);
        mouseSelection = new SimpleMouseSelection(this, gridData);
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public GridData getGridData() {
        return gridData;
    }

    public ImageModel getImageModel() {
        return imageModel;
    }

    public MouseSelection getMouseSelection() {
        return mouseSelection;
    }

    public ImageViewController getImageViewController() {
        return imageViewController;
    }

    public void setCurrentImageView(ImageView imageView) {
        this.imageView = imageView;
        this.imageViewController = new SimpleImageViewController(this,imageView);
    }

    public void setCurrentHistogramView(HistogramView histogramView) {
        this.histogramView = histogramView;
        this.histogramViewController = new SimpleHistogramViewController(this, histogramView);
    }
}
