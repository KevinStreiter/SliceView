package ch.fhnw.ima.sliceview.logic.impl;
import ch.fhnw.ima.sliceview.app.ApplicationContext;
import ch.fhnw.ima.sliceview.logic.GridData;
import ch.fhnw.ima.sliceview.logic.ImageViewController;
import ch.fhnw.ima.sliceview.logic.MouseSelection;
import ch.fhnw.ima.sliceview.present.image.ImageView;

public class SimpleImageViewController implements ImageViewController {

    private ImageView imageView;
    private MouseSelection mouseSelection;
    private GridData gridData;


    public SimpleImageViewController(ApplicationContext applicationContext, ImageView imageView) {
        this.mouseSelection = applicationContext.getMouseSelection();
        this.gridData = applicationContext.getGridData();
        this.imageView = imageView;
        getAllCoordinates();
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

    public void setXCoordinate(int xCoordinate) {
        System.out.println("x "+xCoordinate);
        mouseSelection.setXCoordinate(xCoordinate);
    }

    public void setYCoordinate(int yCoordinate) {
        System.out.println("y "+yCoordinate);
        mouseSelection.setYCoordinate(yCoordinate);
    }
}
