package view.filebrowser;

import model.context.DirectoryListListener;
import model.context.ObservableDirectoryList;
import model.context.UserDirectory;
import model.data.UserFile;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;
import java.util.HashSet;

public class UserFileJTree extends JTree {

    private DefaultMutableTreeNode rootNode;
    private HashSet<String> currentlyStoredDirectories;

    public UserFileJTree() {
        currentlyStoredDirectories = new HashSet<>();
        setModel(new DefaultTreeModel(rootNode = new DefaultMutableTreeNode("Root")));
        setShowsRootHandles(false);
        addDirectoryNode(new UserDirectory(new File("C:\\Users\\Snaxx\\Pictures")));
    }

    /**
     * Adds a directory node to the JTree.
     * The directory node will contain all the files in the directory.
     * todo: all files are added. this app only cares about images.
     * @param userDirectory The directory to add to the JTree.
     */
    public void addDirectoryNode(UserDirectory userDirectory) {
        // only add new directories to the tree
        // keep track of the directories that have already been added
        if (currentlyStoredDirectories.contains(userDirectory.getDirectoryPath().getAbsolutePath())) {
            return;
        } else {
            currentlyStoredDirectories.add(userDirectory.getDirectoryPath().getAbsolutePath());
        }

        DefaultMutableTreeNode directoryNode = new DefaultMutableTreeNode(userDirectory.getName());

        for (UserFile file : userDirectory.getDirectoryFiles()) {
            String fileName = file.getFile().getName();
            DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(fileName);
            directoryNode.add(fileNode);
        }

        rootNode.add(directoryNode);
        ((DefaultTreeModel) getModel()).reload();
    }

    public HashSet<String> getCurrentlyStoredDirectories() {
        return currentlyStoredDirectories;
    }
}
