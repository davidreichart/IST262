package model.util;

import model.data.PixelColor;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.TreeMap;

public class ImageInspectorTest {

    @Test
    public void getExactColorDistribution_ReturnsCorrectMapGivenPNG() {
        BufferedImage pngImage = null;
        try {
            pngImage = ImageIO.read(new File("src/test/resources/bluePNG.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        TreeMap<PixelColor, Integer> methodReturnPNG = ImageInspector.getExactColorDistribution(pngImage);
        PixelColor expectedPNGPixelColor = new PixelColor(new Color(0, 0, 255));

        // png images should be returning very accurate colors since they are lossless
        assertEquals(1, methodReturnPNG.size());
        assertEquals(expectedPNGPixelColor.getColor(), methodReturnPNG.firstKey().getColor());
    }

    @Test
    public void getExactColorDistribution_ReturnsInaccurateColorMapGivenJPEG() {
        //todo: in an ideal world we can get this test to fail but this may be impossible given the compression inherent to JPEGs
        BufferedImage jpegImage = null;
        try {
            jpegImage = ImageIO.read(new File("src/test/resources/greenJPEG.jpeg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        TreeMap<PixelColor, Integer> methodReturnJPEG = ImageInspector.getExactColorDistribution(jpegImage);
        PixelColor perfectColor = new PixelColor(new Color(0, 255, 0));

        // jpeg images should be returning inaccurate colors since they are lossy,
        // but there should still be consistency in the colors
        assertEquals(1, methodReturnJPEG.size());
        assertNotEquals(perfectColor.getColor(), methodReturnJPEG.firstKey().getColor());
    }

    @Test
    public void getRoughColorDistribution_ReturnsCorrectMapGivenPNG() {
        BufferedImage pngImage = null;
        try {
            pngImage = ImageIO.read(new File("src/test/resources/bluePNG.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        TreeMap<PixelColor, Integer> methodReturnPNG = ImageInspector.getRoughColorDistribution(pngImage);
        PixelColor expectedPNGPixelColor = new PixelColor(Color.BLUE);

        // png images should be returning very accurate colors since they are lossless
        assertEquals(3, methodReturnPNG.size());

        // rough color distribution inserts possible color values
        assertEquals(0, methodReturnPNG.get(new PixelColor(Color.RED)));
        assertEquals(0, methodReturnPNG.get(new PixelColor(Color.GREEN)));
        assertEquals(10000, methodReturnPNG.get(new PixelColor(Color.BLUE)));
    }

    @Test
    public void getRoughColorDistribution_ReturnsInaccurateColorMapGivenJPEG() {
        BufferedImage jpegImage = null;
        try {
            jpegImage = ImageIO.read(new File("src/test/resources/greenJPEG.jpeg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        TreeMap<PixelColor, Integer> methodReturnJPEG = ImageInspector.getRoughColorDistribution(jpegImage);
        PixelColor perfectColor = new PixelColor(Color.GREEN);

        // jpeg images should be returning inaccurate colors since they are lossy,
        // but there should still be consistency in the colors
        assertEquals(3, methodReturnJPEG.size());
        assertNotEquals(perfectColor.getColor(), methodReturnJPEG.firstKey().getColor());

        // rough color distribution inserts possible color values
        assertEquals(0, methodReturnJPEG.get(new PixelColor(Color.RED)));
        assertEquals(10000, methodReturnJPEG.get(new PixelColor(Color.GREEN)));
        assertEquals(0, methodReturnJPEG.get(new PixelColor(Color.BLUE)));
    }

    @Test
    public void getResolution_ReturnsCorrectResolution() {
        BufferedImage pngImage = null;
        try {
            pngImage = ImageIO.read(new File("src/test/resources/bluePNG.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedImage jpegImage = null;
        try {
            jpegImage = ImageIO.read(new File("src/test/resources/greenJPEG.jpeg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Dimension jpegResolution = ImageInspector.getResolution(jpegImage);
        Dimension pngResolution = ImageInspector.getResolution(pngImage);
        Dimension expected = new Dimension(100, 100);

        assertEquals(expected, pngResolution);
        assertEquals(expected, jpegResolution);
    }

    @Test
    public void getPixelCount_ReturnsCorrectPixelCount() {
        BufferedImage pngImage = null;
        try {
            pngImage = ImageIO.read(new File("src/test/resources/bluePNG.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedImage jpegImage = null;
        try {
            jpegImage = ImageIO.read(new File("src/test/resources/greenJPEG.jpeg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int jpegPixelCount = ImageInspector.getPixelCount(jpegImage);
        int pngPixelCount = ImageInspector.getPixelCount(pngImage);
        int expected = 10000;

        assertEquals(expected, jpegPixelCount);
        assertEquals(expected, pngPixelCount);
    }
}
