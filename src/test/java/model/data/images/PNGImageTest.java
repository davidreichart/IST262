package model.data.images;

import model.data.FileMetadata;
import model.data.FileTag;
import model.service.ImageInspectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PNGImageTest {

    private PNGImage testPNGImage;

    @BeforeEach
    void init() {
        File file = new File("src/test/resources/bluePNG.png");
        Image image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        FileTag fileTag = new FileTag("testTag", new Color(0x000000));
        FileMetadata metadata = new FileMetadata(new Dimension(100, 200), 100 * 200);

        this.testPNGImage = new PNGImage(file, image, fileTag, metadata);
    }

    @Test
    void testToString_isFormattedCorrectly() {
    }

    @Test
    void addFileTag_replacesExistingFileTag() {
        FileTag testTagToAdd = new FileTag("addme", new Color(0x5d62bf));
        this.testPNGImage.addFileTag(testTagToAdd);
        assertEquals(testTagToAdd, this.testPNGImage.getFileTag());
    }

    @Test
    void removeFileTag_setsExistingFileTagToFakeNull() {
        this.testPNGImage.removeFileTag(this.testPNGImage.getFileTag().getName());
        FileTag fakeNullTag = new FileTag("null", new Color(0x000000));
        assertEquals(fakeNullTag, this.testPNGImage.getFileTag());
    }

    @Test
    void getFile_returnsExpectedFileObject() {
        assertEquals(new File("src/test/resources/bluePNG.png"), this.testPNGImage.getFile());
    }

    @Test
    void setFile_updatesFileObject() {
        this.testPNGImage.setFile(new File("src/test/resources/redPNG100x200.png"));
        assertEquals(new File("src/test/resources/redPNG100x200.png"), this.testPNGImage.getFile());
    }

    @Test
    void getImage_returnsExpectedImageObject() {
        ImageInspectionService inspectionService = new ImageInspectionService(this.testPNGImage.getImage());
        Image sameImage = this.testPNGImage.getImage();
        assertTrue(inspectionService.isSameImage(sameImage));
    }

    @Test
    void setImage_updatesImageObject() {
        Image differentImage = null;
        try {
            differentImage = ImageIO.read(new File("src/test/resources/redPNG100x200.png"));
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        assert differentImage != null;
        this.testPNGImage.setImage(differentImage);
        ImageInspectionService inspectionService = new ImageInspectionService(this.testPNGImage.getImage());
        assertTrue(inspectionService.isSameImage(differentImage));
    }

    @Test
    void getFileTag_returnsExpectedFileTagObject() {
        FileTag expected = new FileTag("testTag", new Color(0x000000));
        assertEquals(expected, this.testPNGImage.getFileTag());
    }

    @Test
    void setFileTag_updatesFileTagObject() {
        FileTag differntFileTag = new FileTag("differentTag", new Color(0x0EEEEE));
        this.testPNGImage.setFileTag(differntFileTag);
        assertEquals(differntFileTag, this.testPNGImage.getFileTag());
    }

    @Test
    void getMetadata_returnsExpectedFileMetadataObject() {
        FileMetadata expected = new FileMetadata(new Dimension(100, 200), 100 * 200);
        assertEquals(expected, this.testPNGImage.getMetadata());
    }

    @Test
    void setMetadata_updatesFileMetadataObject() {
        FileMetadata differentMetadata = new FileMetadata(new Dimension(400, 50), 400 * 50);
        this.testPNGImage.setMetadata(differentMetadata);
        assertEquals(differentMetadata, this.testPNGImage.getMetadata());
    }
}