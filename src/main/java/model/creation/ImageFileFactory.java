package model.creation;

import model.data.images.ImageFile;
import model.data.images.JPEGImage;
import model.data.images.PNGImage;
import model.service.FileInspectionService;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageFileFactory {

    public static ImageFile createImageFile(File file) throws IOException {
        FileInspectionService fileInspectionService = new FileInspectionService(file);

        return switch (fileInspectionService.getFileExtension()) {
            case "png":
                yield createPNGImage(file);
            case "jpeg":
                yield createJPEGImage(file);
            default:
                throw new IOException("The provided file is not a known image format.");
        };
    }

    public static PNGImage createPNGImage(File file) {
        return new PNGImage(file);
    }

    public static JPEGImage createJPEGImage(File file) {
        return new JPEGImage(file);
    }
}
