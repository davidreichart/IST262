package model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;

public class ImageInspectionServiceTest {

    @Test
    void getFileExtensionShouldReturnPng() {
        File pngFile = new File("src/test/resources/bluePNG.png");
        ImageInspectionService inspectionService = new ImageInspectionService(pngFile);
        Assertions.assertEquals("png", inspectionService.getFileExtension());
    }

    @Test
    void getFileExtensionShouldReturnJpeg() {
        File jpegFile = new File("src/test/resources/greenJPEG.jpeg");
        ImageInspectionService inspectionService = new ImageInspectionService(jpegFile);
        Assertions.assertEquals("jpeg", inspectionService.getFileExtension());
    }

    @Test
    void isImageFileIdentifiesValidPngImage() {
        File pngFile = new File("src/test/resources/bluePNG.png");
        ImageInspectionService inspectionService = new ImageInspectionService(pngFile);
        Assertions.assertTrue(inspectionService.isImageFile(pngFile));
    }

    @Test
    void isImageFileIdentifiesValidJpegImage() {
        File jpegFile = new File("src/test/resources/greenJPEG.jpeg");
        ImageInspectionService inspectionService = new ImageInspectionService(jpegFile);
        Assertions.assertTrue(inspectionService.isImageFile(jpegFile));
    }

    @Test
    void isImageFileIdentifiesInvalidImageFile() {
        File txtFile = new File("src/test/resources/textFile.txt");
        ImageInspectionService inspectionService = new ImageInspectionService(txtFile);
        Assertions.assertFalse(inspectionService.isImageFile(txtFile));
    }

    @Test
    void parseImageFromFileCorrectlyReturnsBufferedImageFromImageFile() {
        File pngFile = new File("src/test/resources/bluePNG.png");
        ImageInspectionService inspectionService = new ImageInspectionService(pngFile);
        Assertions.assertTrue(inspectionService.parseImageFromFile() instanceof BufferedImage);
    }

    @Test
    void getResolutionReturnsCorrectPngResolution() {
        File pngFile = new File("src/test/resources/bluePNG.png");
        ImageInspectionService inspectionService = new ImageInspectionService(pngFile);
        int[] correctResolution = new int[2];
        correctResolution[0] = 100; // height
        correctResolution[1] = 100; // width
        int[] foundResolution = inspectionService.getResolution();
        Assertions.assertArrayEquals(correctResolution, foundResolution);
    }
}
