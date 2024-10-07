package model.data.filetypes;

import model.data.PixelColor;
import model.util.ImageInspector;

import java.awt.*;
import java.awt.image.*;
import java.util.TreeMap;

/**
 * The ImageFile class is a representation of an image file on a user's system.
 * A METADATA record contains file statistics and information.
 * An IMAGE_METADATA record contains image-specific metadata.
 */
public class ImageFile extends SystemFile {
    /**
     * The ImageMetadata record contains image-specific metadata.
     * @param width The width of the image in pixels.
     * @param height The height of the image in pixels.
     * @param roughColorHistogram A TreeMap containing the rough color distribution of the image.
     * @param preciseColorHistogram A TreeMap containing the precise color distribution of the image.
     */
    public record ImageMetadata(int width, int height, TreeMap<PixelColor, Integer> roughColorHistogram, TreeMap<PixelColor, Integer> preciseColorHistogram) {
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
        this.IMAGE_METADATA = new ImageMetadata(resolution.width, resolution.height, ImageInspector.getRoughColorDistribution(image), ImageInspector.getExactColorDistribution(image));
    }

    /**
     * Returns the image metadata record associated with this ImageFile.
     * The image metadata record's attributes can be accessed using the dot operator on the returned record.
     * @return The image metadata record associated with this ImageFile.
     */
    public ImageMetadata IMAGE_METADATA() {
        return this.IMAGE_METADATA;
    }
}
