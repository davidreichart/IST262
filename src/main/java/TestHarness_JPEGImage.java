import model.creation.ImageFileFactory;
import model.data.images.JPEGImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class TestHarness_JPEGImage {

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_PURPLE = "\u001B[35m";
    private final String ANSI_CYAN = "\u001B[36m";
    private JPEGImage jpegImage;

    public TestHarness_JPEGImage() {
        System.out.println(ANSI_CYAN + "Testing for" + ANSI_PURPLE + " JPEGImage " + ANSI_RESET + ANSI_CYAN + "class" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "---------------------------" + ANSI_RESET);
        File userFile = getFileFromUser();
        boolean givenImageFile = false;
        while (!givenImageFile) {
            if (isJpegImageFile(userFile)) {
                givenImageFile = true;
            } else {
                System.out.println(ANSI_RED + "You must provide a JPEG image.");
            }
        }
        this.jpegImage = ImageFileFactory.createJPEGImage(userFile);
        System.out.println(ANSI_GREEN + "JPEGImage object created. . ." + ANSI_RESET);
        test_toString();
        test_addFileTag();
        test_removeFileTag();
        test_getFile();
        test_setFile();
        test_setImage();
        test_getFileTag();
        test_setFileTag();
        test_getMetadata();
        test_setMetadata();
    }

    /**
     * Non-test helper method.
     * Prompts the user to enter a path to a file on their computer.
     * If the user provides a valid path to a file on their computer, this returns a File object for that file.
     * If the user does not provide a valid path to a file on their system, it infinitely prompts them to reenter a path until a valid file is given.
     * @return A file object leading to a file on the user's computer.
     */
    private File getFileFromUser() {
        boolean validInput = false;
        System.out.println(ANSI_CYAN + "    Example test files can be found in the test/resources directory of this project if needed." + ANSI_RESET);
        System.out.println(ANSI_CYAN + "    Must be absolute path, for example -> C:\\Users\\YourName\\Desktop\\IST261\\src\\test\\resources\\greenJPEG.jpeg" + ANSI_RESET);
        Scanner consoleScanner = new Scanner(System.in);
        File inputFile = null;
        while (!validInput) {
            System.out.print(ANSI_PURPLE + "Enter in the file path for a JPEG image file: " + ANSI_RESET);
            String inputPath = consoleScanner.nextLine();
            inputFile = new File(inputPath);
            if (!inputFile.isFile()) {
                System.out.println(ANSI_RED + "Your input does not lead to a file on the system." + ANSI_RESET);
            } else {
                validInput = true;
            }
        }
        return inputFile;
    }

    /**
     * Non-test helper method.
     * Checks to see if the provided file is a PNG or JPEG image file.
     * @param userFile The file to check.
     * @return <br>
     * TRUE if the provided file is a PNG or JPEG file. <br>
     * FALSE if the provided file is not a PNG or JPEG file.
     */
    private boolean isJpegImageFile(File userFile) {
        try {
            String fileType = Files.probeContentType(userFile.toPath());
            switch (fileType) {
                case "image/jpeg":
                    return true;
                default:
                    System.out.println(ANSI_RED + "The provided file was of type: " + fileType + " you must provide a png / jpeg image file." + ANSI_RESET);
                    return false;
            }
        } catch (IOException ioException) {
            System.out.println(ANSI_RED + "IO Exception occurred in isImageFile." + ANSI_RESET);
            return false;
        }
    }

    private void test_toString() {
        System.out.println(ANSI_PURPLE + "\ntoString method. . ." + ANSI_RESET);
        System.out.println(this.jpegImage.toString());
    }

    private void test_addFileTag() {
        System.out.println(ANSI_PURPLE + "\naddFileTag method. . ." + ANSI_RESET);

    }

    private void test_removeFileTag() {
        System.out.println(ANSI_PURPLE + "\nremoveFileTag method. . ." + ANSI_RESET);

    }

    private void test_getFile() {
        System.out.println(ANSI_PURPLE + "\ngetFile method. . ." + ANSI_RESET);

    }

    private void test_setFile() {
        System.out.println(ANSI_PURPLE + "\nsetFile method. . ." + ANSI_RESET);

    }

    private void test_setImage() {
        System.out.println(ANSI_PURPLE + "\nsetImage method. . ." + ANSI_RESET);

    }

    private void test_getFileTag() {
        System.out.println(ANSI_PURPLE + "\ngetFileTag method. . ." + ANSI_RESET);
    }

    private void test_setFileTag() {
        System.out.println(ANSI_PURPLE + "\nsetFileTag method. . ." + ANSI_RESET);
    }

    private void test_getMetadata() {
        System.out.println(ANSI_PURPLE + "\ngetMetadata method. . ." + ANSI_RESET);
    }

    private void test_setMetadata() {
        System.out.println(ANSI_PURPLE + "\nsetMetadata method. . ." + ANSI_RESET);

    }
}
