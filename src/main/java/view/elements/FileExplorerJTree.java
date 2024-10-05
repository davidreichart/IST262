package view.elements;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;

public class FileExplorerJTree extends JTree {

    public FileExplorerJTree() {
        // Create tree nodes
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Files");

        // Set model
        this.setModel(new DefaultTreeModel(rootNode));
        this.setBackground(new Color(0x2b2d30));

        this.setCellRenderer(new FileExplorerCellRenderer());

        addFileDirectoryToTree("src/main/java");
        expandAllNodes(this);
    }

    /**
     * Expands all nodes in the JTree
     *
     * @param tree JTree to expand
     */
    private void expandAllNodes(JTree tree) {
        // Expand each row in the tree iteratively
        int rowCount = tree.getRowCount();
        for (int row = 0; row < rowCount; row++) {
            tree.expandRow(row);
        }

        // Now, check for children iteratively
        for (int i = 0; i < rowCount; i++) {
            TreePath path = tree.getPathForRow(i);
            // If the path is not null, expand all children
            if (path != null) {
                // Get the number of children
                int childCount = tree.getModel().getChildCount(path.getLastPathComponent());
                // Expand all children
                for (int j = 0; j < childCount; j++) {
                    TreePath childPath = path.pathByAddingChild(tree.getModel().getChild(path.getLastPathComponent(), j));
                    tree.expandPath(childPath);
                }
            }
        }
    }

    /**
     * Adds a given directory to the JTree.
     * Only the files in that directory will be added to the tree.
     * This does not check for nested directories.
     * @param directoryPath String path to the directory to add to the tree
     * @throws IllegalArgumentException If the path provided is not a directory
     */
    public void addFileDirectoryToTree(String directoryPath) throws IllegalArgumentException {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("The path provided is not a directory.");
        }

        File[] files = directory.listFiles();
        // directory node to be added to the tree
        // found files will be nested under this node
        DefaultMutableTreeNode newDirectoryNode = new DefaultMutableTreeNode(directory.getName());

        // this method does not support nested directories only the direct files in the directory will be added
        Arrays.stream(files).forEach(file -> {
            if (file.exists() && !file.isDirectory()) {
                DefaultMutableTreeNode newFileNode = new DefaultMutableTreeNode(file.getName());
                newDirectoryNode.add(newFileNode);
            }
        });

        // adding the new directory node to the tree
        DefaultTreeModel model = (DefaultTreeModel) this.getModel();
        model.insertNodeInto(newDirectoryNode, (MutableTreeNode) model.getRoot(), model.getChildCount(model.getRoot()));
    }

    /**
     * Custom cell renderer for the JTree
     * Styles the tree nodes to have a dark theme
     */
    private static class FileExplorerCellRenderer extends DefaultTreeCellRenderer {
        private boolean selected;

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            this.selected = sel;
            setBackgroundSelectionColor(new Color(0x43454a));
            setBackgroundNonSelectionColor(tree.getBackground());
            setForeground(Color.WHITE);

            return this;
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (isSelected()) {
                Rectangle bounds = getBounds();
                g.setColor(getBackgroundSelectionColor());
                g.fillRect(0, 0, bounds.width, bounds.height); // Fill the entire width and height
            }
            super.paintComponent(g); // this must occur after so the drawn rectangle is under the text
        }

        private boolean isSelected() {
            return this.selected;
        }
    }
}
