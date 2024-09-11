package model.data.images;

import model.data.FileMetadata;
import model.data.FileTag;

import java.awt.*;
import java.io.File;

public class JPEGImage extends ImageFile {

    private File file;
    private Image image;
    private FileTag fileTag;
    private FileMetadata metadata;

    public JPEGImage(File file) {
        super(file);
    }

    @Override
    public String toString() {
        StringBuilder objectInfo = new StringBuilder();
        objectInfo.append("JPEG Image:\n")
                .append("    File Name: ").append(this.file).append("\n");

        return objectInfo.toString();
    }
}