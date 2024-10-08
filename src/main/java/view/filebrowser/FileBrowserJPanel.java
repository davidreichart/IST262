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
        add(expandAllJButton, BorderLayout.NORTH);
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
    }

    public JScrollPane createFileTreeScrollPane() {
        JScrollPane scrollPane = new JScrollPane(fileTree);
        return scrollPane;
    }

    public UserFileJTree getFileTree() {
        return fileTree;
    }

    public JButton getExpandAllJButton() {
        return expandAllJButton;
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
}
