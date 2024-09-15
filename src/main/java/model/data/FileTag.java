package model.data;

import java.awt.*;
import java.util.Comparator;

/**
 * The FileTag class represents a user-definable data point to categorize ImageFile objects with.
 * These tags can be used when performing filtering/sorting operations when displaying files in the program's GUI.
 */
public class FileTag implements Comparable<FileTag> {

    private String name;
    private Color color;

    /**
     * Constructs a new FileTag object that can be applied to an ImageFile object.
     * The FileTag class represents a user-definable data point to categorize ImageFile objects with.
     * @param name The name (and primary data point) of this FileTag.
     * @param color The color to associate with GUI elements for this FileTag.
     */
    public FileTag(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    /**
     * Returns a string detailing all information defining this file tag.
     * @return a string detailing all information defining this file tag.
     */
    @Override
    public String toString() {
        return "FILE TAG: \n" +
                "    Tag name: " + this.name + "\n" +
                "    Color: " + this.color.toString();
    }

    /**
     * Compares two FileTags by their name. FileTag order is determined by tag name, sorted lexicographically.
     * @param o The FileTag to compare against this FileTag.
     * @return <br>
     * A positive number if o1's tag name comes before o2's tag name lexicographically. <br>
     * 0 if o1 and o2 have the same tag name. <br>
     * A negative number if o1's tag name comes after o2's tag name lexicographically.
     */
    @Override
    public int compareTo(FileTag o) {
        return this.getName().compareTo(o.getName());
    }

    /**
     * Checks to see if the provided object is a FileTag and if it is, checks to see if that FileTag object also has
     * the same name as this FileTag.
     * @param obj The object to compare this ImageFile's FileTag to.
     * @return <br>
     * True if the provided object is an FileTag with the same name as this FileTag. <br>
     * False if the provided object isn't a FileTag, or the provided FileTag has a different name.
     */
    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        }

        return compareTo((FileTag) obj) == 0;
    }

    /**
     * Returns the name of this FileTag.
     * The name is the primary data point used for any filtering/sorting operations that are influenced/driven by file tags.
     * @return The name of this FileTag.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets/Replaces the name of this FileTag.
     * The name is the primary data point used for any filtering/sorting operations that are influenced/driven by file tags.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the Color object associated with this FileTag.
     * This color is referenced by the program's GUI.
     * @return The Color associated with this FileTag.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets/Replaces the Color object associated with this FileTag.
     * This color is referenced by the program's GUI.
     * @param color The new Color to be applied to this FileTag.
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
