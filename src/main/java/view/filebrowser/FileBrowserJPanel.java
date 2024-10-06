package view.filebrowser;

import model.context.DirectoryListListener;
import model.context.UserDirectory;
import view.Renderable;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.HashSet;

public class FileBrowserJPanel extends JPanel implements Renderable, DirectoryListListener {

    UserFileJTree fileTree;
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
        setPreferredSize(new java.awt.Dimension(1000, 800));
        setLayout(new BorderLayout());
    }

    /**
     * Attaches JComponent objects to this Renderable object.
     * {@link #buildComponents()} should be before this method.
     */
    @Override
    public void addComponents() {
        add(fileTree);
        add(expandAllJButton, BorderLayout.NORTH);
    }

    /**
     * Builds the JComponent objects attached to this Renderable object.
     * {@link #addComponents()} should be called after this method.
     */
    @Override
    public void buildComponents() {
        this.fileTree = new UserFileJTree();
        this.expandAllJButton = new JButton("Expand All");
    }

    public UserFileJTree getFileTree() {
        return fileTree;
    }

    public JButton getExpandAllJButton() {
        return expandAllJButton;
    }

    /**
     * Called when the directory list has changed.
     * Listeners should update their view of the directory list to match the new list.
     *
     * @param newDirectoryList The new list of directories.
     */
    @Override
    public void directoryListChanged(HashSet<UserDirectory> newDirectoryList) {
        for (UserDirectory directory : newDirectoryList) {
            fileTree.addDirectoryNode(directory);
        }
    }
}
