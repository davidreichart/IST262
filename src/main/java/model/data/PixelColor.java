package model.data;

import java.awt.*;

/**
 * The PixelColor object is a wrapper for the Color object.
 * It is used to make Color objects comparable by checking their RGB values.
 */
public class PixelColor implements Comparable<PixelColor> {
    private Color color;

    /**
     * Constructs a new PixelColor object with the provided Color object.
     * The PixelColor object is a wrapper for the Color object.
     * It is used to make Color objects comparable by checking their RGB values.
     * @param color The Color object to be attached to this PixelColor.
     */
    public PixelColor(Color color) {
        this.color = color;
    }

    /**
     * Provides the Color object attached to this PixelColor.
     * @return The Color object attached to this PixelColor.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Provides a string representation of the color object attached to this PixelColor.
     * @return A string representation of the color object attached to this PixelColor.
     */
    @Override
    public String toString() {
        return "PixelColor{" +
                "color=" + color +
                '}';
    }

    /**
     * Compares the RGB values of the PixelColor objects.
     * @param o the PixelColor to be compared.
     * @return a negative integer, zero, or a positive integer as this PixelColor is less than, equal to, or greater than the PixelColor.
     */
    @Override
    public int compareTo(PixelColor o) {
        return this.color.getRGB() - o.color.getRGB();
    }

    /**
     * Checks if the provided object is a PixelColor object and if the Color objects are the same color.
     * @param obj The object to compare to.
     * @return True if both PixelColors have the same color attached. <br>
     * False if both PixelColors have different colors OR the provided object is not a PixelColor object.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PixelColor) {
            PixelColor other = (PixelColor) obj;
            return this.color.equals(other.color);
        }
        return false;
    }
}
