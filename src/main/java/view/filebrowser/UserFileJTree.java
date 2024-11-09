package view.filebrowser;

import model.data.filetypes.ImageFile;
import model.data.filetypes.SystemDirectory;
import view.filebrowser.nodes.DirectoryNode;
import view.filebrowser.nodes.ImageNode;

import javax.swing.*;
import javax.swing.tree.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * A JTree that displays the user's file system.
 * Mimics the file system structure of the user's computer.
 * Each directory node contains all the image files in the directory.
 * The unknown directory node contains any files added with the "Add File" button.
 */
public class UserFileJTree extends JTree implements Serializable {

    private final DefaultMutableTreeNode rootNode;
    private DefaultMutableTreeNode unknownDirectoryNode = new DirectoryNode(new SystemDirectory("unknown"));
    private HashSet<String> currentlyStoredDirectories;
    private ArrayList<DirectoryNode> currentDirectoryNodes;

    public UserFileJTree() {
        currentlyStoredDirectories = new HashSet<>();
        setModel(new DefaultTreeModel(rootNode = new DefaultMutableTreeNode()));
        // the unknown directory node will hold any files added with the "Add File" button
        rootNode.add(this.unknownDirectoryNode);
        reloadTree();
    }

    /**
     * Adds a directory node to the JTree.
     * The directory node will contain all the files in the directory.
     */
    public void addDirectoryNode(SystemDirectory systemDirectory) {
        // only add new directories to the tree
        // keep track of the directories that have already been added
        if (currentlyStoredDirectories.contains(systemDirectory.directoryPath())) {
            return;
        } else {
            currentlyStoredDirectories.add(systemDirectory.directoryPath());
        }

        DefaultMutableTreeNode directoryNode = new DirectoryNode(systemDirectory);

        // create file nodes with references to each tracked image file
        for (ImageFile imageFile : systemDirectory.directoryImageFiles()) {
            ImageNode imageNode = new ImageNode(imageFile);
            directoryNode.add(imageNode);
        }

        rootNode.add(directoryNode);
        reloadTree();
    }

    /**
     * Calls the reload method of the DefaultTreeModel to update the JTree.
     */
    public void reloadTree() {
        ((DefaultTreeModel) getModel()).reload();
    }

    /**
     * Returns the currently stored directories.
     * This is a list of directories currently displayed in the JTree.
     * The list is of the absolute paths of the directories.
     * @return The currently stored directories.
     */
    public HashSet<String> getCurrentlyStoredDirectories() {
        return currentlyStoredDirectories;
    }

    /**
     * Return the current directory nodes being displayed in the JTree.
     * @return The current directory nodes being displayed in the JTree as DirectoryNode objects.
     */
    public ArrayList<DirectoryNode> getCurrentDirectoryNodes() {
        return currentDirectoryNodes;
    }

    /**
     * Returns a reference to the unknown directory node.
     * This is the node that contains any files added with the "Add File" button.
     * @return The unknown directory node.
     */
    public DefaultMutableTreeNode getUnknownDirectoryNode() {
        return unknownDirectoryNode;
    }
}
