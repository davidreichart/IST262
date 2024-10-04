package model.data.metadata;

import java.io.File;
import java.util.Comparator;

/**
 * The Metadata class is a representation of contextualizing data points for any valid file on a user's computer.
 * Sub-metadata classes exist that are more specific to certain file types (i.e., ImageMetadata).
 * The Metadata class is intended to be a parent class for all other metadata types and cannot be instantiated.
 */
public abstract class Metadata implements Comparable<Metadata> {
    enum ContentType {
        IMAGE,
        VIDEO,
        AUDIO,
        TEXT,
        OTHER
    }

    private String absoluteFilePath;    // get
    private File file;
    private ContentType contentType;    // get
    private long byteCount;             // get

    /**
     * Constructs a new Metadata object.
     * The Metadata class is a representation of contextualizing data points for any valid file on a user's computer. <br>
     * The Metadata.builder() method should be used in cases where some fields are unknown.
     * @param absoluteFilePath The absolute file path leading to this file on the user's system.
     */
    public Metadata(String absoluteFilePath) {
        this.absoluteFilePath = absoluteFilePath;
        this.file = new File(absoluteFilePath);
        defineContentType();
        this.byteCount = this.file.length();
    }

    /**
     * Identifies the appropriate ContentType this metadata object is associated with.
     * The ContentType enum has five possible values: IMAGE, VIDEO, AUDIO, TEXT, and OTHER.
     */
    public void defineContentType() {
        String extension = this.file.getName().substring(this.file.getName().lastIndexOf(".") + 1);
        switch (extension) {
            case "jpg", "jpeg", "png", "gif", "bmp" -> this.contentType = ContentType.IMAGE;
            case "mp4", "avi", "mov", "wmv", "flv" -> this.contentType = ContentType.VIDEO;
            case "mp3", "wav", "flac", "aac", "ogg" -> this.contentType = ContentType.AUDIO;
            case "txt", "doc", "docx", "pdf", "rtf" -> this.contentType = ContentType.TEXT;
            default -> this.contentType = ContentType.OTHER;
        }
    }

    /**
     * Returns a JSON representation of the core metadata attributes for this file.
     * Content Type specific metadata should be accessed through other methods prefixed with the specific type.
     * (i.e., "imageMetadataJson()" for ImageMetadata objects).
     * @return A JSON representation of the core metadata attributes for this file.
     */
    public String metadataJSON() {
        return "\"Metadata\": {\n" +
                "  \"absoluteFilePath\": \"" + this.absoluteFilePath + "\",\n" +
                "  \"contentType\": \"" + this.contentType + "\",\n" +
                "  \"byteCount\": " + this.byteCount + ",\n" +
                "}";
    }

    /**
     * Returns a string representing the core metadata attributes for this file.
     * The string is JSON formatted, but is not a valid JSON object.
     * @return A string representing the core metadata attributes for this file.
     */
    @Override
    public String toString() {
        return "\"Metadata\": {\n" +
                "  \"absoluteFilePath\": \"" + this.absoluteFilePath + "\",\n" +
                "  \"contentType\": \"" + this.contentType + "\",\n" +
                "  \"byteCount\": " + this.byteCount + ",\n" +
                "  \"fileName\": \"" + getFileName() + "\",\n" +
                "  \"fileExtension\": \"" + getFileExtension() + "\"\n" +
                "}";
    }

    /**
     * Compare two files by their size.<br><br>
     * Use .compare(Metadata otherMetadata) to compare two files by their size according to the following rules:<br>
     * - If this file is larger than the other file, a positive number is returned. (use > 0)<br>
     * - If this file is smaller than the other file, a negative number is returned. (use < 0)<br>
     * - If the files are the same size, zero is returned (use == 0).
     */
    public int compareSize(Metadata otherMetadata) {
        Comparator<Metadata> compareSize = Comparator.comparing(Metadata::getByteCount);
        return compareSize.compare(this, otherMetadata);
    }

    /**
     * Compare two files by their file name.<br><br>
     * Use .compare(Metadata otherMetadata) to compare two files by their file name according to the following rules:<br>
     * - If this file comes before the other file lexicographically, a negative integer is returned. (use < 0)<br>
     * - If this file comes after the other file lexicographically, a positive integer is returned. (use > 0)<br>
     * - If the files have the same name, zero is returned (use == 0).
     */
    public int compareLexicographically(Metadata otherMetadata) {
        Comparator<Metadata> compareLexicographically = Comparator.comparing(Metadata::getFileName);
        return compareLexicographically.compare(this, otherMetadata);
    }


    /**
     * Defines the natural ordering of Metadata objects.
     * Natural ordering follows lexicographical order by file name.
     * @param o the object to be compared.
     * @return a negative integer if the name of this file comes before the other file lexicographically, <br>
     *         a positive integer if the name of this file comes after the other file lexicographically, <br>
     *         or zero if the files have the same name.
     */
    @Override
    public int compareTo(Metadata o) {
        return compareLexicographically(o);
    }

    /**
     * Returns the absolute file path of this metadata object.
     * This can be used as an identifier for the file on the user's system to ensure that the multiple possible
     * metadata types are connected to the same file.
     * @return The absolute file path of this metadata object.
     */
    public String getAbsoluteFilePath() {
        return this.absoluteFilePath;
    }

    /**
     * Returns a ContentType enum value representing the type of file this metadata object is associated with.
     * The ContentType enum has five possible values: IMAGE, VIDEO, AUDIO, TEXT, and OTHER.
     * This can be used to verify the type of file before casting to a more specific metadata type.
     * @return A ContentType enum value representing the type of file this metadata object is associated with.
     */
    public ContentType getContentType() {
        return this.contentType;
    }

    /**
     * Returns the name of the file associated with this metadata object.
     * The file name will include the file's extension, but excludes the path to the file.
     * @return The name of the file associated with this metadata object.
     */
    public String getFileName() {
        return this.file.getName();
    }

    /**
     * Returns the file extension of the file associated with this metadata object.
     * The file extension is the characters following the last period in the file name.
     * For example, "image.png" would return "png".
     * @return The file extension of the file associated with this metadata object.
     */
    public String getFileExtension() {
        return this.file.getName().substring(this.file.getName().lastIndexOf(".") + 1);
    }

    /**
     * Returns the size of this file in bytes.
     * Other get methods are available for other measurements (i.e., kilobytes, megabytes).
     * This program uses the base-10 definition of kilobytes, megabytes, etc. (1 KB = 1000 bytes).
     * Base-10 is used to match the way file sizes are displayed in most operating systems.
     * @return The size of this file in bytes.
     */
    public long getByteCount() {
        return this.byteCount;
    }

    /**
     * Returns the size of this file in kilobytes.
     * Other get methods are available for other measurements (i.e., kilobytes, megabytes, etc.).
     * One Kilobyte = one thousand bytes.
     * @return The size of this file in kilobytes.
     */
    public float getKilobyteCount() {
        return (float) this.byteCount / 1000;
    }

    /**
     * Returns the size of this file in megabytes.
     * Other get methods are available for other measurements (i.e., kilobytes, megabytes, etc.).
     * One Megabyte = one thousand kilobytes.
     * One Megabyte = one million bytes.
     * @return The size of this file in megabytes.
     */
    public float getMegabyteCount() {
        return (float) this.byteCount / 1000 / 1000;
    }

    /**
     * Returns the size of this file in gigabytes.
     * Other get methods are available for other measurements (i.e., kilobytes, megabytes, etc.).
     * One Gigabyte = one thousand megabytes.
     * One Gigabyte = one million kilobytes.
     * One Gigabyte = one billion bytes.
     * @return The size of this file in gigabytes.
     */
    public float getGigabyteCount() {
        return (float) this.byteCount / 1000 / 1000 / 1000;
    }

    /**
     * Returns the size of this file in terabytes.
     * Other get methods are available for other measurements (i.e., kilobytes, megabytes, etc.).
     * One Terabyte = one thousand gigabytes.
     * One Terabyte = one million megabytes.
     * One Terabyte = one billion kilobytes.
     * One Terabyte = one trillion bytes.
     * @return The size of this file in terabytes.
     */
    public float getTerabyteCount() {
        return (float) this.byteCount / 1000 / 1000 / 1000 / 1000;
    }
}
