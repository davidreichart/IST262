package view.filebrowser.nodes;

import model.data.filetypes.ImageFile;
import model.data.filetypes.SystemDirectory;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

/**
 * A node representing an image file in the file browser.
 * Contains a reference to the image file associated with the node.
 */
public class ImageNode extends DefaultMutableTreeNode {

    private ImageFile imageFile;

    public ImageNode(ImageFile imageFile) {
        super(imageFile.METADATA().fileName());
        this.imageFile = imageFile;
    }

    /**
     * Returns a reference to the image file associated with the node.
     * @return the image file associated with the node
     */
    public ImageFile getImageFile() {
        return imageFile;
    }

    public String parentPath() {
        File imageFile = new File(this.imageFile.METADATA().absoluteFilePath());
        return imageFile.getParent();
    }
}
