package model.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;

public class FileTagTest {

    @Test
    public void fileTagConstructor() {
        FileTag fileTag = new FileTag("Test", Color.GRAY);

        assertEquals("Test", fileTag.getName());
        assertEquals(Color.GRAY, fileTag.getColor());
    }

    @Test
    public void fileTagSetters() {
        FileTag fileTag = new FileTag("Test", Color.GRAY);
        fileTag.setName("Test2");
        fileTag.setColor(Color.BLACK);

        assertEquals("Test2", fileTag.getName());
        assertEquals(Color.BLACK, fileTag.getColor());
    }

    @Test
    public void fileTagGetters() {
        FileTag fileTag = new FileTag("Test", Color.GRAY);

        assertEquals("Test", fileTag.getName());
        assertEquals(Color.GRAY, fileTag.getColor());
    }

    @Test
    public void toStringReturnsNameAndColor() {
        FileTag fileTag = new FileTag("Test", Color.GRAY);
        String expected = "FILE TAG: \n" +
                "    Tag name: Test\n" +
                "    Color: java.awt.Color[r=128,g=128,b=128]";

        assertEquals(expected, fileTag.toString());
    }

    @Test
    public void compareToCorrectlyReturnsComparisonValues() {
        FileTag fileTag = new FileTag("Test", Color.GRAY);
        FileTag fileTag2 = new FileTag("Test2", Color.GRAY);
        FileTag fileTag3 = new FileTag("Test", Color.GRAY);

        // fileTag's name comes before fileTag2's name, so a positive number should be returned
        assertTrue(fileTag.compareTo(fileTag2) < 0);
        // fileTag's name is the same as fileTag3's name, so 0 should be returned
        assertTrue(fileTag.compareTo(fileTag3) == 0);
        // fileTag2's name comes after fileTag's name, so a negative number should be returned
        assertTrue(fileTag2.compareTo(fileTag) > 0);
    }


    @Test
    public void equalsIdentifiesEqualAndUnequalFileTags() {
        FileTag fileTag = new FileTag("Test", Color.GRAY);
        FileTag fileTag2 = new FileTag("Test", Color.GRAY);
        FileTag fileTag3 = new FileTag("Test2", Color.GRAY);
        FileTag fileTag4 = new FileTag("Test", Color.BLACK);

        assertTrue(fileTag.equals(fileTag2));
        assertFalse(fileTag.equals(fileTag3));
        assertFalse(fileTag.equals(fileTag4));
    }
}
