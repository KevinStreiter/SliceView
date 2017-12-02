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

    public void getAllCoordinates(){

        imageView.setOnMouseMoved((event) -> {

            int xCoordinate = imageView.getDataX((int)event.getX());
            setXCoordinate(xCoordinate);
            System.out.println(xCoordinate);
            int yCoordinate = imageView.getDataY((int)event.getY());
            setYCoordinate(yCoordinate);
            System.out.println(yCoordinate);

        });

       imageView.setOnMouseExited((event -> {

            setXCoordinate(0);
            setYCoordinate(0);

        }));
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void setXCoordinate(int xCoordinate) {
        mouseSelection.setXCoordinate(xCoordinate);
    }

    public void setYCoordinate(int yCoordinate) {
        mouseSelection.setYCoordinate(yCoordinate);
    }
}
