package model.creation;

import model.data.images.ImageFile;
import model.data.images.JPEGImage;
import model.data.images.PNGImage;
import model.service.FileInspectionService;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * An ImageFileFactory is used to instantiate new ImageFile objects in their correct subclass form.
 * A File object is provided and then assessed to return the appropriate object based upon the codex (extension) of the image.
 */
public final class ImageFileFactory {

    /**
     * Intakes a File object and produces an ImageFile of the appropriate subtype based upon the file's extension.
     * If a non-supported or non-image file is provided, an exception will be thrown and no new object will be
     * created.
     * @param file The file to parse into an ImageFile object.
     * @return An ImageFile object as either its PNGImage or JPEGImage subtype where appropriate.
     * @throws IOException If the provided File is not an image file.
     */
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

    /**
     * Returns the memory address of this ImageFileFactory.
     * This class is currently static and holds no state.
     * There is no need to instantiate it to make use of it.
     * @return the memory address of this ImageFileFactory
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Instantiates a new PNGImage object. This method does not check to ensure the provided File object is a PNG file.
     * @param file The file to parse into a PNGImage object.
     * @return A PNGImage object corresponding to the input File.
     */
    public static PNGImage createPNGImage(File file) {
        return new PNGImage(file);
    }

    /**
     * Instantiates a new JPEGImage object. This method does not check to ensure the provided File object is a JPEG file.
     * @param file The file to parse into a JPEGImage object.
     * @return A JPEGImage object corresponding to the input File.
     */
    public static JPEGImage createJPEGImage(File file) {
        return new JPEGImage(file);
    }
}
