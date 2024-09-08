package model.files;

public class ImageStatistics {

    private int imageHeight;
    private int imageWidth;
    private int totalPixels;

    public ImageStatistics(int imageHeight, int imageWidth) {
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.totalPixels = imageHeight * imageWidth;
    }

    @Override
    public String toString() {
        StringBuilder objectInfo = new StringBuilder();
        objectInfo.append("    Image Statistics:\n")
                .append("        Resolution: ").append(this.imageHeight).append(" x ").append(this.imageWidth).append("\n")
                .append("        Pixel Count: ").append(this.totalPixels);

        return objectInfo.toString();
    }
}