package ch.fhnw.ima.sliceview.logic;

import ch.fhnw.ima.sliceview.present.image.ImageView;

public interface ImageViewController {

    ImageView getImageView();

    void getAllCoordinates();

    void setXCoordinate(int xCoordinate);

    void setYCoordinate(int yCoordinate);

}
