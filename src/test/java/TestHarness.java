import model.data.filetypes.FileSystemResource;
import model.data.filetypes.ImageFile;
import model.data.filetypes.SystemDirectory;

import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;

public class TestHarness {
    public static void main(String[] args) {
        InterfaceTest.instantiation();

        InterfaceTest.open();

        InterfaceTest.rename();
        InterfaceTest.renameCleanUp();

        InterfaceTest.moveTo();
        InterfaceTest.moveToCleanUp();

        InterfaceTest.getContentType();
    }

    public static class InterfaceTest {

        public static final String RESET = "\u001B[0m";
        public static final String BLACK = "\u001B[30m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String WHITE = "\u001B[37m";

        /**
         * Tests the instantiation of the FileSystemResource interface and its implementations.
         */
        public static void instantiation() {
            System.out.println();
            System.out.println(CYAN + "instantiation" + RESET);
            System.out.println(CYAN + "-------------" + RESET);
            // Arrange
            FileSystemResource[] resources = {
                new ImageFile("src/test/resources/bluePNG.png"),
                new SystemDirectory("src/test/resources")
            };

            // Act
            if (resources[0] instanceof ImageFile) {
                System.out.println(GREEN + resources[0] + RESET);
            } else {
                System.out.println(RED + resources[0] + " should be an ImageFile object" + RESET);
            }

            if (resources[1] instanceof SystemDirectory) {
                System.out.println(GREEN + resources[1] + RESET);
            } else {
                System.out.println(RED + resources[1] + " should be a SystemDirectory object" + RESET);
            }
            System.out.println();
            for (FileSystemResource resource : resources) {
                System.out.println(GREEN + resource.getContentType() + RESET);
            }
        }

        /**
         * Tests the open method of the FileSystemResource interface and its implementations.
         * Directories cannot be opened, so the method will throw an exception.
         */
        public static void open() {
            System.out.println();
            System.out.println(CYAN + "open" + RESET);
            System.out.println(CYAN + "----" + RESET);
            System.out.println(YELLOW + "Directories cannot be opened.\n" + RESET);
            // Arrange
            FileSystemResource[] resources = {
                new ImageFile("src/test/resources/bluePNG.png"),
                new SystemDirectory("src/test/resources")
            };

            // Act
            for (FileSystemResource resource : resources) {
                try {
                    resource.open();
                    System.out.println(GREEN + "Opened " + resource.getContentType() + RESET);
                } catch (FileNotFoundException e) {
                    System.out.println(RED + "Failed to open " + resource.getContentType() + RESET);
                }
            }
        }

        /**
         * Tests the rename method of the FileSystemResource interface and its implementations.
         */
        public static void rename() {
            System.out.println();
            System.out.println(CYAN + "rename" + RESET);
            System.out.println(CYAN + "------" + RESET);
            // Arrange
            FileSystemResource png = new ImageFile("src/test/resources/redPNG100x200.png");
            FileSystemResource jpeg = new ImageFile("src/test/resources/greenJPEG.jpeg");

            String oldPngName = "", oldJpegName = "", newPngName = "", newJpegName = "";
            // type casting to get access to names
            if (png instanceof ImageFile && jpeg instanceof ImageFile) {
                oldPngName = ((ImageFile) png).METADATA().fileName();
                oldJpegName = ((ImageFile) jpeg).METADATA().fileName();
            }

            // Act
            System.out.println(YELLOW + "File names before renaming:" + RESET);
            System.out.println(YELLOW + oldPngName + RESET);
            System.out.println(YELLOW + oldJpegName + RESET);
            System.out.println();
            try {
                png.rename("redPNG100x200-2.png");
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }

            try {
                jpeg.rename("greenJPEG2.jpeg");
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }

            FileSystemResource newPng = new ImageFile("src/test/resources/redPNG100x200-2.png");
            FileSystemResource newJpeg = new ImageFile("src/test/resources/greenJPEG2.jpeg");

            // type casting to get access to names
            if (newPng instanceof ImageFile && newJpeg instanceof ImageFile) {
                newPngName = ((ImageFile) newPng).METADATA().fileName();
                newJpegName = ((ImageFile) newJpeg).METADATA().fileName();
            }

            System.out.println(GREEN + "File names after renaming:" + RESET);
            System.out.println(GREEN + newPngName + RESET);
            System.out.println(GREEN + newJpegName + RESET);
        }

        /**
         * Cleans up the rename test by changing the names back to their original names.
         */
        public static void renameCleanUp() {
            FileSystemResource newPng = new ImageFile("src/test/resources/redPNG100x200-2.png");
            FileSystemResource newJpeg = new ImageFile("src/test/resources/greenJPEG2.jpeg");

            try {
                newPng.rename("redPNG100x200.png");
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }

            try {
                newJpeg.rename("greenJPEG.jpeg");
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }
        }

        /**
         * Tests the moveTo method of the FileSystemResource interface and its implementations.
         * moveTo should move files to the "testDirectory" directory.
         */
        public static void moveTo() {
            System.out.println();
            System.out.println(CYAN + "moveTo" + RESET);
            System.out.println(CYAN + "------" + RESET);
            // Arrange
            FileSystemResource png = new ImageFile("src/test/resources/redPNG100x200.png");
            FileSystemResource jpeg = new ImageFile("src/test/resources/greenJPEG.jpeg");

            if (png instanceof ImageFile && jpeg instanceof ImageFile) {
                System.out.println(YELLOW + "File paths before moving:" + RESET);
                System.out.println(YELLOW + ((ImageFile) png).METADATA().absoluteFilePath() + RESET);
                System.out.println(YELLOW + ((ImageFile) jpeg).METADATA().absoluteFilePath() + RESET);
            }

            // Act
            try {
                png.moveTo("src/test/resources/testDirectory");
            } catch (InvalidPathException e) {
                System.out.println(e.getMessage());
            }

            try {
                jpeg.moveTo("src/test/resources/testDirectory");
            } catch (InvalidPathException e) {
                System.out.println(e.getMessage());
            }

            FileSystemResource newPng = new ImageFile("src/test/resources/testDirectory/redPNG100x200.png");
            FileSystemResource newJpeg = new ImageFile("src/test/resources/testDirectory/greenJPEG.jpeg");
            if (png instanceof ImageFile && jpeg instanceof ImageFile) {
                System.out.println(GREEN + "File paths after moving:" + RESET);
                System.out.println(GREEN + ((ImageFile) newPng).METADATA().absoluteFilePath() + RESET);
                System.out.println(GREEN + ((ImageFile) newJpeg).METADATA().absoluteFilePath() + RESET);
            }
        }

        /**
         * Cleans up the moveTo test by moving the files back to their original locations.
         * moveTo should move files from the "testDirectory" directory back to the "resources" directory.
         */
        public static void moveToCleanUp() {
            FileSystemResource newPng = new ImageFile("src/test/resources/testDirectory/redPNG100x200.png");
            FileSystemResource newJpeg = new ImageFile("src/test/resources/testDirectory/greenJPEG.jpeg");
            try {
                newPng.moveTo("src/test/resources");
            } catch (InvalidPathException e) {
                System.out.println(e.getMessage());
            }

            try {
                newJpeg.moveTo("src/test/resources");
            } catch (InvalidPathException e) {
                System.out.println(e.getMessage());
            }
        }

        /**
         * Tests the getContentType method of the FileSystemResource interface.
         * getContentType should output the type of the file system resource.
         */
        public static void getContentType() {
            System.out.println();
            System.out.println(CYAN + "getContentType" + RESET);
            System.out.println(CYAN + "-------------" + RESET);
            // Arrange
            FileSystemResource[] resources = {
                new ImageFile("src/test/resources/bluePNG.png"),
                new SystemDirectory("src/test/resources")
            };

            // Act
            for (FileSystemResource resource : resources) {
                System.out.println(resource.getContentType());
            }
        }
    }
}
