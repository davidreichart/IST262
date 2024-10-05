package model.data.metadata;

import model.data.PixelColor;
import model.util.FileInspector;
import model.util.ImageInspector;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.TreeMap;

/**
 * The ImageMetadata object is a collection of data points/statistics to be associated with an image file
 * on the user's system.
 */
public class ImageMetadata extends Metadata {

    private Dimension resolution;
    private int pixelCount;
    private TreeMap<PixelColor, Integer> roughColorDistribution;
    private TreeMap<PixelColor, Integer> exactColorDistribution;

    /**
     * Constructs a new ImageMetadata object when all possible attributes are already known.
     * The ImageMetadata class is a representation of contextualizing data points for any valid image file on a user's computer.
     * The ImageMetadata.builder() method should be used in cases where some fields are unknown.
     * @param absoluteFilePath The absolute file path leading to this image on the user's system.
     * @param resolution The height x width of this image as a Dimension.
     * @param pixelCount The number of pixels in this image.
     * @param roughColorDistribution A map of pixel counts by a limited number of possible colors.
     * @param exactColorDistribution A map of pixel counts by an unlimited number of possible colors.
     */
    public ImageMetadata(String absoluteFilePath, Dimension resolution, int pixelCount,
                         TreeMap<PixelColor, Integer> roughColorDistribution,
                         TreeMap<PixelColor, Integer> exactColorDistribution) {
        super(absoluteFilePath);
        this.resolution = resolution;
        this.pixelCount = pixelCount;
        this.roughColorDistribution = roughColorDistribution;
        this.exactColorDistribution = exactColorDistribution;
    }

    /**
     * Constructor used by the ImageMetadata builder.
     * Automatically attempts to calculate relevant image metadata if not provided.
     * If the file is not an image file, all image metadata will be set to default zero/zero equivalent values.
     * @param builder The builder instance used to create a new ImageMetadata object.
     */
    private ImageMetadata(Builder builder) {
        super(builder.absoluteFilePath); // Call to parent constructor for file Metadata

        if (builder.resolution != null) {
            this.resolution = builder.resolution;
        } else {
            if (FileInspector.isImageFile(this.getFile())) {
                BufferedImage image = ImageInspector.loadImage(this.getAbsoluteFilePath());
                this.resolution = ImageInspector.getResolution(image);
            } else {
                this.resolution = new Dimension(0, 0);
            }
        }

        if (builder.pixelCount > 0) {
            this.pixelCount = builder.pixelCount;
        } else {
            if (FileInspector.isImageFile(this.getFile())) {
                BufferedImage image = ImageInspector.loadImage(this.getAbsoluteFilePath());
                this.pixelCount = ImageInspector.getPixelCount(image);
            } else {
                this.pixelCount = 0;
            }
        }

        if (builder.roughColorDistribution != null) {
            this.roughColorDistribution = builder.roughColorDistribution;
        } else {
            if (FileInspector.isImageFile(this.getFile())) {
                BufferedImage image = ImageInspector.loadImage(this.getAbsoluteFilePath());
                this.roughColorDistribution = ImageInspector.getRoughColorDistribution(image);
            } else {
                this.roughColorDistribution = new TreeMap<>();
            }
        }

        if (builder.exactColorDistribution != null) {
            this.exactColorDistribution = builder.exactColorDistribution;
        } else {
            if (FileInspector.isImageFile(this.getFile())) {
                BufferedImage image = ImageInspector.loadImage(this.getAbsoluteFilePath());
                this.exactColorDistribution = ImageInspector.getExactColorDistribution(image);
            } else {
                this.exactColorDistribution = new TreeMap<>();
            }
        }
    }

    /**
     * Initializes the build process for creating a new ImageMetadata object.
     * Allows for the creation of ImageMetadata objects using order agnostic method-chaining.
     * There are zero required variables. Default null values are inserted in place of missing data.
     * @return An instance of the ImageMetadata builder.
     */
    public static Builder builder(String absoluteFilePath) {
        return new Builder(absoluteFilePath);
    }

    /**
     * Builder class for constructing an ImageMetadata object.
     * Allows for the creation of ImageMetadata objects using order agnostic method-chaining.
     * There are zero required variables. Default null values are inserted in place of missing data.
     */
    public static class Builder {
        private String absoluteFilePath;
        private Dimension resolution;
        private int pixelCount;
        private TreeMap<PixelColor, Integer> roughColorDistribution;
        private TreeMap<PixelColor, Integer> exactColorDistribution;

        /**
         * Constructor used by the ImageMetadata builder.
         * An absolute file path is required to build an ImageMetadata object's underlying Metadata.
         * @param absoluteFilePath The absolute file path leading to this image on the user's system.
         */
        private Builder(String absoluteFilePath) {
            this.absoluteFilePath = absoluteFilePath;
        }

        /**
         * Sets the height x width of this image as a Dimension.
         * @param resolution A dimension object holding this image's size.
         * @return An instance of the ImageMetadata builder.
         */
        public Builder resolution(Dimension resolution) {
            this.resolution = resolution;
            return this;
        }

        /**
         * Sets the number of pixels that make up this image. (Height * Width)
         * @param pixelCount The number of pixels in this image.
         * @return An instance of the ImageMetadata builder.
         */
        public Builder pixelCount(int pixelCount) {
            this.pixelCount = pixelCount;
            return this;
        }

        /**
         * Sets a map composed of three possible Color keys (red, green, blue) and Integer
         * values representing the frequency with which that color found in the image.
         * For example, A pixel is deemed "red" if its red RGB value is higher than its green/blue values.
         * @param roughColorDistribution A map of pixel counts by a limited number of possible colors.
         * @return An instance of the ImageMetadata builder.
         */
        public Builder roughColorDistribution(TreeMap<PixelColor, Integer> roughColorDistribution) {
            this.roughColorDistribution = roughColorDistribution;
            return this;
        }

        /**
         * Sets a map composed Color keys paired with Integer values representing the frequency
         * of occurrence for the corresponding Color.
         * There are no predefined color keys. For example, if an image has 10,000 different colors, this map would
         * have 10,000 individual color keys.
         * @param exactColorDistribution A map of pixel counts by an unlimited number of possible colors.
         * @return An instance of the ImageMetadata builder.
         */
        public Builder exactColorDistribution(TreeMap<PixelColor, Integer> exactColorDistribution) {
            this.exactColorDistribution = exactColorDistribution;
            return this;
        }

        /**
         * Completes the ImageMetadata build process.
         * Any skipped attributes will be initialized as null.
         * @return An ImageMetadata object as defined by all previously specified attributes.
         */
        public ImageMetadata build() {
            return new ImageMetadata(this);
        }
    }

    /**
     * Presents all currently stored attributes that contextualize the corresponding image for this ImageMetadata object.
     * @return A string listing all attributes store on this ImageMetadata object.
     */
    @Override
    public String toString() {
        return "    ImageMetadata {\n" +
                "       Resolution: " + this.resolution.toString() + "\n" +
                "       Pixel count: " + this.pixelCount + "\n" +
                "       Rough colormap size: " + this.roughColorDistribution.size() + "\n" +
                "       Exact colormap size: " + this.exactColorDistribution.size() + "\n" +
                "    }\n";
    }

    public Dimension getResolution() {
        return resolution;
    }

    public int getPixelCount() {
        return pixelCount;
    }

    public TreeMap<PixelColor, Integer> getRoughColorDistribution() {
        return roughColorDistribution;
    }

    public TreeMap<PixelColor, Integer> getExactColorDistribution() {
        return exactColorDistribution;
    }
}
