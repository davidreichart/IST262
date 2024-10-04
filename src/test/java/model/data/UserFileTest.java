package model.data;

import model.data.metadata.FileMetadata;
import model.data.metadata.ImageMetadata;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.TreeMap;

public class UserFileTest {

    @Test
    public void userFileBuilder_fullBuilder() {
        LinkedHashSet<FileTag> fileTags = new LinkedHashSet<>();
        fileTags.add(new FileTag("TestFileTag", Color.GRAY));
        UserFile testUserFile = new UserFile(
                new File("testFile.jpg"),
                new FileMetadata.Builder()
                        .absolutePath("C:\\Users\\test\\testFile.jpg")
                        .contentType("image/jpeg")
                        .byteCount(1000)
                        .fileName("testFile.jpg")
                        .fileExtension(".jpg")
                        .build(),
                new ImageMetadata.Builder()
                        .resolution(new Dimension(1920, 1080))
                        .pixelCount(2073600)
                        .roughColorDistribution(new TreeMap<PixelColor, Integer>())
                        .exactColorDistribution(new TreeMap<PixelColor, Integer>())
                        .build(),
                fileTags
        );

        // checking FileMetadata attributes
        assertEquals("C:\\Users\\test\\testFile.jpg", testUserFile.getFileMetadata().getAbsolutePath());
        assertEquals("image/jpeg", testUserFile.getFileMetadata().getContentType());
        assertEquals(1000, testUserFile.getFileMetadata().getByteCount());
        assertEquals("testFile.jpg", testUserFile.getFileMetadata().getFileName());
        assertEquals(".jpg", testUserFile.getFileMetadata().getFileExtension());

        // checking ImageMetadata attributes
        assertEquals(1920, testUserFile.getImageMetadata().getResolution().width);
        assertEquals(1080, testUserFile.getImageMetadata().getResolution().height);
        assertEquals(2073600, testUserFile.getImageMetadata().getPixelCount());
        assertEquals(new TreeMap<PixelColor, Integer>(), testUserFile.getImageMetadata().getRoughColorDistribution());
        assertEquals(new TreeMap<PixelColor, Integer>(), testUserFile.getImageMetadata().getExactColorDistribution());

        assertEquals(fileTags, testUserFile.getFileTags());
    }

    @Test
    public void userFileBuilder_noMetadataBuilder() {
        LinkedHashSet<FileTag> fileTags = new LinkedHashSet<>();
        fileTags.add(new FileTag("TestFileTag", Color.GRAY));
        UserFile noMetadata = UserFile.builder(new File("testFile.jpg"))
                .fileTags(fileTags)
                .build();

        // UserFiles without metadata should throw UnsupportedOperationException when trying to access metadata
        assertThrows(UnsupportedOperationException.class, () -> noMetadata.getFileMetadata());
        assertThrows(UnsupportedOperationException.class, () -> noMetadata.getImageMetadata());
        assertEquals(new File("testFile.jpg"), noMetadata.getFile());
        assertEquals(fileTags, noMetadata.getFileTags());
    }

    @Test
    public void userFileConstructor_fullNormalConstructor() {
        FileMetadata testFileMetadata = new FileMetadata(
              "C:\\Users\\test\\testFile.jpg",
                "image/jpeg",
                1000,
                "testFile.jpg",
                ".jpg"
        );
        ImageMetadata testImageMetadata = new ImageMetadata(
                new Dimension(1920, 1080),
                2073600,
                new TreeMap<PixelColor, Integer>(),
                new TreeMap<PixelColor, Integer>()
        );
        LinkedHashSet<FileTag> fileTags = new LinkedHashSet<>();
        fileTags.add(new FileTag("TestFileTag", Color.GRAY));

        UserFile testUserFile = new UserFile(
                new File("testFile.jpg"),
                testFileMetadata,
                testImageMetadata,
                fileTags
        );

        // checking FileMetadata attributes
        assertEquals("C:\\Users\\test\\testFile.jpg", testUserFile.getFileMetadata().getAbsolutePath());
        assertEquals("image/jpeg", testUserFile.getFileMetadata().getContentType());
        assertEquals(1000, testUserFile.getFileMetadata().getByteCount());
        assertEquals("testFile.jpg", testUserFile.getFileMetadata().getFileName());
        assertEquals(".jpg", testUserFile.getFileMetadata().getFileExtension());

        // checking ImageMetadata attributes
        assertEquals(1920, testUserFile.getImageMetadata().getResolution().width);
        assertEquals(1080, testUserFile.getImageMetadata().getResolution().height);
        assertEquals(2073600, testUserFile.getImageMetadata().getPixelCount());
        assertEquals(new TreeMap<PixelColor, Integer>(), testUserFile.getImageMetadata().getRoughColorDistribution());
        assertEquals(new TreeMap<PixelColor, Integer>(), testUserFile.getImageMetadata().getExactColorDistribution());

        assertEquals(fileTags, testUserFile.getFileTags());
    }

    @Test
    public void generateAllImageMetadata_GeneratesImageMetadataObject() {
        File testFile = new File("src/test/resources/bluePNG.png");
        LinkedHashSet<FileTag> fileTags = new LinkedHashSet<>();
        UserFile testUserFile = UserFile.builder(testFile)
                .fileTags(fileTags)
                .build();
        testUserFile.generateAllImageMetadata();

        assertNotNull(testUserFile.getImageMetadata());
        assertEquals(100, testUserFile.getImageMetadata().getResolution().width);
        assertEquals(100, testUserFile.getImageMetadata().getResolution().height);
        assertEquals(10000, testUserFile.getImageMetadata().getPixelCount());
    }

    @Test
    public void generateAllFileMetadata_GeneratesFileMetadataObject() {
        File testFile = new File("src/test/resources/bluePNG.png");
        LinkedHashSet<FileTag> fileTags = new LinkedHashSet<>();
        UserFile testUserFile = UserFile.builder(testFile)
                .fileTags(fileTags)
                .build();
        testUserFile.generateAllFileMetadata();

        assertNotNull(testUserFile.getFileMetadata());
        // not checking an absolute path since this varies by system
        assertEquals("image/png", testUserFile.getFileMetadata().getContentType());
        assertEquals(693, testUserFile.getFileMetadata().getByteCount());
        assertEquals("bluePNG.png", testUserFile.getFileMetadata().getFileName());
        assertEquals("png", testUserFile.getFileMetadata().getFileExtension());
    }

    @Test
    public void addFileTag_AppendsNewFileTag() {
        File testFile = new File("src/test/resources/bluePNG.png");
        LinkedHashSet<FileTag> fileTags = new LinkedHashSet<>();
        UserFile testUserFile = UserFile.builder(testFile)
                .fileTags(fileTags)
                .build();
        FileTag testFileTag = new FileTag("TestFileTag", Color.GRAY);
        testUserFile.addFileTag(testFileTag);

        assertTrue(testUserFile.getFileTags().contains(testFileTag));
    }

    @Test
    public void removeFileTag_RemovesFileTag() {
        File testFile = new File("src/test/resources/bluePNG.png");
        LinkedHashSet<FileTag> fileTags = new LinkedHashSet<>();
        FileTag testTagOne = new FileTag("TestFileTag", Color.GRAY);
        FileTag testTagTwo = new FileTag("TestFileTag2", Color.BLACK);
        fileTags.add(testTagOne);
        fileTags.add(testTagTwo);
        UserFile testUserFile = UserFile.builder(testFile)
                .fileTags(fileTags)
                .build();

        assertTrue(testUserFile.getFileTags().size() == 2);
        assertTrue(testUserFile.getFileTags().contains(testTagOne));
        assertTrue(testUserFile.getFileTags().contains(testTagTwo));

        testUserFile.removeFileTag("TestFileTag");
        assertTrue(testUserFile.getFileTags().size() == 1);
        assertFalse(testUserFile.getFileTags().contains(testTagOne));
    }

    @Test
    public void getFile_ReturnsFile() {
        File testFile = new File("src/test/resources/bluePNG.png");
        LinkedHashSet<FileTag> fileTags = new LinkedHashSet<>();
        UserFile testUserFile = UserFile.builder(testFile)
                .fileTags(fileTags)
                .build();

        assertEquals(testFile, testUserFile.getFile());
    }
}
