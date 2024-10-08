package controller.filedata;

import model.ApplicationContext;
import model.data.filetypes.ImageFile;
import view.ApplicationJFrame;
import view.filebrowser.FileNode;
import view.filebrowser.UserFileJTree;
import view.filedata.FileStatisticsJPanel;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileStatisticsJPanelController {

    private ApplicationJFrame frame;
    private ApplicationContext context;
    private FileStatisticsJPanel fileStatisticsJPanel;
    private UserFileJTree userFileJTree;

    public FileStatisticsJPanelController(ApplicationJFrame frame, ApplicationContext context) {
        this.frame = frame;
        this.context = context;
        this.fileStatisticsJPanel = frame.getFileStatisticsJPanel();
        this.userFileJTree = frame.getFileBrowserJPanel().getFileTree();

        fileStatisticsJPanel.add(new JLabel("File Statistics"));
        userFileJTree.addTreeSelectionListener(displayFileMetadataInFileStatisticsJPanel());
    }

    public TreeSelectionListener displayFileMetadataInFileStatisticsJPanel() {
        return new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) userFileJTree.getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }
                // if the current node is a FileNode AND the system file is an ImageFile
                // we can cast the node to get access to all metadata for the file
                if (node instanceof FileNode && ((FileNode) node).getSystemFile() instanceof ImageFile) {
                    node = (FileNode) node;
                    fileStatisticsJPanel.removeAll();
                    fileStatisticsJPanel.add(imageMetadataTable((FileNode) node));
                    fileStatisticsJPanel.revalidate();
                }
            }
        };
    }

    public JTable imageMetadataTable(FileNode node) {
        // this JTable needs ImageFiles to get access to all metadata
        if (!(node.getSystemFile() instanceof  ImageFile)) {
            return null;
        }

        JTable fileMetadataTable = new JTable(5, 2);
        fileMetadataTable.getColumnModel().getColumn(0).setMaxWidth(400); // the first column is the key

        fileMetadataTable.setValueAt("File Path", 0, 0);
        fileMetadataTable.setValueAt(node.getSystemFile().METADATA().absoluteFilePath(), 0, 1);

        fileMetadataTable.setValueAt("File Size (kb)", 1, 0);
        fileMetadataTable.setValueAt(node.getSystemFile().METADATA().kilobyteCount(), 1, 1);

        fileMetadataTable.setValueAt("Image Dimensions", 2, 0);
        fileMetadataTable.setValueAt(((ImageFile) node.getSystemFile()).IMAGE_METADATA().width() + " x " + ((ImageFile) node.getSystemFile()).IMAGE_METADATA().height(), 2, 1);

        fileMetadataTable.setValueAt("Pixel Count", 3, 0);
        fileMetadataTable.setValueAt(((ImageFile) node.getSystemFile()).IMAGE_METADATA().pixelCount(), 3, 1);
        return fileMetadataTable;
    }
}
