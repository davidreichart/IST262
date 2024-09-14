package model.data.images;

import model.data.FileTag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PNGImageTest {

    @BeforeEach
    void init() {
        File file = new File("src/test/resources/bluePNG.png");
        Image image;
        try {
            image = ImageIO.read(file);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        FileTag fileTag = new FileTag("testTag", new Color(0x000000))
    }

    @Test
    void testToString_isFormattedCorrectly() {
    }

    @Test
    void addFileTag() {
    }

    @Test
    void removeFileTag() {
    }

    @Test
    void getFile_returnsExpectedFileObject() {
    }

    @Test
    void setFile_updatesFileObject() {
    }

    @Test
    void getImage_returnsExpectedImageObject() {
    }

    @Test
    void setImage_updatesImageObject() {
    }

    @Test
    void getFileTag_returnsExpectedFileTagObject() {
    }

    @Test
    void setFileTag_updatesFileTagObject() {
    }

    @Test
    void getMetadata_returnsExpectedFileMetadataObject() {
    }

    @Test
    void setMetadata_updatesFileMetadataObject() {
    }
}