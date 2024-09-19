package model.data;

import java.awt.*;
import java.util.TreeMap;

public class ImageMetadata {

    private Dimension resolution;
    private int pixelCount;
    private TreeMap<Color, Integer> roughColorDistribution;
    private TreeMap<Color, Integer> exactColorDistribution;

    public ImageMetadata (Dimension resolution, int pixelCount, TreeMap<Color, Integer> roughColorDistribution,
                          TreeMap<Color, Integer> exactColorDistribution) {
        this.resolution = resolution;
        this.pixelCount = pixelCount;
        this.roughColorDistribution = roughColorDistribution;
        this.exactColorDistribution = exactColorDistribution;
    }

    private ImageMetadata(Builder builder) {
        this.resolution = builder.resolution;
        this.pixelCount = builder.pixelCount;
        this.roughColorDistribution = builder.roughColorDistribution;
        this.exactColorDistribution = builder.exactColorDistribution;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Dimension resolution;
        private int pixelCount;
        private TreeMap<Color, Integer> roughColorDistribution;
        private TreeMap<Color, Integer> exactColorDistribution;

        public Builder resolution(Dimension resolution) {
            this.resolution = resolution;
            return this;
        }

        public Builder pixelCount(int pixelCount) {
            this.pixelCount = pixelCount;
            return this;
        }

        public Builder roughColorDistribution(TreeMap<Color, Integer> roughColorDistribution) {
            this.roughColorDistribution = roughColorDistribution;
            return this;
        }

        public Builder exactColorDistribution(TreeMap<Color, Integer> exactColorDistribution) {
            this.exactColorDistribution = exactColorDistribution;
            return this;
        }

        public ImageMetadata build() {
            return new ImageMetadata(this);
        }
    }

    public Dimension getResolution() {
        return resolution;
    }

    public int getPixelCount() {
        return pixelCount;
    }

    public TreeMap<Color, Integer> getRoughColorDistribution() {
        return roughColorDistribution;
    }

    public TreeMap<Color, Integer> getExactColorDistribution() {
        return exactColorDistribution;
    }
}
