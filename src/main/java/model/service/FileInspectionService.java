package model.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileInspectionService {

    public File getFileToInspect() {
        return fileToInspect;
    }

    public void setFileToInspect(File fileToInspect) {
        this.fileToInspect = fileToInspect;
    }

    private File fileToInspect;

    public FileInspectionService(File fileToInspect) {
        this.fileToInspect = fileToInspect;
    }

    public String getFileType() {
        String fileType = "";
        try {
            fileType = Files.probeContentType(this.fileToInspect.toPath());
        } catch (IOException ioException) {
            System.out.println(ioException);
        }

        return fileType;
    }

    public String getFileExtension() {
        String fileName = this.fileToInspect.getName();
        int extensionDotIndex = fileName.lastIndexOf(".");
        return fileName.substring(extensionDotIndex + 1);
    }
}
