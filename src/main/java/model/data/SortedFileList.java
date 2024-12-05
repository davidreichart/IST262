package model.data;

import model.ApplicationContext;
import model.data.filetypes.ImageFile;
import model.data.filetypes.SystemFile;
import model.util.FileInspector;
import model.util.ImageInspector;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortedFileList {

    private ArrayList<String> fileNames;
    private ArrayList<String> absoluteFilePaths;
    private ApplicationContext context;

    public SortedFileList(ApplicationContext context) {
        this.fileNames = new ArrayList<>();
        this.absoluteFilePaths = new ArrayList<>();
        this.context = context;
        buildTestData();
        sortFiles();
    }

    /**
     * Creates test data to be added and displayed in the file list.
     * Test data is located at {@code serTest} in the file directory of this project.
     */
    public void buildTestData() {
        ArrayList<File> testData = new ArrayList<>();
        File[] testDataOne = new File("serTest").listFiles();
        File[] testDataTwo = new File("src/test/resources").listFiles();
        assert testDataOne != null;
        testData.addAll(List.of(testDataOne));
        assert testDataTwo != null;
        testData.addAll(List.of(testDataTwo));
        for (File data : testData) {
            if (data.isFile() && FileInspector.isImageFile(data)) {
                context.addNewSystemFile(new ImageFile(data.getAbsolutePath()));
                this.fileNames.add(data.getName());
                this.absoluteFilePaths.add(data.getAbsolutePath());
            }
        }
    }

    /**
     * Prints all files currently being tracked (by name) to the command line.
     */
    public void printToCommandLine() {
        // can't print without files
        if (this.fileNames.isEmpty()) {
            System.err.println("Nothing to print.");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fileNames.size(); i++) {
            if (i % 5 ==  0) sb.append("\n");
            sb.append(fileNames.get(i)).append(" ");
        }
        System.out.println("File names:");
        System.out.println(sb.toString());
    }

    /**
     * Adds the file located at the given absolute file path to the {@link ApplicationContext} system file list and
     * {@link SortedFileList} name lists.
     * @param newItem The absolute file path leading to the resource to add.
     * @throws IllegalArgumentException If the given String does not lead to a file, is empty, or does not lead to
     * an image file.
     */
    public void addItem(String newItem) throws IllegalArgumentException {
        File toAdd = new File(newItem);
        // we need a valid image file
        if (!toAdd.isFile() || !FileInspector.isImageFile(toAdd)) {
            throw new IllegalArgumentException("The path provided either is not a file or not an image file.");
        }
        // can't process empty input
        if (newItem.isEmpty()) {
            throw new IllegalArgumentException("Cannot process empty input.");
        }
        // can't add duplicates
        if (this.fileNames.contains(toAdd.getName())) {
            throw new IllegalArgumentException("The requested file is already being tracked.");
        }

        // add the new file to the context
        context.addNewSystemFile(new ImageFile(toAdd.getAbsolutePath()));
        // add the new file to lists
        this.fileNames.add(toAdd.getName());
        this.absoluteFilePaths.add(toAdd.getAbsolutePath());
        sortFiles();
    }

    /**
     * Removes the requested file from the tracking lists in the {@link ApplicationContext} system file list and
     * {@link SortedFileList} name lists.
     * @param itemToRemove The absolute file path of the file to remove.
     * @throws IllegalArgumentException If the input string does not lead to a tracked file.
     */
    public void removeItem(String itemToRemove) throws IllegalArgumentException {
        File toRemove = new File(itemToRemove);
        // we need a valid image file
        if (!toRemove.isFile() || !FileInspector.isImageFile(toRemove)) {
            throw new IllegalArgumentException("The path provided either is not a file or not an image file.");
        }
        // can't process empty input
        if (itemToRemove.isEmpty()) {
            throw new IllegalArgumentException("Cannot process empty input.");
        }
        // can't remove something that isn't there
        if (!this.fileNames.contains(toRemove.getName())) {
            throw new IllegalArgumentException("The requested file is not currently being tracked.");
        }

        // remove and restore sort order
        this.fileNames.remove(toRemove.getName());
        this.absoluteFilePaths.remove(itemToRemove);
        context.removeSystemFile(new ImageFile(toRemove.getAbsolutePath()));
        sortFiles();
    }

    public boolean getItem(String searchTerm) {
        if (this.fileNames.contains(searchTerm)) {
            return true;
        } else if (this.absoluteFilePaths.contains(searchTerm)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sorts the list of file names in ascending lexicographical order.
     */
    public void sortFiles() {
        Collections.sort(this.absoluteFilePaths);
        Collections.sort(this.fileNames);
    }

    /**
     * Extracts all file names from the given list of SystemFiles and adds them to the list of file names.
     * @param systemFiles The list of SystemFiles to extract file names from.
     */
    public void findAllFileNames(ArrayList<SystemFile> systemFiles) {
        for (SystemFile systemFile : systemFiles) {
            if (fileNames.contains(systemFile.METADATA().fileName())) {
                continue;
            }
            this.fileNames.add(systemFile.METADATA().fileName());
            this.absoluteFilePaths.add(systemFile.METADATA().absoluteFilePath());
        }
    }

    /**
     * Returns the list of file names currently stored in this SortedFileList.
     * @return The list of file names.
     */
    public ArrayList<String> getFileNames() {
        return fileNames;
    }

    /**
     * Returns the list of absolute file paths currently stored in this SortedFileList.
     * @return The list of absolute file paths.
     */
    public ArrayList<String> getAbsoluteFilePaths() {
        return absoluteFilePaths;
    }
}
