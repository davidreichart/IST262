package model.data.images;

import model.data.FileMetadata;
import model.data.FileTag;

import java.awt.*;
import java.io.File;

public class PNGImage extends ImageFile {

    private File file;
    private Image image;
    private FileTag fileTag;
    private FileMetadata metadata;

    public PNGImage(File file) {
        super(file);
    }

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
