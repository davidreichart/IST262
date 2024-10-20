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

    public JTable imageMetadataTable(ImageNode node) {
        ImageFile imageFile = node.getImageFile();

        JTable fileMetadataTable = new JTable(5, 2);
        fileMetadataTable.getColumnModel().getColumn(0).setMaxWidth(400); // the first column is the key

        fileMetadataTable.setValueAt("File Path", 0, 0);
        fileMetadataTable.setValueAt(imageFile.METADATA().absoluteFilePath(), 0, 1);

        fileMetadataTable.setValueAt("File Size (kb)", 1, 0);
        fileMetadataTable.setValueAt(imageFile.METADATA().kilobyteCount(), 1, 1);

        fileMetadataTable.setValueAt("Image Dimensions", 2, 0);
        fileMetadataTable.setValueAt(imageFile.IMAGE_METADATA().width() + " x " + imageFile.IMAGE_METADATA().height(), 2, 1);

        fileMetadataTable.setValueAt("Pixel Count", 3, 0);
        fileMetadataTable.setValueAt(imageFile.IMAGE_METADATA().pixelCount(), 3, 1);
        return fileMetadataTable;
    }

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

    public ActionListener editFileSizeButtonActionListener() {
        return e -> {
            DefaultMutableTreeNode node = getSelectedNode();
            if (node instanceof ImageNode) {
                ImageFile imageFile = ((ImageNode) node).getImageFile();
                String input = JOptionPane.showInputDialog("Enter a new file size for this object:");
                // only the core metadata record needs to be changed
                if (input != null) {
                    SystemFile.Metadata updatedMetadata = new SystemFile.Metadata(Integer.parseInt(input), imageFile.METADATA().absoluteFilePath());
                    imageFile.setMETADATA(updatedMetadata);
                    renderImageMetadataTable((ImageNode) node);
                }
            }
        };
    }

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

    private DefaultMutableTreeNode getSelectedNode() {
        return (DefaultMutableTreeNode) userFileJTree.getLastSelectedPathComponent();
    }

    private void enableAllEditButtons() {
        fileStatisticsJPanel.getEditFilePathButton().setEnabled(true);
        fileStatisticsJPanel.getEditFileSizeButton().setEnabled(true);
        fileStatisticsJPanel.getEditImageHeightButton().setEnabled(true);
        fileStatisticsJPanel.getEditImageWidthButton().setEnabled(true);
    }

    private void disableAllEditButtons() {
        fileStatisticsJPanel.getEditFilePathButton().setEnabled(false);
        fileStatisticsJPanel.getEditFileSizeButton().setEnabled(false);
        fileStatisticsJPanel.getEditImageHeightButton().setEnabled(false);
        fileStatisticsJPanel.getEditImageWidthButton().setEnabled(false);
    }

    private void renderImageMetadataTable(ImageNode node) {
        fileStatisticsJPanel.removeAll();
        fileStatisticsJPanel.add(fileStatisticsJPanel.getControlsPanel(), BorderLayout.WEST);
        fileStatisticsJPanel.add(imageMetadataTable(node), BorderLayout.CENTER);
        fileStatisticsJPanel.revalidate();
    }
}
