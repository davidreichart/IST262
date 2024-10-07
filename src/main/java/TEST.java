import model.data.filetypes.ImageFile;
import model.util.ImageInspector;

import java.awt.image.BufferedImage;

public class TEST {
    public static void main(String[] args) {
        BufferedImage image = ImageInspector.loadImage("C:\\Users\\Snaxx\\Pictures\\gw058.jpg");
        int[][][] histogram = ImageInspector.generateColorHistogram(image, 8);
        for (int red = 0; red < histogram.length; red++) {
            for (int blue = 0; blue < histogram[red].length; blue++) {
                for (int green = 0; green < histogram[red][blue].length; green++) {
                    if (histogram[red][blue][green] == 0) {
                        continue;
                    }
                    System.out.println("Red: " + red + " Blue: " + blue + " Green: " + green + " Count: " + histogram[red][blue][green]);
                }
            }
        }
    }
}
