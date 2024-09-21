package model.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.TreeMap;

/**
 * The ImageInspector class provides utility methods for extracting data about image files.
 * Primarily, this class is used to build a metadata profile for valid image files.
 * This class expects to receive BufferedImage objects.
 */
public final class ImageInspector {

    private ImageInspector() {}

    /**
     * Reads a given image pixel-by-pixel to count the frequency of individual colors occurring in that image.
     * The provided map may have thousands of keys since this method does not have any limits on the number of
     * colors logged.
     * @param image The image to create a color map from.
     * @return A Map of Color keys with Integer values representing the frequency of each color.
     */
    public static TreeMap<Color, Integer> getExactColorDistribution(BufferedImage image) {
        TreeMap<Color, Integer> colorDistributionMap = new TreeMap<>();

        // iterating through every pixel in the image
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color currentPixelColor = new Color(image.getRGB(x, y));
                if (!colorDistributionMap.containsKey(currentPixelColor)) {
                    colorDistributionMap.put(currentPixelColor, 1);
                } else {
                    colorDistributionMap.merge(currentPixelColor, 1, Integer::sum);
                }
            }
        }
        return colorDistributionMap;
    }

    /**
     * Reads a given image pixel-by-pixel to count the frequency of a limited set of colors in that image.
     * The possible colors to be logged include: red, green or blue.
     * A pixel is assigned based upon which color value it is closest to.
     * For example, a pixel with RGB values of [255,100,20] would be assigned as "red" and increment that key's value.
     * @param image The image to create a limited color map from.
     * @return A Map of specific Color keys with Integer values representing the frequency of each color.
     */
    public static TreeMap<Color, Integer> getRoughColorDistribution(BufferedImage image) {
        TreeMap<Color, Integer> colorDistributionMap = new TreeMap<>();
        colorDistributionMap.put(Color.RED, 0);
        colorDistributionMap.put(Color.GREEN, 0);
        colorDistributionMap.put(Color.BLUE, 0);

        // iterating through every pixel in the image
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color currentPixelColor = new Color(image.getRGB(x, y));
                int redValue = currentPixelColor.getRed();
                int blueValue = currentPixelColor.getBlue();
                int greenValue = currentPixelColor.getGreen();
                if (redValue > blueValue && redValue > greenValue) {
                    // red is dominant
                    colorDistributionMap.merge(Color.RED, 1, Integer::sum);
                } else if (blueValue > greenValue) {
                    // blue is dominant
                    colorDistributionMap.merge(Color.BLUE, 1, Integer::sum);
                } else {
                    // green is dominant
                    colorDistributionMap.merge(Color.GREEN, 1, Integer::sum);
                }
            }
        }
        return colorDistributionMap;
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
}
