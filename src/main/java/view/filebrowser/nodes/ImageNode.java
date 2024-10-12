package view.filebrowser.nodes;

import model.data.filetypes.ImageFile;

import javax.swing.tree.DefaultMutableTreeNode;

public class ImageNode extends DefaultMutableTreeNode {

    private ImageFile imageFile;

    public ImageNode(ImageFile imageFile) {
        super(imageFile.METADATA().fileName());
        this.imageFile = imageFile;
    }

    public ImageFile getImageFile() {
        return imageFile;
    }
}
