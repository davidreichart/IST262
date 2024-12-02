import controller.ApplicationController;
import model.ApplicationContext;
import model.data.SortedFileList;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        SwingUtilities.invokeLater(app);
        m08_a01(app);
    }

    // resources for m08_a01 tests
    public static String spacer = "===================";
    public static String toAdd = "img.png";
    public static Path path = Paths.get(toAdd);
    public static String toRemove = "serTest/circle.png";
    public static Path path2 = Paths.get(toRemove);
    public static String toGet = "serTest/square.png";
    public static Path path3 = Paths.get(toGet);

    /**
     * Paths.get(...) should be grabbing the absolute file path leading to the relative file path of each test image.
     * These tests are meant to mimic the behavior of the SortedFileList class as it acts within the swing GUI
     * by using the terminal as the GUI.
     * @param app The application to test.
     */
    public static void m08_a01(App app) {
        System.out.println("\n" + spacer + "m08_a01" + spacer);

        testAdd(app);
        testRemove(app);
        testGet(app);
    }

    public static void testAdd(App app) {
        // resources
        ApplicationController controller = app.getApplicationController();
        SortedFileList sortedFileList = controller.getSortedFileBrowserJPanelController().getSortedFileList();
        ArrayList<String> fileNames = sortedFileList.getFileNames();

        System.out.println("\n" + spacer + "Testing add" + spacer);

        System.out.println("Before adding " + toAdd + ": " + fileNames.contains("img.png"));
        System.out.println("\033[31mShould be false\033[0m");
        sortedFileList.addItem(path.toAbsolutePath().toString());
        System.out.println("After adding " + toAdd + ": " + fileNames.contains("img.png"));
        System.out.println("\033[32mShould be true\033[0m");
    }

    public static void testRemove(App app) {
        // resources
        ApplicationController controller = app.getApplicationController();
        SortedFileList sortedFileList = controller.getSortedFileBrowserJPanelController().getSortedFileList();
        ArrayList<String> fileNames = sortedFileList.getFileNames();

        System.out.println("\n" + spacer + "Testing remove" + spacer);

        System.out.println("Before removing " + toRemove + ": " + fileNames.contains("circle.png"));
        System.out.println("\033[32mShould be true\033[0m");
        sortedFileList.removeItem(path2.toAbsolutePath().toString());
        System.out.println("After removing " + toRemove + ": " + fileNames.contains("circle.png"));
        System.out.println("\033[31mShould be false\033[0m");
    }

    public static void testGet(App app) {
        // resources
        ApplicationController controller = app.getApplicationController();
        SortedFileList sortedFileList = controller.getSortedFileBrowserJPanelController().getSortedFileList();
        ArrayList<String> fileNames = sortedFileList.getFileNames();

        System.out.println("\n" + spacer + "Testing get" + spacer);

        System.out.println("Trying to get " + toAdd + ": " + sortedFileList.getItem(toAdd));
        System.out.println("\033[32mShould be true\033[0m");
        System.out.println("Trying to get " + toRemove + ": " + sortedFileList.getItem(toRemove));
        System.out.println("\033[31mShould be false\033[0m");
        System.out.println("Trying to get " + "notInList.png" + ": " + sortedFileList.getItem("notInList.png"));
        System.out.println("\033[31mShould be false\033[0m");
        System.out.println("Trying to get " + toGet + ": " + sortedFileList.getItem(path3.toAbsolutePath().toString()));
        System.out.println("\033[32mShould be true\033[0m");

        System.out.println("\n" + spacer + "End of m08_a01" + spacer);
        sortedFileList.printToCommandLine();
    }
}
