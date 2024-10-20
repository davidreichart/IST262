package view.filebrowser.nodes;

import model.data.filetypes.SystemDirectory;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Represents a directory node in the file browser tree.
 * A reference to the SystemDirectory object is stored in the node.
 */
public class DirectoryNode extends DefaultMutableTreeNode {

    private SystemDirectory systemDirectory;

    public DirectoryNode(SystemDirectory systemDirectory) {
        super(systemDirectory.directoryPath());
        this.systemDirectory = systemDirectory;
    }

    /**
     * Returns the SystemDirectory object associated with this node.
     * @return the SystemDirectory object associated with this node
     */
    public SystemDirectory getSystemDirectory() {
        return systemDirectory;
    }
}
