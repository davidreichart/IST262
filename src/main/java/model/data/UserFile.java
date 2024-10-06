package model.data;

import model.data.metadata.ImageMetadata;
import model.util.FileInspector;
import model.util.ImageInspector;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Objects;

/**
 * The UserFile class represents a file on the user's system.
 */
public class UserFile {
    private File file;
    private ImageMetadata imageMetadata;
    private LinkedHashSet<FileTag> fileTags;

    /**
     * Constructs a new UserFile object when all possible attributes are already known.
     * The UserFile class represents a file on the user's system.
     * @param file The file object leading to a valid file on the user's system.
     * @param imageMetadata A collection of data points/statistics to be associated with an
     *                      image file on the user's system
     * @param fileTags A set of FileTags used in additional categorization of UserFiles.
     */
    public UserFile(File file, ImageMetadata imageMetadata, LinkedHashSet<FileTag> fileTags) {
        this.file = file;
        this.imageMetadata = imageMetadata;
        this.fileTags = fileTags;
    }

    /**
     * Constructor used by the UserFile builder.
     * If File/Image metadata objects were not provided, they will be automatically generated.
     * If no FileTags are provided, an empty set will be created and can be added to later.
     * @param builder The builder instance used to create a new UserFile object.
     */
    private UserFile(Builder builder) {
        this.file = builder.file;
        if (builder.imageMetadata == null) {
            this.imageMetadata = ImageMetadata.builder(this.file.getAbsolutePath()).build();
        } else {
            this.imageMetadata = builder.imageMetadata;
        }
        if (builder.fileTags == null) {
            this.fileTags = new LinkedHashSet<>();
        } else {
            this.fileTags = builder.fileTags;
        }
    }

    /**
     * Initializes the build process for creating a new UserFile object.
     * Allows for the creation of UserFIle objects using order agnostic method-chaining.
     * A file object leading to a valid file on the user's system must be provided.
     * Any ignored parameters (such as metadata) will be automatically generated after the build process.
     * @param file The file to represent with this UserFile object.
     * @return The builder instance used to create a new UserFile object.
     */
    public static Builder builder(File file) {
        return new Builder(file);
    }

    /**
     * Builder class for constructing a UserFile object.
     * Allows for the creation of UserFIle objects using order agnostic method-chaining.
     * A file object leading to a valid file on the user's system must be provided.
     * Any ignored parameters (such as metadata) will be automatically generated after the build process.
     */
    public static class Builder {
        private File file;
        private ImageMetadata imageMetadata;
        private LinkedHashSet<FileTag> fileTags;

        /**
         * Initializes the build process for creating a new UserFile.
         * A file object leading to the file to represent is required.
         * @param file The file object leading to a valid file on the user's system.
         */
        public Builder(File file) {
            // required fields
            this.file = file;
        }

        /**
         * Sets the ImageMetadata object associated to this UserFile. The File must be an image.
         * The ImageMetadata object is a collection of data points/statistics to be associated with an image file
         * on the user's system.
         * If skipped during the build process, a new ImageMetadata object will be generated automatically.
         * @param imageMetadata The ImageMetadata object contextualizing this image file.
         * @return An instance of the UserFile builder.
         */
        public Builder imageMetadata(ImageMetadata imageMetadata) {
            this.imageMetadata = imageMetadata;
            return this;
        }

        /**
         * Sets the set of FileTags used in helping to organize/categorize UserFiles.
         * If skipped during the build process, an empty set will be created and can be added to later.
         * @param fileTags A Set of FileTags to associate to this UserFile.
         * @return An instance of the UserFile builder.
         */
        public Builder fileTags(LinkedHashSet<FileTag> fileTags) {
            this.fileTags = fileTags;
            return this;
        }

        /**
         * Completes the UserFile build process.
         * Any skipped attributes will be automatically generated (not null).
         * @return A UserFile object as defined by all previously specified attributes.
         */
        public UserFile build() {
            return new UserFile(this);
        }
    }

    /**
     * Calls on methods provided by the ImageInspector class to generate a fully populated ImageMeta object.
     * The ImageMetadata object is a collection of data points/statistics to be associated with an image file
     * on the user's system.
     */
    public void generateAllImageMetadata() {
        BufferedImage image;
        try {
            image = ImageIO.read(this.file);
        } catch (IOException ioException) {
            System.out.println("There was an issue when parsing a BufferedImage from this UserFile's file object in: generateAllImageMetadata()");
            return;
        }
        this.imageMetadata = ImageMetadata.builder(this.imageMetadata.getAbsoluteFilePath())
                .resolution(ImageInspector.getResolution(image))
                .pixelCount(ImageInspector.getPixelCount(image))
                .roughColorDistribution(ImageInspector.getRoughColorDistribution(image))
                .exactColorDistribution(ImageInspector.getExactColorDistribution(image))
                .build();
    }

    /**
     * Adds a new FileTag to this UserFile.
     * If the tag provided is already attached to this UserFile, an exception is thrown.
     * @param fileTag The FileTag to attach to this UserFile.
     * @throws UnsupportedOperationException If the FileTag passed to this method is already applied to this UserFile.
     */
    public void addFileTag(FileTag fileTag) throws UnsupportedOperationException {
        if (this.fileTags.contains(fileTag)) {
            throw new UnsupportedOperationException("This file already has a tag by that name applied.");
        } else {
            this.fileTags.add(fileTag);
        }
    }

    /**
     * Searches through the file tags associated to this UserFile and removes the tag if one is found.
     * @param tagToRemove The name of the tag to try and remove.
     */
    public void removeFileTag(String tagToRemove) {
        for (FileTag tag : this.fileTags) {
            if (tag.getName().equals(tagToRemove)) {
                this.fileTags.remove(tag);
                break;
            }
        }
    }

    /**
     * @return The ImageMetadata object attached to this UserFile.
     * @throws UnsupportedOperationException If the ImageMetadata object for this UserFile is currently null.
     */
    public ImageMetadata getImageMetadata() throws UnsupportedOperationException {
        if (Objects.equals(this.imageMetadata.getResolution(), new Dimension(0, 0))) {
            throw new UnsupportedOperationException("This file's image-metadata is currently null. Run \"generateAllImageMetadata\" to create this object.");
        }
        return this.imageMetadata;
    }

    /**
     * @return The set containing all FileTags attached to this UserFile.
     * @throws UnsupportedOperationException If there are no FileTags currently associated to this UserFile.
     */
    public LinkedHashSet<FileTag> getFileTags() throws UnsupportedOperationException {
        if (this.fileTags.isEmpty()) {
            throw new UnsupportedOperationException("There are no tags currently associated with this file.");
        }
        return this.fileTags;
    }

    /**
     * @return The file object leading to the file on the user's system.
    */
    public File getFile() {
        return file;
    }
}
