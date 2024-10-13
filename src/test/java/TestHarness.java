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
        /**
         * Tests the instantiation of the FileSystemResource interface and its implementations.
         */
        public static void instantiation() {
            System.out.println("instantiation");
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

        /**
         * Tests the open method of the FileSystemResource interface and its implementations.
         * Directories cannot be opened, so the method will throw an exception.
         */
        public static void open() {
            System.out.println("open");
            // Arrange
            FileSystemResource[] resources = {
                new ImageFile("src/test/resources/bluePNG.png"),
                new SystemDirectory("src/test/resources")
            };

            // Act
            for (FileSystemResource resource : resources) {
                try {
                    resource.open();
                    System.out.println("Opened " + resource.getContentType());
                } catch (FileNotFoundException e) {
                    System.out.println("Failed to open " + resource.getContentType());
                }
            }
        }

        /**
         * Tests the rename method of the FileSystemResource interface and its implementations.
         */
        public static void rename() {
            System.out.println("rename");
            // Arrange
            FileSystemResource png = new ImageFile("src/test/resources/redPNG100x200.png");
            FileSystemResource jpeg = new ImageFile("src/test/resources/greenJPEG.jpeg");

            // Act
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
            System.out.println("moveTo");
            // Arrange
            FileSystemResource png = new ImageFile("src/test/resources/redPNG100x200.png");
            FileSystemResource jpeg = new ImageFile("src/test/resources/greenJPEG.jpeg");

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
            System.out.println("getContentType");
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
