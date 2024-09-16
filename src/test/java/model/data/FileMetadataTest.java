package model.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FileMetadataTest {

    private FileMetadata testFileMetadata;
    private Dimension testResolution = new Dimension(300, 500);
    private int testPixelCount = testResolution.height * testResolution.width;

    @BeforeEach
    void init() {
        this.testFileMetadata = new FileMetadata(testResolution, testPixelCount);
    }

    @Test
    void testToString() {
        String expected = "METADATA: \n" +
                "    Resolution: 500 x 300\n" +
                "    Pixel count: " + 500 * 300 + "\n";
        assertEquals(expected, this.testFileMetadata.toString());
    }

    @Test
    void getResolution() {
        assertEquals(this.testResolution, testFileMetadata.getResolution());
    }

    @Test
    void setResolution() {
        Dimension differentDimension = new Dimension(600, 200);
        this.testFileMetadata.setResolution(differentDimension);
        assertNotEquals(this.testResolution, testFileMetadata.getResolution());
    }

    @Test
    void getPixelCount() {
        assertEquals(this.testPixelCount, testFileMetadata.getPixelCount());
    }

    @Test
    void setPixelCount() {
        int differentPixelCount = 20;
        this.testFileMetadata.setPixelCount(differentPixelCount);
        assertNotEquals(this.testPixelCount, this.testFileMetadata.getPixelCount());
    }
}