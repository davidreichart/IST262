package model.data.filetypes;

import java.io.InputStream;

/**
 * FileSystemResources are objects that represent an element from the file system of a computer.
 * Examples of FileSystemResources include files and directories.
 */
public interface FileSystemResource {

    /**
     * Provides a string with the absolute path of the file system resource.
     * @return The absolute path of the file system resource.
     */
    public String getAbsolutePath();

    /**
     * Identifies if this file system resource is a directory.
     * @return True if the file system resource is a directory, false otherwise.
     */
    public boolean isDirectory();

    /**
     * Identifies if this file system resource is a system file.
     * @return True if the file system resource is a system file, false otherwise.
     */
    public boolean isSystemFile();
}
