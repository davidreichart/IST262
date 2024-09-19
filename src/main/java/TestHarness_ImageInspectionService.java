import model.ImageInspectionService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TestHarness_ImageInspectionService {

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_BLUE = "\u001B[34m";
    private final String ANSI_CYAN = "\u001B[36m";
    private ImageInspectionService imageInspectionService;
    private Image secondImage;

    public TestHarness_ImageInspectionService() {
        System.out.println(ANSI_CYAN + "Testing for" + ANSI_RESET + ANSI_BLUE + " ImageInspectionService " + ANSI_RESET + ANSI_CYAN + "class");
        System.out.println(ANSI_BLUE + "----------------------------------------" + ANSI_RESET);
        Image userImageFile = getImageFileFromUser();
        System.out.println(ANSI_GREEN + "First image file found. . ." + ANSI_RESET);
        getSecondImageFileFromUser();
        this.imageInspectionService = new ImageInspectionService(userImageFile);
        System.out.println(ANSI_GREEN + "Second image file found. . ." + ANSI_RESET);
        System.out.println(ANSI_GREEN + "ImageInspectionService created. . ." + ANSI_RESET);
        test_toString();
        test_isSameImage();
        test_getRoughColorDistribution();
        test_getResolution();
        test_getImageToInspect();
        test_convertImageToBufferedImage();
        test_setImageToInspect();
        test_convertImageToBufferedImage_overloaded();
        test_getExactColorDistribution();
        System.out.println(ANSI_GREEN + "\nImageInspectionService tests complete. . ."+ ANSI_RESET);
    }

    /**
     * Non-Test helper method.
     * Requests the user to provide a path to a file from the terminal.
     * If that file path leads to a valid file AND that file is an image, this method returns an Image object for that file.
     * If the user provides an invalid path OR a file that is not an image, they will be infinitely prompted until a valid image file is given.
     * @return An image object corresponding to a file on the user's computer.
     */
    private Image getImageFileFromUser() {
        File userFile = getFileFromUser();
        while (!isImageFile(userFile)) {
            userFile = getFileFromUser();
        }
        Image userImage = null;
        try {
            userImage = ImageIO.read(userFile);
        } catch (IOException ioException) {
            System.out.println(ANSI_RED + "IO Exception occurred while translating input file into an Image object." + ANSI_RESET);
        }
        return userImage;
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
            System.out.print(ANSI_BLUE + "Enter in the file path for a (png / jpeg) image file: " + ANSI_RESET);
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
    private boolean isImageFile(File userFile) {
        try {
            String fileType = Files.probeContentType(userFile.toPath());
            switch (fileType) {
                case "image/png", "image/jpeg":
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

    /**
     * Non-test helper method.
     * Prompts the user for a second file. This follows the same steps as the initial prompt.
     */
    private void getSecondImageFileFromUser() {
        System.out.println(ANSI_CYAN + "    Some of these methods require a second Image object to run." + ANSI_RESET);
        System.out.println(ANSI_CYAN + "    You may input the same image file path or a different one." + ANSI_RESET);
        this.secondImage = getImageFileFromUser();
    }

    private void test_toString() {
        System.out.println(ANSI_BLUE + "\ntoString method. . ." + ANSI_RESET);
        System.out.println(this.imageInspectionService.toString());
    }

    private void test_isSameImage() {
        System.out.println(ANSI_BLUE + "\nisSameImage method. . ." + ANSI_RESET);
        System.out.println("UNIMPLEMENTED");
    }

    private void test_getExactColorDistribution() {
        System.out.println(ANSI_BLUE + "\ngetExactColorDistribution method. . ." + ANSI_RESET);
        HashMap<Color, Integer> methodResult = this.imageInspectionService.getExactColorDistribution();

        // there could be 10s of thousands of entries in this map,
        // so we ask the user if they want to see that BEFORE flooding the console
        System.out.println(ANSI_YELLOW + "    getExactColorDistribution found " + methodResult.size() + " different color(s)." + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "    Do you want to display the full results? This may hide previous console output." + ANSI_RESET);
        Scanner consoleScanner = new Scanner(System.in);
        System.out.print(ANSI_CYAN + "Enter y or n: " + ANSI_RESET);
        boolean displayFullResults = false;
        String input = (String) consoleScanner.next();

        // checking the user's decision
        if (input.equals("y") || input.equals("Y")) {
            displayFullResults = true;
        } else if (input.equals("n") || input.equals("N")) {
            displayFullResults = false;
        } else {
            System.out.println(ANSI_RED + "Did not enter y or n, full results will not be displayed." + ANSI_RESET);
            displayFullResults = false;
        }

        if (displayFullResults) {
            for (Map.Entry<Color, Integer> entry : methodResult.entrySet()) {
                System.out.print("| Color: " + entry.getKey() + " Quantity: " + entry.getValue() + " |");
            }
        }
    }

    private void test_getRoughColorDistribution() {
        System.out.println(ANSI_BLUE + "\ngetRoughColorDistribution method. . ." + ANSI_RESET);
        HashMap<Color, Integer> methodResult = this.imageInspectionService.getRoughColorDistribution();
        boolean displayFullResults = false;
        for (Map.Entry<Color, Integer> entry : methodResult.entrySet()) {
            System.out.print("| Color: " + entry.getKey() + " Quantity: " + entry.getValue() + " |");
        }
        System.out.println();
    }

    private void test_getResolution() {
        System.out.println(ANSI_BLUE + "\ngetResolution method. . ." + ANSI_RESET);
        Dimension methodResult = this.imageInspectionService.getResolution();
        System.out.println(methodResult.toString());
    }

    private void test_getImageToInspect() {
        System.out.println(ANSI_BLUE + "\ngetImageToInspect method. . ." + ANSI_RESET);
        System.out.println(this.imageInspectionService.getImageToInspect().toString());
    }

    private void test_convertImageToBufferedImage() {
        System.out.println(ANSI_BLUE + "\nconvertImageToBufferedImage method. . ." + ANSI_RESET);
        BufferedImage methodResult = this.imageInspectionService.convertImageToBufferedImage();
        System.out.println(methodResult.toString());
    }

    private void test_setImageToInspect() {
        System.out.println(ANSI_BLUE + "\nsetImageToInspect method. . ." + ANSI_RESET);
        System.out.println(ANSI_CYAN + "    Previous image held by ImageInspectionService:" + ANSI_RESET);
        System.out.println(this.imageInspectionService.getImageToInspect());
        this.imageInspectionService.setImageToInspect(this.secondImage);
        System.out.println(ANSI_CYAN + "    Image now held by ImageInspectionService:" + ANSI_RESET);
        System.out.println(this.imageInspectionService.getImageToInspect());
    }

    private void test_convertImageToBufferedImage_overloaded() {
        System.out.println(ANSI_BLUE + "\nconvertImageToBufferedImage(Image argument overloaded) method. . ." + ANSI_RESET);
        BufferedImage methodResult = this.imageInspectionService.convertImageToBufferedImage(this.secondImage);
        System.out.println(methodResult.toString());
    }
}
