package model.service;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageInspectionServiceTest {

    private ImageInspectionService imageInspectionService;
    private File testPngFile = new File("src/test/resources/bluePNG.png");
    private Dimension testPngDimension = new Dimension(100, 100);
    private int testPngPixelCount = 100 * 100;
    private File testJpegFile = new File("src/test/resources/greenJPEG.jpeg");
    private Dimension testJpegDimension = new Dimension(100, 100);
    private int testJpegPixelCount = 100 * 100;
    private File testTxtFile = new File("src/test/resources/textFile.txt");

    @Test
    void testToString_returnsPathOfHeldImage() {
        Image image = parseImageFromFile(this.testPngFile);
        this.imageInspectionService = new ImageInspectionService(image);
        assertEquals("name", this.imageInspectionService.toString());
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