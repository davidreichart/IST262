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
        BufferedImage serviceImage = convertImageToBufferedImage();
        BufferedImage objImage = convertImageToBufferedImage((Image) obj);

        // two different sized images can't be the same
        if (serviceImage.getHeight() != objImage.getHeight() || serviceImage.getWidth() != objImage.getWidth()) {
            return false;
        }

        // If two images have the same quantity of pixels by color, we assume they are the same.
        // Note that this doesn't currently track for x-y positions of said pixels.
        HashMap<Color, Integer> serviceImageColorMap = getExactColorDistribution();
        HashMap<Color, Integer> objImageColorMap = getExactColorDistribution();

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
     * Returns a hash map containing the number of pixels and their corresponding colors found within an image.
     * @return A hash map with key = color and value = number of pixels of that color found within an image.
     */
    public HashMap<Color, Integer> getExactColorDistribution() {
        HashMap<Color, Integer> colorDistributionMap = new HashMap<>();
        BufferedImage bufferedImage = new BufferedImage(
                this.imageToInspect.getWidth(null),
                this.imageToInspect.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        // iterating through every pixel in the image
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                Color currentPixelColor = new Color(bufferedImage.getRGB(x, y));
                if (!colorDistributionMap.containsKey(currentPixelColor)) {
                    colorDistributionMap.put(currentPixelColor, 1);
                } else {
                    colorDistributionMap.merge(currentPixelColor, 1, Integer::sum);
                }
            }
        }
        return colorDistributionMap;
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

    /**
     * Converts the Image object held by this ImageInspectionService from an Image object to a BufferedImage object.
     * @return A BufferedImage of the Image object held by this ImageInspectionService.
     */
    private BufferedImage convertImageToBufferedImage() {
        return new BufferedImage(
                this.imageToInspect.getWidth(null),
                this.imageToInspect.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Converts the given Image object into a BufferedImage object.
     * @param imageToConvert The Image to convert to a BufferedImage.
     * @return A BufferedImage correspondent to the given Image.
     */
    private BufferedImage convertImageToBufferedImage(Image imageToConvert) {
        return new BufferedImage(
                imageToConvert.getWidth(null),
                imageToConvert.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
    }
}
