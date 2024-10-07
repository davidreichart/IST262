package model.util;

import model.data.PixelColor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.TreeMap;

/**
 * The ImageInspector class provides utility methods for extracting data about image files.
 * Primarily, this class is used to build a metadata profile for valid image files.
 * This class expects to receive BufferedImage objects.
 */
public final class ImageInspector {

    private ImageInspector() {}

    // bin size is the number of bins for each color channel
    // for example, if binSize is 8, then there will be 8 bins for red, 8 bins for blue, and 8 bins for green
    // this means that there will be 8^3 = 512 total bins
    public static int[][][] generateColorHistogram(BufferedImage image, int binSize) {
        int[][][] histogram = new int[256 / binSize][256 / binSize][256 / binSize];

        // iterating through every pixel in the image
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color currentPixelColor = new Color(image.getRGB(x, y));
                // get the red, blue, and green values of the current pixel
                int redValue = currentPixelColor.getRed();
                int blueValue = currentPixelColor.getBlue();
                int greenValue = currentPixelColor.getGreen();

                // calculate the bin that corresponds to the current pixel's color
                int redBin = redValue / histogram.length;
                int blueBin = blueValue / histogram.length;
                int greenBin = greenValue / histogram.length;

                // increment the count of the corresponding bin
                histogram[redBin][blueBin][greenBin]++;
            }
        }
        return histogram;
    }

    /**
     * Returns a dimension object holding the height and width of the provided image file.
     * @param image The image file to assess.
     * @return A dimension object holding the height and width of the provided image file.
     */
    public static Dimension getResolution(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();
        return new Dimension(width, height);
    }

    /**
     * Calculates and returns the total number of pixels in the provided image.
     * @param image The image file to assess.
     * @return the total number of pixels in the provided image.
     */
    public static int getPixelCount(BufferedImage image) {
        return image.getWidth() * image.getHeight();
    }

    /**
     * Loads an image from the provided file path.
     * If the file is not a valid image file, an IllegalArgumentException is thrown.
     * @param path The path to the image file.
     * @return A BufferedImage object representing the image file.
     * @throws IllegalArgumentException If the provided file is not a valid image file.
     */
    public static BufferedImage loadImage(String path) throws IllegalArgumentException {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (image == null) {
            throw new IllegalArgumentException("The provided file is not a valid image file.");
        } else {
            return image;
        }
    }
}
