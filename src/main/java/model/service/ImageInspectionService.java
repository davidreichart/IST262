package model.service;

import java.awt.*;

/**
 * The ImageInspectionService contains methods to contextualize Image files.
 * This class is primarily meant to be a helper class with functionality that provides data points to be stored in
 * a {@code FileMetadata} object. They do not have to be stored in said objects.
 * To work with a different Image, the {@code setImageToInspect} method updates the currently stored file or a new
 * ImageInspectionService must be created.
 */
public class ImageInspectionService {

    private Image imageToInspect;

    /**
     * Constructs a new ImageInspectionService.
     * The ImageInspectionService object contains methods to contextualize metadata about the provided image.
     * @param imageToInspect The image to assess with the service.
     */
    public ImageInspectionService(Image imageToInspect) {
        this.imageToInspect = imageToInspect;
    }

    /**
     * Returns a Dimension object representing the resolution (height x width) of the image file held by this ImageInspectionService.
     * @param imageToInspect The currently stored image.
     * @return A Dimension object representing the resolution (height x width) of the image.
     */
    public Dimension getResolution(Image imageToInspect) {
        int height = imageToInspect.getHeight(null);
        int width = imageToInspect.getWidth(null);
        return new Dimension(width, height);
    }

    /**
     * Returns a copy of the Image object currently held by this ImageInspectionService.
     * @return An Image object currently held by this ImageInspectionService
     */
    public Image getImageToInspect() {
        return imageToInspect;
    }

    /**
     * Replaces the Image object currently held by this ImageInspectionService.
     * This method can be used instead of creating a new ImageInspectionService for each image when dealing with multiple at a time.
     * @param imageToInspect The new Image to be assessed by this ImageInspectionService.
     */
    public void setImageToInspect(Image imageToInspect) {
        this.imageToInspect = imageToInspect;
    }
}
