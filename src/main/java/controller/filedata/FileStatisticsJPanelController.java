package controller.filedata;

import model.ApplicationContext;
import model.data.filetypes.ImageFile;
import view.ApplicationJFrame;
import view.filebrowser.UserFileJTree;
import view.filebrowser.nodes.ImageNode;
import view.filedata.FileStatisticsJPanel;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
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

                if (node instanceof ImageNode) {
                    node = (ImageNode) node;
                    fileStatisticsJPanel.removeAll();
                    fileStatisticsJPanel.add(imageMetadataTable((ImageNode) node));
                    fileStatisticsJPanel.revalidate();
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

    public ActionListener editDataButtonActionListener() {
        return e -> {

        };
    }
}
