package model.data.filetypes;

import model.util.ImageInspector;

import java.awt.*;
import java.awt.image.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.TreeMap;

/**
 * The ImageFile class is a representation of an image file on a user's system.
 * A METADATA record contains file statistics and information.
 * An IMAGE_METADATA record contains image-specific metadata.
 */
public class ImageFile extends SystemFile implements FileSystemResource {

    /**
     * The ImageMetadata record contains image-specific metadata.
     * @param width The width of the image in pixels.
     * @param height The height of the image in pixels.
     * @param colorHistogram A 3D array representing the color histogram of the image.
     */
    public record ImageMetadata(int width, int height, int[][][] colorHistogram) {
        public int pixelCount() {
            return width * height;
        }
    }

    private final ImageMetadata IMAGE_METADATA;

    /**
     * Constructs a new ImageFile object.
     * The ImageFile class is a representation of an image file on a user's system.
     * @param absoluteFilePath The absolute file path leading to this file on the user's system.
     */
    public ImageFile(String absoluteFilePath) {
        super(absoluteFilePath);

        BufferedImage image = ImageInspector.loadImage(absoluteFilePath);
        Dimension resolution = ImageInspector.getResolution(image);
        this.IMAGE_METADATA = new ImageMetadata(resolution.width, resolution.height, ImageInspector.generateColorHistogram(image, 8));
    }

    /**
     * Returns the image metadata record associated with this ImageFile.
     * The image metadata record's attributes can be accessed using the dot operator on the returned record.
     * @return The image metadata record associated with this ImageFile.
     */
    public ImageMetadata IMAGE_METADATA() {
        return this.IMAGE_METADATA;
    }

    /**
     * Provides a string with the absolute path of the file system resource.
     *
     * @return The absolute path of the file system resource.
     */
    @Override
    public String getAbsolutePath() {
        return this.METADATA().absoluteFilePath();
    }

    /**
     * Identifies if this file system resource is a directory.
     *
     * @return True if the file system resource is a directory, false otherwise.
     */
    @Override
    public boolean isDirectory() {
        return false;
    }

    /**
     * Identifies if this file system resource is a system file.
     *
     * @return True if the file system resource is a system file, false otherwise.
     */
    @Override
    public boolean isSystemFile() {
        return true;
    }
}
