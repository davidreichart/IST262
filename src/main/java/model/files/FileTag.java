package model.files;

import java.awt.*;

public class FileTag {

    private String name;
    private Color color;

    public FileTag(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }
}
