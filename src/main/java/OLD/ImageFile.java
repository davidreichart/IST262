package OLD;

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
    private SortedSet<FileTag_Old> fileTagOlds; // we use a set to prevent duplicates
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
        this.fileTagOlds = new TreeSet<FileTag_Old>();
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
        this.fileTagOlds.add(new FileTag_Old(tagName, tagColor));
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

    /**
     * Returns the file object associated with this ImageFile. The file object is primarily used when needing to work
     * with / know the file path leading to this ImageFile on the user's system.
     * @return This ImageFile's associated File object.
     */
    public File getFile() {
        return file;
    }

    /**
     * Replaces the file object associated with this ImageFile. The file object is primarily used when needing to work
     * with / know the file path leading to this ImageFile on the user's system.
     * @param file The File object to replace the one associated with this ImageFile.
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Returns the set of all FileTags associated to this ImageFile. FileTags are used to categorize/organize files.
     * @return The set of all FileTags associated to this ImageFile.
     */
    public SortedSet<FileTag_Old> getFileTags() {
        return fileTagOlds;
    }

    /**
     * Replaces the set of FileTags that should be paired to this ImageFile. The FileTags in the given set should
     * all be known by the ApplicationState. //todo: validate that they are known by ApplicationState
     * @param fileTagOlds The set of FileTags to replace the current set associated to this ImageFile.
     */
    public void setFileTags(SortedSet<FileTag_Old> fileTagOlds) {
        this.fileTagOlds = fileTagOlds;
    }

    /**
     * Returns the ImageStatistics object containing statistical data collected about this Image.
     * @return the ImageStatistics object containing statistical data collected about this Image.
     */
    public ImageStatistics getStatistics() {
        return statistics;
    }

    /**
     * Replaces the ImageStatistics object that tracks all calculated statistical data about this Image.
     * This method should only be used if all statistical data needs to be replaced. Individual statistics can be updated
     * by getting the existing ImageStatistics object.
     * @param statistics The new set of statistics to apply to this ImageFile.
     */
    public void setStatistics(ImageStatistics statistics) {
        this.statistics = statistics;
    }
}