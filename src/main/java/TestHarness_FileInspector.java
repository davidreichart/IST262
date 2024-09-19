import model.util.FileInspector;

import java.io.File;

public class TestHarness_FileInspector {

    private final File PNG_IMAGE = new File("src/test/resources/bluePNG.png");
    private final File JPEG_IMAGE = new File("src/test/resources/greenJPEG.jpeg");
    private final File TXT_DOCUMENT = new File("src/test/resources/textFile.txt");
    private final File BAD_PATH = new File("bad/path/example.txt");

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_CYAN = "\u001B[36m";
    private final String SPACER = ANSI_CYAN + "----------------------------------" + ANSI_RESET;
    private final String HEADER = ANSI_CYAN + "==================================" + ANSI_RESET;

    public TestHarness_FileInspector() {
        System.out.println(this.HEADER);
        System.out.println(this.ANSI_CYAN + "CLASS TEST HARNESS: FileInspector" + this.ANSI_RESET);
        System.out.println(this.HEADER);
        test_isImageFile_returnsTrueGivenImageFile();
        System.out.println(this.SPACER);
        test_isImageFile_returnsFalseGivenNonImageFile();
        System.out.println(this.SPACER);
        test_getFileContentType_returnsAccurateType();
        System.out.println(this.SPACER);
        test_getFileContentType_catchesBadFilePath();
        System.out.println(this.SPACER);
        test_getFileSizeInBytes_returnsCorrectlyGivenValidFile();
        System.out.println(this.SPACER);
        test_getFileSizeInBytes_returnsZeroGivenBadFilePath();
        System.out.println(this.SPACER);
        test_getFileExtension_returnsAccurateExtension();
        System.out.println(this.SPACER);
        test_getFileExtension_catchesBadFilePath();
        System.out.println(this.SPACER);
    }

    private void test_isImageFile_returnsTrueGivenImageFile() {
        System.out.println(this.ANSI_CYAN + "FileInspector.isImageFile" + this.ANSI_RESET);
        System.out.println(this.ANSI_CYAN + "(given image files)" + this.ANSI_RESET);
        System.out.println("Given PNG_IMAGE:        " + FileInspector.isImageFile(this.PNG_IMAGE));
        System.out.println("Given JPEG_IMAGE:       " + FileInspector.isImageFile(this.JPEG_IMAGE));
    }

    private void test_isImageFile_returnsFalseGivenNonImageFile() {
        System.out.println(this.ANSI_CYAN + "FileInspector.isImageFile" + this.ANSI_RESET);
        System.out.println(this.ANSI_CYAN + "(given non-image files, should return false)" + this.ANSI_RESET);
        System.out.println("Given TXT_DOCUMENT:     " + FileInspector.isImageFile(this.TXT_DOCUMENT));
        System.out.println("Given BAD_PATH:         " + FileInspector.isImageFile(this.BAD_PATH));
    }

    private void test_getFileContentType_returnsAccurateType() {
        System.out.println(this.ANSI_CYAN + "FileInspector.getFileContentType" + this.ANSI_RESET);
        System.out.println(this.ANSI_CYAN + "(given valid files)" + this.ANSI_RESET);
        System.out.println("Given PNG_IMAGE:        " + FileInspector.getFileContentType(this.PNG_IMAGE));
        System.out.println("Given JPEG_IMAGE:       " + FileInspector.getFileContentType(this.JPEG_IMAGE));
        System.out.println("Given TXT_DOCUMENT:     " + FileInspector.getFileContentType(this.TXT_DOCUMENT));
    }

    private void test_getFileContentType_catchesBadFilePath() {
        System.out.println(this.ANSI_CYAN + "FileInspector.getFileContentType" + this.ANSI_RESET);
        System.out.println(this.ANSI_CYAN + "(given invalid path, should log error)" + this.ANSI_RESET);
        System.out.println("Given BAD_PATH:         " + FileInspector.getFileContentType(this.BAD_PATH));

    }

    private void test_getFileSizeInBytes_returnsCorrectlyGivenValidFile() {
        System.out.println(this.ANSI_CYAN + "FileInspector.getFileSizeInBytes" + this.ANSI_RESET);
        System.out.println(this.ANSI_CYAN + "(given valid files)" + this.ANSI_RESET);
        System.out.println("Given PNG_IMAGE:        " + FileInspector.getFileSizeInBytes(this.PNG_IMAGE));
        System.out.println("Given JPEG_IMAGE:       " + FileInspector.getFileSizeInBytes(this.JPEG_IMAGE));
        System.out.println("Given TXT_DOCUMENT:     " + FileInspector.getFileSizeInBytes(this.TXT_DOCUMENT));

    }

    private void test_getFileSizeInBytes_returnsZeroGivenBadFilePath() {
        System.out.println(this.ANSI_CYAN + "FileInspector.getFileSizeInBytes" + this.ANSI_RESET);
        System.out.println(this.ANSI_CYAN + "(given invalid path, should be 0)" + this.ANSI_RESET);
        System.out.println("Given BAD_PATH:         " + FileInspector.getFileSizeInBytes(this.BAD_PATH));
    }

    private void test_getFileExtension_returnsAccurateExtension() {
        System.out.println(this.ANSI_CYAN + "FileInspector.getFileExtension" + this.ANSI_RESET);
        System.out.println(this.ANSI_CYAN + "(given valid files)" + this.ANSI_RESET);
        System.out.println("Given PNG_IMAGE:        " + FileInspector.getFileExtension(this.PNG_IMAGE));
        System.out.println("Given JPEG_IMAGE:       " + FileInspector.getFileExtension(this.JPEG_IMAGE));
        System.out.println("Given TXT_DOCUMENT:     " + FileInspector.getFileExtension(this.TXT_DOCUMENT));
    }

    private void test_getFileExtension_catchesBadFilePath() {
        System.out.println(this.ANSI_CYAN + "FileInspector.getFileExtension" + this.ANSI_RESET);
        System.out.println(this.ANSI_CYAN + "(given invalid path, should return an empty string)" + this.ANSI_RESET);
        System.out.println("Given BAD_PATH:         " + FileInspector.getFileExtension(this.BAD_PATH));
    }
}
