package model.data;

import model.data.metadata.ImageMetadata;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.util.TreeMap;

public class ImageMetadataTest {

//    @Test
//    public void imageMetadataConstructor() {
//        Dimension resolution = new Dimension(1920, 1080);
//        int pixelCount = 2073600;
//        TreeMap<PixelColor, Integer> roughColorDistribution = new TreeMap<>();
//        TreeMap<PixelColor, Integer> exactColorDistribution = new TreeMap<>();
//        ImageMetadata imageMetadata = new ImageMetadata(resolution, pixelCount, roughColorDistribution, exactColorDistribution);
//
//        assertEquals(resolution, imageMetadata.getResolution());
//        assertEquals(pixelCount, imageMetadata.getPixelCount());
//        assertEquals(roughColorDistribution, imageMetadata.getRoughColorDistribution());
//        assertEquals(exactColorDistribution, imageMetadata.getExactColorDistribution());
//    }

//    @Test
//    public void imageMetadataBuilder_buildsWithAllFields() {
//        // expected values
//        Dimension resolution = new Dimension(1920, 1080);
//        int pixelCount = (int) (resolution.getWidth() * resolution.getHeight());
//        TreeMap<PixelColor, Integer> roughColorDistribution = new TreeMap<>();
//        TreeMap<PixelColor, Integer> exactColorDistribution = new TreeMap<>();
//
//        // create ImageMetadata using builder
//        ImageMetadata imageMetadata = new ImageMetadata.Builder()
//                .resolution(resolution)
//                .pixelCount(pixelCount)
//                .roughColorDistribution(roughColorDistribution)
//                .exactColorDistribution(exactColorDistribution)
//                .build();
//
//        assertEquals(resolution, imageMetadata.getResolution());
//        assertEquals(pixelCount, imageMetadata.getPixelCount());
//        assertEquals(roughColorDistribution, imageMetadata.getRoughColorDistribution());
//        assertEquals(exactColorDistribution, imageMetadata.getExactColorDistribution());
//    }

//    @Test
//    public void imageMetadataBuilder_buildsWithoutStatisticsFields() {
//        // expected values
//        Dimension resolution = new Dimension(1920, 1080);
//        int pixelCount = (int) (resolution.getWidth() * resolution.getHeight());
//
//        // create ImageMetadata using builder
//        ImageMetadata imageMetadata = new ImageMetadata.Builder()
//                .resolution(resolution)
//                .pixelCount(pixelCount)
//                .build();
//
//        // built object should not have these maps
//        TreeMap<PixelColor, Integer> roughColorDistribution = new TreeMap<>();
//        TreeMap<PixelColor, Integer> exactColorDistribution = new TreeMap<>();
//
//        assertEquals(resolution, imageMetadata.getResolution());
//        assertEquals(pixelCount, imageMetadata.getPixelCount());
//        assertNotEquals(roughColorDistribution, imageMetadata.getRoughColorDistribution());
//        assertNotEquals(exactColorDistribution, imageMetadata.getExactColorDistribution());
//    }

//    @Test
//    public void toString_ReturnsResolutionAndPixelCount() {
//        Dimension resolution = new Dimension(1920, 1080);
//        int pixelCount = 2073600;
//        TreeMap<PixelColor, Integer> roughColorDistribution = new TreeMap<>();
//        TreeMap<PixelColor, Integer> exactColorDistribution = new TreeMap<>();
//        ImageMetadata imageMetadata = new ImageMetadata(resolution, pixelCount, roughColorDistribution, exactColorDistribution);
//        String expected = "    ImageMetadata {\n" +
//                "       Resolution: java.awt.Dimension[width=1920,height=1080]\n" +
//                "       Pixel count: 2073600\n" +
//                "       Rough colormap size: 0\n" +
//                "       Exact colormap size: 0\n" +
//                "    }\n";
//
//        assertEquals(expected, imageMetadata.toString());
//    }
}
