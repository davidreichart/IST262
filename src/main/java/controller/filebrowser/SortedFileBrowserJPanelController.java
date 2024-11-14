package controller.filebrowser;

import model.ApplicationContext;
import model.data.SortedFileList;
import view.ApplicationJFrame;
import view.filebrowser.FileBrowserJPanel;
import view.filebrowser.SortedFileBrowserJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class SortedFileBrowserJPanelController {

    private ApplicationJFrame frame;
    private ApplicationContext context;
    private SortedFileList sortedFileList;
    private SortedFileBrowserJPanel panel;

    public SortedFileBrowserJPanelController(ApplicationJFrame frame, ApplicationContext context) {
        this.frame = frame;
        this.context = context;
        this.sortedFileList = new SortedFileList(this.context);
        this.panel = this.frame.getSortedFileBrowserJPanel();

        this.frame.getApplicationJMenuBar()
                .getSwapFileBrowserJMenuItem()
                .addActionListener(swapLists());
        this.panel.getAddItemButton().addActionListener(addItem());
        this.panel.getRemoveItemButton().addActionListener(removeItem());
        this.panel.getSearchButton().addActionListener(searchItem());
        this.panel.getPrintButton().addActionListener(printToCommandLine());
        this.panel.getFileList().addMouseListener(rightClickItemMenu());
        this.panel.getSearchField().addMouseListener(rightClickSearchTextFieldMenu());
        reRenderSortedFileList();
    }

    public void reRenderSortedFileList() {
        this.panel.updateFileList(this.sortedFileList.getAbsoluteFilePaths());
    }

    /**
     * Returns an ActionListener that will refresh the list of files in the file browser panel.
     * This will swap the file browser view modes.
     * @return The ActionListener that will refresh the list of files in the file browser panel.
     */
    public ActionListener swapLists() {
        return e -> {
            JSplitPane temp = this.frame.getTopFileAndBrowserPane();
            // display the sorted file list
            if (temp.getLeftComponent() instanceof FileBrowserJPanel) {
                temp.removeAll();
                temp.add(this.panel, JSplitPane.LEFT);
                temp.add(this.frame.getFileDisplayJPanel(), JSplitPane.RIGHT);
                temp.setDividerLocation(400); // resizes the panel
                reRenderSortedFileList();
                this.panel.setVisible(true);
                this.frame.getFileBrowserJPanel().setVisible(false);
            // display the default file tree
            } else if (temp.getLeftComponent() instanceof SortedFileBrowserJPanel) {
                temp.removeAll();
                temp.add(this.frame.getFileBrowserJPanel(), JSplitPane.LEFT);
                temp.add(this.frame.getFileDisplayJPanel(), JSplitPane.RIGHT);
                this.panel.setVisible(false);
                this.frame.getFileBrowserJPanel().setVisible(true);
            }
        };
    }

    public ActionListener addItem() {
        return e -> {
            String newItem = this.panel.getSearchField().getText();
            try {
                this.sortedFileList.addItem(newItem);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this.frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            reRenderSortedFileList();
        };
    }

    public ActionListener removeItem() {
        return e -> {
            String itemToRemove = this.panel.getSearchField().getText();
            try {
                this.sortedFileList.removeItem(itemToRemove);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this.frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            reRenderSortedFileList();
        };
    }

    public ActionListener searchItem() {
        return e -> {
            String searchTerm = this.panel.getSearchField().getText();
            String item = this.sortedFileList.getItem(searchTerm);
            if (!item.isEmpty()) {
                this.panel.getFileList().setSelectedValue(item, true);
            }
        };
    }

    public ActionListener printToCommandLine() {
        return e -> this.sortedFileList.printToCommandLine();
    }

    public MouseAdapter rightClickItemMenu() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {
                    JList<String> list = panel.getFileList();
                    int index = list.locationToIndex(evt.getPoint()); // what was clicked?
                    list.setSelectedIndex(index);
                    JPopupMenu menu = new JPopupMenu();

                    // when the user right clicks, let them append the item to their clipboard
                    JMenuItem copy = new JMenuItem("Copy");
                    copy.addActionListener(e -> {
                        String selected = list.getSelectedValue();
                        if (selected != null) {
                            StringSelection selection = new StringSelection(selected);
                            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                            clipboard.setContents(selection, selection);
                        }
                    });
                    menu.add(copy);

                    // force the popup to be where the event happened
                    menu.show(list, evt.getX(), evt.getY());
                }
            }
        };
    }

    public MouseAdapter rightClickSearchTextFieldMenu() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {
                    JTextField searchField = panel.getSearchField();
                    JPopupMenu menu = new JPopupMenu();

                    // when the user right clicks, let them paste from their clipboard
                    JMenuItem paste = new JMenuItem("Paste");
                    paste.addActionListener(e -> {
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                            try {
                                String contents = (String) clipboard.getData(DataFlavor.stringFlavor);
                                searchField.setText(contents);
                                searchField.setForeground(Color.BLACK);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame, "Error pasting from clipboard.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    menu.add(paste);

                    // force the popup to be where the event happened
                    menu.show(searchField, evt.getX(), evt.getY());
                }
            }
        };
    }
}
