import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

public class ImageProcessing {
    public static void main(String[] args) {
        // The provided images are apple.jpg, flower.jpg, and kitten.jpg
        int[][] imageData = imgToTwoD("./apple.jpg");
        // Or load your own image using a URL!
        //int[][] imageData = imgToTwoD("https://content.codecademy.com/projects/project_thumbnails/phaser/bug-dodger.png");
        viewImageData(imageData);
        int[][] trimmed = trimBorders(imageData, 20);
        int[][] negative = negativeColor(imageData);
        int[][] horizontallyStretched = stretchHorizontally(imageData);
        int[][] verticallyShrunk = shrinkVertically(imageData);
        int[][] invertedImage = invertImage(imageData);
        int[][] filteredImage = colorFilter(imageData, -75, 30, -30);
        int[][] randomImage = paintRandomImage(new int[500][500]);
        int[][] imageWithRectangle = paintRectangle(imageData, 30, 30, 2, 2, getColorIntValFromRGBA(new int[] {255, 255, 0, 255}));
        int[][] randomRectangles = generateRectangles(imageData, 1000);

        twoDToImage(randomRectangles, "./trimmed_apple.jpg");
        // int[][] allFilters = stretchHorizontally(shrinkVertically(colorFilter(negativeColor(trimBorders(invertImage(imageData), 50)), 200, 20, 40)));
        // Painting with pixels
    }

    /**
     * Converts a given two-dimensional array of hexadecimal integers representing pixels from an image
     * and trims a give number of pixels from all four sides of the image.
     * @param imageTwoD The 2-D hexadecimal integer array of pixels.
     * @param pixelCount The number of pixels to trim off of all sides of the given image.
     * @return A trimmed version of the image as a 2-D hexadecimal integer array representing pixels.
     */
    public static int[][] trimBorders(int[][] imageTwoD, int pixelCount) {
        // cannot remove all pixels from the input image
        if (imageTwoD.length > pixelCount * 2 && imageTwoD[0].length > pixelCount * 2) {

            // copy over the input pixels into a smaller sized array
            int[][] trimmedImg = new int[imageTwoD.length - pixelCount * 2][imageTwoD[0].length - pixelCount * 2];
            for (int i = 0; i < trimmedImg.length; i++) {
                for (int j = 0; j < trimmedImg[i].length; j++) {
                    trimmedImg[i][j] = imageTwoD[i + pixelCount][j + pixelCount];
                }
            }
            return trimmedImg;
        } else {
            System.out.println("Cannot trim that many pixels from the given image.");
            return imageTwoD;
        }
    }

    /**
     * Inverts the colors of a given image.
     * The pixels of the image to invert must be in the form of a two-dimensional hexadecimal integer array.
     * @param imageTwoD The hexadecimal pixel values to invert.
     * @return A 2-D array of inverted, hexadecimal values representing pixels of an image.
     */
    public static int[][] negativeColor(int[][] imageTwoD) {
        int[][] negativeImage = new int[imageTwoD.length][imageTwoD[0].length];

        for (int row = 0; row < imageTwoD.length; row++) {
            for (int column = 0; column < imageTwoD[row].length; column++) {
                int[] oldRGBA = getRGBAFromPixel(imageTwoD[row][column]);
                // negative values should just be zero
                int newRed = Math.max(255 - oldRGBA[0], 0);
                int newGreen = Math.max(255 - oldRGBA[1], 0);
                int newBlue = Math.max(255 - oldRGBA[2], 0);
                int newAlpha = Math.max(255 - oldRGBA[3], 0);
                int[] newRGBA = { newRed, newGreen, newBlue, newAlpha };
                negativeImage[row][column] = getColorIntValFromRGBA(newRGBA);
            }
        }

        return negativeImage;
    }

    /**
     * Stretches a given image horizontally to be double its initial width.
     * The image must be provided as a two-dimensional array of hexadecimal integers.
     * @param imageTwoD The 2-D hexadecimal array representing the initial image's pixels.
     * @return A 2-D hexadecimal integer array representing the initial image with double the width.
     */
    public static int[][] stretchHorizontally(int[][] imageTwoD) {
        int[][] horizontallyStretched = new int[imageTwoD.length][imageTwoD[0].length * 2];

        int doubleColumnIndex;
        for (int row = 0; row < imageTwoD.length; row++) {
            doubleColumnIndex = 0;
            for (int column = 0; column < imageTwoD[row].length; column++) {
                int currentPixel = imageTwoD[row][column];

                horizontallyStretched[row][doubleColumnIndex] = currentPixel;
                horizontallyStretched[row][doubleColumnIndex + 1] = currentPixel;
                doubleColumnIndex += 2;
            }
        }

        return horizontallyStretched;
    }

    /**
     * Shrinks an input image vertically to be half its original height.
     * The image must be provided as a two-dimensional array of hexadecimal integers.
     * @param imageTwoD The 2-D hexadecimal array representing the initial image's pixels.
     * @return A 2-D hexadecimal integer array representing the initial image with half the height.
     */
    public static int[][] shrinkVertically(int[][] imageTwoD) {
        int[][] verticallyShrunk = new int[imageTwoD.length / 2][imageTwoD[0].length];

        // every other pixel from the initial image is skipped and therefore lost
        for (int row = 0; row < verticallyShrunk.length; row++) {
            for (int column = 0; column < verticallyShrunk[row].length; column++) {
                verticallyShrunk[row][column] = imageTwoD[row * 2][column];
            }
        }

        return verticallyShrunk;
    }

    /**
     * Flips the given image to its opposite horizontal and vertical positions.
     * The image must be provided as a two-dimensional array of hexadecimal integers.
     * @param imageTwoD The 2-D hexadecimal array representing the initial image's pixels.
     * @return A 2-D hexadecimal integer array representing the initial image flipped horizontally and vertically.
     */
    public static int[][] invertImage(int[][] imageTwoD) {
        int[][] invertedTransform = new int[imageTwoD.length][imageTwoD[0].length];

        int newRow = 0;
        int newColumn = 0;
        for (int row = imageTwoD.length - 1; row > 0; row--) {
            newColumn = 0;
            for (int column = imageTwoD[0].length - 1; column > 0 ; column--) {
                invertedTransform[newRow][newColumn] = imageTwoD[row][column];
                newColumn++;
            }
            newRow++;
        }

        return invertedTransform;
    }

    /**
     * Applies a specified color shift to the input image.
     * Positive red/green/blue change values will increase the corresponding values.
     * Negative red/green/blue change values will decrease the corresponding values.
     * Any values that would become less than 0 are truncated to 0.
     * The image must be provided as a two-dimensional array of hexadecimal integers.
     * @param imageTwoD The 2-D hexadecimal array representing the initial image's pixels.
     * @param redChangeValue The amount to increase/decrease red color values of the input image.
     * @param greenChangeValue The amount to increase/decrease green color values of the input image.
     * @param blueChangeValue The amount to increase/decrease blue color values of the input image.
     * @return A color shifted image in the form of a 2-D hexadecimal integer array.
     */
    public static int[][] colorFilter(int[][] imageTwoD, int redChangeValue, int greenChangeValue, int blueChangeValue) {
        int[][] filteredImage = new int[imageTwoD.length][imageTwoD[0].length];

        for (int row = 0; row < filteredImage.length; row++) {
            for (int column = 0; column < filteredImage[row].length; column++) {
                int[] initialRGBA = getRGBAFromPixel(imageTwoD[row][column]);
                int newRed = initialRGBA[0] + redChangeValue;
                int newGreen = initialRGBA[1] + greenChangeValue;
                int newBlue = initialRGBA[2] + blueChangeValue;

                if (newRed < 0) {
                    newRed = 0;
                } else if (newRed > 255) {
                    newRed = 255;
                }

                if (newGreen < 0) {
                    newGreen = 0;
                } else if (newGreen > 255) {
                    newGreen = 255;
                }

                if (newBlue < 0) {
                    newBlue = 0;
                } else if (newBlue > 255) {
                    newBlue = 255;
                }

                int[] newRGBA = { newRed, newGreen, newBlue, initialRGBA[3] };

                filteredImage[row][column] = getColorIntValFromRGBA(newRGBA);
            }
        }

        return filteredImage;
    }

    /**
     * Generates a random image from randomly colored pixels using a random number generator.
     * @param canvas An empty 2-D array representing the size of image to generate.
     * @return A 2-D array of randomly generated hexadecimal values representing an image's pixels.
     */
    public static int[][] paintRandomImage(int[][] canvas) {
        int[][] randomImage = new int[canvas.length][canvas[0].length];
        Random rng = new Random();

        for (int row = 0; row < canvas.length; row++) {
            for (int column = 0; column < canvas[row].length; column++) {
                int randomRed = rng.nextInt(256);
                int randomGreen = rng.nextInt(256);
                int randomBlue = rng.nextInt(256);
                int hexadecimal = getColorIntValFromRGBA(new int[] {randomRed, randomGreen, randomBlue, 255});

                randomImage[row][column] = hexadecimal;
            }
        }

        return randomImage;
    }

    /**
     * Draws a rectangle on top of an image at the specified location and of the specified size.
     * @param canvas The image to draw the rectangle on to, represented by a 2-D array of pixels.
     * @param width The width of the rectangle to draw in pixels.
     * @param height The height of the rectangle to draw in pixels.
     * @param rowPosition The row index to start drawing the rectangle at
     * @param colPosition The column index to start drawing the rectangle at
     * @param color The color to fill the drawn rectangle with.
     * @return The input image with a rectangle drawn atop it as a 2-D array of hexadecimal integers.
     */
    public static int[][] paintRectangle(int[][] canvas, int width, int height, int rowPosition, int colPosition, int color) {
        int[][] imageWithRectangle = canvas;

        for (int row = rowPosition; row < canvas.length; row++) {
            // number of rows to fill = requested height
            if (row == rowPosition + height) {
                break;
            }

            for (int column = colPosition; column < canvas[row].length; column++) {
                // number of columns to fill = requested width
                if (column == colPosition + width) {
                    break;
                }
                imageWithRectangle[row][column] = color;
            }
        }

        return imageWithRectangle;
    }

    /**
     * Generates randomly placed rectangles on an image.
     * @param canvas The 2-D array of hexadecimal integers representing an image.
     * @param numRectangles The number of rectangles to generate randomly on the input image.
     * @return A 2-D hexadecimal integer array representing the input image with randomly placed rectangles.
     */
    public static int[][] generateRectangles(int[][] canvas, int numRectangles) {
        int rowCount = canvas.length;
        int columnCount = canvas[0].length;
        int[][] imageWithRandomRectangles = new int[canvas.length][canvas[0].length];
        Random rng = new Random();

        for (int i = 0; i < numRectangles; i++) {
            // size of rectangle
            int width = rng.nextInt(columnCount);
            int height = rng.nextInt(rowCount);

            // location to start the rectangle at
            int rowPosition = rng.nextInt(rowCount - height);
            int columnPosition = rng.nextInt(columnCount - width);

            // color of rectangle
            int red = rng.nextInt(256);
            int green = rng.nextInt(256);
            int blue = rng.nextInt(256);
            int alpha = 255;
            int hexadecimal = getColorIntValFromRGBA(new int[] { red, green, blue, alpha });

            paintRectangle(canvas, width, height, rowPosition, columnPosition, hexadecimal);
        }
        return imageWithRandomRectangles;
    }

    /**
     * Provides a two-dimensional array of integers representing the hexadecimal value for each pixel in
     * a provided image.
     * The image may either be a URL to a web-hosted image, or a path to an image on the user's system.
     * Hexadecimal values contain (R, G, B, A) data.
     * @param inputFileOrLink The URL/file path leading to the image to convert.
     * @return A 2-D array of integers representing hexadecimal values corresponding to pixels from the input image.
     */
    public static int[][] imgToTwoD(String inputFileOrLink) {
        try {
            BufferedImage image = null;
            if (inputFileOrLink.substring(0, 4).toLowerCase().equals("http")) {
                URL imageUrl = new URL(inputFileOrLink);
                image = ImageIO.read(imageUrl);
                if (image == null) {
                    System.out.println("Failed to get image from provided URL.");
                }
            } else {
                image = ImageIO.read(new File(inputFileOrLink));
            }
            int imgRows = image.getHeight();
            int imgCols = image.getWidth();
            int[][] pixelData = new int[imgRows][imgCols];
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    pixelData[i][j] = image.getRGB(j, i);
                }
            }
            return pixelData;
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Converts a two-dimensional array of integers representing pixel data into an image.
     * The created image is then saved to a file of the input name.
     * @param imgData The 2-D array of pixel data.
     * @param fileName The name to save the new image as.
     */
    public static void twoDToImage(int[][] imgData, String fileName) {
        try {
            int imgRows = imgData.length;
            int imgCols = imgData[0].length;
            BufferedImage result = new BufferedImage(imgCols, imgRows, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    result.setRGB(j, i, imgData[i][j]);
                }
            }
            File output = new File(fileName);
            ImageIO.write(result, "jpg", output);
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e.getLocalizedMessage());
        }
    }

    /**
     * Takes an integer representing a hexadecimal color value and provides an array containing the
     * red, green, blue, and alpha values seperated into individual integers.
     * These values will range from 0-255.
     * The provided int[] array will always be of length 4.
     * @param pixelColorValue The hexadecimal value for a single pixel.
     * @return An array of four integers representing [red][green][blue][alpha] values of the input hexadecimal integer.
     */
    public static int[] getRGBAFromPixel(int pixelColorValue) {
        Color pixelColor = new Color(pixelColorValue);
        return new int[] { pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha() };
    }

    /**
     * Converts an array of integers representing the [red][green][blue][alpha] values for a single pixel
     * into an equivalent hexadecimal value as a single integer.
     * @param colorData The red, green, blue, and alpha values to convert into a single integer.
     * @return An integer representing the equivalent hexadecimal value for the given input.
     */
    public static int getColorIntValFromRGBA(int[] colorData) {
        if (colorData.length == 4) {
            Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
            return color.getRGB();
        } else {
            System.out.println("Incorrect number of elements in RGBA array.");
            return -1;
        }
    }

    /**
     * Extracts a 3x3 section from the top left of a given image.
     * The image must be in the form of a two-dimensional hexadecimal integer array.
     * {@link ImageProcessing#imgToTwoD(String)} Can convert an image into the needed array for this method.
     * Used to view the structure of the image data in both the raw pixel form and the extracted RGBA form
     * @param imageTwoD The 2-D array of hexadecimal values to analyze.
     */
    public static void viewImageData(int[][] imageTwoD) {
        if (imageTwoD.length > 3 && imageTwoD[0].length > 3) {
            int[][] rawPixels = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rawPixels[i][j] = imageTwoD[i][j];
                }
            }
            System.out.println("Raw pixel data from the top left corner. (hexadecimal)");
            System.out.print(Arrays.deepToString(rawPixels).replace("],", "],\n") + "\n");
            int[][][] rgbPixels = new int[3][3][4];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rgbPixels[i][j] = getRGBAFromPixel(imageTwoD[i][j]);
                }
            }
            System.out.println();
            System.out.println("Extracted RGBA pixel data from top the left corner. (red, green, blue, alpha)");
            for (int[][] row : rgbPixels) {
                System.out.print(Arrays.deepToString(row) + System.lineSeparator());
            }
        } else {
            System.out.println("The image is not large enough to extract 9 pixels from the top left corner");
        }
    }
}