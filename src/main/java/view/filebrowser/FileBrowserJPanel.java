package view.filebrowser;

import model.data.filetypes.SystemDirectory;
import model.data.filetypes.SystemDirectoryListListener;
import view.Renderable;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * The file browser JPanel that displays the list of files and directories in the FileBrowser.
 * This JPanel contains a JTree that displays the list of files and directories in the FileBrowser.
 * Controls are positioned at the top of the JPanel to allow the user to interact with the FileBrowser.
 */
public class FileBrowserJPanel extends JPanel implements Renderable, SystemDirectoryListListener {

    UserFileJTree fileTree;
    JScrollPane fileScrollPane;
    JButton expandAllJButton;
    JButton previousFileButton;
    JButton nextFileButton;
    JButton addNewFileButton;
    JButton refreshListButton;
    JButton deleteSelectedFileButton;

    public FileBrowserJPanel() {
        setAttributes();
        buildComponents();
        addComponents();

        setVisible(true);
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
        add(fileScrollPane, BorderLayout.CENTER);

        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2, 3));
        controls.add(expandAllJButton);
        controls.add(previousFileButton);
        controls.add(nextFileButton);
        controls.add(addNewFileButton);
        controls.add(refreshListButton);
        controls.add(deleteSelectedFileButton);
        add(controls, BorderLayout.NORTH);
    }

    /**
     * Builds the JComponent objects attached to this Renderable object.
     * {@link #addComponents()} should be called after this method.
     */
    @Override
    public void buildComponents() {
        this.fileTree = new UserFileJTree();
        this.fileScrollPane = createFileTreeScrollPane();
        this.expandAllJButton = new JButton("Expand All");
        this.previousFileButton = new JButton("Previous");
        this.nextFileButton = new JButton("Next");
        this.addNewFileButton = new JButton("Add File");
        this.refreshListButton = new JButton("Refresh");
        this.deleteSelectedFileButton = new JButton("Remove");
    }

    public JScrollPane createFileTreeScrollPane() {
        JScrollPane scrollPane = new JScrollPane(fileTree);
        return scrollPane;
    }

    /**
     * Method to be called when the list of SystemDirectories is updated.
     * Any logic that needs to respond to the list of SystemDirectories being updated should be placed here.
     *
     * @param systemDirectories The updated list of SystemDirectories.
     */
    @Override
    public void systemDirectoriesListUpdated(HashSet<SystemDirectory> systemDirectories) {
        for (SystemDirectory directory : systemDirectories) {
            fileTree.addDirectoryNode(directory);
        }
    }


    /**
     * Getter for the fileTree.
     * The fileTree is a JTree that displays the list of files and directories in the FileBrowser.
     * @return The fileTree.
     */
    public UserFileJTree getFileTree() {
        return fileTree;
    }

    /**
     * Getter for the expandAllJButton.
     * The expandAllJButton is a JButton that allows the user to expand all directory nodes in the FileBrowser.
     * @return The expandAllJButton.
     */
    public JButton getExpandAllJButton() {
        return expandAllJButton;
    }

    /**
     * Getter for the previousFileButton.
     * The previousFileButton is a JButton that allows the user to navigate to the previous file in the FileBrowser.
     * This button will only allow navigation between files within the same level of the tree && the same parent (directory).
     * @return The previousFileButton.
     */
    public JButton getPreviousFileButton() {
        return previousFileButton;
    }

    /**
     * Getter for the nextFileButton.
     * The nextFileButton is a JButton that allows the user to navigate to the next file in the FileBrowser.
     * This button will only allow navigation between files within the same level of the tree && the same parent (directory).
     * @return The nextFileButton.
     */
    public JButton getNextFileButton() {
        return nextFileButton;
    }

    /**
     * Getter for the addNewFileButton.
     * The addNewFileButton is a JButton that allows the user to add a new file to the FileBrowser.
     * A file chooser dialog will be opened when the button is clicked for entering the file path.
     * The input file will be added under the "unknown files" directory.
     * @return The addNewFileButton.
     */
    public JButton getAddNewFileButton() {
        return addNewFileButton;
    }

    /**
     * Getter for the refreshListButton.
     * The refreshListButton is a JButton that allows the user to refresh the list of files in the FileBrowser.
     * @return The refreshListButton.
     */
    public JButton getRefreshListButton() {
        return refreshListButton;
    }

    /**
     * Getter for the deleteSelectedFileButton.
     * The deleteSelectedFileButton is a JButton that allows the user to delete the currently selected file.
     * This does not delete the file from the disk, but rather removes it from the list of files in the FileBrowser.
     * @return The deleteSelectedFileButton.
     */
    public JButton getDeleteSelectedFileButton() {
        return deleteSelectedFileButton;
    }
}
