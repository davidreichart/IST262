package model.data.metadata;

/**
 * The FileMetadata class is a representation of contextualizing data points for any valid file on a user's computer.
 */
public class FileMetadata {
    private String absolutePath;
    private String contentType;
    private long byteCount;
    private String fileName;
    private String fileExtension;

    /**
     * Constructs a new FileMetadata object when all possible attributes are already known.
     * The FileMetadata class is a representation of contextualizing data points for any valid file on a user's computer. <br>
     * The FileMetadata.builder() method should be used in cases where some fields are unknown.
     * @param absolutePath The absolute file path leading to this file on the user's system.
     * @param contentType The content type/extension string pair describing the kind of file this is.
     * @param byteCount The size of this file on disk in bytes.
     * @param fileName The name of this file, including the dot extension, excluding the file path.
     * @param fileExtension The dot extension for this file.
     */
    public FileMetadata(String absolutePath, String contentType, long byteCount, String fileName, String fileExtension) {
        this.absolutePath = absolutePath;
        this.contentType = contentType;
        this.byteCount = byteCount;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    /**
     * Constructor used by the FileMetadata builder.
     * Initializes any omitted fields during the build process to be null.
     * @param builder The builder instance used to create a new FileMetadata object.
     */
    private FileMetadata(Builder builder) {
        if (builder.absolutePath != null) {
            this.absolutePath = builder.absolutePath;
        }
        if (builder.contentType != null) {
            this.contentType = builder.contentType;
        }
        if (builder.byteCount > -1) {
            this.byteCount = builder.byteCount;
        }
        if (builder.fileName != null) {
            this.fileName = builder.fileName;
        }
        if (builder.fileExtension != null) {
            this.fileExtension = builder.fileExtension;
        }
    }

    /**
     * Initializes the build process for creating a new FileMetadata object.
     * Allows for the creation of FileMetadata objects using order agnostic method-chaining.
     * There are zero required variables. Default null values are inserted in place of missing data.
     * @return An instance of the FileMetadata builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing a FileMetadata object.
     * Allows for the creation of FileMetadata objects using order agnostic method-chaining.
     * There are zero required variables. Default null values are inserted in place of missing data.
     */
    public static class Builder {
        private String absolutePath;
        private String contentType;
        private long byteCount;
        private String fileName;
        private String fileExtension;

        /**
         * Sets the absolute path leading to this file.
         * @param absolutePath A string holding the absolute path to a file on the user's system.
         * @return The current builder instance.
         */
        public Builder absolutePath(String absolutePath) {
            this.absolutePath = absolutePath;
            return this;
        }

        /**
         * Sets the content type describing this file.
         * Must be equivalent to what Files.probeContentType produces. For example, "image/png" for a .PNG file.
         * @param contentType A string holding the content type of this file.
         * @return The current builder instance.
         */
        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        /**
         * Sets the byte count representing this file's size on the disk.
         * @param byteCount The number of bytes making up this file.
         * @return The current builder instance.
         */
        public Builder byteCount(long byteCount) {
            this.byteCount = byteCount;
            return this;
        }

        /**
         * Sets the name of this file.
         * This does not include path information.
         * This does include the extension type (i.e., .png, .txt).
         * @param fileName The string holding the name of this file.
         * @return The current builder instance.
         */
        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        /**
         * Sets the dot file extension correspondent to this file.
         * For example, a .png image would be given "png".
         * @param fileExtension The string holding this file's extension name as it appears in a file browser.
         * @return The current builder instance.
         */
        public Builder fileExtension(String fileExtension) {
            this.fileExtension = fileExtension;
            return this;
        }

        /**
         * Completes the FileMetadata build process.
         * Any skipped attributes will be initialized as null.
         * @return A FileMetadata object as defined by all previously specified attributes.
         */
        public FileMetadata build() {
            return new FileMetadata(this);
        }
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getAbsolutePath() {
        return this.absolutePath;
    }

    public String getContentType() {
        return this.contentType;
    }

    public long getByteCount() {
        return this.byteCount;
    }

    public String getFileExtension() {
        return this.fileExtension;
    }

    /**
     * Presents all currently stored attributes that contextualize the corresponding file for this FileMetadata object.
     * @return A string listing all attributes stored on this FileMetadata object.
     */
    @Override
    public String toString() {
        return "    FileMetadata {\n" +
                "       Absolute path: " + this.absolutePath + "\n" +
                "       Content type: " + this.contentType + "\n" +
                "       File size (bytes): " + this.byteCount + "\n" +
                "       File name: " + this.fileName + "\n" +
                "       File extension: " + this.fileExtension + "\n" +
                "   }\n";
    }
}
