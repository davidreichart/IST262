package model.data.filetypes;

import model.util.ImageInspector;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

/**
 * The ImageFile class is a representation of an image file on a user's system.
 * A METADATA record contains file statistics and information.
 * An IMAGE_METADATA record contains image-specific metadata.
 */
public class ImageFile extends SystemFile implements FileSystemResource {

    /**
     * The ImageMetadata record contains image-specific metadata.
     * @param width The width of the image in pixels.
     * @param height The height of the image in pixels.
     * @param colorHistogram A 3D array representing the color histogram of the image.
     */
    public record ImageMetadata(int width, int height, int[][][] colorHistogram) {
        public int pixelCount() {
            return width * height;
        }
    }

    private ImageMetadata IMAGE_METADATA;

    /**
     * Constructs a new ImageFile object.
     * The ImageFile class is a representation of an image file on a user's system.
     * @param absoluteFilePath The absolute file path leading to this file on the user's system.
     */
    public ImageFile(String absoluteFilePath) {
        super(absoluteFilePath);

        BufferedImage image = ImageInspector.loadImage(absoluteFilePath);
        Dimension resolution = ImageInspector.getResolution(image);
        this.IMAGE_METADATA = new ImageMetadata(resolution.width, resolution.height, ImageInspector.generateColorHistogram(image, 8));
    }

    /**
     * Returns the image metadata record associated with this ImageFile.
     * The image metadata record's attributes can be accessed using the dot operator on the returned record.
     * @return The image metadata record associated with this ImageFile.
     */
    public ImageMetadata IMAGE_METADATA() {
        return this.IMAGE_METADATA;
    }

    /**
     * Opens an input stream which can be used to read the data associated with this file system resource.
     * Example uses are for reading characters in a text file, or for reading pixels in an image file.
     * @return An InputStream for the contents contained within the input {@code file};
     * @throws FileNotFoundException If this resource does not exist.
     */
    @Override
    public InputStream open() throws FileNotFoundException {
        if (!new File(this.METADATA().absoluteFilePath()).exists()) {
            throw new FileNotFoundException("The file does not exist.");
        }

        return new FileInputStream(new File(this.METADATA().absoluteFilePath()));
    }

    /**
     * Renames this file system resource.
     * The previous name for the given file/directory will be lost.
     *
     * @param name The new name for this file system resource.
     * @throws UnsupportedOperationException If the file cannot be renamed.
     */
    @Override
    public void rename(String name) throws UnsupportedOperationException {
        File oldFile = new File(this.METADATA().absoluteFilePath());
        File newFile = new File(oldFile.getParent() + File.separator + name);

        if (!oldFile.renameTo(newFile)) {
            throw new UnsupportedOperationException("Failed to rename the file.");
        }
    }

    /**
     * Moves this file system resource to the input file path.
     *
     * @param path The path to the directory to move this system resource to.
     * @throws InvalidPathException If the input file path does not exist or
     *                              this file system resource is already in the input directory.
     */
    @Override
    public void moveTo(String path) throws InvalidPathException {
        try {
            Files.move(Path.of(this.METADATA().absoluteFilePath()), Path.of(path + File.separator + this.METADATA().fileName()));
        } catch (IOException e) {
            throw new InvalidPathException("The input file path does not exist.", path);
        }
    }

    /**
     * Returns a string detailing the type of system resource this is.
     * Possible values include: <br>
     * "Directory" for directories, <br>
     * "Image" for image files (e.g. .png, .jpg), <br>
     * "Text" for text files (e.g. .txt), <br>
     *
     * @return A string detailing the type of system resource this is.
     */
    @Override
    public String getContentType() {
        return "Image";
    }

    /**
     * Replaces the image metadata record associated with this ImageFile.
     * @param imageMetadata The image metadata record to associate with this ImageFile.
     */
    public void setIMAGE_METADATA(ImageMetadata imageMetadata) {
        this.IMAGE_METADATA = imageMetadata;
    }
}
