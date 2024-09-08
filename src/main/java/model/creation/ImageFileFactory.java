package model.creation;

import model.files.IImageFile;
import model.files.JPEGImage;
import model.files.PNGImage;
import model.service.ImageInspectionService;

import java.io.File;
import java.io.IOException;

public class ImageFileFactory {

    public static IImageFile createImageFile(File file) throws IOException {
        ImageInspectionService inspectionService = new ImageInspectionService(file);
        String fileExtension = inspectionService.getFileExtension();

        return switch (fileExtension) {
            case "png" -> new PNGImage(file);
            case "jpeg" -> new JPEGImage(file);
            default -> throw new IOException("The provided file is not a known image format.");
        };
    }
}
