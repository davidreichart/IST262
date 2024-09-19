package model.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class FileInspector {

    private FileInspector() {}

    public static boolean isImageFile(File file) {
        String fileContentType = getFileContentType(file);
        String[] imageContentTypes = { "image/png", "image/jpeg" };
        return Arrays.asList(imageContentTypes).contains(fileContentType);
    }

    public static String getFileContentType(File file) {
        String fileContentType = "";
        try {
            fileContentType = Files.probeContentType(file.toPath());
        } catch (IOException ioException) {
            if (!Files.exists(file.toPath())) {
                System.out.println("The provided path does not lead to a file.");
                System.out.println("Path given: " + file.toString());
            } else {
                System.out.println(ioException.getMessage());
            }
        }
        return fileContentType;
    }

    public static long getFileSizeInBytes(File file) {
        long byteCount = 0;
        try {
            byteCount = Files.size(file.toPath());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return byteCount;
    }

    public static String getFileExtension(File file) {
        String contentType = getFileContentType(file);
        return contentType.substring(contentType.lastIndexOf("/") + 1);
    }
}
