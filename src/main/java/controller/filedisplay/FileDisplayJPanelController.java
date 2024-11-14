package controller.filedisplay;

import model.ApplicationContext;
import model.data.filetypes.SystemDirectory;
import view.ApplicationJFrame;
import view.filebrowser.UserFileJTree;
import view.filebrowser.nodes.DirectoryNode;
import view.filebrowser.nodes.ImageNode;
import view.filedisplay.FileDisplayJPanel;
import view.filedisplay.ImagePanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Controller for the FileDisplayJPanel in the ApplicationJFrame.
 * The FileDisplayJPanelController is responsible for the following: <br>
 * - Displaying all images in the selected directory. todo: not finished <br>
 * The FileDisplayJPanelController is initialized in the ApplicationJFrameController.
 */
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

        this.frame.getSortedFileBrowserJPanel().getFileList().addListSelectionListener(displayIndividualFile());
    }

    /**
     * Returns a TreeSelectionListener that displays all files in the selected directory.
     * Images are rendered in the fileDisplayJPanel.
     * @return A TreeSelectionListener that displays all files in the selected directory.
     */
    public TreeSelectionListener displayFilesInSelectedDirectory() {
        return new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) userFileJTree.getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }

                // when a directory is selected, render the images in that directory
                // when an image is selected, render all images in its parent directory
                if (node instanceof DirectoryNode) {
                    String directoryPath = ((DirectoryNode) node).getSystemDirectory().directoryPath();
                    // don't rerender an already displayed directory
                    if (lastCheckedDirectoryPath.equals(directoryPath)) {
                        return;
                    } else {
                        lastCheckedDirectoryPath = directoryPath;
                        renderFiles(((DirectoryNode) node).getSystemDirectory());
                    }
                } else if (node instanceof ImageNode) {
                    String directoryPath = ((ImageNode) node).parentPath();
                    // don't rerender an already displayed directory
                    if (lastCheckedDirectoryPath.equals(directoryPath)) {
                        return;
                    } else {
                        lastCheckedDirectoryPath = directoryPath;
                        DirectoryNode parentNode = (DirectoryNode) node.getParent();
                        renderFiles(parentNode.getSystemDirectory());
                    }
                }
            }
        };
    }

    /**
     * Returns a ListSelectionListener that displays the selected image in the fileDisplayJPanel.
     * @return A ListSelectionListener that displays the selected image in the fileDisplayJPanel.
     */
    public ListSelectionListener displayIndividualFile() {
        return (ListSelectionEvent e) -> {
            // duplicate event prevention
            if (e.getValueIsAdjusting()) {
                return;
            }

            JList<String> list = frame.getSortedFileBrowserJPanel().getFileList();
            int selectedIndex = list.getSelectedIndex();
            if (selectedIndex == -1) {
                return;
            }

            // get the absolute path of the selected image and render it alone
            String selection = list.getModel().getElementAt(selectedIndex);
            JPanel content = new JPanel();
            content.add(new ImagePanel(selection, this.fileDisplayJPanel.getSize()));
            this.fileDisplayJPanel.setViewportView(content);
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
