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

                    JTable fileMetadataTable = new JTable(2, 1);
                    fileMetadataTable.setValueAt("File Path: " + ((FileNode) node).getSystemFile().METADATA().absoluteFilePath(), 0, 0);
                    fileMetadataTable.setValueAt("Pixel Count: " + ((ImageFile) ((FileNode) node).getSystemFile()).IMAGE_METADATA().pixelCount(), 1, 0);
                    fileStatisticsJPanel.add(fileMetadataTable);
                    fileStatisticsJPanel.revalidate();
                }


            }
        };
    }
}
