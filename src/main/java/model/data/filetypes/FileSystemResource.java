package model.data.filetypes;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.InvalidPathException;

/**
 * FileSystemResources are objects that represent an element from the file system of a computer.
 * Examples of FileSystemResources include files and directories.
 */
public interface FileSystemResource {

    /**
     * Opens an input stream which can be used to read the data associated with this file system resource.
     * Example uses are for reading characters in a text file, or for reading pixels in an image file.
     * @return An InputStream for the contents contained within the input {@code file};
     * @throws FileNotFoundException If this resource does not exist.
     */
    public InputStream open() throws FileNotFoundException;

    /**
     * Renames this file system resource.
     * The previous name for the given file/directory will be lost.
     * @param name The new name for this file system resource.
     * @throws UnsupportedOperationException If the resource cannot be renamed.
     */
    public void rename(String name) throws UnsupportedOperationException;

    /**
     * Moves this file system resource to the input file path.
     * @param path The path to the directory to move this system resource to.
     * @throws InvalidPathException If the input file path does not exist or
     * this file system resource is already in the input directory.
     */
    public void moveTo(String path) throws InvalidPathException;

    /**
     * Returns a string detailing the type of system resource this is.
     * Possible values include: <br>
     * "Directory" for directories, <br>
     * "Image" for image files (e.g. .png, .jpg), <br>
     * "Text" for text files (e.g. .txt), <br>
     * @return A string detailing the type of system resource this is.
     */
    public String getContentType();
}
