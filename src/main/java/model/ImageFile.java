package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The ImageFile class represents an image / picture file on the user's computer. ImageFiles have ImageStatistics which
 * represent data points (like height, width, pixel count) of the file itself. Tags serve as custom attributes intended
 * to aid in searching/sorting/filtering operations on all images stored by the application.
 */
public class ImageFile {
    private File file;
    private SortedSet<FileTag> fileTags; // we use a set to prevent duplicates
    private ImageStatistics statistics;

    /**
     * The ImageFile class represents a picture file, agnostic to the file's extension type. (i.e. png, jpg, etc.).
     * Statistical information on the provided file is automatically generated at the time of instantiation. <br>
     * If the provided file path String is found to be invalid, the caller will be prompted to enter in a new file
     * path until a valid path is provided.
     * @param filePath A String representing the location on the user's system where the picture/image is located.
     */
    public ImageFile(String filePath) {
        // The caller needs to provide a file path that leads to an image file
        while (!isValidImagePath(filePath)) {
            System.out.print("Enter a file path: ");
        }
        this.file = new File(filePath);
        this.fileTags = new TreeSet<FileTag>();
        this.statistics = new ImageStatistics();
    }

    /**
     * Validates a provided file path in the form of a String. <br>
     * For a given file path to be valid, it must both lead to a file on the user's system AND that file must be
     * an image file (i.e. png, jpg).
     * @param filePath The String representation of a file path.
     * @return
     * True: for valid image file path Strings. <br>
     * False: for invalid image file path Strings.
     */
    private boolean isValidImagePath(String filePath) {
        // ensure the given path points to a file on the system
        try {
            Paths.get(filePath);
        }
        catch (InvalidPathException invalidPathException) {
            System.out.println("The provided file path does not lead to a valid file on the system.");
            return false;
        }

        // ensure the file pointed to is an image
        try {
            ImageIO.read(new File(filePath));
        }
        catch (IOException ioException) {
            System.out.println("The provided file is not an image file.");
            return false;
        }

        return true;
    }

    public void addNewTag(String tagName, Color tagColor) {
        //todo: a hashSet & properly made hashCode could allow for quick lookup of if a tag with tagName is in the set
        this.fileTags.add(new FileTag(tagName, tagColor));
    }

    /**
     * Returns a string representation of this image file's data known to the application. This information includes
     * all data stored in this images {@code ImageStatistics} (statistics) object.
     * @return A string representation of all known attributes of this image file.
     */
    @Override
    public String toString() {
        StringBuilder object = new StringBuilder();
        object.append("Image File: ").append(this.file.getName()).append("\n")
                .append("File Path: ").append(this.file.getPath()).append("\n")
                .append(this.statistics.toString());
        return object.toString();
    }

    /**********
     * Class accessors and mutators
     * currently, all class variables have get & set methods.
     ***********/

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public SortedSet<FileTag> getFileTags() {
        return fileTags;
    }

    public void setFileTags(SortedSet<FileTag> fileTags) {
        this.fileTags = fileTags;
    }

    public ImageStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(ImageStatistics statistics) {
        this.statistics = statistics;
    }
}