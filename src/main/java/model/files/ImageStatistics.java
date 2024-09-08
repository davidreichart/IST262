package model.data;

public class ImageStatistics {

    private int imageHeight;
    private int imageWidth;
    private int totalPixels;

    public ImageStatistics(int imageHeight, int imageWidth) {
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.totalPixels = imageHeight * imageWidth;
    }
}