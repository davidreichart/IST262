package model.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.util.TreeMap;

public class ImageMetadataTest {

    @Test
    public void imageMetadataConstructor() {
        Dimension resolution = new Dimension(1920, 1080);
        int pixelCount = 2073600;
        TreeMap<Color, Integer> roughColorDistribution = new TreeMap<>();
        TreeMap<Color, Integer> exactColorDistribution = new TreeMap<>();
        ImageMetadata imageMetadata = new ImageMetadata(resolution, pixelCount, roughColorDistribution, exactColorDistribution);

        assertEquals(resolution, imageMetadata.getResolution());
        assertEquals(pixelCount, imageMetadata.getPixelCount());
        assertEquals(roughColorDistribution, imageMetadata.getRoughColorDistribution());
        assertEquals(exactColorDistribution, imageMetadata.getExactColorDistribution());
    }

    @Test
    public void imageMetadataBuilder() {
        // expected values
        Dimension resolution = new Dimension(1920, 1080);
        int pixelCount = (int) (resolution.getWidth() * resolution.getHeight());
        TreeMap<Color, Integer> roughColorDistribution = new TreeMap<>();
        TreeMap<Color, Integer> exactColorDistribution = new TreeMap<>();

        // create ImageMetadata using builder
        ImageMetadata imageMetadata = new ImageMetadata.Builder()
                .resolution(resolution)
                .pixelCount(pixelCount)
                .roughColorDistribution(roughColorDistribution)
                .exactColorDistribution(exactColorDistribution)
                .build();

        assertEquals(resolution, imageMetadata.getResolution());
        assertEquals(pixelCount, imageMetadata.getPixelCount());
        assertEquals(roughColorDistribution, imageMetadata.getRoughColorDistribution());
        assertEquals(exactColorDistribution, imageMetadata.getExactColorDistribution());
    }

    @Test
    public void toStringReturnsResolutionAndPixelCount() {
        Dimension resolution = new Dimension(1920, 1080);
        int pixelCount = 2073600;
        TreeMap<Color, Integer> roughColorDistribution = new TreeMap<>();
        TreeMap<Color, Integer> exactColorDistribution = new TreeMap<>();
        ImageMetadata imageMetadata = new ImageMetadata(resolution, pixelCount, roughColorDistribution, exactColorDistribution);
        String expected = "    ImageMetadata {\n" +
                "       Resolution: java.awt.Dimension[width=1920,height=1080]\n" +
                "       Pixel count: 2073600\n" +
                "       Rough colormap size: 0\n" +
                "       Exact colormap size: 0\n" +
                "    }\n";

        assertEquals(expected, imageMetadata.toString());
    }
}
