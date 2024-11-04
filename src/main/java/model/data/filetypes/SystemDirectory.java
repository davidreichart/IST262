package model.data.filetypes;

import model.util.FileInspector;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * The SystemDirectory class is a representation of a directory on the user's system.
 * SystemDirectories contain HashSets of SystemFiles found in the directory.
 */
public class SystemDirectory implements FileSystemResource, Serializable {

    private String directoryPath;
    private HashSet<ImageFile> directoryImageFiles;

    public SystemDirectory(String directoryPath) throws IllegalArgumentException {
        // unknown is a special case used to hold individually added images
        // it is not an actual directory
        if (directoryPath.equals("unknown")) {
            this.directoryPath = directoryPath;
            this.directoryImageFiles = new HashSet<>();
            return;
        }
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
        for (File file : new File(directoryPath).listFiles()) {
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

    /**
     * Opens an input stream which can be used to read the data associated with this file system resource.
     * Example uses are for reading characters in a text file, or for reading pixels in an image file.
     * @return An InputStream for the contents contained within the input {@code file};
     * @throws FileNotFoundException If the file does not exist.
     */
    @Override
    public InputStream open() throws FileNotFoundException {
        if (!Files.exists(Path.of(directoryPath))) {
            throw new FileNotFoundException("The file does not exist.");
        }

        return new FileInputStream(new File(directoryPath));
    }

    /**
     * Renames this file system resource.
     * The previous name for the given file/directory will be lost.
     *
     * @param name The new name for this file system resource.
     * @throws UnsupportedOperationException If the directory cannot be renamed.
     */
    @Override
    public void rename(String name) throws UnsupportedOperationException {
        File oldDirectory = new File(directoryPath);
        File newDirectory = new File(oldDirectory.getParent() + File.separator + name);

        if (!oldDirectory.renameTo(newDirectory)) {
            throw new UnsupportedOperationException("Failed to rename the directory.");
        }
    }

    /**
     * Moves this file system resource to the input file path.
     *
     * @param path The path to the directory to move this system resource to.
     * @throws InvalidPathException If the input file path does not exist or
     *                              this file system resource is already in the input directory.
     */
    @Override
    public void moveTo(String path) throws InvalidPathException {
        try {
            Files.move(Path.of(directoryPath), Path.of(path + File.separator + new File(directoryPath).getName()));
        } catch (IOException e) {
            throw new InvalidPathException("The input file path does not exist.", path);
        }
    }

    /**
     * Returns a string detailing the type of system resource this is.
     * Possible values include: <br>
     * "Directory" for directories, <br>
     * "Image" for image files (e.g. .png, .jpg), <br>
     * "Text" for text files (e.g. .txt), <br>
     *
     * @return A string detailing the type of system resource this is.
     */
    @Override
    public String getContentType() {
        return "Directory";
    }
}
