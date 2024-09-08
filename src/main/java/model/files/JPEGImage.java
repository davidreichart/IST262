package model.data;

import java.io.File;

public class JPEGImage implements IImageFile {

    private File file;

    public JPEGImage(File file) {
        if (!validateFileType(file)) {
            throw new IllegalArgumentException("The file is not a jpeg file.");
        } else {
            this.file = file;
        }
    }

    @Override
    public boolean validateFileType(File file) {
        String fileName = file.getName();
        int extensionDotIndex = fileName.lastIndexOf(".");
        return fileName.substring(extensionDotIndex).equals("jpeg");
    }

    public File getFile() {
        return file;
    }
}
