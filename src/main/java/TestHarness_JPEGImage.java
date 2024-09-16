import model.data.FileMetadata;
import model.data.FileTag;
import model.data.images.JPEGImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TestHarness_JPEGImage {
    public TestHarness_JPEGImage() {
        File testFile = new File("src/test/resources/greenJPEG.jpeg");
        Image testImage = null;
        try {
            testImage = ImageIO.read(testFile);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        FileTag testFileTag = new FileTag("tester_tag", new Color(0x4299d7));
        FileMetadata testMetadata = new FileMetadata(new Dimension(100, 100), 100 * 100);

        JPEGImage testJpegImage = new JPEGImage(testFile, testImage, testFileTag, testMetadata);

        System.out.println("Class Test: JPEGImage");
        System.out.println("---------------------");
        System.out.println(test_JPEGImage_toString(testJpegImage));
        System.out.println(test_JPEGImage_addFileTag(testJpegImage));
        System.out.println(test_JPEGImage_removeFileTag(testJpegImage));
        System.out.println(test_JPEGImage_getFile(testJpegImage));
        System.out.println(test_JPEGImage_setFile(testJpegImage));
        System.out.println(test_JPEGImage_getFileTag(testJpegImage));
        System.out.println(test_JPEGImage_setFileTag(testJpegImage));
        System.out.println(test_JPEGImage_getMetadata(testJpegImage));
        System.out.println(test_JPEGImage_setMetadata(testJpegImage));
        System.out.println();
    }

    private String test_JPEGImage_toString(JPEGImage jpegImage) {
        String testResult = "toString:\n" +
                "    result: " + jpegImage.toString();
        return testResult;
    }

    private String test_JPEGImage_addFileTag(JPEGImage jpegImage) {
        FileTag differentTag = new FileTag("differentTag", new Color(0x253627));
        jpegImage.setFileTag(differentTag);
        String testResult = "addFileTag:\n" +
                "    result: " + jpegImage.getFileTag().getName();
        // reset after test
        FileTag testFileTag = new FileTag("tester_tag", new Color(0x4299d7));
        jpegImage.setFileTag(testFileTag);
        return testResult;
    }

    private String test_JPEGImage_removeFileTag(JPEGImage jpegImage) {
        jpegImage.removeFileTag(jpegImage.getFileTag().getName());
        String testResult = "removeFileTag:\n" +
                "    result: " + jpegImage.getFileTag().getName();
        // reset after test
        FileTag testFileTag = new FileTag("tester_tag", new Color(0x4299d7));
        jpegImage.setFileTag(testFileTag);
        return testResult;
    }

    private String test_JPEGImage_getFile(JPEGImage jpegImage) {
        String testResult = "getFile:\n" +
                "    result: " + jpegImage.getFile().getPath();
        return testResult;
    }

    private String test_JPEGImage_setFile(JPEGImage jpegImage) {
        File differntFile = new File("src/test/resources/yellowJPEG100x200.jpeg");
        jpegImage.setFile(differntFile);
        String testResult = "setFile:\n" +
                "    result: " + jpegImage.getFile().getPath();
        // reset after test
        File testFile = new File("src/test/resources/greenJPEG.jpeg");
        jpegImage.setFile(testFile);
        return testResult;
    }

    private String test_JPEGImage_getFileTag(JPEGImage jpegImage) {
        String testResult = "getFileTag:\n" +
                "    result: " + jpegImage.getFileTag().getName();
        return testResult;
    }

    private String test_JPEGImage_setFileTag(JPEGImage jpegImage) {
        FileTag differentFileTag = new FileTag("different_tag", new Color(0x7f721b));
        jpegImage.setFileTag(differentFileTag);
        String testResult = "setFileTag:\n" +
                "    result: " + jpegImage.getFileTag().getName();
        // resetting after test
        FileTag testFileTag = new FileTag("tester_tag", new Color(0x4299d7));
        jpegImage.setFileTag(testFileTag);
        return testResult;
    }

    private String test_JPEGImage_getMetadata(JPEGImage jpegImage) {
        String testResult = "getMetadata:\n" +
                "    result: " + jpegImage.getMetadata().toString();
        return testResult;
    }

    private String test_JPEGImage_setMetadata(JPEGImage jpegImage) {
        FileMetadata differentMetadata = new FileMetadata(new Dimension(800, 30), 800 * 30);
        jpegImage.setMetadata(differentMetadata);
        String testResult = "setMetadata:\n" +
                "    result: " + jpegImage.getMetadata().toString();
        FileMetadata testMetadata = new FileMetadata(new Dimension(100, 100), 100 * 100);
        jpegImage.setMetadata(testMetadata);
        return testResult;
    }
}
