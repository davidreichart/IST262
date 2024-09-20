package model.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.TreeMap;

public final class ImageInspector {

    private ImageInspector() {}

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

    public static Dimension getResolution(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();
        return new Dimension(width, height);
    }

    public static int getPixelCount(BufferedImage image) {
        return image.getWidth() * image.getHeight();
    }
}
