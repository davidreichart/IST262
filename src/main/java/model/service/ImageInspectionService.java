package model.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
     * Returns a string detailing the source of the image currently held by this instance of the ImageInspectionService.
     * This can be used if it is unclear what image is currently in the service queue.
     * @return a string detailing the source of the image currently held by this instance of the service.
     */
    @Override
    public String toString() {
        return "Currently held image: " + this.imageToInspect.getSource().toString();
    }

    /**
     * Checks two Image objects for equality.
     * The following conditions must be true to determine equality: <br>
     * Images are the same dimension. <br>
     * Images have the same quantity of individual colors (checked per pixel).
     * @param obj The other object that must be an image to check for equality with the image held by the ImageInspectionService.
     * @return <br>
     * True if all above mentioned conditions are met. <br>
     * False if ANY above mentioned conditions are not met.
     */
    public boolean isSameImage(Object obj) {
        //todo: this feels real bloated, too many responsibilities, could use this functionality elsewhere in the service

        // provided object must be of type Image
        if (!obj.getClass().equals(this.imageToInspect.getClass())) {
            return false;
        }

        // need to cast Image -> BufferedImage to inspect objects further
        BufferedImage serviceImage = new BufferedImage(
                this.imageToInspect.getWidth(null),
                this.imageToInspect.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        BufferedImage objImage = new BufferedImage(
                ((Image) obj).getWidth(null),
                ((Image) obj).getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        // two different sized images can't be the same
        if (serviceImage.getHeight() != objImage.getHeight() || serviceImage.getWidth() != objImage.getWidth()) {
            return false;
        }

        // If two images have the same quantity of pixels by color, we assume they are the same.
        // Note that this doesn't currently track for x-y positions of said pixels.
        HashMap<Color, Integer> serviceImageColorMap = new HashMap<>();
        HashMap<Color, Integer> objImageColorMap = new HashMap<>();
        for (int y = 0; y < serviceImage.getHeight(); y++) {
            for (int x = 0; x < serviceImage.getWidth(); x++) {
                Color serviceImagePixelColor = new Color(serviceImage.getRGB(x, y));
                Color objImagePixelColor = new Color(objImage.getRGB(x, y));
                if (!serviceImageColorMap.containsKey(serviceImagePixelColor)) {
                    serviceImageColorMap.put(serviceImagePixelColor, 1);
                } else {
                    serviceImageColorMap.merge(serviceImagePixelColor, 1, Integer::sum);
                }
                if (!objImageColorMap.containsKey(objImagePixelColor)) {
                    objImageColorMap.put(objImagePixelColor, 1);
                } else {
                    objImageColorMap.merge(objImagePixelColor, 1, Integer::sum);
                }
            }
        }

        for (Map.Entry<Color, Integer> serviceColorMapEntry : serviceImageColorMap.entrySet()) {
            Color currentColor = serviceColorMapEntry.getKey();
            Integer currentQuantity = serviceColorMapEntry.getValue();
            // the other image's map must both contain this image's color AND have the same quantity recorded.
            if (!Objects.equals(objImageColorMap.get(currentColor), currentQuantity)) {
                return false;
            }
        }
        // will be reached if all required conditions are met
        return true;
    }

    /**
     * Returns a Dimension object representing the resolution (height x width) of the image file held by this ImageInspectionService.
     * @return A Dimension object representing the resolution (height x width) of the image.
     */
    public Dimension getResolution() {
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
