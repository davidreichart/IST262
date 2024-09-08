package model.files;

import model.creation.ImageStatisticsFactory;

import java.awt.*;
import java.io.File;

public class JPEGImage extends AbstractImageFile {

    private File file;
    private ImageStatistics imageStatistics;
    private FileTag fileTag;

    public JPEGImage(File file) {
        this.file = file;
        this.imageStatistics = ImageStatisticsFactory.generateStatistics(this);
    }

    @Override
    public String toString() {
        StringBuilder objectInfo = new StringBuilder();
        objectInfo.append("JPEG Image:\n")
                .append("    File Name: ").append(this.file).append("\n")
                .append(this.imageStatistics.toString());

        return objectInfo.toString();
    }
}
