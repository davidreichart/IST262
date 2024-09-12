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
     * Returns a string representation of this PNGImage object.
     * The string includes all metadata known about this image.
     * @return A string representing the full context of this PNGImage.
     */
    @Override
    public String toString() {
        StringBuilder objectInfo = new StringBuilder();
        objectInfo.append("PNG Image:\n")
                .append("    File Name: ").append(this.file).append("\n");

        return objectInfo.toString();
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public FileTag getFileTag() {
        return fileTag;
    }

    @Override
    public void setFileTag(FileTag fileTag) {
        this.fileTag = fileTag;
    }

    @Override
    public FileMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(FileMetadata metadata) {
        this.metadata = metadata;
    }
}
