package OLD;

import java.awt.*;

/**
 * The FileTag class represents tags meant to aid in the organization of files managed by the application. File tags
 * serve as simple user-definable attributes. FileTags are intended to be stored in an internal list of any given
 * object representing a file on the user's computer. <br>
 * A file cannot have duplicate tags. A tag is considered a duplicate if they have the same name.
 */
public class FileTag_Old implements Comparable<FileTag_Old> {
    private String name;
    private Color color;

    public FileTag_Old(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public int compareTo(FileTag_Old o) {
        return this.getName().compareTo(o.getName());
    }

    /**
     * Returns the name of this file tag.
     * @returnthe name of this file tag.
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of this file tag.
     * @param name the new name for this file tag.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the color associated with this file tag. A color object is returned. The color represents what color is used
     * in the styling of associated swing elements.
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * Changes the color associated with this file tag. A color object is required. The color represents what color is used
     * in the styling of associated swing elements.
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}