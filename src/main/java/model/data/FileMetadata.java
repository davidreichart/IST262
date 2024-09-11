package model.data;

import java.awt.*;

public class FileMetadata {

    private Dimension resolution;
    private int pixelCount;

    public FileMetadata(Dimension resolution, int pixelCount) {
        this.resolution = resolution;
        this.pixelCount = pixelCount;
    }

}
