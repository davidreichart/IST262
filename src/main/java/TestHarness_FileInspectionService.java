import model.service.FileInspectionService;

import java.io.File;

public class TestHarness_FileInspectionService {
    public TestHarness_FileInspectionService() {
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
                "    result: " + fileInspectionService.getFileType();
        return testResult;
    }

    private String test_fileInspectionService_getFileExtension(File file) {
        FileInspectionService fileInspectionService = new FileInspectionService(file);
        String testResult = "getFileExtension:\n" +
                "    result: " + fileInspectionService.getFileExtension();
        return testResult;
    }

    private String test_fileInspectionService_getFileToInspect(File file) {
        FileInspectionService fileInspectionService = new FileInspectionService(file);
        String testResult = "getFileToInspect:\n" +
                "    result: " + fileInspectionService.getFileToInspect().getPath();
        return testResult;
    }

    private String test_fileInspectionService_setFileToInspect(File file) {
        FileInspectionService fileInspectionService = new FileInspectionService(file);
        File differentFile = new File("src/test/resources/yellowJPEG100x200.jpeg");
        fileInspectionService.setFileToInspect(differentFile);
        String testResult = "setFileToInspect:\n" +
                "    result: " + fileInspectionService.getFileToInspect().getPath();
        return testResult;
    }
}
