package model.data;

import model.util.FileInspector;
import model.util.ImageInspector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;

public class UserFile {
    private File file;
    private FileMetadata fileMetadata;
    private ImageMetadata imageMetadata;
    private LinkedHashSet<FileTag> fileTags;

    private UserFile(Builder builder) {
        this.file = builder.file;
        this.fileMetadata = builder.fileMetadata;
        this.imageMetadata = builder.imageMetadata;
        this.fileTags = builder.fileTags;
    }

    public static Builder builder(File file) {
        return new Builder(file);
    }

    public static class Builder {
        private File file;
        private FileMetadata fileMetadata;
        private ImageMetadata imageMetadata;
        private LinkedHashSet<FileTag> fileTags;

        public Builder(File file) {
            // required fields
            this.file = file;
        }

        public Builder fileMetadata(FileMetadata fileMetadata) {
            this.fileMetadata = fileMetadata;
            return this;
        }

        public Builder imageMetadata(ImageMetadata imageMetadata) {
            this.imageMetadata = imageMetadata;
            return this;
        }

        public Builder fileTags(LinkedHashSet<FileTag> fileTags) {
            this.fileTags = fileTags;
            return this;
        }

        public UserFile build() {
            return new UserFile(this);
        }
    }

    public void generateAllImageMetadata() {
        BufferedImage image;
        try {
            image = ImageIO.read(this.file);
        } catch (IOException ioException) {
            System.out.println("There was an issue when parsing a BufferedImage from this UserFile's file object in: generateAllImageMetadata()");
            return;
        }
        this.imageMetadata = ImageMetadata.builder()
                .resolution(ImageInspector.getResolution(image))
                .pixelCount(ImageInspector.getPixelCount(image))
                .roughColorDistribution(ImageInspector.getRoughColorDistribution(image))
                .exactColorDistribution(ImageInspector.getExactColorDistribution(image))
                .build();
    }

    public void generateAllFileMetadata() {
        this.fileMetadata = FileMetadata.builder()
                .absolutePath(this.file.getAbsolutePath())
                .contentType(FileInspector.getFileContentType(this.file))
                .byteCount(FileInspector.getFileSizeInBytes(this.file))
                .fileName(this.file.getName())
                .fileExtension(FileInspector.getFileExtension(this.file))
                .build();
    }

    public FileMetadata getFileMetadata() throws UnsupportedOperationException {
        if (this.fileMetadata == null) {
            throw new UnsupportedOperationException("This file's metadata is currently null. Run \"generateAllFileMetadata\" to create this object.");
        }
        return this.fileMetadata;
    }

    public ImageMetadata getImageMetadata() throws UnsupportedOperationException {
        if (this.imageMetadata == null) {
            throw new UnsupportedOperationException("This file's image-metadata is currently null. Run \"generateAllImageMetadata\" to create this object.");
        }
        return this.imageMetadata;
    }

    public LinkedHashSet<FileTag> getFileTags() throws UnsupportedOperationException {
        if (this.fileTags.isEmpty()) {
            throw new UnsupportedOperationException("There are no tags currently associated with this file.");
        }
        return this.fileTags;
    }
}
