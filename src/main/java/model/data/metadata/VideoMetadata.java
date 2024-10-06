package model.data.metadata;

import java.awt.*;

/**
 * The VideoMetadata object is a collection of data points/statistics to be associated with a video file
 * on the user's system.
 */
public class VideoMetadata extends Metadata {

    private int frameCount;
    private int durationInSeconds;
    private Dimension resolution;

    /**
     * Constructs a new VideoMetadata object when only the absolute file path is known.
     * The VideoMetadata class is a representation of contextualizing data points for any valid video file on
     * a user's computer.
     * The VideoMetadata.builder() method should be used in cases where some fields are unknown.
     * This constructor sets all metadata fields to default zero/zero equivalent values.
     * @param absoluteFilePath The absolute file path leading to this video on the user's system.
     */
    public VideoMetadata(String absoluteFilePath) {
        super(absoluteFilePath);
        this.frameCount = 0;
        this.durationInSeconds = 0;
        this.resolution = new Dimension(0, 0);
    }

    /**
     * Constructs a new VideoMetadata object when all possible attributes are already known.
     * The VideoMetadata class is a representation of contextualizing data points for any valid video file on
     * a user's computer.
     * @param absoluteFilePath The absolute file path leading to this video on the user's system.
     * @param frameCount The number of frames in this video.
     * @param durationInSeconds The duration of this video in seconds.
     * @param resolution The height x width of this video as a Dimension.
     */
    public VideoMetadata(String absoluteFilePath, int frameCount, int durationInSeconds, Dimension resolution) {
        super(absoluteFilePath);
        this.frameCount = frameCount;
        this.durationInSeconds = durationInSeconds;
        this.resolution = resolution;
    }

    private VideoMetadata(Builder builder) {
        super(builder.absoluteFilePath); // calling the parent constructor to apply core file metadata

        // applying the frame count
        if (builder.frameCount == 0) {
            this.frameCount = 0;
        } else {
            this.frameCount = builder.frameCount;
        }

        // applying the duration
        if (builder.durationInSeconds == 0) {
            this.durationInSeconds = 0;
        } else {
            this.durationInSeconds = builder.durationInSeconds;
        }

        // applying the resolution
        if (builder.resolution == null) {
            this.resolution = new Dimension(0, 0);
        } else {
            this.resolution = builder.resolution;
        }
    }

    /**
     * Builder class for constructing new VideoMetadata objects.
     * Allows for the creation of VideoMetadata objects using order agnostic method-chaining.
     * Only a file path is required to create a new VideoMetadata object.
     */
    public static class Builder {
        private String absoluteFilePath;
        private int frameCount;
        private int durationInSeconds;
        private Dimension resolution;

        /**
         * Constructs a new Builder object for creating a new VideoMetadata object.
         * An absolute file path is required to create a new VideoMetadata object's underlying Metadata.
         *
         * @param absoluteFilePath The absolute file path leading to this video on the user's system.
         */
        public Builder(String absoluteFilePath) {
            this.absoluteFilePath = absoluteFilePath;
        }

        /**
         * Sets the number of frames in this video.
         *
         * @param frameCount The number of frames in this video.
         * @return This Builder instance.
         */
        public Builder frameCount(int frameCount) {
            this.frameCount = frameCount;
            return this;
        }

        /**
         * Sets the duration of this video in seconds.
         *
         * @param durationInSeconds The duration of this video in seconds.
         * @return This Builder instance.
         */
        public Builder durationInSeconds(int durationInSeconds) {
            this.durationInSeconds = durationInSeconds;
            return this;
        }

        /**
         * Sets the height x width of this video as a Dimension.
         *
         * @param resolution The height x width of this video as a Dimension.
         * @return This Builder instance.
         */
        public Builder resolution(Dimension resolution) {
            this.resolution = resolution;
            return this;
        }

        /**
         * Completes the VideoMetadata build process.
         * Any skipped attributes will be set to default zero/zero equivalent values.
         *
         * @return A new VideoMetadata object with the provided attributes.
         */
        public VideoMetadata build() {
            return new VideoMetadata(this);
        }
    }

    /**
     * Returns the total number of frames in this video.
     * @return The total number of frames in this video.
     */
    public int getFrameCount() {
        return frameCount;
    }

    /**
     * Returns the duration of this video in seconds.
     * @return The duration of this video in seconds.
     */
    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    /**
     * Returns the duration of this video in minutes.
     * Seconds are truncated.
     * @return The duration of this video in minutes.
     */
    public int getDurationInMinutes() {
        return durationInSeconds / 60;
    }

    /**
     * Returns the duration of this video in hours.
     * Seconds and minutes are truncated.
     * @return The duration of this video in hours.
     */
    public int getDurationInHours() {
        return durationInSeconds / 3600;
    }

    /**
     * Returns the height x width of this video as a Dimension.
     * @return The height x width of this video as a Dimension.
     */
    public Dimension getResolution() {
        return resolution;
    }
}
