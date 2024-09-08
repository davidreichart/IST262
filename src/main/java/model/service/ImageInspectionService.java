package model.service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Objects;

public class ImageInspectionService {

    File fileToInspect;

    public ImageInspectionService(File fileToParse) {
        this.fileToInspect = fileToParse;
    }

    public boolean isValidFilePath(String inputFilePath) {
        try {
            Paths.get(inputFilePath);
        } catch (InvalidPathException invalidPathException) {
            System.out.println("The provided file path does not lead to a valid file on the system.");
            return false;
        }
        return true;
    }

    public boolean isImageFile(File file) {
        try {
            ImageIO.read(file);
        } catch (IOException ioException) {
            System.out.println("The provided file is not an image file.");
            return false;
        }
        return true;
    }

    private BufferedImage parseImageFromFile() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(this.fileToInspect);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

        if(Objects.isNull(image)) {
            throw new IllegalArgumentException("The given file is not an image file.");
        } else {
            return image;
        }
    }

    public String getFileExtension() {
        String fileName = this.fileToInspect.getName();
        int extensionDotIndex = fileName.lastIndexOf(".");
        return fileName.substring(extensionDotIndex + 1);
    }

    public int[] getResolution() {
        BufferedImage image = null;
        try {
            image = parseImageFromFile();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
        }
        assert image != null;

        int[] resolution = new int[2];
        resolution[0] = image.getHeight();
        resolution[1] = image.getWidth();
        return resolution;
    }
}
