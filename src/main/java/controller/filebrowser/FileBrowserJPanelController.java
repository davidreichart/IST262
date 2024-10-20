package controller.filebrowser;

import model.ApplicationContext;
import model.data.filetypes.ImageFile;
import model.util.FileInspector;
import view.ApplicationJFrame;
import view.filebrowser.UserFileJTree;
import view.filebrowser.nodes.ImageNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Controller for the FileBrowserJPanel in the ApplicationJFrame.
 * The FileBrowserJPanelController is responsible for the following: <br>
 * - Expanding all directories in the file tree. <br>
 * - Moving the selected file to the previous file. <br>
 * - Moving the selected file to the next file. <br>
 * - Adding a new file to the file tree. <br>
 * - Refreshing the file tree. <br>
 * - Deleting the selected file from the file tree. <br>
 * The FileBrowserJPanelController is initialized in the ApplicationJFrameController.
 */
public class FileBrowserJPanelController {

    private ApplicationJFrame frame;
    private ApplicationContext context;
    int lastCheckedDirectoryCount;


    public FileBrowserJPanelController(ApplicationJFrame frame, ApplicationContext context) {
        this.frame = frame;
        this.context = context;
        this.lastCheckedDirectoryCount = 0;

        frame.getFileBrowserJPanel()
                .getExpandAllJButton()
                .addActionListener(expandAllDirectories());

        frame.getFileBrowserJPanel()
                .getPreviousFileButton()
                .addActionListener(moveSelectedToPrevious());

        frame.getFileBrowserJPanel()
                .getNextFileButton()
                .addActionListener(moveSelectedToNext());

        frame.getFileBrowserJPanel()
                .getAddNewFileButton()
                .addActionListener(addNewFile());

        frame.getFileBrowserJPanel()
                .getRefreshListButton()
                .addActionListener(refreshList());

        frame.getFileBrowserJPanel()
                .getDeleteSelectedFileButton()
                .addActionListener(deleteSelectedFile());
    }

    /**
     * Adds an ActionListener that when triggered, expands all directories in the file tree.
     * @return an ActionListener that expands all directories in the file tree.
     */
    public ActionListener expandAllDirectories() {
        return e -> {
            for (int i = 0; i < frame.getFileBrowserJPanel().getFileTree().getRowCount(); i++) {
                frame.getFileBrowserJPanel().getFileTree().expandRow(i);
            }
        };
    }

    /**
     * Adds an ActionListener that when triggered, moves the selected file in the file tree to the previous file.
     * This will ONLY move selection on the same level as the currently selected node. <br>
     * For example: If a directory is selected, the previous directory will be selected.
     * OR;<br>
     * If a file is selected, the previous file will be selected but selection will never move outside the directory.
     * @return an ActionListener that moves the selected file to the previous file.
     */
    public ActionListener moveSelectedToPrevious() {
        return e -> {
            JTree workingTree = frame.getFileBrowserJPanel().getFileTree();

            TreePath currentSelection = workingTree.getSelectionPath();
            // select the first item if none are selected
            // todo: should maybe select the first directory instead of the root node
            if (currentSelection == null) {
                TreePath newSelection = new TreePath(workingTree.getModel().getRoot());
                workingTree.setSelectionPath(newSelection);
                workingTree.scrollPathToVisible(newSelection);
            } else {
                DefaultMutableTreeNode currentSelectedNode = (DefaultMutableTreeNode) currentSelection.getLastPathComponent();
                DefaultMutableTreeNode previousNode = (DefaultMutableTreeNode) currentSelectedNode.getPreviousSibling();
                if (previousNode != null) {
                    TreePath newSelection = currentSelection.getParentPath().pathByAddingChild(previousNode);
                    workingTree.setSelectionPath(newSelection);
                    workingTree.scrollPathToVisible(newSelection);
                } // else nothing will happen and selection doesn't change
            }
        };
    }

    /**
     * Adds an ActionListener that when triggered, moves the selected file in the file tree to the next file.
     * This will ONLY move selection on the same level as the currently selected node. <br>
     * For example: If a directory is selected, the next directory will be selected.
     * OR;<br>
     * If a file is selected, the next file will be selected but selection will never move outside the directory.
     * @return an ActionListener that moves the selected file to the next file.
     */
    public ActionListener moveSelectedToNext() {
        return e -> {
            JTree workingTree = frame.getFileBrowserJPanel().getFileTree();

            TreePath currentSelection = workingTree.getSelectionPath();
            // select the first item if none are selected
            if (currentSelection == null) {
                TreePath newSelection = new TreePath(workingTree.getModel().getRoot());
                workingTree.setSelectionPath(newSelection);
                workingTree.scrollPathToVisible(newSelection);
            } else {
                DefaultMutableTreeNode currentSelectedNode = (DefaultMutableTreeNode) currentSelection.getLastPathComponent();
                DefaultMutableTreeNode nextNode = (DefaultMutableTreeNode) currentSelectedNode.getNextSibling();
                if (nextNode != null) {
                    TreePath newSelection = currentSelection.getParentPath().pathByAddingChild(nextNode);
                    workingTree.setSelectionPath(newSelection);
                    workingTree.scrollPathToVisible(newSelection);
                } // else nothing will happen and selection doesn't change
            }
        };
    }

    /**
     * Adds an ActionListener what when triggered, prompts the user for a file path to add to the file tree.
     * The file will be added to the 'Unknown Directory' in the file tree and must be reloaded.
     * The file must be an image file.
     * @return an ActionListener that prompts the user for a file path to add to the file tree.
     */
    public ActionListener addNewFile() {
        return e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter the path to the image you would like to add.\n " +
                    "You must give an absolute file path.");
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Empty input cannot be read.");
                return;
            }
            File inputFile = new File(input);
            // only accepting image files
            if (FileInspector.isImageFile(inputFile)) {
                ImageFile newImageFile = new ImageFile(inputFile.getAbsolutePath());
                context.addNewSystemFile(newImageFile);
                // add the file to the unknown directory node
                frame.getFileBrowserJPanel().getFileTree().getUnknownDirectoryNode().add(new ImageNode(newImageFile));
            } else {
                JOptionPane.showMessageDialog(frame, "The file you attempted to add is not an image file. \n" +
                        "Or your file path could not be read. \n" +
                        "Valid example: C:\\Users\\User\\Pictures\\image.jpg");
                return;
            }
        };
    }

    /**
     * Adds an ActionListener that when triggered, refreshes the file tree in the file browser panel.
     * This reloads the model of the JTree and repaints the tree.
     * @return an ActionListener that refreshes the file tree in the file browser panel.
     */
    public ActionListener refreshList() {
        return e ->  {
            frame.getFileBrowserJPanel().getFileTree().reloadTree();
        };
    }

    /**
     * Adds an ActionListener that when triggered, deletes the selected file from the file tree.
     * Only files may be removed, not directories.
     * This does not delete the file from the user's system, only from the application's context.
     * @return an ActionListener that deletes the selected file from the file tree.
     */
    public ActionListener deleteSelectedFile() {
        return e ->  {
            JTree workingTree = frame.getFileBrowserJPanel().getFileTree();
            TreePath currentSelection = workingTree.getSelectionPath();
            // the user must select a file before one can be deleted
            if (currentSelection == null) {
                JOptionPane.showMessageDialog(frame, "No file selected to delete.");
                return;
            }

            // only files may be deleted, and currently only ImageNodes are being created by this application
            DefaultMutableTreeNode currentSelectedNode = (DefaultMutableTreeNode) currentSelection.getLastPathComponent();
            if (currentSelectedNode instanceof ImageNode imageNode) {
                context.removeSystemFile(imageNode.getImageFile());
                // delete the node from its parent directory node
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode) currentSelectedNode.getParent();
                parent.remove(currentSelectedNode);
                ((UserFileJTree) workingTree).reloadTree();
            } else {
                JOptionPane.showMessageDialog(frame, "Directories may not deleted.");
            }
        };
    }
}
