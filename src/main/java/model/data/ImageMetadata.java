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
        if (builder.resolution != null) {
            this.resolution = builder.resolution;
        }
        if (builder.pixelCount > 0) {
            this.pixelCount = builder.pixelCount;
        }
        if (builder.roughColorDistribution != null) {
            this.roughColorDistribution = builder.roughColorDistribution;
        }
        if (builder.exactColorDistribution != null) {
            this.exactColorDistribution = builder.exactColorDistribution;
        }
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

    @Override
    public String toString() {
        return "    ImageMetadata {\n" +
                "       Resolution:" + this.resolution.toString() + "\n" +
                "       Pixel count:" + this.pixelCount + "\n" +
                "       Rough colormap size: " + this.roughColorDistribution.size() + "\n" +
                "       Exact colormap size: " + this.exactColorDistribution.size() + "\n" +
                "   }\n";
    }
}
