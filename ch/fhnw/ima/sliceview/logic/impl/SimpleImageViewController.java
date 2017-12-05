package ch.fhnw.ima.sliceview.logic.impl;
import ch.fhnw.ima.sliceview.app.ApplicationContext;
import ch.fhnw.ima.sliceview.logic.ImageViewController;
import ch.fhnw.ima.sliceview.logic.MouseSelection;
import ch.fhnw.ima.sliceview.present.image.ImageView;

public class SimpleImageViewController implements ImageViewController {

    private ImageView imageView;
    private MouseSelection mouseSelection;

    public SimpleImageViewController(ApplicationContext applicationContext, ImageView imageView) {
        this.mouseSelection = applicationContext.getMouseSelection();
        this.imageView = imageView;

        getAllCoordinates();
    }

    public void setXCoordinate(int xCoordinate) {
        mouseSelection.setXCoordinate(xCoordinate);
    }

    public void setYCoordinate(int yCoordinate) {
        mouseSelection.setYCoordinate(yCoordinate);
    }

    private void getAllCoordinates(){

        imageView.setOnMouseMoved((event) -> {

            setXCoordinate(imageView.getDataX((int) event.getX()));
            setYCoordinate(imageView.getDataY((int) event.getY()));
        });

        imageView.setOnMouseExited((event) -> {

            setXCoordinate(-1);
            setYCoordinate(-1);
        });
    }
}
