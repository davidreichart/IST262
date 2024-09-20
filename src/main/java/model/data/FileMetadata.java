package model.data;

public class FileMetadata {
    private String absolutePath;
    private String contentType;
    private long byteCount;
    private String fileName;
    private String fileExtension;

    public FileMetadata(String absolutePath, String contentType, long byteCount, String fileName, String fileExtension) {
        this.absolutePath = absolutePath;
        this.contentType = contentType;
        this.byteCount = byteCount;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String absolutePath;
        private String contentType;
        private long byteCount;
        private String fileName;
        private String fileExtension;

        public Builder absolutePath(String absolutePath) {
            this.absolutePath = absolutePath;
            return this;
        }

        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder byteCount(long byteCount) {
            this.byteCount = byteCount;
            return this;
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder fileExtension(String fileExtension) {
            this.fileExtension = fileExtension;
            return this;
        }

        public FileMetadata build() {
            return new FileMetadata(this);
        }
    }

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
