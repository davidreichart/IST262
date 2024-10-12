package view.filebrowser.nodes;

import model.data.filetypes.SystemDirectory;

import javax.swing.tree.DefaultMutableTreeNode;

public class DirectoryNode extends DefaultMutableTreeNode {

    private SystemDirectory systemDirectory;

    public DirectoryNode(SystemDirectory systemDirectory) {
        super(systemDirectory.directoryPath());
        this.systemDirectory = systemDirectory;
    }

    public SystemDirectory getSystemDirectory() {
        return systemDirectory;
    }
}
