package model.creation;

import model.data.images.ImageFile;
import model.data.images.JPEGImage;
import model.data.images.PNGImage;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageFileFactoryTest {

    @Test
    void createImageFile_correctlyCreatesPNGImage() {
        ImageFile factoryCreated = null;
        try {
            factoryCreated = ImageFileFactory.createImageFile(new File("src/test/resources/bluePNG.png"));
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        assert factoryCreated != null;
        assertTrue(factoryCreated.getClass().isAssignableFrom(PNGImage.class));
    }

    @Test
    void createImageFile_correctlyCreatesJPEGImage() {
        ImageFile factoryCreated = null;
        try {
            factoryCreated = ImageFileFactory.createImageFile(new File("src/test/resources/greenJPEG.jpeg"));
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        assert factoryCreated != null;
        assertTrue(factoryCreated.getClass().isAssignableFrom(JPEGImage.class));
    }

    @Test
    void testToString() {
        // unimplemented
    }

    @Test
    void createPNGImage_returnsWorkingPNGImageObject() {
        ImageFile factoryCreated = ImageFileFactory.createPNGImage(new File("src/test/resources/bluePNG.png"));
        assertTrue(factoryCreated.getClass().isAssignableFrom(PNGImage.class));
    }

    @Test
    void createJPEGImage_returnsWorkingJPEGImageObject() {
        ImageFile factoryCreated = ImageFileFactory.createJPEGImage(new File("src/test/resources/greenJPEG.jpeg"));
        assertTrue(factoryCreated.getClass().isAssignableFrom(JPEGImage.class));
    }
}