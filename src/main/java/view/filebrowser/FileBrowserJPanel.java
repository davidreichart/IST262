package view.filebrowser;

import model.data.filetypes.SystemDirectory;
import model.data.filetypes.SystemDirectoryListListener;
import view.Renderable;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.HashSet;

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


    public UserFileJTree getFileTree() {
        return fileTree;
    }

    public JButton getExpandAllJButton() {
        return expandAllJButton;
    }

    public JScrollPane getFileScrollPane() {
        return fileScrollPane;
    }

    public JButton getPreviousFileButton() {
        return previousFileButton;
    }

    public JButton getNextFileButton() {
        return nextFileButton;
    }

    public JButton getAddNewFileButton() {
        return addNewFileButton;
    }

    public JButton getRefreshListButton() {
        return refreshListButton;
    }

    public JButton getDeleteSelectedFileButton() {
        return deleteSelectedFileButton;
    }
}
