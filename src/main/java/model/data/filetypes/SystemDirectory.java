package model.data.filetypes;

import model.util.FileInspector;

import java.io.File;
import java.nio.file.*;
import java.util.*;

/**
 * The SystemDirectory class is a representation of a directory on the user's system.
 * SystemDirectories contain HashSets of SystemFiles found in the directory.
 */
public class SystemDirectory {

    private final String directoryPath;
    private HashSet<ImageFile> directoryImageFiles;

    public SystemDirectory(String directoryPath) throws IllegalArgumentException {
        if (!Files.isDirectory(Path.of(directoryPath))) {
            throw new IllegalArgumentException("Directory path must lead to a valid directory.");
        }

        this.directoryPath = directoryPath;
        this.directoryImageFiles = new HashSet<>();
        scanDirectoryForImages();
    }

    /**
     * Scans the directory for image files and adds them to the directoryImageFiles HashSet.
     * @throws IllegalArgumentException If the file is not an image file.
     */
    public void scanDirectoryForImages() throws IllegalArgumentException {
        for (File file : Objects.requireNonNull(new File(directoryPath).listFiles())) {
            if (FileInspector.isImageFile(file)) {
                directoryImageFiles.add(new ImageFile(file.getAbsolutePath()));
            } else {
                continue;
            }
        }
    }

    /**
     * Returns the path to the directory.
     * @return The path to the directory.
     */
    public String directoryPath() {
        return directoryPath;
    }

    /**
     * Returns a HashSet of ImageFile objects found in the directory.
     * @return A HashSet of ImageFile objects found in the directory.
     */
    public HashSet<ImageFile> directoryImageFiles() {
        return directoryImageFiles;
    }
}
