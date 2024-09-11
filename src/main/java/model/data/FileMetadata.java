package model.data;

import java.awt.*;

public class FileMetadata {

    private Dimension resolution;
    private int pixelCount;

    public FileMetadata(Dimension resolution, int pixelCount) {
        this.resolution = resolution;
        this.pixelCount = pixelCount;
    }

    public Dimension getResolution() {
        return resolution;
    }

    public void setResolution(Dimension resolution) {
        this.resolution = resolution;
    }

    public int getPixelCount() {
        return pixelCount;
    }

    public void setPixelCount(int pixelCount) {
        this.pixelCount = pixelCount;
    }
}
