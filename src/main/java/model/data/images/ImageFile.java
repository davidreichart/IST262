package model.data.images;

import model.data.OldFileMetadata;
import model.data.FileTag;

import java.awt.*;
import java.io.File;

/**
 * The ImageFile class is an abstract class that serves as the base for commonality between any image file.
 * ImageFile objects store metadata that contextualizes the image with file properties, tags, and a reference to a
 * corresponding Image object.
 */
public abstract class ImageFile {

    private File file;
    private Image image;
    private FileTag fileTag;
    private OldFileMetadata metadata;

    /**
     * Creates a new ImageFile when only a file path is known.
     * The ImageFile class is an abstract class that serves as the base for commonality between any image file.
     * @param file
     */
    public ImageFile (File file) {
        this.file = file;
    }

    /**
     * Creates a new ImageFile when all class variables are known ahead of time.
     * If these are not known, the File-only constructor should be used.
     * The ImageFile class is an abstract class that serves as the base for commonality between any image file.
     * @param file The File object (primarily, the file-path) leading to this image.
     * @param image The Image object reference for the file.
     * @param fileTag The tag used to categorize this image.
     * @param metadata The collection of metadata further contextualizing this image.
     */
    public ImageFile (File file, Image image, FileTag fileTag, OldFileMetadata metadata) {
        this.file = file;
        this.image = image;
        this.fileTag = fileTag;
        this.metadata = metadata;
    }

    /**
     * Returns a string containing all known information about this ImageFile, including all of its metadata.
     * @return A string of all known information on this ImageFile.
     */
    @Override
    public String toString() {
        return "Image File Path: " + this.file.getPath() + "\n" +
                "Image object: " + this.image.toString() + "\n" +
                "File Tag: " + this.file.getName() + "\n" +
                "File Metadata: " + this.metadata.toString();
    }

    /**
     * Applies the passed FileTag to this image file.
     * If this image already has a file tag, an exception will be thrown and the tag will not be replaced.
     * If there is no tag applied, the given tag will be applied.
     * @param fileTag The file tag to apply to this image file.
     * @throws UnsupportedOperationException If this image file already has a file tag applied to it.
     */
    public void addFileTag(FileTag fileTag) throws UnsupportedOperationException {
        if (this.fileTag == null) {
            throw new UnsupportedOperationException("This file already has a tag.");
        } else if (this.fileTag.getName().equals("null")) {
            this.fileTag = fileTag;
        } else {
            this.fileTag = fileTag;
        }
    }

    /**
     * Removes the current FileTag from this image file.
     * If the string given does not match the tag for this image, an execution is thrown and the tag is not updated.
     * If there is no tag applied to this image file, an exception is thrown and no action is taken.
     * A tag "null" will be applied in its place, this tag is ignored by the GUI.
     * @param tagToRemove A string equal to the name of the FileTag to be removed from this image file.
     * @throws IllegalArgumentException If this image file has no tag OR <br>
     * this image file does not have a tag by the given {@code tagToRemove} name.
     */
    public void removeFileTag(String tagToRemove) throws IllegalArgumentException {
        //todo: the "null" tag strategy should probably be replaced. This should happen when multiple tags per image is supported
        if (this.fileTag == null) {
            throw new IllegalArgumentException("There is no tag to remove.");
        } else if (!this.fileTag.getName().equals(tagToRemove)) {
            throw new IllegalArgumentException("This file does not have a tag with that name.");
        } else {
            this.fileTag = new FileTag("null", new Color(0x000000));
        }
    }

    /**
     * Returns the File object associated with this ImageFile. This provides a path to the file on the users system.
     * @return A File object corresponding to this ImageFile.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Replaces the File object associated with this ImageFile.
     * This should be done if the file changes location on the system.
     * @param file The new File object to associate with this ImageFile.
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Returns the Image object representation of this ImageFile.
     * @return The Image object representation of this ImageFile.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Replaces the existing Image object representation of this ImageFile.
     * @param image The new Image object that this ImageFile should be associated to.
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Returns the FileTag applied to this ImageFile.
     * The FileTag is used for custom organization as defined by the user.
     * @return The FileTag applied to this ImageFile.
     */
    public FileTag getFileTag() {
        return fileTag;
    }

    /**
     * Replaces the existing FileTag applied to this ImageFile.
     * The FileTag is used for custom organization as defined by the user.
     * @param fileTag The new FileTag to replace the existing tag on this ImageFile.
     */
    public void setFileTag(FileTag fileTag) {
        this.fileTag = fileTag;
    }

    /**
     * Returns a FileMetadata object that contains multiple fields of information that further contextualize an ImageFile.
     * @return A FileMetadata object containing full image file context.
     */
    public OldFileMetadata getMetadata() {
        return metadata;
    }

    /**
     * Replaces the existing FileMetadata object used to store contextual information about an ImageFile.
     * @param metadata The new FileMetadata object ot overwrite the existing one on this ImageFile.
     */
    public void setMetadata(OldFileMetadata metadata) {
        this.metadata = metadata;
    }
}
