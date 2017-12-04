package ch.fhnw.ima.sliceview.present.image;

import ch.fhnw.ima.sliceview.app.ApplicationContext;
import ch.fhnw.ima.sliceview.logic.ImageModel;
import ch.fhnw.ima.sliceview.logic.ImageModelListener;
import ch.fhnw.ima.sliceview.logic.MouseSelectionListener;
import ch.fhnw.ima.sliceview.logic.impl.SimpleImageViewController;
import ch.fhnw.ima.sliceview.present.DrawingPane;
import javafx.scene.image.Image;

public class ImageView extends DrawingPane {
    private ApplicationContext applicationContext;
    private double imageX;
    private double imageY;
    private double imageWidth;
    private double imageHeight;



    ImageView(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;

        imageX = 0;
        imageY = 0;

        applicationContext.getImageModel().addListener(this::repaint);

        applicationContext.getMouseSelection().addListener2(new MouseSelectionListener() {

            @Override
            public void selectionChanged() {

            }

            @Override
            public void histogramChanged() {
                repaint();
                g.drawImage(applicationContext.getMouseSelection().setImage(), imageX, imageY, imageWidth, imageHeight);
            }
        });
    }

    @Override
    protected void paint() {
        Image image = applicationContext.getImageModel().getImage();
        if (image != null) {
            double aspectRatio = image.getWidth() / image.getHeight();

            imageHeight = getHeight();
            imageWidth = (int) (imageHeight * aspectRatio);
            if (imageWidth > getWidth()) {
                imageWidth = getWidth();
                imageHeight = (int) (imageWidth / aspectRatio);
            }

            imageX = (getWidth() - imageWidth) / 2;
            imageY = (getHeight() - imageHeight) / 2;

            g.drawImage(applicationContext.getImageModel().getImage(), imageX, imageY, imageWidth, imageHeight);

        }
    }

    /**
     * Converts mouse coordinates into data grid coordinates.
     * @param x the position of the mouse in the view
     * @return the corresponding position in the data grid
     */
    public int getDataX(int x) {
        if (imageWidth > 0 || x != -1) {
            double normalizedX = (double)(x - imageX) / (double)imageWidth;
            if ((normalizedX >= 0) && (normalizedX <= 1)) {
                return (int) (normalizedX * (applicationContext.getGridData().getWidth()-1));
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    /**
     * Converts mouse coordinates into data grid coordinates.
     * @param y the position of the mouse in the view
     * @return the corresponding position in the data grid
     */
    public int getDataY(int y) {
        if (imageHeight > 0 || y != -1) {
            double normalizedY = (double)(y - imageY) / (double)imageHeight;
            if ((normalizedY >= 0) && (normalizedY <= 1)) {
                return (int) (normalizedY * (applicationContext.getGridData().getHeight()-1));
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}