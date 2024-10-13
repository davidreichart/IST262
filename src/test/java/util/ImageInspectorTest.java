package util;

import model.util.ImageInspector;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageInspectorTest {

    // Arrange
    File pngFile = new File("src/test/resources/bluePNG.png");
    File jpegFile = new File("src/test/resources/greenJPEG.jpeg");
    File txtFile = new File("src/test/resources/textFile.txt");
    File invalid = new File("src/test/resources/invalidFile");

    @Test
    public void generateColorHistogram_generatesHistogramForPNG() {
        // Act
        BufferedImage pngImage = ImageInspector.loadImage(pngFile.getAbsolutePath());
        int[][][] histogram = ImageInspector.generateColorHistogram(pngImage, 8);

        // Assert
        // we expect a 32x32x32 histogram because we are using a bin size of 8
        assertEquals(32, histogram.length);
        assertEquals(32, histogram[0].length);
        assertEquals(32, histogram[0][0].length);
    }

    @Test
    public void generateColorHistogram_generatesHistogramForJPEG() {
        // Act
        BufferedImage jpegImage = ImageInspector.loadImage(jpegFile.getAbsolutePath());
        int[][][] histogram = ImageInspector.generateColorHistogram(jpegImage, 8);

        // Assert
        // we expect a 32x32x32 histogram because we are using a bin size of 8
        assertEquals(32, histogram.length);
        assertEquals(32, histogram[0].length);
        assertEquals(32, histogram[0][0].length);
    }

    @Test
    public void getResolution_returnsResolutionForPNG() {
        // Act
        BufferedImage pngImage = ImageInspector.loadImage(pngFile.getAbsolutePath());
        int width = pngImage.getWidth();
        int height = pngImage.getHeight();

        // Assert
        assertEquals(100, width);
        assertEquals(100, height);
    }

    @Test
    public void getResolution_returnsResolutionForJPEG() {
        // Act
        BufferedImage jpegImage = ImageInspector.loadImage(jpegFile.getAbsolutePath());
        int width = jpegImage.getWidth();
        int height = jpegImage.getHeight();

        // Assert
        assertEquals(100, width);
        assertEquals(100, height);
    }

    @Test
    public void getPixelCount_returnsAccuratePixelCount() {
        // Act
        BufferedImage pngImage = ImageInspector.loadImage(pngFile.getAbsolutePath());
        BufferedImage jpegImage = ImageInspector.loadImage(jpegFile.getAbsolutePath());
        int pngPixelCount = ImageInspector.getPixelCount(pngImage);
        int jpegPixelCount = ImageInspector.getPixelCount(jpegImage);

        // Assert
        assertEquals(10000, pngPixelCount);
        assertEquals(10000, jpegPixelCount);
    }

    @Test
    public void loadImage_returnsBufferedImageForPNG() {
        // Act
        BufferedImage pngImage = ImageInspector.loadImage(pngFile.getAbsolutePath());

        // Assert
        assertEquals(100, pngImage.getWidth());
        assertEquals(100, pngImage.getHeight());
    }

    @Test
    public void loadImage_returnsBufferedImageForJPEG() {
        // Act
        BufferedImage jpegImage = ImageInspector.loadImage(jpegFile.getAbsolutePath());

        // Assert
        assertEquals(100, jpegImage.getWidth());
        assertEquals(100, jpegImage.getHeight());
    }

    @Test
    public void loadImage_throwsExceptionForInvalidFile() {
        // Act & Assert
        try {
            ImageInspector.loadImage(txtFile.getAbsolutePath());
        } catch (IllegalArgumentException e) {
            assertEquals("The provided file is not a valid image file.", e.getMessage());
        }
    }
}
