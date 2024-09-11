package model.data.images;

import model.data.FileMetadata;
import model.data.FileTag;

import java.awt.*;
import java.io.File;

public abstract class ImageFile {

    private File file;
    private Image image;
    private FileTag fileTag;
    private FileMetadata metadata;

    public ImageFile (File file) {
        this.file = file;
    }

    public ImageFile (File file, Image image, FileTag fileTag, FileMetadata metadata) {
        this.file = file;
        this.image = image;
        this.fileTag = fileTag;
        this.metadata = metadata;
    }

    public void addFileTag(FileTag fileTag) {
        if (this.fileTag == null) {
            throw new UnsupportedOperationException("This file already has a tag.");
        } else if (this.fileTag.getName().equals("null")) {
            this.fileTag = fileTag;
        } else {
            this.fileTag = fileTag;
        }
    }

    public void removeFileTag(String tagToRemove) {
        if (this.fileTag == null) {
            throw new IllegalArgumentException("There is no tag to remove.");
        } else if (!this.fileTag.getName().equals(tagToRemove)) {
            throw new IllegalArgumentException("This file does not have a tag with that name.");
        } else {
            this.fileTag = new FileTag("null", new Color(0x000000));
        }
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public FileTag getFileTag() {
        return fileTag;
    }

    public void setFileTag(FileTag fileTag) {
        this.fileTag = fileTag;
    }

    public FileMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(FileMetadata metadata) {
        this.metadata = metadata;
    }
}
