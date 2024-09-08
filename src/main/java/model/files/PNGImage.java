package model.data;

import java.io.File;

public class PNGImage implements IImageFile {

    private File file;

    public PNGImage(File file) {
        if (!validateFileType(file)) {
            throw new IllegalArgumentException("This file is not a png file.");
        } else {
            this.file = file;
        }
    }

    @Override
    public boolean validateFileType(File file) {
        String fileName = file.getName();
        int extensionDotIndex = fileName.lastIndexOf(".");
        return fileName.substring(extensionDotIndex).equals("png");
    }

    public File getFile() {
        return file;
    }
}
