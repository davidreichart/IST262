package model.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * The FileInspector class provides utility methods for extracting data about any file on a user's system.
 * Primarily, this class is used to build a metadata profile of any valid file.
 * This class expects to receive File objects to perform its duties.
 */
public final class FileInspector {

    private FileInspector() {}

    /**
     * Analyzes a file's content type to assess if it is an image file (i.e., png, jpeg).
     * @param file The file to analyze.
     * @return True if the provided file is an image file. <br>
     * False if the provided file is invalid OR is not an image type file.
     */
    public static boolean isImageFile(File file) {
        if (!file.exists()) {
            return false;
        }

        String fileContentType = getFileContentType(file);
        String[] imageContentTypes = { "image/png", "image/jpeg" };
        return Arrays.asList(imageContentTypes).contains(fileContentType);
    }

    /**
     * Checks the given string to see if it is a valid file path.
     * @param filePath The file path to check.
     * @return True if the provided string is a valid file path. <br>
     * False if the provided string is not a valid file path.
     */
    public static boolean isFile(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * Analyzes a file to assess its content type. For example, a PNG file would return "image/png".
     * A TXT file would return "plain/text". If the file provided is invalid and does not lead to a file on the user's
     * system, an empty string will be returned.
     * @param file The file to analyze.
     * @return A string describing the type of file provided.
     */
    public static String getFileContentType(File file) {
        if (!file.exists()) {
            return "";
        }

        String fileContentType = "";
        try {
            fileContentType = Files.probeContentType(file.toPath());
        } catch (IOException ioException) {
            if (!Files.exists(file.toPath())) {
                System.out.println("The provided path does not lead to a file.");
                System.out.println("Path given: " + file);
            } else {
                System.out.println(ioException.getMessage());
            }
        }
        return fileContentType;
    }

    /**
     * Calculates the provided file's size on disk, measured in bytes.
     * @param file The file to analyze.
     * @return The number of bytes that make up the given file.
     */
    public static long getFileSizeInBytes(File file) {
        if (!file.exists()) {
            return -1;
        }

        long byteCount = 0;
        try {
            byteCount = Files.size(file.toPath());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return byteCount;
    }

    /**
     * Extracts the dot-extension from the provided file. For example, if given a .PNG file, this method would return "png".
     * If the file is invalid or does not lead to a file on the user's system, an empty string is returned instead.
     * @param file The file to analyze.
     * @return A string representing the dot extension of provided file.
     */
    public static String getFileExtension(File file) {
        if (!file.exists()) {
            return "";
        }

        String contentType = file.getName();
        int lastDotIndex = contentType.lastIndexOf('.');
        return contentType.substring(lastDotIndex + 1);
    }
}
