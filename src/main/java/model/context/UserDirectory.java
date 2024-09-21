package model.context;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * The UserDirectory class is a representation of a directory on the user's system which UserFiles can be created from.
 */
public class UserDirectory {

    private File directoryPath;
    private int directoryFileCount;

    /**
     * Constructs a new UserDirectory object when all possible attributes are already known.
     * The UserDirectory class is a representation of a directory on the user's system which UserFiles can be created from.
     * @param directoryPath The path leading to this UserDirectory.
     * @param directoryFileCount The number of files found in this UserDirectory.
     */
    public UserDirectory(File directoryPath, int directoryFileCount) {
        try {
            isValidDirectory(directoryPath);
            this.directoryPath = directoryPath;
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            this.directoryPath = null;
        }
        this.directoryFileCount = directoryFileCount;
    }

    /**
     * Assesses if the provided file leads to a valid directory on the user's system.
     * @param directoryPath A file holding the directory path to check.
     * @return True if the provided file leads to a valid directory.
     * @throws IllegalArgumentException If the file does NOT lead to a valid directory.
     */
    public boolean isValidDirectory(File directoryPath) throws IllegalArgumentException {
        if (directoryPath.exists()) {
            return true;
        } else {
            throw new IllegalArgumentException("The provided string does not lead to a valid directory.");
        }
    }

    /**
     * Counts the number of files in this UserDirectory.
     * @return the total number of files in this UserDirectory at the time of scanning.
     */
    public int countFilesInDirectory() {
        try {
            Stream<Path> files = Files.list(this.directoryPath.toPath());
            return (int) files.count();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
