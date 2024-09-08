package OLD;

/**
 * The ImageStatistics class is intended to be paired with an {@code ImageFile} object. It is a data structure that holds
 * the properties describing a given Image. Example statistics include height, width, and pixel count.
 */
public class ImageStatistics {
    private int height;
    private int width;
    private int pixelCount;

    /**
     * Creates a new ImageStatistics object to manage data representation of an ImageFile object. <br>
     * Given no values at instantiation, all statistical data points are initialized as zero / empty values (not null).
     */
    public ImageStatistics() {
        this.height = 0;
        this.width = 0;
        this.pixelCount = 0;
    }

    /**
     * Creates a new ImageStatistics object to manage data representation of an ImageFile object. <br>
     * This construction method assumes all data points are already known at time of instantiation and must be provided.
     * Use an empty constructor if this is not the case.
     * @param height The height (vertical resolution) of the image.
     * @param width The width (horizontal resolution) of the image.
     */
    public ImageStatistics(int height, int width) {
        this.height = height;
        this.width = width;
        this.pixelCount = this.height * this.width;
    }

    /**
     * Returns a String representation of statistical data representing this image file. All data points are included,
     * regardless of their current values.
     * @return a String representation of statistical data representing this image file.
     */
    @Override
    public String toString() {
        StringBuilder object = new StringBuilder();
        object.append("Image Statistics: \n")
                // each new line should use 4 space characters to create indentation
                .append("    Resolution: ").append(this.height).append(" x ").append(this.width).append("\n")
                .append("    Pixel Count: ").append(this.pixelCount).append("\n");
        return object.toString();
    }

    /**
     * Returns the ({@code int}) height of this image. Height represents the vertical resolution of a given image.
     * @return The height of this image.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the ({@code int}) height of this image. Height represents the vertical resolution of a given image.
     * @param height The new height of this image.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns the ({@code int}) width of this image. Width represents the horizontal resolution of a given image.
     * @return The width of this image.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the ({@code int}) width of this image. Width represents the horizontal resolution of a given image.
     * @param width The new width of this image.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the ({@code int}) number of pixels that make up this image.
     * @return The number of pixels that make up this image.
     */
    public int getPixelCount() {
        return pixelCount;
    }

    /**
     * Sets the ({@code int}) number of pixels that make up this image. This value should be determined by the image's
     * height times width values.
     * @param pixelCount The number of pixels that make up this image.
     */
    public void setPixelCount(int pixelCount) {
        this.pixelCount = pixelCount;
    }
}