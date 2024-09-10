package model.service;

import java.awt.*;

public class ImageInspectionService {

    Image imageToInspect;

    public ImageInspectionService(Image imageToInspect) {
        this.imageToInspect = imageToInspect;
    }

    public Dimension getResolution(Image imageToInspect) {
        int height = imageToInspect.getHeight(null);
        int width = imageToInspect.getWidth(null);
        return new Dimension(width, height);
    }
}
