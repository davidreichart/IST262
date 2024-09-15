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
        testFileInspectionService();
        testJPEGImage();
    }

    public void testFileInspectionService() {
        File testPngFile = new File("src/test/resources/bluePNG.png");
        File testJpegFile = new File("src/test/resources/greenJPEG.jpeg");
        File testTxtFile = new File("src/test/resources/textFile.txt");

        System.out.println("Class Test: FileInspectionService");
        System.out.println("---------------------------------");
        System.out.println(test_fileInspectionService_getFileType(testPngFile));
        System.out.println(test_fileInspectionService_getFileExtension(testPngFile));
        System.out.println(test_fileInspectionService_getFileToInspect(testPngFile));
        System.out.println(test_fileInspectionService_setFileToInspect(testPngFile));
        System.out.println();

    }

    private String test_fileInspectionService_getFileType(File file) {
        FileInspectionService fileInspectionService = new FileInspectionService(file);
        String testResult = "getFileType:\n" +
                "    expected: image/png\n" +
                "    actual: " + fileInspectionService.getFileType();
        return testResult;
    }

    private String test_fileInspectionService_getFileExtension(File file) {
        FileInspectionService fileInspectionService = new FileInspectionService(file);
        String testResult = "getFileExtension:\n" +
                "    expected: png\n" +
                "    actual: " + fileInspectionService.getFileExtension();
        return testResult;
    }

    private String test_fileInspectionService_getFileToInspect(File file) {
        FileInspectionService fileInspectionService = new FileInspectionService(file);
        String testResult = "getFileToInspect:\n" +
                "    expected: src\\test\\resources\\bluePNG.png\n" +
                "    actual: " + fileInspectionService.getFileToInspect().getPath();
        return testResult;
    }

    private String test_fileInspectionService_setFileToInspect(File file) {
        FileInspectionService fileInspectionService = new FileInspectionService(file);
        File differentFile = new File("src/test/resources/yellowJPEG100x200.jpeg");
        fileInspectionService.setFileToInspect(differentFile);
        String testResult = "setFileToInspect:\n" +
                "    expected: src\\test\\resources\\yellowJPEG100x200.jpeg\n" +
                "    actual: " + fileInspectionService.getFileToInspect().getPath();
        return testResult;
    }

    public void testJPEGImage() {
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
