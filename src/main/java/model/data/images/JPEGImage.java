package model.data.images;

import model.data.FileMetadata;
import model.data.FileTag;

import java.awt.*;
import java.io.File;

/**
 * A JPEGImage is an object holding all information needed to contextualize a JPEG image file.
 */
public class JPEGImage extends ImageFile {

    private File file;
    private Image image;
    private FileTag fileTag;
    private FileMetadata metadata;

    /**
     * Creates a new JPEGImage when only a file path is known.
     * A JPEGImage is an object holding all information needed to contextualize a JPEG image file.
     * @param file
     */
    public JPEGImage(File file) {
        super(file);
    }

    /**
     * Creates a new JPEGImage when all class variables are known ahead of time.
     * If these are not known, the File-only constructor should be used.
     * A JPEGImage is an object holding all information needed to contextualize a JPEG image file.
     * @param file The File object (primarily, the file-path) leading to this image.
     * @param image The Image object reference for the file.
     * @param fileTag The tag used to categorize this image.
     * @param metadata The collection of metadata further contextualizing this image.
     */
    public JPEGImage(File file, Image image, FileTag fileTag, FileMetadata metadata) {
        super(file, image, fileTag, metadata);
    }

    /**
     * Returns a string representation of this JPEGImage object.
     * The string includes all metadata known about this image.
     * @return A string representing the full context of this JPEGImage.
     */
    @Override
    public String toString() {
        StringBuilder objectInfo = new StringBuilder();
        objectInfo.append("JPEG Image:\n")
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
