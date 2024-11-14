package model.data;

import model.data.filetypes.SystemFile;

import java.util.ArrayList;
import java.util.Collections;

public class SortedFileList {

    private ArrayList<String> fileNames;

    public SortedFileList() {
        this.fileNames = new ArrayList<>();
    }

    /**
     * Sorts the list of file names in ascending lexicographical order.
     */
    public void sortFiles() {
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
        }
    }

    /**
     * Returns the list of file names currently stored in this SortedFileList.
     * @return The list of file names.
     */
    public ArrayList<String> getFileNames() {
        return fileNames;
    }
}
