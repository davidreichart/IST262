package model.data.filetypes;

import model.data.FileTag;

import java.io.File;
import java.util.Comparator;
import java.util.LinkedHashSet;

/**
 * The SystemFile class is a representation of a file on a user's system.
 * This class is intended to be a parent class for all other file types and cannot be instantiated.
 * A METADATA record contains file statistics and information.
 */
public abstract class SystemFile {
    /**
     * The Metadata record contains file statistics and information.
     * @param byteCount The size of the file in bytes.
     * @param absoluteFilePath The absolute file path leading to this file on the user's system.
     */
    public record Metadata(long byteCount, String absoluteFilePath) {

        public int kilobyteCount() {
            return (int) (byteCount / 1000);
        }

        public int megabyteCount() {
            return (int) (byteCount / 1000000);
        }

        public int gigabyteCount() {
            return (int) (byteCount / 1000000000);
        }
    }

    private final Metadata METADATA;
    private LinkedHashSet<FileTag> fileTags;

    /**
     * Constructs a new SystemFile object.
     * The SystemFile class is a representation of a file on a user's system. <br>
     * This class is intended to be a parent class for all other file types and cannot be instantiated.
     * @param absoluteFilePath The absolute file path leading to this file on the user's system.
     */
    public SystemFile(String absoluteFilePath) {
        if (!new File(absoluteFilePath).exists()) {
            throw new IllegalArgumentException("The provided string does not lead to a file.");
        }

        this.METADATA = new Metadata(new File(absoluteFilePath).length(), absoluteFilePath);
        this.fileTags = new LinkedHashSet<>();
    }

    /**
     * Returns the metadata record associated with this SystemFile.
     * The metadata record's attributes can be accessed using the dot operator on the returned record.
     * @return The metadata record associated with this SystemFile.
     */
    public Metadata METADATA() {
        return this.METADATA;
    }

    /**
     * Returns a file object leading to this ImageFile's file on the user's system.
     * @return A file object leading to this ImageFile's file on the user's system.
     */
    public File getFile() {
        return new File(this.METADATA.absoluteFilePath());
    }

    /**
     * @return The set containing all FileTags attached to this UserFile.
     * @throws UnsupportedOperationException If there are no FileTags currently associated to this UserFile.
     */
    public LinkedHashSet<FileTag> getFileTags() throws UnsupportedOperationException {
        if (this.fileTags.isEmpty()) {
            throw new UnsupportedOperationException("There are no tags currently associated with this file.");
        }
        return this.fileTags;
    }

    /**
     * Adds a new FileTag to this UserFile.
     * If the tag provided is already attached to this UserFile, an exception is thrown.
     * @param fileTag The FileTag to attach to this UserFile.
     * @throws UnsupportedOperationException If the FileTag passed to this method is already applied to this UserFile.
     */
    public void addFileTag(FileTag fileTag) throws UnsupportedOperationException {
        if (this.fileTags.contains(fileTag)) {
            throw new UnsupportedOperationException("This file already has a tag by that name applied.");
        } else {
            this.fileTags.add(fileTag);
        }
    }

    /**
     * Searches through the file tags associated to this UserFile and removes the tag if one is found.
     * @param tagToRemove The name of the tag to try and remove.
     */
    public void removeFileTag(String tagToRemove) {
        for (FileTag tag : this.fileTags) {
            if (tag.getName().equals(tagToRemove)) {
                this.fileTags.remove(tag);
                break;
            }
        }
    }

    /**
     * Compare two files by their size.<br>
     * @return A positive integer (> 1) if this file is larger than the other file.<br>
     * A negative integer (< 1) if this file is smaller than the other file.<br>
     * Zero if the files are the same size.
     */
    public int compareSize(SystemFile otherSystemFile) {
        int thisSize = (int) this.METADATA().byteCount();
        int otherSize = (int) otherSystemFile.METADATA().byteCount();
        return Comparator.comparingInt(Integer::intValue).compare(thisSize, otherSize);
    }

    /**
     * Compare two files by their file name.<br>
     * @return A negative integer if this file comes before the other file lexicographically.<br>
     * A positive integer if this file comes after the other file lexicographically.<br>
     * Zero if the files have the same name.
     */
    public int compareLexicographically(SystemFile otherSystemFile) {
        String thisFileName = new File(this.METADATA().absoluteFilePath()).getName();
        String otherFileName = new File(otherSystemFile.METADATA().absoluteFilePath()).getName();
        return Comparator.comparing(String::toString).compare(thisFileName, otherFileName);
    }
}
