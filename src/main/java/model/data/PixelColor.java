package model.data;

import java.awt.*;

/**
 * The PixelColor object is a wrapper for the Color object.
 * It is used to make Color objects comparable by checking their RGB values.
 */
public class PixelColor implements Comparable<PixelColor> {
    private Color color;

    public PixelColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "PixelColor{" +
                "color=" + color +
                '}';
    }

    @Override
    public int compareTo(PixelColor o) {
        return this.color.getRGB() - o.color.getRGB();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PixelColor) {
            PixelColor other = (PixelColor) obj;
            return this.color.equals(other.color);
        }
        return false;
    }
}
