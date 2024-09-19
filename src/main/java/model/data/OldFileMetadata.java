package model.data;

import java.awt.*;

/**
 * The FileMetadata class is to be associated with an ImageFile object.
 * It is a data storage class that contains contextual information about an ImageFile generated by the program.
 * The primary data contents of this object concern statistical information/properties (i. e. dimension, pixel count)
 * not provided by existing java objects.
 */
public class OldFileMetadata {

    private Dimension resolution;
    private int pixelCount;

    /**
     * Constructs a new FileMetadata object when all possible data points held by this object are already known.
     * If data is missing, the empty constructor should be used and updated using this class's set methods.
     * @param resolution The Dimension resolution of this image file.
     * @param pixelCount The total number of pixels that make up this image.
     */
    public OldFileMetadata(Dimension resolution, int pixelCount) {
        this.resolution = resolution;
        this.pixelCount = pixelCount;
    }

    /**
     * Returns a string detailing all possible metadata points held by this instance.
     * Any unknown values will be printed with zero/null placeholder values.
     * @return a string detailing all possible metadata points held by this instance.
     */
    @Override
    public String toString() {
        return "METADATA: \n" +
                "    Resolution: " + (int) this.resolution.getHeight() + " x " + (int) this.resolution.getWidth() + "\n" +
                "    Pixel count: " + this.pixelCount + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(OldFileMetadata.class)) {
            return false;
        }
        return this.resolution.equals(((OldFileMetadata) obj).getResolution());
    }

    /**
     * Returns the stored resolution of the image (height x width) as a Dimension object.
     * @return The Dimension representation of the image file's resolution.
     */
    public Dimension getResolution() {
        return resolution;
    }

    /**
     * Sets/replaces the Dimension object representing the resolution (height x width) of the image.
     * @param resolution The Dimension representation of the image file's resolution to store.
     */
    public void setResolution(Dimension resolution) {
        this.resolution = resolution;
    }

    /**
     * Returns the total number of pixels that make up the image as an int.
     * @return The total number of pixels in this image.
     */
    public int getPixelCount() {
        return pixelCount;
    }

    /**
     * Sets/replaces the int representing the number of pixels that make up the image.
     * @param pixelCount The total number of pixels making up the image to store.
     */
    public void setPixelCount(int pixelCount) {
        this.pixelCount = pixelCount;
    }
}
