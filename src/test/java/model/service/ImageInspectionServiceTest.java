package model.service;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ImageInspectionServiceTest {

    private ImageInspectionService imageInspectionService;
    private final File testPngFile = new File("src/test/resources/bluePNG.png");
    private final Dimension testPngDimension = new Dimension(100, 100);
    private final int testPngPixelCount = 100 * 100;
    private final File testJpegFile = new File("src/test/resources/greenJPEG.jpeg");
    private final Dimension testJpegDimension = new Dimension(100, 100);
    private final int testJpegPixelCount = 100 * 100;
    private final File testTxtFile = new File("src/test/resources/textFile.txt");

    @Test
    void testToString_returnsPathOfHeldImage() {
        Image image = parseImageFromFile(this.testPngFile);
        this.imageInspectionService = new ImageInspectionService(image);
        String expected = "Currently held image dimensions: " + image.getHeight(null) + " x " + image.getWidth(null);
        assertEquals(expected, this.imageInspectionService.toString());
    }

    @Test
    void getResolution_returnsDimensionOfHeldImage() {
        Image image = parseImageFromFile(this.testPngFile);
        this.imageInspectionService = new ImageInspectionService(image);
        assertEquals(this.testPngDimension, this.imageInspectionService.getResolution());
    }

    @Test
    void getImageToInspect_returnsNameOfHeldImage() {
        Image image = parseImageFromFile(this.testPngFile);
        this.imageInspectionService = new ImageInspectionService(image);
        assertEquals(image, this.imageInspectionService.getImageToInspect());
    }

    @Test
    void setImageToInspect_replacesHeldImage() {
        Image image = parseImageFromFile(this.testPngFile);
        this.imageInspectionService = new ImageInspectionService(image);
        Image differentImage = parseImageFromFile(this.testJpegFile);
        this.imageInspectionService.setImageToInspect(differentImage);
        assertNotEquals(image, this.imageInspectionService.getImageToInspect());
    }

    @Test
    void getExactColorDistribution_returnsAccurateHashMap() {
        Image image = parseImageFromFile(this.testPngFile);
        this.imageInspectionService = new ImageInspectionService(image);
        HashMap<Color, Integer> methodReturnValue = imageInspectionService.getExactColorDistribution();
        HashMap<Color, Integer> expected = new HashMap<>();
        expected.put(new Color(0, 0, 255), 10000);
        assertEquals(expected, methodReturnValue);
    }

    @Test
    void convertImageToBufferedImage_convertsImageHeldByImageInspectionService() {
        Image image = parseImageFromFile(this.testPngFile);
        this.imageInspectionService = new ImageInspectionService(image);
        BufferedImage methodReturnValue = imageInspectionService.convertImageToBufferedImage();
        assertTrue(methodReturnValue.getClass().isAssignableFrom(BufferedImage.class));
    }

    @Test
    void convertImageToBufferedImage_convertsInputImage() {
        Image image = parseImageFromFile(this.testPngFile);
        this.imageInspectionService = new ImageInspectionService(image);
        Image testImage = parseImageFromFile(this.testJpegFile); // this must be used for this test, not the one stored in the service
        BufferedImage methodReturnValue = imageInspectionService.convertImageToBufferedImage(testImage);
        assertTrue(methodReturnValue.getClass().isAssignableFrom(BufferedImage.class));
    }

    @Test
    void getRoughColorDistribution_returnsAccurateColorMap() {
        Image image = parseImageFromFile(this.testPngFile);
        this.imageInspectionService = new ImageInspectionService(image);
        HashMap<Color, Integer> methodReturnValue = imageInspectionService.getRoughColorDistribution();
        HashMap<Color, Integer> expected = new HashMap<>();
        expected.put(Color.RED, 0);
        expected.put(Color.GREEN, 0);
        expected.put(Color.BLUE, 10000);
        assertEquals(expected, methodReturnValue);
    }

    private Image parseImageFromFile(File file) {
        Image image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return image;
    }
}