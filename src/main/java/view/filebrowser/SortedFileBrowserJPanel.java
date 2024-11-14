package view.filebrowser;

import model.data.filetypes.SystemDirectory;
import model.data.filetypes.SystemDirectoryListListener;
import view.Renderable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class SortedFileBrowserJPanel extends JPanel implements Renderable, SystemDirectoryListListener {

    private JList<String> fileList;
    private DefaultListModel<String> listModel;

    public SortedFileBrowserJPanel() {
        setAttributes();
        buildComponents();
        addComponents();

        setVisible(true);
    }

    /**
     * Method to be called when the list of SystemDirectories is updated.
     * Any logic that needs to respond to the list of SystemDirectories being updated should be placed here.
     *
     * @param systemDirectories The updated list of SystemDirectories.
     */
    @Override
    public void systemDirectoriesListUpdated(HashSet<SystemDirectory> systemDirectories) {

    }

    /**
     * Sets the default style and behavior of this Renderable object.
     */
    @Override
    public void setAttributes() {
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setLayout(new BorderLayout());
    }

    /**
     * Attaches JComponent objects to this Renderable object.
     * {@link #buildComponents()} should be before this method.
     */
    @Override
    public void addComponents() {
        add(new JScrollPane(fileList), BorderLayout.CENTER);
        add(new JLabel("Sorted Files"), BorderLayout.NORTH);
    }

    /**
     * Builds the JComponent objects attached to this Renderable object.
     * {@link #addComponents()} should be called after this method.
     */
    @Override
    public void buildComponents() {
        this.listModel = new DefaultListModel<>();
        this.fileList = new JList<>(listModel);
    }

    /**
     * Appends all the file names to the list model.
     * This method expects to receive a list of file names that are already sorted and will not alter the order.
     * @param sortedFileNames The list of file names to append to the list model.
     */
    public void updateFileList(ArrayList<String> sortedFileNames) {
        listModel.clear();

        for (String fileName : sortedFileNames) {
            listModel.addElement(fileName);
        }
    }

    /**
     * Returns the JList object that displays the sorted file names.
     * @return The JList object that displays the sorted file names.
     */
    public JList<String> getFileList() {
        return fileList;
    }
}
