package controller.filedata;

import model.ApplicationContext;
import model.data.filetypes.ImageFile;
import model.data.filetypes.SystemFile;
import view.ApplicationJFrame;
import view.filebrowser.UserFileJTree;
import view.filebrowser.nodes.ImageNode;
import view.filedata.FileStatisticsJPanel;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Controller for the FileStatisticsJPanel in the ApplicationJFrame.
 * The FileStatisticsJPanelController is responsible for the following: <br>
 * - Displaying the metadata of the selected file in the file tree. <br>
 * - Editing the file path of the selected file. <br>
 * - Editing the file size of the selected file. <br>
 * - Editing the image height of the selected file. <br>
 * - Editing the image width of the selected file. <br>
 * The FileStatisticsJPanelController is initialized in the ApplicationJFrameController.
 */
public class FileStatisticsJPanelController {

    private ApplicationJFrame frame;
    private ApplicationContext context;
    private FileStatisticsJPanel fileStatisticsJPanel;
    private UserFileJTree userFileJTree;
    private JTable currentlyActiveTable;

    public FileStatisticsJPanelController(ApplicationJFrame frame, ApplicationContext context) {
        this.frame = frame;
        this.context = context;
        this.fileStatisticsJPanel = frame.getFileStatisticsJPanel();
        this.userFileJTree = frame.getFileBrowserJPanel().getFileTree();

        userFileJTree.addTreeSelectionListener(displayFileMetadataInFileStatisticsJPanel());

        fileStatisticsJPanel.getEditFilePathButton()
                .addActionListener(editFilePathButtonActionListener());

        fileStatisticsJPanel.getEditFileSizeButton()
                .addActionListener(editFileSizeButtonActionListener());

        fileStatisticsJPanel.getEditImageHeightButton()
                .addActionListener(editImageHeightButtonActionListener());

        fileStatisticsJPanel.getEditImageWidthButton()
                .addActionListener(editImageWidthButtonActionListener());
    }

    /**
     * Updates the JTable in the FileStatisticsJPanel with the metadata of the selected file.
     * @return A TreeSelectionListener that updates the JTable in the FileStatisticsJPanel.
     */
    public TreeSelectionListener displayFileMetadataInFileStatisticsJPanel() {
        return new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                // get currently selected node
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) userFileJTree.getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }

                // only update the view if the selected node is for an image file
                if (node instanceof ImageNode) {
                    enableAllEditButtons();
                    JTable table = imageMetadataTable((ImageNode) node);
                    renderImageMetadataTable((ImageNode) node);
                }
            }
        };
    }

    /**
     * Creates a JTable with the metadata of the file attached to the given ImageNode.
     * @param node The selected image file node.
     * @return A JTable with the metadata of the file attached to the given ImageNode.
     */
    public JTable imageMetadataTable(ImageNode node) {
        ImageFile imageFile = node.getImageFile();

        JTable fileMetadataTable = new JTable(5, 2);
        fileMetadataTable.getColumnModel().getColumn(0).setMaxWidth(400); // the first column is the key

        fileMetadataTable.setValueAt("File Path", 0, 0);
        fileMetadataTable.setValueAt(imageFile.METADATA().absoluteFilePath(), 0, 1);

        fileMetadataTable.setValueAt("File Size (kb)", 1, 0);
        fileMetadataTable.setValueAt(imageFile.METADATA().byteCount(), 1, 1);

        fileMetadataTable.setValueAt("Image Dimensions", 2, 0);
        fileMetadataTable.setValueAt(imageFile.IMAGE_METADATA().width() + " x " + imageFile.IMAGE_METADATA().height(), 2, 1);

        fileMetadataTable.setValueAt("Pixel Count", 3, 0);
        fileMetadataTable.setValueAt(imageFile.IMAGE_METADATA().pixelCount(), 3, 1);
        return fileMetadataTable;
    }

    /**
     * Updates the metadata of the selected file with the new file path.
     * The metadata is updated in the model and the JTable is re-rendered.
     * @return An ActionListener that updates the metadata of the selected file with the new file path.
     */
    public ActionListener editFilePathButtonActionListener() {
        return e -> {
            DefaultMutableTreeNode node = getSelectedNode();
            if (node instanceof ImageNode) {
                ImageFile imageFile = ((ImageNode) node).getImageFile();
                String input = JOptionPane.showInputDialog("Enter a new file path for this object:");
                // only the core metadata record needs to be changed
                if (input != null) {
                    SystemFile.Metadata updatedMetadata = new SystemFile.Metadata(imageFile.METADATA().byteCount(), input);
                    imageFile.setMETADATA(updatedMetadata);
                    renderImageMetadataTable((ImageNode) node);
                }
            }
        };
    }

    /**
     * Updates the metadata of the selected file with the new file size.
     * The metadata is updated in the model and the JTable is re-rendered.
     * @return An ActionListener that updates the metadata of the selected file with the new file size.
     */
    public ActionListener editFileSizeButtonActionListener() {
        return e -> {
            DefaultMutableTreeNode node = getSelectedNode();
            if (node instanceof ImageNode) {
                ImageFile imageFile = ((ImageNode) node).getImageFile();
                String input = JOptionPane.showInputDialog("Enter a new file size (in bytes) for this object:");
                // only the core metadata record needs to be changed
                if (input != null) {
                    long newSize = Long.parseLong(input);
                    SystemFile.Metadata updatedMetadata = new SystemFile.Metadata(newSize, imageFile.METADATA().absoluteFilePath());
                    imageFile.setMETADATA(updatedMetadata);
                    renderImageMetadataTable((ImageNode) node);
                }
            }
        };
    }

    /**
     * Updates the metadata of the selected file with the new image height.
     * The metadata is updated in the model and the JTable is re-rendered.
     * @return An ActionListener that updates the metadata of the selected file with the new image height.
     */
    public ActionListener editImageHeightButtonActionListener() {
        return e -> {
            DefaultMutableTreeNode node = getSelectedNode();
            if (node instanceof ImageNode) {
                ImageFile imageFile = ((ImageNode) node).getImageFile();
                String input = JOptionPane.showInputDialog("Enter a new image height for this object:");
                // only the image metadata record needs to be changed
                if (input != null) {
                    ImageFile.ImageMetadata updatedMetadata = new ImageFile.ImageMetadata(imageFile.IMAGE_METADATA().width(), Integer.parseInt(input), imageFile.IMAGE_METADATA().colorHistogram());
                    imageFile.setIMAGE_METADATA(updatedMetadata);
                    renderImageMetadataTable((ImageNode) node);
                }
            }
        };
    }

    /**
     * Updates the metadata of the selected file with the new image width.
     * The metadata is updated in the model and the JTable is re-rendered.
     * @return An ActionListener that updates the metadata of the selected file with the new image width.
     */
    public ActionListener editImageWidthButtonActionListener() {
        return e -> {
            DefaultMutableTreeNode node = getSelectedNode();
            if (node instanceof ImageNode) {
                ImageFile imageFile = ((ImageNode) node).getImageFile();
                String input = JOptionPane.showInputDialog("Enter a new image width for this object:");
                // only the image metadata record needs to be changed
                if (input != null) {
                    ImageFile.ImageMetadata updatedMetadata = new ImageFile.ImageMetadata(Integer.parseInt(input), imageFile.IMAGE_METADATA().height(), imageFile.IMAGE_METADATA().colorHistogram());
                    imageFile.setIMAGE_METADATA(updatedMetadata);
                    renderImageMetadataTable((ImageNode) node);
                }
            }
        };
    }

    /**
     * Returns the currently selected node in the file tree.
     * @return The currently selected node in the file tree.
     */
    private DefaultMutableTreeNode getSelectedNode() {
        return (DefaultMutableTreeNode) userFileJTree.getLastSelectedPathComponent();
    }

    /**
     * Enables all the edit buttons in the FileStatisticsJPanel.
     */
    private void enableAllEditButtons() {
        fileStatisticsJPanel.getEditFilePathButton().setEnabled(true);
        fileStatisticsJPanel.getEditFileSizeButton().setEnabled(true);
        fileStatisticsJPanel.getEditImageHeightButton().setEnabled(true);
        fileStatisticsJPanel.getEditImageWidthButton().setEnabled(true);
    }

    /**
     * Disables all the edit buttons in the FileStatisticsJPanel.
     */
    private void disableAllEditButtons() {
        fileStatisticsJPanel.getEditFilePathButton().setEnabled(false);
        fileStatisticsJPanel.getEditFileSizeButton().setEnabled(false);
        fileStatisticsJPanel.getEditImageHeightButton().setEnabled(false);
        fileStatisticsJPanel.getEditImageWidthButton().setEnabled(false);
    }

    /**
     * Clears the FileStatisticsJPanel and renders a new JTable with the most up-to-date metadata of the selected image file.
     * @param node The selected image file node.
     */
    private void renderImageMetadataTable(ImageNode node) {
        fileStatisticsJPanel.removeAll();
        fileStatisticsJPanel.add(fileStatisticsJPanel.getControlsPanel(), BorderLayout.WEST);
        fileStatisticsJPanel.add(imageMetadataTable(node), BorderLayout.CENTER);
        fileStatisticsJPanel.revalidate();
    }
}
