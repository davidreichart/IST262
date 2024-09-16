import model.data.FileMetadata;
import model.data.FileTag;
import model.data.images.JPEGImage;
import model.service.FileInspectionService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TestHarness {
    public TestHarness() {
        TestHarness_JPEGImage testHarnessJpegImage = new TestHarness_JPEGImage();
        TestHarness_ImageInspectionService testHarnessImageInspectionService = new TestHarness_ImageInspectionService();
    }
}
