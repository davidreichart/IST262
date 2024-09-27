package model.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;

import java.io.File;

public class FileInspectorTest {

    @Test
    public void isImageFile_identifiesImageFiles() {
        File pngImage = new File("src/test/resources/bluePNG.png");
        File jpegImage = new File("src/test/resources/greenJPEG.jpeg");
        File txtFile = new File("src/test/resources/textFile.txt");
        assertTrue(FileInspector.isImageFile(pngImage));
        assertTrue(FileInspector.isImageFile(jpegImage));
        assertFalse(FileInspector.isImageFile(txtFile));
    }

    @Test
    public void getFileContentType_returnsCorrectContentType() {
        File pngImage = new File("src/test/resources/bluePNG.png");
        File jpegImage = new File("src/test/resources/greenJPEG.jpeg");
        File txtFile = new File("src/test/resources/textFile.txt");
        assertEquals("image/png", FileInspector.getFileContentType(pngImage));
        assertEquals("image/jpeg", FileInspector.getFileContentType(jpegImage));
        assertEquals("text/plain", FileInspector.getFileContentType(txtFile));

        File invalidFile = new File("src/test/resources/invalidFile");
        assertEquals("", FileInspector.getFileContentType(invalidFile));
    }

    @Test
    public void getFileSizeInBytes_returnsCorrectFileSize() {
        File pngImage = new File("src/test/resources/bluePNG.png");
        File jpegImage = new File("src/test/resources/greenJPEG.jpeg");
        File txtFile = new File("src/test/resources/textFile.txt");

        // file sizes reference the Windows property window for each respective file
        assertEquals(693, FileInspector.getFileSizeInBytes(pngImage));
        assertEquals(1359, FileInspector.getFileSizeInBytes(jpegImage));
        assertEquals(11, FileInspector.getFileSizeInBytes(txtFile));

        // method should do a null/file existence check
        File invalidFile = new File("src/test/resources/invalidFile");
        assertEquals(-1, FileInspector.getFileSizeInBytes(invalidFile));
    }

    @Test
    public void getFileExtension_returnsCorrectExtension() {
        File pngImage = new File("src/test/resources/bluePNG.png");
        File jpegImage = new File("src/test/resources/greenJPEG.jpeg");
        File txtFile = new File("src/test/resources/textFile.txt");
        assertEquals("png", FileInspector.getFileExtension(pngImage));
        assertEquals("jpeg", FileInspector.getFileExtension(jpegImage));
        assertEquals("txt", FileInspector.getFileExtension(txtFile));

        File invalidFile = new File("src/test/resources/invalidFile");
        assertEquals("", FileInspector.getFileExtension(invalidFile));
    }
}
