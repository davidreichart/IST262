package model.creation;

import model.data.images.ImageFile;
import model.data.images.JPEGImage;
import model.data.images.PNGImage;
import model.service.ImageInspectionService;

import java.io.File;
import java.io.IOException;

public class ImageFileFactory {

    private ImageInspectionService inspectionService;

    public static ImageFile createImageFile(File file) throws IOException {
        ImageInspectionService inspectionService = new ImageInspectionService(file);
        String fileExtension = inspectionService.getFileExtension();

        return switch (fileExtension) {
            case "png" -> new PNGImage(file);
            case "jpeg" -> new JPEGImage(file);
            default -> throw new IOException("The provided file is not a known image format.");
        };
    }

    private void applyStatisticsToNewFile(ImageFile imageFile) {

    }
}
