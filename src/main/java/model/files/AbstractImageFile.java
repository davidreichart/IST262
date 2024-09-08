package model.files;

import java.awt.*;
import java.io.File;

abstract class AbstractImageFile implements IImageFile {

    private File file;
    private ImageStatistics imageStatistics;
    private FileTag fileTag;

    @Override
    public void addFileTag(FileTag fileTag) {
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
        return this.file;
    }

    @Override
    public ImageStatistics getImageStatistics() {
        return this.imageStatistics;
    }
}
