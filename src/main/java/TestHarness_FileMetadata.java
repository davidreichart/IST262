import model.data.FileMetadata;
import model.util.FileInspector;

import java.io.File;

public class TestHarness_FileMetadata {

    private final File PNG_IMAGE = new File("src/test/resources/bluePNG.png");
    private final File JPEG_IMAGE = new File("src/test/resources/greenJPEG.jpeg");
    private final File TXT_DOCUMENT = new File("src/test/resources/textFile.txt");
    private final File BAD_PATH = new File("bad/path/example.txt");

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_PURPLE = "\u001B[35m";
    private final String SPACER = ANSI_PURPLE + "----------------------------------" + ANSI_RESET;
    private final String HEADER = ANSI_PURPLE + "==================================" + ANSI_RESET;

    public TestHarness_FileMetadata() {
        System.out.println(this.HEADER);
        System.out.println(this.ANSI_PURPLE + "CLASS TEST HARNESS: FileMetadata" + this.ANSI_RESET);
        System.out.println(this.ANSI_YELLOW + "NOTE: This class depends on the FileInspector for data collection." + this.ANSI_RESET);
        System.out.println(this.ANSI_YELLOW + "      Errors may be a result of FileInspector's behavior." + this.ANSI_RESET);
        System.out.println(this.HEADER);
        test_classConstructor_givenFieldsManually();
        System.out.println(this.SPACER);
        test_classConstructor_givenFieldsFromImageInspector();
        System.out.println(this.SPACER);
        test_builder_buildsGivenAllFieldsManually();
        System.out.println(this.SPACER);
        test_builder_buildsGivenNotAllFieldsManually();
        System.out.println(this.SPACER);
        test_builder_buildsGivenAllFieldsFromImageInspector();
        System.out.println(this.SPACER);
        test_builder_buildsGivenNotAllFieldsFromImageInspector();
        System.out.println(this.SPACER);

    }

    private void test_classConstructor_givenFieldsManually() {
        System.out.println(this.ANSI_PURPLE + "new FileMetadata constructor" + this.ANSI_RESET);
        System.out.println(this.ANSI_PURPLE + "(all fields manually provided)" + this.ANSI_RESET);
        FileMetadata metadata = new FileMetadata(
                "...Absolute Path",
                "...Content Type",
                200,
                "...File Name",
                "...File Extension");
        System.out.println(ANSI_GREEN + "FileMetadata object constructed. . ." + ANSI_RESET);
        System.out.println(metadata.toString());
    }

    private void test_classConstructor_givenFieldsFromImageInspector() {
        System.out.println(this.ANSI_PURPLE + "new FileMedata constructor" + this.ANSI_RESET);
        System.out.println(this.ANSI_PURPLE + "(all fields provided by FileInspector & File class calls" + this.ANSI_RESET);
        FileMetadata metadata = new FileMetadata(
                this.PNG_IMAGE.getAbsolutePath(),
                FileInspector.getFileContentType(this.PNG_IMAGE),
                FileInspector.getFileSizeInBytes(this.PNG_IMAGE),
                this.PNG_IMAGE.getName(),
                FileInspector.getFileExtension(this.PNG_IMAGE));
        System.out.println(this.ANSI_GREEN + "FileMetadata object constructed (PNG_IMAGE). . ." + this.ANSI_RESET);
        System.out.println(metadata.toString());
    }

    private void test_builder_buildsGivenAllFieldsManually() {
        System.out.println(this.ANSI_PURPLE + "new FileMetadata builder" + this.ANSI_RESET);
        System.out.println(this.ANSI_PURPLE + "(all fields manually provided)" + this.ANSI_RESET);
        FileMetadata metadata = FileMetadata.builder()
                .absolutePath("...Absolute Path")
                .contentType("...Content type")
                .byteCount(200)
                .fileName("...File name")
                .fileExtension("...File extension")
                .build();
        System.out.println(this.ANSI_GREEN + "FileMetadata object constructed. . ." + this.ANSI_RESET);
        System.out.println(metadata.toString());
    }

    private void test_builder_buildsGivenNotAllFieldsManually() {
        System.out.println(this.ANSI_PURPLE + "new FileMetadata builder" + this.ANSI_RESET);
        System.out.println(this.ANSI_PURPLE + "(fields manually provided, some fields skipped)" + this.ANSI_RESET);
        FileMetadata metadata = FileMetadata.builder()
                .absolutePath("...Absolute path")
                .fileExtension("...File extension")
                .build();
        System.out.println(this.ANSI_GREEN + "FileMetadata object constructed. . ." + this.ANSI_RESET);
        System.out.println(metadata.toString());
    }

    private void test_builder_buildsGivenAllFieldsFromImageInspector() {
        System.out.println(this.ANSI_PURPLE + "new FileMetadata builder" + this.ANSI_RESET);
        System.out.println(this.ANSI_PURPLE + "(all fields provided by ImageInspector & Files class calls)" + this.ANSI_RESET);
        FileMetadata metadata = FileMetadata.builder()
                .absolutePath(this.PNG_IMAGE.getAbsolutePath())
                .contentType(FileInspector.getFileContentType(this.PNG_IMAGE))
                .byteCount(FileInspector.getFileSizeInBytes(this.PNG_IMAGE))
                .fileName(this.PNG_IMAGE.getName())
                .fileExtension(FileInspector.getFileExtension(this.PNG_IMAGE))
                .build();
        System.out.println(this.ANSI_GREEN + "FileMetadata object constructed (PNG_IMAGE). . ." + this.ANSI_RESET);
        System.out.println(metadata.toString());
    }

    private void test_builder_buildsGivenNotAllFieldsFromImageInspector() {
        System.out.println(this.ANSI_PURPLE + "new FileMetadata builder" + this.ANSI_RESET);
        System.out.println(this.ANSI_PURPLE + "(fields provided by ImageInspector & Files class calls, some fields skipped)" + this.ANSI_RESET);
        FileMetadata metadata = FileMetadata.builder()
                .absolutePath(this.PNG_IMAGE.getAbsolutePath())
                .contentType(FileInspector.getFileContentType(this.PNG_IMAGE))
                .build();
        System.out.println(this.ANSI_GREEN + "FileMetadata object constructed (PNG_IMAGE). . ." + this.ANSI_RESET);
        System.out.println(metadata.toString());
    }
}
