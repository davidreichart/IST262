import model.creation.ImageFileFactory;
import model.files.IImageFile;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);
        System.out.print("Enter a file path to an image: ");
        String filePath = consoleScanner.next();

        IImageFile imageFile = null;
        try {
            imageFile = ImageFileFactory.createImageFile(new File(filePath));
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

        assert imageFile != null;
        System.out.println(imageFile.toString());
    }
}