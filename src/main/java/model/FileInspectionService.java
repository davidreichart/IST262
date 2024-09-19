package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * The FileInspectionService class contains methods used to analyze and contextualize File objects.
 * This is primarily meant to be used as a helper class.
 * This stores the File object passed into the FileInspectionService at instantiation.
 * To work with a different file object, the {@code setFileToInspect} method updates the currently stored file or a new
 * FileInspectionService must be created.
 */
public class FileInspectionService {

    private File fileToInspect;

    /**
     * Creates a new FileInspectionService that can analyze/contextualize the provided File object.
     * {@code setFileToInspect} must be used to work with a new File.
     * @param fileToInspect The File object to have the FileInspectionService work with.
     */
    public FileInspectionService(File fileToInspect) {
        this.fileToInspect = fileToInspect;
    }

    /**
     * Returns a string of the absolute path leading to the file currently held by this instance of the FileInspectionService.
     * This can be used if it is unclear what file is currently in the service queue.
     * @return a string of the absolute path leading to the file currently held by this instance of the service.
     */
    @Override
    public String toString() {
        return "Currently held file: " + this.fileToInspect.getAbsolutePath();
    }

    /**
     * Returns a String of the MIME type for the file currently held by this FileInspectionService.
     * For example, when given a .png file, this method provides an "image/jpeg" string.
     * @return a String representing the dot extension for the file currently held by this FileInspectionService.
     */
    public String getFileType() {
        String fileType = "";
        try {
            fileType = Files.probeContentType(this.fileToInspect.toPath());
        } catch (IOException ioException) {
            System.out.println(ioException);
        }

        return fileType;
    }

    /**
     * Returns a String representing the dot extension for the file currently held by this FileInspectionService.
     * For example, when given a .png file, this method provides a "png" string.
     * @return a String representing the dot extension for the file currently held by this FileInspectionService.
     */
    public String getFileExtension() {
        String fileName = this.fileToInspect.getName();
        int extensionDotIndex = fileName.lastIndexOf(".");
        return fileName.substring(extensionDotIndex + 1);
    }

    /**
     * Returns the File object that this FileInspectionService is currently holding to be analyzed.
     * @return The File object currently relevant to this FileInspectionService.
     */
    public File getFileToInspect() {
        return fileToInspect;
    }

    /**
     * Replaces the existing File object being analyzed by this FileInspectionService.
     * @param fileToInspect The new File object to be analzyed by this FileInspectionService.
     */
    public void setFileToInspect(File fileToInspect) {
        this.fileToInspect = fileToInspect;
    }
}
