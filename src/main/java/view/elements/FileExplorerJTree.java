package view.elements;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.*;
import java.awt.*;

public class FileExplorerJTree extends JTree {

    public FileExplorerJTree() {
        // Create tree nodes
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode parentOne = new DefaultMutableTreeNode("Parent One");
        DefaultMutableTreeNode childOneOne = new DefaultMutableTreeNode("Child One One");
        parentOne.add(childOneOne);

        DefaultMutableTreeNode parentTwo = new DefaultMutableTreeNode("Parent Two");
        DefaultMutableTreeNode childTwoOne = new DefaultMutableTreeNode("Child Two One");
        parentTwo.add(childTwoOne);

        rootNode.add(parentOne);
        rootNode.add(parentTwo);

        // Set model
        this.setModel(new DefaultTreeModel(rootNode));
        this.setBackground(new Color(0x2b2d30));
        for (int i = 0; i < this.getRowCount(); i++) {
            this.expandRow(i);
        }
        this.setCellRenderer(new FileExplorerCellRenderer());
    }

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
