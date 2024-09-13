package model.data.images;

import model.data.FileMetadata;
import model.data.FileTag;

import java.awt.*;
import java.io.File;

/**
 * A PNGImage is an object holding all information needed to contextualize a JPEG image file.
 */
public class PNGImage extends ImageFile {

    private File file;
    private Image image;
    private FileTag fileTag;
    private FileMetadata metadata;

    /**
     * Creates a new PNGImage when only a file path is known.
     * A PNGImage is an object holding all information needed to contextualize a PNG image file.
     * @param file
     */
    public PNGImage(File file) {
        super(file);
    }

    /**
     * Creates a new PNGImage when all class variables are known ahead of time.
     * If these are not known, the File-only constructor should be used.
     * A PNGImage is an object holding all information needed to contextualize a PNG image file.
     * @param file The File object (primarily, the file-path) leading to this image.
     * @param image The Image object reference for the file.
     * @param fileTag The tag used to categorize this image.
     * @param metadata The collection of metadata further contextualizing this image.
     */
    public PNGImage(File file, Image image, FileTag fileTag, FileMetadata metadata) {
        super(file, image, fileTag, metadata);
    }

    /**
     * Returns a string containing all known information about this PNGImage, including all of its metadata.
     * @return A string of all known information on this PNGImage.
     */
    @Override
    public String toString() {
        return "PNG Image File Path: " + this.file.getPath() + "\n" +
                "Image object: " + this.image.toString() + "\n" +
                "File Tag: " + this.file.getName() + "\n" +
                "File Metadata: " + this.metadata.toString();
    }
}
