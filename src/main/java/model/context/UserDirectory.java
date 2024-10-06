package model.context;

import model.data.UserFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * The UserDirectory class is a representation of a directory on the user's system which UserFiles can be created from.
 */
public class UserDirectory {

    private File directoryPath;
    private int directoryFileCount;
    private HashSet<UserFile> directoryFiles;

    /**
     * Constructs a new UserDirectory object when all possible attributes are already known.
     * The UserDirectory class is a representation of a directory on the user's system which UserFiles can be created from.
     * @param directoryPath The path leading to this UserDirectory.
     * @param directoryFileCount The number of files found in this UserDirectory.
     */
    public UserDirectory(File directoryPath, int directoryFileCount, HashSet<UserFile> directoryFiles) {
        try {
            isValidDirectory(directoryPath);
            this.directoryPath = directoryPath;
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            this.directoryPath = null;
        }
        this.directoryFileCount = directoryFileCount;
        this.directoryFiles = directoryFiles;
    }

    /**
     * Constructs a new UserDirectory object when only the directory path is known.
     * All other parameters will be automatically generated at construction.
     * @param directoryPath The path leading to this UserDirectory.
     */
    public UserDirectory(File directoryPath) {
        this.directoryFiles = new HashSet<>();
        try {
            isValidDirectory(directoryPath);
            this.directoryPath = directoryPath;
            findAllFilesInDirectory();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            this.directoryPath = null;
        }
        this.directoryFileCount = countFilesInDirectory();
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
        return 0;
    }

    public void findAllFilesInDirectory() {
        for (File file : Objects.requireNonNull(this.directoryPath.listFiles())) {
            if (file.isFile()) {
                this.directoryFiles.add(UserFile.builder(file).build());
            }
        }
    }

    /**
     * Compares two UserDirectory objects for equality based on their directory path names.
     * @param obj The UserDirectory object to compare against.
     * @return True if the directory paths are the same. <br>
     *    False if the directory paths are different or the input object is not a UserDirectory.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserDirectory) {
            UserDirectory other = (UserDirectory) obj;
            return this.getDirectoryPath().getName().equals(other.getDirectoryPath().getName());
        } else {
            return false;
        }
    }

    public void addFileToDirectory(UserFile file) {
        this.directoryFiles.add(file);
    }

    /**
     * Returns the file object containing the directory path for this UserDirectory.
     * @return The file object containing the directory path for this UserDirectory.
     */
    public File getDirectoryPath() {
        return directoryPath;
    }

    public String getName() {
        return directoryPath.getName();
    }

    public HashSet<UserFile> getDirectoryFiles() {
        return directoryFiles;
    }
}
