package model.files;

import model.creation.ImageStatisticsFactory;

import java.awt.*;
import java.io.File;

public class PNGImage implements IImageFile {

    private File file;
    private ImageStatistics imageStatistics;
    private FileTag fileTag;

    public PNGImage(File file) {
        this.file = file;
        this.imageStatistics = ImageStatisticsFactory.generateStatistics(this);
    }

    @Override
    public String toString() {
        StringBuilder objectInfo = new StringBuilder();
        objectInfo.append("PNG Image:\n")
                .append("    File Name: ").append(this.file).append("\n")
                .append(this.imageStatistics.toString());

        return objectInfo.toString();
    }

    @Override
    public void addFileTag(FileTag fileTag) throws UnsupportedOperationException {
        if (this.fileTag == null) {
            throw new UnsupportedOperationException("This file already has a tag.");
        } else if (this.fileTag.getName().equals("null")) {
            this.fileTag = fileTag;
        } else {
            this.fileTag = fileTag;
        }
    }

    @Override
    public void removeFileTag(String tagToRemove) {
        if (this.fileTag == null) {
            throw new IllegalArgumentException("There is no tag to remove.");
        } else if (!this.fileTag.getName().equals(tagToRemove)) {
            throw new IllegalArgumentException("This file does not have a tag with that name.");
        } else {
            this.fileTag = new FileTag("null", new Color(0x000000));
        }
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public ImageStatistics getImageStatistics() {
        return imageStatistics;
    }
}
