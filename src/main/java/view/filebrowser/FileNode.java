package view.filebrowser;

import model.data.filetypes.ImageFile;
import model.data.filetypes.SystemFile;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class FileNode extends DefaultMutableTreeNode {

    private SystemFile systemFile;

    public FileNode(SystemFile systemFile) {
        super(new File(systemFile.METADATA().absoluteFilePath()).getName());

        if (systemFile instanceof ImageFile) {
            this.systemFile = (ImageFile) systemFile;
        } else {
            this.systemFile = systemFile;
        }
    }

    public SystemFile getSystemFile() {
        return systemFile;
    }
}
