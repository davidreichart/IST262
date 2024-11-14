package controller.filedata;

import model.ApplicationContext;
import model.data.filetypes.ImageFile;
import model.data.filetypes.SystemFile;
import model.util.FileInspector;
import view.ApplicationJFrame;
import view.filebrowser.FileBrowserJPanel;
import view.filebrowser.SortedFileBrowserJPanel;
import view.filebrowser.UserFileJTree;
import view.filebrowser.nodes.DirectoryNode;
import view.filebrowser.nodes.ImageNode;
import view.filedata.FileStatisticsJPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
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

        // display table of metadata when a file is selected in the browser
        userFileJTree.addTreeSelectionListener(displayFileMetadataInFileStatisticsJPanel());

        // file path edit
        fileStatisticsJPanel.getEditFilePathButton()
                .addActionListener(editFilePathButtonActionListener());

        // file size edit
        fileStatisticsJPanel.getEditFileSizeButton()
                .addActionListener(editFileSizeButtonActionListener());

        // image height edit
        fileStatisticsJPanel.getEditImageHeightButton()
                .addActionListener(editImageHeightButtonActionListener());

        // image width edit
        fileStatisticsJPanel.getEditImageWidthButton()
                .addActionListener(editImageWidthButtonActionListener());

        frame.getSortedFileBrowserJPanel()
                .getFileList()
                .addListSelectionListener(displaySortedFilePanelStatistics());
        frame.getSortedFileBrowserJPanel()
                .getFileList()
                .addListSelectionListener(enableButtonsWhenItemIsSelected());
        fileStatisticsJPanel.getEditFilePathButton()
                .addActionListener(ALT_editFilePathButtonActionListener());
        fileStatisticsJPanel.getEditFileSizeButton()
                .addActionListener(ALT_editFileSizeButtonActionListener());
        fileStatisticsJPanel.getEditImageHeightButton()
                .addActionListener(ALT_editImageHeightButtonActionListener());
        fileStatisticsJPanel.getEditImageWidthButton()
                .addActionListener(ALT_editImageWidthButtonActionListener());
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
                } else if (node instanceof DirectoryNode) {
                    // directories shouldn't show metadata
                    fileStatisticsJPanel.removeAll();
                    fileStatisticsJPanel.add(fileStatisticsJPanel.getControlsPanel(), BorderLayout.WEST);
                    disableAllEditButtons();
                    fileStatisticsJPanel.repaint();
                    fileStatisticsJPanel.revalidate();
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
                    if (!FileInspector.isFile(input)) {
                        JOptionPane.showMessageDialog(
                                frame,
                                "The file path you entered is not valid.",
                                "Invalid File Path",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
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

                // empty input check
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "The file size cannot be empty.",
                            "Invalid File Size",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // numeric / negative input check
                for (char c : input.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        JOptionPane.showMessageDialog(
                                frame,
                                "The file size must only consist of numbers AND be a positive value.",
                                "Invalid File Size",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                long newSize = Long.parseLong(input);

                SystemFile.Metadata updatedMetadata = new SystemFile.Metadata(newSize, imageFile.METADATA().absoluteFilePath());
                imageFile.setMETADATA(updatedMetadata);
                renderImageMetadataTable((ImageNode) node);
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

                // empty input check
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "The image height cannot be empty.",
                            "Invalid Image Height",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // numeric / negative input check
                for (char c : input.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        JOptionPane.showMessageDialog(
                                frame,
                                "The image height must only consist of numbers AND be a positive value",
                                "Invalid Image Height",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                int newHeight = Integer.parseInt(input);

                ImageFile.ImageMetadata updatedMetadata = new ImageFile.ImageMetadata(imageFile.IMAGE_METADATA().width(), newHeight, imageFile.IMAGE_METADATA().colorHistogram());
                imageFile.setIMAGE_METADATA(updatedMetadata);
                renderImageMetadataTable((ImageNode) node);
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

                // empty input check
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "The image width cannot be empty.",
                            "Invalid Image Width",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // numeric / negative input check
                for (char c : input.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        JOptionPane.showMessageDialog(
                                frame,
                                "The image width must only consist of numbers AND be a positive value",
                                "Invalid Image Width",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                ImageFile.ImageMetadata updatedMetadata = new ImageFile.ImageMetadata(Integer.parseInt(input), imageFile.IMAGE_METADATA().height(), imageFile.IMAGE_METADATA().colorHistogram());
                imageFile.setIMAGE_METADATA(updatedMetadata);
                renderImageMetadataTable((ImageNode) node);
            }
        };
    }

    public ListSelectionListener enableButtonsWhenItemIsSelected() {
        return e -> {
            if (frame.getSortedFileBrowserJPanel().getFileList().getSelectedValue() != null) {
                enableAllEditButtons();
            } else {
                disableAllEditButtons();
            }
        };
    }

    /**
     * Displays the metadata of the selected file in the FileStatisticsJPanel.
     * @return A ListSelectionListener that displays the metadata of the selected file in the FileStatisticsJPanel.
     */
    public ListSelectionListener displaySortedFilePanelStatistics() {
        return e -> {
            JList<String> list = frame.getSortedFileBrowserJPanel().getFileList();
            String selection = list.getSelectedValue();
            // stream filter for selection
            SystemFile selectedFile = context.getSystemFiles().stream()
                    .filter(f -> f.METADATA().absoluteFilePath().equals(selection))
                    .findFirst()
                    .orElse(null);
            if (selectedFile == null) {
                // item doesnt exist, do nothing
                return;
            }
            // making a table out of the metadata on this image file
            ImageFile imageFile = (ImageFile) selectedFile;

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

            // update GUI
            fileStatisticsJPanel.removeAll();
            fileStatisticsJPanel.add(fileStatisticsJPanel.getControlsPanel(), BorderLayout.WEST);
            fileStatisticsJPanel.add(fileMetadataTable, BorderLayout.CENTER);
            fileStatisticsJPanel.revalidate();
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

    /**
     * Action listener for {@link SortedFileBrowserJPanel} edit image width button.
     * @return ActionListener for edit image width button.
     */
    private ActionListener ALT_editImageWidthButtonActionListener() {
        return e -> {
            // this listener only works on the SortedFileBrowser
            Component current = this.frame.getTopFileAndBrowserPane().getLeftComponent();
            if (current instanceof FileBrowserJPanel) {
                return;
            }
            JList<String> list = frame.getSortedFileBrowserJPanel().getFileList();
            int index = list.getSelectedIndex();
            String selection = list.getSelectedValue();
            if (selection == null) {
                return;
            }

            // stream filter for selection
            SystemFile selectedFile = context.getSystemFiles().stream()
                    .filter(f -> f.METADATA().absoluteFilePath().equals(selection))
                    .findFirst()
                    .orElse(null);
            if (selectedFile == null) {
                // item doesnt exist, do nothing
                return;
            }

            ImageFile imageFile = (ImageFile) selectedFile;
            String input = JOptionPane.showInputDialog("Enter a new image width for this object:");

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(
                        frame,
                        "The image width cannot be empty.",
                        "Invalid Image Width",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            // numeric / negative input check
            for (char c : input.toCharArray()) {
                if (!Character.isDigit(c)) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "The image width must only consist of numbers AND be a positive value",
                            "Invalid Image Width",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            ImageFile.ImageMetadata updatedMetadata = new ImageFile.ImageMetadata(Integer.parseInt(input), imageFile.IMAGE_METADATA().height(), imageFile.IMAGE_METADATA().colorHistogram());
            imageFile.setIMAGE_METADATA(updatedMetadata);
            // flick back and forth to trigger another event listener and update the table
            if (index - 1 < 0) {
                list.setSelectedIndex(index + 1);
                list.setSelectedIndex(index);
            } else {
                list.setSelectedIndex(index - 1);
                list.setSelectedIndex(index);
            }
        };
    }

    /**
     * Action listener for {@link SortedFileBrowserJPanel} edit image height button.
     * @return ActionListener for edit image height button.
     */
    private ActionListener ALT_editImageHeightButtonActionListener() {
        return e -> {
            // this listener only works on the SortedFileBrowser
            Component current = this.frame.getTopFileAndBrowserPane().getLeftComponent();
            if (current instanceof FileBrowserJPanel) {
                return;
            }
            JList<String> list = frame.getSortedFileBrowserJPanel().getFileList();
            int index = list.getSelectedIndex();
            String selection = list.getSelectedValue();
            if (selection == null) {
                return;
            }

            // stream filter for selection
            SystemFile selectedFile = context.getSystemFiles().stream()
                    .filter(f -> f.METADATA().absoluteFilePath().equals(selection))
                    .findFirst()
                    .orElse(null);
            if (selectedFile == null) {
                // item doesnt exist, do nothing
                return;
            }

            ImageFile imageFile = (ImageFile) selectedFile;
            String input = JOptionPane.showInputDialog("Enter a new image height for this object:");

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(
                        frame,
                        "The image height cannot be empty.",
                        "Invalid Image Height",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            // numeric / negative input check
            for (char c : input.toCharArray()) {
                if (!Character.isDigit(c)) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "The image height must only consist of numbers AND be a positive value",
                            "Invalid Image Height",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            int newHeight = Integer.parseInt(input);

            ImageFile.ImageMetadata updatedMetadata = new ImageFile.ImageMetadata(imageFile.IMAGE_METADATA().width(), newHeight, imageFile.IMAGE_METADATA().colorHistogram());
            imageFile.setIMAGE_METADATA(updatedMetadata);

            // flick back and forth to trigger another event listener and update the table
            if (index - 1 < 0) {
                list.setSelectedIndex(index + 1);
                list.setSelectedIndex(index);
            } else {
                list.setSelectedIndex(index - 1);
                list.setSelectedIndex(index);
            }
        };
    }

    /**
     * Action listener for {@link SortedFileBrowserJPanel} edit file size button.
     * @return ActionListener for edit file size button.
     */
    private ActionListener ALT_editFileSizeButtonActionListener() {
        return e -> {
            // this listener only works on the SortedFileBrowser
            Component current = this.frame.getTopFileAndBrowserPane().getLeftComponent();
            if (current instanceof FileBrowserJPanel) {
                return;
            }
            JList<String> list = frame.getSortedFileBrowserJPanel().getFileList();
            int index = list.getSelectedIndex();
            String selection = list.getSelectedValue();
            if (selection == null) {
                return;
            }

            // stream filter for selection
            SystemFile selectedFile = context.getSystemFiles().stream()
                    .filter(f -> f.METADATA().absoluteFilePath().equals(selection))
                    .findFirst()
                    .orElse(null);
            if (selectedFile == null) {
                // item doesn't exist, do nothing
                return;
            }

            ImageFile imageFile = (ImageFile) selectedFile;
            String input = JOptionPane.showInputDialog("Enter a new file size for this object:");

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(
                        frame,
                        "The file size cannot be empty.",
                        "Invalid File Size",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            // numeric / negative input check
            for (char c : input.toCharArray()) {
                if (!Character.isDigit(c)) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "The file size must only consist of numbers AND be a positive value",
                            "Invalid File Size",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            long newSize = Long.parseLong(input);

            SystemFile.Metadata updatedMetadata = new SystemFile.Metadata(newSize, imageFile.METADATA().absoluteFilePath());
            imageFile.setMETADATA(updatedMetadata);

            // flick back and forth to trigger another event listener and update the table
            if (index - 1 < 0) {
                list.setSelectedIndex(index + 1);
                list.setSelectedIndex(index);
            } else {
                list.setSelectedIndex(index - 1);
                list.setSelectedIndex(index);
            }
        };
    }

    /**
     * Action listener for {@link SortedFileBrowserJPanel} edit file path button.
     * @return ActionListener for edit file path button.
     */
    private ActionListener ALT_editFilePathButtonActionListener() {
        return e -> {
            // this listener only works on the SortedFileBrowser
            Component current = this.frame.getTopFileAndBrowserPane().getLeftComponent();
            if (current instanceof FileBrowserJPanel) {
                return;
            }
            JList<String> list = frame.getSortedFileBrowserJPanel().getFileList();
            int index = list.getSelectedIndex();
            String selection = list.getSelectedValue();
            if (selection == null) {
                return;
            }

            // stream filter for selection
            SystemFile selectedFile = context.getSystemFiles().stream()
                    .filter(f -> f.METADATA().absoluteFilePath().equals(selection))
                    .findFirst()
                    .orElse(null);
            if (selectedFile == null) {
                // item doesnt exist, do nothing
                return;
            }

            ImageFile imageFile = (ImageFile) selectedFile;
            String input = JOptionPane.showInputDialog("Enter a new file path for this object:");

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(
                        frame,
                        "The file path cannot be empty.",
                        "Invalid File Path",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            SystemFile.Metadata updatedMetadata = new SystemFile.Metadata(imageFile.METADATA().byteCount(), input);
            imageFile.setMETADATA(updatedMetadata);

            // flick back and forth to trigger another event listener and update the table
            if (index - 1 < 0) {
                list.setSelectedIndex(index + 1);
                list.setSelectedIndex(index);
            } else {
                list.setSelectedIndex(index - 1);
                list.setSelectedIndex(index);
            }
        };
    }
}
