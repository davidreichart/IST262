package model.data;

import java.awt.*;

/**
 * The FileTag class represents a user-definable data point to categorize ImageFile objects with.
 * These tags can be used when performing filtering/sorting operations when displaying files in the program's GUI.
 */
public class FileTag {

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
