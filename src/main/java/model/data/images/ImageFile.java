package model.data.images;

import model.data.FileTag;
import model.data.ImageStatistics;

import java.awt.*;
import java.io.File;

public abstract class ImageFile {

    private File file;
    private ImageStatistics imageStatistics;
    private FileTag fileTag;

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

    public ImageStatistics getImageStatistics() {
        return this.imageStatistics;
    }
}
