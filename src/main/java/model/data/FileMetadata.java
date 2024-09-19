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
        this.absolutePath = builder.absolutePath;
        this.contentType = builder.contentType;
        this.byteCount = builder.byteCount;
        this.fileName = builder.fileName;
        this.fileExtension = builder.fileExtension;
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
}
