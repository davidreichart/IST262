package util;

import model.util.FileInspector;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileInspectorTest {

    // Arrange
    File pngFile = new File("src/test/resources/bluePNG.png");
    File jpegFile = new File("src/test/resources/greenJPEG.jpeg");
    File txtFile = new File("src/test/resources/textFile.txt");
    File invalid = new File("src/test/resources/invalidFile");

    @Test
    public void isImageFile_validImageFile_true() {
        // Act & Assert
        assertTrue(FileInspector.isImageFile(pngFile));
        assertTrue(FileInspector.isImageFile(jpegFile));
    }

    @Test
    public void isImageFile_invalidImageFile_false() {
        // Act & Assert
        assertFalse(FileInspector.isImageFile(txtFile));
        assertFalse(FileInspector.isImageFile(invalid));
    }

    @Test
    public void getFileContentType_returnsAccurateContentType() {
        // Act
        String pngContentType = FileInspector.getFileContentType(pngFile);
        String jpegContentType = FileInspector.getFileContentType(jpegFile);
        String txtContentType = FileInspector.getFileContentType(txtFile);

        // Assert
        assertEquals("image/png", pngContentType);
        assertEquals("image/jpeg", jpegContentType);
        assertEquals("text/plain", txtContentType);
    }

    @Test
    public void getFileContentType_invalidFile_returnsEmptyString() {
        // Act
        String invalidContentType = FileInspector.getFileContentType(invalid);

        // Assert
        assertTrue(invalidContentType.isEmpty());
    }

    @Test
    public void getFileSizeInBytes_returnsAccurateSize() {
        // Act
        long pngSize = FileInspector.getFileSizeInBytes(pngFile);
        long jpegSize = FileInspector.getFileSizeInBytes(jpegFile);
        long txtSize = FileInspector.getFileSizeInBytes(txtFile);

        // Assert
        // sizes reference Windows file properties
        assertEquals(693, pngSize);
        assertEquals(7113, jpegSize);
        assertEquals(44, txtSize);
    }

    @Test
    public void getFileSizeInBytes_invalidFile_returnsNegativeOne() {
        // Act
        long invalidSize = FileInspector.getFileSizeInBytes(invalid);

        // Assert
        assertEquals(-1, invalidSize);
    }

    @Test
    public void getFileExtension_returnsExtension() {
        // Act
        String pngExtension = FileInspector.getFileExtension(pngFile);
        String jpegExtension = FileInspector.getFileExtension(jpegFile);
        String txtExtension = FileInspector.getFileExtension(txtFile);

        // Assert
        assertEquals("png", pngExtension);
        assertEquals("jpeg", jpegExtension);
        assertEquals("txt", txtExtension);
    }

    @Test
    public void getFileExtension_invalidFile_returnsEmptyString() {
        // Act
        String invalidExtension = FileInspector.getFileExtension(invalid);

        // Assert
        assertTrue(invalidExtension.isEmpty());
    }
}
