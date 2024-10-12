package view.filebrowser;

import model.data.filetypes.ImageFile;
import model.data.filetypes.SystemDirectory;
import view.filebrowser.nodes.DirectoryNode;
import view.filebrowser.nodes.ImageNode;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashSet;

public class UserFileJTree extends JTree {

    private DefaultMutableTreeNode rootNode;
    private HashSet<String> currentlyStoredDirectories;

    public UserFileJTree() {
        currentlyStoredDirectories = new HashSet<>();
        setModel(new DefaultTreeModel(rootNode = new DefaultMutableTreeNode("Root")));
        setShowsRootHandles(false);
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
        ((DefaultTreeModel) getModel()).reload();
    }

    public HashSet<String> getCurrentlyStoredDirectories() {
        return currentlyStoredDirectories;
    }
}
