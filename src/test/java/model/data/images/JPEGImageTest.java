package model.data.images;

import model.data.OldFileMetadata;
import model.data.FileTag;
import model.util.ImageInspectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
class JPEGImageTest {

    private JPEGImage testJPEGImage;

    @BeforeEach
    void init() {
        File file = new File("src/test/resources/greenJPEG.jpeg");
        Image image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        FileTag fileTag = new FileTag("testTag", new Color(0x000000));
        OldFileMetadata metadata = new OldFileMetadata(new Dimension(100, 200), 100 * 200);

        this.testJPEGImage = new JPEGImage(file, image, fileTag, metadata);
    }

    @Test
    void testToString_isFormattedCorrectly() {
    }

    @Test
    void addFileTag_replacesExistingFileTag() {
        FileTag testTagToAdd = new FileTag("addme", new Color(0x5d62bf));
        this.testJPEGImage.addFileTag(testTagToAdd);
        assertEquals(testTagToAdd, this.testJPEGImage.getFileTag());
    }

    @Test
    void removeFileTag_setsExistingFileTagToFakeNull() {
        this.testJPEGImage.removeFileTag(this.testJPEGImage.getFileTag().getName());
        FileTag fakeNullTag = new FileTag("null", new Color(0x000000));
        assertEquals(fakeNullTag, this.testJPEGImage.getFileTag());
    }

    @Test
    void getFile_returnsExpectedFileObject() {
        assertEquals(new File("src/test/resources/greenJPEG.jpeg"), this.testJPEGImage.getFile());
    }

    @Test
    void setFile_updatesFileObject() {
        this.testJPEGImage.setFile(new File("src/test/resources/yellowJPEG100x200.jpeg"));
        assertEquals(new File("src/test/resources/yellowJPEG100x200.jpeg"), this.testJPEGImage.getFile());
    }

    @Test
    void getImage_returnsExpectedImageObject() {
        ImageInspectionService inspectionService = new ImageInspectionService(this.testJPEGImage.getImage());
        Image sameImage = this.testJPEGImage.getImage();
        assertTrue(inspectionService.isSameImage(sameImage));
    }

    @Test
    void setImage_updatesImageObject() {
        Image differentImage = null;
        try {
            differentImage = ImageIO.read(new File("src/test/resources/yellowJPEG100x200.jpeg"));
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        assert differentImage != null;
        this.testJPEGImage.setImage(differentImage);
        ImageInspectionService inspectionService = new ImageInspectionService(this.testJPEGImage.getImage());
        assertTrue(inspectionService.isSameImage(differentImage));
    }

    @Test
    void getFileTag_returnsExpectedFileTagObject() {
        FileTag expected = new FileTag("testTag", new Color(0x000000));
        assertEquals(expected, this.testJPEGImage.getFileTag());
    }

    @Test
    void setFileTag_updatesFileTagObject() {
        FileTag differntFileTag = new FileTag("differentTag", new Color(0x0EEEEE));
        this.testJPEGImage.setFileTag(differntFileTag);
        assertEquals(differntFileTag, this.testJPEGImage.getFileTag());
    }

    @Test
    void getMetadata_returnsExpectedFileMetadataObject() {
        OldFileMetadata expected = new OldFileMetadata(new Dimension(100, 200), 100 * 200);
        assertEquals(expected, this.testJPEGImage.getMetadata());
    }

    @Test
    void setMetadata_updatesFileMetadataObject() {
        OldFileMetadata differentMetadata = new OldFileMetadata(new Dimension(400, 50), 400 * 50);
        this.testJPEGImage.setMetadata(differentMetadata);
        assertEquals(differentMetadata, this.testJPEGImage.getMetadata());
    }
}