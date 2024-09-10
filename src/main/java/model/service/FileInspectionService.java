package model.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileInspectionService {
    private File fileToInspect;

    public FileInspectionService(File fileToInspect) {
        this.fileToInspect = fileToInspect;
    }

    public String getFileType(File fileToInspect) {
        String fileType = "";
        try {
            fileType = Files.probeContentType(fileToInspect.toPath());
        } catch (IOException ioException) {
            System.out.println(ioException);
        }

        return fileType;
    }

    public String getFileExtension(File fileToInspect) {
        String fileName = fileToInspect.getName();
        int extensionDotIndex = fileName.lastIndexOf(".");
        return fileName.substring(extensionDotIndex + 1);
    }
}
