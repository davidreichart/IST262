package controller.filedata;

import model.ApplicationContext;
import model.data.filetypes.ImageFile;
import model.data.filetypes.SystemDirectory;
import model.data.filetypes.SystemFile;
import view.ApplicationJFrame;
import view.filebrowser.UserFileJTree;
import view.filebrowser.nodes.DirectoryNode;
import view.filebrowser.nodes.ImageNode;
import view.filedata.FileStatisticsJPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.Objects;

public class FileStatisticsTableController {

    private ApplicationJFrame frame;
    private ApplicationContext context;
    private FileStatisticsJPanel fileStatisticsJPanel;
    private UserFileJTree userFileJTree;

    public FileStatisticsTableController(ApplicationJFrame frame, ApplicationContext context,
                                         FileStatisticsJPanel fileStatisticsJPanel, UserFileJTree userFileJTree) {
        this.frame = frame;
        this.context = context;
        this.fileStatisticsJPanel = fileStatisticsJPanel;
        this.userFileJTree = userFileJTree;
        attachListeners();
    }

    private void attachListeners() {
        userFileJTree.addTreeSelectionListener(changeMetadataTablesOnValueChanged());
    }

    public TreeSelectionListener changeMetadataTablesOnValueChanged() {
        return e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) userFileJTree.getLastSelectedPathComponent();
            if (Objects.isNull(selectedNode)) return;

            if (selectedNode instanceof ImageNode) {
                enableControls();
                renderMetadataTables(selectedNode);
            } else if (selectedNode instanceof DirectoryNode) {
                disableControls();
                renderMetadataTables(selectedNode);
            }
            fileStatisticsJPanel.repaint();
            fileStatisticsJPanel.revalidate();
        };
    }

    private void enableControls() {
        fileStatisticsJPanel.getEditFilePathButton().setEnabled(true);
        fileStatisticsJPanel.getEditFileSizeButton().setEnabled(true);
        fileStatisticsJPanel.getEditImageHeightButton().setEnabled(true);
        fileStatisticsJPanel.getEditImageWidthButton().setEnabled(true);
    }

    private void disableControls() {
        fileStatisticsJPanel.getEditFilePathButton().setEnabled(false);
        fileStatisticsJPanel.getEditFileSizeButton().setEnabled(false);
        fileStatisticsJPanel.getEditImageHeightButton().setEnabled(false);
        fileStatisticsJPanel.getEditImageWidthButton().setEnabled(false);
    }

    private void renderMetadataTables(DefaultMutableTreeNode node) {
        fileStatisticsJPanel.removeAll();
        fileStatisticsJPanel.add(fileStatisticsJPanel.getControlsPanel(), BorderLayout.WEST);
        JPanel tablesPanel = new JPanel(new GridLayout(1, 2)); // Use GridLayout with 1 row and 2 columns
        tablesPanel.add(buildImageMetadataTable(node));
        tablesPanel.add(buildDirectoryMetadataTable(node));
        fileStatisticsJPanel.add(tablesPanel, BorderLayout.CENTER);
        fileStatisticsJPanel.revalidate();
    }

    private JTable buildImageMetadataTable(DefaultMutableTreeNode node) {
        // nothing to render
        if (!(node instanceof ImageNode)) {
            return new JTable(5, 2);
        }

        JTable imageMDTable = new JTable(5, 2);

        // file context to render
        ImageFile file = ((ImageNode) node).getImageFile();
        SystemFile.Metadata MD = file.METADATA();
        ImageFile.ImageMetadata IMD = file.IMAGE_METADATA();

        // widen the key/label column
        imageMDTable.getColumnModel()
                .getColumn(0).setMaxWidth(400);

        // absolute file path
        imageMDTable.setValueAt("File Path", 0, 0);
        imageMDTable.setValueAt(MD.absoluteFilePath(), 0, 1);
        // file size in kb
        imageMDTable.setValueAt("File Size (bytes)", 1, 0);
        imageMDTable.setValueAt(MD.byteCount(), 1, 1);
        // dimensions
        imageMDTable.setValueAt("Image Dimensions", 2, 0);
        imageMDTable.setValueAt(IMD.width() + "x" + IMD.height(), 2, 1);
        // pixel count
        imageMDTable.setValueAt("Pixel Count", 3, 0);
        imageMDTable.setValueAt(IMD.pixelCount(), 3, 1);

        return imageMDTable;
    }

    private JTable buildDirectoryMetadataTable(DefaultMutableTreeNode node) {
        SystemDirectory directory = null;
        if (node instanceof ImageNode) {
            String directoryPath = ((ImageNode) node).parentPath();
            // find the corresponding system directory object for this image file
            for (SystemDirectory contextDirectory : context.getSystemDirectoryList().getSystemDirectories()) {
                if (contextDirectory.directoryImageFiles().contains(((ImageNode) node).getImageFile())) {
                    directory = contextDirectory;
                    break;
                }
            }
            // was the requested directory never found? nothing to render
            if (Objects.isNull(directory)) {
                System.out.println("NULL");
                return new JTable(5, 2);
            }
        } else if (node instanceof DirectoryNode) {
            // directory nodes have direct access to the relevant directory object
            directory = ((DirectoryNode) node).getSystemDirectory();
        } else {
            // nothing to render
            return new JTable(5, 2);
        }

        JTable directoryMDTable = new JTable(5, 2);
        SystemDirectory.Metadata MD = directory.METADATA();

        // widen the key/label column
        directoryMDTable.getColumnModel()
                .getColumn(0).setMaxWidth(400);

        // file count
        directoryMDTable.setValueAt("File Count", 0, 0);
        directoryMDTable.setValueAt(MD.fileCount(), 0, 1);
        // image count
        directoryMDTable.setValueAt("Image Count", 1, 0);
        directoryMDTable.setValueAt(MD.imageCount(), 1, 1);
        //folder count
        directoryMDTable.setValueAt("Folder Count", 2, 0);
        directoryMDTable.setValueAt(MD.folderCount(), 2, 1);

        return directoryMDTable;
    }
}
