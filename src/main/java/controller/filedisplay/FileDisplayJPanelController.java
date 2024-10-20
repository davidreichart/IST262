package controller.filedisplay;

import model.ApplicationContext;
import model.data.filetypes.SystemDirectory;
import view.ApplicationJFrame;
import view.filebrowser.UserFileJTree;
import view.filebrowser.nodes.DirectoryNode;
import view.filedisplay.FileDisplayJPanel;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileDisplayJPanelController {

    private ApplicationJFrame frame;
    private ApplicationContext context;
    private FileDisplayJPanel fileDisplayJPanel;
    private UserFileJTree userFileJTree;
    private String lastCheckedDirectoryPath = "";

    public FileDisplayJPanelController(ApplicationJFrame frame, ApplicationContext context) {
        this.frame = frame;
        this.context = context;
        this.fileDisplayJPanel = frame.getFileDisplayJPanel();
        this.userFileJTree = frame.getFileBrowserJPanel().getFileTree();

        userFileJTree.addTreeSelectionListener(displayFilesInSelectedDirectory());
    }

    public TreeSelectionListener displayFilesInSelectedDirectory() {
        return new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) userFileJTree.getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }

                if (node instanceof DirectoryNode) {
                    String directoryPath = ((DirectoryNode) node).getSystemDirectory().directoryPath();
                    if (lastCheckedDirectoryPath.equals(directoryPath)) {
                        return;
                    } else {
                        lastCheckedDirectoryPath = directoryPath;
                        renderFiles(((DirectoryNode) node).getSystemDirectory());
                    }
                }
            }
        };
    }

    /**
     * Clears any existing rendered content from the fileDisplayJPanel and replaces it with a new JScrollPane
     * containing a JList of ImageIcons for all images in the last-checked directory.
     * If the last checked directory path is null or empty, no JScrollPane is created and nothing is removed from the panel.
     */
    public void renderFiles(SystemDirectory directory) {
        // create a JScrollPane holding a JList of ImageIcons for all images in the given directory
        fileDisplayJPanel.updateCurrentlyRenderedImages(directory.directoryImageFiles());
    }
}
