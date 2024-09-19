package model.data;

import model.OldFileMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class OldFileMetadataTest {

    private OldFileMetadata testOldFileMetadata;
    private Dimension testResolution = new Dimension(300, 500);
    private int testPixelCount = testResolution.height * testResolution.width;

    @BeforeEach
    void init() {
        this.testOldFileMetadata = new OldFileMetadata(testResolution, testPixelCount);
    }

    @Test
    void testToString() {
        String expected = "METADATA: \n" +
                "    Resolution: 500 x 300\n" +
                "    Pixel count: " + 500 * 300 + "\n";
        assertEquals(expected, this.testOldFileMetadata.toString());
    }

    @Test
    void getResolution() {
        assertEquals(this.testResolution, testOldFileMetadata.getResolution());
    }

    @Test
    void setResolution() {
        Dimension differentDimension = new Dimension(600, 200);
        this.testOldFileMetadata.setResolution(differentDimension);
        assertNotEquals(this.testResolution, testOldFileMetadata.getResolution());
    }

    @Test
    void getPixelCount() {
        assertEquals(this.testPixelCount, testOldFileMetadata.getPixelCount());
    }

    @Test
    void setPixelCount() {
        int differentPixelCount = 20;
        this.testOldFileMetadata.setPixelCount(differentPixelCount);
        assertNotEquals(this.testPixelCount, this.testOldFileMetadata.getPixelCount());
    }
}